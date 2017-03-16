package recipeTool;
import java.util.*;

public class Book {
	
	//PROPERTIES
	private static HashMap<String, Recipe> recipes = new HashMap<String, Recipe>();

	//SETTERS
	public static void setRecipe(Recipe recipe){
		recipes.put(recipe.getName(), recipe);
	}
	
	//GETTERS
	public static Recipe getRecipe(String name){
		return recipes.get(name);
	}
	public static Set<String> listRecipes(){
		return recipes.keySet();
	}
	
	//DELETE
	public static void deleteRecipe(String name){
		recipes.remove(name);
	}
	
	//PRINT
	public static void print(){
		System.out.println("BOOK INFO:");
		System.out.println("Recipes in book: " + listRecipes());
		System.out.println("");
	}
}
