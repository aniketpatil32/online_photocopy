package xerox;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import xerox.show_users.captured;

import javax.swing.JLabel;
import java.awt.Font;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.ImageIcon;
import java.awt.Toolkit;
import java.awt.Color;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class search_result extends JFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JPanel contentPane;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					search_result frame = new search_result();
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
	public search_result() {
		setIconImage(Toolkit.getDefaultToolkit().getImage(search_result.class.getResource("/xerox/Logo Transperent.png")));
		setResizable(false);
		this.setTitle("PHOENIX COPIER");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 539, 381);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblRecord = new JLabel("Record");
		lblRecord.setFont(new Font("Tahoma", Font.PLAIN, 16));
		lblRecord.setBounds(205, 34, 104, 14);
		contentPane.add(lblRecord);
		
		JLabel lblUsername = new JLabel("Username");
		lblUsername.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblUsername.setBounds(110, 87, 83, 14);
		contentPane.add(lblUsername);
		
		JLabel lblNewLabel = new JLabel("New label");
		lblNewLabel.setText(captured.username);
		lblNewLabel.setBounds(247, 87, 193, 14);
		contentPane.add(lblNewLabel);
		
		JLabel lblName = new JLabel("Name");
		lblName.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblName.setBounds(110, 125, 46, 14);
		contentPane.add(lblName);
		
		JLabel lblNewLabel_1 = new JLabel("New label");
		lblNewLabel_1.setText(captured.name);
		lblNewLabel_1.setBounds(247, 125, 193, 14);
		contentPane.add(lblNewLabel_1);
		
		JLabel lblMobile = new JLabel("Mobile");
		lblMobile.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblMobile.setBounds(110, 162, 46, 14);
		contentPane.add(lblMobile);
		
		JLabel lblNewLabel_2 = new JLabel("New label");
		lblNewLabel_2.setText(captured.mobile);
		lblNewLabel_2.setBounds(247, 162, 194, 14);
		contentPane.add(lblNewLabel_2);
		
		JLabel lblEmail = new JLabel("Email");
		lblEmail.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblEmail.setBounds(110, 195, 46, 14);
		contentPane.add(lblEmail);
		
		JLabel lblNewLabel_3 = new JLabel("New label");
		lblNewLabel_3.setText(captured.email);
		lblNewLabel_3.setBounds(247, 205, 193, 14);
		contentPane.add(lblNewLabel_3);
		
		JLabel lblBalance = new JLabel("Balance");
		lblBalance.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblBalance.setBounds(110, 230, 46, 14);
		contentPane.add(lblBalance);
		
		JLabel lblNewLabel_4 = new JLabel("New label");
		lblNewLabel_4.setText(captured.balance);
		lblNewLabel_4.setBounds(247, 230, 193, 14);
		contentPane.add(lblNewLabel_4);
		
		JLabel lblNewLabel_5 = new JLabel("Status");
		lblNewLabel_5.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblNewLabel_5.setBounds(110, 267, 46, 14);
		contentPane.add(lblNewLabel_5);
		
		JLabel lblNewLabel_6 = new JLabel("New label");
		lblNewLabel_6.setText(captured.status);
		lblNewLabel_6.setBounds(247, 267, 193, 14);
		contentPane.add(lblNewLabel_6);
		
		JLabel label_display = new JLabel("");
	    label_display.setBounds(247, 304, 193, 14);
		contentPane.add(label_display);
		
		JLabel lblPassword = new JLabel("Password");
		lblPassword.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				label_display.setText(captured.password);		
			}
		});
		lblPassword.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblPassword.setBounds(110, 304, 58, 14);
		contentPane.add(lblPassword);
		
		

		
		JButton btnHome = new JButton("Back");
		btnHome.setBackground(new Color(51, 51, 153));
		btnHome.setForeground(Color.WHITE);
		btnHome.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				dispose();
				show_users su=new show_users();
				su.setVisible(true);
			}
		});
		btnHome.setBounds(8, 11, 89, 23);
		contentPane.add(btnHome);
	
		JLabel lblAniketOmkar = new JLabel("BY ANIKET & OMKAR");
		lblAniketOmkar.setBounds(340, 327, 152, 14);
		contentPane.add(lblAniketOmkar);
		
		JLabel label = new JLabel("");
		label.setIcon(new ImageIcon(search_result.class.getResource("/xerox/back ground.jpeg")));
		label.setBounds(0, 0, 533, 352);
		contentPane.add(label);
		
		
		
		
		
		
		
		
	}
}
