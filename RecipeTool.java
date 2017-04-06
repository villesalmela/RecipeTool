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
	User deleting ingredients leads to empty recipe
	Overgrowing columns and rows
*/
package recipeTool;

import java.util.Date;
import java.util.LinkedHashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.TreeSet;
import java.io.IOException;
import java.net.URISyntaxException;
import java.nio.file.Files;
import java.nio.file.Paths;
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
			exception.printStackTrace();
			Database.close();
		}
		GUI frame;
		try {
			frame = new GUI();
			frame.setVisible(true);
		} catch (Exception e) {
			Dialogs.error("Unexpected error, application will exit.");
			e.printStackTrace();
			System.exit(1);
		}
	}//end method main
	
	/**
	 * This method will try to load existing settings using {@link recipeTool.Settings#readSettings()},
	 * failing that it will use default values as connection parameters:<br>ip: localhost<br>port: 3306<br>username: root<br>password: [empty string]<p>
	 * After setting up the connection parameters, the method will try to connect to db-server and create the required database and tables, if they do not exist yet.
	 * @throws SQLException if there is database error.
	 */
	public static void prepareDatabase() throws SQLException{
		
		//try reading settings from a file
		List<String> settings = Settings.readSettings();
		if (settings != null) Database.setup(settings.get(0), settings.get(1), settings.get(2), settings.get(3), "recipe_tool");
		//if fails, use default settings for setup
		else Database.setup("localhost", "3306", "root", "", "recipe_tool");
		
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
	
	/**
	 * This method will save data from {@link recipeTool.Book} and {@link recipeTool.Storage} to a SQL database using {@link recipeTool.Database}.
	 * @return Nothing.
	 * @throws SQLException if there is a database error.
	 */
	public static void save() throws SQLException{
		Database.update("TRUNCATE TABLE ingredients");
		try(PreparedStatement statement = Database.prepare("INSERT INTO Ingredients VALUES (?,?,?,?,?)")){
			for (String i : Storage.listIngredients()){
				String name = i;
				String unit = Storage.getUnit(i).toString();
				String allergens = Storage.getAllergens(i).toString();
				String expiration = null;
				if (Storage.getExpiration(i) != null){
					expiration = DateFormat.getDateInstance().format(Storage.getExpiration(i));
				}
				double amount = Storage.getAmount(i);
				statement.setString(1, name);
				statement.setString(2, unit);
				statement.setString(3, allergens.substring(1, allergens.length()-1));
				statement.setString(4, expiration);
				statement.setDouble(5, amount);
				Database.update(statement);
			}
		}
			Database.update("TRUNCATE TABLE Recipes");
			try(PreparedStatement statement = Database.prepare("INSERT INTO Recipes VALUES (?,?,?,?)")){
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
			}
		
	}//end method save
	
	/**
	 * This method will load data from SQL database using {@link recipeTool.Database}, and copy it to the {@link recipeTool.Storage} and {@link recipeTool.Book}.
	 * @return Nothing.
	 * @throws SQLException if there is a database error.
	 * @throws InvalidDataException if the data received from the database is somehow defective/missing.
	 */
	public static void load() throws SQLException, InvalidDataException{
		try(ResultSet results = Database.query("SELECT Name, Unit, Allergens, Expiration, Amount FROM Ingredients")){
			
			//Declare variables
			
			String name;
			Unit unit = null;
			boolean pass;
			String alrg;
			TreeSet<String> allergens;
			String exp;
			Date expiration;
			double amount;
			
			//Process table ingredients
			while (results.next()){
		
				//Validate name
				name = results.getString(1);
				if (name.equals("") || name.equals(null) || name.equals("null")) throw new InvalidDataException("Ingredient name cannot be empty.");

				//Validate unit
				pass = false;
				for (Unit u: Unit.values()){
					if (u.toString().equals(results.getString(2))) {pass = true; break;}
				}
				if (pass == false) throw new InvalidDataException("Unknown unit.");
				unit = Unit.valueOf(results.getString(2));
				
				//Validate allergens
				alrg = results.getString(3);
				if (alrg.equals("") || alrg.equals(null) || alrg.equals("null")) allergens = null;
				else allergens = new TreeSet<>(Arrays.asList(results.getString(3).split(", ")));
				
				//Validate expiration
				exp = results.getString(4);
				if (exp.equals("") || exp.equals(null) || exp.equals("null")) expiration = null;
				else{
					try {
						expiration = DateFormat.getDateInstance().parse(results.getString(4));
					} catch (Exception exception) {
						throw new InvalidDataException("Date format unknown.");
					}
				}
				
				//Validate amount
				amount = results.getDouble(5);
				if (amount < 0) throw new InvalidDataException("Ingredient amount cannot be negative");
				

				//Copy variables into runtime memory
				Storage.setIngredient(name, amount, unit, allergens, expiration);
			}
		}
		
		try(ResultSet results = Database.query("SELECT Name, Instruction, Ingredients, Amounts FROM Recipes")){
			
			//Declare variables
			String rname;
			String instruction;
			LinkedHashMap<String, Double> ingredients;
			List<String> inames;
			String iname;
			List<String> amounts;
			double amount;
			
			//Process table recipes
			while (results.next()){
				ingredients = new LinkedHashMap<>();
				
				//Validate name
				rname = results.getString(1);
				if (rname.equals("") || rname.equals(null) || rname.equals("null")) throw new InvalidDataException("Receipe name cannot be empty.");

				//Validate instructions
				instruction = results.getString(2);
				if (instruction.equals("") || instruction.equals(null) || instruction.equals("null")) instruction = null;
				
				//Validate ingredients
				iname = results.getString(3);
				if (iname.equals("") || iname.equals(null) || iname.equals("null")) throw new InvalidDataException("Receipe cannot have zero ingredients.");
				else inames = new LinkedList<>(Arrays.asList(iname.split(", ")));
				
				amounts = new LinkedList<>(Arrays.asList(results.getString(4).split(", ")));
				if (amounts.size() != inames.size()) throw new InvalidDataException("Ingredient amounts in a recipe are corrupted.");
				
				int i = 0;
				for (String iname2 : inames){
					if (Storage.hasIngredient(iname2) == false) throw new InvalidDataException("Recipe contains an unknown ingredient.");
					try {
						amount = Double.parseDouble(amounts.get(i));
						if (amount < 0) throw new InvalidDataException("Ingredient amount cannot be negative");
					} catch (Exception exception) {
						throw new InvalidDataException("Ingredient amounts in a recipe are corrupted.");
					}
					ingredients.put(iname2, amount);
					i++;
				}
				
				//Copy variables into runtime memory
				Book.setRecipe(rname, instruction, ingredients);
			}
		}
	}//end method load
	
	public static void test() throws SQLException, IOException, URISyntaxException, InvalidDataException{
		String sql = new String (Files.readAllBytes(Paths.get(RecipeTool.class.getResource("/recipeTool/sql/test.sql").toURI())));
		Database.update(sql);

		sql = new String (Files.readAllBytes(Paths.get(RecipeTool.class.getResource("/recipeTool/sql/test2.sql").toURI())));
		Database.update(sql);
		load();
	}//end method test
	
}//end class RecipeTool
