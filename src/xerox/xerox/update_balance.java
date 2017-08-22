package xerox;


import javax.swing.*;
import javax.swing.border.EmptyBorder;

import xerox.authenticate.data;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.*;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

public class update_balance extends JFrame {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	JTextField textField;
	private JTextField textField_1;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {					
					update_balance frame = new update_balance();
					frame.setVisible(true);
					
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}
	Statement stmt = null;
    Connection conn=null;
	/**
	 * Create the frame.
	 */
	public update_balance() {
		setIconImage(Toolkit.getDefaultToolkit().getImage(update_balance.class.getResource("/xerox/Logo Transperent.png")));
		setResizable(false);
		this.setTitle("PHOENIX COPIER");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 279);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		textField = new JTextField();
		textField.setBounds(255, 74, 86, 20);
		contentPane.add(textField);
		textField.setColumns(10);
		
		JLabel lblAmount = new JLabel("Amount");
		lblAmount.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblAmount.setBounds(124, 129, 65, 14);
		contentPane.add(lblAmount);
		
		textField_1 = new JTextField();
		textField_1.addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent e) {
			    if (e.getKeyCode()==KeyEvent.VK_ENTER){
			    	try{
						  
						 int count=0;
						  conn=dbconnection.connect();
					      //STEP 2: Register JDBC driver
					      Class.forName("com.mysql.jdbc.Driver");				      
					      //STEP 4: Execute a query
					     int balance,old_balance = 0,bal;
					     String temp;
					     temp=textField_1.getText();
					     data.username=textField.getText();
					     textField_1.setText("");
					     textField.setText("");
					     balance=Integer.parseInt(temp);
					     bal=balance;
					     stmt = conn.createStatement();
						 String sql = "SELECT * FROM login WHERE username='"+data.username+"'";
						 textField.setText("");
						  ResultSet rs= stmt.executeQuery(sql);
						  while(rs.next())
						  {
							  count++;
						  }
						  if(count>0)
						  {  
							  stmt = conn.createStatement();
							     sql = "SELECT balance "
							     		+ "FROM login "
							     		+ "WHERE username='"+data.username+"'";
							     ResultSet rm = stmt.executeQuery(sql);
							      while(rm.next())
							      {
							    	 old_balance=rm.getInt("balance"); 
							      }
							      System.out.println("old balance is"+old_balance);
							      balance=old_balance+balance;
							      System.out.println("New balance is:"+balance);
							      stmt = conn.createStatement();
							      sql = "UPDATE login  SET balance="+balance+" WHERE username = '"+data.username+"'";
				                  stmt.executeUpdate(sql);
							      JOptionPane.showMessageDialog(null,"your account has been credited");
							      java.util.Date date = new java.util.Date();
							      data.history=bal+" "+"Rupees were credited in the account of "+data.username;
							      stmt = conn.createStatement();
								  sql ="INSERT INTO history(username,activity,date)"
								  		+ "VALUES('"+data.username+"','"+data.history+"','"+date+"')";
								     stmt.executeUpdate(sql);
						  }
						  else
						  {
							  JOptionPane.showMessageDialog(null,"Enter valid username");
						  }
						 
						 
					   }catch(SQLException se){
						   JOptionPane.showMessageDialog(null,"Enter valid username");
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
					      textField_1.setText("");
						  textField.setText("");
					   }//end try
			    } 
			}
		});
		textField_1.setBounds(255, 128, 86, 20);
		contentPane.add(textField_1);
		textField_1.setColumns(10);
		
		JButton btnNewButton = new JButton("Add");
		btnNewButton.setBackground(new Color(51, 51, 153));
		btnNewButton.setForeground(Color.WHITE);
		btnNewButton.setFont(new Font("Tahoma", Font.PLAIN, 11));
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try{
					  
					 int count=0;
					  conn=dbconnection.connect();
				      //STEP 2: Register JDBC driver
				      Class.forName("com.mysql.jdbc.Driver");				      
				      //STEP 4: Execute a query
				     int balance,old_balance = 0,bal;
				     String temp;
				     temp=textField_1.getText();
				     data.username=textField.getText();
				     textField_1.setText("");
				     textField.setText("");
				     balance=Integer.parseInt(temp);
				     bal=balance;
				     stmt = conn.createStatement();
					 String sql = "SELECT * FROM login WHERE username='"+data.username+"'";
					 textField.setText("");
					  ResultSet rs= stmt.executeQuery(sql);
					  while(rs.next())
					  {
						  count++;
					  }
					  if(count>0)
					  {  
						  stmt = conn.createStatement();
						     sql = "SELECT balance "
						     		+ "FROM login "
						     		+ "WHERE username='"+data.username+"'";
						     ResultSet rm = stmt.executeQuery(sql);
						      while(rm.next())
						      {
						    	 old_balance=rm.getInt("balance"); 
						      }
						      System.out.println("old balance is"+old_balance);
						      balance=old_balance+balance;
						      System.out.println("New balance is:"+balance);
						      stmt = conn.createStatement();
						      sql = "UPDATE login  SET balance="+balance+" WHERE username = '"+data.username+"'";
			                  stmt.executeUpdate(sql);
						      JOptionPane.showMessageDialog(null,"your account has been credited");
						      java.util.Date date = new java.util.Date();
						      data.history=bal+" "+"Rupees were credited in the account of "+data.username;
						      stmt = conn.createStatement();
							  sql ="INSERT INTO history(username,activity,date)"
							  		+ "VALUES('"+data.username+"','"+data.history+"','"+date+"')";
							     stmt.executeUpdate(sql);
					  }
					  else
					  {
						  JOptionPane.showMessageDialog(null,"Enter valid username");
					  }
					 
				   }catch(SQLException se){
					   JOptionPane.showMessageDialog(null,"Enter valid username");
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
				      textField_1.setText("");
					  textField.setText("");
				   }//end try
		    

				
			}
		});
		btnNewButton.setBounds(118, 184, 89, 23);
		contentPane.add(btnNewButton);
		
		JLabel lblUsername = new JLabel("Username");
		lblUsername.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblUsername.setBounds(124, 75, 65, 14);
		contentPane.add(lblUsername);
		
		JLabel lblAddMoney = new JLabel("User Wallet ");
		lblAddMoney.setFont(new Font("Tahoma", Font.PLAIN, 16));
		lblAddMoney.setBounds(177, 10, 103, 20);
		contentPane.add(lblAddMoney);
		
		JButton btnHome = new JButton("Home");
		btnHome.setBackground(new Color(51, 51, 153));
		btnHome.setForeground(Color.WHITE);
		btnHome.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try
				{
					dispose();
					admin ad=new admin();
					ad.setVisible(true);
				}catch(Exception e1){
					JOptionPane.showMessageDialog(null,"Exception is there");
				}
				
			}
		});
		btnHome.setFont(new Font("Tahoma", Font.PLAIN, 11));
		btnHome.setBounds(10, 11, 89, 23);
		contentPane.add(btnHome);
		
		JButton btnDeduct = new JButton("Deduct");
		btnDeduct.setBackground(new Color(51, 51, 153));
		btnDeduct.setForeground(Color.WHITE);
		btnDeduct.setFont(new Font("Tahoma", Font.PLAIN, 11));
		btnDeduct.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try{
					 int old_total = 0,count=0;
					  conn=dbconnection.connect();
				      //STEP 2: Register JDBC driver
				      Class.forName("com.mysql.jdbc.Driver");				      
				      //STEP 4: Execute a query
				     int balance,old_balance = 0,bal;
				     String temp,username;
				     temp=textField_1.getText();
				     data.username=textField.getText();
				     balance=Integer.parseInt(temp);
				     bal=balance;
				     stmt = conn.createStatement();
					 String sql = "SELECT * FROM login WHERE username='"+data.username+"'";
					 textField.setText("");
					  ResultSet rs= stmt.executeQuery(sql);
					  while(rs.next())
					  {
						  count++;
					  }
					  if(count>0)
					  {  
						  stmt = conn.createStatement();
						  sql = "SELECT balance "
						     		+ "FROM login "
						     		+ "WHERE username='admin'";
						  ResultSet rm = stmt.executeQuery(sql);
						      while(rm.next())
						      {
						    	 old_total=rm.getInt("balance"); 
						      }
						     
							 stmt = conn.createStatement();
						     sql = "SELECT balance "
						     		+ "FROM login "
						     		+ "WHERE username='"+data.username+"'";
						     ResultSet rk = stmt.executeQuery(sql);
						      while(rk.next())
						      {
						    	 old_balance=rk.getInt("balance"); 
						      }
						     System.out.println("old balance is"+old_balance);
						 if(old_balance>=balance)
						 {
						      balance=old_balance-balance;
						      data.total_income=old_total+balance;
						      System.out.println("New balance is:"+balance);
						      stmt = conn.createStatement();
						      sql = "UPDATE login  SET balance="+balance+" WHERE username = '"+data.username+"'";
			                  stmt.executeUpdate(sql);
						      JOptionPane.showMessageDialog(null,"your account has been Debited");
						      //income
						      stmt = conn.createStatement();
						      data.total_income=old_total+bal;
						      sql = "UPDATE login  SET balance="+data.total_income+" WHERE username ='admin'";
			                  stmt.executeUpdate(sql);
						      
						      		      
						      java.util.Date date = new java.util.Date();
						      data.history=bal+" "+"Rupees were debited from the account of "+data.username;
						      stmt = conn.createStatement();
							  sql ="INSERT INTO history(username,activity,date)"
							  		+ "VALUES('"+data.username+"','"+data.history+"','"+date+"')";
							     stmt.executeUpdate(sql);
						 }
						 else
						 {
							 JOptionPane.showMessageDialog(null,"Deduction amount exceeds over balance");
						 }
						  
					  }
					  else
					  {
						  JOptionPane.showMessageDialog(null,"Enter valid username");
					  }
				      
				   }catch(SQLException se){
				      //Handle errors for JDBC
					   JOptionPane.showMessageDialog(null,"Enter valid username");
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
				      textField_1.setText("");
					  textField.setText("");
				   }//end try
			}
		});
		btnDeduct.setBounds(252, 184, 89, 23);
		contentPane.add(btnDeduct);

		JLabel lblAniketOmkar = new JLabel("BY ANIKET & OMKAR");
		lblAniketOmkar.setBounds(279, 225, 131, 14);
		contentPane.add(lblAniketOmkar);
		
		JLabel label = new JLabel("");
		label.setIcon(new ImageIcon(update_balance.class.getResource("/xerox/back ground.jpeg")));
		label.setBounds(0, 0, 444, 250);
		contentPane.add(label);
		
	}
}
