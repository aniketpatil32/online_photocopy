package xerox;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import net.proteanit.sql.DbUtils;
import xerox.authenticate.data;
import xerox.user.upload;

import javax.swing.JTextField;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JTextPane;
import javax.swing.JSplitPane;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.awt.event.ActionEvent;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.Toolkit;
import javax.swing.ImageIcon;
import java.awt.Color;

public class user_document extends JFrame {

	private JPanel contentPane;
	private JTextField textField;
	private JTextField textField_1;
	private JTextField textField_2;

	/**
	 * Launch the application.
	 */
	Connection conn=null;
	Statement stmt=null;
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					user_document frame = new user_document();
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
	public user_document() {
		setResizable(false);
		setIconImage(Toolkit.getDefaultToolkit().getImage(user_document.class.getResource("/xerox/Logo Transperent.png")));
		this.setTitle("PHOENIX COPIER");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 346, 356);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		textField = new JTextField();
		textField.setBounds(174, 77, 115, 20);
		contentPane.add(textField);
		textField.setColumns(10);
		
		JFileChooser fileChooser = new JFileChooser();
		fileChooser.setBounds(0, -33, 582, 397);
		
		JButton btnChooseFile = new JButton("Choose file");
		btnChooseFile.setBackground(new Color(51, 51, 153));
		btnChooseFile.setForeground(Color.WHITE);
		btnChooseFile.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				JFrame frame = new JFrame();
				int returnVal=fileChooser.showOpenDialog(frame);
				
				 if (returnVal == JFileChooser.APPROVE_OPTION) {
					 upload.file = fileChooser.getSelectedFile();
		             System.out.println(upload.file.getName());
		             textField_1.setText(upload.file.getName());
		            }
		            else{
		                          
		            	JOptionPane.showMessageDialog(null,"File not selected");
		            }      
			}
		});
		btnChooseFile.setBounds(37, 122, 103, 23);
		contentPane.add(btnChooseFile);
		
		JButton btnUsername = new JButton("Username");
		btnUsername.setBackground(new Color(51, 51, 153));
		btnUsername.setForeground(Color.WHITE);
		btnUsername.setBounds(37, 76, 103, 23);
		contentPane.add(btnUsername);
		
		textField_1 = new JTextField();
		textField_1.setBounds(174, 123, 115, 20);
		contentPane.add(textField_1);
		textField_1.setColumns(10);
		
		JButton btnSava = new JButton("Save");
		btnSava.setBackground(new Color(51, 51, 153));
		btnSava.setForeground(Color.WHITE);
		btnSava.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				 data.username=textField.getText();
				 conn=dbconnection.connect();
       	         document_upload.document();
       	         textField.setText("");
       	         textField_1.setText("");
			}
		});
		btnSava.setBounds(118, 167, 89, 23);
		contentPane.add(btnSava);
		
		JButton btnUsername_1 = new JButton("Username");
		btnUsername_1.setBackground(new Color(51, 51, 153));
		btnUsername_1.setForeground(Color.WHITE);
		btnUsername_1.setBounds(37, 227, 103, 23);
		contentPane.add(btnUsername_1);
		
		textField_2 = new JTextField();
		textField_2.addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent e) {
				 if (e.getKeyCode()==KeyEvent.VK_ENTER){
					 try{
						  document_view dv=new document_view();
						  data.username=textField_2.getText();
						  conn=dbconnection.connect();
					      //STEP 2: Register JDBC driver
					      Class.forName("com.mysql.jdbc.Driver");				      
					      //STEP 4: Execute a query
					     
						 stmt = conn.createStatement();
					     String sql= "SELECT id,username,filename FROM document WHERE username='"+data.username+"'";
					     ResultSet rs= stmt.executeQuery(sql);
					     dv.getTable().setModel(DbUtils.resultSetToTableModel(rs));
					     
					    dispose();
					    dv.setVisible(true);
					  
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
		});
		textField_2.setBounds(174, 230, 115, 20);
		contentPane.add(textField_2);
		textField_2.setColumns(10);
		
		JButton btnView = new JButton("View");
		btnView.setBackground(new Color(51, 51, 153));
		btnView.setForeground(Color.WHITE);
		btnView.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try{
					  document_view dv=new document_view();
					  data.username=textField_2.getText();
					  conn=dbconnection.connect();
				      //STEP 2: Register JDBC driver
				      Class.forName("com.mysql.jdbc.Driver");				      
				      //STEP 4: Execute a query
				     
					 stmt = conn.createStatement();
				     String sql= "SELECT id,username,filename FROM document WHERE username='"+data.username+"'";
				     ResultSet rs= stmt.executeQuery(sql);
				     dv.getTable().setModel(DbUtils.resultSetToTableModel(rs));
				     
				    dispose();
				    dv.setVisible(true);
				  
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
		btnView.setBounds(118, 268, 89, 23);
		contentPane.add(btnView);
		
		JLabel lblUserDocument = new JLabel("User Document");
		lblUserDocument.setFont(new Font("Tahoma", Font.PLAIN, 16));
		lblUserDocument.setBounds(118, 11, 136, 20);
		contentPane.add(lblUserDocument);
	
		JButton btnHome = new JButton("Home");
		btnHome.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				dispose();
				admin ad=new admin();
				ad.setVisible(true);
			}
		});
		btnHome.setBackground(new Color(51, 51, 153));
		btnHome.setForeground(Color.WHITE);
		btnHome.setBounds(10, 12, 89, 23);
		contentPane.add(btnHome);
		
		JLabel lblAniketOmkar = new JLabel("BY ANIKET & OMKAR");
		lblAniketOmkar.setBounds(174, 302, 141, 14);
		contentPane.add(lblAniketOmkar);
		
		JLabel label = new JLabel("");
		label.setIcon(new ImageIcon(user_document.class.getResource("/xerox/back ground.jpeg")));
		label.setBounds(0, 0, 340, 327);
		contentPane.add(label);
		
		
		
	
	}
}
