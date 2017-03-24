package recipeTool;

import java.util.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.DateFormat;
import java.text.ParseException;
import java.util.Arrays;

public class RecipeTool {

	public static void main(String[] args) throws Exception {
	
//START
		
		Database.init();
		Database.update("CREATE DATABASE IF NOT EXISTS recipe_tool");
	

		
		Database.open();
		Database.update("CREATE TABLE IF NOT EXISTS Ingredients ("
						+ "Name VARCHAR(255) PRIMARY KEY,"
						+ "Unit VARCHAR(255),"
						+ "Allergens VARCHAR(255),"
						+ "Expiration VARCHAR(255),"
						+ "Amount REAL)");
		
		GUI frame = new GUI();
		frame.setVisible(true);
		load();
		
	}
	public static void save() throws SQLException{
		Database.update("TRUNCATE TABLE Ingredients");
		
		PreparedStatement statement = Database.prepare("INSERT INTO Ingredients VALUES (?,?,?,?,?)");
		for (String i : Storage.listIngredients()){
			String name = i;
			String unit = Storage.getUnit(i).toString();
			String allergens = Arrays.asList(Storage.getAllergens(i)).toString();
			String expiration = DateFormat.getDateInstance().format(Storage.getExpiration(i));
			double amount = Storage.getAmount(i);
			statement.setString(1, name);
			statement.setString(2, unit);
			statement.setString(3, allergens);
			statement.setString(4, expiration);
			statement.setDouble(5, amount);
			Database.update(statement);
		}
	}
	
	public static void load() throws SQLException, ParseException{
		ResultSet results = Database.query("SELECT Name, Unit, Allergens, Expiration, Amount FROM Ingredients");
		
		String name;
		String tempUnit;
		Unit unit = null;
		String[] allergens = null;
		Date expiration;
		double amount;
		
		while (results.next()){
			name = results.getString(1);
			tempUnit = results.getString(2);
			allergens = results.getString(3).split(",");
			expiration = DateFormat.getDateInstance().parse(results.getString(4));
			amount = results.getDouble(5);
			
			switch (tempUnit){
				case "KG": unit = Unit.KG; break;
				case "L": unit = Unit.L; break;
				case "PCS": unit = Unit.PCS; break;
				default: System.out.println("Unit-SQL-ERROR");
			}
			
			Storage.setIngredient(name, amount, unit, allergens, expiration);
		}
	}
}
