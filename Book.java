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
	
	//SEARCH
	public static Set<String> search(boolean enough, ArrayList<String> allergens, ArrayList<String> ingredients){
		Set<String> result = listRecipes();
		Set<String> rejected = new HashSet<String>();
		if (enough){
			for (String name : result){
				if (Book.getRecipe(name).getEnough() == false) rejected.add(name);
			}
		}
		if (!(allergens.isEmpty())){
			for (String name : result){
				for (String allergen : allergens){
					if (Book.getRecipe(name).listAllergens().contains(allergen)) {
						rejected.add(name);
						break;
					}
				}
			}
		}
		if (!(ingredients.isEmpty())){
			for (String name : result){
				for (String ingredient : ingredients){
					if (!(Book.getRecipe(name).listIngredients().contains(ingredient))) {
						rejected.add(name);
						break;
					}
				}
			}
		}
		for (String name : rejected){
			result.remove(name); 
		}
		return result;
	}
}
