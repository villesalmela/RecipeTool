package recipeTool;

import java.sql.*;

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

	// CONNECTION SETUP
	/**
	 * The URL-address for the database server's root.<br>
	 * For example 127.0.0.1:3306
	 */
	private static String HOST_URL;
	/**
	 * The URL-address for the database.<br>
	 * For example 127.0.0.1:3306/database_name
	 */
	private static String DB_URL;
	/**
	 * User name for the database. This user must have administrative
	 * privileges.
	 */
	private static String USER;
	/**
	 * User's password.
	 */
	private static String PASS;
	private static Connection connection = null;

	/**
	 * This constructor is private, thus preventing the instantiation of this
	 * class.
	 */
	private Database() {
	}

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
		HOST_URL = "jdbc:mariadb://" + url + ":" + port;
		DB_URL = "jdbc:mariadb://" + url + ":" + port + "/" + db;
		USER = user;
		PASS = pass;
	}

	/**
	 * This method will check, if the database is currently connected.
	 * 
	 * @return {@code true} if JDBC connection is valid; {@code false} if
	 *         connection is invalid.
	 */
	public static boolean isConnected() {
		try {
			return connection.isValid(1);
		} catch (Exception exception) {
			return false;
		}
	}

	// OPEN & CLOSE CONNECTION
	/**
	 * This method will create initial connection to the database server's root,
	 * for initializing the database.
	 * 
	 * @throws SQLException
	 *             if there is a database error.
	 */
	public static void init() throws SQLException {
		// Open a connection
		connection = DriverManager.getConnection(HOST_URL, USER, PASS);
	}

	/**
	 * This method will open a connection to the database, which is set up using
	 * {@link recipeTool.Database#setup(String, String, String, String, String)}
	 * 
	 * @throws SQLException
	 *             if there is a database error.
	 */
	public static void open() throws SQLException {
		// Open a connection
		close();
		connection = DriverManager.getConnection(DB_URL, USER, PASS);
	}

	/**
	 * This method will close the connection.
	 * 
	 */
	public static void close() {
		// Close a connection
		try {
			if (connection != null)
				connection.close();
		} catch (Exception e) {
			// Nothing much can be done, swallow the exception
		}
	}

	// PREPARE
	/**
	 * This method will prepare a statement, using provided string and an active
	 * connection.
	 * 
	 * @param sql
	 *            A String containing the SQL-commands.
	 * @return A PreparedStatement.
	 * @throws SQLException
	 *             if there is a database error.
	 */
	public static PreparedStatement prepare(String sql) throws SQLException {
		PreparedStatement statement = connection.prepareStatement(sql);
		return statement;

	}

	// QUERY PREPARED
	/**
	 * This method will query the database using an active connection, and
	 * return the results.<br>
	 * This method is overloaded, and there is also a version, which accepts a
	 * String as a parameter.
	 * <p>
	 * This method uses two resources: a ResultSet and its Statement. Remember
	 * to close the Statement after invoking this method, this will also trigger
	 * the closing of ResultSet.
	 * 
	 * @param statement
	 *            A PreparedStatement which contains a precompiled
	 *            SQL-statement.
	 * @return A ResultSet a ResultSet object that contains the data produced by
	 *         the query; never null
	 * @throws SQLException
	 *             if there is a database error.
	 */
	public static ResultSet query(PreparedStatement statement) throws SQLException {
		ResultSet result = statement.executeQuery();
		return result;

	}

	// QUERY NORMAL
	/**
	 * This method will query the database using an active connection, and
	 * return the results.<br>
	 * This method is overloaded, and there is also a version, which accepts a
	 * PreparedStatement as a parameter.
	 * <p>
	 * This method uses two resources: a ResultSet and its Statement. Remember
	 * to close the Statement after invoking this method, this will also trigger
	 * the closing of ResultSet.
	 * 
	 * @param sql
	 *            A String which contains SQL-statement.
	 * @return A ResultSet a ResultSet object that contains the data produced by
	 *         the query; never null
	 * @throws SQLException
	 *             if there is a database error.
	 */
	public static ResultSet query(String sql) throws SQLException {
		Statement statement = connection.createStatement();
		ResultSet result = statement.executeQuery(sql);
		return result;

	}

	// UPDATE PREPARED
	/**
	 * This method will update the database using an active connection.<br>
	 * This method is overloaded, and there is also a version, which accepts a
	 * String as a parameter.
	 * <p>
	 * This method will automatically close all resources it uses.
	 * 
	 * @param statement
	 *            A PreparedStatement which contains a precompiled
	 *            SQL-statement.
	 * @return either (1) the row count for SQL Data Manipulation Language (DML)
	 *         statements or (2) 0 for SQL statements that return nothing
	 * @throws SQLException
	 *             if there is a database error.
	 */
	public static int update(PreparedStatement statement) throws SQLException {
		try {
			int result = statement.executeUpdate();
			return result;
		} finally {
			statement.close();
		}

	}

	// UPDATE NORMAL
	/**
	 * This method will update the database using an active connection.<br>
	 * This method is overloaded, and there is also a version, which accepts a
	 * PreparedStatement as a parameter.
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
		try (Statement statement = connection.createStatement()) {
			int result = statement.executeUpdate(sql);
			return result;
		}
	}
}