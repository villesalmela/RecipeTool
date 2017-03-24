package recipeTool;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.*;

public class Storage {
	
//PROPERTIES
	private static Set<String> ingredients = new TreeSet<String>();
	private static Map<String, Unit> units = new TreeMap<String, Unit>();
	private static Map<String, TreeSet<String>> allergens = new HashMap<String, TreeSet<String>>();
	private static Map<String, Date> expirations = new HashMap<String, Date>();
	private static Map<String, Double> amounts = new HashMap<String, Double>();
	
	
//CREATE & DELETE
	public static void setIngredient(String name, double amount, Unit unit, String[] allergen, Date expiration){ //build new ingredient from scratch
		ingredients.add(name);
		amounts.put(name, amount);
		units.put(name, unit);
		allergens.put(name, new TreeSet<String>(Arrays.asList(allergen)));
		expirations.put(name, expiration);
	}
	
	
	public static void deleteIngredient(String name){						//delete all keys and values for one ingredient
		ingredients.remove(name);
		amounts.remove(name);
		units.remove(name);
		allergens.remove(name);
		expirations.remove(name);
	}
	
	
//GETTERS
	public static String[] listIngredients(){							//get list of all ingredients in storage, in A-Z order
		return ingredients.toArray(new String[0]);
	}
	
	
	public static String[] listAllergens(){
		Set<String> temp = new TreeSet<String>();
		for(String i : ingredients){
			temp.addAll(Arrays.asList(getAllergens(i)));
		}
		return temp.toArray(new String[0]);
	}
	
	
	public static Double getAmount(String name){							//get amount (in storage) of specified ingredient
		return new BigDecimal(amounts.get(name)).setScale(2, RoundingMode.HALF_UP).doubleValue();
	}
	
	
	public static Unit getUnit(String name){								//get the unit of specified ingredient
		return units.get(name);
	}
	
	
	public static String[] getAllergens(String name){					//get list of all allergens for specified ingredient, in A-Z order
		return allergens.get(name).toArray(new String[0]);
	}
	
	
	public static Date getExpiration(String name){							//get the expiration date of specified ingredient
		return expirations.get(name);
	}
	
	
	public static boolean hasIngredient(String name){						//true, if specified ingredient is in storage
		boolean has = false;
		for(String i : ingredients){
			if (i.equals(name)) has = true;
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
