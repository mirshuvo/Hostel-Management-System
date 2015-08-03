package code;

import java.awt.EventQueue;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.UIManager;
import javax.swing.UIManager.LookAndFeelInfo;
import javax.swing.border.EmptyBorder;

public class DeleteUser extends JFrame {

	private JPanel contentPane;

	private JLabel nameLabel;
	private JLabel phoneLabel;
	private JLabel emailLabel;
	private JLabel addressLabel;
	private JLabel passwordLabel;

	private JButton btnDelete;
	private JComboBox comboBox;

	private Connection con = null;
	private Statement stmt = null;
	private PreparedStatement preStat = null;
	private ResultSet rs;

	private String query;
	private String id = null;

	/**
	 * Create the frame.
	 */
	public DeleteUser() {
		setTitle("Delete User");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 371);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);

		// ---------MENU-----------------

		JMenuBar menubar = new JMenuBar();
		JMenu file = new JMenu("Menu");
		JMenuItem eMenuItem = new JMenuItem("Admin Panel");
		eMenuItem.setToolTipText("Back to admin panel");
		eMenuItem.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent event) {
				try {
					for (LookAndFeelInfo info : UIManager
							.getInstalledLookAndFeels()) {
						if ("Nimbus".equals(info.getName())) {
							UIManager.setLookAndFeel(info.getClassName());
							break;
						}
					}
				} catch (Exception e) {
					// If Nimbus is not available, fall back to cross-platform
					try {
						UIManager.setLookAndFeel(UIManager
								.getCrossPlatformLookAndFeelClassName());
					} catch (Exception ex) {
						// not worth my time
					}
				}

				EventQueue.invokeLater(new Runnable() {
					public void run() {
						try {
							AdminView frame = new AdminView();
							frame.setVisible(true);
							frame.setResizable(false);
						} catch (Exception e) {
							e.printStackTrace();
						}
					}
				});
				setVisible(false);
				dispose();

			}
		});
		file.add(eMenuItem);
		menubar.add(file);
		setJMenuBar(menubar);

		JLabel idLabel = new JLabel("Select ID");
		idLabel.setFont(new Font("Dialog", Font.BOLD, 25));
		idLabel.setBounds(24, 9, 118, 42);
		contentPane.add(idLabel);

		JLabel lblName = new JLabel("Name :-");
		lblName.setFont(new Font("Dialog", Font.BOLD, 20));
		lblName.setBounds(24, 63, 118, 26);
		contentPane.add(lblName);

		JLabel lblAddress = new JLabel("Address :-");
		lblAddress.setFont(new Font("Dialog", Font.BOLD, 20));
		lblAddress.setBounds(24, 101, 118, 26);
		contentPane.add(lblAddress);

		JLabel lblPhone = new JLabel("Phone :-");
		lblPhone.setFont(new Font("Dialog", Font.BOLD, 20));
		lblPhone.setBounds(24, 139, 118, 26);
		contentPane.add(lblPhone);

		JLabel lblEmail = new JLabel("Email :- ");
		lblEmail.setFont(new Font("Dialog", Font.BOLD, 20));
		lblEmail.setBounds(24, 177, 118, 26);
		contentPane.add(lblEmail);

		JLabel lblPassword = new JLabel("Password :-");
		lblPassword.setFont(new Font("Dialog", Font.BOLD, 20));
		lblPassword.setBounds(24, 215, 118, 26);
		contentPane.add(lblPassword);

		nameLabel = new JLabel("");
		nameLabel.setFont(new Font("Dialog", Font.BOLD, 20));
		nameLabel.setBounds(154, 63, 268, 26);
		contentPane.add(nameLabel);

		addressLabel = new JLabel("");
		addressLabel.setFont(new Font("Dialog", Font.BOLD, 20));
		addressLabel.setBounds(154, 101, 268, 26);
		contentPane.add(addressLabel);

		phoneLabel = new JLabel("");
		phoneLabel.setFont(new Font("Dialog", Font.BOLD, 20));
		phoneLabel.setBounds(154, 140, 268, 26);
		contentPane.add(phoneLabel);

		emailLabel = new JLabel("");
		emailLabel.setFont(new Font("Dialog", Font.BOLD, 20));
		emailLabel.setBounds(154, 178, 268, 26);
		contentPane.add(emailLabel);

		passwordLabel = new JLabel("");
		passwordLabel.setFont(new Font("Dialog", Font.BOLD, 20));
		passwordLabel.setBounds(154, 216, 268, 26);
		contentPane.add(passwordLabel);

		// ----------------FOR COMBOBOX-----------------------

		con = ConnectionManager.getConnection();
		query = "select count(id) from member";
		int n = 0;

		try {
			stmt = con.createStatement();
			rs = stmt.executeQuery(query);
			while (rs.next()) {
				n = rs.getInt("count(id)");
				// System.out.println(n);

			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		ConnectionManager.close();

		String[] AllID = new String[n];

		con = ConnectionManager.getConnection();
		query = "select id from member";
		int count = 0;

		try {
			stmt = con.createStatement();
			rs = stmt.executeQuery(query);
			while (rs.next()) {

				id = rs.getString("id");
				// System.out.println(id);
				AllID[count] = id;
				count++;
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		ConnectionManager.close();

		comboBox = new JComboBox(AllID);
		comboBox.setFont(new Font("SansSerif", Font.PLAIN, 20));
		comboBox.setBounds(183, 11, 192, 42);
		contentPane.add(comboBox);
		comboBox.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				// System.out.println(comboBox.getSelectedItem());
				String name = null, address = null, phone = null, email = null, password = null;
				con = ConnectionManager.getConnection();

				query = "select name,address,phone,email,password from member where id = "
						+ "'" + comboBox.getSelectedItem() + "'";

				try {
					stmt = con.createStatement();
					rs = stmt.executeQuery(query);
					while (rs.next()) {
						name = rs.getString("name");
						address = rs.getString("address");
						phone = rs.getString("phone");
						email = rs.getString("email");
						password = rs.getString("password");
					}
				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				ConnectionManager.close();

				nameLabel.setText(name);
				addressLabel.setText(address);
				phoneLabel.setText(phone);
				emailLabel.setText(email);
				passwordLabel.setText(password);

			}
		});

		btnDelete = new JButton("Delete");
		btnDelete.setFont(new Font("SansSerif", Font.PLAIN, 25));
		btnDelete.setBounds(321, 258, 112, 39);
		contentPane.add(btnDelete);
		btnDelete.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				// write

				if (comboBox.getSelectedIndex() != -1) {
					String userid = comboBox.getSelectedItem().toString();
					//System.out.println(userid);

					Object[] button = { "Cancel", "Ok" };
					int n = JOptionPane.showOptionDialog(contentPane,
							"Are You Sure?", "",
							JOptionPane.YES_NO_CANCEL_OPTION,
							JOptionPane.QUESTION_MESSAGE, null, button,
							button[1]);

					if (button[n].equals("Ok")) {
						con = ConnectionManager.getConnection();
						query = "delete member where id = ?";

						try {

							preStat = con.prepareStatement(query);
							preStat.setString(1, userid);
							preStat.executeUpdate();

							JOptionPane.showMessageDialog(contentPane,
									"User is deleted.");

						} catch (SQLException e2) {
							System.out.println(e2.getMessage());
						}
						ConnectionManager.close();
					}

				} else {
					JOptionPane.showMessageDialog(contentPane,
							"User ID not found.");
				}
			}
		});

	}
}
