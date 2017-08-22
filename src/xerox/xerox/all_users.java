package xerox;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.Statement;
import java.awt.event.ActionEvent;
import javax.swing.ImageIcon;
import java.awt.Toolkit;
import java.awt.Color;
import javax.swing.JTextField;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

public class all_users extends JFrame {

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
	
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					all_users frame = new all_users();
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
	public all_users() {
		setIconImage(Toolkit.getDefaultToolkit().getImage(all_users.class.getResource("/xerox/Logo Transperent.png")));
		setResizable(false);
		this.setTitle("PHOENIX COPIER");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 696, 417);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(82, 99, 556, 246);
		contentPane.add(scrollPane);
		
		setTable(new JTable());
		scrollPane.setViewportView(getTable());
		
		JLabel lblAllUsers = new JLabel("All Users");
		lblAllUsers.setFont(new Font("Tahoma", Font.PLAIN, 16));
		lblAllUsers.setBounds(306, 12, 116, 24);
		contentPane.add(lblAllUsers);
		
		JButton btnHome = new JButton("Home");
		btnHome.setBackground(new Color(51, 51, 153));
		btnHome.setForeground(Color.WHITE);
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
		btnHome.setFont(new Font("Tahoma", Font.PLAIN, 11));
		btnHome.setBounds(28, 14, 89, 23);
		contentPane.add(btnHome);
		
		JLabel lblByAniket = new JLabel("BY ANIKET & OMKAR");
		lblByAniket.setBounds(538, 361, 152, 14);
		contentPane.add(lblByAniket);
		
		JLabel lblUsername = new JLabel("Username : ");
		lblUsername.setBounds(462, 19, 77, 14);
		contentPane.add(lblUsername);
		
		textField = new JTextField();
		textField.addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent e) {
				if (e.getKeyCode()==KeyEvent.VK_ENTER){
					
					try{
						Connection conn = dbconnection.connect();
						String sql,username;
						stmt=conn.createStatement();
						username=textField.getText();
						sql="delete from login where username='"+username+"'";
						stmt.executeUpdate(sql);
						JOptionPane.showMessageDialog(null, "'"+username+"' was deleted");
						textField.setText("");
						
					}catch(Exception ex){

					
						JOptionPane.showMessageDialog(null, "user not found");
						textField.setText("");
						
					}
					
				}
			}
		});
		textField.setBounds(549, 16, 120, 20);
		contentPane.add(textField);
		textField.setColumns(10);
		
		JButton btnDeleteUser = new JButton("Delete User");
		btnDeleteUser.setForeground(Color.WHITE);
		btnDeleteUser.setBackground(new Color(51, 51, 153));
		btnDeleteUser.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try{
					Connection conn = dbconnection.connect();
					String sql,username;
					stmt=conn.createStatement();
					username=textField.getText();
					sql="delete from login where username='"+username+"'";
					stmt.executeUpdate(sql);
					JOptionPane.showMessageDialog(null, "'"+username+"' was deleted");
					textField.setText("");
					
				}catch(Exception ex){

				
					JOptionPane.showMessageDialog(null, "user not found");
					textField.setText("");
					
				}
				
			}
		});
		btnDeleteUser.setBounds(509, 54, 120, 23);
		contentPane.add(btnDeleteUser);
		
		JLabel label = new JLabel("");
		label.setIcon(new ImageIcon(all_users.class.getResource("/xerox/back ground.jpeg")));
		label.setBounds(0, 0, 690, 388);
		contentPane.add(label);
		
		
	}

	public JTable getTable() {
		return table;
	}

	public void setTable(JTable table) {
		this.table = table;
	}
}
