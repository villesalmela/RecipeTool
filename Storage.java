package recipeTool;
import java.util.*;

public class Storage {
	
	//PROPERTIES
	private static HashMap<String, Object[]> ingredients = new HashMap<String, Object[]>();
	
	//SETTERS
	public static void setIngredient(Ingredient ingredient, double amount){
		Object[] temp = new Object[]{ingredient, amount};
		ingredients.put(ingredient.getName(), temp);
	}
	
	//GETTERS
	public static Ingredient getIngredient(String name){
		return (Ingredient) ingredients.get(name)[0];
	}
	public static double getAmount(String name){
		return (double) ingredients.get(name)[1];
	}
	public static Set<String> listIngredients(){
		return ingredients.keySet();
	}
	
	//DELETE
	public static void deleteIngredient(String name){
		ingredients.remove(name);
	}
	
	//PRINT
	public static void print(){
		System.out.println("STORAGE INFO: ");
		System.out.println("Ingredients in storage: " + listIngredients());
		System.out.println("");
		
	}
	

}
