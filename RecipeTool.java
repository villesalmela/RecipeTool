/*
TODO:
Improve:
	Descriptive commenting
	Consistent naming of methods, variables, objects and classes
	Error handling
	
Fix:
	Expanding columns and rows
	When empty:
		get returns: null
		list returns: empty list
	GUI lots of methods
	
New feature:
	Serialization to MariaDB and File
*/
package recipeTool;

import java.io.IOException;
import java.net.URISyntaxException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.DateFormat;
import java.util.Arrays;
import java.util.Date;
import java.util.LinkedHashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.TreeSet;

import utilities.Database;
import utilities.Dialogs;
import utilities.InvalidDataException;
import utilities.Settings;
import utilities.Unit;

/**
 * This class is the starting point for application RecipeTool.<br>
 * The class also contains methods, which transforms data from database compatible format into internal data structure, and vice versa.
 * 
 * @author Ville Salmela
 *
 */
public class RecipeTool {

	/**
	 * This constructor is private, thus preventing the instantiation of this
	 * class.
	 */
	private RecipeTool() {
	}

	/**
	 * This method will prepare the database for this application and launch the
	 * Graphical User Interface.
	 * 
	 * @param args
	 *            Any arguments are ignored.
	 */
	public static void main(String[] args) {
		try {
			try {
				prepareDatabase();
				Dialogs.notice("Automatic database setup completed successfully.");
			} catch (Exception exception) {
				Dialogs.notice(
						"Automatic database setup did not succeed. Go to settings to manually connect.\n" + exception);
				exception.printStackTrace();
				Database.close();
			}
			GUI frame;
			frame = new GUI();
			frame.setVisible(true);
		} catch (Exception exception) {
			mayday(exception);
		}
	}// end method main

	/**
	 * This method will try to load existing settings using
	 * {@link utilities.Settings#readSettings()},<br>
	 * failing that it will use default values as connection parameters:<br>
	 * ip: localhost<br>
	 * port: 3306<br>
	 * username: root<br>
	 * password: [empty string] <br>
	 * <br>
	 * After setting up the connection parameters, the method will try to
	 * connect to db-server and create the required database and tables, if they
	 * do not exist yet.
	 * 
	 * @throws SQLException
	 *             if there is database error.
	 */
	public static void prepareDatabase() throws SQLException {

		// try reading settings from a file
		List<String> settings = Settings.readSettings();
		if (settings != null)
			Database.setup(settings.get(0), settings.get(1), settings.get(2), settings.get(3), "recipe_tool");
		// if fails, use default settings for setup
		else
			Database.setup("localhost", "3306", "root", "", "recipe_tool");

		Database.init();
		Database.update("CREATE DATABASE IF NOT EXISTS recipe_tool");

		Database.open();
		Database.update("CREATE TABLE IF NOT EXISTS Ingredients ("
				+ "Name VARCHAR(255) PRIMARY KEY," + "Unit VARCHAR(3) NOT NULL," + "Allergens VARCHAR(2550),"
				+ "Expiration VARCHAR(10)," + "Amount REAL NOT NULL)");

		Database.update("CREATE TABLE IF NOT EXISTS Recipes ("
				+ "Name VARCHAR(255) PRIMARY KEY," + "Instruction TEXT," + "Ingredients VARCHAR(2550) NOT NULL,"
				+ "Amounts VARCHAR(70) NOT NULL)");
	}// end method prepareDatabase

	/**
	 * This method will save data from {@link recipeTool.Book} and
	 * {@link recipeTool.Storage} to a SQL database using
	 * {@link utilities.Database}.
	 * 
	 * @throws SQLException
	 *             if there is a database error.
	 */
	public static void save() throws SQLException {
		Database.update("TRUNCATE TABLE ingredients");
		for (String i : Storage.listIngredients()) {
			String name = i;
			String unit = Storage.getUnit(i).toString();
			String allergens = null;
			if (Storage.getAllergens(i) != null) {
				allergens = Storage.getAllergens(i).toString();
			}
			String expiration = null;
			if (Storage.getExpiration(i) != null) {
				expiration = DateFormat.getDateInstance().format(Storage.getExpiration(i));
			}
			double amount = Storage.getAmount(i);

			PreparedStatement statement = Database.prepare("INSERT INTO Ingredients VALUES (?,?,?,?,?)");
			statement.setString(1, name);
			statement.setString(2, unit);
			statement.setString(3, allergens == null ? null : allergens.substring(1, allergens.length() - 1));
			statement.setString(4, expiration);
			statement.setDouble(5, amount);
			Database.update(statement);

		}
		Database.update("TRUNCATE TABLE Recipes");

		List<String> inames = new LinkedList<>();
		List<String> temp = new LinkedList<>();
		for (String rname : Book.listRecipes()) {
			inames.clear();
			temp.clear();
			String name = rname;
			String instruction = Book.getInsturction(rname);

			inames = new LinkedList<>(Book.listIngredients(rname));

			String ingredients = inames.toString().substring(1, inames.toString().length() - 1);
			for (String iname : inames) {
				temp.add(Double.toString(Book.getAmount(rname, iname)));
			}
			String amounts = temp.toString().substring(1, temp.toString().length() - 1);
			PreparedStatement statement = Database.prepare("INSERT INTO Recipes VALUES (?,?,?,?)");
			statement.setString(1, name);
			statement.setString(2, instruction);
			statement.setString(3, ingredients);
			statement.setString(4, amounts);
			Database.update(statement);
		}

	}// end method save

	/**
	 * This method will load data from SQL database using
	 * {@link utilities.Database}, and copy it to the
	 * {@link recipeTool.Storage} and {@link recipeTool.Book}.
	 * 
	 * @throws SQLException
	 *             if there is a database error.
	 * @throws InvalidDataException
	 *             if the data received from the database is somehow
	 *             defective/missing.
	 */
	public static void load() throws SQLException, InvalidDataException {
		try (ResultSet results = Database.query("SELECT Name, Unit, Allergens, Expiration, Amount FROM Ingredients")) {
			try (Statement statement = results.getStatement()) {

				// Declare variables

				String name;
				Unit unit = null;
				String alrg;
				TreeSet<String> allergens;
				String exp;
				Date expiration;
				double amount;

				// Process table ingredients
				while (results.next()) {

					name = results.getString(1);

					// Convert unit
					try {
						unit = Unit.valueOf(results.getString(2));
					} catch (Exception exception) {
						throw new InvalidDataException("Unit format is unknown.");
					}

					// Convert allergens
					alrg = results.getString(3);
					if (alrg.equals("") || alrg.equals(null) || alrg.equals("null"))
						allergens = null;
					else
						allergens = new TreeSet<>(Arrays.asList(results.getString(3).split(", ")));

					// Convert expiration date
					exp = results.getString(4);
					if (exp.equals("") || exp.equals(null) || exp.equals("null"))
						expiration = null;
					else {
						try {
							expiration = DateFormat.getDateInstance().parse(results.getString(4));
						} catch (Exception exception) {
							throw new InvalidDataException("Date format is unknown.");
						}
					}

					amount = results.getDouble(5);

					// Copy variables into runtime memory
					Storage.setIngredient(name, amount, unit, allergens, expiration);
				}
			}
		}

		try (ResultSet results = Database.query("SELECT Name, Instruction, Ingredients, Amounts FROM Recipes")) {
			try (Statement statement = results.getStatement()) {

				// Declare variables
				String rname;
				String instruction;
				LinkedHashMap<String, Double> ingredients;
				List<String> inames;
				List<String> amounts;
				double amount;

				// Process table recipes
				while (results.next()) {
					
					ingredients = new LinkedHashMap<>();
					rname = results.getString(1);
					instruction = results.getString(2);
					inames = new LinkedList<>(Arrays.asList(results.getString(3).split(", ")));
					amounts = new LinkedList<>(Arrays.asList(results.getString(4).split(", ")));
					
					if (amounts.size() != inames.size())
						throw new InvalidDataException("Ingredient amounts in a recipe are corrupted.");

					int i = 0;
					for (String iname : inames) {
						try {
							amount = Double.parseDouble(amounts.get(i));
						} catch (Exception exception) {
							throw new InvalidDataException("Ingredient amounts in a recipe are corrupted.");
						}
						ingredients.put(iname, amount);
						i++;
					}

					// Copy variables into runtime memory
					Book.setRecipe(rname, instruction, ingredients);
				}
			}
		}
	}// end method load

	public static void test() throws SQLException, IOException, URISyntaxException, InvalidDataException {
		String sql = new String(
				Files.readAllBytes(Paths.get(RecipeTool.class.getResource("/recipeTool/sql/test.sql").toURI())));
		Database.update(sql);

		sql = new String(
				Files.readAllBytes(Paths.get(RecipeTool.class.getResource("/recipeTool/sql/test2.sql").toURI())));
		Database.update(sql);
		load();
	}// end method test

	static void mayday(Exception exception) {
		Dialogs.error("The application encountered an unexpected situation, that could not be handled. "
				+ "This will likely cause instability or abnormal behaviour."
				+ "\n\nIf you want to keep your changes, you should try saving if possible, and then restart."
				+ "\n\nError info: " + exception.toString());
		exception.printStackTrace();
	}// end method mayday

}// end class RecipeTool
