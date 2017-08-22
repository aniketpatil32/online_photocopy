package xerox;

import java.sql.*;
public class dbconnection{
	static final String JDBC_DRIVER = "com.mysql.jdbc.Driver";  
    static final String DB_URL = "jdbc:mysql://167.114.194.134:3306/phoenixc_xerox";
   //static final String DB_URL = "jdbc:mysql://localhost:3306/zerox";

   //  Database credentials
      static final String USER = "phoenixc_phoenix";
      static final String PASS = "phoenix@123";
    //static final String USER = "root";
    //static final String PASS = "";
   // JDBC driver name and database URL
   public static Connection  connect() {
	   Connection conn = null;
   try{
      //STEP 2: Register JDBC driver
      Class.forName("com.mysql.jdbc.Driver");
       
      //STEP 3: Open a connectionr
      System.out.println("Connecting to database...");
      conn =DriverManager.getConnection(DB_URL,USER,PASS);
      //JOptionPane.showMessageDialog(null,"connected to database");
      System.out.println("connected to database");
      return conn;
       //conn.close();
   }catch(SQLException | ClassNotFoundException se){
      //Handle errors for JDBC
      se.printStackTrace();
      return null;
   }finally
   {
   System.out.println("connection closed!");
   }
}//end main
}//end FirstExample
