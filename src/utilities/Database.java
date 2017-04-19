package utilities;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

/**
 * This class contains methods which interact with a SQL database using a JDBC
 * driver.<br>
 * The class includes methods for:<br>
 * -setting up connection parameters<br>
 * -opening and closing a connection<br>
 * -preparing a statement<br>
 * -queries and updates (overloaded to accept both String commands and Prepared
 * Statements)
 * 
 * @author Ville Salmela
 *
 */
public class Database {

	/**
	 * The URL-address for the database server's root.<br>
	 * For example 127.0.0.1
	 */
	private static String hostURL;
	
	/**
	 * The database name.
	 */
	private static String dbName;

	/**
	 * Port number, which the database server listens.
	 */
	private static String portNumber;

	/**
	 * User name for the database. This user must have administrative
	 * privileges.
	 */
	private static String username;
	
	/**
	 * User's password.
	 */
	private static String password;
	
	/**
	 * Holds the connection the database.
	 */
	private static Connection connection = null;

	/**
	 * This constructor is private, thus preventing the instantiation of this
	 * class.
	 */
	private Database() {}

	/**
	 * This method will setup the connection parameters required to create a
	 * JDBC connection.
	 * 
	 * @param url
	 *            The URL-address for the database server's root. For example
	 *            127.0.0.1
	 * @param port
	 *            The port which the database server is listening. For example
	 *            3306
	 * @param user
	 *            User name for the database. This user must have administrative
	 *            privileges.
	 * @param pass
	 *            User's password.
	 * @param db
	 *            Database name.
	 * 
	 */
	public static void setup(String url, String port, String user, String pass, String db) {
		
		//Setup the connection parameters
		hostURL = url;
		dbName = db;
		portNumber = port;
		username = user;
		password = pass;
	}// end method setup

	/**
	 * This method will check, if the database is currently connected.
	 * 
	 * @return {@code true} if JDBC connection is valid; {@code false} if
	 *         connection is invalid.
	 */
	public static boolean isConnected() {
		try {

			// Check if the connection is valid
			return connection.isValid(1);
			
		} catch (Exception exception) {
			return false;
		}
	}// end method isConnected

	/**
	 * This method will create initial connection to the database server's root,
	 * for initializing the database.
	 * 
	 * @throws SQLException
	 *             if there is a database error.
	 */
	public static void init() throws SQLException {
		
		// Close any previous connections
		close();
		
		// Open a connection
		connection = DriverManager.getConnection("jdbc:mariadb://" + hostURL + ":" + portNumber, username, password);
	}// end method init

	/**
	 * This method will open a connection to the database, which is set up using
	 * {@link utilities.Database#setup(String, String, String, String, String)}
	 * 
	 * @throws SQLException
	 *             if there is a database error.
	 */
	public static void open() throws SQLException {
		
		// Close any previous connections
		close();
		
		// Open a connection
		connection = DriverManager.getConnection("jdbc:mariadb://" + hostURL + ":" + portNumber + "/" + dbName,
				username, password);
	}// end method open

	/**
	 * This method will close the connection.
	 * 
	 */
	public static void close() {
		try {

			//If the connections exists, close it
			if (connection != null)
				connection.close();
		} catch (Exception e) {
			// Swallow any exceptions
		}
	}// end method close

	/**
	 * This method will prepare a statement, using provided string and an active
	 * connection.
	 * 
	 * @param sql
	 *            A String containing the SQL-commands.
	 * @return A PreparedStatement.
	 * @throws SQLException
	 *             if there is a database error.
	 * @throws NullPointerException
	 * 				if the connection is closed.
	 */
	public static PreparedStatement prepare(String sql) throws SQLException {
		PreparedStatement statement = connection.prepareStatement(sql);
		return statement;

	}// end method prepare

	/**
	 * This method will query the database using an active connection, and
	 * return the results.<br>
	 * This method is overloaded, and there is also a version, which accepts a
	 * String as a parameter. See: {@link utilities.Database#query(String)}
	 * <p>
	 * This method uses two resources: a ResultSet and its Statement. Remember
	 * to close the Statement after invoking this method, this will also trigger
	 * the closing of ResultSet.
	 * 
	 * @param statement
	 *            A PreparedStatement which contains a precompiled
	 *            SQL-statement.
	 * @return A ResultSet object that contains the data produced by
	 *         the query; never null
	 * @throws SQLException
	 *             if there is a database error.
	 */
	public static ResultSet query(PreparedStatement statement) throws SQLException {
		
		// Do the querying
		ResultSet result = statement.executeQuery();
		
		// Return the result
		return result;
	}// end method query

	/**
	 * This method will query the database using an active connection, and
	 * return the results.<br>
	 * This method is overloaded, and there is also a version, which accepts a
	 * PreparedStatement as a parameter. See: {@link utilities.Database#query(PreparedStatement)}
	 * <p>
	 * This method uses two resources: a ResultSet and its Statement. Remember
	 * to close the Statement after invoking this method, this will also trigger
	 * the closing of ResultSet.
	 * 
	 * @param sql
	 *            A String which contains SQL-statement.
	 * @return A ResultSet object that contains the data produced by
	 *         the query; never null
	 * @throws SQLException
	 *             if there is a database error.
	 */
	public static ResultSet query(String sql) throws SQLException {
		
		// Create a statement
		Statement statement = connection.createStatement();
		
		// Do the querying
		ResultSet result = statement.executeQuery(sql);
		
		// Return the result
		return result;

	}// end method query

	/**
	 * This method will update the database using an active connection.<br>
	 * This method is overloaded, and there is also a version, which accepts a
	 * String as a parameter. See: {@link utilities.Database#update(String)}
	 * <p>
	 * Remember to close the statement after it is no longer necessary.
	 * 
	 * @param statement
	 *            A PreparedStatement which contains a precompiled
	 *            SQL-statement.
	 * @return either (1) the row count for SQL Data Manipulation Language (DML)
	 *         statements or (2) 0 for SQL statements that return nothing
	 * @throws SQLException
	 *             if there is a database error.
	 *             
	 */
	public static int update(PreparedStatement statement) throws SQLException {
		
		// Do the updating
		int result = statement.executeUpdate();
		
		// Return the result
		return result;

	}// end method update

	/**
	 * This method will update the database using an active connection.<br>
	 * This method is overloaded, and there is also a version, which accepts a
	 * PreparedStatement as a parameter. See: {@link utilities.Database#update(PreparedStatement)}
	 * <p>
	 * This method will automatically close all resources it uses.
	 * 
	 * @param sql
	 *            A String which contains SQL-statement.
	 * @return either (1) the row count for SQL Data Manipulation Language (DML)
	 *         statements or (2) 0 for SQL statements that return nothing
	 * @throws SQLException
	 *             if there is a database error.
	 */
	public static int update(String sql) throws SQLException {
		
		// Create a statement
		try (Statement statement = connection.createStatement()) {
			
			// Do the updating
			int result = statement.executeUpdate(sql);
			
			// Return the result
			return result;
		}
	}// end method update
}// end class Database