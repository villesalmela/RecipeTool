package recipeTool;
import java.util.*;

public class Storage {
	
//PROPERTIES
	private static Map<String, Unit> units = new TreeMap<String, Unit>();
	private static Map<String, TreeSet<String>> allergens = new HashMap<String, TreeSet<String>>();
	private static Map<String, Date> expirations = new HashMap<String, Date>();
	private static Map<String, Double> amounts = new HashMap<String, Double>();
	
	
//CREATE & DELETE
	public static void setIngredient(String name, double amount, Unit unit, TreeSet<String> allergen, Date expiration){ //build new ingredient from scratch
		amounts.put(name, amount);
		units.put(name, unit);
		allergens.put(name, allergen);
		expirations.put(name, expiration);
	}
	public static void deleteIngredient(String name){						//delete all keys and values for one ingredient
		amounts.remove(name);
		units.remove(name);
		allergens.remove(name);
		expirations.remove(name);
	}
	
	
//GETTERS
	public static Vector<String> listIngredients(){							//get list of all ingredients in storage, in A-Z order
		Vector<String> temp = new Vector<String>();
		temp.addAll(units.keySet());
		return temp;
	}
	public static Double getAmount(String name){							//get amount (in storage) of specified ingredient
		return amounts.get(name);
	}
	public static Unit getUnit(String name){								//get the unit of specified ingredient
		return units.get(name);
	}
	public static Vector<String> getAllergens(String name){					//get list of all allergens for specified ingredient, in A-Z order
		Vector<String> temp = new Vector<String>();
		temp.addAll(allergens.get(name));
		return temp;
	}
	public static Date getExpiration(String name){							//get the expiration date of specified ingredient
		return expirations.get(name);
	}
	public static boolean hasIngredient(String name){						//true, if specified ingredient is in storage
		boolean has = false;
		for(String iname : units.keySet()){
			if (iname.equals(name)) has = true;
		}
		return has;
	}

	
//SETTERS
	public static void setAmount(String name, double amount){				//replace old amount with new one
		amounts.put(name, amount);
	}
	public static void setUnit(String name, Unit unit){						//replace old unit with new one
		units.put(name, unit);
	}
	public static void setAllergens(String name, TreeSet<String> allergen){	//replace old allergens with new ones
		allergens.put(name, allergen);
	}
	public static void setExpiration(String name, Date expiration){			//replace old expiration date with new one
		expirations.put(name, expiration);
	}



		

	

}
