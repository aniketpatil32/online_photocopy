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

public class terms extends JFrame {

	private JPanel contentPane;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					terms frame = new terms();
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
	public terms() {
		setTitle("PHOENIX PHOTOCOPIER");
		setIconImage(Toolkit.getDefaultToolkit().getImage(terms.class.getResource("/xerox/Logo Transperent.png")));
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 557, 329);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblTermsConditons = new JLabel("Terms & Conditions");
		lblTermsConditons.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblTermsConditons.setBounds(174, 11, 125, 14);
		contentPane.add(lblTermsConditons);
		
		JLabel lblkeepBackupOf = new JLabel("1. Keep backup of your files/data as it may be lost in case of meltdown of server.");
		lblkeepBackupOf.setBounds(73, 136, 458, 14);
		contentPane.add(lblkeepBackupOf);
		
		JLabel lbluploadingCopyrightMaterials = new JLabel("2. Uploading copyright materials {Books,Images,Pdf etc.) is prohibited.");
		lbluploadingCopyrightMaterials.setBounds(73, 161, 432, 14);
		contentPane.add(lbluploadingCopyrightMaterials);
		
		JLabel lbluploadingOfPornographic = new JLabel("3. Uploading of pornographic material is strictly prohibited.");
		lbluploadingOfPornographic.setBounds(74, 186, 413, 14);
		contentPane.add(lbluploadingOfPornographic);
		
		JLabel lblallRightTo = new JLabel("4. All right to declarative your account will be kept by Admin only.");
		lblallRightTo.setBounds(74, 211, 413, 14);
		contentPane.add(lblallRightTo);
		
		JLabel lblthisAppIs = new JLabel("5. This App is for personal use only within the karad city.");
		lblthisAppIs.setBounds(74, 236, 388, 14);
		contentPane.add(lblthisAppIs);
		
		JLabel label = new JLabel("");
		label.setIcon(new ImageIcon(terms.class.getResource("/xerox/logo manual.png")));
		label.setBounds(195, 36, 89, 83);
		contentPane.add(label);
		
		JButton btnBack = new JButton("Ok");
		btnBack.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				dispose();
				
			}
		});
		btnBack.setForeground(Color.WHITE);
		btnBack.setBackground(new Color(51, 51, 153));
		btnBack.setBounds(195, 261, 89, 23);
		contentPane.add(btnBack);
		
		JLabel label_1 = new JLabel("");
		label_1.setIcon(new ImageIcon(terms.class.getResource("/xerox/back ground.jpeg")));
		label_1.setBounds(0, 0, 541, 290);
		contentPane.add(label_1);
		
		
	}
}
