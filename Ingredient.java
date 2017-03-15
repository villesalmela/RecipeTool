package recipeTool;
import java.util.*;

public class Ingredient {
	
	//PROPERTIES
	private String name;
	private Unit unit;
	private ArrayList<String> allergens;
	private Date expiration;
	
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
	public void print(){}
}
