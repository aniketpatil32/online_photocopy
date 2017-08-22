package xerox;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import java.awt.Font;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import java.awt.Color;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.Toolkit;

public class contact extends JFrame {

	private JPanel contentPane;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					contact frame = new contact();
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
	public contact() {
		setTitle("PHOENIX PHOTOCOPIER");
		setIconImage(Toolkit.getDefaultToolkit().getImage(contact.class.getResource("/xerox/Logo Transperent.png")));
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 469, 298);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblAddress = new JLabel("Opp. Government Pharmacy College,Vidyanagar,Karad.415124.");
		lblAddress.setBounds(83, 129, 357, 14);
		contentPane.add(lblAddress);
		
		JLabel lblContacts = new JLabel("Contacts");
		lblContacts.setFont(new Font("Tahoma", Font.PLAIN, 16));
		lblContacts.setBounds(163, 11, 75, 14);
		contentPane.add(lblContacts);
		
		JLabel lblAddress_1 = new JLabel("Address :");
		lblAddress_1.setBounds(27, 129, 59, 14);
		contentPane.add(lblAddress_1);
		
		JLabel lblNewLabel = new JLabel("Website :");
		lblNewLabel.setBounds(27, 154, 59, 14);
		contentPane.add(lblNewLabel);
		
		JLabel lblNewLabel_1 = new JLabel("www.phoenixcopier.com");
		lblNewLabel_1.setBounds(83, 154, 171, 14);
		contentPane.add(lblNewLabel_1);
		
		JLabel lblMobile = new JLabel("Mobile :");
		lblMobile.setBounds(27, 179, 46, 14);
		contentPane.add(lblMobile);
		
		JLabel lblNewLabel_2 = new JLabel("+91 8275660002");
		lblNewLabel_2.setBounds(83, 179, 103, 14);
		contentPane.add(lblNewLabel_2);
		
		JLabel label = new JLabel("");
		label.setIcon(new ImageIcon(contact.class.getResource("/xerox/logo manual.png")));
		label.setBounds(163, 35, 89, 83);
		contentPane.add(label);
		
		JButton btnOk = new JButton("OK");
		btnOk.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				dispose();
			}
		});
		btnOk.setForeground(Color.WHITE);
		btnOk.setBackground(new Color(51, 51, 153));
		btnOk.setBounds(163, 225, 89, 23);
		contentPane.add(btnOk);
		
		JLabel label_1 = new JLabel("");
		label_1.setIcon(new ImageIcon(contact.class.getResource("/xerox/back ground.jpeg")));
		label_1.setBounds(0, 0, 453, 259);
		contentPane.add(label_1);
		
		
	}
}
