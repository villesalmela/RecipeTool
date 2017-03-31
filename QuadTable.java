package recipeTool;

import java.util.*;

public class QuadTable<K,A,B,C,D>{
	
	private final Map<K,A> first = new HashMap<>();
	private final Map<K,B> second = new HashMap<>();
	private final Map<K,C> third = new HashMap<>();
	private final Map<K,D> fourth = new HashMap<>();
	private final Map[] mapArray = new Map[]{first,second,third,fourth};
	
	public QuadTable(){
		
	}
	
	public void set(K key, A value1, B value2, C value3, D value4){
		if(!(value1==null)) this.first.put(key, value1);
		if(!(value2==null)) this.second.put(key, value2);
		if(!(value3==null)) this.third.put(key, value3);
		if(!(value4==null)) this.fourth.put(key, value4);
	}
	
	public A getFirst(K key){
		return this.first.get(key);
	}
	
	public B getSecond(K key){
		return this.second.get(key);
	}
	
	public C getThird(K key){
		return this.third.get(key);
	}
	
	public D getFourth(K key){
		return this.fourth.get(key);
	}
	
	public Object getValue(K key, int i){
		return mapArray[i-1].get(key);
	}
	
	public TreeSet<K> getKeys(){
		TreeSet<K> temp = new TreeSet<>();
		for (Map map : mapArray){
			temp.addAll(map.keySet());
		}
		return temp;
	}
	
	public void deleteKey(K key){
		for (Map map : mapArray){
			map.remove(key);
		}
	}
	
	public void deleteValue(K key, int i){
		mapArray[i-1].remove(key);
	}
	
	
}
