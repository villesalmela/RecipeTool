package recipeTool;
import java.util.*;

public class Book {
	
//PROPERTIES
	final private static DuoTable<String, String, LinkedHashMap<String, Double>> duoTable = new DuoTable<>();
	/*
	 * FIRST = Instruction
	 * SECOND = Ingredients
	 */
	
//CREATE & DELETE
	public static void setRecipe(String name, String instruction, LinkedHashMap<String, Double> ingredient){		//build new recipe from scratch
		duoTable.set(name, instruction, ingredient);
	}
	
	public static void deleteRecipe(String name){									//delete entire recipe
		duoTable.deleteKey(name);
	}
	
	public static void deleteIngredient(String rname, String iname){				//delete one ingredient in one recipe
		duoTable.getSecond(rname).remove(iname);
	}
	
	public static void deleteAllIngredients(String iname){							//delete one ingredient in all recipes
		for (String rname : duoTable.getKeys()){
			duoTable.getSecond(rname).remove(iname);
		}
	}
	
//GETTERS (INTERNAL)
	public static String getInsturction(String name){								//get instruction for one recipe
		return duoTable.getFirst(name);
	}
	
	public static Double getAmount(String rname, String iname){						//get amount of one ingredient in one recipe
		return duoTable.getSecond(rname).get(iname);
	}
	
//GETTERS (EXTERNAL)
	public static TreeSet<String> getAllergens(String rname){						//get list of all allergens in one recipe
		TreeSet<String> temp = new TreeSet<>();								//	in A-Z order
		for (String iname : duoTable.getSecond(rname).keySet()){
			temp.addAll(Storage.getAllergens(iname));
		}
		return temp;
	}
	
	public static Object[] getExpiration(String rname) {								//get the closest expiration date
		String temp = null;																//	and the name of this ingredient
		Date expiration = null;														
		for (String iname : duoTable.getSecond(rname).keySet()){
			if (Storage.getExpiration(iname) == null) continue;
			else if (expiration == null) {
				expiration = Storage.getExpiration(iname);
				temp = iname;
			}
			else if (expiration.after(Storage.getExpiration(iname)) == true){
				expiration = Storage.getExpiration(iname);
				temp = iname;
			}
		}
		Object[] pair = new Object[]{expiration, temp};
		return pair;
	}

	public static boolean getEnough(String rname) {									//true if enough ingredients in storage
		boolean enough = true;														//	false if NOT enough ingredients in storage
		for (String iname : duoTable.getSecond(rname).keySet()){
			if (getAmount(rname, iname) > Storage.getAmount(iname)) enough = false;
		}
		return enough;
	}
	
//SETTERS
	public static void setInstruction(String name, String instruction){				//replace old instruction with new one
		duoTable.set(name, instruction, null);
	}
	
	public static void setIngredient(String rname, String iname, double amount){	//add new ingredient to existing recipe
		duoTable.getSecond(rname).put(iname, amount);
	}
	
//HAS
	public static boolean hasRecipe(String name){
		boolean has = false;
		if (duoTable.getKeys().contains(name) == true) has = true;
		return has;
	}
	
	public static boolean hasIngredient(String rname, String iname){				//true, if recipe has specified ingredient
		boolean has = false;
		if (duoTable.getSecond(rname).keySet().contains(iname) == true) has = true;
		return has;
	}

//LIST
	public static TreeSet<String> listRecipes(){										//get list of all recipes in A-Z order
		return duoTable.getKeys();
	}
	
	public static LinkedHashSet<String> listIngredients(String name) {				//get list of ingredients in one recipe
		return new LinkedHashSet<>(duoTable.getSecond(name).keySet());
	}
	
	public static TreeSet<String> whoHas(String iname){
		TreeSet<String> temp = listRecipes();
		for (String recipe : listRecipes()){
			if (hasIngredient(recipe, iname) == false) temp.remove(recipe);
		}
		return temp;
	}
	
//FILTER
	public static String[] filterRecipes(List<String> mustHave, List<String> avoidAllergens, boolean enough, boolean sort){
		List<String> temp = new ArrayList<>();
		temp.addAll(listRecipes());
		for (String rname : listRecipes()){
			if (mustHave.isEmpty() == false){
				if (duoTable.getSecond(rname).keySet().containsAll(mustHave) == false) temp.remove(rname);
			}
			if (avoidAllergens.isEmpty() == false){
				for (String allergen : avoidAllergens){
					if (getAllergens(rname).contains(allergen)) temp.remove(rname);
				}
			}
			if (enough == true){
				for (String iname : duoTable.getSecond(rname).keySet()){
					if(Storage.getAmount(iname) < getAmount(rname, iname)) temp.remove(rname);
				}
			}
		}
		if (sort == true) Collections.sort(temp);
		else Collections.sort(temp, new Expiry());
		return temp.toArray(new String[0]);
	}
}
