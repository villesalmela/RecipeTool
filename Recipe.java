package recipeTool;

import java.util.*;
import java.util.Map.Entry;

public class Recipe {
	
	//PROPERTIES
	private String name;
	private String instruction;
	private HashMap<Ingredient, Double> ingredients;
	
	//SETTERS
	public void setName(String name){}
	public void setInstruction(String instruction){}
	public void setIngredient(Ingredient ingredient, double amount){}
	
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
	public Date getExpiration(){
		Calendar temp = Calendar.getInstance();
		temp.add(Calendar.YEAR, 100);
		Date expiration = temp.getTime();
		
		Iterator<Entry<Ingredient, Double>> iter = ingredients.entrySet().iterator();
		while (iter.hasNext()){
			Date compare = iter.next().getKey().getExpiration();
			if (compare.before(expiration)) compare = expiration;
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
	public void print(){}
	}
