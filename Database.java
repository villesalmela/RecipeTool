package recipeTool;

import java.sql.*;

public class Database {
	
   //CONNECTION SETUP
	private static String HOST_URL;
	private static String DB_URL;
	private static String USER;
	private static String PASS;
	private static Connection connection = null;
	
	public static void setup(String ip, String port, String user, String pass, String db){
		HOST_URL = "jdbc:mariadb://"+ip+":"+port;
		DB_URL = "jdbc:mariadb://"+ip+":"+port+"/"+db;
		USER = user;
		PASS = pass;
	}
	
	public static boolean isConnected(){
		try {
			return connection.isValid(1);
		} catch (Exception exception) {
			return false;
		}
	}
	
   //OPEN & CLOSE CONNECTION
   public static void init() throws SQLException {
	   //Open a connection
	   connection = DriverManager.getConnection(HOST_URL, USER, PASS);
   }
   
   public static void open() throws SQLException {
	   //Open a connection
	   close();
	   connection = DriverManager.getConnection(DB_URL, USER, PASS);
   }
   
   public static void close(){
	   //Close a connection
	   try {
		if(connection!=null) connection.close();
	} catch (Exception e) {
		//Nothing much can be done, swallow the exception 
	}
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