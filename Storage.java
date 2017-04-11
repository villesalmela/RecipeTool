package recipeTool;

import java.util.*;

/**
 * This class contains all the ingredients. It stores the
 * following information:<br>
 * -name<br>
 * -amount<br>
 * -unit<br>
 * -allergens<br>
 * -expiration date.
 * 
 * @author Ville Salmela
 *
 */
class Storage { // package-private

	// PROPERTIES
	/**
	 * This map holds all the ingredients.
	 * Key is ingredient name, and values are ingredient properties, stored in Object array.<br>
	 * The first item in object array must be the amount.<br>
	 * The second item in object array must be the unit.<br>
	 * The third item in object array must be allergens.<br>
	 * The fourth item in object array must be expiration date.<p>
	 * This field should be accessed using setter and getter methods provided in this class.
	 */
	final private static Map<String, Object[]> map = new HashMap<>();

	/**
	 * This constructor is private, thus preventing the instantiation of this
	 * class.
	 */
	private Storage() {
	}

	// CREATE & DELETE
	/**
	 * This method will insert one ingredient into the storage with the
	 * specified attributes. If the ingredient already exist, new values will be
	 * used to replace existing values.
	 * 
	 * @param name
	 *            The name of the ingredient. Cannot be {@code null} or empty.
	 * @param amount
	 *            The amount of the ingredient in storage. Cannot be negative.
	 * @param unit
	 *            The unit of the ingredient. Cannot be {@code null}. See
	 *            {@link recipeTool.Unit}
	 * @param allergen
	 *            The allergens of the ingredient, or {@code null} if there are none. The values will be sorted
	 *            alphabetically.
	 * @param expiration
	 *            The expiration date of the ingredient, or {@code null} if there is none.
	 * @throws InvalidDataException if any argument is not accepted.
	 */
	public static void setIngredient(String name, double amount, Unit unit, TreeSet<String> allergen, Date expiration) throws InvalidDataException {
		// Validate name
		if (name == null || name.equals("")) throw new InvalidDataException("Ingredient name cannot be empty.");
		
		// Validate amount
		if (amount < 0) throw new InvalidDataException("Ingredient amount cannot be negative.");
		
		// Validate unit
		if (unit == null) throw new InvalidDataException("Unit is not recognized by this application.");
		
		// Validate allergens
		if (allergen != null) {
			for (String temp : allergen) {
				if (temp == null || temp.equals(""))
					allergen.iterator().remove();
			}
			if (allergen.isEmpty()) allergen = null;
		}
		// Set properties
		map.put(name, new Object[] { unit, allergen, expiration, amount });
	}
	
	/**
	 * This method will delete all ingredients in storage.
	 */
	public static void clear(){
		map.clear();
	}

	/**
	 * This method will remove one ingredient from storage.
	 * 
	 * @param name
	 *            The name of the ingredient to be removed. Cannot be
	 *            {@code null} or empty.
	 * 
	 * @throws NoSuchElementException
	 *             if the ingredient with attribute {@code name} does not exist
	 *             in storage.
	 * @throws IllegalArgumentException
	 *             if {@code name} is {@code null}/empty
	 */
	public static void deleteIngredient(String name) {
		if (name == null || name.equals(""))
			throw new IllegalArgumentException();
		if (hasIngredient(name) == false)
			throw new NoSuchElementException();
		map.remove(name);
	}

	// GETTERS
	/**
	 * This method will get the unit of specified ingredient.
	 * 
	 * @param name
	 *            The name of the ingredient. Cannot be {@code null} or empty
	 * @return Unit enumerable, never {@code null}. See {@link recipeTool.Unit}
	 * @throws NoSuchElementException
	 *             if the ingredient with attribute {@code name} does not exist
	 *             in storage.
	 * @throws IllegalArgumentException
	 *             if {@code name} is {@code null}/empty
	 */
	public static Unit getUnit(String name) {
		if (name == null || name.equals(""))
			throw new IllegalArgumentException();
		if (hasIngredient(name) == false)
			throw new NoSuchElementException();
		return (Unit) map.get(name)[0];
	}

	/**
	 * This method will get the allergens of specified ingredient.
	 * 
	 * @param name
	 *            The name of the ingredient. Cannot be {@code null} or empty
	 * @return Alphabetically sorted list of allergens, or {@code null} if there are none.
	 * @throws NoSuchElementException
	 *             if the ingredient with attribute {@code name} does not exist
	 *             in storage.
	 * @throws IllegalArgumentException
	 *             if {@code name} is {@code null}/empty
	 */
	@SuppressWarnings("unchecked")
	public static TreeSet<String> getAllergens(String name) {
		if (name == null || name.equals(""))
			throw new IllegalArgumentException();
		if (hasIngredient(name) == false)
			throw new NoSuchElementException();
		return (TreeSet<String>) map.get(name)[1];
	}

	/**
	 * This method will get the expiration date of specified ingredient.
	 * 
	 * @param name
	 *            The name of the ingredient. Cannot be {@code null} or empty
	 * @return The expiration date<br>
	 * 		or {@code null} if there is no date.
	 * @throws NoSuchElementException
	 *             if the ingredient with attribute {@code name} does not exist
	 *             in storage.
	 * @throws IllegalArgumentException
	 *             if {@code name} is {@code null}/empty
	 */
	public static Date getExpiration(String name) {
		if (name == null || name.equals(""))
			throw new IllegalArgumentException();
		if (hasIngredient(name) == false)
			throw new NoSuchElementException();
		return (Date) map.get(name)[2];
	}

	/**
	 * This method will get the amount of specified ingredient.
	 * 
	 * @param name
	 *            The name of the ingredient. Cannot be {@code null} or empty
	 * @return The amount, never {@code null}.
	 * @throws NoSuchElementException
	 *             if the ingredient with attribute {@code name} does not exist
	 *             in storage.
	 * @throws IllegalArgumentException
	 *             if {@code name} is {@code null}/empty
	 */
	public static double getAmount(String name) {
		if (name == null || name.equals(""))
			throw new IllegalArgumentException();
		if (hasIngredient(name) == false)
			throw new NoSuchElementException();
		return (double) map.get(name)[3];
	}

	// HAS
	/**
	 * This method will check, if the specified ingredient is in the storage.
	 * Even ingredients with zero amount are counted.
	 * 
	 * @param name
	 *            The name of the ingredient.
	 * @return {@code true} if the ingredient is in the storage.<br>
	 * 		{@code false} if the ingredient is NOT in the storage.
	 * @throws IllegalArgumentException
	 *             if {@code name} is {@code null}/empty
	 */
	public static boolean hasIngredient(String name) {
		if (name == null || name.equals(""))
			throw new IllegalArgumentException();
		return map.keySet().contains(name);
	}

	// LISTERS
	/**
	 * This method will list all ingredients in storage, by name.
	 * 
	 * @return A list containing all ingredients in storage, sorted
	 *         alphabetically.
	 */
	public static TreeSet<String> listIngredients() {
		return new TreeSet<>(map.keySet());
	}

	/**
	 * This method will list all allergens from all ingredients in storage.
	 * 
	 * @return A list containing all allergens from all ingredients, sorted
	 *         alphabetically.
	 */
	public static TreeSet<String> listAllergens() {
		TreeSet<String> temp = new TreeSet<>();
		for (String i : listIngredients()) {
			if (getAllergens(i) != null)
				temp.addAll(getAllergens(i));
		}
		return temp;
	}

}
