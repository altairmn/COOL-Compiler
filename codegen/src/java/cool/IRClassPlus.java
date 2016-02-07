package cool;
import java.util.ArrayList;
import java.util.HashMap;

/* For each class, we have a list of methods and a list of attributes. Additionally, we have a hashmap that stores
 * offsets of both methods and attributes.
 * 
 */


public class IRClassPlus {
	public String name;
	public String parent = null;
	public HashMap <String, AST.attr> alist;
	public HashMap <String, AST.method> mlist;
	
	
	public HashMap <String, Integer> attrOffset;
	public HashMap <String, Integer> methodOffset;
	public HashMap <String, String> IRname;
	
	public ArrayList <AST.method> methodList;
	public ArrayList <AST.attr> attrList;
	
	IRClassPlus(String nm, String pr, HashMap<String, AST.attr> al, HashMap<String, AST.method> ml, HashMap<String, Integer> ao, HashMap <String, Integer> mo, ArrayList <AST.attr> pa, ArrayList <AST.method> pm, HashMap <String, String> irn) {
		name = new String(nm);
		if(pr != null) parent = new String(pr);
		alist = new HashMap <String, AST.attr>();
		alist.putAll(al);
		mlist = new HashMap <String, AST.method>();
		mlist.putAll(ml);
		
		attrOffset = new HashMap <String, Integer>();
		attrOffset.putAll(ao);
		
		methodOffset = new HashMap <String, Integer>();
		methodOffset.putAll(mo);
		
		attrList = new ArrayList <AST.attr>();
		attrList.addAll(pa);
		
		methodList = new ArrayList <AST.method>();
		methodList.addAll(pm);
		
		IRname = new HashMap <String, String>();
		IRname.putAll(irn);
	}
}