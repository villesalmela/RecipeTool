package recipeTool;
import java.util.*;

public class Storage {
	
	//PROPERTIES
	private HashMap<String, Object[]> ingredients;
	
	//SETTERS
	public void setIngredient(Ingredient ingredient, double amount){
		Object[] temp = new Object[]{ingredient, amount};
		ingredients.put(ingredient.getName(), temp);
	}
	
	//GETTERS
	public Ingredient getIngredient(String name){
		return (Ingredient) ingredients.get(name)[0];
	}
	public double getAmount(String name){
		return (double) ingredients.get(name)[1];
	}
	public Set<String> listIngredients(){
		return ingredients.keySet();
	}
	
	//DELETE
	public void deleteIngredient(String name){}
	

}
