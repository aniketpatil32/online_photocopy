package xerox;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import java.awt.Font;
import javax.swing.ImageIcon;
import java.awt.Toolkit;
import javax.swing.JButton;
import java.awt.Color;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class manual extends JFrame {

	private JPanel contentPane;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					manual frame = new manual();
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
	public manual() {
		setTitle("PHOENIX PHOTOCOPIER");
		setIconImage(Toolkit.getDefaultToolkit().getImage(manual.class.getResource("/xerox/Logo Transperent.png")));
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 454, 348);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblUserManual = new JLabel("User Manual");
		lblUserManual.setFont(new Font("Tahoma", Font.PLAIN, 16));
		lblUserManual.setBounds(158, 11, 100, 14);
		contentPane.add(lblUserManual);
		
		JLabel lblifYouAre = new JLabel("1. If you are not a member then register first.");
		lblifYouAre.setBounds(60, 124, 314, 14);
		contentPane.add(lblifYouAre);
		
		JLabel lblafterLoginPress = new JLabel("2. After login press choose file button to select your file.");
		lblafterLoginPress.setBounds(60, 149, 346, 14);
		contentPane.add(lblafterLoginPress);
		
		JLabel lblsaveYourDocuments = new JLabel("3. Save your documents using save button.");
		lblsaveYourDocuments.setBounds(60, 174, 266, 14);
		contentPane.add(lblsaveYourDocuments);
		
		JLabel lblyouCanSee = new JLabel("4. You can view & edit your account details using account button.");
		lblyouCanSee.setBounds(60, 199, 374, 14);
		contentPane.add(lblyouCanSee);
		
		JLabel lblonlySavedDocuments = new JLabel("5. Only saved documents can be accessed later.");
		lblonlySavedDocuments.setBounds(60, 224, 314, 14);
		contentPane.add(lblonlySavedDocuments);
		
		JLabel label = new JLabel("");
		label.setIcon(new ImageIcon(manual.class.getResource("/xerox/logo manual.png")));
		label.setBounds(158, 30, 89, 83);
		contentPane.add(label);
		
		JButton btnHome = new JButton("Ok");
		btnHome.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				dispose();
			
			}
		});
		btnHome.setBackground(new Color(51, 51, 153));
		btnHome.setForeground(Color.WHITE);
		btnHome.setBounds(145, 251, 89, 23);
		contentPane.add(btnHome);
		
		JLabel lblByAniket = new JLabel("BY ANIKET & OMKAR");
		lblByAniket.setBounds(304, 284, 124, 14);
		contentPane.add(lblByAniket);
		
		
		JLabel label_1 = new JLabel("");
		label_1.setIcon(new ImageIcon(manual.class.getResource("/xerox/back ground.jpeg")));
		label_1.setBounds(0, 0, 434, 309);
		contentPane.add(label_1);
		
		
	}
}
