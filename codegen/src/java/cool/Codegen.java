package cool;

import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Formatter;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.Map.Entry;




public class Codegen{
	
	IRClassTable IRclassTable;
	ArrayList < ArrayList <Integer> > classGraph;
	HashMap <Integer, String> idxName;
	HashMap <String, AST.class_> idxCont;

	int strCount = 0;
	
	public Codegen(AST.program program, PrintWriter out){
		//Write Code generator code here
        out.println("; I am a comment in LLVM-IR. Feel free to remove me.");
       
        // go through all classes. For each class make object structures, then include virtual table for a class.
        CodegenInit(out);
        
        IRclassTable = new IRClassTable();
        ProcessGraph(program.classes, out);

        AST.class_ theClass = idxCont.get("Main");

        List<AST.feature> theFeatures = new ArrayList<AST.feature>();
        theFeatures = theClass.features;
        
        for(int i = 0; i < theFeatures.size(); ++i)
        {
        	AST.feature ftr = new AST.feature();
        	ftr = theFeatures.get(i);
        	if(ftr.getClass() == AST.attr.class)
        	{
        		AST.expression expr = new AST.expression();
        		AST.attr temp = (AST.attr)ftr;
        		expr = temp.value;
        		ProcessStr(expr,out);

        	}
        	else if(ftr.getClass() == AST.method.class)
        	{
        		AST.expression expr = new AST.expression();
        		AST.method temp = (AST.method)ftr;
        		expr = temp.body;
        		ProcessStr(expr,out);

        	}

        }

        for(AST.class_ e : program.classes) 
        {
			if(!(e.name.equals("Main")))
			{
				//List<AST.feature> theFeatures = new ArrayList<AST.feature>();
        		theFeatures = theClass.features;

				for(int i = 0; i < theFeatures.size(); ++i)
		        {
		        	AST.feature ftr = new AST.feature();
		        	ftr = theFeatures.get(i);
		        	if(ftr.getClass() == AST.attr.class)
		        	{
		        		AST.expression expr = new AST.expression();
		        		AST.attr temp = (AST.attr)ftr;
		        		expr = temp.value;
		        		ProcessStr(expr,out);

		        	}
		        	else if(ftr.getClass() == AST.method.class)
		        	{
		        		AST.expression expr = new AST.expression();
		        		AST.method temp = (AST.method)ftr;
		        		expr = temp.body;
		        		ProcessStr(expr,out);

		        	}
		        }
			}
		}

	}


	private void printStringConst(AST.expression expr_str, PrintWriter out)
	{
		AST.string_const str_obj = (AST.string_const)expr_str;	
  		if(strCount==0){
  			out.println("@.str = private unnamed_addr constant ["+ (str_obj.value.length()+1) + " x i8] c\""+str_obj.value+"\\00\", align 1");
  		}
  		else{
  			out.println("@.str."+ strCount+ " = private unnamed_addr constant ["+ (str_obj.value.length()+1) + " x i8] c\""+str_obj.value+"\\00\", align 1");
  		}
  		strCount++;
	}




	private void ProcessStr(AST.expression expr_str, PrintWriter out)
	{
		
		// assign
		if(expr_str.getClass() == AST.assign.class)
		{
			AST.assign str = (AST.assign)expr_str;
			AST.expression exp = str.e1;
			ProcessStr(exp,out);
		}


		// static dispatch
		else if(expr_str.getClass() == AST.static_dispatch.class)
		{
			AST.static_dispatch str = (AST.static_dispatch)expr_str;
			List<AST.expression> expression_listsd = new ArrayList<AST.expression>();
            expression_listsd = str.actuals;

            for(int i = 0; i < expression_listsd.size(); i++)
			{
				expr_str = expression_listsd.get(i);

				if(expr_str.getClass() == AST.string_const.class)
				{
					printStringConst(expr_str,out);
					return;
				}
				else
					ProcessStr(expr_str,out);
			}
		}


		// dispatch
		else if(expr_str.getClass() == AST.dispatch.class)
		{
			AST.dispatch str = (AST.dispatch)expr_str;   

			List<AST.expression> expression_listd = new ArrayList<AST.expression>();
			expression_listd = str.actuals;

			for(int l = 0; l < expression_listd.size(); l++)
			{
				expr_str = expression_listd.get(l);

				if(expr_str.getClass() == AST.string_const.class)
				{
					printStringConst(expr_str,out);
					return;
				}
				else
					ProcessStr(expr_str,out);
			}               
		}


		// if-then-else
		else if(expr_str.getClass() == AST.cond.class)
		{
			AST.cond str = (AST.cond)expr_str;
			AST.expression e1 = str.predicate;
			AST.expression e2 = str.ifbody;
			AST.expression e3 = str.elsebody;

			if(e1.getClass() == AST.string_const.class)
			{
				printStringConst(e1, out);
				return;
			}
			else
				ProcessStr(e1,out);

			if(e2.getClass() == AST.string_const.class)
			{
				printStringConst(e2, out);
				return;
			}
			else
				ProcessStr(e2,out);

			if(e3.getClass() == AST.string_const.class)
			{
				printStringConst(e3, out);
				return;
			}
			else
				ProcessStr(e3,out);

		}


		// let
		else if(expr_str.getClass() == AST.let.class)
		{
			AST.let str = (AST.let)expr_str;
			AST.expression e1 = str.value;		

			if(e1.getClass() == AST.string_const.class)
			{
				printStringConst(str.value,out);
				return;
			}
			else
				ProcessStr(e1,out);

			AST.expression e2 = str.body;
			if(e2.getClass() == AST.string_const.class)
			{
				printStringConst(e2,out);
				return;
			}
			else
				ProcessStr(e2,out);
		}


		// block
		else if(expr_str.getClass() == AST.block.class)
		{
			AST.block str = (AST.block)expr_str;
			List<AST.expression> listExp = new ArrayList<AST.expression>();
			listExp = str.l1;
			for(int i = 0; i < listExp.size(); ++i)
	        {
	        	AST.expression e2 = new AST.expression();
	        	e2 = listExp.get(i);
	        	if(e2.getClass() == AST.string_const.class)
	        	{
	        		AST.string_const str2 = (AST.string_const)e2;
	        		printStringConst(str2,out);
	        		return;
	        	}
	        	else
					ProcessStr(e2,out);
	        	
	        }

		}


		// while loop
		else if(expr_str.getClass() == AST.loop.class)
		{
			AST.loop str = (AST.loop)expr_str;
			AST.expression e1 = str.predicate;
			AST.expression e2 = str.body;
			if(e1.getClass() == AST.string_const.class)
        	{
        		AST.string_const str2 = (AST.string_const)e1;
        		printStringConst(str2,out);
        		return;
        	}
        	else
				ProcessStr(e1,out);
			if(e2.getClass() == AST.string_const.class)
        	{
        		AST.string_const str3 = (AST.string_const)e2;
        		printStringConst(str3,out);
        		return;
        	}
        	else
				ProcessStr(e2,out);
		}

		
		// typcase
		else if(expr_str.getClass() == AST.typcase.class)
		{
			AST.typcase str = (AST.typcase)expr_str;
			AST.expression e1 = str.predicate;

			if(e1.getClass() == AST.string_const.class)
			{
				printStringConst(e1, out);
				return;
			}
			else
				ProcessStr(e1, out);
		
			List<AST.branch> branchList = new ArrayList<AST.branch>();
			branchList = str.branches;


			for(int i=0; i<branchList.size(); i++)
			{
				AST.branch branch1 = new AST.branch(branchList.get(i).name,branchList.get(i).type,branchList.get(i).value,branchList.get(i).lineNo);
				branch1 = branchList.get(i);

				if(branch1.value.getClass() == AST.string_const.class)
				{
					printStringConst(branch1.value,out);
					return;
				}
				else
					ProcessStr(branch1.value,out);
			}
		}


		// comparision operator (less than jaisa)
		else if(expr_str.getClass() == AST.eq.class)
		{
			AST.eq str = (AST.eq)expr_str;

			if(str.e1.getClass() == AST.string_const.class)
			{
				printStringConst(str.e1, out);
				return;
			}
			else
				ProcessStr(str.e1, out);


			if(str.e2.getClass() == AST.string_const.class)
			{
				printStringConst(str.e2, out);
				return;
			}
			else
				ProcessStr(str.e2, out);
		}



		// string const
		else if(expr_str.getClass() == AST.string_const.class)
		{
			AST.string_const str = (AST.string_const)expr_str;
			printStringConst(str,out);
		}
	}
	
	
	private void CodegenInit(PrintWriter out) {
		out.println(Constants.DATA_LAYOUT);
		out.println(Constants.TARGET_TRIPLE);
		out.println(Constants.ERRORS);
		out.println(Constants.CMETHODS);
		out.println(Constants.CMETHOD_HELPERS);
	}
	
	
	// given a method, get its type signature
	private String getIRType(String typeid) {
		if(Constants.FC_TYPES.containsKey(typeid)) {
			return Constants.FC_TYPES.get(typeid);
		}
		else return "%class." + typeid + "*";
	}
	
	private void EmitClassDecl(IRClassPlus irclass, PrintWriter out) {
		out.print("%class." + irclass.name + ".Base = type {");
		for(int i = 0; i < irclass.attrList.size(); ++i) {
			AST.attr at = irclass.attrList.get(i);
			if(i != 0)
				out.print(", ");
			out.print(getIRType(at.typeid));
		}
		out.print(" }\n");
		out.println("%class." + irclass.name + " = type { i32, i8*, %class." + irclass.name + ".Base }\n");
	}
	
	private void EmitClassVT(IRClassPlus irclass, PrintWriter out) {
		out.print("@_ZTV" + irclass.name.length() + irclass.name + " = constant " +
				"[" + irclass.methodList.size() + " x i8*] [");
		for(int i = 0; i < irclass.methodList.size(); ++i) {
			if(i != 0)
				out.print(", ");
			AST.method me = irclass.methodList.get(i);
			out.print("i8* bitcast (");
			out.print(getIRType(me.typeid));
			out.print(" ( ");
			for(int j = 0; j < me.formals.size(); ++j) {
				AST.formal fr = me.formals.get(j);
				if(j != 0)
					out.print(", ");
				out.print(getIRType(fr.typeid));
			}
			out.print(" )* ");
			
			// now name of method
			out.print(irclass.IRname.get(me.name));
			out.print(" to i8*)");
		}
		out.print("] \n");
	}
	

	/* A constructor for all base types is required */
	
	private void EmitMethods(IRClassPlus irclass, PrintWriter out) {
		if(irclass.name.equals("Int") || irclass.name.equals("Bool")) return;
		else if(irclass.name.equals("String")) {
			out.println(Constants.STRING_CONCAT);
			out.println(Constants.STRING_COPY);
			out.println(Constants.STRING_LENGTH);
			out.println(Constants.STRING_SUBSTR);
		} else if(irclass.name.equals("Object")) {
			out.println(Constants.OBJECT_ABORT);
			// constructor for base type
			out.println(Constants.OBJECT_BASE);
			out.println(Constants.OBJECT_TYPENAME);
		} else if(irclass.name.equals("IO")) {
			EmitBaseConstructor(irclass, out);
		} else {
			EmitBaseConstructor(irclass, out);
		}
	}
	
	private String baseClassName(String className) {
		return "class." + className + ".Base";
	}
	
	private void EmitBaseConstructor(IRClassPlus irclass, PrintWriter out) {
		out.print("define void @_Z" + irclass.name.length() + irclass.name + "Base" + "C");
		out.print(" ( %" + irclass.name + ".Base*" + "%this" + " ) { \n");
		out.println("entry: ");
		
		
		int ptr = 0;
		for(int i = 0; i < irclass.attrList.size(); ++i) {
			AST.attr at = irclass.attrList.get(i);
			out.println("%" + ptr + " = getelementptr inbouds %" + baseClassName(at.typeid) + ", " + baseClassName(at.typeid) + "* %this, i32 0, i32 " + i);
			out.println("call void " + "@_Z" + at.typeid.length() + at.typeid + "Base"+ "C" + "( %" + baseClassName(at.typeid) + "*" + "%" + i);
			out.println("return void");
		}
	}
	
	private void ProcessGraph(List <AST.class_> classes, PrintWriter out) {
			
	
		Integer sz = 0;		// stores the number of classes
		idxCont = new HashMap <String, AST.class_> ();
		HashMap <String, Integer> classIdx = new HashMap <String, Integer> ();
		idxName = new HashMap <Integer, String>();
		classGraph = new ArrayList < ArrayList <Integer> >();

		/* Laying the groundwork */
		classIdx.put("Object", 0);
		idxName.put(0, "Object");
		classIdx.put("IO", 1);
		idxName.put(0, "IO");
		
		classGraph.add(new ArrayList <Integer> (Arrays.asList(1)));
		classGraph.add(new ArrayList <Integer>());	// for IO
		idxName.put(0, "Object");
		idxName.put(1, "IO");
		
		sz = sz + 2;	// IO and Object (2 classes) already present
		
		/* Checking for :
		 * - bad redefinitions
		 * - bad inheritance
		 * Also : assigning an integer corresponding to each class.
		 */
		for(AST.class_ e : classes) {
			idxName.put(sz, e.name);			// Reverse lookup. Integer -> className
			classIdx.put(e.name, sz++);			// className -> Integer
			idxCont.put(e.name, e);				// getting the class from name. Used later.
			classGraph.add(new ArrayList <Integer> ());
		}


		
		/* We are creating an undirected graph in this method.
		 * Also: Checking for - undefined parent class error
		 */
		for(AST.class_ e : classes) {
			int u = classIdx.get(e.parent);
			int v = classIdx.get(e.name);
			classGraph.get(u).add(v);			// adding an edge from parent -> child in the graph
		}
		
		
		/* Class Declarations here */
		Queue<Integer> q = new LinkedList<Integer>();				
		q.clear(); q.offer(0);
		while (!q.isEmpty()) {
			int u = q.poll();
			if(u != 1 && u != 0) {
				IRclassTable.insert(idxCont.get(idxName.get(u)));		// insert classes in BFS-order so that methods and attributes can be inherited.
			}
			EmitClassDecl(IRclassTable.getIRClassPlus(idxName.get(u)), out);
			for(Integer v : classGraph.get(u)) {
				q.offer(v);
			}
		
		}

		/* prints comdat any */
		/*
		q.clear(); q.offer(0);
		HashMap <String, Boolean> mentioned = new HashMap<String, Boolean>();
		while (!q.isEmpty()) {
			int u = q.poll();
			IRClassPlus irc = IRclassTable.getIRClassPlus(idxName.get(u));
			for(Entry<String, String> entry : irc.IRname.entrySet()) {
				if(mentioned.containsKey(entry.getValue()) == false) {
					String mod_name = entry.getValue();
					// change first character to dollar
					out.print(mod_name + " = " + " comdat any ");
				}
			}
			for(Integer v : classGraph.get(u)) {
				q.offer(v);
			}
		
		}
		*/

		/* prints virtual table */
		q.clear(); q.offer(0);
		while (!q.isEmpty()) {
			int u = q.poll();
			EmitClassVT(IRclassTable.getIRClassPlus(idxName.get(u)), out);
			for(Integer v : classGraph.get(u)) {
				q.offer(v);
			}
		}
		
		/* prints definitions */
		q.clear(); q.offer(0);
		while (!q.isEmpty()) {
			int u = q.poll();
			EmitMethods(IRclassTable.getIRClassPlus(idxName.get(u)), out);
			for(Integer v : classGraph.get(u)) {
				q.offer(v);
			}
		
		}
		
	}

}