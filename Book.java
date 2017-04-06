package recipeTool;
import java.util.*;

/**
 * This entirely static class contains all the recipes. It stores the following information:<br>
 * -recipe name<br>-instructions<br>-ingredients (name & amount)<p>
 * This class will retrieve additional recipe information using {@link recipeTool.Storage}:<br>
 * -summary of ingredients allergens<br>
 * -the soonest expiration date and related ingredient<br>
 * -does the storage has enough ingredients to prepare at least one serving?
 * @author Ville Salmela
 *
 */
class Book { //package-private
	
//PROPERTIES
	final private static DuoTable<String, String, LinkedHashMap<String, Double>> duoTable = new DuoTable<>();
	/*
	 * FIRST = Instruction
	 * SECOND = Ingredients
	 */
	
	private Book(){}
	
//CREATE & DELETE
	
	/**
	 * This method will insert one recipe into the book with the specified attributes.
	 * If the recipe already exists, new values will be used to replace existing values.
	 * @param name The name of the recipe. Cannot be {@code null} or empty.
	 * @param instruction The instruction for the recipe.
	 * @param ingredient A map of ingredients, containing linked ingredient names and amounts.<br>
	 * Ingredient name cannot be {@code null} or empty and amount cannot be {@code null} or negative.
	 * @return Nothing.
	 * @throws IllegalArgumentException if {@code name} of the recipe or in the map is {@code null}/empty, or if any amount in map {@code ingredient} is  {@code null}/empty.
	 */
	public static void setRecipe(String name, String instruction, LinkedHashMap<String, Double> ingredient){		//build new recipe from scratch
		if (name == null || name.equals("")) throw new IllegalArgumentException();
		for (String iname : ingredient.keySet()){
			if (iname == null || iname.equals("") || ingredient.get(iname) == null || ingredient.get(iname) < 0) throw new IllegalArgumentException();
		}
		duoTable.set(name, instruction, ingredient);
	}
	
	
	/**
	 * This method will remove one recipe from the book.
	 * @param name The name of the recipe to be removed. Cannot be {@code null} or empty.
	 * @return Nothing.
	 * @throws NoSuchElementException if the recipe with attribute {@code name} does not exist in book.
	 * @throws IllegalArgumentException if {@code name} is {@code null}/empty
	 */
	public static void deleteRecipe(String name){									//delete entire recipe
		if (name == null || name.equals("")) throw new IllegalArgumentException();
		if (hasRecipe(name) == false) throw new NoSuchElementException();
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
	/**
	 * This method will list all allergens that the specified recipe contains.
	 * @param rname The recipe name.
	 * @return A list of allergens.
	 * @throws NoSuchElementException if the recipe with attribute {@code rname} does not exist in book.
	 * @throws IllegalArgumentException if {@code rname} is {@code null}/empty
	 */
	public static TreeSet<String> getAllergens(String rname){						//get list of all allergens in one recipe
		if (rname == null || rname.equals("")) throw new IllegalArgumentException();
		if (hasRecipe(rname) == false) throw new NoSuchElementException();
		TreeSet<String> temp = new TreeSet<>();								//	in A-Z order
		for (String iname : duoTable.getSecond(rname).keySet()){
			if (Storage.getAllergens(iname) != null) temp.addAll(Storage.getAllergens(iname));
		}
		return temp;
	}
	
	/**
	 * This method will check which one of the recipe's ingredients has the soonest expiration date.
	 * @param rname The recipe name.
	 * @return An object array, containing the [expiration date, ingredient name].
	 * @throws NoSuchElementException if the recipe with attribute {@code rname} does not exist in book.
	 * @throws IllegalArgumentException if {@code rname} is {@code null}/empty
	 */
	public static Object[] getExpiration(String rname) {								//get the closest expiration date
		if (rname == null || rname.equals("")) throw new IllegalArgumentException();
		if (hasRecipe(rname) == false) throw new NoSuchElementException();
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

	
	/**
	 * This method will check, if there is enough ingredients in storage, to prepare the specified recipe.
	 * @param rname Recipe name
	 * @return {@code true} if there is enough ingredients in storage,<br>
	 * {@code false} if there is NOT enough ingredients in storage.
	 * @throws NoSuchElementException if the recipe does not exist in the book.
	 * @throws IllegalArgumentException if {@code rname} is {@code null}/empty.
	 */
	public static boolean getEnough(String rname) {									//true if enough ingredients in storage
		if (rname == null || rname.equals("")) throw new IllegalArgumentException();
		if (hasRecipe(rname) == false) throw new NoSuchElementException();
		boolean enough = true;														//	false if NOT enough ingredients in storage
		for (String iname : duoTable.getSecond(rname).keySet()){
			if (getAmount(rname, iname) > Storage.getAmount(iname)) enough = false;
		}
		return enough;
	}
	
//HAS
	
	/**
	 * This method will check, if the book contains the specified recipe.
	 * @param name The name of the recipe. Cannot be {@code null} or empty.
	 * @return {@code true} if the recipe is in the book.<br>{@code false} if the recipe is NOT in the book.
	 * @throws IllegalArgumentException if {@code name} is {@code null}/empty.
	 */
	public static boolean hasRecipe(String name){
		if (name == null || name.equals("")) throw new IllegalArgumentException();
		return duoTable.getKeys().contains(name);
	}
	
	
	/**
	 * This method will check, if the specified recipe contains the specified ingredient.
	 * @param rname The name of the recipe.
	 * @param iname The name of the ingredient.
	 * @return {@code true} if the recipe contains the ingredient<br>
	 * {@code false} if the recipe does NOT contain the ingredient.
	 * @throws IllegalArgumentException if {@code rname} or {@code iname} is {@code null}/empty.
	 * @throws NoSuchElementException if the recipe does not exist in the book.
	 */
	public static boolean hasIngredient(String rname, String iname){				//true, if recipe has specified ingredient
		if (rname == null || rname.equals("") || iname == null || iname.equals("")) throw new IllegalArgumentException();
		if (hasRecipe(rname) == false) throw new NoSuchElementException();
		return duoTable.getSecond(rname).keySet().contains(iname);
	}

//LIST
	/**
	 * This method will list all recipes in the book.
	 * @return A list containing all recipes, sorted alphabetically.
	 */
	public static TreeSet<String> listRecipes(){										//get list of all recipes in A-Z order
		return duoTable.getKeys();
	}
	
	/**
	 * This method will list all ingredient names in specified recipe.
	 * @param name The name of the recipe.
	 * @return A list of all the ingredient names in one recipe, in the order of input.
	 * @throws NoSuchElementException if the recipe does not exist in the book.
	 * @throws IllegalArgumentException if {@code name} is {@code null}/empty.
	 */
	public static LinkedHashSet<String> listIngredients(String name) {				//get list of ingredients in one recipe
		if (name == null || name.equals("")) throw new IllegalArgumentException();
		if (hasRecipe(name) == false) throw new NoSuchElementException();
		return new LinkedHashSet<>(duoTable.getSecond(name).keySet());
	}
	
	/**
	 * This method will check, which recipes contain a specified ingredient.
	 * @param iname Ingredient name
	 * @return A list of all recipes which contain the ingredient, sorted alphabetically.
	 * @throws NoSuchElementException if the ingredient does not exist in storage.
	 * @throws IllegalArgumentException if {@code iname} is {@code null}/empty.
	 */
	public static TreeSet<String> whoHas(String iname){
		if (iname == null || iname.equals("")) throw new IllegalArgumentException();
		if (Storage.hasIngredient(iname) == false) throw new NoSuchElementException();
		TreeSet<String> temp = listRecipes();
		for (String recipe : listRecipes()){
			if (hasIngredient(recipe, iname) == false) temp.remove(recipe);
		}
		return temp;
	}
	
//FILTER
	/**
	 * This method will filter recipes based on multiple parameters.
	 * @param mustHave List of those ingredients which must be present in the desired recipe.
	 * @param avoidAllergens List of those allergens which must not be present in the desired recipe.
	 * @param enough {@code true} if the all the required ingredients must be available in storage, {@code false} otherwise.
	 * @param sort {@code true} if the recipes should be sorted alphabetically, {@code false} if they should be sorted by expiration date.
	 * @return A list with desired recipes, or {@code null} if there are none.
	 * @throws IllegalArgumentException if {@code mustHave} or {@code avoidAllergens} is {@code null}.
	 */
	public static String[] filterRecipes(List<String> mustHave, List<String> avoidAllergens, boolean enough, boolean sort){
		if (mustHave == null || avoidAllergens == null) throw new IllegalArgumentException();
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
		return temp.isEmpty() ? null : temp.toArray(new String[0]);
	}
}
