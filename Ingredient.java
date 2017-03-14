package recipeTool;
import java.util.*;

public class Ingredient {
	
	//RUNTIME DATABASE
	private static ArrayList<String> names;
	private static HashMap<String, Integer> unit;
	private static HashMap<String, ArrayList<String>> allergens;
	private static HashMap<String, Date> expiration;
	private static HashMap<String, Double> amount;
	

	//READ
	public static ArrayList<String> getNames(){
		return names;
	}
	public static int getUnit(String name){
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
	public static void setUnit(String name, int x){
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
	

}
