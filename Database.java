package recipeTool;

import java.sql.*;

public class Database {
	
   //CONNECTION SETUP
   private static final String DB_URL = "jdbc:mariadb://localhost:3306/";
   private static final String USER = "root";
   private static final String PASS = "6RuN@2r8%+4*)ypQ2v$97(6em2-!/$,T";
   private static Connection connection = null;
   
   //OPEN & CLOSE CONNECTION
   public static void open() throws SQLException {
	   //Open a connection
	   if(connection==null) connection = DriverManager.getConnection(DB_URL, USER, PASS);
   }
   
   public static void close() throws SQLException {
	   //Close a connection
	   if(connection!=null) connection.close();
   }
   
   //GET CONNECTION
   public static Connection getConnection(){return connection;}
   
   //QUERY METHOD
   public static ResultSet query(PreparedStatement statement) throws SQLException{
       ResultSet result = statement.executeQuery();
       return result;
   }
   
   //UPDATE METHOD
   public static int update(PreparedStatement statement) throws SQLException{
       int result = statement.executeUpdate();
       return result;  
   }
}