package cool;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.Vector;

import cool.AST.class_;


public class Semantic{
	private boolean errorFlag = false;
	public void reportError(String filename, int lineNo, String error){
		errorFlag = true;
		System.err.println(filename+":"+lineNo+": "+error);
	}
	public boolean getErrorFlag(){
		return errorFlag;
	}

/*
	Don't change code above this line
*/

	/*
	 * ScopeTable <AST.attr> : Stores attributes for the current scope. Attributes (redefined) include class attributes, let-declarations, method-parameters, assign-variable
	 * classTable			: Stores HashMap<String, ClassPlus>. Helps retrieve methods within class and check for correct method overrides and attribute override errors.
	 * filename				: Filename of current class being explored.
	 */
	ScopeTable<AST.attr> scopeTable = new ScopeTable<AST.attr>();
	ClassTable classTable = new ClassTable();
	String filename;
	
	public Semantic(AST.program program){
		//Write Semantic analyzer code here
		
		/*
		 * ProcessGraph method checks for bad class hierarchies and creates an inheritance graph.
		 * It also checks for wrong method overrides and attribute override errors.
		 */
		ProcessGraph(program.classes);
		List<Error> errors = classTable.getErrors();	// ClassTable cannot access reportError method (its not static). Thus, errors are returned in a list (from classTable) and printed.
		for(Error e : errors) {
			reportError(e.fname, e.line, e.err);
		}
		
		for(AST.class_ e : program.classes) {
			filename = e.filename;				// filename for each class	
			scopeTable.enterScope();			// enter new scope for a class
			scopeTable.insert("self", new AST.attr("self", e.name, new AST.no_expr(e.lineNo), e.lineNo));		// self is available as attribute within the class
			scopeTable.insertAll(classTable.getAttrs(e.name));		// insert all inherited and other declared attributes within the class into the scope
			ProcessNode(e);
			////System.out.println("###Finished processing class " +  e.name);
			scopeTable.exitScope();				// once class is processed, exit the scope.
		}
		
		/* The following checks:
		 * - If "Main" class is present in the program.
		 * - If the "Main" class is present, does it have a 'main' method.
		 */
		ClassPlus main_class = classTable.getClassPlus("Main");
		if(main_class == null)
			reportError(filename, 1, "Program does not contain class 'Main'");
		else if(main_class.mlist.containsKey("main") == false)
			reportError(filename, 1, "'Main' class does not contain 'main' method");
		
		
	}
	
	private void ProcessGraph(List <AST.class_> classes) {
		
		/* Steps:
		 * First create the graph and check inheritance and shite.
		 * After checking inheritance, create the classTable class nodes and repopulate with methods only.
		 * While repopulating check for method errors.
		 * Before entering class, push stuff in the attributes and recheck for attribute related errors.
		 * Attributes are straight inherited from the parent class and overloading is checked. Types of parent class are retained.
		 * Done!
		 */
		
		/* Steps followed:
		 * - For each class in the class_list:
		 * 		+ assign a corresponding integer.
		 * 		+ check if the class redefines any class from the set { "IO", "Object", "String", "Int", "Bool" }
		 * 		+ check if the class inherits from any class from the set { "String", "Int", "Bool" }
		 * - Create a directed graph using the integers assigned above.
		 * - Check the graph for cycles.
		 * - If no cycles present, populate ClassTable in bfs order (more on that later)
		 * 
		 * Note : All errors in this method are fatal and signal a problem in the class hierarchy.
		 * Thus, the semantic analyzer immediately terminates after reporting the error.
		 */
		
		Integer sz = 0;		// stores the number of classes
		HashMap <String, AST.class_> idxCont = new HashMap <String, AST.class_> ();
		HashMap <String, Integer> classIdx = new HashMap <String, Integer> ();
		HashMap <Integer, String> idxName = new HashMap <Integer, String>();
		ArrayList < ArrayList <Integer> > classGraph = new ArrayList < ArrayList <Integer> >();
		
		List <String> no_redef = Arrays.asList("Object", "String", "Int", "Bool", "IO");
		List <String> no_inherit = Arrays.asList("String", "Int", "Bool");

		/* Laying the groundwork */
		classIdx.put("Object", 0);
		idxName.put(0, "Object");
		classIdx.put("IO", 1);
		idxName.put(0, "IO");
		
		classGraph.add(new ArrayList <Integer> (Arrays.asList(1)));
		classGraph.add(new ArrayList <Integer>());	// for IO
		
		sz = sz + 2;	// IO and Object (2 classes) already present
		
		/* Checking for :
		 * - bad redefinitions
		 * - bad inheritance
		 * Also : assigning an integer corresponding to each class.
		 */
		for(AST.class_ e : classes) {
			if(no_redef.contains(e.name)) {
				reportError(e.filename, e.lineNo, "Cannot redefine class : " + e.name);
				System.exit(1);
			}
			else if(no_inherit.contains(e.parent)) {
				reportError(e.filename, e.lineNo, "Class cannot inherit : " + e.parent);
				System.exit(1);
			}
			else if(classIdx.containsKey(e.name) == false) {
				idxName.put(sz, e.name);			// Reverse lookup. Integer -> className
				classIdx.put(e.name, sz++);			// className -> Integer
				idxCont.put(e.name, e);				// getting the class from name. Used later.
				classGraph.add(new ArrayList <Integer> ());
			}
		}
		
		/* We are creating an undirected graph in this method.
		 * Also: Checking for - undefined parent class error
		 */
		for(AST.class_ e : classes) {
			if(classIdx.containsKey(e.parent) == false) {
				reportError(e.filename, e.lineNo, "Parent class not found : " + e.parent);
				System.exit(1);
			}
			int u = classIdx.get(e.parent);
			int v = classIdx.get(e.name);
			classGraph.get(u).add(v);			// adding an edge from parent -> child in the graph
		}
		
		boolean cycles = false;
		Boolean[] visited = new Boolean[sz + 10];
		Arrays.fill(visited, Boolean.FALSE);
		Queue<Integer> q = new LinkedList<Integer>(); q.offer(0);
		
		/*
		 * The following loop navigates the graph in BFS-order. The graph is checked for cycles.
		 * If multiple cycles exist, they are all reported.
		 */		
		while (!q.isEmpty()) {
			int u = q.poll();
			if(visited[u] == false)
				visited[u] = true;
			else {
				reportError(idxCont.get(idxName.get(u)).filename, 1, "Class " +  idxName.get(u) + ", or an ancestor of " + idxName.get(u) + ", is involved in an inheritance cycle.");
				cycles = true;		// `cycles` is set to true if cycles are found
				if(q.isEmpty()) {
					for(int i = 0; i < sz; ++i)
						if(visited[i] == false) {
							q.offer(i);
							break;
						}
				}
				continue;
			}
			for(Integer v : classGraph.get(u)) {
				q.offer(v);
			}
			if(q.isEmpty()) {
				for(int i = 0; i < sz; ++i)
					if(visited[i] == false) {
						q.offer(i);
						break;
					}
			}
		}
		
		if(cycles) System.exit(1);		// exit if cycles found
				
		q.clear(); q.offer(0);
		
		while (!q.isEmpty()) {
			int u = q.poll();
			if(u != 1 && u != 0) {
				classTable.insert(idxCont.get(idxName.get(u)));		// insert classes in BFS-order so that methods and attributes can be inherited.
			}
			for(Integer v : classGraph.get(u)) {
				q.offer(v);
			}
		}
	}
	
	private void ProcessNode(AST.class_ class_) {
		/* The method checks if the features are
		 * attr or method. Corresponding overloaded
		 * function is called.
		 */
		for(AST.feature e : class_.features) {
			if(e.getClass() == AST.method.class) {
				ProcessNode((AST.method)e);
			}
			else if(e.getClass() == AST.attr.class) {
				ProcessNode((AST.attr)e);
			}
		}
	}
	
	private void ProcessNode(AST.method method) {
		AST.attr a_self = scopeTable.lookUpLocal("self");	// getting the self (class) object to get class name.
		
		scopeTable.enterScope();
		
		for(AST.formal e : method.formals) {
			AST.feature f = scopeTable.lookUpLocal(e.name);
			/* Error:
			 * repeat formal parameters : Example func(x : Int, y : Bool, x : String) : Object { ... }// x is multiply defined
			 */
			if(f != null && f.getClass() == AST.attr.class) {
				AST.attr a_f = (AST.attr) f;
				reportError(filename, a_f.lineNo, "Formal parameter " + a_f.name + " is multiply defined.");
			}
			scopeTable.insert(e.name, new AST.attr(e.name, e.typeid, new AST.no_expr(e.lineNo), e.lineNo));
		}
		ProcessNode(method.body);
		// if return type conforms to the method type, then hurray
		if(classTable.conforms(method.body.type, method.typeid) == false) {
			reportError(filename, method.body.lineNo, "Inferred return type " + method.body.type + 
					" of method " + method.name + " does not conform to declared return type " + method.typeid);
		}
		scopeTable.exitScope();
	}
	
	private void ProcessNode(AST.attr attr) {
		AST.attr a_self = scopeTable.lookUpLocal("self");	// getting the self (class) object to get class name.
		
		if(attr.value.getClass() != AST.no_expr.class) {
			ProcessNode(attr.value);
			// if return type conforms to the method type, then hurray
			if(classTable.conforms(attr.value.type, attr.typeid) == false) {
				reportError(filename, attr.value.lineNo, "Inferred type " + attr.value.type + " of initialization of attribute "
						+ attr.name + " does not conform to declared type " + attr.typeid);
			}
		}
	}
	
	private void ProcessNode(AST.expression expr) {
		/* ProcessNode functions given in the following order
		 * program = class_list
		 * class = 
		 * feature = method / attribute
		 * ID <- expr [ASSIGN] 
		 * <expr>.<id>(<expr>,....) [DISPATCH]
		 * <expr>@<type>.<id> (<expr>,....) [STATIC DISPATCH]
		 * if <expr> then <expr> else <expr> fi	[COND]
		 * while <expr> loop <expr> pool [WHILE]
		 * { <expr>, <expr>, ... }	[BLOCK]
		 * let ID : TYPE <- expr	[LET-EXPR]
		 * case <expr> of ID : TYPE => expr esac [CASE]
		 * new TYPE	[NEW]
		 * isvoid <expr> [ISVOID]
		 * expr + expr	[PLUS]
		 * expr - expr	[SUB]
		 * expr * expr	[MUL]
		 * expr / expr	[DIVIDE]
		 * ~expr		[COMP]
		 * expr < expr	[LT]
		 * expr <= expr	[LEQ]
		 * expr = expr	[EQ]
		 * not expr		[NEG]
		 * (expr)		
		 * ID			[
		 * integer		[INT_CONST]
		 * string		[STRING_CONST]
		 * true			[BOOL_CONST]
		 * false		[BOOL_CONST]
		 */
		if(expr.getClass() == AST.assign.class)
			ProcessNode((AST.assign)expr);
		else if(expr.getClass() == AST.static_dispatch.class)
			ProcessNode((AST.static_dispatch)expr);
		else if(expr.getClass() == AST.dispatch.class)
			ProcessNode((AST.dispatch)expr);
		else if(expr.getClass() == AST.cond.class)
			ProcessNode((AST.cond)expr);
		else if(expr.getClass() == AST.loop.class)
			ProcessNode((AST.loop)expr);
		else if(expr.getClass() == AST.block.class)
			ProcessNode((AST.block)expr);
		else if(expr.getClass() == AST.let.class)
			ProcessNode((AST.let)expr);
		else if(expr.getClass() == AST.typcase.class)
			ProcessNode((AST.typcase)expr);
		else if(expr.getClass() == AST.new_.class)
			ProcessNode((AST.new_)expr);
		else if(expr.getClass() == AST.isvoid.class)
			ProcessNode((AST.isvoid)expr);
		else if(expr.getClass() == AST.plus.class)
			ProcessNode((AST.plus)expr);
		else if(expr.getClass() == AST.sub.class)
			ProcessNode((AST.sub)expr);
		else if(expr.getClass() == AST.mul.class)
			ProcessNode((AST.mul)expr);
		else if(expr.getClass() == AST.divide.class)
			ProcessNode((AST.divide)expr);
		else if(expr.getClass() == AST.comp.class)
			ProcessNode((AST.comp)expr);
		else if(expr.getClass() == AST.lt.class)
			ProcessNode((AST.lt)expr);
		else if(expr.getClass() == AST.leq.class)
			ProcessNode((AST.leq)expr);
		else if(expr.getClass() == AST.eq.class)
			ProcessNode((AST.eq)expr);
		else if(expr.getClass() == AST.neg.class)
			ProcessNode((AST.neg)expr);
		else if(expr.getClass() == AST.object.class)
			ProcessNode((AST.object)expr);
		else if(expr.getClass() == AST.int_const.class)
			ProcessNode((AST.int_const)expr);
		else if(expr.getClass() == AST.string_const.class)
			ProcessNode((AST.string_const)expr);
		else if(expr.getClass() == AST.bool_const.class)
			ProcessNode((AST.bool_const)expr);
	}
	
	private void ProcessNode(AST.assign assign) {
		ProcessNode(assign.e1);
		AST.attr a = scopeTable.lookUpGlobal(assign.name);
		/* Errors:
		 * - Variable has not been declared
		 * - Type of expression being assigned to the variable does not conform to the type of the variable
		 */
		if(a == null)
			reportError(filename, assign.lineNo, "Assignment to undeclared variable " + assign.name);
		else if(classTable.conforms(assign.e1.type, a.typeid) == false)
			reportError(filename, assign.lineNo, "Type " + assign.e1.type + " of assigned expression does not conform to declared type "
					+ a.typeid + " of identifier " + a.name);
		assign.type = assign.e1.type;
	}
	private void ProcessNode(AST.static_dispatch sd) {
		AST.method m = null;
		boolean found = false;
		ProcessNode(sd.caller);				// first process the caller.
		
		for(AST.expression e : sd.actuals)	// then process all of the actual parameters (left-to-right)
			ProcessNode(e);

		/* Errors:
		 * - Class is not defined
		 * - The caller's type does not conformed to the dispatched class type.
		 * - Method dispatched does not exist.
		 * - Dispatched method is invoked with the wrong number of arguments
		 * - The types of the method actual parameters don't conform to the types of the method formal parameters
		 */
		ClassPlus c = classTable.getClassPlus(sd.typeid);
		if(c == null)
			reportError(filename, sd.lineNo, "Static dispatch to undefined class " + sd.typeid);
		else if(classTable.conforms(sd.caller.type, c.name) == false)
			reportError(filename, sd.lineNo, "Expression type " + sd.caller.type + " does not conform to declared static dispatch type " + c.name);
		else {
			if(c.mlist.containsKey(sd.name)) {
				found = true;
				m = c.mlist.get(sd.name);
				if(sd.actuals.size() != m.formals.size())
					reportError(filename, sd.lineNo, "Method " + m.name + " invoked with wrong number of arguments.");
				else {
					for(int i = 0; i < sd.actuals.size(); ++i) {
						String actual_type = sd.actuals.get(i).type;
						String formal_type = m.formals.get(i).typeid;
						if(classTable.conforms(actual_type, formal_type) == false)
							reportError(filename, sd.lineNo, "In call of method " + m.name + ", type " + actual_type + " does not conform to declared type " + formal_type);			
					}
				}	
			}
			else {
				reportError(filename, sd.lineNo, "Static dispatch to undefined method " + sd.name);
			}
		}
		if(found)
			sd.type = m.typeid;
		else
			sd.type = "Object";
		
		/* Example errors:
		 * testing.cl:1: Static dispatch to undefined method g.
		 * testing.cl:1: Static dispatch to undefined class Xash.
		 * testing.cl:1: Undeclared identifier zx.
		 * testing.cl:1: Expression type Bash does not conform to declared static dispatch type Hash.
		 * testing.cl:1: Method f invoked with wrong number of arguments.
		 * testing.cl:1: In call of method f, type String of parameter x does not conform to declared type Int.
		 */
	}
	
	private void ProcessNode(AST.dispatch dispatch) {
		AST.method m = null;
		boolean found = false;

		ProcessNode(dispatch.caller);

		
		for(AST.expression e : dispatch.actuals)
			ProcessNode(e);
		
		ClassPlus c = classTable.getClassPlus(dispatch.caller.type);
		if(c == null) {
			reportError(filename, dispatch.lineNo, "Class " + dispatch.caller.type + " is undefined.");
		} else {
			if(c.mlist.containsKey(dispatch.name)) {
				//System.out.println("Found the method " + dispatch.name  + " in class " + c.name);
				found = true;
				m = c.mlist.get(dispatch.name);
				//System.out.println("Return type of method " + m.name + " is " + m.typeid);
				if(dispatch.actuals.size() != m.formals.size())
					reportError(filename, dispatch.lineNo, "Method " + m.name + " invoked with wrong number of arguments.");
				else {
					for(int i = 0; i < dispatch.actuals.size(); ++i) {
						String actual_type = dispatch.actuals.get(i).type;
						String formal_type = m.formals.get(i).typeid;
						if(classTable.conforms(actual_type, formal_type) == false)
							reportError(filename, dispatch.lineNo, "In call of method " + m.name + ", type " + actual_type + " does not conform to declared type " + formal_type);			
					}
				}	
			}
			else {
				reportError(filename, dispatch.lineNo, "Dispatch to undefined method " + dispatch.name);
			}
		}
		if(found)
			dispatch.type = m.typeid;
		else
			dispatch.type = "Object";
	
	}
	private void ProcessNode(AST.cond cond) {

		ProcessNode(cond.predicate);
		if(cond.predicate.type.equals("Bool") == false) {
			reportError(filename, cond.predicate.lineNo, "Predicate of 'if' does not have type Bool.");
		}
		ProcessNode(cond.ifbody);
		ProcessNode(cond.elsebody);
		cond.type = classTable.lca(cond.ifbody.type, cond.elsebody.type);
	}
	private void ProcessNode(AST.loop loop) {

		ProcessNode(loop.predicate);
		if(loop.predicate.type.equals("Bool") == false) {
			reportError(filename, loop.predicate.lineNo, "Loop condition does not have type Bool.");
		}
		ProcessNode(loop.body);
		loop.type = "Object";
	}
	private void ProcessNode(AST.block block) {
		for(AST.expression e : block.l1)
			ProcessNode(e);
		block.type = block.l1.get(block.l1.size() - 1).type;
	}
	private void ProcessNode(AST.let let) {
		if(let.value.getClass() != AST.no_expr.class) {
			ProcessNode(let.value);
			if(classTable.conforms(let.value.type, let.typeid) == false)
				reportError(filename, let.lineNo, "Inferred type of " + let.value.type + " of initialization"
						+ "of " + let.name + " does not conform to idenitifier's declared type " + let.typeid);
		}
		scopeTable.enterScope();
		scopeTable.insert(let.name, new AST.attr(let.name, let.typeid, let.value, let.lineNo));
		ProcessNode(let.body);
		/*
		 * testing.cl:1: Inferred type String of initialization of b does not conform to identifier's declared type Int.
		 */
		let.type = let.body.type;
		scopeTable.exitScope();
	}
	private void ProcessNode(AST.typcase typcase) {
		ProcessNode(typcase.predicate);
		for(AST.branch e : typcase.branches) {
			scopeTable.enterScope();
			ClassPlus c = classTable.getClassPlus(e.type);
			if(c == null) {
				reportError(filename, e.lineNo, "Class " + e.type + " of case branch is undefined.");
				scopeTable.insert(e.name, new AST.attr(e.name, "Object", e.value, e.lineNo));	// In the case of erroneous branch type, branch variable has type "Object" for the scope.
			}
			else scopeTable.insert(e.name, new AST.attr(e.name, e.type, e.value, e.lineNo));
			ProcessNode(e.value);
			scopeTable.exitScope();
		}
		HashMap <String, Boolean> br_types = new HashMap<String, Boolean> ();
		AST.branch b = typcase.branches.get(0);
		String typ = b.value.type;
		
		for(AST.branch br : typcase.branches) {
			if(br_types.containsKey(br.type) == false)
				br_types.put(br.type, true);
			else
				reportError(filename, br.lineNo, "Duplicate branch " + br.type + " in case statement.");
			typ = classTable.lca(typ, br.value.type);
		}
		typcase.type = typ;
	}

	private void ProcessNode(AST.new_ new_) {
		ClassPlus c = classTable.getClassPlus(new_.typeid);
		if(c == null) {
			reportError(filename, new_.lineNo, "'new' used with undefined class " + new_.typeid);
			new_.type = "Object";
		} else
			new_.type = new_.typeid;
	}
	private void ProcessNode(AST.isvoid isvoid) {
		isvoid.type = "Bool";
	}
	private void ProcessNode(AST.plus plus) {
		ProcessNode(plus.e1);
		ProcessNode(plus.e2);
		if(plus.e1.type.equals("Int") == false || plus.e2.type.equals("Int") == false) {
			reportError(filename, plus.lineNo, "non-Int arguments: " + plus.e1.type + " + " + plus.e2.type);
		}
		plus.type = "Int";
	}
	private void ProcessNode(AST.sub sub) {
		ProcessNode(sub.e1);
		ProcessNode(sub.e2);
		if(sub.e1.type.equals("Int") == false || sub.e2.type.equals("Int") == false) {
			reportError(filename, sub.lineNo, "non-Int arguments: " + sub.e1.type + " - " + sub.e2.type);
		}
		sub.type = "Int";
	}
	
	private void ProcessNode(AST.mul mul) {
		ProcessNode(mul.e1);
		ProcessNode(mul.e2);
		if(mul.e1.type .equals("Int") == false || mul.e2.type.equals("Int") == false) {
			reportError(filename, mul.lineNo, "non-Int arguments: " + mul.e1.type + " * " + mul.e2.type);
		}
		mul.type = "Int";
	}
	
	private void ProcessNode(AST.divide divide) {
		ProcessNode(divide.e1);
		ProcessNode(divide.e2);
		if(divide.e1.type .equals("Int") == false || divide.e2.type.equals("Int") == false) {
			reportError(filename, divide.lineNo, "non-Int arguments: " + divide.e1.type + " / " + divide.e2.type);
		}
		divide.type = "Int";
		
	}
	private void ProcessNode(AST.comp comp) {	// comp is NOT

		ProcessNode(comp.e1);

		if(comp.e1.type.equals("Bool") == false)
			reportError(filename, comp.lineNo, "Argument of 'not' has type " + comp.e1.type + " instead of Bool.");
		comp.type = "Bool";

	}
	private void ProcessNode(AST.lt lt) {
		ProcessNode(lt.e1);
		ProcessNode(lt.e2);
		if(lt.e1.type.equals("Int") == false || lt.e2.type.equals("Int") == false) {
			reportError(filename, lt.lineNo, "non-Int arguments: " + lt.e1.type + " < " + lt.e2.type);
		}
		lt.type = "Bool";
	}
	private void ProcessNode(AST.leq leq) {
		ProcessNode(leq.e1);
		ProcessNode(leq.e2);
		if(leq.e1.type.equals("Int") == false || leq.e2.type.equals("Int") == false) {
			reportError(filename, leq.lineNo, "non-Int arguments:" + leq.e1.type + " <= " + leq.e2.type);
		}
		leq.type = "Bool";
	}
	private void ProcessNode(AST.eq eq) {
		ProcessNode(eq.e1);
		ProcessNode(eq.e2);
		List <String> basic_types = Arrays.asList("String", "Int", "Bool");
		if(basic_types.contains(eq.e1.type) || basic_types.contains(eq.e2.type)) {
			if(eq.e1.type.equals(eq.e2.type) == false) {
				reportError(filename, eq.lineNo, "Illegal comparison with a basic type.");
			}
		}
		eq.type = "Bool";
	}
	private void ProcessNode(AST.neg neg) {		// neg is ~

		ProcessNode(neg.e1);
		if(neg.e1.type.equals("Int") == false)
			reportError(filename, neg.lineNo, "Argument of '~' has type " + neg.e1.type + " instead of Int");
		neg.type = "Int";
	}
	private void ProcessNode(AST.object object) {

		AST.attr a = scopeTable.lookUpGlobal(object.name);
		if(a == null) {
			reportError(filename, object.lineNo, "Undeclared identifier " + object.name);
			object.type = "Object";
		}
		else
			object.type = a.typeid;
	}
	private void ProcessNode(AST.int_const int_const) {
		int_const.type = "Int";
	}
	private void ProcessNode(AST.string_const string_const) {
		string_const.type = "String";
	}
	private void ProcessNode(AST.bool_const bool_const) {
		bool_const.type = "Bool";
	}
}