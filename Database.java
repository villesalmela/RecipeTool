package recipeTool;

import java.sql.*;

public class Database {
	
   //CONNECTION SETUP
	private static String HOST_URL = "jdbc:mariadb://localhost:3306/";
	private static String DB_URL = "jdbc:mariadb://localhost:3306/recipe_tool";
	private static String USER = "root";
	private static String PASS = "6RuN@2r8%+4*)ypQ2v$97(6em2-!/$,T";
	private static Connection connection = null;
   
   //OPEN & CLOSE CONNECTION
   public static void init() throws SQLException {
	   //Open a connection
	   connection = DriverManager.getConnection(HOST_URL, USER, PASS);
   }
   
   public static void open() throws SQLException {
	   //Open a connection
	   connection = DriverManager.getConnection(DB_URL, USER, PASS);
   }
   
   public static void close() throws SQLException {
	   //Close a connection
	   if(connection!=null) connection.close();
   }
   
   //PREPARE
   public static PreparedStatement prepare(String sql) throws SQLException{
	   PreparedStatement statement = connection.prepareStatement(sql);
	   return statement;
   }
   
   //QUERY PREPARED
   public static ResultSet query(PreparedStatement statement) throws SQLException{
       ResultSet result = statement.executeQuery();
       return result;
   }
   
   //QUERY NORMAL
   public static ResultSet query(String sql) throws SQLException{
       Statement statement = connection.createStatement();
	   ResultSet result = statement.executeQuery(sql);
       return result;
   }
   
   //UPDATE PREPARED
   public static int update(PreparedStatement statement) throws SQLException{
       int result = statement.executeUpdate();
       return result;  
   }
   
   //UPDATE NORMAL
   public static int update(String sql) throws SQLException{
       Statement statement = connection.createStatement();
	   int result = statement.executeUpdate(sql);
       return result;  
   }
}