package xerox;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import net.proteanit.sql.DbUtils;
import xerox.authenticate.data;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JButton;
import java.awt.Font;
import java.awt.event.ActionListener;
import java.io.File;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.awt.event.ActionEvent;
import javax.swing.JFileChooser;
import javax.swing.ImageIcon;
import javax.swing.JTextField;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.Toolkit;
import java.awt.Color;
import javax.swing.SwingConstants;


public class user extends JFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private JPanel contentPane;

	/**
	 * Launch the application.
	 */
	static Connection conn=null;
	static Statement stmt=null;
	private JTextField textField;
	private JTextField textField_1;
	
	public static class print_statusfun{
		ResultSet rs;
		public static void status(){
			print_status ps=new print_status();
			try{
				  conn=dbconnection.connect();
			      //STEP 2: Register JDBC driver
			      Class.forName("com.mysql.jdbc.Driver");				      
			      //STEP 4: Execute a query
			     
				 stmt = conn.createStatement();
			     String sql= "SELECT id,username,filename,printed FROM upload WHERE username='"+data.username+"'";
			     ResultSet rs= stmt.executeQuery(sql);
			     ps.getTable().setModel(DbUtils.resultSetToTableModel(rs));
			     ps.setVisible(true);
			     
			  
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
	public static class upload{
		static File file;
	}
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
				
					user frame = new user();
					frame.setVisible(true);
					
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public user() {
		setIconImage(Toolkit.getDefaultToolkit().getImage(user.class.getResource("/xerox/Logo Transperent.png")));
		setResizable(false);
		this.setTitle("PHOENIX COPIER");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 576, 350);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblWelcome = new JLabel("welcome,");
		lblWelcome.setFont(new Font("Tahoma", Font.PLAIN, 12));
		lblWelcome.setBounds(372, 27, 53, 14);
		contentPane.add(lblWelcome);
		
		JLabel label = new JLabel("");
		label.setFont(new Font("Tahoma", Font.PLAIN, 12));
		label.setText(data.username);
		label.setBounds(435, 27, 102, 14);
		contentPane.add(label);
		
		JButton btnUpload = new JButton("upload ");
		btnUpload.setBackground(new Color(51, 51, 153));
		btnUpload.setForeground(Color.WHITE);
		btnUpload.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				           try{
			       	         conn=dbconnection.connect();
			       	         data.quantity=Integer.parseInt(textField_1.getText());
			       	         file_upload.upload();
			       	         upload.file=null;
			       	         textField.setText("");
			       	         textField_1.setText("1");
				             }catch(Exception e1)
				               {
				        	   JOptionPane.showMessageDialog(null,"File and Quantity are mandatory");
				               }
				           
			       	  }
			});
		btnUpload.setBounds(326, 223, 120, 23);
		contentPane.add(btnUpload);
		
		JLabel lblWelcomeT = new JLabel("User Portal");
		lblWelcomeT.setFont(new Font("Tahoma", Font.PLAIN, 16));
		lblWelcomeT.setBounds(212, 22, 137, 23);
		contentPane.add(lblWelcomeT);
		
		JButton btnCredit = new JButton("Account");
		btnCredit.setBackground(new Color(51, 51, 153));
		btnCredit.setForeground(Color.WHITE);
		btnCredit.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try{
					 
					  conn=dbconnection.connect();
				      //STEP 2: Register JDBC driver
				      Class.forName("com.mysql.jdbc.Driver");				      
				      //STEP 4: Execute a query
				     
					 stmt = conn.createStatement();
				     String sql = "SELECT balance FROM login WHERE username='"+data.username+"'";
				     ResultSet rm = stmt.executeQuery(sql);
				     while(rm.next())
				     {
				    	 data.balance=rm.getString("balance");
				     }
				       dispose();
					   user_account ac=new user_account();
					   ac.setVisible(true);
					  // ac.label_5.setText(data.balance);
				  
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
		});
		btnCredit.setBounds(22, 75, 102, 23);
		contentPane.add(btnCredit);
		
		JButton btnLogout = new JButton("logout");
		btnLogout.setBackground(new Color(51, 51, 153));
		btnLogout.setForeground(Color.WHITE);
		btnLogout.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try
				{
					dispose();
					authenticate au=new authenticate();
					au.setVisible(true);
				}
				catch(Exception e1)
				{
					JOptionPane.showMessageDialog(null,"exceptions are there");
				}
			}
		});
		btnLogout.setBounds(22, 23, 102, 23);
		contentPane.add(btnLogout);
		
		JFileChooser fileChooser = new JFileChooser();
		fileChooser.setBounds(0, -33, 582, 397);
		//contentPane.add(fileChooser);
		
		JButton btnChooseFile = new JButton("Choose file ");
		btnChooseFile.setBackground(new Color(51, 51, 153));
		btnChooseFile.setForeground(Color.WHITE);
		btnChooseFile.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				JFrame frame=new JFrame();
				 int returnVal=fileChooser.showOpenDialog(frame);
				
				 if (returnVal == JFileChooser.APPROVE_OPTION) {
					 upload.file = fileChooser.getSelectedFile();
		             System.out.println(upload.file.getName());
		             textField.setText(upload.file.getName());
		            }
		            else{
		                          
		            	JOptionPane.showMessageDialog(null,"File not selected");
		            }      
				 
			}
		});
		btnChooseFile.setBounds(180, 125, 113, 23);
		contentPane.add(btnChooseFile);
		
		JLabel lblShop = new JLabel("SHOP  STATUS :");
		lblShop.setFont(new Font("Tahoma", Font.PLAIN, 12));
		lblShop.setBounds(22, 250, 102, 14);
		contentPane.add(lblShop);


		JLabel label_3 = new JLabel("");
		label_3.setFont(new Font("Tahoma", Font.PLAIN, 14));
		label_3.setForeground(Color.RED);
		label_3.setText(data.shop_status);
		label_3.setBounds(123, 250, 75, 14);
		contentPane.add(label_3);
		
		JButton btnPrintStatus = new JButton("Print Status");
		btnPrintStatus.setBackground(new Color(51, 51, 153));
		btnPrintStatus.setForeground(Color.WHITE);
		btnPrintStatus.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try{
					  print_status ps=new print_status();
					  conn=dbconnection.connect();
				      //STEP 2: Register JDBC driver
				      Class.forName("com.mysql.jdbc.Driver");				      
				      //STEP 4: Execute a query
				     
					 stmt = conn.createStatement();
				     String sql = "SELECT id,filename,quantity,printed FROM upload WHERE username='"+data.username+"'";
				     ResultSet rm = stmt.executeQuery(sql);
				    ps.getTable().setModel(DbUtils.resultSetToTableModel(rm));
				    dispose();
				    ps.setVisible(true);
				  
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
		});
		btnPrintStatus.setBounds(22, 125, 102, 23);
		contentPane.add(btnPrintStatus);
		
		JButton btnNewButton = new JButton("Quantity");
		btnNewButton.setBackground(new Color(51, 51, 153));
		btnNewButton.setForeground(Color.WHITE);
		btnNewButton.setBounds(180, 173, 113, 23);
		contentPane.add(btnNewButton);
		
		textField = new JTextField();
		textField.setHorizontalAlignment(SwingConstants.CENTER);
		textField.setBounds(326, 126, 120, 20);
		contentPane.add(textField);
		textField.setColumns(10);
		
		textField_1 = new JTextField();
		textField_1.setHorizontalAlignment(SwingConstants.CENTER);
		textField_1.setText("1");
		textField_1.addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent e) {
				 if (e.getKeyCode()==KeyEvent.VK_ENTER){
					 try{
		       	         conn=dbconnection.connect();
		       	         data.quantity=Integer.parseInt(textField_1.getText());
		       	         file_upload.upload();
		       	         upload.file=null;
		       	         textField.setText("");
		       	         textField_1.setText("1");
			             }catch(Exception e1)
			               {
			        	   JOptionPane.showMessageDialog(null,"File and Quantity are mandatory");
			               }
				 }
			}
		});
		textField_1.setBounds(326, 174, 120, 20);
		contentPane.add(textField_1);
		textField_1.setColumns(10);
		
		JButton btnMyDocuments = new JButton("Documents");
		btnMyDocuments.setBackground(new Color(51, 51, 153));
		btnMyDocuments.setForeground(Color.WHITE);
		btnMyDocuments.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try{
					 //fetching documents for user
					  view_user_document vd=new  view_user_document();
					  conn=dbconnection.connect();
				      //STEP 2: Register JDBC driver
				      Class.forName("com.mysql.jdbc.Driver");				      
				      //STEP 4: Execute a query
				     
					 stmt = conn.createStatement();
				     String sql= "SELECT id,filename FROM document WHERE username='"+data.username+"'";
				     ResultSet rs= stmt.executeQuery(sql);
				     vd.getTable().setModel(DbUtils.resultSetToTableModel(rs));
				     
				    dispose();
				    vd.setVisible(true);
				  
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
		});
		btnMyDocuments.setBounds(22, 173, 102, 23);
		contentPane.add(btnMyDocuments);
		

		JButton btnSave = new JButton("Save");
		btnSave.setBackground(new Color(51, 51, 153));
		btnSave.setForeground(Color.WHITE);
		btnSave.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				 conn=dbconnection.connect();
       	         document_upload.document();
       	         textField.setText("");
       	         textField_1.setText("");
			}
		});
		btnSave.setBounds(456, 125, 93, 23);
		contentPane.add(btnSave);
		
		JLabel lblAniketOmkar = new JLabel("BY ANIKET & OMKAR");
		lblAniketOmkar.setBounds(402, 296, 147, 14);
		contentPane.add(lblAniketOmkar);
		
		
		JLabel label_1 = new JLabel("");
		label_1.setIcon(new ImageIcon(user.class.getResource("/xerox/back ground.jpeg")));
		label_1.setBounds(0, 0, 570, 321);
		contentPane.add(label_1);			
		
		
		
		
		
		
		
		
		
		
	
		
		
		
		}
}
