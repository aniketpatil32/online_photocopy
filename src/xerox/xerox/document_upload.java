package xerox;

import java.io.FileInputStream;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import javax.sql.rowset.serial.SerialBlob;
import javax.swing.JOptionPane;

import xerox.authenticate.data;
import xerox.user.upload;

public class document_upload {
	 static Connection conn=null;
	 static PreparedStatement stmt=null;
	public static void document(){
		FileInputStream input = null;
		
		try {
			
			input = new FileInputStream(upload.file);
			byte b[] = new byte[(int)upload.file.length()];
			input.read(b);
			java.sql.Blob b2=new SerialBlob(b);
		    //Desktop.getDesktop().open(upload.file);
		   
			
			// 1. Get a connection to database
		    conn= dbconnection.connect();
     
			// 2. Prepare statement
		    
		    String sql="INSERT INTO document(username,filename,file) VALUES('"+data.username+"','"+upload.file.getName()+"',?)";
		    PreparedStatement stmt= conn.prepareStatement(sql);
			// 3. Set parameter for resume file name
			
			//JOptionPane.showMessageDialog(null,"before stmt");
			 stmt.setBlob(1,b2);
			//stmt.setBinaryStream(1, input);
			//JOptionPane.showMessageDialog(null,"stmt");
             // 4. Execute statemenent
			stmt.executeUpdate();
			JOptionPane.showMessageDialog(null,"File Saved to Documents");
			
		  } catch (Exception exc) {
			  exc.printStackTrace();
			   JOptionPane.showMessageDialog(null, exc);
		} finally {		
			if (input != null) {
				try {
					input.close();
				} catch (IOException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
			try {
				if(conn!=null)
				{
					
	             conn.close();
				}
				if(stmt!=null)
				{
	             stmt.close();
				}
			} catch (SQLException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
		}
	}

}
