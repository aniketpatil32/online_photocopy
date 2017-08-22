package xerox;

import java.sql.*;
import java.util.Map;

import javax.swing.*;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.Color;
import java.awt.Toolkit;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.font.TextAttribute;
public class authenticate {

	private static JFrame frmOnlinePhotocopy;
	private JTextField textField;

	/**
	 * Launch the application.
	 */
	static Statement stmt = null;
    static Connection conn=null;
    
    public static class data
    {
    static String username,password,email,name,mobile,balance,shop_status;
    static String history;
	static int temp,quantity,total_income;
	static String help_email,help_password,admin_username,admin_password;
    public static void get_admin(){
    	try{
			   conn=dbconnection.connect();
			   stmt = conn.createStatement();
			   String sql = "SELECT * FROM admin";
			   ResultSet rm = stmt.executeQuery(sql);
			 while(rm.next())
		       {
		        admin_username=rm.getString("username");
		        admin_password=rm.getString("password");
		       }
		        //STEP 6: Clean-up environment       
		    rm.close();
		    stmt.close();
	}
	catch(SQLException se){
	      //Handle errors for JDBC
		//JOptionPane.showMessageDialog(null,"Enter valid username and password");
	      se.printStackTrace();
	      
	   }catch(Exception e1){
	      //Handle errors for Class.forName
		  // JOptionPane.showMessageDialog(null,"Enter valid username and password");
	      e1.printStackTrace();
	      	   }finally{
	      //finally block used to close resources
	      try{
	         if(stmt!=null)
	            stmt.close();
	      }catch(SQLException se2){
	      }// nothing we can do
	      try{
	         if(conn!=null)
	            conn.close();
	      }catch(SQLException se){
	         se.printStackTrace();
	      }//end finally try
	      
	   }
		
    }
    }
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					
					authenticate window = new authenticate();
					window.frmOnlinePhotocopy.setVisible(true);
					
					
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}
	
     private JPasswordField passwordField;
	/**
	 * Create the application.
	 */
	
	public authenticate() {
		initialize();
		frmOnlinePhotocopy.setTitle("PHOENIX COPIER");
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frmOnlinePhotocopy = new JFrame();
		frmOnlinePhotocopy.setIconImage(Toolkit.getDefaultToolkit().getImage(authenticate.class.getResource("/xerox/Logo Transperent.png")));
		frmOnlinePhotocopy.setResizable(false);
		frmOnlinePhotocopy.setBounds(100, 100, 504, 329);
		frmOnlinePhotocopy.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frmOnlinePhotocopy.getContentPane().setLayout(null);
		
		textField = new JTextField();
		textField.setBounds(338, 67, 101, 20);
		frmOnlinePhotocopy.getContentPane().add(textField);
		textField.setColumns(10);
		
		JButton btnLogin = new JButton("Login");
		btnLogin.setBackground(new Color(51, 51, 153));
		btnLogin.setForeground(Color.WHITE);
		btnLogin.addActionListener(new ActionListener() {
			@SuppressWarnings("deprecation")
			public void actionPerformed(ActionEvent e) {
				try{
					data.get_admin();   
					conn=dbconnection.connect();
					   
					   stmt = conn.createStatement();
					   String sql = "SELECT status,balance FROM login WHERE username='admin'";
					   ResultSet rm = stmt.executeQuery(sql);
					   while(rm.next())
					   {
						   data.total_income=rm.getInt("balance");
						   data.shop_status=rm.getString("status");
						   if(data.shop_status.equals("confirm"))
						   {
							   data.shop_status="OPEN";
						   }
						   else
						   {
							   data.shop_status="CLOSED";
						   }
					   }
					int count=0;
					String temp = null;
					data.username=textField.getText();
					data.password=passwordField.getText();
					
				    passwordField.setText("");
					stmt = conn.createStatement();
					sql = "SELECT * FROM login WHERE username='"+data.username+"' AND password='"+data.password+"'";
				      ResultSet rs = stmt.executeQuery(sql);
				    while(rs.next())
				    {
				    temp=rs.getString("status");
				    data.name=rs.getString("name");
				    data.email=rs.getString("email");
				    data.mobile=rs.getString("mobile");
				    data.temp=rs.getInt("balance");
				    data.balance=Integer.toString(data.temp);
				    count++;
				    }
				   // System.out.println(data.admin_username+""+data.admin_password);
				    if(count>0)
				       {
				         if(data.username.equals(data.admin_username) && data.password.equals(data.admin_password))
				         {
				        	 
				        	 stmt = conn.createStatement();
						     sql = "UPDATE `login` SET `status` = 'confirmed' WHERE `login`.`username` = 'admin'";;
				             stmt.executeUpdate(sql);
				             JOptionPane.showMessageDialog(null,"Login successful");
					         frmOnlinePhotocopy.dispose();
				        	 admin ad=new admin();
				             ad.setVisible(true);
				            
				         }
				         
				         else if("confirmed".equals(temp))
				         {
				        	 JOptionPane.showMessageDialog(null,"Login successful");
				        	 frmOnlinePhotocopy.dispose();
				        	 user us=new user();
				        	 us.setVisible(true);
				        
				         }
				         else if("blocked".equals(temp))
					      {
					    	
					    	JOptionPane.showMessageDialog(null,"contact admin to activate account");
					      }
				         
				       } 
				    
				    else
				    {
				    	JOptionPane.showMessageDialog(null,"Enter valid username and password");
				    }
                    //STEP 6: Clean-up environment       
				    rs.close();
				    stmt.close();
			}
			catch(SQLException se){
			      //Handle errors for JDBC
				JOptionPane.showMessageDialog(null,"Enter valid username and password");
			      se.printStackTrace();
			     
				  passwordField.setText("");
			   }catch(Exception e1){
			      //Handle errors for Class.forName
				   JOptionPane.showMessageDialog(null,"Enter valid username and password");
			      e1.printStackTrace();
			      
				  passwordField.setText("");
			   }finally{
			      //finally block used to close resources
			      try{
			         if(stmt!=null)
			            stmt.close();
			      }catch(SQLException se2){
			      }// nothing we can do
			      try{
			         if(conn!=null)
			            conn.close();
			      }catch(SQLException se){
			         se.printStackTrace();
			      }//end finally try
			     
				  passwordField.setText("");
			   }
				
			}	
		});
		btnLogin.setBounds(289, 154, 101, 23);
		frmOnlinePhotocopy.getContentPane().add(btnLogin);
		
		JLabel lblUsername = new JLabel("Username");
		lblUsername.setForeground(Color.BLACK);
		lblUsername.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblUsername.setBounds(257, 68, 71, 14);
		frmOnlinePhotocopy.getContentPane().add(lblUsername);
		
		JLabel lblPassword = new JLabel("Password");
		lblPassword.setForeground(Color.BLACK);
		lblPassword.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblPassword.setBounds(257, 117, 71, 14);
		frmOnlinePhotocopy.getContentPane().add(lblPassword);
		
		JLabel lblOnlinePhotocopy = new JLabel("Login");
		lblOnlinePhotocopy.setHorizontalAlignment(SwingConstants.CENTER);
		lblOnlinePhotocopy.setForeground(Color.BLACK);
		lblOnlinePhotocopy.setFont(new Font("Tahoma", Font.PLAIN, 16));
		lblOnlinePhotocopy.setBounds(257, 33, 139, 23);
		frmOnlinePhotocopy.getContentPane().add(lblOnlinePhotocopy);
		
		passwordField = new JPasswordField();
		passwordField.addKeyListener(new KeyAdapter() {
			@SuppressWarnings("deprecation")
			@Override
			public void keyPressed(KeyEvent e) {
				    if (e.getKeyCode()==KeyEvent.VK_ENTER){
				    	try{
							data.get_admin();   
							conn=dbconnection.connect();
							   
							   stmt = conn.createStatement();
							   String sql = "SELECT status,balance FROM login WHERE username='admin'";
							   ResultSet rm = stmt.executeQuery(sql);
							   while(rm.next())
							   {
								   data.total_income=rm.getInt("balance");
								   data.shop_status=rm.getString("status");
								   if(data.shop_status.equals("confirm"))
								   {
									   data.shop_status="OPEN";
								   }
								   else
								   {
									   data.shop_status="CLOSED";
								   }
							   }
							int count=0;
							String temp = null;
							data.username=textField.getText();
							data.password=passwordField.getText();
							
						    passwordField.setText("");
							stmt = conn.createStatement();
							sql = "SELECT * FROM login WHERE username='"+data.username+"' AND password='"+data.password+"'";
						      ResultSet rs = stmt.executeQuery(sql);
						    while(rs.next())
						    {
						    temp=rs.getString("status");
						    data.name=rs.getString("name");
						    data.email=rs.getString("email");
						    data.mobile=rs.getString("mobile");
						    data.temp=rs.getInt("balance");
						    data.balance=Integer.toString(data.temp);
						    count++;
						    }
						   // System.out.println(data.admin_username+""+data.admin_password);
						    if(count>0)
						       {
						         if(data.username.equals(data.admin_username) && data.password.equals(data.admin_password))
						         {
						        	 
						        	 stmt = conn.createStatement();
								     sql = "UPDATE `login` SET `status` = 'confirmed' WHERE `login`.`username` = 'admin'";;
						             stmt.executeUpdate(sql);
						             JOptionPane.showMessageDialog(null,"Login successful");
							         frmOnlinePhotocopy.dispose();
						        	 admin ad=new admin();
						             ad.setVisible(true);
						            
						         }
						         
						         else if("confirmed".equals(temp))
						         {
						        	 JOptionPane.showMessageDialog(null,"Login successful");
						        	 frmOnlinePhotocopy.dispose();
						        	 user us=new user();
						        	 us.setVisible(true);
						        
						         }
						         else if("blocked".equals(temp))
							      {
							    	
							    	JOptionPane.showMessageDialog(null,"contact admin to activate account");
							      }
						         
						       } 
						    
						    else
						    {
						    	JOptionPane.showMessageDialog(null,"Enter valid username and password");
						    }
		                    //STEP 6: Clean-up environment       
						    rs.close();
						    stmt.close();
					}
					catch(SQLException se){
					      //Handle errors for JDBC
						JOptionPane.showMessageDialog(null,"Enter valid username and password");
					      se.printStackTrace();
					     
						  passwordField.setText("");
					   }catch(Exception e1){
					      //Handle errors for Class.forName
						   JOptionPane.showMessageDialog(null,"Enter valid username and password");
					      e1.printStackTrace();
					      
						  passwordField.setText("");
					   }finally{
					      //finally block used to close resources
					      try{
					         if(stmt!=null)
					            stmt.close();
					      }catch(SQLException se2){
					      }// nothing we can do
					      try{
					         if(conn!=null)
					            conn.close();
					      }catch(SQLException se){
					         se.printStackTrace();
					      }//end finally try
					     
						  passwordField.setText("");
					   }
				    }		
			}		    
		});
		passwordField.setBounds(338, 116, 101, 20);
		frmOnlinePhotocopy.getContentPane().add(passwordField);
		
		JButton btnNewButton = new JButton("Sign Up");
		btnNewButton.setBackground(new Color(51, 51, 153));
		btnNewButton.setForeground(Color.WHITE);
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try{
					frmOnlinePhotocopy.dispose();
			         Signup sup=new Signup();
			         sup.setVisible(true);
			       }
				catch(Exception e1){
					JOptionPane.showMessageDialog(null,"Error occured");
				}
			}
			
		});
		btnNewButton.setBounds(289, 236, 101, 23);
		frmOnlinePhotocopy.getContentPane().add(btnNewButton);
		
		JLabel lblNewMember = new JLabel("New member?");
		lblNewMember.setForeground(Color.BLACK);
		lblNewMember.setFont(new Font("Tahoma", Font.PLAIN, 12));
		lblNewMember.setBounds(185, 236, 83, 20);
		frmOnlinePhotocopy.getContentPane().add(lblNewMember);
		
		JLabel lblForgottPassword = new JLabel("Forgot Password?");
		Font font = lblForgottPassword.getFont();
		Map attributes = font.getAttributes();
		attributes.put(TextAttribute.UNDERLINE, TextAttribute.UNDERLINE_ON);
		lblForgottPassword.setFont(font.deriveFont(attributes));
		lblForgottPassword.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				try{
					   conn=dbconnection.connect();
					   stmt = conn.createStatement();
					   String sql = "SELECT email,password FROM helpline";
					   ResultSet rs = stmt.executeQuery(sql);
					   
			             //STEP 6: Clean-up environment 
					   while(rs.next())
					   {
						   data.help_email=rs.getString("email");
						   data.help_password=rs.getString("password");
					   }
					   
					   //String sql = "ALTER TABLE `upload` CHANGE `filename` `filename` VARCHAR(100) CHARACTER SET latin1 COLLATE latin1_swedish_ci NOT NULL";
					   //stmt.executeUpdate(sql);
					   System.out.print(data.help_email+" "+data.help_password);
				    rs.close();
				    stmt.close();
			}
			catch(SQLException se){
			      //Handle errors for JDBC
				JOptionPane.showMessageDialog(null,"Enter valid username and password");
			      se.printStackTrace();
			      textField.setText("");
				  passwordField.setText("");
			   }catch(Exception e1){
			      //Handle errors for Class.forName
				   JOptionPane.showMessageDialog(null,"Enter valid username and password");
			      e1.printStackTrace();
			      textField.setText("");
				  passwordField.setText("");
			   }finally{
			      //finally block used to close resources
			      try{
			         if(stmt!=null)
			            stmt.close();
			      }catch(SQLException se2){
			      }// nothing we can do
			      try{
			         if(conn!=null)
			            conn.close();
			      }catch(SQLException se){
			         se.printStackTrace();
			      }//end finally try
			      textField.setText("");
				  passwordField.setText("");
			   }
				
				frmOnlinePhotocopy.dispose();
				forgot f=new forgot();
				f.setVisible(true);
			}
		});
		lblForgottPassword.setBounds(288, 201, 131, 14);
		frmOnlinePhotocopy.getContentPane().add(lblForgottPassword);
		
		JLabel label = new JLabel("");
		label.setIcon(new ImageIcon(authenticate.class.getResource("/xerox/login.png")));
		label.setBounds(25, 70, 173, 155);
		frmOnlinePhotocopy.getContentPane().add(label);
		
		JLabel lblCopyright = new JLabel("BY ANIKET & OMKAR");
		lblCopyright.setBounds(369, 275, 149, 14);
		frmOnlinePhotocopy.getContentPane().add(lblCopyright);
		
		JLabel lblUserManual = new JLabel("User manual");
		lblUserManual.setForeground(Color.BLACK);
		lblUserManual.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				manual m=new manual();
				//frmOnlinePhotocopy.dispose();
				m.setVisible(true);
			}
		});
		lblUserManual.setFont(new Font("Tahoma", Font.PLAIN, 11));
		lblUserManual.setBounds(21, 275, 83, 14);
		frmOnlinePhotocopy.getContentPane().add(lblUserManual);
		

		JLabel lblContactUs = new JLabel("Contact us");
		lblContactUs.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				contact c=new contact();
				c.setVisible(true);
			}
		});
		lblContactUs.setFont(new Font("Tahoma", Font.PLAIN, 11));
		lblContactUs.setForeground(Color.BLACK);
		lblContactUs.setBounds(113, 275, 71, 14);
		frmOnlinePhotocopy.getContentPane().add(lblContactUs);
		
		JLabel label_1 = new JLabel("");
		label_1.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
			  contact c=new contact();
			  c.setVisible(true);
			}
		});
		label_1.setIcon(new ImageIcon(authenticate.class.getResource("/xerox/logofix.png")));
		label_1.setBounds(24, 11, 197, 23);
		frmOnlinePhotocopy.getContentPane().add(label_1);
		
		JLabel lblQuality = new JLabel("Always Quality Work For Quality People...");
		lblQuality.setForeground(new Color(51, 51, 153));
		lblQuality.setBounds(21, 45, 234, 14);
		frmOnlinePhotocopy.getContentPane().add(lblQuality);
		
		JLabel lblDevelopers = new JLabel("Developers");
		lblDevelopers.setForeground(Color.BLACK);
		lblDevelopers.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				developers d=new developers();
				d.setVisible(true);
			}
		});
		lblDevelopers.setBounds(195, 275, 73, 14);
		frmOnlinePhotocopy.getContentPane().add(lblDevelopers);
		
		
		JLabel lblNewLabel = new JLabel("");
		lblNewLabel.setForeground(new Color(245, 255, 250));
		lblNewLabel.setIcon(new ImageIcon(authenticate.class.getResource("/xerox/back ground.jpeg")));
		lblNewLabel.setHorizontalAlignment(SwingConstants.RIGHT);
		lblNewLabel.setBounds(0, 0, 498, 300);
		frmOnlinePhotocopy.getContentPane().add(lblNewLabel);
		
		
		
		
		
		
		
		
	}

	public void setVisible(boolean b) {
		  frmOnlinePhotocopy.setVisible(b);
		
	}
}
