package recipeTool;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.*;

public class Book {
	
//PROPERTIES
	private static Set<String> recipes = new TreeSet<String>();
	private static Map<String, String> instructions = new HashMap<String, String>();
	private static Map<String, LinkedHashMap<String, Double>> ingredients = new HashMap<String, LinkedHashMap<String, Double>>();

	
//CREATE & DELETE
	public static void setRecipe(String name, String instruction, LinkedHashMap<String, Double> ingredient){		//build new recipe from scratch
		recipes.add(name);
		instructions.put(name, instruction);
		ingredients.put(name, ingredient);
	}
	
	
	public static void deleteRecipe(String name){									//delete entire recipe
		recipes.remove(name);
		instructions.remove(name);
		ingredients.remove(name);
	}
	
	
	public static void deleteIngredient(String rname, String iname){				//delete one ingredient in one recipe
		ingredients.get(rname).remove(iname);
	}
	
	
	public static void deleteAllIngredient(String iname){							//delete one ingredient in all recipes
		for (String rname : recipes){
			ingredients.get(rname).remove(iname);
		}
	}
	
	
//GETTERS
	public static String[] listRecipes(){										//get list of all recipes in A-Z order
		return recipes.toArray(new String[0]);
	}
	
	
	public static String[] listIngredients(String name) {				//get list of ingredients in one recipe
		return ingredients.get(name).keySet().toArray(new String[0]);
	}
	
	
	public static boolean hasIngredient(String rname, String iname){				//true, if recipe has specified ingredient
		boolean has = false;
		if (ingredients.get(rname).keySet().contains(iname)) has = true;
		return has;
	}
	
	
	public static String[] getAllergens(String rname){						//get list of all allergens in one recipe
		TreeSet<String> temp = new TreeSet<String>();								//	in A-Z order
		for (String iname : ingredients.get(rname).keySet()){
			temp.addAll(Arrays.asList(Storage.getAllergens(iname)));
		}
		return temp.toArray(new String[0]);
	}
	
	
	public static Object[] getExpiration(String rname) {								//get the closest expiration date
		String temp = null;																//	and the name of this ingredient
		Date expiration = null;														
		for (String iname : ingredients.get(rname).keySet()){
			if (Storage.getExpiration(iname) == null) continue;
			else if (expiration == null) {
				expiration = Storage.getExpiration(iname);
				temp = iname;
			}
			else if (expiration.after(Storage.getExpiration(iname))){
				expiration = Storage.getExpiration(iname);
				temp = iname;
			}
		}
		Object[] pair = new Object[]{expiration, temp};
		return pair;
	}

	
	public static Boolean getEnough(String rname) {									//true if enough ingredients in storage
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
		return new BigDecimal(ingredients.get(rname).get(iname)).setScale(2, RoundingMode.HALF_UP).doubleValue();
	}
	
	
	public static String[] filterRecipes(List<String> mustHave, List<String> avoidAllergens, boolean enough, boolean sort){
		List<String> temp = new ArrayList<String>();
		temp.addAll(Arrays.asList(listRecipes()));
		for (String rname : recipes){
			if (!(mustHave.isEmpty())){
				if (!(ingredients.get(rname).keySet().containsAll(mustHave))) temp.remove(rname);
			}
			if (!(avoidAllergens.isEmpty())){
				for (String allergen : avoidAllergens){
					if (Arrays.asList(getAllergens(rname)).contains(allergen)) temp.remove(rname);
				}
			}
			if (enough){
				for (String iname : ingredients.get(rname).keySet()){
					if(Storage.getAmount(iname) < getAmount(rname, iname)) temp.remove(rname);
				}
			}
		}
		if (sort) Collections.sort(temp);
		else Collections.sort(temp, new Expiry());
		return temp.toArray(new String[0]);
	}

	
//SETTERS
	public static void setInstruction(String name, String instruction){				//replace old instruction with new one
		instructions.put(name, instruction);
	}
	
	
	public static void setIngredient(String rname, String iname, double amount){	//add new ingredient to existing recipe
		ingredients.get(rname).put(iname, amount);
	}
}
