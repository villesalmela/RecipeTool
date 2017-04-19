package recipeTool;

import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.NoSuchElementException;
import java.util.TreeSet;
import java.util.concurrent.RejectedExecutionException;

import utilities.InvalidDataException;
import utilities.Unit;

/**
 * This class contains all the ingredients. It stores the
 * following information:<br>
 * -name<br>
 * -amount<br>
 * -unit<br>
 * -allergens<br>
 * -expiration date.
 * <p>
 * The getter methods only return immutable objects and copies of mutable objects,
 * so no changes can be made without proper validation.
 * 
 * @author Ville Salmela
 *
 */
final class Storage implements java.io.Serializable { 
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 5984012718953910798L;

	/**
	 * This map holds all the ingredients.<br>
	 * Key: String ingredient name<br>
	 * Value: Object array [unit, allergens, expiration, amount]
	 */
	final private Map<String, Object[]> map = new HashMap<>();

	/**
	 * Constructs a new Storage, with no ingredients.
	 */
	Storage() {}

	/**
	 * This method will insert one ingredient into the storage with the
	 * specified attributes. If the ingredient already exist,
	 * new values will replace existing values.
	 * 
	 * @param name
	 *            The name of the ingredient. Cannot be {@code null} or empty.
	 * @param amount
	 *            The amount of the ingredient in storage. Cannot be negative.
	 * @param unit
	 *            The unit of the ingredient. Cannot be {@code null}. See
	 *            {@link utilities.Unit}
	 * @param allergen
	 *            The allergens of the ingredient, or {@code null} if there are none. The values will be sorted
	 *            alphabetically.
	 * @param expiration
	 *            The expiration date of the ingredient, or {@code null} if there is none.
	 * @throws InvalidDataException if any argument is not accepted.
	 */
	void setIngredient(String name, double amount, Unit unit, TreeSet<String> allergen, Date expiration) throws InvalidDataException {
		
		// Validate name
		if (name == null || name.equals("")) throw new InvalidDataException("Ingredient name cannot be empty.");
		
		// Validate amount
		if (amount < 0) throw new InvalidDataException("Ingredient amount cannot be negative.");
		
		// Validate unit
		if (unit == null) throw new InvalidDataException("Unit is not recognized by this application.");
		
		// Validate allergens
		if (allergen != null) {
			Iterator<String> iter = allergen.iterator();
			while (iter.hasNext()){
				String temp = iter.next();
				if (temp == null || temp.equals(""))
					iter.remove();
			}
			if (allergen.isEmpty()) allergen = null;
		}
		// Set properties
		map.put(name, new Object[] { unit, allergen, expiration, amount });
	}// end method setIngredient
	
	/**
	 * This method will delete all ingredients in storage.
	 */
	void clear(){
		map.clear();
	}// end method clear
	
	/**
	 * This method will reduce the amount of one ingredient.
	 * @param iname Ingredient name.
	 * @param amount The amount to be reduced.
	 * @throws NoSuchElementException
	 *             if the ingredient {@code iname} does not exist
	 *             in storage.
	 * @throws IllegalArgumentException
	 *             if {@code iname} is {@code null}/empty
	 * @throws RejectedExecutionException if reducing would lead to negative amount.
	 */
	void reduce(String iname, double amount){
		
		// Validate name
		if (iname == null || iname.equals(""))
			throw new IllegalArgumentException();
		
		// Check that the ingredient exists
		if (hasIngredient(iname) == false)
			throw new NoSuchElementException();
		
		// Retrieve the ingredient data from map
		Object[] temp = map.get(iname);
		
		// Check that there is not going to be negative values
		if (amount > (double)temp[3]) throw new RejectedExecutionException();
		
		// Do the reducing
		temp[3] = (double)temp[3]-amount;
		
		// Put the ingredient data back to map
		map.put(iname, temp);
	}// end method reduce

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
	void deleteIngredient(String name) {
		
		// Validate name
		if (name == null || name.equals(""))
			throw new IllegalArgumentException();
		
		// Check that the ingredient exists
		if (hasIngredient(name) == false)
			throw new NoSuchElementException();
		
		// Remove the ingredient
		map.remove(name);
	}// end method deleteIngredient

	/**
	 * This method will get the unit of specified ingredient.
	 * 
	 * @param name
	 *            The name of the ingredient. Cannot be {@code null} or empty
	 * @return Unit enumerable, never {@code null}. See {@link utilities.Unit}
	 * @throws NoSuchElementException
	 *             if the ingredient with attribute {@code name} does not exist
	 *             in storage.
	 * @throws IllegalArgumentException
	 *             if {@code name} is {@code null}/empty
	 */
	Unit getUnit(String name) {
		
		// Validate name
		if (name == null || name.equals(""))
			throw new IllegalArgumentException();
		
		// Check that the ingredient exists
		if (hasIngredient(name) == false)
			throw new NoSuchElementException();
		
		// Return the unit
		return (Unit) map.get(name)[0];
	}// end method getUnit

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
	TreeSet<String> getAllergens(String name) {
		
		// Validate name
		if (name == null || name.equals(""))
			throw new IllegalArgumentException();
		
		// Check that the ingredient exists
		if (hasIngredient(name) == false)
			throw new NoSuchElementException();
		
		
		// If there are no allergens, return null
		if (map.get(name)[1] == null) return null;
		
		// Else return copy of the allergens
		return new TreeSet<>(((TreeSet<String>) map.get(name)[1]));
	}// end method getAllergens

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
	Date getExpiration(String name) {
		
		// Validate names
		if (name == null || name.equals(""))
			throw new IllegalArgumentException();
		
		// Check that the ingredient exists
		if (hasIngredient(name) == false)
			throw new NoSuchElementException();
		
		// If there is no expiration date, return null
		if (map.get(name)[2] == null) return null;
		
		// Else return copy of the expiration date
		return new Date(((Date) map.get(name)[2]).getTime());
	}// end method getExpiration

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
	double getAmount(String name) {
		
		// Validate name
		if (name == null || name.equals(""))
			throw new IllegalArgumentException();
		
		// Check that the ingredient exists
		if (hasIngredient(name) == false)
			throw new NoSuchElementException();
		
		// Return the amount
		return (double) map.get(name)[3];
	}// end method getAmount

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
	boolean hasIngredient(String name) {
		
		// Validate name
		if (name == null || name.equals(""))
			throw new IllegalArgumentException();
		
		// Return result
		return map.keySet().contains(name);
	}// end method hasIngredient

	// LISTERS
	/**
	 * This method will list all ingredients in storage, by name.
	 * 
	 * @return A list containing all ingredients in storage, sorted
	 *         alphabetically. Never {@code null}.
	 */
	TreeSet<String> listIngredients() {
		
		// Return copy of the set
		return new TreeSet<>(map.keySet());
	}// end method listIngredients

	/**
	 * This method will list all allergens from all ingredients in storage.
	 * 
	 * @return A list containing all allergens from all ingredients, sorted
	 *         alphabetically. Never {@code null}.
	 */
	TreeSet<String> listAllergens() {
		
		// Prepare the set, which shall contain all allergens from every ingredient
		TreeSet<String> temp = new TreeSet<>();
		
		// For every ingredient
		for (String i : listIngredients()) {
			
			// If there are any allergens, add them to set
			if (getAllergens(i) != null)
				temp.addAll(getAllergens(i));
		}
		
		// Return the set
		return temp;
	}// end method listAllergens
}// end class Storage
