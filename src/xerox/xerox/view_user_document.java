package xerox;

import java.awt.Desktop;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import xerox.admin.print_queue;
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
import java.awt.event.ActionEvent;
import javax.swing.JTextField;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import javax.swing.ImageIcon;
import java.awt.Toolkit;
import java.awt.Color;

public class view_user_document extends JFrame {

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
	PreparedStatement stmt=null;
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
	public view_user_document() {
		setIconImage(Toolkit.getDefaultToolkit().getImage(view_user_document.class.getResource("/xerox/Logo Transperent.png")));
		setResizable(false);
		this.setTitle("PHOENIX COPIER");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 586, 361);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblPrint = new JLabel("Documents");
		lblPrint.setFont(new Font("Tahoma", Font.PLAIN, 16));
		lblPrint.setBounds(237, 22, 102, 14);
		contentPane.add(lblPrint);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(71, 104, 444, 170);
		contentPane.add(scrollPane);
		
		table = new JTable();
		scrollPane.setViewportView(table);
		
		JButton btnHome = new JButton("Home");
		btnHome.setBackground(new Color(51, 51, 153));
		btnHome.setForeground(Color.WHITE);
		btnHome.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try{
					user us=new user();
					dispose();
					us.setVisible(true);
				}catch(Exception e1)
				{
					JOptionPane.showMessageDialog(null,"Exception is there");
				}
			}
		});
		btnHome.setBounds(21, 20, 89, 23);
		contentPane.add(btnHome);
		
		JButton btnPrint = new JButton("view");
		btnPrint.setBackground(new Color(51, 51, 153));
		btnPrint.setForeground(Color.WHITE);
		btnPrint.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				InputStream input=null;
				FileOutputStream output=null;
				try {
					String file_name = null,temp,username = null;
					int temp1;
					temp=textField.getText();
					textField.setText("");
					temp1=Integer.parseInt(temp);
				   // 1. Get a connection to database
				    conn= dbconnection.connect();
		     
					// 2. Prepare statement
				   // index search  
				    String sql="SELECT username,file,filename FROM document WHERE id='"+temp1+"'";
				    PreparedStatement stmt= conn.prepareStatement(sql);
		             // 4. Execute statemenent
					 ResultSet rs = stmt.executeQuery(sql);
					 while(rs.next())
					 {
						 file_name=rs.getString("filename");
						 input=rs.getBinaryStream("file");
						 username=rs.getString("username");
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
                    JOptionPane.showMessageDialog(null,"file stored at"+file.getAbsolutePath());
                    Desktop.getDesktop().open(file);  
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
			
		});
		btnPrint.setBounds(369, 70, 102, 23);
		contentPane.add(btnPrint);
		
		JLabel lblEnterIndex = new JLabel("Enter Index");
		lblEnterIndex.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblEnterIndex.setBounds(103, 74, 80, 14);
		contentPane.add(lblEnterIndex);
		
		textField = new JTextField();
		textField.addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent e) {
			    if (e.getKeyCode()==KeyEvent.VK_ENTER){
			    	InputStream input=null;
					FileOutputStream output=null;
					try {
						String file_name = null,temp,username = null;
						int temp1;
						temp=textField.getText();
						textField.setText("");
						temp1=Integer.parseInt(temp);
					   // 1. Get a connection to database
					    conn= dbconnection.connect();
			     
						// 2. Prepare statement
					    
					    String sql="SELECT username,file,filename FROM document WHERE id='"+temp1+"'";
					    PreparedStatement stmt= conn.prepareStatement(sql);
			             // 4. Execute statemenent
						 ResultSet rs = stmt.executeQuery(sql);
						 while(rs.next())
						 {
							 file_name=rs.getString("filename");
							 input=rs.getBinaryStream("file");
							 username=rs.getString("username");
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
	                    JOptionPane.showMessageDialog(null,"file stored at"+file.getAbsolutePath());
	                    Desktop.getDesktop().open(file);  
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
		textField.setBounds(237, 71, 86, 20);
		contentPane.add(textField);
		textField.setColumns(10);
		
		JLabel lblAniketOmkar = new JLabel("BY ANIKET & OMKAR");
		lblAniketOmkar.setBounds(398, 307, 138, 14);
		contentPane.add(lblAniketOmkar);
		
		JLabel label = new JLabel("");
		label.setIcon(new ImageIcon(view_user_document.class.getResource("/xerox/back ground.jpeg")));
		label.setBounds(0, 0, 580, 332);
		contentPane.add(label);
		
		
	}
	public JTable getTable() {
		return table;
	}

	public void setTable(JTable table) {
		this.table = table;
	}
}
