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
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.UIManager;
import javax.swing.UIManager.LookAndFeelInfo;
import javax.swing.border.EmptyBorder;

public class UpdateUser extends JFrame {

	private JPanel contentPane;

	private JTextField idTextField;

	private JLabel nameLabel;
	private JLabel addressLabel;
	private JLabel phoneLabel;
	private JLabel emailLabel;
	private JLabel passwordLabel;

	private JButton btnDone;
	private JButton btnNameEdit;
	private JButton btnAddressEdit;
	private JButton btnPhoneEdit;
	private JButton btnEmailEdit;
	private JButton btnPasswordEdit;
	private JButton btnUpdate;
	private JButton btnClear;

	private Connection con = null;
	private Statement stmt = null;
	private PreparedStatement preStat = null;
	private ResultSet rs;

	/**
	 * Create the frame.
	 */
	public UpdateUser() {
		setTitle("Update User");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 480, 432);
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
		
		
		

		JLabel lblNewLabel = new JLabel("ID");
		lblNewLabel.setFont(new Font("Dialog", Font.BOLD, 30));
		lblNewLabel.setBounds(12, 6, 45, 48);
		contentPane.add(lblNewLabel);

		idTextField = new JTextField();
		idTextField.setFont(new Font("SansSerif", Font.PLAIN, 20));
		idTextField.setBounds(63, 10, 249, 48);
		contentPane.add(idTextField);
		idTextField.setColumns(10);

		btnDone = new JButton("Done");
		btnDone.setFont(new Font("SansSerif", Font.PLAIN, 25));
		btnDone.setBounds(325, 13, 98, 39);
		contentPane.add(btnDone);

		JLabel lblName = new JLabel("Name");
		lblName.setFont(new Font("SansSerif", Font.PLAIN, 20));
		lblName.setBounds(12, 70, 80, 32);
		contentPane.add(lblName);

		JLabel lblNewLabel_1 = new JLabel("Address");
		lblNewLabel_1.setFont(new Font("SansSerif", Font.PLAIN, 20));
		lblNewLabel_1.setBounds(12, 114, 80, 32);
		contentPane.add(lblNewLabel_1);

		JLabel lblPhone = new JLabel("Phone");
		lblPhone.setFont(new Font("SansSerif", Font.PLAIN, 20));
		lblPhone.setBounds(12, 158, 80, 32);
		contentPane.add(lblPhone);

		JLabel lblEmail = new JLabel("Email");
		lblEmail.setFont(new Font("SansSerif", Font.PLAIN, 20));
		lblEmail.setBounds(12, 202, 80, 32);
		contentPane.add(lblEmail);

		JLabel lblPassword = new JLabel("Password");
		lblPassword.setFont(new Font("SansSerif", Font.PLAIN, 20));
		lblPassword.setBounds(12, 246, 98, 32);
		contentPane.add(lblPassword);

		nameLabel = new JLabel("");
		nameLabel.setFont(new Font("SansSerif", Font.PLAIN, 15));
		nameLabel.setBounds(104, 70, 270, 32);
		contentPane.add(nameLabel);

		addressLabel = new JLabel("");
		addressLabel.setFont(new Font("SansSerif", Font.PLAIN, 15));
		addressLabel.setBounds(104, 114, 270, 32);
		contentPane.add(addressLabel);

		phoneLabel = new JLabel("");
		phoneLabel.setFont(new Font("SansSerif", Font.PLAIN, 15));
		phoneLabel.setBounds(104, 158, 270, 32);
		contentPane.add(phoneLabel);

		emailLabel = new JLabel("");
		emailLabel.setFont(new Font("SansSerif", Font.PLAIN, 15));
		emailLabel.setBounds(104, 202, 270, 32);
		contentPane.add(emailLabel);

		passwordLabel = new JLabel("");
		passwordLabel.setFont(new Font("SansSerif", Font.PLAIN, 15));
		passwordLabel.setBounds(104, 246, 270, 32);
		contentPane.add(passwordLabel);

		btnNameEdit = new JButton("Edit");
		btnNameEdit.setBounds(378, 76, 74, 26);
		contentPane.add(btnNameEdit);

		btnAddressEdit = new JButton("Edit");
		btnAddressEdit.setBounds(378, 120, 74, 26);
		contentPane.add(btnAddressEdit);

		btnPhoneEdit = new JButton("Edit");
		btnPhoneEdit.setBounds(378, 164, 74, 26);
		contentPane.add(btnPhoneEdit);

		btnEmailEdit = new JButton("Edit");
		btnEmailEdit.setBounds(378, 208, 74, 26);
		contentPane.add(btnEmailEdit);

		btnPasswordEdit = new JButton("Edit");
		btnPasswordEdit.setBounds(378, 251, 74, 26);
		contentPane.add(btnPasswordEdit);

		btnUpdate = new JButton("Update");
		btnUpdate.setFont(new Font("SansSerif", Font.PLAIN, 25));
		btnUpdate.setBounds(343, 310, 109, 39);
		contentPane.add(btnUpdate);

		btnClear = new JButton("Clear");
		btnClear.setFont(new Font("SansSerif", Font.PLAIN, 25));
		btnClear.setBounds(232, 310, 98, 39);
		contentPane.add(btnClear);

		btnDone.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				// write
				String userid = idTextField.getText();
				if (!userid.equals("")) {

					String name = null, address = null, phone = null, email = null, password = null;
					con = ConnectionManager.getConnection();

					String query = "select name, address, phone, email, password from member where id = "
							+ "'" + userid + "'";

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

				} else {
					JOptionPane.showMessageDialog(contentPane,
							"Enter User ID first.");
				}
			}
		});

		btnNameEdit.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String str = nameLabel.getText();
				String response = JOptionPane.showInputDialog(null, "",
						"Enter User Name", JOptionPane.QUESTION_MESSAGE);
				if (!response.equals("")) {
					nameLabel.setText(response);
				} else {
					nameLabel.setText(str);
				}
			}
		});

		btnAddressEdit.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String str = addressLabel.getText();

				String response = JOptionPane.showInputDialog(null, "",
						"Enter User Address", JOptionPane.QUESTION_MESSAGE);
				if (!response.equals("")) {
					addressLabel.setText(response);
				} else {
					addressLabel.setText(str);
				}

			}
		});

		btnPhoneEdit.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String str = phoneLabel.getText();
				String response = JOptionPane
						.showInputDialog(null, "", "Enter User Phone number",
								JOptionPane.QUESTION_MESSAGE);
				if (!response.equals("")) {
					phoneLabel.setText(response);
				} else {
					phoneLabel.setText(str);
				}
			}
		});

		btnEmailEdit.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String str = emailLabel.getText();
				String response = JOptionPane.showInputDialog(null, "",
						"Enter User Email address",
						JOptionPane.QUESTION_MESSAGE);
				if (!response.equals("")) {
					emailLabel.setText(response);
				} else {
					emailLabel.setText(str);
				}
			}
		});

		btnPasswordEdit.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				String str = passwordLabel.getText();
				String response = JOptionPane.showInputDialog(null, "",
						"Enter User Password", JOptionPane.QUESTION_MESSAGE);
				if (!response.equals("")) {
					passwordLabel.setText(response);
				} else {
					passwordLabel.setText(str);
				}
			}
		});

		btnUpdate.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				String id = idTextField.getText().toString();

				if (!id.equals("")) {
					Object[] button = { "Cancel", "Ok" };
					int n = JOptionPane.showOptionDialog(contentPane,
							"Are You Sure?", "Update User",
							JOptionPane.YES_NO_CANCEL_OPTION,
							JOptionPane.QUESTION_MESSAGE, null, button,
							button[1]);
					if (button[n].equals("Ok")) {
						String name = nameLabel.getText();
						String address = addressLabel.getText();
						String phone = phoneLabel.getText();
						String email = emailLabel.getText();
						String password = passwordLabel.getText();

						con = ConnectionManager.getConnection();
						
						String namequery = "update member set name = " + "'"
								+ name + "'" + "  where id = ?";
						String addressquery = "update member set address = "
								+ "'" + address + "'" + "  where id = ?";
						String phonequery = "update member set phone = " + "'"
								+ phone + "'" + "  where id = ?";
						String emailquery = "update member set email = " + "'"
								+ email + "'" + "  where id = ?";
						String passwordquery = "update member set password = " + "'"
								+ password + "'" + "  where id = ?";

						try {

							preStat = con.prepareStatement(namequery);
							preStat.setString(1, id);
							preStat.executeUpdate();
							
							preStat = con.prepareStatement(addressquery);
							preStat.setString(1, id);
							preStat.executeUpdate();
							
							preStat = con.prepareStatement(phonequery);
							preStat.setString(1, id);
							preStat.executeUpdate();
							
							preStat = con.prepareStatement(emailquery);
							preStat.setString(1, id);
							preStat.executeUpdate();
							
							preStat = con.prepareStatement(passwordquery);
							preStat.setString(1, id);
							preStat.executeUpdate();
							
							JOptionPane.showMessageDialog(contentPane,
									"User Information is Update.");

						} catch (SQLException e2) {
							System.out.println(e2.getMessage());
						}
						ConnectionManager.close();

					} else {

					}

				} else {
					JOptionPane.showMessageDialog(contentPane, "Enter User ID");
				}
			}
		});

		btnClear.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				idTextField.setText("");
				nameLabel.setText("");
				addressLabel.setText("");
				phoneLabel.setText("");
				emailLabel.setText("");
				passwordLabel.setText("");
			}
		});
	}
}
