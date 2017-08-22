package xerox;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import javax.swing.ImageIcon;
import java.awt.Font;
import java.awt.Color;
import java.awt.Toolkit;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class developers extends JFrame {

	private JPanel contentPane;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					developers frame = new developers();
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
	public developers() {
		setIconImage(Toolkit.getDefaultToolkit().getImage(developers.class.getResource("/xerox/Logo Transperent.png")));
		setTitle("PHOENIX PHOTOCOPIER");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 343, 322);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblDevelopers = new JLabel("Developers");
		lblDevelopers.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblDevelopers.setBounds(112, 11, 78, 22);
		contentPane.add(lblDevelopers);
		
		JLabel lblaniketBaburaoPatil = new JLabel("Aniket Baburao Patil");
		lblaniketBaburaoPatil.setForeground(new Color(51, 51, 153));
		lblaniketBaburaoPatil.setFont(new Font("Tahoma", Font.BOLD, 11));
		lblaniketBaburaoPatil.setBounds(92, 54, 143, 14);
		contentPane.add(lblaniketBaburaoPatil);
		
		JLabel lblEmail = new JLabel("E-mail : aniketpatil32@gmail.com");
		lblEmail.setBounds(92, 79, 210, 14);
		contentPane.add(lblEmail);
		
		JLabel lblMobile = new JLabel("Mobile  : +91 9145752925");
		lblMobile.setBounds(92, 104, 162, 14);
		contentPane.add(lblMobile);
		
		JLabel lblOmkarVasant = new JLabel("Omkar Vasant Erudkar.");
		lblOmkarVasant.setForeground(new Color(51, 51, 153));
		lblOmkarVasant.setFont(new Font("Tahoma", Font.BOLD, 11));
		lblOmkarVasant.setBounds(92, 145, 150, 14);
		contentPane.add(lblOmkarVasant);
		
		JLabel lblEmailOmkarerudakargmailcom = new JLabel("E-mail : omkarerudakar@gmail.com");
		lblEmailOmkarerudakargmailcom.setBounds(92, 171, 223, 14);
		contentPane.add(lblEmailOmkarerudakargmailcom);
		
		JLabel lblMobile_1 = new JLabel("Mobile : +91 9730372753");
		lblMobile_1.setBounds(92, 196, 171, 14);
		contentPane.add(lblMobile_1);
		
		JButton btnOk = new JButton("OK");
		btnOk.setBackground(new Color(51, 51, 153));
		btnOk.setForeground(Color.WHITE);
		btnOk.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				dispose();
			}
		});
		btnOk.setBounds(112, 237, 89, 23);
		contentPane.add(btnOk);
		
		JLabel lblNewLabel = new JLabel("");
		lblNewLabel.setForeground(Color.RED);
		lblNewLabel.setIcon(new ImageIcon(developers.class.getResource("/xerox/back ground.jpeg")));
		lblNewLabel.setBounds(0, 0, 327, 283);
		contentPane.add(lblNewLabel);
	}

}
