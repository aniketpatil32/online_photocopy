package xerox;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import net.proteanit.sql.DbUtils;
import xerox.authenticate.data;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.event.ActionListener;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.awt.event.ActionEvent;
import java.sql.*;
import javax.swing.ImageIcon;
import org.eclipse.wb.swing.FocusTraversalOnArray;
import java.awt.Component;
import java.awt.Toolkit;
import java.awt.Font;
import java.awt.Color;
public class admin extends JFrame {

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
	public static class print_queue{
		ResultSet rs;
		public static void print(){
			queue q=new queue();
			String status="no";
			try{
				  conn=dbconnection.connect();
			      //STEP 2: Register JDBC driver
			      Class.forName("com.mysql.jdbc.Driver");				      
			      //STEP 4: Execute a query
			     
				 stmt = conn.createStatement();
			     String sql= "SELECT id,username,filename,quantity FROM upload WHERE printed='"+status+"'";
			     ResultSet rs= stmt.executeQuery(sql);
			     q.getTable().setModel(DbUtils.resultSetToTableModel(rs));
			     q.setVisible(true);
			     q.textField_2.setText(data.username);
			     
			  
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
	
	
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					admin frame = new admin();
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
	public admin() {
		setIconImage(Toolkit.getDefaultToolkit().getImage(admin.class.getResource("/xerox/Logo Transperent.png")));
		setResizable(false);
		
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 557, 343);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		this.setTitle("PHOENIX COPIER");
		JButton btnShowData = new JButton("show all users");
		btnShowData.setForeground(Color.WHITE);
		btnShowData.setBackground(new Color(51, 51, 153));
		btnShowData.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try{
					  all_users au=new all_users();
					  conn=dbconnection.connect();
				      //STEP 2: Register JDBC driver
				      Class.forName("com.mysql.jdbc.Driver");				      
				      //STEP 4: Execute a query
				     
					 stmt = conn.createStatement();
				     String sql = "SELECT name,username,mobile,status,balance FROM login";
				     ResultSet rm = stmt.executeQuery(sql);
				    au.getTable().setModel(DbUtils.resultSetToTableModel(rm));
				    dispose();
				    au.setVisible(true);
				  
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
		btnShowData.setBounds(92, 107, 129, 23);
		contentPane.add(btnShowData);
		
		JButton btnConfirmAccount = new JButton("confirm account");
		btnConfirmAccount.setForeground(Color.WHITE);
		btnConfirmAccount.setBackground(new Color(51, 51, 153));
		btnConfirmAccount.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try
				{
					dispose();
					confirm c=new confirm();
					c.setVisible(true);
				}
				catch(Exception e1)
				{
					JOptionPane.showMessageDialog(null,"exceptions are there");
				}
			}
		});
		btnConfirmAccount.setBounds(324, 107, 129, 23);
		contentPane.add(btnConfirmAccount);
		
		JButton btnPrintQueue = new JButton("print queue");
		btnPrintQueue.setForeground(Color.WHITE);
		btnPrintQueue.setBackground(new Color(51, 51, 153));
		btnPrintQueue.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				  queue q=new queue();
				try{
					  String status="no";
					  conn=dbconnection.connect();
				      //STEP 2: Register JDBC driver
				      Class.forName("com.mysql.jdbc.Driver");				      
				      //STEP 4: Execute a query
				     
					 stmt = conn.createStatement();
				     String sql= "SELECT id,username,filename,quantity FROM upload WHERE printed='"+status+"'";
				     ResultSet rs= stmt.executeQuery(sql);
				     q.getTable().setModel(DbUtils.resultSetToTableModel(rs));
				     
				    dispose();
				    q.setVisible(true);
				  
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
		btnPrintQueue.setBounds(92, 152, 129, 23);
		contentPane.add(btnPrintQueue);
		
		JButton btnUpdateBalance = new JButton("update balance");
		btnUpdateBalance.setForeground(Color.WHITE);
		btnUpdateBalance.setBackground(new Color(51, 51, 153));
		btnUpdateBalance.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try
				{
					dispose();
					update_balance ab=new update_balance();
					ab.setVisible(true);
				}
				catch(Exception e1)
				{
					JOptionPane.showMessageDialog(null,"exceptions are there");
				}
			}
		});
		btnUpdateBalance.setBounds(324, 152, 129, 23);
		contentPane.add(btnUpdateBalance);
		
		JLabel lblWelcome = new JLabel("welcome Admin!!");
		lblWelcome.setBounds(381, 28, 109, 23);
		contentPane.add(lblWelcome);
		
		JButton btnLogout = new JButton("logout");
		btnLogout.setBackground(new Color(51, 51, 153));
		btnLogout.setForeground(Color.WHITE);
		btnLogout.setFont(new Font("Tahoma", Font.PLAIN, 11));
		btnLogout.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try{
					  authenticate ac=new authenticate();
					  conn=dbconnection.connect();
				      //STEP 2: Register JDBC driver
				      Class.forName("com.mysql.jdbc.Driver");				      
				      //STEP 4: Execute a query
				     
					 stmt = conn.createStatement();
				     String sql ="UPDATE `login` SET `status` = 'blocked' WHERE `login`.`username` = 'admin'";
				     stmt.executeUpdate(sql);
				    dispose();
				    ac.setVisible(true);
				  
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
		btnLogout.setBounds(92, 28, 129, 23);
		contentPane.add(btnLogout);
		
		JButton btnFindUser = new JButton("Find user");
		btnFindUser.setForeground(Color.WHITE);
		btnFindUser.setBackground(new Color(51, 51, 153));
		btnFindUser.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					show_users su=new show_users();
					dispose();
					su.setVisible(true);
				}catch(Exception e1)
				{
					JOptionPane.showMessageDialog(null,"Exception is  there");
				}
			}
		});
		btnFindUser.setBounds(92, 196, 130, 23);
		contentPane.add(btnFindUser);
		
		JButton btnUserDocument = new JButton("User Document");
		btnUserDocument.setForeground(Color.WHITE);
		btnUserDocument.setBackground(new Color(51, 51, 153));
		btnUserDocument.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				dispose();
				user_document ud=new user_document();
				ud.setVisible(true);
			}
		});
		btnUserDocument.setBounds(324, 196, 129, 23);
		contentPane.add(btnUserDocument);
		
		JButton btnHistory = new JButton("History");
		btnHistory.setForeground(Color.WHITE);
		btnHistory.setBackground(new Color(51, 51, 153));
		btnHistory.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try{
					  history h=new history();
					  conn=dbconnection.connect();
				      //STEP 2: Register JDBC driver
				      Class.forName("com.mysql.jdbc.Driver");				      
				      //STEP 4: Execute a query
				     
					 stmt = conn.createStatement();
				     String sql = "SELECT activity,date FROM history";
				     ResultSet rm = stmt.executeQuery(sql);
				     h.getTable().setModel(DbUtils.resultSetToTableModel(rm));
				    dispose();
				     h.setVisible(true);
				  
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
		btnHistory.setBounds(92, 240, 129, 23);
		contentPane.add(btnHistory);
		

		JLabel lblCredit = new JLabel("Credit");
		lblCredit.setBounds(381, 61, 46, 14);
		contentPane.add(lblCredit);
		
		JLabel label_1 = new JLabel("");
		label_1.setText(Integer.toString(data.total_income));
		label_1.setBounds(450, 61, 46, 14);
		contentPane.add(label_1);

		JLabel lblByAniket = new JLabel("BY ANIKET & OMKAR");
		lblByAniket.setBounds(381, 289, 145, 14);
		contentPane.add(lblByAniket);
		
		JLabel label = new JLabel("");
		label.setIcon(new ImageIcon(admin.class.getResource("/xerox/back ground.jpeg")));
		label.setBounds(0, 0, 551, 314);
		contentPane.add(label);
		
		
		
				
		
		
		setFocusTraversalPolicy(new FocusTraversalOnArray(new Component[]{contentPane, btnShowData, btnConfirmAccount, btnPrintQueue, btnUpdateBalance, lblWelcome, btnLogout, btnFindUser, label}));
	}
}
