package code;

import java.awt.BorderLayout;
import java.awt.Color;
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
import javax.swing.JScrollPane;
import javax.swing.JTabbedPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.UIManager;
import javax.swing.UIManager.LookAndFeelInfo;
import javax.swing.border.EmptyBorder;

public class AdminView extends JFrame {

	private JPanel contentPane;
	private JTabbedPane tabbedPane;

	private JPanel depositPanel;
	private JPanel mealPanel;
	private JPanel dailyCostPanel;
	private JPanel allBillsPanel;
	private JPanel finalResultPanel;
	private JPanel userPanel;
	private JButton btnUserCreate;
	private JButton btnDeleteUser;
	private JButton btnUpdate;
	private JTextField depositTextField;
	private JLabel depositLabel;
	private JButton btnDepositDone;

	private JLabel lblNewLabel_1;
	private JLabel lblMeal;
	private JTextField mealTextField;
	private JButton btnMealDone;
	private JLabel lblTotalMeal;
	private JLabel mealLabel;
	private JTable depositTable;
	private JScrollPane depositScrollPane;
	private JTable mealTable;
	private JScrollPane mealScrollPane;
	private JTextField amountTextField;
	private JTextField DateTextField;
	private JTable costTable;
	private JLabel costLabel;

	private JButton btnUpdateBills;

	private JLabel houseRent;
	private JLabel ElectricBills;
	private JLabel gasBills;
	private JLabel waterBills;
	private JLabel chefBills;
	private JLabel garbaseBills;
	private JLabel servantBills;
	private JLabel internetBills;
	private JLabel totalBills;

	private JComboBox depositComboBox;
	private JComboBox mealComboBox;
	private JScrollPane costScrollPane;

	private Connection con = null;
	private Statement stmt = null;
	private PreparedStatement preStat = null;
	private ResultSet rs;

	private String query;
	private String id = null;
	private String name = null;
	private int amount;
	private int mealamount;
	private int costamount;
	private String date;

	private JTable dTable;
	private JScrollPane dScrollPane;
	private JComboBox costComboBox;
	private JTextField nameTextField;
	private JTable table;

	/**
	 * Create the frame.
	 */
	public AdminView() {
		setTitle("Hostel Management System");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 425, 494);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.setLayout(new BorderLayout(0, 0));
		setContentPane(contentPane);

		tabbedPane = new JTabbedPane(JTabbedPane.TOP);
		contentPane.add(tabbedPane, BorderLayout.CENTER);

		// ---------MENU-----------------

		JMenuBar menubar = new JMenuBar();
		JMenu file = new JMenu("Menu");
		JMenuItem eMenuItem = new JMenuItem("Log Out");
		eMenuItem.setToolTipText("Log out application");
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
							Login frame = new Login();
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

		// *************User Panel***************

		userPanel = new JPanel();
		tabbedPane.addTab("User", null, userPanel, null);
		userPanel.setLayout(null);

		btnUserCreate = new JButton("Create User");
		btnUserCreate.setFont(new Font("SansSerif", Font.BOLD, 25));
		btnUserCreate.setBounds(82, 54, 197, 64);
		userPanel.add(btnUserCreate);

		btnDeleteUser = new JButton("Delete User");
		btnDeleteUser.setFont(new Font("SansSerif", Font.BOLD, 25));
		btnDeleteUser.setBounds(82, 156, 197, 64);
		userPanel.add(btnDeleteUser);

		btnUpdate = new JButton("Update User");
		btnUpdate.setFont(new Font("SansSerif", Font.BOLD, 25));
		btnUpdate.setBounds(82, 267, 197, 64);
		userPanel.add(btnUpdate);

		btnUserCreate.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {

				try {
					for (LookAndFeelInfo info : UIManager
							.getInstalledLookAndFeels()) {
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
							CreateUser frame = new CreateUser();
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
		btnDeleteUser.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				try {
					for (LookAndFeelInfo info : UIManager
							.getInstalledLookAndFeels()) {
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
							DeleteUser frame = new DeleteUser();
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

		btnUpdate.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					for (LookAndFeelInfo info : UIManager
							.getInstalledLookAndFeels()) {
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
							UpdateUser frame = new UpdateUser();
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

		// *************Deposit Panel***************

		depositPanel = new JPanel();
		tabbedPane.addTab("Deposit", null, depositPanel, null);
		depositPanel.setLayout(null);

		JLabel lblNewLabel = new JLabel("Select ID");
		lblNewLabel.setBounds(23, 6, 95, 38);
		lblNewLabel.setFont(new Font("SansSerif", Font.PLAIN, 20));
		depositPanel.add(lblNewLabel);

		JLabel lblDeposit = new JLabel("Deposit");
		lblDeposit.setBounds(23, 56, 78, 38);
		lblDeposit.setFont(new Font("SansSerif", Font.PLAIN, 20));
		depositPanel.add(lblDeposit);

		depositTextField = new JTextField();
		depositTextField.setBounds(113, 56, 95, 38);
		depositTextField.setFont(new Font("SansSerif", Font.PLAIN, 20));
		depositTextField.setColumns(10);
		depositPanel.add(depositTextField);

		btnDepositDone = new JButton("Done");
		btnDepositDone.setBounds(219, 56, 78, 38);
		btnDepositDone.setFont(new Font("SansSerif", Font.PLAIN, 20));
		depositPanel.add(btnDepositDone);

		// --------For Table.............
		con = ConnectionManager.getConnection();
		query = "select count(id) from member";
		int num = 0;

		try {
			stmt = con.createStatement();
			rs = stmt.executeQuery(query);
			while (rs.next()) {
				num = rs.getInt("count(id)");
				// System.out.println(n);

			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		ConnectionManager.close();

		con = ConnectionManager.getConnection();
		query = "select name, deposit_amount from deposit natural join member";
		Object[][] data = new Object[num][2];

		try {
			stmt = con.createStatement();
			rs = stmt.executeQuery(query);
			int i = 0;
			while (rs.next()) {

				id = rs.getString("name");
				amount = rs.getInt("deposit_amount");
				// System.out.println(id + "---" + amount);
				data[i][0] = id;
				data[i][1] = amount;
				i++;
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		ConnectionManager.close();

		String[] columnName = { "Name", "Total Deposit" };
		depositTable = new JTable(data, columnName);
		depositTable.setFont(new Font("SansSerif", Font.PLAIN, 17));
		depositTable.setBounds(12, 106, 359, 246);
		depositTable.setEnabled(false);
		depositPanel.add(depositTable);

		depositScrollPane = new JScrollPane(depositTable);
		depositScrollPane.setBounds(12, 107, 359, 245);
		depositPanel.add(depositScrollPane);

		JLabel lblTotalDeposit = new JLabel("Total Deposit");
		lblTotalDeposit.setBounds(23, 364, 125, 25);
		lblTotalDeposit.setFont(new Font("SansSerif", Font.PLAIN, 20));
		depositPanel.add(lblTotalDeposit);

		// ----------For Deposit Level-------------

		con = ConnectionManager.getConnection();
		query = "select sum(deposit_amount) from deposit";
		int sumDepositAmount = 0;

		try {
			stmt = con.createStatement();
			rs = stmt.executeQuery(query);
			while (rs.next()) {
				sumDepositAmount = rs.getInt("sum(deposit_amount)");
				// System.out.println(sumDepositAmount);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		ConnectionManager.close();

		String sumDeposit = Integer.toString(sumDepositAmount);
		depositLabel = new JLabel(sumDeposit);
		depositLabel.setBounds(160, 364, 125, 25);
		depositLabel.setFont(new Font("SansSerif", Font.PLAIN, 20));
		depositPanel.add(depositLabel);

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
		String[] AllName = new String[n];

		con = ConnectionManager.getConnection();
		query = "select id, name from member";
		int count = 0;

		try {
			stmt = con.createStatement();
			rs = stmt.executeQuery(query);
			while (rs.next()) {
				id = rs.getString("id");
				name = rs.getString("name");
				// System.out.println(id);
				AllID[count] = id;
				AllName[count] = name;
				count++;
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		ConnectionManager.close();

		String[] info = new String[n];
		for (int i = 0; i < n; i++) {
			info[i] = AllID[i] + " - " + AllName[i];
		}

		depositComboBox = new JComboBox(info);
		depositComboBox.setBounds(130, 16, 241, 25);
		depositPanel.add(depositComboBox);
		depositComboBox.setSelectedItem(info[0]);

		// ---------------END COMBO BOX-------------------

		btnDepositDone.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {

				String depositValue = depositTextField.getText().toString();

				if (!depositValue.equals("")) {

					// ------- For insert amount into database--------
					int deposit = Integer.parseInt(depositValue);
					String id = depositComboBox.getSelectedItem().toString();

					String[] parts = id.split(" - ");
					String a = parts[0];

					con = ConnectionManager.getConnection();
					query = "update deposit set deposit_amount = deposit_amount + "
							+ deposit + "  where id = ?";

					try {
						preStat = con.prepareStatement(query);
						preStat.setString(1, a);
						preStat.executeUpdate();

					} catch (SQLException e2) {
						System.out.println(e2.getMessage());
					}
					ConnectionManager.close();

					// --------For count Number of member.............
					con = ConnectionManager.getConnection();
					query = "select count(id) from member";
					int num = 0;

					try {
						stmt = con.createStatement();
						rs = stmt.executeQuery(query);
						while (rs.next()) {
							num = rs.getInt("count(id)");
							// System.out.println(n);

						}
					} catch (SQLException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
					ConnectionManager.close();

					// ---------For Show data in the Table-----------

					con = ConnectionManager.getConnection();
					query = "select name, deposit_amount from deposit natural join member";
					Object[][] data1 = new Object[num][2];

					try {
						stmt = con.createStatement();
						rs = stmt.executeQuery(query);
						int i = 0;
						while (rs.next()) {

							id = rs.getString("name");
							amount = rs.getInt("deposit_amount");
							// System.out.println(id + "---" + amount);
							data1[i][0] = id;
							data1[i][1] = amount;
							i++;
						}
					} catch (SQLException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
					ConnectionManager.close();

					depositTable.setVisible(false);
					depositScrollPane.setVisible(false);

					String[] columnName = { "Name", "Total deposit" };
					depositTable = new JTable(data1, columnName);
					depositTable.setFont(new Font("SansSerif", Font.PLAIN, 17));
					depositTable.setBounds(12, 106, 359, 246);
					depositTable.setEnabled(false);
					depositPanel.add(depositTable);

					depositScrollPane = new JScrollPane(depositTable);
					depositScrollPane.setBounds(12, 107, 359, 245);
					depositPanel.add(depositScrollPane);

					// -------For show total amount of deposit

					con = ConnectionManager.getConnection();
					query = "select sum(deposit_amount) from deposit";
					int sumDepositAmount = 0;

					try {
						stmt = con.createStatement();
						rs = stmt.executeQuery(query);
						while (rs.next()) {
							sumDepositAmount = rs.getInt("sum(deposit_amount)");
							// System.out.println(sumDepositAmount);
						}
					} catch (SQLException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
					ConnectionManager.close();

					String sumDeposit = Integer.toString(sumDepositAmount);
					depositLabel.setText(sumDeposit);

					JOptionPane.showMessageDialog(contentPane,
							"Deposit amount is updated");
				} else {
					JOptionPane.showMessageDialog(contentPane,
							"Enter Deposit Amount first!!");
				}
			}
		});

		// *************Meal Panel***************

		mealPanel = new JPanel();
		tabbedPane.addTab("Meal", null, mealPanel, null);
		mealPanel.setLayout(null);

		lblNewLabel_1 = new JLabel("Select ID");
		lblNewLabel_1.setFont(new Font("Dialog", Font.PLAIN, 20));
		lblNewLabel_1.setBounds(10, 22, 99, 38);
		mealPanel.add(lblNewLabel_1);

		lblMeal = new JLabel("Meal");
		lblMeal.setFont(new Font("Dialog", Font.PLAIN, 20));
		lblMeal.setBounds(50, 72, 59, 38);
		mealPanel.add(lblMeal);

		mealTextField = new JTextField();
		mealTextField.setFont(new Font("Dialog", Font.PLAIN, 20));
		mealTextField.setColumns(10);
		mealTextField.setBounds(127, 72, 78, 38);
		mealPanel.add(mealTextField);

		btnMealDone = new JButton("Done");
		btnMealDone.setFont(new Font("SansSerif", Font.PLAIN, 20));
		btnMealDone.setBounds(217, 72, 78, 38);
		mealPanel.add(btnMealDone);

		// -----FOR Meal Table----------

		con = ConnectionManager.getConnection();
		query = "select count(id) from member";
		int size1 = 0;

		try {
			stmt = con.createStatement();
			rs = stmt.executeQuery(query);
			while (rs.next()) {
				size1 = rs.getInt("count(id)");
				// System.out.println(n);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		ConnectionManager.close();

		con = ConnectionManager.getConnection();
		query = "select name, meal_amount from meal_info natural join member";
		Object[][] mealdata = new Object[size1][2];

		try {
			stmt = con.createStatement();
			rs = stmt.executeQuery(query);
			int i = 0;
			while (rs.next()) {

				id = rs.getString("name");
				mealamount = rs.getInt("meal_amount");
				// System.out.println(id + "---" + mealamount);
				mealdata[i][0] = id;
				mealdata[i][1] = mealamount;
				i++;
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		ConnectionManager.close();

		String[] mealcolumnName = { "Name", "Total Meal" };
		mealTable = new JTable(mealdata, mealcolumnName);
		mealTable.setFont(new Font("SansSerif", Font.PLAIN, 17));
		mealTable.setBounds(12, 130, 375, 217);
		mealTable.setEnabled(false);
		mealPanel.add(mealTable);

		mealScrollPane = new JScrollPane(mealTable);
		mealScrollPane.setBounds(12, 131, 375, 214);
		mealPanel.add(mealScrollPane);

		lblTotalMeal = new JLabel("Total Meal");
		lblTotalMeal.setFont(new Font("Dialog", Font.PLAIN, 20));
		lblTotalMeal.setBounds(39, 357, 109, 38);
		mealPanel.add(lblTotalMeal);

		// ----------For Total Meal Level-------------

		con = ConnectionManager.getConnection();
		query = "select sum(meal_amount) from meal_info";
		int sumMealAmount = 0;

		try {
			stmt = con.createStatement();
			rs = stmt.executeQuery(query);
			while (rs.next()) {
				sumMealAmount = rs.getInt("sum(meal_amount)");
				// System.out.println(sumDepositAmount);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		ConnectionManager.close();

		String sumMeal = Integer.toString(sumMealAmount);
		mealLabel = new JLabel(sumMeal);
		mealLabel.setFont(new Font("Dialog", Font.PLAIN, 20));
		mealLabel.setBounds(160, 357, 109, 38);
		mealPanel.add(mealLabel);

		// ----------------FOR COMBOBOX-----------------------

		con = ConnectionManager.getConnection();
		query = "select count(id) from member";
		int size = 0;

		try {
			stmt = con.createStatement();
			rs = stmt.executeQuery(query);
			while (rs.next()) {
				size = rs.getInt("count(id)");
				// System.out.println(n);

			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		ConnectionManager.close();

		String[] mAllID = new String[size];
		String[] mAllName = new String[size];

		con = ConnectionManager.getConnection();
		query = "select id, name from member";
		int count1 = 0;

		try {
			stmt = con.createStatement();
			rs = stmt.executeQuery(query);
			while (rs.next()) {
				id = rs.getString("id");
				name = rs.getString("name");
				// System.out.println(id);
				mAllID[count1] = id;
				mAllName[count1] = name;
				count1++;
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		ConnectionManager.close();

		String[] mealinfo = new String[size];
		for (int i = 0; i < size; i++) {
			mealinfo[i] = mAllID[i] + " - " + mAllName[i];
		}

		mealComboBox = new JComboBox(mealinfo);
		mealComboBox.setBounds(121, 32, 241, 25);
		mealPanel.add(mealComboBox);

		btnMealDone.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String mealAmount = mealTextField.getText().toString();
				if (!mealAmount.equals("")) {

					// ------- For insert amount into database--------
					int meal = Integer.parseInt(mealAmount);
					String id = mealComboBox.getSelectedItem().toString();

					String[] parts = id.split(" - ");
					String a = parts[0];

					con = ConnectionManager.getConnection();
					query = "update meal_info set meal_amount = meal_amount + "
							+ meal + "  where id = ?";

					try {
						preStat = con.prepareStatement(query);
						preStat.setString(1, a);
						preStat.executeUpdate();

					} catch (SQLException e2) {
						System.out.println(e2.getMessage());
					}
					ConnectionManager.close();

					// --------For count Number of member.............
					con = ConnectionManager.getConnection();
					query = "select count(id) from member";
					int num = 0;

					try {
						stmt = con.createStatement();
						rs = stmt.executeQuery(query);
						while (rs.next()) {
							num = rs.getInt("count(id)");
							// System.out.println(num);
						}
					} catch (SQLException e3) {
						// TODO Auto-generated catch block
						e3.printStackTrace();
					}
					ConnectionManager.close();

					// ---------For Show data in the Table-----------

					con = ConnectionManager.getConnection();
					query = "select name, meal_amount from meal_info natural join member";
					Object[][] mealdata1 = new Object[num][2];

					try {
						stmt = con.createStatement();
						rs = stmt.executeQuery(query);
						int i = 0;
						while (rs.next()) {

							id = rs.getString("name");
							mealamount = rs.getInt("meal_amount");

							mealdata1[i][0] = id;
							mealdata1[i][1] = mealamount;
							i++;
						}
					} catch (SQLException e2) {
						// TODO Auto-generated catch block
						e2.printStackTrace();
					}
					ConnectionManager.close();

					mealTable.setVisible(false);
					mealScrollPane.setVisible(false);

					String[] columnName = { "Name", "Total Meal" };
					mealTable = new JTable(mealdata1, columnName);
					mealTable.setFont(new Font("SansSerif", Font.PLAIN, 17));
					mealTable.setBounds(12, 106, 359, 246);
					mealTable.setEnabled(false);
					mealPanel.add(mealTable);

					mealScrollPane = new JScrollPane(mealTable);
					mealScrollPane.setBounds(12, 107, 359, 245);
					mealPanel.add(mealScrollPane);

					// -------For show total amount of Meal

					con = ConnectionManager.getConnection();
					query = "select sum(meal_amount) from meal_info";
					int sumMealAmount = 0;

					try {
						stmt = con.createStatement();
						rs = stmt.executeQuery(query);
						while (rs.next()) {
							sumMealAmount = rs.getInt("sum(meal_amount)");

						}
					} catch (SQLException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
					ConnectionManager.close();

					String sumMeal = Integer.toString(sumMealAmount);
					mealLabel.setText(sumMeal);

					JOptionPane.showMessageDialog(contentPane,
							"Meal amount is updated");

				} else {
					JOptionPane.showMessageDialog(contentPane,
							"Enter the Meal amount First");
				}

			}
		});

		// *************Daily Cost Panel***************

		dailyCostPanel = new JPanel();
		tabbedPane.addTab("Daily Cost", null, dailyCostPanel, null);
		dailyCostPanel.setLayout(null);

		JLabel lblNewLabel_2 = new JLabel("Enter Cost Information");
		lblNewLabel_2.setFont(new Font("Dialog", Font.BOLD, 20));
		lblNewLabel_2.setBounds(12, 12, 232, 25);
		dailyCostPanel.add(lblNewLabel_2);

		JLabel lblNewLabel_3 = new JLabel("Name");
		lblNewLabel_3.setFont(new Font("Dialog", Font.BOLD, 20));
		lblNewLabel_3.setBounds(12, 49, 65, 25);
		dailyCostPanel.add(lblNewLabel_3);

		JLabel lblCostAmount = new JLabel("Amount");
		lblCostAmount.setFont(new Font("Dialog", Font.BOLD, 20));
		lblCostAmount.setBounds(12, 86, 85, 25);
		dailyCostPanel.add(lblCostAmount);

		JLabel lblDate = new JLabel("Date");
		lblDate.setFont(new Font("Dialog", Font.BOLD, 20));
		lblDate.setBounds(12, 123, 85, 25);
		dailyCostPanel.add(lblDate);

		amountTextField = new JTextField();
		amountTextField.setFont(new Font("Dialog", Font.PLAIN, 17));
		amountTextField.setColumns(10);
		amountTextField.setBounds(95, 86, 190, 30);
		dailyCostPanel.add(amountTextField);

		DateTextField = new JTextField();
		DateTextField.setFont(new Font("Dialog", Font.PLAIN, 17));
		DateTextField.setColumns(10);
		DateTextField.setBounds(95, 123, 190, 30);
		DateTextField.setUI(new HintTextFieldUI("Exm : 01-Jan-2015", true));
		dailyCostPanel.add(DateTextField);

		JButton btnCostDone = new JButton("Done");
		btnCostDone.setFont(new Font("SansSerif", Font.PLAIN, 20));
		btnCostDone.setBounds(297, 88, 85, 40);
		dailyCostPanel.add(btnCostDone);

		// -----FOR Daily Cost Table----------

		con = ConnectionManager.getConnection();
		query = "select count(name) from daily_cost";
		int sz = 0;

		try {
			stmt = con.createStatement();
			rs = stmt.executeQuery(query);
			while (rs.next()) {
				sz = rs.getInt("count(name)");
				// System.out.println(n);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		ConnectionManager.close();

		con = ConnectionManager.getConnection();
		query = "select name, cost_amount, cost_date from daily_cost";
		Object[][] costdata = new Object[sz][3];

		try {
			stmt = con.createStatement();
			rs = stmt.executeQuery(query);
			int i = 0;
			while (rs.next()) {

				id = rs.getString("name");
				costamount = rs.getInt("cost_amount");
				date = rs.getString("cost_date");

				costdata[i][0] = id;
				costdata[i][1] = costamount;
				costdata[i][2] = date;
				i++;
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		ConnectionManager.close();

		String[] costColumnName = { "Name", "Total Cost", "Date" };
		costTable = new JTable(costdata, costColumnName);
		costTable.setFont(new Font("SansSerif", Font.PLAIN, 17));
		costTable.setBounds(12, 164, 381, 196);
		dailyCostPanel.add(costTable);

		costScrollPane = new JScrollPane(costTable);
		costScrollPane.setBounds(12, 165, 381, 196);
		dailyCostPanel.add(costScrollPane);

		JLabel lblTotalCost = new JLabel("Total Cost");
		lblTotalCost.setFont(new Font("Dialog", Font.BOLD, 20));
		lblTotalCost.setBounds(12, 372, 116, 25);
		dailyCostPanel.add(lblTotalCost);

		// ----------For Total Cost Level-------------

		con = ConnectionManager.getConnection();
		query = "select sum(cost_amount) from daily_cost";
		int sumCostAmount = 0;

		try {
			stmt = con.createStatement();
			rs = stmt.executeQuery(query);
			while (rs.next()) {
				sumCostAmount = rs.getInt("sum(cost_amount)");
				// System.out.println(sumDepositAmount);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		ConnectionManager.close();

		String sumCost = Integer.toString(sumCostAmount);
		costLabel = new JLabel(sumCost);
		costLabel.setFont(new Font("Dialog", Font.BOLD, 20));
		costLabel.setBounds(128, 372, 116, 25);
		dailyCostPanel.add(costLabel);

		nameTextField = new JTextField();
		nameTextField.setFont(new Font("Dialog", Font.PLAIN, 17));
		nameTextField.setColumns(10);
		nameTextField.setBounds(95, 48, 190, 30);
		dailyCostPanel.add(nameTextField);

		btnCostDone.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {

				String nm = nameTextField.getText().toString();
				String cost = amountTextField.getText().toString();
				String date = DateTextField.getText().toString();

				if (!nm.equals("") && !cost.equals("") && !date.equals("")) {

					// insert cost information into database

					int cst = Integer.parseInt(cost);

					String insertSql = "INSERT INTO daily_cost VALUES(?,?,?)";
					con = ConnectionManager.getConnection();
					try {
						preStat = con.prepareStatement(insertSql);

						preStat.setString(1, nm);
						preStat.setInt(2, cst);
						preStat.setString(3, date);

						preStat.executeQuery();

						ConnectionManager.close();

						JOptionPane.showMessageDialog(contentPane,
								"Daily Cost Table Updated");
					} catch (SQLException e) {
						// TODO Auto-generated catch block
						System.out.println(e.getMessage());
					}

					// Show all cost information into table

					con = ConnectionManager.getConnection();
					query = "select count(name) from daily_cost";
					int sz = 0;

					try {
						stmt = con.createStatement();
						rs = stmt.executeQuery(query);
						while (rs.next()) {
							sz = rs.getInt("count(name)");
							// System.out.println(n);
						}
					} catch (SQLException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
					ConnectionManager.close();

					con = ConnectionManager.getConnection();
					query = "select name, cost_amount, cost_date from daily_cost";
					Object[][] costdata = new Object[sz][3];

					try {
						stmt = con.createStatement();
						rs = stmt.executeQuery(query);
						int i = 0;
						while (rs.next()) {

							id = rs.getString("name");
							costamount = rs.getInt("cost_amount");
							date = rs.getString("cost_date");

							costdata[i][0] = id;
							costdata[i][1] = costamount;
							costdata[i][2] = date;
							i++;
						}
					} catch (SQLException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
					ConnectionManager.close();

					costTable.setVisible(false);
					costScrollPane.setVisible(false);

					String[] costColumnName = { "Name", "Total Cost", "Date" };
					costTable = new JTable(costdata, costColumnName);
					costTable.setFont(new Font("SansSerif", Font.PLAIN, 17));
					costTable.setBounds(12, 164, 381, 196);
					dailyCostPanel.add(costTable);

					costScrollPane = new JScrollPane(costTable);
					costScrollPane.setBounds(12, 165, 381, 196);
					dailyCostPanel.add(costScrollPane);

					// ---Update total cost Label------

					con = ConnectionManager.getConnection();
					query = "select sum(cost_amount) from daily_cost";
					int sumCostAmount = 0;

					try {
						stmt = con.createStatement();
						rs = stmt.executeQuery(query);
						while (rs.next()) {
							sumCostAmount = rs.getInt("sum(cost_amount)");
							// System.out.println(sumDepositAmount);
						}
					} catch (SQLException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
					ConnectionManager.close();

					String sumCost = Integer.toString(sumCostAmount);
					costLabel.setText(sumCost);
				} else {
					JOptionPane.showMessageDialog(contentPane,
							"Enter All Information First");
				}
			}
		});

		// *************All Bills Panel***************

		allBillsPanel = new JPanel();
		tabbedPane.addTab("All Bills", null, allBillsPanel, null);
		allBillsPanel.setLayout(null);

		btnUpdateBills = new JButton("Update Bills");
		btnUpdateBills.setFont(new Font("SansSerif", Font.PLAIN, 20));
		btnUpdateBills.setToolTipText("Update All Bills Information");
		btnUpdateBills.setBounds(54, 12, 143, 36);
		allBillsPanel.add(btnUpdateBills);

		JLabel lblNewLabel_4 = new JLabel("House Rent");
		lblNewLabel_4.setFont(new Font("SansSerif", Font.PLAIN, 20));
		lblNewLabel_4.setBounds(32, 67, 121, 26);
		allBillsPanel.add(lblNewLabel_4);

		JLabel lblElectric = new JLabel("Electric Bills");
		lblElectric.setFont(new Font("SansSerif", Font.PLAIN, 20));
		lblElectric.setBounds(32, 105, 121, 26);
		allBillsPanel.add(lblElectric);

		JLabel lblGasBills = new JLabel("Gas Bills");
		lblGasBills.setFont(new Font("SansSerif", Font.PLAIN, 20));
		lblGasBills.setBounds(32, 143, 121, 26);
		allBillsPanel.add(lblGasBills);

		JLabel lblWaterBills = new JLabel("Water Bills");
		lblWaterBills.setFont(new Font("SansSerif", Font.PLAIN, 20));
		lblWaterBills.setBounds(32, 181, 121, 26);
		allBillsPanel.add(lblWaterBills);

		JLabel lblCheafBills = new JLabel("Chef Bills");
		lblCheafBills.setFont(new Font("SansSerif", Font.PLAIN, 20));
		lblCheafBills.setBounds(32, 219, 121, 26);
		allBillsPanel.add(lblCheafBills);

		JLabel lblGarbaseBills = new JLabel("Garbase Bills");
		lblGarbaseBills.setFont(new Font("SansSerif", Font.PLAIN, 20));
		lblGarbaseBills.setBounds(32, 257, 121, 26);
		allBillsPanel.add(lblGarbaseBills);

		JLabel lblServantBills = new JLabel("Servant Bills");
		lblServantBills.setFont(new Font("SansSerif", Font.PLAIN, 20));
		lblServantBills.setBounds(32, 295, 121, 26);
		allBillsPanel.add(lblServantBills);

		JLabel lblInternetBills = new JLabel("Internet Bills");
		lblInternetBills.setFont(new Font("SansSerif", Font.PLAIN, 20));
		lblInternetBills.setBounds(32, 333, 121, 26);
		allBillsPanel.add(lblInternetBills);

		JLabel lblTotalBills = new JLabel("Total  Bills");
		lblTotalBills.setForeground(new Color(0, 0, 255));
		lblTotalBills.setFont(new Font("SansSerif", Font.PLAIN, 20));
		lblTotalBills.setBounds(54, 371, 148, 26);
		allBillsPanel.add(lblTotalBills);

		houseRent = new JLabel();
		houseRent.setFont(new Font("SansSerif", Font.PLAIN, 20));
		houseRent.setBounds(209, 67, 121, 26);
		allBillsPanel.add(houseRent);

		ElectricBills = new JLabel();
		ElectricBills.setFont(new Font("SansSerif", Font.PLAIN, 20));
		ElectricBills.setBounds(209, 105, 121, 26);
		allBillsPanel.add(ElectricBills);

		gasBills = new JLabel();
		gasBills.setFont(new Font("SansSerif", Font.PLAIN, 20));
		gasBills.setBounds(209, 143, 121, 26);
		allBillsPanel.add(gasBills);

		waterBills = new JLabel();
		waterBills.setFont(new Font("SansSerif", Font.PLAIN, 20));
		waterBills.setBounds(209, 181, 121, 26);
		allBillsPanel.add(waterBills);

		chefBills = new JLabel();
		chefBills.setFont(new Font("SansSerif", Font.PLAIN, 20));
		chefBills.setBounds(209, 219, 121, 26);
		allBillsPanel.add(chefBills);

		garbaseBills = new JLabel();
		garbaseBills.setFont(new Font("SansSerif", Font.PLAIN, 20));
		garbaseBills.setBounds(209, 257, 121, 26);
		allBillsPanel.add(garbaseBills);

		servantBills = new JLabel();
		servantBills.setFont(new Font("SansSerif", Font.PLAIN, 20));
		servantBills.setBounds(209, 295, 121, 26);
		allBillsPanel.add(servantBills);

		internetBills = new JLabel();
		internetBills.setFont(new Font("SansSerif", Font.PLAIN, 20));
		internetBills.setBounds(209, 333, 121, 26);
		allBillsPanel.add(internetBills);

		totalBills = new JLabel();
		totalBills.setForeground(Color.BLUE);
		totalBills.setFont(new Font("SansSerif", Font.PLAIN, 20));
		totalBills.setBounds(245, 371, 110, 26);
		allBillsPanel.add(totalBills);

		// ----For All Label Data Show----------

		con = ConnectionManager.getConnection();
		query = "select * from bills";
		int house = 0;
		int electric = 0;
		int gas = 0;
		int water = 0;
		int chef = 0;
		int garbase = 0;
		int servant = 0;
		int internet = 0;

		try {
			stmt = con.createStatement();
			rs = stmt.executeQuery(query);
			while (rs.next()) {

				house = rs.getInt("house_rent");
				electric = rs.getInt("electric_bills");
				gas = rs.getInt("gas_bills");
				water = rs.getInt("water_bills");
				chef = rs.getInt("chef_bills");
				garbase = rs.getInt("garbase_bills");
				servant = rs.getInt("servant_bills");
				internet = rs.getInt("internet_bills");
			}
		} catch (SQLException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		ConnectionManager.close();

		houseRent.setText(Integer.toString(house));
		ElectricBills.setText(Integer.toString(electric));
		gasBills.setText(Integer.toString(gas));
		waterBills.setText(Integer.toString(water));
		chefBills.setText(Integer.toString(chef));
		garbaseBills.setText(Integer.toString(garbase));
		servantBills.setText(Integer.toString(servant));
		internetBills.setText(Integer.toString(internet));

		int total = house + electric + gas + water + chef + garbase + servant
				+ internet;
		totalBills.setText(Integer.toString(total));

		// -------- End all lavel data input---------------

		btnUpdateBills.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				
				try {
					for (LookAndFeelInfo info : UIManager
							.getInstalledLookAndFeels()) {
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
							UpdateBills frame = new UpdateBills();
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

		// *************Final Result Panel***************

		finalResultPanel = new JPanel();
		tabbedPane.addTab("Final Result", null, finalResultPanel, null);
		finalResultPanel.setLayout(null);
		
		JLabel lblNewLabel_5 = new JLabel("Total Deposit");
		lblNewLabel_5.setFont(new Font("SansSerif", Font.PLAIN, 20));
		lblNewLabel_5.setBounds(51, 6, 124, 24);
		finalResultPanel.add(lblNewLabel_5);
		
		JLabel lblTotalDeposit_1 = new JLabel("Total Cost");
		lblTotalDeposit_1.setFont(new Font("SansSerif", Font.PLAIN, 20));
		lblTotalDeposit_1.setBounds(51, 42, 124, 24);
		finalResultPanel.add(lblTotalDeposit_1);
		
		JLabel lblTotalCost_1 = new JLabel("Total Meal");
		lblTotalCost_1.setFont(new Font("SansSerif", Font.PLAIN, 20));
		lblTotalCost_1.setBounds(51, 78, 124, 24);
		finalResultPanel.add(lblTotalCost_1);
		
		JLabel lblCurrentBalance = new JLabel("Current Balance");
		lblCurrentBalance.setFont(new Font("SansSerif", Font.PLAIN, 20));
		lblCurrentBalance.setBounds(51, 149, 141, 24);
		finalResultPanel.add(lblCurrentBalance);
		
		JLabel totalDeposit = new JLabel("");
		totalDeposit.setFont(new Font("SansSerif", Font.PLAIN, 20));
		totalDeposit.setBounds(215, 6, 124, 24);
		finalResultPanel.add(totalDeposit);
		
		JLabel totalCost = new JLabel("");
		totalCost.setFont(new Font("SansSerif", Font.PLAIN, 20));
		totalCost.setBounds(215, 42, 124, 24);
		finalResultPanel.add(totalCost);
		
		JLabel totalMeal = new JLabel("");
		totalMeal.setFont(new Font("SansSerif", Font.PLAIN, 20));
		totalMeal.setBounds(215, 78, 124, 24);
		finalResultPanel.add(totalMeal);
		
		JLabel currentBalance = new JLabel("");
		currentBalance.setFont(new Font("SansSerif", Font.PLAIN, 20));
		currentBalance.setBounds(215, 149, 124, 24);
		finalResultPanel.add(currentBalance);
		
		JLabel lblTotalMeal_1 = new JLabel("Meal Rate");
		lblTotalMeal_1.setFont(new Font("SansSerif", Font.PLAIN, 20));
		lblTotalMeal_1.setBounds(51, 114, 124, 24);
		finalResultPanel.add(lblTotalMeal_1);
		
		JLabel mealRate = new JLabel("");
		mealRate.setFont(new Font("SansSerif", Font.PLAIN, 20));
		mealRate.setBounds(215, 114, 124, 24);
		finalResultPanel.add(mealRate);
		
		//----- Find Total deposit---------
		
		con = ConnectionManager.getConnection();
		query = "select sum(deposit_amount) from deposit";
		int totaldep = 0;

		try {
			stmt = con.createStatement();
			rs = stmt.executeQuery(query);
			while (rs.next()) {
				totaldep = rs.getInt("sum(deposit_amount)");
				// System.out.println(sumDepositAmount);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		ConnectionManager.close();

		String sumDep = Integer.toString(totaldep);
		totalDeposit.setText(sumDep);
		
		//------End Find deposit--------
		
		//-----Find Total Cost-----
		
		con = ConnectionManager.getConnection();
		query = "select sum(cost_amount) from daily_cost";
		int CostAmount = 0;

		try {
			stmt = con.createStatement();
			rs = stmt.executeQuery(query);
			while (rs.next()) {
				CostAmount = rs.getInt("sum(cost_amount)");
				// System.out.println(sumDepositAmount);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		ConnectionManager.close();

		String totalCst = Integer.toString(CostAmount);
		totalCost.setText(totalCst);
		
		//-----End find total Cost-------
		
		//-----Find Total meal--------
		
		con = ConnectionManager.getConnection();
		query = "select sum(meal_amount) from meal_info";
		int MealAmount = 0;

		try {
			stmt = con.createStatement();
			rs = stmt.executeQuery(query);
			while (rs.next()) {
				MealAmount = rs.getInt("sum(meal_amount)");
				// System.out.println(sumDepositAmount);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		ConnectionManager.close();

		String MealAmt = Integer.toString(MealAmount);
		totalMeal.setText(MealAmt);
		
		//----End find total Meal-------
		
		//---find meal rate------
		
		Double rate = (double) (CostAmount/MealAmount);
		mealRate.setText(Double.toString(rate));
		
		//---end find meal rate-----
		
		//-----find Current Balance-----
		
		int currentamount = totaldep - CostAmount;
		currentBalance.setText(Integer.toString(currentamount));
		
		//-----end Find Current Balance-----
		
		table = new JTable();
		table.setBounds(6, 187, 387, 186);
		finalResultPanel.add(table);
		
		JScrollPane finalscrollPane = new JScrollPane(table);
		finalscrollPane.setBounds(6, 187, 387, 186);
		finalResultPanel.add(finalscrollPane);
	}
}
