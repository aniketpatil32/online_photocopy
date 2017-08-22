package xerox;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import xerox.authenticate.data;
import xerox.show_users.captured;

import javax.swing.JButton;
import javax.swing.JTextField;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
import java.awt.Color;
import javax.swing.ImageIcon;
import java.awt.Toolkit;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.awt.event.ActionEvent;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

public class forgot extends JFrame {

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
					forgot frame = new forgot();
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
	public forgot() {
		setIconImage(Toolkit.getDefaultToolkit().getImage(forgot.class.getResource("/xerox/Logo Transperent.png")));
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 416, 218);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JButton btnSend = new JButton("Send");
		btnSend.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try{  
					conn=dbconnection.connect();
					int count=0;
				      //STEP 2: Register JDBC driver
				      Class.forName("com.mysql.jdbc.Driver");				      
				      //STEP 4: Execute a query
				      data.email=textField.getText();
				      stmt = conn.createStatement();
					  String sql = "SELECT * FROM login WHERE email='"+data.email+"'";
					  textField.setText("");
					  ResultSet rs = stmt.executeQuery(sql);
					  while(rs.next())
					  {
						  data.password=rs.getString("password");
						  count++;
					  }
					  if(count>0)
					  {
						  SendEmail.send();
						  JOptionPane.showMessageDialog(null,"Password has been send to your email");
					  }
					  else
					  {
						  System.out.println("Record not found");
						  JOptionPane.showMessageDialog(null,"Email not registered contact phoenix copier");
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
		btnSend.setForeground(Color.WHITE);
		btnSend.setBackground(new Color(51, 51, 153));
		btnSend.setBounds(285, 74, 97, 23);
		contentPane.add(btnSend);
		
		textField = new JTextField();
		textField.addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent e) {
				if (e.getKeyCode()==KeyEvent.VK_ENTER){
					try{  
						conn=dbconnection.connect();
						int count=0;
					      //STEP 2: Register JDBC driver
					      Class.forName("com.mysql.jdbc.Driver");				      
					      //STEP 4: Execute a query
					      data.email=textField.getText();
					      stmt = conn.createStatement();
						  String sql = "SELECT * FROM login WHERE email='"+data.email+"'";
						  textField.setText("");
						  ResultSet rs = stmt.executeQuery(sql);
						  while(rs.next())
						  {
							  data.password=rs.getString("password");
							  count++;
						  }
						  if(count>0)
						  {
							  SendEmail.send();
							  JOptionPane.showMessageDialog(null,"Password has been send to your email");
						  }
						  else
						  {
							  System.out.println("Record not found");
							  JOptionPane.showMessageDialog(null,"Email not registered contact phoenix copier");
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
		textField.setBounds(117, 75, 144, 20);
		contentPane.add(textField);
		textField.setColumns(10);
		
		JLabel lblEmail = new JLabel("Email ");
		lblEmail.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblEmail.setBounds(46, 73, 46, 20);
		contentPane.add(lblEmail);
		
		JLabel lblPasswordWillBe = new JLabel("Password will be send to your registered email adress.");
		lblPasswordWillBe.setBounds(37, 129, 345, 14);
		contentPane.add(lblPasswordWillBe);
		
		JButton btnHome = new JButton("Home");
		btnHome.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				authenticate au=new authenticate();
				dispose();
				au.setVisible(true);
			}
		});
		btnHome.setForeground(Color.WHITE);
		btnHome.setBackground(new Color(51, 51, 153));
		btnHome.setBounds(37, 11, 89, 23);
		contentPane.add(btnHome);
		
		JLabel lblByAniket = new JLabel("BY ANIKET & OMKAR");
		lblByAniket.setBounds(266, 154, 134, 14);
		contentPane.add(lblByAniket);
		
		JLabel label = new JLabel("");
		label.setIcon(new ImageIcon(forgot.class.getResource("/xerox/back ground.jpeg")));
		label.setBounds(0, 0, 400, 179);
		contentPane.add(label);
		
		
	}
}
