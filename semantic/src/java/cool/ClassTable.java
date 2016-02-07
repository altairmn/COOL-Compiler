package cool;
import java.util.*;
import java.util.Map.Entry;




public class ClassTable {
	private HashMap<String, ClassPlus> classes=new HashMap<String, ClassPlus>();		// for retrieving class related info and class attributes and features
	private HashMap<String, Integer> height = new HashMap<String, Integer>();			// for retrieving class height in the inheritance hierarchy (for conformance check)
	public List<Error> errors = new ArrayList<Error>();
	
	public ClassTable() {
		/* Classes already present in the table:
		 * - Object
		 * - IO
		 * - String
		 * - Int
		 * - Bool
		 * 
		 * Object has methods:
		 * - abort() : Object
		 * - type_name(): String
		 * IO has methods:
		 * - out_string(x : String) : IO
		 * - out_int(x : Int) : IO
		 * - in_string() : String
		 * - in_int() : String
		 * String has methods:
		 * - length() : Int
		 * - concat(s: String) : String
		 * - substr(i : Int, l : Int) : String
		 */
		
		HashMap<String, AST.method> ol = new HashMap <String, AST.method>();
		ol.put("abort", new AST.method("abort", new ArrayList<AST.formal>(), "Object", new AST.no_expr(0), 0));
		ol.put("type_name", new AST.method("type_name", new ArrayList<AST.formal>(), "String", new AST.no_expr(0), 0));

		classes.put("Object", new ClassPlus("Object", null, new HashMap<String, AST.attr>(), ol));
		height.put("Object", 0);
		
		HashMap <String, AST.method> iol = new HashMap<String, AST.method>();
		
		List <AST.formal> os_formals = new ArrayList<AST.formal>();
		os_formals.add(new AST.formal("out_string", "String", 0));
		List <AST.formal> oi_formals = new ArrayList<AST.formal>();
		oi_formals.add(new AST.formal("out_int", "Int", 0));		
		
		iol.put("out_string", new AST.method("out_string", os_formals, "IO", new AST.no_expr(0), 0));
		iol.put("out_int", new AST.method("out_int", oi_formals, "IO", new AST.no_expr(0), 0));
		iol.put("in_string", new AST.method("in_string", new ArrayList<AST.formal>(), "String", new AST.no_expr(0), 0));
		iol.put("in_int", new AST.method("in_int", new ArrayList<AST.formal>(), "Int", new AST.no_expr(0), 0));
		classes.put("IO", new ClassPlus("IO", "Object", new HashMap<String, AST.attr>(), iol));
		classes.get("IO").mlist.putAll(ol);		// IO inherits from Object
		height.put("IO", 1);
		
		classes.put("Int", new ClassPlus("Int", "Object", new HashMap<String, AST.attr>(), new HashMap<String, AST.method>()));
		height.put("Int", 1);
		classes.get("Int").mlist.putAll(ol);	// Int inherits from Object
		
		classes.put("Bool", new ClassPlus("Bool", "Object", new HashMap<String, AST.attr>(), new HashMap<String, AST.method>()));
		height.put("Bool", 1);
		classes.get("Bool").mlist.putAll(ol);	// Bool inherits from Object
		
		HashMap <String, AST.method> sl = new HashMap<String, AST.method>();
		List<AST.formal> concat_formal = new ArrayList<AST.formal>();
		concat_formal.add(new AST.formal("s", "String", 0));
		List<AST.formal> substr_formal = new ArrayList<AST.formal>();
		substr_formal.add(new AST.formal("i", "Int", 0));
		substr_formal.add(new AST.formal("l", "Int", 0));
		
		sl.put("length", new AST.method("length", new ArrayList<AST.formal>(), "Int", new AST.no_expr(0), 0));
		sl.put("concat", new AST.method("concat", concat_formal, "String", new AST.no_expr(0), 0));
		sl.put("substr", new AST.method("substr", substr_formal, "String", new AST.no_expr(0), 0));
		
		classes.put("String", new ClassPlus("String", "Object", new HashMap<String, AST.attr>(), sl));
		height.put("String", 1);
		classes.get("String").mlist.putAll(ol);		// String Inherits from Object
	}
	void insert(AST.class_ c) {
		/* Whenever a new class is inserted,
		 * - Inherits the attributes and methods of the parent class.
		 * - Checks for multiple method or attribute definitions.
		 * - Checks for correct method overrides and any attribute overrides
		 */
		String pr = c.parent;
		ClassPlus tc = new ClassPlus(c.name, c.parent, classes.get(c.parent).alist, classes.get(c.parent).mlist);	// adding the parents attribute list and method list

		
		HashMap <String, AST.attr> tc_alist = new HashMap<String, AST.attr>();
		HashMap <String, AST.method> tc_mlist = new HashMap <String, AST.method>();
		
		/* Checks for the following errors within a class:
		 * - multiple attribute definitions
		 * - multiple method definitions
		 */
		for(AST.feature e : c.features) {
			if(e.getClass() == AST.attr.class) {
				AST.attr ae = (AST.attr) e;
				if(tc_alist.containsKey(ae.name))
					errors.add(new Error(c.filename, ae.lineNo, "Attribute " + ae.name + " is multiply defined in class."));
				else
					tc_alist.put(ae.name, ae);
			}
			else if(e.getClass() == AST.method.class) {
				AST.method me = (AST.method) e;
				if(tc_mlist.containsKey(me.name))
					errors.add(new Error(c.filename, me.lineNo, "Method " + me.name + " is multiply defined."));
				else
					tc_mlist.put(me.name, me);
			}
		}

		
		/* Checks for the following errors with respect to the inherited class:
		 * - redefinition of an inherited attribute (Note: the class retains the inherited attribute and discards the attribute defined within the class)
		 * - wrong redefinition of an inherited method (Note : the class retains the inherited method and discards the method defined within the class)
		 */
		for(Entry<String, AST.attr> entry : tc_alist.entrySet()) {
			if(tc.alist.containsKey(entry.getKey()))
				errors.add(new Error(c.filename, entry.getValue().lineNo, "Attribute " + entry.getValue().name + " is an attribute of an inherited class"));
			else {
				tc.alist.put(entry.getKey(), entry.getValue());
			}

		}
		boolean foundErr;
		for(Entry<String, AST.method> entry : tc_mlist.entrySet()) {
			foundErr = false;
			if(tc.mlist.containsKey(entry.getKey())) {
				AST.method parent_me = tc.mlist.get(entry.getKey());
				AST.method me = entry.getValue();
				if(me.formals.size() != parent_me.formals.size()) {
					errors.add(new Error(c.filename, me.lineNo, "Incompatible number of formal parameters in redefined method " + me.name));
					foundErr = true;
				}
				else {
					if(me.typeid.equals(parent_me.typeid) == false) {
						errors.add(new Error(c.filename, me.lineNo, "In redefined method " + me.name + ", return type "
								+ me.typeid + " is different from original return type " + parent_me.typeid));
						foundErr = true;
					}
					for(int i = 0; i < me.formals.size(); ++i) {
						if(me.formals.get(i).typeid.equals(parent_me.formals.get(i).typeid) == false) {
							errors.add(new Error(c.filename, me.lineNo, "In redefined method " + me.name + ", parameter type"
									+ me.formals.get(i).typeid + " is different from original type " + parent_me.formals.get(i).typeid));
							foundErr = true;
						}
					}
				}
			}

			if(foundErr != true)
				tc.mlist.put(entry.getKey(), entry.getValue());
		}
		height.put(c.name, height.get(c.parent) + 1);

		classes.put(c.name, tc);
	}
	
	
	List<Error> getErrors() {
		return errors;
	}
	
	HashMap<String, AST.attr> getAttrs(String className) {
		return classes.get(className).alist;
	}
	
	ClassPlus getClassPlus(String className) {
		return classes.get(className);
	}

	boolean conforms(String a, String b) {
		if(a.equals(b))
			return true;
		else {
			a = classes.get(a).parent;
			if(a == null) return false;
			else return conforms(a, b);
		}
	}
	
	String lca(String a, String b) {

		if(a.equals(b)) return a;
		else if(height.get(a) < height.get(b))		// a must always be deeper in the tree
			return lca(b, a);
		else
			return lca(classes.get(a).parent, b);
	}
}
