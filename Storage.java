package recipeTool;
import java.util.*;

public class Storage {
	
//PROPERTIES
	final private static QuadTable<String, Unit, TreeSet<String>, Date, Double> quadTable = new QuadTable<>();
	/*
	 * FIRST = Unit
	 * SECOND = Allergens
	 * THIRD = Expiration
	 * FOURTH = Amount
	 */
	
	
//CREATE & DELETE
	public static void setIngredient(String name, double amount, Unit unit, TreeSet<String> allergen, Date expiration){ //build new ingredient from scratch
		quadTable.set(name, unit, allergen, expiration, amount);
	}
	
	public static void deleteIngredient(String name){						//delete all keys and values for one ingredient
		quadTable.deleteKey(name);
	}
	
//GETTERS
	public static Unit getUnit(String name){								//get the unit of specified ingredient
		return quadTable.getFirst(name);
	}
	
	public static TreeSet<String> getAllergens(String name){					//get list of all allergens for specified ingredient, in A-Z order
		return quadTable.getSecond(name);
	}
	
	public static Date getExpiration(String name){							//get the expiration date of specified ingredient
		return quadTable.getThird(name);
	}
	
	public static double getAmount(String name){							//get amount (in storage) of specified ingredient
		return quadTable.getFourth(name);
	}
	
//SETTERS
	public static void setUnit(String name, Unit unit){						//replace old unit with new one
		quadTable.set(name, unit, null, null, null);
	}
		
	public static void setAllergens(String name, TreeSet<String> allergen){	//replace old allergens with new ones
		quadTable.set(name, null, allergen, null, null);
	}
	
	public static void setExpiration(String name, Date expiration){			//replace old expiration date with new one
		quadTable.set(name, null, null, expiration, null);
	}
	
	public static void setAmount(String name, double amount){				//replace old amount with new one
		quadTable.set(name, null, null, null, amount);
	}
	
//HAS
	public static boolean hasIngredient(String name){						//true, if specified ingredient is in storage
		return quadTable.getKeys().contains(name);
	}
	
//LISTERS
	public static TreeSet<String> listIngredients(){							//get list of all ingredients in storage, in A-Z order
		return quadTable.getKeys();
	}
	public static TreeSet<String> listAllergens(){
		TreeSet<String> temp = new TreeSet<>();
		for (String i: listIngredients()){
			temp.addAll(quadTable.getSecond(i));
		}
		return temp;
	}
		
}
