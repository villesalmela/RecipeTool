package recipeTool;
import java.text.DateFormat;
import java.text.ParseException;
import java.util.*;
public class Recipe {

	private static ArrayList<String> names = new ArrayList<String>();
	private static HashMap<String, HashMap<String, Double>> ingredients = new HashMap<String, HashMap<String, Double>>();
	private static HashMap<String, ArrayList<String>> allergens = new HashMap<String, ArrayList<String>>();
	private static HashMap<String, Date> expiration = new HashMap<String, Date>();
	private static HashMap<String, String> instruction = new HashMap<String, String>();
	private static HashMap<String, Boolean> enough = new HashMap<String, Boolean>();
	
	//READ
	public static ArrayList<String> getNames(String name){
		return names;
	}
	public static HashMap<String, Double> getIngredients(String name){
		return ingredients.get(name);
	}
	public static ArrayList<String> getAllergens(String name){
		return allergens.get(name);
	}
	public static Date getExpiration(String name){
		return expiration.get(name);
	}
	public static String getInstruction(String name){
		return instruction.get(name);
	}
	public static boolean getEnough(String name){
		return enough.get(name);
	}
	
	//CREATE & UPDATE
	public static void setIngredients(String name, HashMap<String, Double> x){
		ingredients.put(name, x);
		if (!names.contains(name)){
			names.add(name);
			allergens.put(name, new ArrayList<String>());
		}
	}
	public static void setInstruction(String name, String x){
		instruction.put(name, x);
	}
	private static void setExpiration(String name, Date x){
		expiration.put(name, x);
	}
	private static void setAllergens(String name, ArrayList<String> x){
		allergens.get(name).addAll(x);
	}
	public static void update(String name) throws ParseException{
		boolean y = true;
		expiration.put(name, DateFormat.getDateInstance().parse("31.12.2020"));
		
		
		Iterator<String> iter = ingredients.get(name).keySet().iterator();
		while (iter.hasNext()) {
			String x = iter.next();
			
			if(getIngredients(name).get(x) > Ingredient.getAmount(x)) y = false; //ready to cook?
			
			
			if(getExpiration(name).after(Ingredient.getExpiration(x))) setExpiration(name, Ingredient.getExpiration(x)); //soonest expiry?
			
			setAllergens(name, Ingredient.getAllergens(x));

		}
		enough.put(name, y);
		
	}
	
	//DELETE
	public static void delete(String name){
		names.remove(name);
		ingredients.remove(name);
		allergens.remove(name);
		expiration.remove(name);
		instruction.remove(name);
		enough.remove(name);
	}
	
	//PRINT
	public static void print(String name){
		System.out.println("RECIPE INFO:");
		System.out.println("Instruction: " + getInstruction(name));
		System.out.println("Allergens: " + getAllergens(name));
		System.out.println("Expiration: " + getExpiration(name));
		System.out.println("Ready to cook: " + getEnough(name));
		System.out.println("Ingredients: " + getIngredients(name).toString());
		System.out.println();
		System.out.println();
	}
}
