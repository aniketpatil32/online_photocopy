package xerox;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
import javax.swing.JButton;
import javax.swing.JTextField;
import javax.swing.JPasswordField;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;
import java.awt.event.ActionEvent;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

import javax.mail.internet.InternetAddress;
import javax.swing.ImageIcon;
import java.awt.Toolkit;
import java.awt.Color;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class Signup extends JFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTextField textField;
	private JTextField textField_1;
	private JPasswordField passwordField;
	private JTextField textField_2;
	private JTextField textField_3;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Signup frame = new Signup();
					frame.setVisible(true);
					
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}
	Statement stmt = null;
    Connection conn=null;
    private JPasswordField passwordField_1;
	/**
	 * Create the frame.
	 */
	public Signup() {
		setIconImage(Toolkit.getDefaultToolkit().getImage(Signup.class.getResource("/xerox/Logo Transperent.png")));
		setResizable(false);
		this.setTitle("PHOENIX COPIER");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 633, 385);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblSignup = new JLabel("Signup");
		lblSignup.setFont(new Font("Tahoma", Font.PLAIN, 16));
		lblSignup.setBounds(275, 11, 84, 27);
		contentPane.add(lblSignup);
		
		JLabel lblName = new JLabel("Name");
		lblName.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblName.setBounds(180, 66, 46, 14);
		contentPane.add(lblName);
		
		JLabel lblUsername = new JLabel("Username");
		lblUsername.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblUsername.setBounds(180, 103, 76, 14);
		contentPane.add(lblUsername);
		
		JLabel lblPassword = new JLabel("Password");
		lblPassword.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblPassword.setBounds(180, 140, 61, 14);
		contentPane.add(lblPassword);
		
		JLabel lblEmail = new JLabel("Email");
		lblEmail.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblEmail.setBounds(180, 216, 46, 14);
		contentPane.add(lblEmail);
		
		JLabel lblMobile = new JLabel("Mobile");
		lblMobile.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblMobile.setBounds(180, 255, 46, 14);
		contentPane.add(lblMobile);
		
		JButton btnSignup = new JButton("Signup");
		btnSignup.setBackground(new Color(51, 51, 153));
		btnSignup.setForeground(Color.WHITE);
		btnSignup.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try{
					  conn=dbconnection.connect();
				      //STEP 2: Register JDBC driver
				      Class.forName("com.mysql.jdbc.Driver");				      
				      //STEP 4: Execute a query
				      String name,username,password,email,mobile,confirm;
				      name=textField.getText();
				      username=textField_1.getText();
				      password=passwordField.getText();
				      confirm=passwordField_1.getText();
				      email=textField_2.getText();
				      mobile=textField_3.getText();
				      String regex="\\d{0,3}\\d{10}$";
				      String m = mobile.toString();
				      boolean b=m.matches(regex);
				      boolean c=true;
				      
				      try{
				      InternetAddress emailAddr = new InternetAddress(email);
				      emailAddr.validate();
				      }catch(Exception ex){
				    	  c=false;
				    	  
				      }
				   if(!username.equals("")&& !password.equals(""))
				   {  
					if(b==true)
					 {
				      if(password.equals(confirm))
				      {
				    	  if(c==true){
				    		  
					  stmt = conn.createStatement();
				      String sql = "INSERT INTO login(name,username,password,email,mobile) VALUES('"+name+"','"+username+"','"+password+"','"+email+"','"+mobile+"')";
				      stmt.executeUpdate(sql);
				      JOptionPane.showMessageDialog(null,"To activate account contact admin");
				      dispose();
				      authenticate au=new authenticate();
				      au.setVisible(true);
				                    }
				              else
				               {
				    	         JOptionPane.showMessageDialog(null,"Please check your email"); 
				               }
				      }
				      else{
				    	  JOptionPane.showMessageDialog(null, "Please check your password");
				          }
					   }
					   else{
						    JOptionPane.showMessageDialog(null, "Inavalid Mobile");
					       }
				       }
				      else
				           {
					        JOptionPane.showMessageDialog(null,"Username and password are Mandatory"); 
				           }
				      
				   }catch(SQLException se){
					   JOptionPane.showMessageDialog(null,"Username or email is already registered");
					   textField_2.setText("");
					   textField_1.setText("");
					   //passwordField.setText("");
					  // passwordField_1.setText("");
					   //textField_2.setText("");
					   //textField_3.setText("");
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
		btnSignup.setFont(new Font("Tahoma", Font.PLAIN, 11));
		btnSignup.setBounds(264, 294, 89, 23);
		contentPane.add(btnSignup);
		
		textField = new JTextField();
		textField.setBounds(364, 64, 118, 20);
		contentPane.add(textField);
		textField.setColumns(10);
		
		textField_1 = new JTextField();
		textField_1.setBounds(364, 101, 118, 20);
		contentPane.add(textField_1);
		textField_1.setColumns(10);
		
		passwordField = new JPasswordField();
		passwordField.setBounds(364, 138, 118, 20);
		contentPane.add(passwordField);
		
		textField_2 = new JTextField();
		textField_2.setBounds(364, 214, 118, 20);
		contentPane.add(textField_2);
		textField_2.setColumns(10);
		
		textField_3 = new JTextField();
		textField_3.addKeyListener(new KeyAdapter() {
			@SuppressWarnings("deprecation")
			@Override
			public void keyPressed(KeyEvent e) {
			    if (e.getKeyCode()==KeyEvent.VK_ENTER){
			    	try{
						  conn=dbconnection.connect();
					      //STEP 2: Register JDBC driver
					      Class.forName("com.mysql.jdbc.Driver");				      
					      //STEP 4: Execute a query
					      String name,username,password,email,mobile,confirm;
					      name=textField.getText();
					      username=textField_1.getText();
					      password=passwordField.getText();
					      confirm=passwordField_1.getText();
					      email=textField_2.getText();
					      mobile=textField_3.getText();
					      
					   if(!username.equals("")&& !password.equals(""))
					   {  
						 
					      if(password.equals(confirm))
					      {
					    
						  stmt = conn.createStatement();
					      String sql = "INSERT INTO login(name,username,password,email,mobile) VALUES('"+name+"','"+username+"','"+password+"','"+email+"','"+mobile+"')";
					      stmt.executeUpdate(sql);
					      JOptionPane.showMessageDialog(null,"To activate account contact admin");
					      dispose();
					      authenticate au=new authenticate();
					      au.setVisible(true);
					       }
					      else{
					    	  JOptionPane.showMessageDialog(null, "Please check your password");
					          }
						   }
						   else
					           {
						        JOptionPane.showMessageDialog(null,"Username and password are Mandatory"); 
					           }
					      
					    
					   }catch(SQLException se){
					      //Handle errors for JDBC
						   JOptionPane.showMessageDialog(null,"Username or email is already registered");
						   textField_2.setText("");
						   textField_1.setText(""); 
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
		});
		textField_3.setBounds(364, 253, 118, 20);
		contentPane.add(textField_3);
		textField_3.setColumns(10);
		
		JLabel lblNewLabel = new JLabel("Confirm");
		lblNewLabel.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblNewLabel.setBounds(180, 178, 61, 14);
		contentPane.add(lblNewLabel);
		
		passwordField_1 = new JPasswordField();
		passwordField_1.setBounds(364, 176, 118, 20);
		contentPane.add(passwordField_1);
		
		JLabel lblAniketOmkar = new JLabel("BY ANIKET & OMKAR");
		lblAniketOmkar.setBounds(457, 331, 149, 14);
		contentPane.add(lblAniketOmkar);
		
		JButton btnHome = new JButton("Home");
		btnHome.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				authenticate au=new authenticate();
				dispose();
				au.setVisible(true);
			}
		});
		btnHome.setBackground(new Color(51, 51, 153));
		btnHome.setForeground(Color.WHITE);
		btnHome.setBounds(21, 15, 89, 23);
		contentPane.add(btnHome);

		JLabel lblTermsConditions = new JLabel("Terms & conditions");
		lblTermsConditions.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				terms t=new terms();
				t.setVisible(true);
			}
		});
		lblTermsConditions.setBounds(264, 331, 132, 14);
		contentPane.add(lblTermsConditions);
		
		JLabel label = new JLabel("");
		label.setIcon(new ImageIcon(Signup.class.getResource("/xerox/back ground.jpeg")));
		label.setBounds(0, 0, 627, 356);
		contentPane.add(label);
		
		
		
		
		
	}
}
