/*
TODO:
Improve:
	Coherent and uniform type settings
	Descriptive commenting
	Consistent naming of methods, variables, objects and classes
	Error handling
	Layout & visuals for GUI

New features:
	Modify (ingredients & recipes) in GUI
	
Fix:
	Deal with null & zero values
	Try to get rid of casting
	IfNotNull-refactoring
	Check instanceof before casting
	User deleting ingredients leads to empty recipe
*/
package recipeTool;

import java.util.Date;
import java.util.LinkedHashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.TreeSet;
import java.io.InputStream;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.DateFormat;
import java.util.Arrays;

public class RecipeTool {

	public static void main(String[] args) {
		try {
			prepareDatabase();
			Dialogs.notice("Automatic database setup completed successfully.");
		} catch (Exception exception) {
			Dialogs.notice("Automatic database setup did not succeed. Go to settings to manually connect.\n"+exception);
			Database.close();
		}
		GUI frame;
		try {
			frame = new GUI();
			frame.setVisible(true);
		} catch (Exception e) {
			Dialogs.error("Unexpected error, application will exit.");
			System.exit(1);
		}
	}//end method main
	
	public static void prepareDatabase() throws SQLException{
		
		try { //try reading settings from a file
			String[] settings = Settings.readSettings();
			Database.setup(settings[0], settings[1], settings[2], settings[3], "recipe_tool");
		} catch (Exception exception) { //if fails, use default settings for setup
			Database.setup("localhost", "3306", "root", "", "recipe_tool");
		}
		
		Database.init();
		Database.update("CREATE DATABASE IF NOT EXISTS recipe_tool"); //create database
	

		
		Database.open();
		Database.update("CREATE TABLE IF NOT EXISTS Ingredients (" //create table ingredients
						+ "Name VARCHAR(255) PRIMARY KEY,"
						+ "Unit VARCHAR(3),"
						+ "Allergens VARCHAR(2550),"
						+ "Expiration VARCHAR(10),"
						+ "Amount REAL)");
		
		Database.update("CREATE TABLE IF NOT EXISTS Recipes (" //create table recipes
						+ "Name VARCHAR(255) PRIMARY KEY,"
						+ "Instruction TEXT,"
						+ "Ingredients VARCHAR(2550),"
						+ "Amounts VARCHAR(70))");
	}//end method prepareDatabase
	
	public static void save() throws SQLException{
		Database.update("TRUNCATE TABLE ingredients");
		PreparedStatement statement = Database.prepare("INSERT INTO Ingredients VALUES (?,?,?,?,?)");
		for (String i : Storage.listIngredients()){
			String name = i;
			String unit = Storage.getUnit(i).toString();
			String allergens = Storage.getAllergens(i).toString();
			String expiration = DateFormat.getDateInstance().format(Storage.getExpiration(i));
			double amount = Storage.getAmount(i);
			statement.setString(1, name);
			statement.setString(2, unit);
			statement.setString(3, allergens.substring(1, allergens.length()-1));
			statement.setString(4, expiration);
			statement.setDouble(5, amount);
			Database.update(statement);
		}
		
		Database.update("TRUNCATE TABLE Recipes");
		statement = Database.prepare("INSERT INTO Recipes VALUES (?,?,?,?)");
		List<String> temp = new LinkedList<>();
		List<String> temp2 = new LinkedList<>();
		for (String j : Book.listRecipes()){
			temp.clear();
			temp2.clear();
			String name = j;
			String instruction = Book.getInsturction(j);
			
			temp= new LinkedList<>(Book.listIngredients(j));
			
			String ingredients = temp.toString().substring(1, temp.toString().length()-1);
			for (String k : temp){
				temp2.add(Double.toString(Book.getAmount(j, k)));
			}
			String amounts = temp2.toString().substring(1, temp2.toString().length()-1);
			
			statement.setString(1, name);
			statement.setString(2, instruction);
			statement.setString(3, ingredients);
			statement.setString(4, amounts);
			Database.update(statement);
		}
	}//end method save
	
	@SuppressWarnings("boxing")
	public static void load() throws SQLException{
		ResultSet results = Database.query("SELECT Name, Unit, Allergens, Expiration, Amount FROM Ingredients");
		
		String name;
		String tempUnit;
		Unit unit = null;
		TreeSet<String> allergens;
		Date expiration;
		double amount;
		
		while (results.next()){
			name = results.getString(1);
			tempUnit = results.getString(2);
			allergens = new TreeSet<>(Arrays.asList(results.getString(3).split(", ")));
			try {
				expiration = DateFormat.getDateInstance().parse(results.getString(4));
			} catch (Exception exception) {
				expiration = null;
			}
			amount = results.getDouble(5);
			
			switch (tempUnit){
				case "KG": unit = Unit.KG; break;
				case "L": unit = Unit.L; break;
				case "PCS": unit = Unit.PCS; break;
			}
			
			Storage.setIngredient(name, amount, unit, allergens, expiration);
		}
		results = Database.query("SELECT Name, Instruction, Ingredients, Amounts FROM Recipes");
		String instruction;
		LinkedHashMap<String, Double> ingredients;
		List<String> i;
		List<String> a;
		
		while (results.next()){
			ingredients = new LinkedHashMap<>();
			name = results.getString(1);
			instruction = results.getString(2);
			i = new LinkedList<>(Arrays.asList(results.getString(3).split(", ")));
			a = new LinkedList<>(Arrays.asList(results.getString(4).split(", ")));
			
			for (int k=0; k<i.size(); k++){
				String iname = i.get(k);
				Double amount2;
				try {
					amount2 = Double.parseDouble(a.get(k));
				} catch (Exception exception) {
					amount2 = 0.0;
				}
				ingredients.put(iname, amount2);
			}
			Book.setRecipe(name, instruction, ingredients);
		}
	}//end method load
	
	public static void test() throws SQLException{
		
		InputStream is = RecipeTool.class.getResourceAsStream("test.sql");
		String sql = new String(Settings.convertStreamToString(is));
		Database.update(sql);
		
		is = RecipeTool.class.getResourceAsStream("test2.sql");
		sql = new String(Settings.convertStreamToString(is));
		Database.update(sql);
		load();
	}//end method test
	
}//end class RecipeTool
