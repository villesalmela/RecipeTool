package recipeTool;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedHashMap;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Map;
import java.util.NoSuchElementException;
import java.util.Set;
import java.util.TreeSet;

import utilities.InvalidDataException;

/**
 * This class contains all the recipes. It stores the following information:<br>
 * -recipe name<br>
 * -instructions<br>
 * -ingredients (name and amount)
 * <p>
 * This class will retrieve additional recipe information from
 * {@link recipeTool.Storage}:<br>
 * -summary of ingredients allergens<br>
 * -the soonest expiration date and related ingredient<br>
 * -does the storage has enough ingredients to prepare at least one serving?
 * 
 * @author Ville Salmela
 *
 */
class Book { // package-private

	// PROPERTIES
	/**
	 * This Map holds all the recipes. The key is recipe name, and values are
	 * recipe properties, stored in Object array.<br>
	 * The first item in Object array must be string, which contains the
	 * instructions for one recipe.<br>
	 * The second item in Object array must be map, which contains ingredients
	 * and their amounts for one recipe.
	 * <p>
	 * This field should be accessed using setter and getter methods provided in
	 * this class.
	 */
	final private static Map<String, Object[]> map = new HashMap<>();

	/**
	 * This constructor is private, thus preventing the instantiation of this
	 * class.
	 */
	private Book() {
	}

	/**
	 * This is a private method, which returns a map containing ingredients
	 * and their amounts, for one recipe.<br>
	 * The method just retrieves the second object from object array, which is
	 * mapped to a recipe in {@link recipeTool.Book#map}
	 * 
	 * @param key
	 *            The name of the recipe.
	 * @return A Map which contains the ingredients and amount for one recipe,
	 *         in the same order they were inputed.
	 * @throws IllegalArgumentException if key is {@code null} or empty.
	 * @throws NoSuchElementException if there is no such recipe in the book.
	 */
	@SuppressWarnings("unchecked")
	private static LinkedHashMap<String, Double> getIngredients(String key) {
		if (key == null || key.equals("")) throw new IllegalArgumentException();
		if (hasRecipe(key) == false) throw new NoSuchElementException();
		return ((LinkedHashMap<String, Double>) map.get(key)[1]);
	}

	// CREATE & DELETE

	/**
	 * This method will insert one recipe into the book with the specified
	 * attributes. If the recipe already exists, new values will be used to
	 * replace existing values.
	 * 
	 * @param name
	 *            The name of the recipe. Cannot be {@code null} or empty.
	 * @param instruction
	 *            The instruction for the recipe, or {@code null} if there is
	 *            none.
	 * @param ingredient
	 *            A map of ingredients, containing linked ingredient names and
	 *            amounts.<br>
	 *            Ingredient name cannot be {@code null} or empty and amount
	 *            cannot be {@code null} or negative.<br>
	 *            There has to be at least one pair in the map.
	 * @throws InvalidDataException if any parameter is not accepted.
	 */
	public static void setRecipe(String name, String instruction, LinkedHashMap<String, Double> ingredient) throws InvalidDataException {
		// Validate recipe name
		if (name == null || name.equals(""))
			throw new InvalidDataException("Recipe name cannot be empty.");
		// Validate ingredients
		if (ingredient == null || ingredient.isEmpty()) throw new InvalidDataException("There must be at least one ingredient."); 
		for (String iname : ingredient.keySet()) {
			
			if (iname == null || iname.equals(""))
				throw new InvalidDataException("Ingredient name cannot be empty.");
			if (Storage.hasIngredient(iname) == false) throw new InvalidDataException("Ingredient is unknown.");

			if (ingredient.get(iname) == null || ingredient.get(iname) < 0)
				throw new InvalidDataException("Ingredient amount cannot be null or negative.");
		}

		// Set properties
		map.put(name, new Object[] { instruction, ingredient });
	}
	
	/**
	 * This method will delete all recipes in book.
	 */
	public static void clear(){
		map.clear();
	}
	
	/**
	 * This method will rename one ingredient in all recipes.
	 * @param original The old name.
	 * @param replacing The new name.
	 * @throws IllegalArgumentException if either name is {@code null} or empty.
	 */
	public static void renameIngredient(String original, String replacing){
		if (original == null || replacing == null || original.equals("") || replacing.equals("")) throw new IllegalArgumentException();
		for (String rname : whoHas(original)){
			Double amount = getIngredients(rname).remove(original);
			getIngredients(rname).put(replacing, amount);
		}
	}

	/**
	 * This method will remove one recipe from the book.
	 * 
	 * @param name
	 *            The name of the recipe to be removed. Cannot be {@code null}
	 *            or empty.
	 * 
	 * @throws NoSuchElementException
	 *             if the recipe with attribute {@code name} does not exist in
	 *             book.
	 * @throws IllegalArgumentException
	 *             if {@code name} is {@code null}/empty
	 */
	public static void deleteRecipe(String name) {
		if (name == null || name.equals(""))
			throw new IllegalArgumentException();
		if (hasRecipe(name) == false)
			throw new NoSuchElementException();
		map.remove(name);
	}

	/**
	 * This method will remove one ingredient from all recipes.
	 * If any recipe becomes empty (has no ingredients), the recipe will be removed.
	 * 
	 * @param iname
	 *            The ingredient name, which should be removed.
	 * @throws IllegalArgumentException
	 *             if name is empty or {@code null}.
	 */
	public static void deleteAllIngredients(String iname) {
		if (iname == null || iname.equals(""))
			throw new IllegalArgumentException();
		Set<String> toBeDeleted = new HashSet<>();
		for (String rname : map.keySet()) {
			getIngredients(rname).remove(iname);
			if(listIngredients(rname).isEmpty()) toBeDeleted.add(rname);
		}
		for (String recipe : toBeDeleted){
			deleteRecipe(recipe);
		}
		
	}

	// GETTERS (INTERNAL)
	/**
	 * This method will get instructions for one recipe.
	 * 
	 * @param rname
	 *            The recipe name.
	 * @return String containing the instructions, or {@code null} if there is
	 *         none.
	 * @throws IllegalArgumentException
	 *             if name is empty or {@code null}.
	 */
	public static String getInsturction(String rname) {
		if (rname == null || rname.equals(""))
			throw new IllegalArgumentException();
		return (String) map.get(rname)[0];
	}

	/**
	 * This method will get amount of one ingredient in one recipe.
	 * 
	 * @param rname
	 *            The recipe name.
	 * @param iname
	 *            The ingredient name.
	 * @return The amount of the ingredient in certain recipe, never
	 *         {@code null}.
	 * @throws IllegalArgumentException
	 *             if either name is empty or {@code null}.
	 * @throws NoSuchElementException
	 *             if the recipe doesn't exist in book, or ingredient doesn't
	 *             exist in the recipe.
	 */
	public static Double getAmount(String rname, String iname) {
		if (rname == null || rname.equals("") || iname == null || iname.equals(""))
			throw new IllegalArgumentException();
		if (hasRecipe(rname) == false || hasIngredient(rname, iname) == false)
			throw new NoSuchElementException();
		return getIngredients(rname).get(iname);
	}

	// GETTERS (EXTERNAL)
	/**
	 * This method will list all allergens that the specified recipe contains.
	 * 
	 * @param rname
	 *            The recipe name.
	 * @return A list of allergens, or {@code null} if there are none.
	 * @throws NoSuchElementException
	 *             if the recipe with attribute {@code rname} does not exist in
	 *             book.
	 * @throws IllegalArgumentException
	 *             if {@code rname} is {@code null}/empty
	 */
	public static TreeSet<String> getAllergens(String rname) {
		if (rname == null || rname.equals(""))
			throw new IllegalArgumentException();
		if (hasRecipe(rname) == false)
			throw new NoSuchElementException();
		TreeSet<String> temp = new TreeSet<>();
		for (String iname : getIngredients(rname).keySet()) {
			if (Storage.getAllergens(iname) != null)
				temp.addAll(Storage.getAllergens(iname));
		}
		return temp.isEmpty() ? null : temp;
	}

	/**
	 * This method will check which one of the recipe's ingredients has the
	 * soonest expiration date.
	 * 
	 * @param rname
	 *            The recipe name.
	 * @return An object array, containing the [expiration date, ingredient
	 *         name].
	 * @throws NoSuchElementException
	 *             if the recipe with attribute {@code rname} does not exist in
	 *             book.
	 * @throws IllegalArgumentException
	 *             if {@code rname} is {@code null}/empty
	 */
	public static Object[] getExpiration(String rname) {
		if (rname == null || rname.equals(""))
			throw new IllegalArgumentException();
		if (hasRecipe(rname) == false)
			throw new NoSuchElementException();
		String temp = null;
		Date expiration = null;
		for (String iname : getIngredients(rname).keySet()) {
			if (Storage.getExpiration(iname) == null)
				continue;
			else if (expiration == null) {
				expiration = Storage.getExpiration(iname);
				temp = iname;
			} else if (expiration.after(Storage.getExpiration(iname)) == true) {
				expiration = Storage.getExpiration(iname);
				temp = iname;
			}
		}
		Object[] pair = new Object[] { expiration, temp };
		return pair;
	}

	/**
	 * This method will check, if there is enough ingredients in storage, to
	 * prepare the specified recipe.
	 * 
	 * @param rname
	 *            Recipe name
	 * @return {@code true} if there is enough ingredients in storage,<br>
	 *         {@code false} if there is NOT enough ingredients in storage.
	 * @throws NoSuchElementException
	 *             if the recipe does not exist in the book.
	 * @throws IllegalArgumentException
	 *             if {@code rname} is {@code null}/empty.
	 */
	public static boolean isEnough(String rname) {
		if (rname == null || rname.equals(""))
			throw new IllegalArgumentException();
		if (hasRecipe(rname) == false)
			throw new NoSuchElementException();
		boolean enough = true;
		for (String iname : getIngredients(rname).keySet()) {
			if (getAmount(rname, iname) > Storage.getAmount(iname))
				enough = false;
		}
		return enough;
	}

	// HAS

	/**
	 * This method will check, if the book contains the specified recipe.
	 * 
	 * @param name
	 *            The name of the recipe. Cannot be {@code null} or empty.
	 * @return {@code true} if the recipe is in the book.<br>
	 *         {@code false} if the recipe is NOT in the book.
	 * @throws IllegalArgumentException
	 *             if {@code name} is {@code null}/empty.
	 */
	public static boolean hasRecipe(String name) {
		if (name == null || name.equals(""))
			throw new IllegalArgumentException();
		return map.keySet().contains(name);
	}

	/**
	 * This method will check, if the specified recipe contains the specified
	 * ingredient.
	 * 
	 * @param rname
	 *            The name of the recipe.
	 * @param iname
	 *            The name of the ingredient.
	 * @return {@code true} if the recipe contains the ingredient<br>
	 *         {@code false} if the recipe does NOT contain the ingredient.
	 * @throws IllegalArgumentException
	 *             if {@code rname} or {@code iname} is {@code null}/empty.
	 * @throws NoSuchElementException
	 *             if the recipe does not exist in the book.
	 */
	public static boolean hasIngredient(String rname, String iname) {
		if (rname == null || rname.equals("") || iname == null || iname.equals(""))
			throw new IllegalArgumentException();
		if (hasRecipe(rname) == false)
			throw new NoSuchElementException();
		return getIngredients(rname).keySet().contains(iname);
	}

	// LIST
	/**
	 * This method will list all recipes in the book.
	 * 
	 * @return A list containing all recipes, sorted alphabetically, never
	 *         {@code null}.
	 */
	public static TreeSet<String> listRecipes() {
		return new TreeSet<>(map.keySet());
	}

	/**
	 * This method will list all ingredient names in specified recipe.
	 * 
	 * @param rname
	 *            The name of the recipe.
	 * @return A list of all the ingredient names in one recipe, in the order of
	 *         input, never {@code null}.
	 * @throws NoSuchElementException
	 *             if the recipe does not exist in the book.
	 * @throws IllegalArgumentException
	 *             if {@code name} is {@code null}/empty.
	 */
	public static LinkedHashSet<String> listIngredients(String rname) {
		if (rname == null || rname.equals(""))
			throw new IllegalArgumentException();
		if (hasRecipe(rname) == false)
			throw new NoSuchElementException();
		return new LinkedHashSet<>(getIngredients(rname).keySet());
	}

	/**
	 * This method will check, which recipes contain a specified ingredient.
	 * 
	 * @param iname
	 *            Ingredient name
	 * @return A list of all recipes which contain the ingredient, sorted
	 *         alphabetically, never {@code null}.
	 * @throws NoSuchElementException
	 *             if the ingredient does not exist in storage.
	 * @throws IllegalArgumentException
	 *             if {@code iname} is {@code null}/empty.
	 */
	public static TreeSet<String> whoHas(String iname) {
		if (iname == null || iname.equals(""))
			throw new IllegalArgumentException();
		if (Storage.hasIngredient(iname) == false)
			throw new NoSuchElementException();
		TreeSet<String> temp = listRecipes();
		for (String recipe : listRecipes()) {
			if (hasIngredient(recipe, iname) == false)
				temp.remove(recipe);
		}
		return temp;
	}

	// FILTER
	/**
	 * This method will filter recipes based on multiple parameters.
	 * 
	 * @param mustHave
	 *            List of those ingredients which must be present in the desired
	 *            recipe.
	 * @param avoidAllergens
	 *            List of those allergens which must not be present in the
	 *            desired recipe.
	 * @param enough
	 *            {@code true} if the all the required ingredients must be
	 *            available in storage, {@code false} otherwise.
	 * @param sort
	 *            {@code true} if the recipes should be sorted alphabetically,
	 *            {@code false} if they should be sorted by expiration date.
	 * @return A list with desired recipes, or {@code null} if there are none.
	 * @throws IllegalArgumentException
	 *             if {@code mustHave} or {@code avoidAllergens} is
	 *             {@code null}.
	 */
	public static String[] filterRecipes(List<String> mustHave, List<String> avoidAllergens, boolean enough,
			boolean sort) {
		if (mustHave == null || avoidAllergens == null)
			throw new IllegalArgumentException();
		List<String> temp = new ArrayList<>();
		temp.addAll(listRecipes());
		for (String rname : listRecipes()) {
			if (mustHave.isEmpty() == false) {
				if (getIngredients(rname).keySet().containsAll(mustHave) == false)
					temp.remove(rname);
			}
			if (avoidAllergens.isEmpty() == false) {
				for (String allergen : avoidAllergens) {
					if (getAllergens(rname).contains(allergen))
						temp.remove(rname);
				}
			}
			if (enough == true) {
				for (String iname : getIngredients(rname).keySet()) {
					if (Storage.getAmount(iname) < getAmount(rname, iname))
						temp.remove(rname);
				}
			}
		}
		if (sort == true)
			Collections.sort(temp);
		else
			Collections.sort(temp, new Expiry());
		return temp.isEmpty() ? null : temp.toArray(new String[0]);
	}
}// end class Book
