package xerox;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JTextField;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
import java.sql.*;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import javax.swing.ImageIcon;
import java.awt.Toolkit;
import java.awt.Color;
public class confirm extends JFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTextField textField;

	/**
	 * Launch the application.
	 */
	Connection conn=null;
	Statement stmt=null;
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					confirm frame = new confirm();
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
	public confirm() {
		setIconImage(Toolkit.getDefaultToolkit().getImage(confirm.class.getResource("/xerox/Logo Transperent.png")));
		setResizable(false);
		this.setTitle("PHOENIX COPIER");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 388, 247);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		textField = new JTextField();
		textField.addKeyListener(new KeyAdapter() {
			public void keyPressed(KeyEvent e) {
			    if (e.getKeyCode()==KeyEvent.VK_ENTER){
			    	try{
						  conn=dbconnection.connect();
						  int count=0;
					      //STEP 2: Register JDBC driver
					      Class.forName("com.mysql.jdbc.Driver");				      
					      //STEP 4: Execute a query
					     String temp;
					     temp=textField.getText();
					     textField.setText("");
					     stmt = conn.createStatement();
						 String sql = "SELECT * FROM login WHERE username='"+temp+"'";
						 textField.setText("");
						  ResultSet rs= stmt.executeQuery(sql);
						  while(rs.next())
						  {
							  count++;
						  }
						  if(count>0)
						  {  
						  stmt = conn.createStatement();
					      sql = "UPDATE login  SET status='confirmed' WHERE username = '"+temp+"'";
		                  stmt.executeUpdate(sql);
					      JOptionPane.showMessageDialog(null,"your account has been activated");
						  }
						  else
						  {
							  JOptionPane.showMessageDialog(null,"Enter valid username");
						  }
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
					      textField.setText("");
					   }
			    }
			}
		});
		textField.setBounds(201, 77, 117, 20);
		contentPane.add(textField);
		textField.setColumns(10);
		
		JButton btnConfirm = new JButton("Confirm");
		btnConfirm.setBackground(new Color(51, 51, 153));
		btnConfirm.setForeground(Color.WHITE);
		btnConfirm.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try{
					  conn=dbconnection.connect();
					  int count=0;
				      //STEP 2: Register JDBC driver
				      Class.forName("com.mysql.jdbc.Driver");				      
				      //STEP 4: Execute a query
				     String temp;
				     temp=textField.getText();
				     textField.setText("");
				     stmt = conn.createStatement();
					 String sql = "SELECT * FROM login WHERE username='"+temp+"'";
					 textField.setText("");
					  ResultSet rs= stmt.executeQuery(sql);
					  while(rs.next())
					  {
						  count++;
					  }
					  if(count>0)
					  {  
					  stmt = conn.createStatement();
				      sql = "UPDATE login  SET status='confirmed' WHERE username = '"+temp+"'";
	                  stmt.executeUpdate(sql);
				      JOptionPane.showMessageDialog(null,"your account has been activated");
					  }
					  else
					  {
						  JOptionPane.showMessageDialog(null,"Enter valid username");
					  }
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
				      textField.setText("");
				   }
			}
			
		});
		btnConfirm.setBounds(90, 123, 89, 23);
		contentPane.add(btnConfirm);
		
		JButton btnNewButton = new JButton("Block");
		btnNewButton.setBackground(new Color(51, 51, 153));
		btnNewButton.setForeground(Color.WHITE);
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try{
					  int count=0;
					  conn=dbconnection.connect();
				      //STEP 2: Register JDBC driver
				      Class.forName("com.mysql.jdbc.Driver");				      
				      //STEP 4: Execute a query
				     String temp;
				     temp=textField.getText();
				     stmt = conn.createStatement();
					 String sql = "SELECT * FROM login WHERE username='"+temp+"'";
					 textField.setText("");
					  ResultSet rs= stmt.executeQuery(sql);
					  while(rs.next())
					  {
						  count++;
					  }
					  if(count>0)
					  {  
						  stmt = conn.createStatement();
						  sql = "UPDATE login  SET status= 'blocked' WHERE username = '"+temp+"'";
			              stmt.executeUpdate(sql);
						  JOptionPane.showMessageDialog(null,"your account has been blocked");
						  temp=null;
						  textField.setText("");
					  }
					  else
					  {
						  JOptionPane.showMessageDialog(null,"Enter valid username");
					  }
					 
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
				   }
			}
		});
		btnNewButton.setBounds(223, 123, 95, 23);
		contentPane.add(btnNewButton);
		
		JLabel lblAccountStatus = new JLabel("Account Status");
		lblAccountStatus.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblAccountStatus.setBounds(147, 11, 124, 30);
		contentPane.add(lblAccountStatus);
		
		JButton btnHome = new JButton("Home");
		btnHome.setBackground(new Color(51, 51, 153));
		btnHome.setForeground(Color.WHITE);
		btnHome.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try{
				   dispose();
				   admin ad=new admin();
				   ad.setVisible(true);
			}catch(Exception e1)
				{
				  JOptionPane.showMessageDialog(null,"Exception is there");
				}
			}
		});
		btnHome.setFont(new Font("Tahoma", Font.PLAIN, 11));
		btnHome.setBounds(10, 17, 89, 23);
		contentPane.add(btnHome);
		
		JLabel lblNewLabel = new JLabel("Username");
		lblNewLabel.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblNewLabel.setBounds(90, 78, 66, 14);
		contentPane.add(lblNewLabel);
		
		JLabel lblByAniket = new JLabel("BY ANIKET & OMKAR");
		lblByAniket.setBounds(248, 193, 124, 14);
		contentPane.add(lblByAniket);
		
		JLabel label = new JLabel("");
		label.setIcon(new ImageIcon(confirm.class.getResource("/xerox/back ground.jpeg")));
		label.setBounds(0, 0, 382, 218);
		contentPane.add(label);
		
	}
}
