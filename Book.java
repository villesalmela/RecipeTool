package recipeTool;
import java.util.*;

public class Book {
	
//PROPERTIES
	private static Map<String, String> instructions = new HashMap<String, String>();
	private static Map<String, LinkedHashMap<String, Double>> ingredients = new HashMap<String, LinkedHashMap<String, Double>>();

	
//CREATE & DELETE
	public static void setRecipe(String name, String instruction, LinkedHashMap<String, Double> ingredient){		//build new recipe from scratch
		instructions.put(name, instruction);
		ingredients.put(name, ingredient);
	}
	public static void deleteRecipe(String name){									//delete entire recipe
		instructions.remove(name);
		ingredients.remove(name);
	}
	public static void deleteIngredient(String rname, String iname){				//delete one ingredient in one recipe
		ingredients.get(rname).remove(iname);
	}
	public static void deleteAllIngredient(String iname){							//delete one ingredient in all recipes
		for (String rname : listRecipes()){
			ingredients.get(rname).remove(iname);
		}
	}
	
	
//GETTERS
	public static Vector<String> listRecipes(){										//get list of all recipes in A-Z order
		Vector<String> temp = new Vector<String>();
		temp.addAll(ingredients.keySet());
		return temp;
	}
	public static LinkedHashSet<String> listIngredients(String name) {				//get list of ingredients in one recipe
		LinkedHashSet<String> temp = new LinkedHashSet<String>();					//	in user defined order
		temp.addAll(ingredients.get(name).keySet());
		return temp;
	}
	public static boolean hasIngredient(String rname, String iname){				//true, if recipe has specified ingredient
		boolean has = false;
		if (listIngredients(rname).contains(iname)) has = true;
		return has;
	}
	public static TreeSet<String> getAllergens(String rname){						//get list of all allergens in one recipe
		TreeSet<String> temp = new TreeSet<String>();								//	in A-Z order
		for (String iname : ingredients.get(rname).keySet()){
			temp.addAll(Storage.getAllergens(iname));
		}
		return temp;
	}
	public static Date getExpiration(String rname) {								//get the closest expiration date
		Date expiration = null;														//	for any ingredient in one recipe
		for (String iname : ingredients.get(rname).keySet()){
			if (Storage.getExpiration(iname) == null) continue;
			else if (expiration == null) expiration = Storage.getExpiration(iname);
			else if (expiration.after(Storage.getExpiration(iname))) expiration = Storage.getExpiration(iname);
		}
		return expiration;
	}
	public static boolean getEnough(String rname) {									//true if enough ingredients in storage
		boolean enough = true;														//	false if NOT enough ingredients in storage
		for (String iname : ingredients.get(rname).keySet()){
			if (getAmount(rname, iname) > Storage.getAmount(iname)) enough = false;
		}
		return enough;
	}
	public static String getInsturction(String name){								//get instruction for one recipe
		return instructions.get(name);
	}
	public static Double getAmount(String rname, String iname){						//get amount of one ingredient in one recipe
		return ingredients.get(rname).get(iname);
	}

	
//SETTERS
	public static void setInstruction(String name, String instruction){				//replace old instruction with new one
		instructions.put(name, instruction);
	}
	public static void setIngredient(String rname, String iname, double amount){	//add new ingredient to existing recipe
		ingredients.get(rname).put(iname, amount);
	}

	



}
