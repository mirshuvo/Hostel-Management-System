package code;

import java.awt.EventQueue;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.PreparedStatement;
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

public class CreateUser extends JFrame {

	private JPanel contentPane;

	private JLabel idLabel;
	private JLabel nameLabel;
	private JLabel addressLabel;
	private JLabel phoneLabel;
	private JLabel emailLabel;
	private JLabel passwordLabel;

	private JTextField idTextField;
	private JTextField nameTextField;
	private JTextField addressTextField;
	private JTextField phoneTextField;
	private JTextField emailTextField;
	private JTextField passwordTextField;

	private JButton btnDone;
	private JButton btnClear;

	private Connection con = null;
	private Statement stmt = null;
	private PreparedStatement pstmt = null;

	/**
	 * Create the frame.
	 */
	public CreateUser() {
		setTitle("Create User");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 467);
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

		idLabel = new JLabel("ID");
		idLabel.setFont(new Font("Tahoma", Font.PLAIN, 20));
		idLabel.setBounds(32, 34, 125, 36);
		contentPane.add(idLabel);

		nameLabel = new JLabel("Name");
		nameLabel.setFont(new Font("Tahoma", Font.PLAIN, 20));
		nameLabel.setBounds(32, 82, 125, 36);
		contentPane.add(nameLabel);

		addressLabel = new JLabel("Address");
		addressLabel.setFont(new Font("Tahoma", Font.PLAIN, 20));
		addressLabel.setBounds(32, 130, 125, 36);
		contentPane.add(addressLabel);

		phoneLabel = new JLabel("Phone");
		phoneLabel.setFont(new Font("Tahoma", Font.PLAIN, 20));
		phoneLabel.setBounds(32, 178, 125, 36);
		contentPane.add(phoneLabel);

		emailLabel = new JLabel("Email");
		emailLabel.setFont(new Font("Tahoma", Font.PLAIN, 20));
		emailLabel.setBounds(32, 226, 125, 36);
		contentPane.add(emailLabel);

		passwordLabel = new JLabel("Password");
		passwordLabel.setFont(new Font("Tahoma", Font.PLAIN, 20));
		passwordLabel.setBounds(32, 274, 125, 36);
		contentPane.add(passwordLabel);

		idTextField = new JTextField();
		idTextField.setFont(new Font("SansSerif", Font.PLAIN, 20));
		idTextField.setBounds(167, 34, 233, 36);
		idTextField.setUI(new HintTextFieldUI("Enter ID", true));
		contentPane.add(idTextField);
		idTextField.setColumns(10);

		nameTextField = new JTextField();
		nameTextField.setFont(new Font("SansSerif", Font.PLAIN, 20));
		nameTextField.setColumns(10);
		nameTextField.setBounds(169, 82, 233, 36);
		nameTextField.setUI(new HintTextFieldUI("Enter Name", true));
		contentPane.add(nameTextField);

		addressTextField = new JTextField();
		addressTextField.setFont(new Font("SansSerif", Font.PLAIN, 20));
		addressTextField.setColumns(10);
		addressTextField.setBounds(169, 130, 233, 36);
		addressTextField.setUI(new HintTextFieldUI("Enter Address", true));
		contentPane.add(addressTextField);

		phoneTextField = new JTextField();
		phoneTextField.setFont(new Font("SansSerif", Font.PLAIN, 20));
		phoneTextField.setColumns(10);
		phoneTextField.setBounds(167, 178, 233, 36);
		phoneTextField.setUI(new HintTextFieldUI("Enter Phone number", true));
		contentPane.add(phoneTextField);

		emailTextField = new JTextField();
		emailTextField.setFont(new Font("SansSerif", Font.PLAIN, 20));
		emailTextField.setColumns(10);
		emailTextField.setBounds(169, 226, 233, 36);
		emailTextField.setUI(new HintTextFieldUI("Enter Email address", true));
		contentPane.add(emailTextField);

		passwordTextField = new JTextField();
		passwordTextField.setFont(new Font("SansSerif", Font.PLAIN, 20));
		passwordTextField.setColumns(10);
		passwordTextField.setBounds(167, 274, 233, 36);
		passwordTextField.setUI(new HintTextFieldUI("Enter Password", true));
		contentPane.add(passwordTextField);

		JButton btnDone = new JButton("Done");
		btnDone.setFont(new Font("SansSerif", Font.PLAIN, 25));
		btnDone.setBounds(310, 343, 90, 42);
		contentPane.add(btnDone);

		JButton btnClear = new JButton("Clear");
		btnClear.setFont(new Font("SansSerif", Font.PLAIN, 25));
		btnClear.setBounds(210, 343, 90, 42);
		contentPane.add(btnClear);

		btnDone.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				String id, name, address, phone, email, password;

				id = idTextField.getText().toString();
				name = nameTextField.getText().toString();
				address = addressTextField.getText().toString();
				phone = phoneTextField.getText().toString();
				email = emailTextField.getText().toString();
				password = passwordTextField.getText().toString();

				if (!id.equals("") && !name.equals("") && !address.equals("")
						&& !phone.equals("") && !email.equals("")
						&& !password.equals("")) {

					Object[] button = { "Cancel", "Ok" };
					int n = JOptionPane.showOptionDialog(contentPane,
							"Are You Sure?", "",
							JOptionPane.YES_NO_CANCEL_OPTION,
							JOptionPane.QUESTION_MESSAGE, null, button,
							button[1]);
					if (button[n].equals("Ok")) {
						// write
						String insertSql = "INSERT INTO member VALUES(?,?,?,?,?,?)";
						con = ConnectionManager.getConnection();
						try {
							pstmt = con.prepareStatement(insertSql);

							pstmt.setString(1, id);
							pstmt.setString(2, name);
							pstmt.setString(3, address);
							pstmt.setString(4, phone);
							pstmt.setString(5, email);
							pstmt.setString(6, password);

							pstmt.executeQuery();
							
							JOptionPane.showMessageDialog(contentPane, "User is created");

							ConnectionManager.close();

						} 
						catch (SQLException e) {
							// TODO Auto-generated catch block
							JOptionPane
									.showMessageDialog(contentPane,
											"This User ID is already Used. \n Enter Different User ID to create user.");
						}

					}

				} 
				else {
					JOptionPane.showMessageDialog(contentPane, "First Enter User All Information");
				}
			}
		});

		btnClear.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				// write
				idTextField.setText("");
				nameTextField.setText("");
				addressTextField.setText("");
				phoneTextField.setText("");
				emailTextField.setText("");
				passwordTextField.setText("");
			}
		});
	}
}
