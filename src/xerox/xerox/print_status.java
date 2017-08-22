package xerox;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import xerox.user.print_statusfun;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.ImageIcon;
import java.awt.Toolkit;
import java.awt.Color;

public class print_status extends JFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTable table;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					print_status frame = new print_status();
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
	public print_status() {
		setIconImage(Toolkit.getDefaultToolkit().getImage(print_status.class.getResource("/xerox/Logo Transperent.png")));
		setResizable(false);
		this.setTitle("PHOENIX COPIER");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 706, 427);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(75, 64, 556, 269);
		contentPane.add(scrollPane);
		
		setTable(new JTable());
		scrollPane.setViewportView(getTable());
		
		JLabel lblAllUsers = new JLabel("Print's Status");
		lblAllUsers.setFont(new Font("Tahoma", Font.PLAIN, 16));
		lblAllUsers.setBounds(306, 12, 116, 24);
		contentPane.add(lblAllUsers);
		
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
		btnHome.setFont(new Font("Tahoma", Font.PLAIN, 11));
		btnHome.setBounds(28, 14, 89, 23);
		contentPane.add(btnHome);
		
		JButton btnRefresh = new JButton("Refresh");
		btnRefresh.setForeground(Color.WHITE);
		btnRefresh.setBackground(new Color(51, 51, 153));
		btnRefresh.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				dispose();
				print_statusfun.status();
			}
		});
		btnRefresh.setBounds(591, 15, 89, 23);
		contentPane.add(btnRefresh);
		
		
		JLabel lblAniketOmkar = new JLabel("BY ANIKET & OMKAR");
		lblAniketOmkar.setBounds(489, 355, 142, 14);
		contentPane.add(lblAniketOmkar);
		
		JLabel label = new JLabel("");
		label.setIcon(new ImageIcon(print_status.class.getResource("/xerox/back ground.jpeg")));
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
