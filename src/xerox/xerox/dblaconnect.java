
package xerox;
import java.sql.Connection;
import java.sql.DriverManager;

import javax.swing.JOptionPane;

public  class dblaconnect {
	
	 static String url="jdbc:mysql://localhost:3306/zerox";
	static  String user="root";
	static String pass="";
  public static Connection dbconnect()
  {
	  try{
		  
		  Class.forName("com.mysql.jdbc.Driver");
		 Connection conn=DriverManager.getConnection(url, user, pass);
		 JOptionPane.showMessageDialog(null,"connected to database"); 
		 return conn;
		  
		  
	  }catch(Exception e){
		
		  return null;
	  }
	
  }
}
