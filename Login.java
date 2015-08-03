package code;

import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.SystemColor;
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
import javax.swing.SwingConstants;
import javax.swing.UIManager;
import javax.swing.UIManager.LookAndFeelInfo;
import javax.swing.border.EmptyBorder;

public class Login extends JFrame {

	private JPanel contentPane;

	private JLabel titleLabel;
	private JLabel idLabel;
	private JLabel lblName;
	private JLabel login;

	private JTextField idField;
	private JTextField passwordField;

	private JButton btnClear;
	private JButton btnLogin;
	private String id1;

	private Connection con = null;
	private Statement stmt = null;
	private PreparedStatement preStat = null;
	private ResultSet rs;
	private String query;

	/**
	 * Create the frame.
	 */
	public Login() {
		setTitle("Hostel Management System");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 447);

		contentPane = new JPanel();
		contentPane.setBackground(SystemColor.inactiveCaption);
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);

		// ---------MENU-----------------

		JMenuBar menubar = new JMenuBar();
		JMenu file = new JMenu("Menu");
		JMenuItem eMenuItem = new JMenuItem("Exit");
		eMenuItem.setToolTipText("Exit application");
		JMenuItem e1MenuItem = new JMenuItem("Reset");
		e1MenuItem.setToolTipText("Reset All Information");
		eMenuItem.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent event) {

				System.exit(0);

			}
		});

		e1MenuItem.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent event) {

				Object[] button = { "Cancel", "Ok" };
				int n = JOptionPane.showOptionDialog(contentPane,
						"Are You Sure?", "", JOptionPane.YES_NO_CANCEL_OPTION,
						JOptionPane.QUESTION_MESSAGE, null, button, button[1]);

				if (button[n].equals("Ok")) {

					con = ConnectionManager.getConnection();
					String query1 = "delete from bills";
					String query2 = "delete from daily_cost";
					String query3 = "delete from deposit";
					String query4 = "delete from meal_info";
					String query5 = "delete from member";

					try {

						preStat = con.prepareStatement(query1);
						preStat.executeUpdate();

						preStat = con.prepareStatement(query2);
						preStat.executeUpdate();

						preStat = con.prepareStatement(query3);
						preStat.executeUpdate();

						preStat = con.prepareStatement(query4);
						preStat.executeUpdate();

						preStat = con.prepareStatement(query5);
						preStat.executeUpdate();

						JOptionPane.showMessageDialog(contentPane,
								"Reset is complete.");

					} catch (SQLException e2) {
						System.out.println(e2.getMessage());
					}
					ConnectionManager.close();

				}
			}
		});

		file.add(eMenuItem);
		file.add(e1MenuItem);
		menubar.add(file);
		setJMenuBar(menubar);

		titleLabel = new JLabel("Hostel Management System");
		titleLabel.setForeground(Color.MAGENTA);
		titleLabel.setBackground(Color.WHITE);
		titleLabel.setHorizontalAlignment(SwingConstants.CENTER);

		titleLabel.setFont(new Font("Tahoma", Font.PLAIN, 30));
		titleLabel.setBounds(10, 0, 414, 77);
		contentPane.add(titleLabel);

		login = new JLabel("Login to your account");
		login.setForeground(new Color(128, 0, 0));
		login.setFont(new Font("Tahoma", Font.PLAIN, 20));
		login.setBounds(63, 80, 271, 35);
		contentPane.add(login);

		idField = new JTextField();
		idField.setFont(new Font("SansSerif", Font.PLAIN, 20));
		idField.setText("");
		idField.setUI(new HintTextFieldUI("Enter your ID", true));
		idField.setBounds(141, 137, 193, 42);
		contentPane.add(idField);
		idField.setColumns(10);

		passwordField = new JTextField();
		passwordField.setFont(new Font("SansSerif", Font.PLAIN, 20));
		passwordField.setText("");
		passwordField.setUI(new HintTextFieldUI("Enter your Password", true));
		passwordField.setBounds(141, 207, 193, 42);
		contentPane.add(passwordField);
		passwordField.setColumns(10);

		idLabel = new JLabel("ID ");
		idLabel.setFont(new Font("SansSerif", Font.PLAIN, 20));
		idLabel.setBounds(64, 137, 56, 42);
		contentPane.add(idLabel);

		lblName = new JLabel("Password");
		lblName.setFont(new Font("SansSerif", Font.PLAIN, 20));
		lblName.setBounds(30, 207, 90, 42);
		contentPane.add(lblName);

		btnClear = new JButton("Clear");
		btnClear.setFont(new Font("SansSerif", Font.PLAIN, 20));
		btnClear.setBounds(201, 304, 90, 42);
		contentPane.add(btnClear);

		btnLogin = new JButton("OK");
		btnLogin.setFont(new Font("SansSerif", Font.PLAIN, 20));
		btnLogin.setBounds(295, 304, 90, 42);
		contentPane.add(btnLogin);

		btnClear.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				idField.setText("");
				passwordField.setText("");
			}
		});

		btnLogin.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				id1 = idField.getText().toString();
				String id2 = "'" + id1 + "'";
				String password = passwordField.getText().toString();
				String str5 = null;

				ResultSet rs;
				con = ConnectionManager.getConnection();
				String query = "select password from member where id = " + id2;

				try {
					stmt = con.createStatement();
					rs = stmt.executeQuery(query);
					while (rs.next()) {

						str5 = rs.getString("password");
					}

					if (password.equals(str5) && id1.equals("admin")) {
						try {
							for (LookAndFeelInfo info : UIManager
									.getInstalledLookAndFeels()) {
								if ("Nimbus".equals(info.getName())) {
									UIManager.setLookAndFeel(info
											.getClassName());
									break;
								}
							}
						} catch (Exception en) {
							// If Nimbus is not available, fall back to
							// cross-platform
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
					} else if (password.equals(str5) && !id1.equals("admin")) {
						try {
							for (LookAndFeelInfo info : UIManager
									.getInstalledLookAndFeels()) {
								if ("Nimbus".equals(info.getName())) {
									UIManager.setLookAndFeel(info
											.getClassName());
									break;
								}
							}
						} catch (Exception en) {
							// If Nimbus is not available, fall back to
							// cross-platform
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
									UserView frame = new UserView(id1);
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

					else {
						login.setText("Enter valid ID and Password");
					}

				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				ConnectionManager.close();
			}
		});
	}

	public static void main(String[] args) {

		try {
			for (LookAndFeelInfo info : UIManager.getInstalledLookAndFeels()) {
				if ("Nimbus".equals(info.getName())) {
					UIManager.setLookAndFeel(info.getClassName());
					break;
				}
			}
		} catch (Exception en) {
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
					Login frame = new Login();
					frame.setVisible(true);
					frame.setResizable(false);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}
}
