package recipeTool;
import java.util.*;

public class Book {
	
	//PROPERTIES
	private HashMap<String, Recipe> recipes;

	//SETTERS
	public void setRecipe(Recipe recipe){
		recipes.put(recipe.getName(), recipe);
	}
	
	//GETTERS
	public Recipe getRecipe(String name){
		return recipes.get(name);
	}
	public Set<String> listRecipes(){
		return recipes.keySet();
	}
	
	//DELETE
	public void deleteRecipe(String name){
		recipes.remove(name);
	}
}
