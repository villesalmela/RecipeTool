package recipeTool;

import java.util.*;

public class Recipe {
	
	//PROPERTIES
	private String name;
	private String instruction;
	private boolean enough;
	private HashMap<String, Double> ingredients = new HashMap<String, Double>();
	
	//CONSTRUCTOR
	public Recipe(String name, String instruction){
		this.name = name;
		this.instruction = instruction;
	}
	
	//SETTERS
	public void setName(String name){
		this.name = name;
	}
	public void setInstruction(String instruction){
		this.instruction = instruction;
	}
	public void setIngredient(String name, double amount){
		ingredients.put(name, amount);
	}
	
	//GETTERS
	public String getName(){
		return name;
	}
	public String getInstruction(){
		return instruction;
	}
	public Set<String> listIngredients(){
		return ingredients.keySet();
	}
	public double getAmount(String name){
		return ingredients.get(name);
	}
	
	
	
	
	
	public boolean getEnough(){
		enough = true;
		for (String name : listIngredients()){
			if (getAmount(name) > Storage.getAmount(name)) enough = false;
		}
		return enough;
	}
	public Date getExpiration(){
		Date expiration = null;
		Date compare;
		for (String name : listIngredients()){
			compare = Storage.getIngredient(name).getExpiration();
			if (expiration == null) expiration = compare; 
			else if (expiration.after(compare)) expiration = compare;
		}

		return expiration;
	}
	public ArrayList<String> listAllergens(){
		ArrayList<String> allergens = new ArrayList<String>();

		for (String name : listIngredients()){
			allergens.addAll(Storage.getIngredient(name).listAllergens());
		}
		return allergens;
	}
	
	
	
	
	
	
	//DELETE
	public void deleteIngredient(String name){
		ingredients.remove(name);
	}
	
	//PRINT
	public void print(){
		System.out.println("RECIPE INFO");
		System.out.println("Name: " + name);
		System.out.println("Ingredients: " + ingredients.entrySet());
		System.out.println("Instruction: " + instruction);
		System.out.println("All ingredients in storage? " + getEnough());
		System.out.println("Expiration: " + getExpiration());
		System.out.println("Allergens: " + listAllergens());
		System.out.println("");
	}
}
