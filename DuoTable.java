package recipeTool;

import java.util.*;

public class DuoTable<K,A,B> {
	
	private final Map<K,A> first = new HashMap<>();
	private final Map<K,B> second = new HashMap<>();
	
	public void set(K key, A value1, B value2){
		if(!(value1==null)) this.first.put(key, value1);
		if(!(value2==null)) this.second.put(key, value2);
	}
	
	public A getFirst(K key){
		return this.first.get(key);
	}
	
	public B getSecond(K key){
		return this.second.get(key);
	}
	
	public TreeSet<K> getKeys(){
		TreeSet<K> temp = new TreeSet<>();
		temp.addAll(this.first.keySet());
		temp.addAll(this.second.keySet());
		return temp;
		
	}
	
	public void deleteKey(K key){
		this.first.remove(key);
		this.second.remove(key);
	}
	
	public void deleteValue(K key, int i){
		switch(i){
		case 1: this.first.remove(key); break;
		case 2:	this.second.remove(key); break;
		}
	}
	
}
