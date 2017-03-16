package recipeTool;

import java.util.*;
import java.util.Map.Entry;

public class Recipe {
	
	//PROPERTIES
	private String name;
	private String instruction;
	private boolean enough;
	private HashMap<Ingredient, Double> ingredients = new HashMap<Ingredient, Double>();
	
	//CONSTRUCTOR
	public Recipe(String name, String instruction, Ingredient ingredient, Double amount){
		this.name = name;
		this.instruction = instruction;
		this.ingredients.put(ingredient, amount);
	}
	
	//SETTERS
	public void setName(String name){
		this.name = name;
	}
	public void setInstruction(String instruction){
		this.instruction = instruction;
	}
	public void setIngredient(Ingredient ingredient, double amount){
		this.ingredients.put(ingredient, amount);
	}
	
	//GETTERS
	public String getName(){
		return name;
	}
	public String getInstruction(){
		return instruction;
	}
	public HashMap<Ingredient, Double> getIngredients(){
		return ingredients;
	}
	public boolean getEnough(){
		enough = true;
		Iterator<Entry<Ingredient, Double>> iter = ingredients.entrySet().iterator();
		while (iter.hasNext()){
			Ingredient testIngredient = iter.next().getKey();
			if (Storage.getAmount(testIngredient.getName()) < ingredients.get(testIngredient)) enough = false;
		}
		return enough;
	}
	public Date getExpiration(){
		Calendar temp = Calendar.getInstance();
		temp.add(Calendar.YEAR, 100);
		Date expiration = temp.getTime();
		
		Iterator<Entry<Ingredient, Double>> iter = ingredients.entrySet().iterator();
		while (iter.hasNext()){
			Date compare = iter.next().getKey().getExpiration();
			if (compare.before(expiration)) expiration = compare;
		}
		return expiration;
	}
	public ArrayList<String> getAllergens(){
		ArrayList<String> allergens = new ArrayList<String>();
		Iterator<Entry<Ingredient, Double>> iter = ingredients.entrySet().iterator();
		while (iter.hasNext()){
			allergens.addAll(iter.next().getKey().getAllergens());
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
		System.out.println("Allergens: " + getAllergens());
		System.out.println("");
	}
}
