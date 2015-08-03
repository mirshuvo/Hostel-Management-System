package code;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JButton;

import java.awt.Font;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class MainWindow extends JFrame {

	private JPanel contentPane;

	private JButton btnNew;
	private JButton btnView;
	private JButton btnResent;

	/**
	 * Create the frame.
	 */
	public MainWindow() {
		setTitle("Hostel Management System");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 325);

		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);

		btnNew = new JButton("Create New Sheet");
		btnNew.setFont(new Font("SansSerif", Font.PLAIN, 20));
		btnNew.setBounds(84, 46, 239, 59);
		contentPane.add(btnNew);

		btnView = new JButton("View Sheet");
		btnView.setFont(new Font("SansSerif", Font.PLAIN, 20));
		btnView.setBounds(84, 130, 239, 59);
		contentPane.add(btnView);

		btnResent = new JButton("Reset Sheet");
		btnResent.setFont(new Font("SansSerif", Font.PLAIN, 20));
		btnResent.setBounds(84, 209, 239, 59);
		contentPane.add(btnResent);

		btnNew.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				// write
				// JOptionPane.showMessageDialog(contentPane,
				// "This is dialog box");
				Object[] monthName = { "January", "February", "March", "April",
						"May", "June", "July", "August", "September",
						"October", "November", "December" };

				String s = (String) JOptionPane.showInputDialog(contentPane,
						"Select a Month..", "Customized Dialog",
						JOptionPane.PLAIN_MESSAGE, null, monthName, "January");
				/* if(s == "January"){
					setTitle(s);
				} */
				
			}
		});

		btnView.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				// write
			}
		});

		btnResent.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				// write
			}
		});
	}

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					MainWindow frame = new MainWindow();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}
}
