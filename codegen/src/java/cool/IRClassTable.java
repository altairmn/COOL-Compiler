package cool;
import java.util.*;
import java.util.Map.Entry;




public class IRClassTable {
	private HashMap<String, IRClassPlus> classes=new HashMap<String, IRClassPlus>();		// for retrieving class related info and class attributes and features
	private HashMap<String, Integer> height = new HashMap<String, Integer>();			// for retrieving class height in the inheritance hierarchy (for conformance check)
	
	
	public List<Error> errors = new ArrayList<Error>();
	
	public IRClassTable() {
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

		List<AST.formal> obj_formal = new ArrayList<AST.formal>();
		obj_formal.add(new AST.formal("this", "Object", 0));
		
		HashMap<String, AST.method> ol = new HashMap <String, AST.method>();
		ol.put("abort", new AST.method("abort", obj_formal, "Object", new AST.no_expr(0), 0));
		ol.put("type_name", new AST.method("type_name", obj_formal, "String", new AST.no_expr(0), 0));
		
		
		ArrayList <AST.method> obj_mlist = new ArrayList <AST.method>();
		obj_mlist.add(new AST.method("abort", obj_formal, "Object", new AST.no_expr(0), 0));
		obj_mlist.add(new AST.method("type_name", obj_formal, "String", new AST.no_expr(0), 0));
		obj_mlist.add(new AST.method("copy", obj_formal, "Object", new AST.no_expr(0), 0));
		
		HashMap <String, Integer> obj_moffset = new HashMap <String, Integer>();
		obj_moffset.put("abort", 0);
		obj_moffset.put("type_name", 1);
		obj_moffset.put("copy", 2);
		
		HashMap <String, String> irname = new HashMap <String, String>();
		irname.put("abort", "@_ZN6Object5abort");
		irname.put("type_name", "@_ZN6Object9type_name");
		irname.put("copy", "@_ZN6Object4copy");

		classes.put("Object", new IRClassPlus("Object", null, new HashMap<String, AST.attr>(), ol, new HashMap <String, Integer>(), obj_moffset, new ArrayList <AST.attr>(), obj_mlist, irname));
		height.put("Object", 0);
		
		HashMap <String, AST.method> iol = new HashMap<String, AST.method>();
		
		List <AST.formal> os_formals = new ArrayList<AST.formal>();
		List <AST.formal> io_formal = new ArrayList <AST.formal> ();
		io_formal.add(new AST.formal("this", "IO", 0));
		os_formals.addAll(io_formal);
		os_formals.add(new AST.formal("out_string", "String", 0));
		List <AST.formal> oi_formals = new ArrayList<AST.formal>();
		oi_formals.addAll(io_formal);
		oi_formals.add(new AST.formal("out_int", "Int", 0));

		
		iol.put("out_string", new AST.method("out_string", os_formals, "IO", new AST.no_expr(0), 0));
		iol.put("out_int", new AST.method("out_int", oi_formals, "IO", new AST.no_expr(0), 0));
		iol.put("in_string", new AST.method("in_string", io_formal, "String", new AST.no_expr(0), 0));
		iol.put("in_int", new AST.method("in_int", io_formal, "Int", new AST.no_expr(0), 0));
		
		HashMap <String, Integer> io_moffset = new HashMap <String, Integer>();
		io_moffset.putAll(obj_moffset);
		io_moffset.put("out_string", 3);
		io_moffset.put("out_int", 4);
		io_moffset.put("in_string", 5);
		io_moffset.put("in_int", 6);
		
		ArrayList <AST.method> io_mlist = new ArrayList <AST.method>();
		io_mlist.addAll(obj_mlist);
		io_mlist.add(new AST.method("out_string", os_formals, "IO", new AST.no_expr(0), 0));
		io_mlist.add(new AST.method("out_int", oi_formals, "IO", new AST.no_expr(0), 0));
		io_mlist.add(new AST.method("in_string", io_formal, "String", new AST.no_expr(0), 0));
		io_mlist.add(new AST.method("in_int", io_formal, "Int", new AST.no_expr(0), 0));
		
		// redefine copy
		io_mlist.set(2, new AST.method("copy", io_formal, "IO", new AST.no_expr(0), 0));
		
		HashMap <String, String> io_irname = new HashMap <String, String>();
		io_irname.putAll(irname);
		io_irname.put("out_string", "@_ZN2IO10out_string");
		io_irname.put("in_string", "@_ZN2IO7out_int");
		io_irname.put("in_string", "@_ZN2IO9in_string");
		io_irname.put("in_int", "@_ZN2IO9in_int");
		
		// change copy irname
		io_irname.put("copy", "@_ZN2IO4copy");
		
		
		classes.put("IO", new IRClassPlus("IO", "Object", new HashMap<String, AST.attr>(), iol, new HashMap<String, Integer> (), io_moffset, new ArrayList <AST.attr>(), io_mlist, io_irname));
		classes.get("IO").mlist.putAll(ol);		// IO inherits from Object
		height.put("IO", 1);
		classes.get("IO").attrList.add(new AST.attr("__Base", "Object" + ".Base", new AST.no_expr(0), 0));
		classes.get("IO").attrOffset.put("__Base", 0);
		
		classes.put("Int", new IRClassPlus("Int", "Object", new HashMap<String, AST.attr>(), new HashMap<String, AST.method>(), new HashMap <String, Integer>(), obj_moffset, new ArrayList <AST.attr>(), obj_mlist, irname));
		height.put("Int", 1);
		classes.get("Int").mlist.putAll(ol);	// Int inherits from Object
		classes.get("Int").methodList.get(2).typeid = "Int";	// redefine copy
		classes.get("Int").IRname.put("copy", "@_ZN3Int4copy");
		
		classes.put("Bool", new IRClassPlus("Bool", "Object", new HashMap<String, AST.attr>(), new HashMap<String, AST.method>(), new HashMap <String, Integer>(), obj_moffset, new ArrayList <AST.attr>(), obj_mlist, irname));
		height.put("Bool", 1);
		classes.get("Bool").mlist.putAll(ol);	// Bool inherits from Object
		classes.get("Int").methodList.get(2).typeid = "Bool";
		classes.get("Bool").IRname.put("copy", "@_ZN4Bool4copy");
		
		HashMap <String, AST.method> sl = new HashMap<String, AST.method>();
		List<AST.formal> str_formal = new ArrayList<AST.formal>();
		str_formal.add(new AST.formal("this", "String", 0));
		List<AST.formal> concat_formal = new ArrayList<AST.formal>();
		concat_formal.addAll(str_formal);
		concat_formal.add(new AST.formal("that", "String", 0));
		List<AST.formal> substr_formal = new ArrayList<AST.formal>();
		substr_formal.addAll(str_formal);
		substr_formal.add(new AST.formal("index", "Int", 0));
		substr_formal.add(new AST.formal("len", "Int", 0));
		
		sl.put("length", new AST.method("length", str_formal, "Int", new AST.no_expr(0), 0));
		sl.put("concat", new AST.method("concat", concat_formal, "String", new AST.no_expr(0), 0));
		sl.put("substr", new AST.method("substr", substr_formal, "String", new AST.no_expr(0), 0));
		
		HashMap <String, Integer> str_moffset = new HashMap <String, Integer>();
		str_moffset.putAll(obj_moffset);
		str_moffset.put("length", 3);
		str_moffset.put("concat", 4);
		str_moffset.put("substr", 5);
		
		ArrayList <AST.method> str_mlist = new ArrayList <AST.method>();
		str_mlist.addAll(obj_mlist);
		str_mlist.add(new AST.method("length", str_formal, "Int", new AST.no_expr(0), 0));
		str_mlist.add(new AST.method("concat", concat_formal, "String", new AST.no_expr(0), 0));
		str_mlist.add(new AST.method("substr", substr_formal, "String", new AST.no_expr(0), 0));
		str_mlist.set(2, new AST.method("copy", str_formal, "String", new AST.no_expr(0), 0));
		str_mlist.get(2).typeid = "String";		// redefine copy
		
		HashMap <String, String> str_irname = new HashMap <String, String> ();
		str_irname.putAll(irname);
		str_irname.put("length", "@_ZN6String6length");
		str_irname.put("concat", "@_ZN6String6concat");
		str_irname.put("substr", "@_ZN6String6substr");
		
		// change copy
		str_irname.put("copy", "@_ZN6String4copy");
				
		
		
		classes.put("String", new IRClassPlus("String", "Object", new HashMap<String, AST.attr>(), sl, new HashMap<String, Integer>(), str_moffset, new ArrayList <AST.attr>(), str_mlist, str_irname));
		height.put("String", 1);
		classes.get("String").mlist.putAll(ol);		// String Inherits from Object
		classes.get("String").IRname.put("copy", "@_ZN5String4copy");
	}
	void insert(AST.class_ c) {
		/* Whenever a new class is inserted,
		 * - Inherits the attributes and methods of the parent class.
		 * - Checks for multiple method or attribute definitions.
		 * - Checks for correct method overrides and any attribute overrides
		 */
		String pr = c.parent;
		IRClassPlus tc = new IRClassPlus(c.name, c.parent, classes.get(pr).alist, classes.get(pr).mlist, new HashMap <String, Integer>(),
				classes.get(pr).methodOffset, new ArrayList <AST.attr> (), classes.get(pr).methodList, classes.get(pr).IRname);	// adding the parents attribute list and method list

		
		HashMap <String, AST.attr> tc_alist = new HashMap<String, AST.attr>();
		HashMap <String, AST.method> tc_mlist = new HashMap <String, AST.method>();
		
		tc.attrList.add(new AST.attr("__Base", pr + ".Base", new AST.no_expr(0), 0));
		tc.attrOffset.put("__Base", 0);
	
		/* Checks for the following errors with respect to the inherited class:
		 * - redefinition of an inherited attribute (Note: the class retains the inherited attribute and discards the attribute defined within the class)
		 * - wrong redefinition of an inherited method (Note : the class retains the inherited method and discards the method defined within the class)
		 */
		/* adding attrs of parent */
		for(Entry<String, AST.attr> entry : tc_alist.entrySet()) {
			tc.alist.put(entry.getKey(), entry.getValue());
		}
		
		/* attrs of the parent are accessed via the base attr */
		
		
		/* Checks for the following errors within a class:
		 * - multiple attribute definitions
		 * - multiple method definitions
		 */
		/* adding attrs and methods of class */
		
		int attr_ptr = 1;
		
		for(AST.feature e : c.features) {
			if(e.getClass() == AST.attr.class) {
				AST.attr ae = (AST.attr) e;
				tc_alist.put(ae.name, ae);
				tc.attrList.add(ae);
				tc.attrOffset.put(ae.name, attr_ptr);
				attr_ptr++;
			}
			else if(e.getClass() == AST.method.class) {
				AST.method me = (AST.method) e;
				tc_mlist.put(me.name, me);
			}
		}
		
		// change the copy method name
		tc.IRname.put("copy", "@_ZN" + tc.name.length() + tc.name + "4copy");
		
		// tc_mlist contains methods in current class
		
		int method_ptr = tc.methodList.size();
		for(Entry<String, AST.method> entry : tc_mlist.entrySet()) {
			String me_name = entry.getKey();
			if(tc.mlist.containsKey(entry.getKey())) {		// overloaded method
				tc.methodList.set(tc.methodOffset.get(me_name), entry.getValue());
				tc.IRname.put(me_name, "@_ZN" + tc.name.length() + tc.name + me_name.length() + me_name);
			} else {
				tc.methodList.add(entry.getValue());
				tc.methodOffset.put(entry.getKey(), method_ptr);
				tc.IRname.put(me_name, "@_ZN" + tc.name.length() + tc.name + me_name.length() + me_name);
				method_ptr++;
			}
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
	
	IRClassPlus getIRClassPlus(String className) {
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
