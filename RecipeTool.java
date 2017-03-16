package recipeTool;

import java.text.DateFormat;
import java.text.ParseException;
import java.util.*;
import java.util.Map.Entry;

public class RecipeTool {

	public static void main(String[] args) throws ParseException {

	//TEST VALUES
		
		//Allergens
		
		ArrayList<String> a1 = new ArrayList<String>();
		a1.add("allergen1a");
		a1.add("allergen1b");
		
		ArrayList<String> a2 = new ArrayList<String>();
		a2.add("allergen2a");
		a2.add("allergen2b");
		
		ArrayList<String> a3 = new ArrayList<String>();
		a3.add("allergen3a");
		a3.add("allergen3b");
		

		
		//Expirations
		
		Date e1 = DateFormat.getDateInstance().parse("1.1.2017");
		Date e2 = DateFormat.getDateInstance().parse("2.2.2017");
		Date e3 = DateFormat.getDateInstance().parse("3.3.2017");
		
		//Recipes
		
		HashMap<String, Double> h1 = new HashMap<String, Double>();
		h1.put("ingredient1", 10.0);
		h1.put("ingredient2", 15.0);
		
		HashMap<String, Double> h2 = new HashMap<String, Double>();
		h2.put("ingredient2", 20.0);
		h2.put("ingredient3", 25.0);
		
		HashMap<String, Double> h3 = new HashMap<String, Double>();
		h3.put("ingredient3", 30.0);
		h3.put("ingredient1", 35.0);
		

		
	
		
	//END TEST VALUES
		

	createIngredient("ingredient1", Unit.KG, a1, e1, 100.0);
	createIngredient("ingredient2", Unit.L, a2, e2, 200.0);
	createIngredient("ingredient3", Unit.PCS, a3, e3, 300.0);
	
	createRecipe("recipe1", "ohje1", h1);
	createRecipe("recipe2", "ohje2", h2);
	createRecipe("recipe3", "ohje3", h3);
	
	Storage.print();
	Book.print();
	
	Book.getRecipe("recipe1").print();
	Book.getRecipe("recipe2").print();
	Book.getRecipe("recipe3").print();
	
	Storage.getIngredient("ingredient1").print();
	Storage.getIngredient("ingredient2").print();
	Storage.getIngredient("ingredient3").print();
	
	//Search Test Values
	ArrayList<String> a5 = new ArrayList<String>();
	a5.add("ingredient1");
	ArrayList<String> a4 = new ArrayList<String>();
	a4.add("allergen2a");
	//a4.add("allergen3b");
	
	
	System.out.println(Book.search(true, a4, a5));
	
	}
	public static void createIngredient(String name, Unit unit, ArrayList<String> allergens, Date expiration, double amount) {
		Storage.setIngredient(new Ingredient(name, unit, allergens, expiration), amount);
	}
	
	public static void createRecipe(String name, String instruction, HashMap<String, Double> ingredients){
		Book.setRecipe(new Recipe(name, instruction));
		for (Entry<String, Double> ingredient : ingredients.entrySet()){
			Book.getRecipe(name).setIngredient(ingredient.getKey(), ingredient.getValue());
		}
	}

}
