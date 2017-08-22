package xerox;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import xerox.authenticate.data;

import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JButton;
import javax.swing.JTextField;
import java.awt.Font;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.awt.event.ActionEvent;
import javax.swing.JComboBox;
import javax.swing.ImageIcon;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.Toolkit;
import java.awt.Color;
public class user_account extends JFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private JPanel contentPane;
	JLabel label_5;
	/**
	 * Launch the application.
	 */
	Connection conn=null;
	Statement stmt=null;
	String [] options={"","name","username","password","email","mobile"};
	static String name,email,mobile;
	static int balance;
	static String value;
	public static class modified
	{
		static String username,password,name,mobile,email;
	}
	private JTextField textField;
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					user_account frame = new user_account();
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
	public user_account() {
		setIconImage(Toolkit.getDefaultToolkit().getImage(user_account.class.getResource("/xerox/Logo Transperent.png")));
		setResizable(false);
		this.setTitle("PHOENIX COPIER");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 536, 332);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblName = new JLabel("Name");
		lblName.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblName.setBounds(92, 71, 46, 14);
		contentPane.add(lblName);
		
		JLabel lblUsername = new JLabel("Username");
		lblUsername.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblUsername.setBounds(92, 105, 65, 14);
		contentPane.add(lblUsername);
		
		JLabel lblMobileNo = new JLabel("Mobile no");
		lblMobileNo.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblMobileNo.setBounds(92, 140, 65, 14);
		contentPane.add(lblMobileNo);
		
		JLabel lblNewLabel = new JLabel("Credit");
		lblNewLabel.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblNewLabel.setBounds(92, 211, 73, 14);
		contentPane.add(lblNewLabel);
		
		JButton btnChangeInfo = new JButton("Update");
		btnChangeInfo.setBackground(new Color(51, 51, 153));
		btnChangeInfo.setForeground(Color.WHITE);
		btnChangeInfo.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try{  
					String sql;
					conn=dbconnection.connect();
				      //STEP 2: Register JDBC driver
				      Class.forName("com.mysql.jdbc.Driver");				      
				      //STEP 4: Execute a query
				       if(value=="name")
						 {
				    	     modified.name=textField.getText();							 
							 stmt = conn.createStatement();
							 sql = "UPDATE login  SET name=" + "\"" + modified.name + "\"" + " WHERE username=" + "\"" + data.username + "\"" + "";
			                 stmt.executeUpdate(sql);
			                 data.name=modified.name;
						 }
						 
				       else  if(value=="username")
					  {
				    	 ResultSet rs=null;
					     stmt = conn.createStatement();
					     sql = "SELECT * FROM login WHERE username='"+textField.getText()+"'";
					     rs=stmt.executeQuery(sql);
			             if(rs.next()==true)
			             {
			            	 JOptionPane.showMessageDialog(null,"username already exist");
			             }
			             else{
			            	 
						 modified.username=textField.getText();
						 stmt = conn.createStatement();
						 sql = "UPDATE login  SET username=" + "\"" + modified.username + "\"" + "  WHERE username = " + "\"" + data.username + "\"" + "";
		                 stmt.executeUpdate(sql);
		                 data.username=modified.username;
			             }
					  }
					 else if(value=="mobile")
					 {
						 modified.mobile=textField.getText();
						 stmt = conn.createStatement();
					     sql = "UPDATE login  SET mobile="+modified.mobile+" WHERE username = '"+data.username+"'";
		                 stmt.executeUpdate(sql);
		                 data.mobile=modified.mobile;
					  }
					 else if(value=="email")
					 {
						 modified.email=textField.getText();
						 System.out.println(modified.email);
						 stmt = conn.createStatement();
						 sql = "UPDATE login SET email=" + "\"" + modified.email + "\"" + " WHERE username = " + "\"" + data.username + "\"" + "";
		                 stmt.executeUpdate(sql);
		                 data.email=modified.email;
					 }
					 else  if(value=="password")
					  {
						 modified.password=textField.getText();
						 stmt = conn.createStatement();
						 sql = "UPDATE login  SET password=" + "\"" + modified.password + "\"" + "  WHERE username = " + "\"" + data.username + "\"" + "";
		                 stmt.executeUpdate(sql);
		                 data.password = modified.password ;
					  }
				     textField.setText("");
					 dispose();
					 user_account frame = new user_account();
					 frame.setVisible(true);
					 
				   
				 }catch(SQLException se){
				      //Handle errors for JDBC
					 JOptionPane.showMessageDialog(null,"Username Already exist");
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
				      textField.setText("");
				   }//en
			 }
			
	});
		btnChangeInfo.setFont(new Font("Tahoma", Font.PLAIN, 11));
		btnChangeInfo.setBounds(321, 255, 97, 23);
		contentPane.add(btnChangeInfo);
		
		JLabel lblUsersProfile = new JLabel("User's Profile");
		lblUsersProfile.setFont(new Font("Tahoma", Font.PLAIN, 16));
		lblUsersProfile.setBounds(145, 24, 115, 14);
		contentPane.add(lblUsersProfile);
		
		JButton btnHome = new JButton("Home");
		btnHome.setBackground(new Color(51, 51, 153));
		btnHome.setForeground(Color.WHITE);
		btnHome.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try{
					dispose();
					user us=new user();
					us.setVisible(true);
				}catch(Exception e1)
				{
					JOptionPane.showMessageDialog(null, "Exception is there");
				}
			}
		});
		btnHome.setFont(new Font("Tahoma", Font.PLAIN, 11));
		btnHome.setBounds(10, 15, 89, 23);
		contentPane.add(btnHome);
		
		JLabel label_1 = new JLabel("");
		label_1.setText(data.name);
		label_1.setBounds(214, 72, 136, 14);
		contentPane.add(label_1);
		
		JLabel label_2 = new JLabel("");
		label_2.setText(data.username);
		label_2.setBounds(214, 106, 136, 14);
		contentPane.add(label_2);
		
		JLabel label_3 = new JLabel("");
		label_3.setText(data.mobile);
		label_3.setBounds(214, 141, 136, 14);
		contentPane.add(label_3);
		
		JLabel label_5 = new JLabel("");
		label_5.setText(data.balance);
		label_5.setBounds(214, 212, 136, 14);
		contentPane.add(label_5);
		
		JLabel lblEmail = new JLabel("Email");
		lblEmail.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblEmail.setBounds(92, 178, 46, 14);
		contentPane.add(lblEmail);
		
		JLabel label_4 = new JLabel("");
		label_4.setText(data.email);
		label_4.setBounds(211, 179, 213, 14);
		contentPane.add(label_4);
		
		JComboBox comboBox = new JComboBox(options);
		comboBox.setBackground(Color.WHITE);
		comboBox.setForeground(Color.BLACK);
		comboBox.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				 if (comboBox.getSelectedIndex() != -1) {                     
		               value=(String) comboBox.getItemAt(comboBox.getSelectedIndex());             
		            }     
				 System.out.println(value);
		            //statusLabel.setText(data);
				
			}
		});
		comboBox.setBounds(39, 257, 144, 20);
		contentPane.add(comboBox);
		
		textField = new JTextField();
		textField.addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent e) {
				 if (e.getKeyCode()==KeyEvent.VK_ENTER){
					 try{  
							String sql;
							conn=dbconnection.connect();
						      //STEP 2: Register JDBC driver
						      Class.forName("com.mysql.jdbc.Driver");				      
						      //STEP 4: Execute a query
						       if(value=="name")
								 {
						    	     modified.name=textField.getText();							 
									 stmt = conn.createStatement();
									 sql = "UPDATE login  SET name=" + "\"" + modified.name + "\"" + " WHERE username=" + "\"" + data.username + "\"" + "";
					                 stmt.executeUpdate(sql);
					                 data.name=modified.name;
								 }
								 
						       else  if(value=="username")
							  {
						    	 ResultSet rs=null;
							     stmt = conn.createStatement();
							     sql = "SELECT * FROM login WHERE username='"+textField.getText()+"'";
							     rs=stmt.executeQuery(sql);
					             if(rs.next()==true)
					             {
					            	 JOptionPane.showMessageDialog(null,"username already exist");
					             }
					             else{
					            	 
								 modified.username=textField.getText();
								 stmt = conn.createStatement();
								 sql = "UPDATE login  SET username=" + "\"" + modified.username + "\"" + "  WHERE username = " + "\"" + data.username + "\"" + "";
				                 stmt.executeUpdate(sql);
				                 data.username=modified.username;
					             }
							  }
							 else if(value=="mobile")
							 {
								 modified.mobile=textField.getText();
								 stmt = conn.createStatement();
							     sql = "UPDATE login  SET mobile="+modified.mobile+" WHERE username = '"+data.username+"'";
				                 stmt.executeUpdate(sql);
				                 data.mobile=modified.mobile;
							  }
							 else if(value=="email")
							 {
								 modified.email=textField.getText();
								 System.out.println(modified.email);
								 stmt = conn.createStatement();
								 sql = "UPDATE login SET email=" + "\"" + modified.email + "\"" + " WHERE username = " + "\"" + data.username + "\"" + "";
				                 stmt.executeUpdate(sql);
				                 data.email=modified.email;
							 }
							 else  if(value=="password")
							  {
								 modified.password=textField.getText();
								 stmt = conn.createStatement();
								 sql = "UPDATE login  SET password=" + "\"" + modified.password + "\"" + "  WHERE username = " + "\"" + data.username + "\"" + "";
				                 stmt.executeUpdate(sql);
				                 data.password = modified.password ;
							  }
						     textField.setText("");
							 dispose();
							 user_account frame = new user_account();
							 frame.setVisible(true);
							 
						   
						 }catch(SQLException se){
						      //Handle errors for JDBC
							 JOptionPane.showMessageDialog(null,"Username Already exist");
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
						      textField.setText("");
						   }//en
					 
				 }
			}
		});
		textField.setBounds(193, 257, 107, 20);
		contentPane.add(textField);
		textField.setColumns(10);
		
		JLabel lblEnter = new JLabel("Enter");
		lblEnter.setBounds(39, 232, 46, 14);
		contentPane.add(lblEnter);
		
		JLabel lblAniketOmkar = new JLabel("BY ANIKET & OMKAR");
		lblAniketOmkar.setBounds(354, 289, 144, 14);
		contentPane.add(lblAniketOmkar);
		
		JLabel label = new JLabel("");
		label.setIcon(new ImageIcon(user_account.class.getResource("/xerox/back ground.jpeg")));
		label.setBounds(0, 0, 530, 303);
		contentPane.add(label);
		
		
	}
}
