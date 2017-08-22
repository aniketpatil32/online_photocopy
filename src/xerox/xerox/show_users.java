package xerox;
import java.sql.*;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.JButton;
import java.awt.Font;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import javax.swing.ImageIcon;
import java.awt.Toolkit;
import java.awt.Color;

public class show_users extends JFrame {

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
	public static class captured
	{
		static String name,username,email,mobile,balance,status,password; 
	}
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					show_users frame = new show_users();
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
	public show_users() {
		setIconImage(Toolkit.getDefaultToolkit().getImage(show_users.class.getResource("/xerox/Logo Transperent.png")));
		setResizable(false);
		this.setTitle("PHOENIX COPIER");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 432, 245);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblNewLabel = new JLabel("Username");
		lblNewLabel.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblNewLabel.setBounds(109, 80, 65, 17);
		contentPane.add(lblNewLabel);
		
		textField = new JTextField();
		textField.addKeyListener(new KeyAdapter() {
			@Override
	
				public void keyPressed(KeyEvent e) {
				    if (e.getKeyCode()==KeyEvent.VK_ENTER){
				    	try{  
							conn=dbconnection.connect();
							int temp=0,count=0;
						      //STEP 2: Register JDBC driver
						      Class.forName("com.mysql.jdbc.Driver");				      
						      //STEP 4: Execute a query
						      stmt = conn.createStatement();
							  String sql = "SELECT * FROM login WHERE username='"+textField.getText()+"'";
							  textField.setText("");
							  ResultSet rs = stmt.executeQuery(sql);
							  while(rs.next())
							  {
								  captured.name=rs.getString("name");
								  captured.username=rs.getString("username");
								  captured.mobile=rs.getString("mobile");
								  temp=rs.getInt("balance");
								  captured.balance=Integer.toString(temp);
								  captured.status=rs.getString("status");
								  captured.email=rs.getString("email");
								  captured.password=rs.getString("password");
								  count++;
							  }
							  if(count>0)
							  {
								  search_result sr=new search_result();
								  System.out.println("got the record");
								  System.out.println("username"+captured.username);
								  System.out.println("name"+captured.name);
								  System.out.println("email"+captured.email);
								  System.out.println("mobile"+captured.mobile);
								  System.out.println("status"+captured.status);
								  dispose();
								  sr.setVisible(true);
								  
							  }
							  else
							  {
								  System.out.println("Record not found");
								  JOptionPane.showMessageDialog(null,"Record not found");
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
						   }//en

				
			  }
			}	    
		});
		textField.setBounds(226, 79, 114, 20);
		contentPane.add(textField);
		textField.setColumns(10);
		
		JButton btnUsersInfo = new JButton("User Profile");
		btnUsersInfo.setBackground(new Color(51, 51, 153));
		btnUsersInfo.setForeground(Color.WHITE);
		btnUsersInfo.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				try{  
					conn=dbconnection.connect();
					int temp=0,count=0;
				      //STEP 2: Register JDBC driver
				      Class.forName("com.mysql.jdbc.Driver");				      
				      //STEP 4: Execute a query
				      stmt = conn.createStatement();
					  String sql = "SELECT * FROM login WHERE username='"+textField.getText()+"'";
					  textField.setText("");
					  ResultSet rs = stmt.executeQuery(sql);
					  while(rs.next())
					  {
						  captured.name=rs.getString("name");
						  captured.username=rs.getString("username");
						  captured.mobile=rs.getString("mobile");
						  temp=rs.getInt("balance");
						  captured.balance=Integer.toString(temp);
						  captured.status=rs.getString("status");
						  captured.email=rs.getString("email");
						  captured.password=rs.getString("password");
						  count++;
					  }
					  if(count>0)
					  {
						  search_result sr=new search_result();
						  System.out.println("got the record");
						  System.out.println("username"+captured.username);
						  System.out.println("name"+captured.name);
						  System.out.println("email"+captured.email);
						  System.out.println("mobile"+captured.mobile);
						  System.out.println("status"+captured.status);
						  dispose();
						  sr.setVisible(true);
						  
					  }
					  else
					  {
						  System.out.println("Record not found");
						  JOptionPane.showMessageDialog(null,"Record not found");
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
				   }//en

			}
		});
		btnUsersInfo.setFont(new Font("Tahoma", Font.PLAIN, 11));
		btnUsersInfo.setBounds(158, 136, 102, 23);
		contentPane.add(btnUsersInfo);
		
		JLabel lblShowUsers = new JLabel("Find Users");
		lblShowUsers.setFont(new Font("Tahoma", Font.PLAIN, 16));
		lblShowUsers.setBounds(171, 22, 77, 14);
		contentPane.add(lblShowUsers);
		
		JButton btnNewButton = new JButton("Home");
		btnNewButton.setBackground(new Color(51, 51, 153));
		btnNewButton.setForeground(Color.WHITE);
		btnNewButton.addActionListener(new ActionListener() {
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
		btnNewButton.setFont(new Font("Tahoma", Font.PLAIN, 11));
		btnNewButton.setBounds(20, 13, 89, 23);
		contentPane.add(btnNewButton);
		
		JLabel lblNewLabel_1 = new JLabel("BY ANIKET & OMKAR");
		lblNewLabel_1.setBounds(267, 188, 137, 14);
		contentPane.add(lblNewLabel_1);
		
		JLabel label = new JLabel("");
		label.setIcon(new ImageIcon(show_users.class.getResource("/xerox/back ground.jpeg")));
		label.setBounds(0, 0, 444, 254);
		contentPane.add(label);
		
		
	}

}
