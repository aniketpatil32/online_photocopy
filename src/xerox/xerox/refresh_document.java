package xerox;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import net.proteanit.sql.DbUtils;


public  class refresh_document{
		ResultSet rs;
		static Connection conn=null;
		static Statement stmt=null;
		public static void refresh_document(){
			document_view dv=new document_view();
			
			try{
				  conn=dbconnection.connect();
			      //STEP 2: Register JDBC driver
			      Class.forName("com.mysql.jdbc.Driver");				      
			      //STEP 4: Execute a query
			     
				 stmt = conn.createStatement();
			     String sql= "SELECT id,username,filename FROM document";
			     ResultSet rs= stmt.executeQuery(sql);
			     dv.getTable().setModel(DbUtils.resultSetToTableModel(rs));
			     dv.setVisible(true);
			     
			  
			   }catch(SQLException se){
			      //Handle errors for JDBC
			      se.printStackTrace();
			      
			   }catch(Exception e1){
			      //Handle errors for Class.forName
			      e1.printStackTrace();
			      
			   }finally{
			      //finally block used to close resources
			      try{
			         if(stmt!=null)
			            conn.close();
			      }catch(SQLException se){
			      }// do nothing
			      try{
			         if(conn!=null)
			            conn.close();
			      }catch(SQLException se){
			         se.printStackTrace();
			      }//end finally try
			   }//end try
		}
}
