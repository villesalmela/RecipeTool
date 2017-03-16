package recipeTool;
import java.util.*;

public class Ingredient {
	
	//PROPERTIES
	private String name;
	private Unit unit;
	private ArrayList<String> allergens = new ArrayList<String>();
	private Date expiration;
	
	//CONSTURCTOR
	public Ingredient(String name, Unit unit, ArrayList<String> allergens, Date expiration){
		this.name = name;
		this.unit = unit;
		this.allergens = allergens;
		this.expiration = expiration;
	}
	
	//SETTERS
	public void setName(String name){
		this.name = name;
	}
	public void setUnit(Unit unit){
		this.unit = unit;
	}
	public void setAllergens(ArrayList<String> allergens){
		this.allergens = allergens;
	}
	public void setExpiration(Date expiration){
		this.expiration = expiration;
	}
	
	//GETTERS
	public String getName(){
		return name;
	}
	public Unit getUnit(){
		return unit;
	}
	public ArrayList<String> getAllergens(){
		return allergens;
	}
	public Date getExpiration(){
		return expiration;
	}
	
	//DELETE
	public void deleteAllergens(){
		allergens.clear();
	}
	public void deleteExpiration(){
		expiration = null;
	}
	
	//PRINT
	public void print(){
		System.out.println("INGREDIENT INFO:");
		System.out.println("Name: " + name);
		System.out.println("Unit: " + unit);
		System.out.println("Allergens: " + allergens);
		System.out.println("Expiration: " + expiration);
		System.out.println("Amount in storage: " + Storage.getAmount(name));
		System.out.println("");
	}
	
	//TEMP
	public String toString(){return name;}
	
}
