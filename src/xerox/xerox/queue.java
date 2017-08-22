package xerox;

import java.awt.Desktop;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import xerox.admin.print_queue;
import xerox.authenticate.data;

import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
import javax.swing.JTable;
import javax.swing.JScrollPane;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.awt.event.ActionEvent;
import javax.swing.JTextField;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import javax.swing.ImageIcon;
import java.awt.Toolkit;
import java.awt.Color;

public class queue extends JFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTable table;
	private JTextField textField;

	/**
	 * Launch the application.
	 */
	Connection conn=null;
	Statement stmt=null;
	private JTextField textField_1;
	JTextField textField_2;
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					queue frame = new queue();
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
	public queue() {
		setIconImage(Toolkit.getDefaultToolkit().getImage(queue.class.getResource("/xerox/Logo Transperent.png")));
		setResizable(false);
		this.setTitle("PHOENIX COPIER");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 604, 478);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblPrint = new JLabel("Print");
		lblPrint.setFont(new Font("Tahoma", Font.PLAIN, 16));
		lblPrint.setBounds(259, 22, 46, 14);
		contentPane.add(lblPrint);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(73, 195, 471, 217);
		contentPane.add(scrollPane);
		
		table = new JTable();
		scrollPane.setViewportView(table);
		
		JButton btnHome = new JButton("Home");
		btnHome.setForeground(Color.WHITE);
		btnHome.setBackground(new Color(51, 51, 153));
		btnHome.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try{
					admin ad=new admin();
					dispose();
					ad.setVisible(true);
				}catch(Exception e1)
				{
					JOptionPane.showMessageDialog(null,"Exception is there");
				}
			}
		});
		btnHome.setBounds(21, 20, 89, 23);
		contentPane.add(btnHome);
		
		JButton btnPrint = new JButton("Print");
		btnPrint.setBackground(new Color(51, 51, 153));
		btnPrint.setForeground(Color.WHITE);
		btnPrint.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				InputStream input=null;
				FileOutputStream output=null;
				try {
					String file_name = null,status="yes",temp,username = null;
					int temp1;
					temp=textField.getText();
					textField.setText("");
					temp1=Integer.parseInt(temp);
				   // 1. Get a connection to database
				    conn= dbconnection.connect();
		     
					// 2. Prepare statement
				    
				    String sql="SELECT username,file,filename FROM upload WHERE id='"+temp1+"'";
				    Statement stmt= conn.prepareStatement(sql);
		             // 4. Execute statemenent
					 ResultSet rs = stmt.executeQuery(sql);
					 while(rs.next())
					 {
						 file_name=rs.getString("filename");
						 input=rs.getBinaryStream("file");
						 username=rs.getString("username");
						 data.username=username;
					 }
					 System.out.println(file_name);
					 byte buffer[] = new byte[2048];
					 File file=new File(file_name);
					 output=new FileOutputStream(file);
					 while(input.read(buffer)>0){
						 output.write(buffer);
						}
					 output.close();
					 input.close();
                    System.out.println("file stored at"+file.getAbsolutePath());
                    Desktop.getDesktop().open(file);  
                    
                    
                    
                    stmt= conn.prepareStatement(sql);
                    sql= "UPDATE upload "
                    		+ "SET printed='"+status+"' WHERE id='"+temp1+"'";
                    stmt.executeUpdate(sql);
                    
                    
                  //history
                    String temp2;
                    temp2=Integer.toString(temp1);
                    java.util.Date date = new java.util.Date();
                    data.history=data.username+""+"s file was printed havind id: "+temp2;
				      stmt = conn.createStatement();
					  sql="INSERT INTO history(username,activity,date)"
					  		+ "VALUES('"+data.username+"','"+data.history+"','"+date+"')";
					     stmt.executeUpdate(sql);
					     //refresh frame
					     dispose();
						 print_queue.print();
						 
						 textField_2.setText(data.username);
                 /*   FileInputStream textStream;
                    textStream = new FileInputStream(file);

                    DocFlavor flavor = DocFlavor.INPUT_STREAM.AUTOSENSE;
                    Doc mydoc = new SimpleDoc(textStream, flavor, null);
                    PrintRequestAttributeSet aset = new HashPrintRequestAttributeSet();

                       PrintService[] services = PrintServiceLookup.lookupPrintServices(
                    				flavor, aset);
                       PrintService defaultService = PrintServiceLookup.lookupDefaultPrintService();

                       if(services.length == 0) {
                           if(defaultService == null) {
                                 //no printer found

                           } else {
                                //print using default
                                DocPrintJob job = defaultService.createPrintJob();
                                job.print(mydoc, aset);

                           }

                        } else {

                           //built in UI for printing you may not use this
                           PrintService service = ServiceUI.printDialog(null, 200, 200, services, defaultService, flavor, aset);


                            if (service != null)
                            {
                               DocPrintJob job = service.createPrintJob();
                               job.print(mydoc, aset);
                            }

                        }*/
				  } catch (Exception exc) {
					  exc.printStackTrace();
					  // JOptionPane.showMessageDialog(null, exc);
					  JOptionPane.showMessageDialog(null,"Run application as administrator");
				} finally {		
					if (input != null) {
						try {
							input.close();
						} catch (IOException e1) {
							// TODO Auto-generated catch block
							e1.printStackTrace();
						}
					}
					try {
						if(conn!=null)
						{
							
			             conn.close();
						}
						if(stmt!=null)
						{
			             stmt.close();
						}
					} catch (SQLException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
					textField.setText("");
				}
			}
			
		});
		btnPrint.setBounds(140, 161, 89, 23);
		contentPane.add(btnPrint);
		
		JLabel lblEnterIndex = new JLabel("Enter Index");
		lblEnterIndex.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblEnterIndex.setBounds(30, 114, 80, 14);
		contentPane.add(lblEnterIndex);
		
		textField = new JTextField();
		textField.addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent e) {
			    if (e.getKeyCode()==KeyEvent.VK_ENTER){
			    	InputStream input=null;
					FileOutputStream output=null;
					try {
						String file_name = null,status="yes",temp,username = null;
						int temp1;
						temp=textField.getText();
						textField.setText("");
						temp1=Integer.parseInt(temp);
					   // 1. Get a connection to database
					    conn= dbconnection.connect();
			     
						// 2. Prepare statement
					    
					    String sql="SELECT username,file,filename FROM upload WHERE id='"+temp1+"'";
					    Statement stmt= conn.prepareStatement(sql);
			             // 4. Execute statemenent
						 ResultSet rs = stmt.executeQuery(sql);
						 while(rs.next())
						 {
							 file_name=rs.getString("filename");
							 input=rs.getBinaryStream("file");
							 username=rs.getString("username");
							 data.username=username;
						 }
						 System.out.println(file_name);
						 byte buffer[] = new byte[2048];
						 File file=new File(file_name);
						 output=new FileOutputStream(file);
						 while(input.read(buffer)>0){
							 output.write(buffer);
							}
						 output.close();
						 input.close();
	                    System.out.println("file stored at"+file.getAbsolutePath());
	                    Desktop.getDesktop().open(file);  
	                    
	                    
	                    
	                    stmt= conn.prepareStatement(sql);
	                    sql= "UPDATE upload "
	                    		+ "SET printed='"+status+"' WHERE id='"+temp1+"'";
	                    stmt.executeUpdate(sql);
	                    textField_2.setText(username);
	                    
	                  //history
	                    String temp2;
	                    temp2=Integer.toString(temp1);
	                    java.util.Date date = new java.util.Date();
	                    data.history=data.username+""+"s file was printed havind id: "+temp2;
					      stmt = conn.createStatement();
						  sql="INSERT INTO history(username,activity,date)"
						  		+ "VALUES('"+data.username+"','"+data.history+"','"+date+"')";
						     stmt.executeUpdate(sql);
						     
						     //refresh frame
						     
						     dispose();
							 print_queue.print();
							 textField_2.setText(data.username);
	                 /*   FileInputStream textStream;
	                    textStream = new FileInputStream(file);

	                    DocFlavor flavor = DocFlavor.INPUT_STREAM.AUTOSENSE;
	                    Doc mydoc = new SimpleDoc(textStream, flavor, null);
	                    PrintRequestAttributeSet aset = new HashPrintRequestAttributeSet();

	                       PrintService[] services = PrintServiceLookup.lookupPrintServices(
	                    				flavor, aset);
	                       PrintService defaultService = PrintServiceLookup.lookupDefaultPrintService();

	                       if(services.length == 0) {
	                           if(defaultService == null) {
	                                 //no printer found

	                           } else {
	                                //print using default
	                                DocPrintJob job = defaultService.createPrintJob();
	                                job.print(mydoc, aset);

	                           }

	                        } else {

	                           //built in UI for printing you may not use this
	                           PrintService service = ServiceUI.printDialog(null, 200, 200, services, defaultService, flavor, aset);


	                            if (service != null)
	                            {
	                               DocPrintJob job = service.createPrintJob();
	                               job.print(mydoc, aset);
	                            }

	                        }*/
					  } catch (Exception exc) {
						  exc.printStackTrace();
						   //JOptionPane.showMessageDialog(null, exc);
						  JOptionPane.showMessageDialog(null,"Run application as administrator");
					} finally {		
						if (input != null) {
							try {
								input.close();
							} catch (IOException e1) {
								// TODO Auto-generated catch block
								e1.printStackTrace();
							}
						}
						try {
							if(conn!=null)
							{
								
				             conn.close();
							}
							if(stmt!=null)
							{
				             stmt.close();
							}
						} catch (SQLException e1) {
							// TODO Auto-generated catch block
							e1.printStackTrace();
						}
						textField.setText("");
					}
			    }
			}
		});
		textField.setBounds(140, 113, 89, 20);
		contentPane.add(textField);
		textField.setColumns(10);
		
		JButton btnRefresh = new JButton("Refresh");
		btnRefresh.setBackground(new Color(51, 51, 153));
		btnRefresh.setForeground(Color.WHITE);
		btnRefresh.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			   dispose();
			   print_queue.print();
			   
			}
		});
		
		btnRefresh.setBounds(471, 20, 89, 23);
		contentPane.add(btnRefresh);
		
		textField_1 = new JTextField();
		textField_1.addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent e) {
				if (e.getKeyCode()==KeyEvent.VK_ENTER){
					try{
						  conn=dbconnection.connect();
					      //STEP 2: Register JDBC driver
					      Class.forName("com.mysql.jdbc.Driver");				      
					      //STEP 4: Execute a query
					     int balance,old_balance = 0,bal;
					     String temp,username;
					     temp=textField_1.getText();
					     username=textField_2.getText();
					     balance=Integer.parseInt(temp);
					     bal=balance;
						 stmt = conn.createStatement();
					     String sql = "SELECT balance "
					     		+ "FROM login "
					     		+ "WHERE username='"+username+"'";
					     ResultSet rs = stmt.executeQuery(sql);
					      while(rs.next())
					      {
					    	 old_balance=rs.getInt("balance"); 
					      }
					      System.out.println("old balance is"+old_balance);
					 if(old_balance>=balance)
					 {
					      balance=old_balance-balance;
					      System.out.println("New balance is:"+balance);
					      stmt = conn.createStatement();
					      sql = "UPDATE login  SET balance="+balance+" WHERE username = '"+username+"'";
		                  stmt.executeUpdate(sql);
					      JOptionPane.showMessageDialog(null,"your account has been Debited");
					      java.util.Date date = new java.util.Date();
					      data.history=bal+" "+"Rupees were credited in the account of "+data.username;
					      stmt = conn.createStatement();
						  sql ="INSERT INTO history(username,activity,date)"
						  		+ "VALUES('"+data.username+"','"+data.history+"','"+date+"')";
						     stmt.executeUpdate(sql);
					 }
					 else
					 {
						 JOptionPane.showMessageDialog(null,"Deduction amount exceeds over balance");
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
				
			}
		});
		textField_1.setBounds(423, 113, 86, 20);
		contentPane.add(textField_1);
		textField_1.setColumns(10);
		
		JButton btnDeduct = new JButton("Deduct");
		btnDeduct.setBackground(new Color(51, 51, 153));
		btnDeduct.setForeground(Color.WHITE);
		btnDeduct.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try{
					  conn=dbconnection.connect();
				      //STEP 2: Register JDBC driver
				      Class.forName("com.mysql.jdbc.Driver");				      
				      //STEP 4: Execute a query
				     int balance,old_balance = 0,bal;
				     String temp,username;
				     temp=textField_1.getText();
				     username=textField_2.getText();
				     balance=Integer.parseInt(temp);
				     bal=balance;
					 stmt = conn.createStatement();
				     String sql = "SELECT balance "
				     		+ "FROM login "
				     		+ "WHERE username='"+username+"'";
				     ResultSet rs = stmt.executeQuery(sql);
				      while(rs.next())
				      {
				    	 old_balance=rs.getInt("balance"); 
				      }
				      System.out.println("old balance is"+old_balance);
				 if(old_balance>=balance)
				 {
				      balance=old_balance-balance;
				      System.out.println("New balance is:"+balance);
				      stmt = conn.createStatement();
				      sql = "UPDATE login  SET balance="+balance+" WHERE username = '"+username+"'";
	                  stmt.executeUpdate(sql);
				      JOptionPane.showMessageDialog(null,"your account has been Debited");
				      java.util.Date date = new java.util.Date();
				      data.history=bal+" "+"Rupees were credited in the account of "+data.username;
				      stmt = conn.createStatement();
					  sql ="INSERT INTO history(username,activity,date)"
					  		+ "VALUES('"+data.username+"','"+data.history+"','"+date+"')";
					     stmt.executeUpdate(sql);
				 }
				 else
				 {
					 JOptionPane.showMessageDialog(null,"Deduction amount exceeds over balance");
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
		btnDeduct.setBounds(424, 161, 89, 23);
		contentPane.add(btnDeduct);
		
		textField_2 = new JTextField();
		textField_2.setBounds(423, 71, 86, 20);
		contentPane.add(textField_2);
		textField_2.setColumns(10);
		
		JLabel lblUsername = new JLabel("Username");
		lblUsername.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblUsername.setBounds(338, 72, 65, 14);
		contentPane.add(lblUsername);
		
		JLabel lblAmount = new JLabel("Amount");
		lblAmount.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblAmount.setBounds(338, 115, 65, 14);
		contentPane.add(lblAmount);
		
		JLabel lblAniketOmkar = new JLabel("BY ANIKET & OMKAR");
		lblAniketOmkar.setBounds(423, 423, 130, 14);
		contentPane.add(lblAniketOmkar);
		
		
		JLabel label = new JLabel("");
		label.setIcon(new ImageIcon(queue.class.getResource("/xerox/back ground.jpeg")));
		label.setBounds(0, 0, 598, 449);
		contentPane.add(label);
		
		
		
		
		
	}
	public JTable getTable() {
		return table;
	}

	public void setTable(JTable table) {
		this.table = table;
	}
}
