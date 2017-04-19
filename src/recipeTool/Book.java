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
 * This class will retrieve additional information from
 * {@link recipeTool.Storage}:<br>
 * -summary of ingredients allergens<br>
 * -the soonest expiration date and related ingredient<br>
 * -does the Storage has enough ingredients to prepare the recipe at least once?
 * <p>
 * The getter methods only return immutable objects and copies of mutable objects,
 * so no changes can be made without proper validation.
 * 
 * @author Ville Salmela
 *
 */
final class Book implements java.io.Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -5708943979738196043L;

	/**
	 * This Map holds all the recipes.<br>
	 * Key: String recipe name<br>
	 * Value: Object array [instructions, ingredients map]
	 */
	final private Map<String, Object[]> map = new HashMap<>();

	/**
	 * Constructs a new book, with no recipes.
	 */
	Book() {}

	/**
	 * This is a private method, which returns a map containing ingredients and
	 * their amounts, for one recipe.<br>
	 * The method just retrieves the second object from object array, which is
	 * mapped to a recipe name in {@link recipeTool.Book#map}
	 * 
	 * @param key
	 *            The recipe name.
	 * @return A Map which contains the ingredient names and their amounts for
	 *         one recipe, in the same order they were inputed.
	 * @throws IllegalArgumentException
	 *             if key is {@code null} or empty.
	 * @throws NoSuchElementException
	 *             if there is no such recipe in the book.
	 */
	@SuppressWarnings("unchecked")
	private LinkedHashMap<String, Double> getIngredients(String key) {

		// Validate key
		if (key == null || key.equals(""))
			throw new IllegalArgumentException();

		// Check that the recipe exists
		if (hasRecipe(key) == false)
			throw new NoSuchElementException();

		// Return the ingredients map
		return ((LinkedHashMap<String, Double>) map.get(key)[1]);
	}// end method getIngredients

	/**
	 * This method will insert one recipe into the book with the specified
	 * attributes. If the recipe already exists, new values will replace
	 * existing values.
	 * 
	 * @param name
	 *            The name of the recipe. Cannot be {@code null} or empty.
	 * @param instruction
	 *            The instruction for the recipe, or {@code null} if there is
	 *            none.
	 * @param ingredient
	 *            A map of ingredients, containing ingredient names and
	 *            amounts.<br>
	 *            Ingredient name cannot be {@code null} or empty and amount
	 *            cannot be {@code null} or negative.<br>
	 *            There has to be at least one pair in the map.
	 * @throws InvalidDataException
	 *             if any parameter is not accepted.
	 */
	void setRecipe(String name, String instruction, LinkedHashMap<String, Double> ingredient)
			throws InvalidDataException {

		// Validate recipe name
		if (name == null || name.equals(""))
			throw new InvalidDataException("Recipe name cannot be empty.");

		// Must have at least one entry
		if (ingredient == null || ingredient.isEmpty())
			throw new InvalidDataException("Recipe must have at least one ingredient.");

		for (String iname : ingredient.keySet()) {

			// Validate ingredient names
			if (iname == null || iname.equals(""))
				throw new InvalidDataException("Ingredient name cannot be empty.");

			// Validate ingredient presence in Storage
			if (RecipeTool.getStorage().hasIngredient(iname) == false)
				throw new InvalidDataException("Recipe cannot have an ingredient, which is not present in Storage.");

			// Validate amounts
			if (ingredient.get(iname) == null || ingredient.get(iname) < 0)
				throw new InvalidDataException("Ingredient amount cannot be null or negative.");
		}

		// Put recipe to map
		map.put(name, new Object[] { instruction, ingredient });
	}// end method setRecipe

	/**
	 * This method will delete all recipes in the Book.
	 */
	void clear() {
		map.clear();
	}// end method clear

	/**
	 * This method will rename one ingredient in all recipes.
	 * 
	 * @param original
	 *            The old name.
	 * @param replacing
	 *            The new name.
	 * @throws IllegalArgumentException
	 *             if either name is {@code null} or empty.
	 */
	void renameIngredient(String original, String replacing) {

		// Validate names
		if (original == null || replacing == null || original.equals("") || replacing.equals(""))
			throw new IllegalArgumentException();

		// For each recipe which has the ingredient named original:
		// replace it with ingredient named replacing
		for (String rname : whoHas(original)) {
			Double amount = getIngredients(rname).remove(original);
			getIngredients(rname).put(replacing, amount);
		}
	}// end method rename Ingredient

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
	void deleteRecipe(String name) {

		// Validate name
		if (name == null || name.equals(""))
			throw new IllegalArgumentException();

		// Check if the recipe exists
		if (hasRecipe(name) == false)
			throw new NoSuchElementException();

		// Remove the recipe
		map.remove(name);
	}// end method deleteRecipe

	/**
	 * This method will remove one ingredient from all recipes. If any recipe
	 * becomes empty (has no ingredients), the recipe will be removed.
	 * 
	 * @param iname
	 *            The ingredient name, which should be removed.
	 * @throws IllegalArgumentException
	 *             if name is empty or {@code null}.
	 */
	void deleteAllIngredients(String iname) {

		// Validate name
		if (iname == null || iname.equals(""))
			throw new IllegalArgumentException();

		// Prepare a set, which shall contain those recipes, that become empty
		Set<String> toBeDeleted = new HashSet<>();

		// For every recipe, delete one ingredient
		for (String rname : map.keySet()) {
			getIngredients(rname).remove(iname);

			// If recipe becomes empty, add it to set
			if (listIngredients(rname).isEmpty())
				toBeDeleted.add(rname);
		}

		// For every recipe in set, delete it
		for (String recipe : toBeDeleted) {
			deleteRecipe(recipe);
		}
	}// end method deleteAllIngredients

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
	 * @throws NoSuchElementException
	 *             if the recipe named {@code rname} does not exist in
	 *             book.
	 */
	String getInsturction(String rname) {
		
		// Validate name
		if (rname == null || rname.equals(""))
			throw new IllegalArgumentException();
		
		// Check that the recipe exists
		if (hasRecipe(rname) == false)
			throw new NoSuchElementException();
		
		// Return the instruction
		return (String) map.get(rname)[0];
	}// end method getInstruction

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
	double getAmount(String rname, String iname) {
		
		// Validate names
		if (rname == null || rname.equals("") || iname == null || iname.equals(""))
			throw new IllegalArgumentException();
		
		// Check that the recipe exists and that it has the ingredient
		if (hasRecipe(rname) == false || hasIngredient(rname, iname) == false)
			throw new NoSuchElementException();
		
		// Return the amount
		return getIngredients(rname).get(iname);
	}// end method getAmount

	/**
	 * This method will list all allergens that the specified recipe contains.
	 * 
	 * @param rname
	 *            The recipe name.
	 * @return A list of allergens, or {@code null} if there are none.
	 * @throws NoSuchElementException
	 *             if the recipe named {@code rname} does not exist in
	 *             book.
	 * @throws IllegalArgumentException
	 *             if {@code rname} is {@code null}/empty
	 */
	TreeSet<String> getAllergens(String rname) {
		
		// Validate name
		if (rname == null || rname.equals(""))
			throw new IllegalArgumentException();
		
		// Check that the recipe exists
		if (hasRecipe(rname) == false)
			throw new NoSuchElementException();
		
		// Prepare an A-Z sorted set, which shall contain all the allergens
		TreeSet<String> temp = new TreeSet<>();
		
		// For every ingredient in one recipe
		for (String iname : getIngredients(rname).keySet()) {
			
			// If there are any allergens
			if (RecipeTool.getStorage().getAllergens(iname) != null)
				
				//Add them to set
				temp.addAll(RecipeTool.getStorage().getAllergens(iname));
		}
		
		// If the allergens set is empty, return null, otherwise return the set.
		return temp.isEmpty() ? null : temp;
	}// end method getAllergens

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
	Object[] getExpiration(String rname) {
		
		// Validate name
		if (rname == null || rname.equals(""))
			throw new IllegalArgumentException();
		
		// Check that the recipe exists
		if (hasRecipe(rname) == false)
			throw new NoSuchElementException();
		
		// Prepare the variables
		String temp = null;
		Date expiration = null;
		
		// For every ingredient in this recipe
		for (String iname : getIngredients(rname).keySet()) {

			// Ignore, if it does not have expiration date
			if (RecipeTool.getStorage().getExpiration(iname) == null)
				continue;
			
			// For first occurrence only, select whatever comes
			else if (expiration == null) {
				expiration = RecipeTool.getStorage().getExpiration(iname);
				temp = iname;
			} 
			
			// Select the soonest expiration date and its corresponding ingredient name
			else if (expiration.after(RecipeTool.getStorage().getExpiration(iname)) == true) {
				expiration = RecipeTool.getStorage().getExpiration(iname);
				temp = iname;
			}
		}
		// Put values into array
		Object[] pair = new Object[] { expiration, temp };
		
		//Return the array
		return pair;
	}// end method getExpiration

	/**
	 * This method will check, if there is enough ingredients in
	 * Storage, to prepare the recipe at least once.
	 * 
	 * @param rname
	 *            Recipe name
	 * @return {@code true} if there is enough ingredients in
	 *         Storage,<br>
	 *         {@code false} if there is NOT enough ingredients in
	 *         Storage.
	 * @throws NoSuchElementException
	 *             if the recipe does not exist in the book.
	 * @throws IllegalArgumentException
	 *             if {@code rname} is {@code null}/empty.
	 */
	boolean isEnough(String rname) {
		
		// Validate name
		if (rname == null || rname.equals(""))
			throw new IllegalArgumentException();
		
		// Check if the recipe exists
		if (hasRecipe(rname) == false)
			throw new NoSuchElementException();
		
		// For every ingredient in recipe
		for (String iname : getIngredients(rname).keySet()) {
			
			// If amount in recipe is more than amount in storage, return false
			if (getAmount(rname, iname) > RecipeTool.getStorage().getAmount(iname)){
				return false;
			}
		}
		
		// Otherwise return true
		return true;
	}// end method isEnough


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
	boolean hasRecipe(String name) {
		
		// Validate name
		if (name == null || name.equals(""))
			throw new IllegalArgumentException();
		
		// Return true if recipe is in the book, false otherwise
		return map.keySet().contains(name);
	}// end method hasRecipe

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
	boolean hasIngredient(String rname, String iname) {
		
		// Validate names
		if (rname == null || rname.equals("") || iname == null || iname.equals(""))
			throw new IllegalArgumentException();
		
		// Check that the recipe exists
		if (hasRecipe(rname) == false)
			throw new NoSuchElementException();
		
		// Return true if recipe has ingredient, false otherwise
		return getIngredients(rname).keySet().contains(iname);
	}// end method hasIngredient

	/**
	 * This method will list all recipes in the book.
	 * 
	 * @return A list containing all recipes, sorted alphabetically, never
	 *         {@code null}.
	 */
	TreeSet<String> listRecipes() {
		return new TreeSet<>(map.keySet());
	}//end method listRecipes

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
	LinkedHashSet<String> listIngredients(String rname) {
		
		// Validate name
		if (rname == null || rname.equals(""))
			throw new IllegalArgumentException();
		
		// Check that the recipe exists
		if (hasRecipe(rname) == false)
			throw new NoSuchElementException();
		
		// Return a copy of a set of the ingredients
		return new LinkedHashSet<>(getIngredients(rname).keySet());
	}// end method listIngredients

	/**
	 * This method will check, which recipes contain a specified ingredient.
	 * 
	 * @param iname
	 *            Ingredient name
	 * @return A list of all recipes which contain the ingredient, sorted
	 *         alphabetically, never {@code null}.
	 * @throws IllegalArgumentException
	 *             if {@code iname} is {@code null}/empty.
	 */
	TreeSet<String> whoHas(String iname) {
		
		// Validate name
		if (iname == null || iname.equals(""))
			throw new IllegalArgumentException();
		
		// Prepare an A-Z sorted set, which shall contain those recipes, which have the ingredient
		TreeSet<String> temp = listRecipes();
		
		// For every recipe in book
		for (String recipe : listRecipes()) {
			
			// If recipe does not have the ingredient, remove it from the list
			if (hasIngredient(recipe, iname) == false)
				temp.remove(recipe);
		}
		
		// Return those recipes which still remain on the list
		return temp;
	}// end method whoHas

	/**
	 * This method will filter recipes based on multiple parameters.
	 * 
	 * @param mustHave
	 *            List of those ingredients which must be present in the desired
	 *            recipe. Cannot be {@code null}.
	 * @param avoidAllergens
	 *            List of those allergens which must NOT be present in the
	 *            desired recipe. Cannot be {@code null}.
	 * @param enough
	 *            {@code true} if there must be enough ingredients in storage,
	 *            to prepare the recipe at least once, {@code false} otherwise.
	 * @param sort
	 *            {@code true} if the recipes should be sorted alphabetically,
	 *            {@code false} if they should be sorted by expiration date.
	 * @return A list with desired recipes, or {@code null} if there are none.
	 * @throws IllegalArgumentException
	 *             if {@code mustHave} or {@code avoidAllergens} is
	 *             {@code null}.
	 */
	String[] filterRecipes(List<String> mustHave, List<String> avoidAllergens, boolean enough, boolean sort) {
		
		// Validate parameters
		if (mustHave == null || avoidAllergens == null)
			throw new IllegalArgumentException();
		
		// Prepare an initial list, which contains all recipes
		List<String> temp = new ArrayList<>(listRecipes());
		
		// For each recipe
		for (String rname : listRecipes()) {
			if (mustHave.isEmpty() == false) {
				
				// If it does not contain all ingredients in mustHave, remove it from list 
				if (getIngredients(rname).keySet().containsAll(mustHave) == false)
					temp.remove(rname);
			}
			if (avoidAllergens.isEmpty() == false) {
				
				// If it does have any allergen in avoidAllergens, remove it from list
				for (String allergen : avoidAllergens) {
					if (getAllergens(rname).contains(allergen))
						temp.remove(rname);
				}
			}
			
			// If there has to be enough ingredients is storage, to prepare recipe at least once
			if (enough == true) {
				
				// Remove those which don't have enough
				if(isEnough(rname) == false) temp.remove(rname);
			}
		}
		
		if (sort == true)
			
			// Sort A-Z, according to name
			Collections.sort(temp);
		else
			
			//Sort Sooner-Later-Never, according to expiration date
			Collections.sort(temp, new Expiry());
		return temp.isEmpty() ? null : temp.toArray(new String[0]);
	}// end method filterRecipes
}// end class Book
