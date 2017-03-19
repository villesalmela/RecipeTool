package recipeTool;
import java.util.*;

public class Storage {
	
//PROPERTIES
	private static Map<String, Unit> units = new HashMap<String, Unit>();
	private static Map<String, TreeSet<String>> allergens = new HashMap<String, TreeSet<String>>();
	private static Map<String, Date> expirations = new HashMap<String, Date>();
	private static Map<String, Double> amounts = new HashMap<String, Double>();
	
	
//CREATE & DELETE
	public static void setIngredient(String name, double amount, Unit unit, TreeSet<String> allergen, Date expiration){ //like constructor
		amounts.put(name, amount);
		units.put(name, unit);
		allergens.put(name, allergen);
		expirations.put(name, expiration);
	}
	public static void deleteIngredient(String name){						//delete all keys and values
		amounts.remove(name);
		units.remove(name);
		allergens.remove(name);
		expirations.remove(name);
	}
	
	
//GETTERS
	public static Vector<String> listIngredients(){							//only get, internal, A-Z
		Vector<String> temp = new Vector<String>();
		temp.addAll(units.keySet());
		return temp;
	}
	public static Double getAmount(String name){							//get & set, internal
		return amounts.get(name);
	}
	public static Unit getUnit(String name){								//get & set, internal
		return units.get(name);
	}
	public static Vector<String> getAllergens(String name){					//get & set, internal, A-Z
		Vector<String> temp = new Vector<String>();
		temp.addAll(allergens.get(name));
		return temp;
	}
	public static Date getExpiration(String name){							//get & set, internal
		return expirations.get(name);
	}
	public static boolean hasIngredient(String name){
		boolean has = false;
		for(String iname : units.keySet()){
			if (iname.equals(name)) has = true;
		}
		return has;
	}

	
//SETTERS
	public static void setAmount(String name, double amount){				//set & get, internal
		amounts.put(name, amount);
	}
	public static void setUnit(String name, Unit unit){						//set & get, internal
		units.put(name, unit);
	}
	public static void setAllergens(String name, TreeSet<String> allergen){		//set & get, internal, A-Z
		allergens.put(name, allergen);
	}
	public static void setExpiration(String name, Date expiration){			//set & get, internal
		expirations.put(name, expiration);
	}



		

	

}
