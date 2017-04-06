package recipeTool;
import java.util.*;

/**
 * This entirely static class contains all the ingredients. It stores the following information:<br>
 * -name<br>-amount<br>-unit<br>-allergens<br>-expiration date.
 * @author Ville Salmela
 *
 */
class Storage { //package-private
	
//PROPERTIES
	final private static QuadTable<String, Unit, TreeSet<String>, Date, Double> quadTable = new QuadTable<>();
	/*
	 * FIRST = Unit
	 * SECOND = Allergens
	 * THIRD = Expiration
	 * FOURTH = Amount
	 */
	
	private Storage(){}
	
//CREATE & DELETE
	/**
	 * This method will insert one ingredient into the storage with the specified attributes.
	 * If the ingredient already exist, new values will be used to replace existing values.
	 * @param name The name of the ingredient. Cannot be {@code null} or empty.
	 * @param amount The amount of the ingredient in storage. Cannot be negative.
	 * @param unit The unit of the ingredient. Cannot be {@code null}. See {@link recipeTool.Unit}
	 * @param allergen The allergens of the ingredient. The values will be sorted alphabetically.
	 * @param expiration The expiration date of the ingredient.
	 * 
	 * @return Nothing.
	 * @throws IllegalArgumentException if {@code name} is {@code null}/empty, or if {@code amount} is negative.
	 */
	public static void setIngredient(String name, double amount, Unit unit, TreeSet<String> allergen, Date expiration){ //build new ingredient from scratch
		if (name == null || name.equals("") || amount < 0 || unit == null) throw new IllegalArgumentException();
		quadTable.set(name, unit, allergen, expiration, amount);
	}
	
	/**
	 * This method will remove one ingredient from storage.
	 * @param name The name of the ingredient to be removed. Cannot be {@code null} or empty.
	 * @return Nothing.
	 * @throws NoSuchElementException if the ingredient with attribute {@code name} does not exist in storage.
	 * @throws IllegalArgumentException if {@code name} is {@code null}/empty
	 */
	public static void deleteIngredient(String name){						//delete all keys and values for one ingredient
		if (name == null || name.equals("")) throw new IllegalArgumentException();
		if (hasIngredient(name) == false) throw new NoSuchElementException();
		quadTable.deleteKey(name);
	}
	
//GETTERS
	/**
	 * This method will get the unit of specified ingredient.
	 * @param name The name of the ingredient. Cannot be {@code null} or empty
	 * @return Unit enumerable. See {@link recipeTool.Unit}
	 * @throws NoSuchElementException if the ingredient with attribute {@code name} does not exist in storage.
	 * @throws IllegalArgumentException if {@code name} is {@code null}/empty
	 */
	public static Unit getUnit(String name){								//get the unit of specified ingredient
		if (name == null || name.equals("")) throw new IllegalArgumentException();
		if (hasIngredient(name) == false) throw new NoSuchElementException();
		return quadTable.getFirst(name);
	}
	
	/**
	 * This method will get the allergens of specified ingredient.
	 * @param name The name of the ingredient. Cannot be {@code null} or empty
	 * @return Alphabetically sorted list of allergens
	 * @throws NoSuchElementException if the ingredient with attribute {@code name} does not exist in storage.
	 * @throws IllegalArgumentException if {@code name} is {@code null}/empty
	 */
	public static TreeSet<String> getAllergens(String name){					//get list of all allergens for specified ingredient, in A-Z order
		if (name == null || name.equals("")) throw new IllegalArgumentException();
		if (hasIngredient(name) == false) throw new NoSuchElementException();
		return quadTable.getSecond(name);
	}
	
	/**
	 * This method will get the expiration date of specified ingredient.
	 * @param name The name of the ingredient. Cannot be {@code null} or empty
	 * @return The expiration date<br>or {@code null} if there is no date.
	 * @throws NoSuchElementException if the ingredient with attribute {@code name} does not exist in storage.
	 * @throws IllegalArgumentException if {@code name} is {@code null}/empty
	 */
	public static Date getExpiration(String name){							//get the expiration date of specified ingredient
		if (name == null || name.equals("")) throw new IllegalArgumentException();
		if (hasIngredient(name) == false) throw new NoSuchElementException();
		return quadTable.getThird(name) == null ? null : quadTable.getThird(name);
	}
	
	/**
	 * This method will get the amount of specified ingredient.
	 * @param name The name of the ingredient. Cannot be {@code null} or empty
	 * @return The amount.
	 * @throws NoSuchElementException if the ingredient with attribute {@code name} does not exist in storage.
	 * @throws IllegalArgumentException if {@code name} is {@code null}/empty
	 */
	public static double getAmount(String name){							//get amount (in storage) of specified ingredient
		if (name == null || name.equals("")) throw new IllegalArgumentException();
		if (hasIngredient(name) == false) throw new NoSuchElementException();
		return quadTable.getFourth(name);
	}
	
	
//HAS
	/**
	 * This method will check, if the specified ingredient is in the storage. Even ingredients with zero amount are counted.
	 * @param name The name of the ingredient.
	 * @return {@code true} if the ingredient is in the storage.<br>{@code false} if the ingredient is NOT in the storage.
	 * @throws IllegalArgumentException if {@code name} is {@code null}/empty
	 */
	public static boolean hasIngredient(String name){						//true, if specified ingredient is in storage
		if (name == null || name.equals("")) throw new IllegalArgumentException();
		return quadTable.getKeys().contains(name);
	}
	
//LISTERS
	/**
	 * This method will list all ingredients in storage, by name.
	 * @return A list containing all ingredients in storage, sorted alphabetically.
	 */
	public static TreeSet<String> listIngredients(){							//get list of all ingredients in storage, in A-Z order
		return quadTable.getKeys();
	}
	
	
	/**
	 * This method will list all allergens from all ingredients in storage.
	 * @return A list containing all allergens from all ingredients, sorted alphabetically.
	 */
	public static TreeSet<String> listAllergens(){
		TreeSet<String> temp = new TreeSet<>();
		for (String i: listIngredients()){
			if(quadTable.getSecond(i) != null) temp.addAll(quadTable.getSecond(i));
		}
		return temp;
	}
		
}
