package recipeTool;
import java.util.*;

public class Ingredient {
	
	//RUNTIME DATABASE
	private static ArrayList<String> names = new ArrayList<String>();
	private static HashMap<String, Unit> unit = new HashMap<String, Unit>();
	private static HashMap<String, ArrayList<String>> allergens = new HashMap<String, ArrayList<String>>();
	private static HashMap<String, Date> expiration = new HashMap<String, Date>();
	private static HashMap<String, Double> amount = new HashMap<String, Double>();
	

	//READ
	public static ArrayList<String> getNames(){
		return names;
	}
	public static Unit getUnit(String name){
		return unit.get(name);
	}
	public static ArrayList<String> getAllergens(String name){
		return allergens.get(name);
	}
	public static Date getExpiration(String name){
		return expiration.get(name);
	}
	public static double getAmount(String name){
		return amount.get(name);
	}
	
	//CREATE & UPDATE
	public static void setUnit(String name, Unit x){
		unit.put(name, x);
		if (!names.contains(name)) names.add(name);
	}
	public static void setAllergens(String name, ArrayList<String> x){
		allergens.put(name, x);
	}
	public static void setExpiration(String name, Date x){
		expiration.put(name, x);
	}
	public static void setAmount(String name, double x){
		amount.put(name, x);
	}
	
	//DELETE
	public static void delete(String name){
		unit.remove(name);
		allergens.remove(name);
		expiration.remove(name);
		amount.remove(name);
		names.remove(name);
	}
	
	//PRINT
	public static void print(String name){
		System.out.println("INGREDIENT INFO:");
		System.out.println("Name: " + name);
		System.out.println("Amount: " + getAmount(name) + " " + unit.get(name));
		System.out.println("Expiration: " + getExpiration(name));
		System.out.println("Allergens: " + getAllergens(name));
		System.out.println();
	}
	

}
