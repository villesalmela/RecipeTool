package recipeTool;

import java.io.IOException;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import utilities.Database;
import utilities.Dialogs;
import utilities.Serial;
import utilities.Settings;

/**
 * This class is the starting point for application RecipeTool.<br>
 * The class also contains methods, which handle exporting and importing data.
 * 
 * @author Ville Salmela
 *
 */
public class RecipeTool {

	/**
	 * This Object holds all the ingredients.
	 */
	private static Storage storage = new Storage();

	/**
	 * This Object holds all the recipes.
	 */
	private static Book book = new Book();

	/**
	 * This field holds the current state of loading/saving policy.<br>
	 * true, if using database<br>
	 * false, if using local disk<br>
	 * <br>
	 * The saving policy can change if user allows or denies the use of database,
	 * or if database is unavailable/comes available again.
	 */
	private static boolean saveToDB = true;

	/**
	 * This constructor is private, thus preventing the instantiation of this
	 * class.
	 */
	private RecipeTool() {}

	/**
	 * This method will return the Storage, which holds all the ingredients.
	 * 
	 * @return {@link Storage}
	 */
	static Storage getStorage() {
		return storage;
	}

	/**
	 * This method will return the Book, which holds all the recipes.
	 * 
	 * @return {@link Book}
	 */
	static Book getBook() {
		return book;
	}

	/**
	 * This method will set saving/loading policy, as in whether to use database
	 * or local disk.
	 * 
	 * @param value
	 *            true, to use database<br>
	 *            false, to use local disk
	 */
	static void setSavePolicy(boolean value) {
		saveToDB = value;
	}
	
	/**
	 * This method get the current saving/loading policy
	 * as in whether to use database or local disk.
	 * 
	 * @return
	 *            true, if using database<br>
	 *            false, if using local disk
	 */
	static boolean getSavePolicy(){
		return saveToDB;
	}

	/**
	 * This method will prepare the database for this application and launch the
	 * Graphical User Interface.
	 * 
	 * @param args
	 *            Any arguments are ignored.
	 */
	public static void main(String[] args) {

		// Ignore any command line parameters
		args = null;

		try {
			// Check if settings file is ok
			if (Settings.readSettings() != null && Settings.readSettings().length >= 5) {
			
				try {
					// Read saving policy preference, local if specified, database otherwise 
					saveToDB = Settings.readSettings()[4].equals("false") ? false : true;
				} catch (Exception exception){
					// if it fails, no problem, default is true
				}
			}
			// If using database
			if (saveToDB) {
				try {
					
					// Prepare database
					prepareDatabase();
				} catch (Exception exception) {
					
					// If preparing failed, change saving policy to local disk
					saveToDB = false;
					
					// Present user with error message
					Dialogs.notice("Automatic database setup did not succeed. Using local disk instead."
							+ "You can also connect manually using the Settings-button.\n" + exception);
					exception.printStackTrace();
					
					//Close database connection
					Database.close();
				} 
			}
			
			// Launch Graphical User Interface
			GUI frame;
			frame = new GUI();
			frame.setVisible(true);
			
		} catch (Exception exception) {
			mayday(exception);
		}
	}// end method main

	/**
	 * This method will prepare the database for use.<br>
	 * The method will accept settings as arguments, or it can use following
	 * default settings:<br>
	 * ip: localhost<br>
	 * port: 3306<br>
	 * username: root<br>
	 * password: [empty string] <br>
	 * <br>
	 * After setting up the connection parameters, the method will try to
	 * connect to db-server and create the required database and tables, if they
	 * do not exist yet.
	 * 
	 * @param settings
	 *            -{@code null} to use default settings<br>
	 *            -s1, s2, s3, s4, s5 for manual settings<br>
	 *            -leave empty for reading settings from a file.
	 * @throws SQLException
	 *             if there is database error.
	 */
	static void prepareDatabase(String... settings) throws SQLException {

		// If no parameter is passed, try reading settings from a file
		if (settings != null && settings.length == 0)
			settings = Settings.readSettings();

		// If reading succeeded or got manual values, setup the database using
		// those settings
		if ((settings != null) && (settings.length >= 5))
			Database.setup(settings[0], settings[1], settings[2], settings[3], "recipe_tool");

		// If reading failed, use default settings for setup
		else
			Database.setup("localhost", "3306", "root", "", "recipe_tool");

		// Create initial connection to database server's root (e.g. 127.0.0.1),
		// and create the database if it's not there already
		Database.init();
		Database.update("CREATE DATABASE IF NOT EXISTS recipe_tool");

		// Now open connection directly to the target database (e.g.
		// 127.0.0.1/recipeTool),
		// and create the table if it's not there already
		Database.open();
		Database.update(
				"CREATE TABLE IF NOT EXISTS Objects (" + "Name VARCHAR(255) PRIMARY KEY," + "Object VARBINARY(8000))");

	}// end method prepareDatabase

	/**
	 * This method will serialize and save data from {@link recipeTool.RecipeTool#book} and
	 * {@link recipeTool.RecipeTool#storage} to a SQL database or local disk using
	 * {@link utilities.Database} and {@link utilities.Serial}.
	 * 
	 * @throws SQLException
	 *             if there is a database error.
	 * @throws IOException
	 *             if there is an I/O problem.
	 */
	static void save() throws SQLException, IOException {

		// If using local disk
		if (!saveToDB) {
			try {

				// Serialize objects and save them to file
				Serial.toFile(storage, Settings.getFolderPath() + "/Storage.ser");
				Serial.toFile(book, Settings.getFolderPath() + "/Book.ser");

			} catch (Exception exception) {
				throw new IOException();
			}
		}

		// If using database
		if (saveToDB) {

			// Prepare a statement
			try (PreparedStatement statement = Database.prepare("INSERT INTO Objects VALUES (?,?)");) {

				// Clear existing values from database
				Database.update("TRUNCATE TABLE objects");

				// Save storage
				statement.setString(1, "Storage");
				statement.setBinaryStream(2, Serial.toStream(storage));
				Database.update(statement);

				// Save book
				statement.setString(1, "Book");
				statement.setBinaryStream(2, Serial.toStream(book));
				Database.update(statement);

			} catch (Exception exception) {
				throw new SQLException();
			}
		}
	}// end method save

	/**
	 * This method will load data from SQL database using
	 * {@link utilities.Database} and {@link utilities.Serial}, and copy it to
	 * the {@link recipeTool.RecipeTool#storage} and {@link recipeTool.RecipeTool#book}.
	 * 
	 * @throws SQLException
	 *             if there is a database error.
	 * @throws IOException
	 *             if there is a input/output error.
	 */
	static void load() throws SQLException, IOException {

		// If using database
		if (saveToDB) {

			// Retrieve serialized objects from database
			try (ResultSet result = Database.query("SELECT Name, Object FROM Objects");
					Statement statement = result.getStatement();) {

				// Iterate through results
				while (result.next()) {
					String name = result.getString(1);

					// Deserialize storage
					if (name.equals("Storage"))
						storage = (Storage) Serial.fromStream(result.getBinaryStream(2));

					// Deserialize book
					else if (name.equals("Book"))
						book = (Book) Serial.fromStream(result.getBinaryStream(2));
				}
			} catch (Exception exception) {
				throw new SQLException();
			}
		}

		// If using local disk
		if (!saveToDB) {
			try {
				// Deserialize storage
				storage = (Storage) Serial.fromFile(Settings.getFolderPath() + "/Storage.ser");

				// Deserialize book
				book = (Book) Serial.fromFile(Settings.getFolderPath() + "/Book.ser");

			} catch (Exception exception) {
				throw new IOException();
			}
		}
	}// end method load

	/**
	 * This method loads test values.
	 * Files are retrieved from inside the JAR-file, and then deserialized.
	 * 
	 * @throws IOException if loading fails.
	 */
	static void test() throws IOException {
		
		try {
			// Deserialize storage test file
			storage = (Storage) Serial.fromStream(RecipeTool.class.getResourceAsStream("/recipeTool/test/test.ser"));
			
			// Deserialize book test file
			book = (Book) Serial.fromStream(RecipeTool.class.getResourceAsStream("/recipeTool/test/test2.ser"));
		} catch (Exception exception) {
			throw new IOException();
		}
	}// end method test

	/**
	 * This method will be invoked, when an unexpected exception occurs, in a
	 * way that it would normally crash the Java Virtual Machine.<br>
	 * <br>
	 * Instead of crashing, the user is given information about the exception,
	 * and encouraged to try saving and then restart manually.
	 * 
	 * @param exception
	 *            The exception which was not handled normally.
	 */
	static void mayday(Exception exception) {
		
		//Present error message to user
		Dialogs.error("The application encountered an unexpected situation, that could not be handled. "
				+ "This will likely cause instability or abnormal behaviour."
				+ "\n\nIf you want to keep your changes, you should try saving if possible, and then restart."
				+ "\n\nError info: " + exception.toString());
		
		// Print stack to console
		exception.printStackTrace();
	}// end method mayday

}// end class RecipeTool
