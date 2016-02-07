package cool;
import java.util.*;
public class ScopeTable<T> {
	private int scope;
	private ArrayList<HashMap<String, T>> maps=new ArrayList<HashMap<String, T>>();
	public ScopeTable(){
		scope = 0;
		maps.add(new HashMap<String, T>());
	}
	void insert(String s, T t){
		maps.get(scope).put(s,t);
	}
	
	void insertAll(HashMap<String, T> hs) {
		maps.get(scope).putAll(hs);
	}
	void enterScope(){
		scope++;
		maps.add(new HashMap<String, T>());
	}
	void exitScope(){
		if (scope>0){
			maps.remove(scope);
			scope--;
		}
	}	
	T lookUpLocal(String t){
		return maps.get(scope).get(t);
	}
	T lookUpGlobal(String t){
		for ( int i = scope; i>=0 ; i--){
			if (maps.get(i).containsKey(t))
				return maps.get(i).get(t);
		}
		return null;
	}
}
