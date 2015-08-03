package code;

import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTabbedPane;
import javax.swing.SwingConstants;
import javax.swing.UIManager;
import javax.swing.UIManager.LookAndFeelInfo;
import javax.swing.border.EmptyBorder;
import javax.swing.JButton;

public class UserView extends JFrame {

	private JPanel contentPane;
	private JTabbedPane tabbedPane;
	private JPanel messageToAdmin;

	private JPanel finalCalculation;

	private JLabel nameLabel;
	private JLabel depositLabel;
	private JLabel mealLabel;
	private JLabel rateLabel;
	private JLabel debitLabel;
	private JLabel creditLabel;
	private JLabel TotalPayableBillLabel;
	private JLabel depositAmount;
	private JLabel mealAmount;
	private JLabel mealRateAmount;
	private JLabel debitAmount;
	private JLabel creditAmount;
	private JLabel totalAmount;

	private JPanel allBills;

	private JLabel houseRent;
	private JLabel electricBill;
	private JLabel gasBill;
	private JLabel waterBill;
	private JLabel chefBill;
	private JLabel garbaseBill;
	private JLabel servantCost;
	private JLabel internetBill;
	private JLabel userFixedBill;
	private JLabel houseRentAmount;
	private JLabel electricBillAmount;
	private JLabel gasBillAmount;
	private JLabel waterBillAmount;
	private JLabel chefBillAmount;
	private JLabel garbaseBillAmount;
	private JLabel servantCostAmount;
	private JLabel internetBillAmount;
	private JLabel fixedBillAmount;

	private Connection con = null;
	private Statement stmt = null;
	private PreparedStatement preStat = null;
	private ResultSet rs;
	private String query;
	
	private Double perUserAmount;
	private JLabel lblNewLabel;
	private JLabel lblName;
	private JLabel lblAddress;
	private JLabel lblPhone;
	private JLabel lblEmail;
	private JLabel lblPassword;
	private JLabel userID;
	private JLabel userName;
	private JLabel userAddress;
	private JLabel userPhone;
	private JLabel userEmail;
	private JLabel userPassword;
	
	private String id=null;

	/**
	 * Create the frame.
	 */
	public UserView(String id1) {
		id = id1;
		setTitle("Hostel Management System");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 493);
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

		// -----Name Show............

		con = ConnectionManager.getConnection();
		query = "select name from member where id = " + "'" + id1 + "'";
		String name = null;

		try {
			stmt = con.createStatement();
			rs = stmt.executeQuery(query);
			while (rs.next()) {
				name = rs.getString("name");
				// System.out.println(n);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		ConnectionManager.close();

		// ----- Show total deposit---------

		con = ConnectionManager.getConnection();
		query = "select deposit_amount from deposit where id = " + "'" + id1
				+ "'";
		int deposit = 0;

		try {
			stmt = con.createStatement();
			rs = stmt.executeQuery(query);
			while (rs.next()) {
				deposit = rs.getInt("deposit_amount");
				// System.out.println(n);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		ConnectionManager.close();

		// --------Show total meal-------------

		con = ConnectionManager.getConnection();
		query = "select meal_amount from meal_info where id = " + "'" + id1
				+ "'";
		int meal = 0;

		try {
			stmt = con.createStatement();
			rs = stmt.executeQuery(query);
			while (rs.next()) {
				meal = rs.getInt("meal_amount");
				// System.out.println(n);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		ConnectionManager.close();

		// **------find meal Rate---------

		// -----Find Total Cost-----

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

		// -----End find total Cost-------

		// -----Find Total meal--------

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

		// ----End find total Meal-------

		// ---find meal rate------

		Double rate = (double) (CostAmount / MealAmount);

		// ---end find meal rate-----

		// ------find Debit-------

		Double debit = rate * meal;

		// ------find Credit--------

		Double credit = deposit - debit;

		// *************All Bills Panel***************

		allBills = new JPanel();
		tabbedPane.addTab("All Bills", null, allBills, null);
		allBills.setLayout(null);

		houseRent = new JLabel("House Rent");
		houseRent.setFont(new Font("SansSerif", Font.PLAIN, 20));
		houseRent.setBounds(31, 19, 128, 31);
		allBills.add(houseRent);

		electricBill = new JLabel("Electric Bill");
		electricBill.setFont(new Font("SansSerif", Font.PLAIN, 20));
		electricBill.setBounds(31, 62, 128, 31);
		allBills.add(electricBill);

		gasBill = new JLabel("Gas Bill");
		gasBill.setFont(new Font("SansSerif", Font.PLAIN, 20));
		gasBill.setBounds(31, 105, 128, 31);
		allBills.add(gasBill);

		waterBill = new JLabel("Water Bill");
		waterBill.setFont(new Font("SansSerif", Font.PLAIN, 20));
		waterBill.setBounds(31, 148, 128, 31);
		allBills.add(waterBill);

		chefBill = new JLabel("Chef Bill");
		chefBill.setFont(new Font("SansSerif", Font.PLAIN, 20));
		chefBill.setBounds(31, 191, 128, 31);
		allBills.add(chefBill);

		garbaseBill = new JLabel("Garbase Bill");
		garbaseBill.setFont(new Font("SansSerif", Font.PLAIN, 20));
		garbaseBill.setBounds(31, 234, 128, 31);
		allBills.add(garbaseBill);

		servantCost = new JLabel("Servant Cost");
		servantCost.setFont(new Font("SansSerif", Font.PLAIN, 20));
		servantCost.setBounds(31, 277, 128, 31);
		allBills.add(servantCost);

		internetBill = new JLabel("Internet Bill");
		internetBill.setFont(new Font("SansSerif", Font.PLAIN, 20));
		internetBill.setBounds(31, 320, 128, 31);
		allBills.add(internetBill);

		userFixedBill = new JLabel("Per User Total Fixed Bills");
		userFixedBill.setFont(new Font("SansSerif", Font.PLAIN, 20));
		userFixedBill.setBounds(31, 363, 233, 31);
		allBills.add(userFixedBill);

		houseRentAmount = new JLabel("0");
		houseRentAmount.setFont(new Font("SansSerif", Font.PLAIN, 20));
		houseRentAmount.setBounds(197, 19, 128, 31);
		allBills.add(houseRentAmount);

		electricBillAmount = new JLabel("0");
		electricBillAmount.setFont(new Font("SansSerif", Font.PLAIN, 20));
		electricBillAmount.setBounds(197, 62, 128, 31);
		allBills.add(electricBillAmount);

		gasBillAmount = new JLabel("0");
		gasBillAmount.setFont(new Font("SansSerif", Font.PLAIN, 20));
		gasBillAmount.setBounds(197, 105, 128, 31);
		allBills.add(gasBillAmount);

		waterBillAmount = new JLabel("0");
		waterBillAmount.setFont(new Font("SansSerif", Font.PLAIN, 20));
		waterBillAmount.setBounds(197, 148, 128, 31);
		allBills.add(waterBillAmount);

		chefBillAmount = new JLabel("0");
		chefBillAmount.setFont(new Font("SansSerif", Font.PLAIN, 20));
		chefBillAmount.setBounds(197, 191, 128, 31);
		allBills.add(chefBillAmount);

		garbaseBillAmount = new JLabel("0");
		garbaseBillAmount.setFont(new Font("SansSerif", Font.PLAIN, 20));
		garbaseBillAmount.setBounds(197, 234, 128, 31);
		allBills.add(garbaseBillAmount);

		servantCostAmount = new JLabel("0");
		servantCostAmount.setFont(new Font("SansSerif", Font.PLAIN, 20));
		servantCostAmount.setBounds(197, 277, 128, 31);
		allBills.add(servantCostAmount);

		internetBillAmount = new JLabel("0");
		internetBillAmount.setFont(new Font("SansSerif", Font.PLAIN, 20));
		internetBillAmount.setBounds(197, 320, 128, 31);
		allBills.add(internetBillAmount);

		fixedBillAmount = new JLabel("0");
		fixedBillAmount.setFont(new Font("SansSerif", Font.PLAIN, 20));
		fixedBillAmount.setBounds(290, 363, 128, 31);
		allBills.add(fixedBillAmount);

		// ----Show All Bills-----------

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
		
		houseRentAmount.setText(Integer.toString(house));
		electricBillAmount.setText(Integer.toString(electric));
		gasBillAmount.setText(Integer.toString(gas));
		waterBillAmount.setText(Integer.toString(water));
		chefBillAmount.setText(Integer.toString(chef));
		garbaseBillAmount.setText(Integer.toString(garbase));
		servantCostAmount.setText(Integer.toString(servant));
		internetBillAmount.setText(Integer.toString(internet));
		
		int total = house + electric + gas + water + chef + garbase + servant
				+ internet;
		
		con = ConnectionManager.getConnection();
		query = "select count(id) from member";
		int count = 0;

		try {
			stmt = con.createStatement();
			rs = stmt.executeQuery(query);
			while (rs.next()) {

				count = rs.getInt("count(id)");
				
			}
		} catch (SQLException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		ConnectionManager.close();
		
		perUserAmount = (double) (total / count);
		fixedBillAmount.setText(Double.toString(perUserAmount));

		// *************Final Calculation Panel***************

		finalCalculation = new JPanel();
		tabbedPane.addTab("Final Calculation", null, finalCalculation, null);
		finalCalculation.setLayout(null);

		nameLabel = new JLabel("Tanvir Hossain");
		nameLabel.setFont(new Font("SansSerif", Font.PLAIN, 25));
		nameLabel.setHorizontalAlignment(SwingConstants.CENTER);
		nameLabel.setBounds(6, 6, 401, 41);
		finalCalculation.add(nameLabel);

		depositLabel = new JLabel("Total Deposit");
		depositLabel.setFont(new Font("SansSerif", Font.PLAIN, 20));
		depositLabel.setHorizontalAlignment(SwingConstants.LEFT);
		depositLabel.setBounds(16, 59, 152, 35);
		finalCalculation.add(depositLabel);

		mealLabel = new JLabel("Total Meal");
		mealLabel.setHorizontalAlignment(SwingConstants.LEFT);
		mealLabel.setFont(new Font("SansSerif", Font.PLAIN, 20));
		mealLabel.setBounds(16, 106, 152, 35);
		finalCalculation.add(mealLabel);

		rateLabel = new JLabel("Meal Rate");
		rateLabel.setHorizontalAlignment(SwingConstants.LEFT);
		rateLabel.setFont(new Font("SansSerif", Font.PLAIN, 20));
		rateLabel.setBounds(16, 153, 152, 35);
		finalCalculation.add(rateLabel);

		debitLabel = new JLabel("Debit");
		debitLabel.setHorizontalAlignment(SwingConstants.LEFT);
		debitLabel.setFont(new Font("SansSerif", Font.PLAIN, 20));
		debitLabel.setBounds(16, 200, 152, 35);
		finalCalculation.add(debitLabel);

		creditLabel = new JLabel("Credit");
		creditLabel.setHorizontalAlignment(SwingConstants.LEFT);
		creditLabel.setFont(new Font("SansSerif", Font.PLAIN, 20));
		creditLabel.setBounds(16, 247, 152, 35);
		finalCalculation.add(creditLabel);

		TotalPayableBillLabel = new JLabel("Total Payable Amount");
		TotalPayableBillLabel.setHorizontalAlignment(SwingConstants.LEFT);
		TotalPayableBillLabel.setFont(new Font("SansSerif", Font.PLAIN, 20));
		TotalPayableBillLabel.setBounds(29, 308, 199, 35);
		finalCalculation.add(TotalPayableBillLabel);

		depositAmount = new JLabel("0");
		depositAmount.setFont(new Font("SansSerif", Font.PLAIN, 20));
		depositAmount.setBounds(210, 59, 101, 35);
		finalCalculation.add(depositAmount);

		mealAmount = new JLabel("0");
		mealAmount.setFont(new Font("SansSerif", Font.PLAIN, 20));
		mealAmount.setBounds(210, 106, 101, 35);
		finalCalculation.add(mealAmount);

		mealRateAmount = new JLabel("0");
		mealRateAmount.setFont(new Font("SansSerif", Font.PLAIN, 20));
		mealRateAmount.setBounds(210, 153, 101, 35);
		finalCalculation.add(mealRateAmount);

		debitAmount = new JLabel("0");
		debitAmount.setFont(new Font("SansSerif", Font.PLAIN, 20));
		debitAmount.setBounds(210, 200, 101, 35);
		finalCalculation.add(debitAmount);

		creditAmount = new JLabel("0");
		creditAmount.setFont(new Font("SansSerif", Font.PLAIN, 20));
		creditAmount.setBounds(210, 247, 101, 35);
		finalCalculation.add(creditAmount);

		totalAmount = new JLabel("0");
		totalAmount.setFont(new Font("SansSerif", Font.PLAIN, 20));
		totalAmount.setBounds(264, 308, 101, 35);
		finalCalculation.add(totalAmount);
		
		nameLabel.setText(name);
		depositAmount.setText(Integer.toString(deposit));
		mealAmount.setText(Integer.toString(meal));
		mealRateAmount.setText(Double.toString(rate));
		debitAmount.setText(Double.toString(debit));
		creditAmount.setText(Double.toString(credit));
		
		Double payable = perUserAmount - credit;
		totalAmount.setText(Double.toString(payable));
		
		
		//------------ABOUT PANEL------------
		
		JPanel about = new JPanel();
		tabbedPane.addTab("About", null, about, null);
		about.setLayout(null);
		
		lblNewLabel = new JLabel("ID");
		lblNewLabel.setFont(new Font("SansSerif", Font.PLAIN, 20));
		lblNewLabel.setBounds(6, 19, 92, 31);
		about.add(lblNewLabel);
		
		lblName = new JLabel("Name");
		lblName.setFont(new Font("SansSerif", Font.PLAIN, 20));
		lblName.setBounds(6, 62, 92, 31);
		about.add(lblName);
		
		lblAddress = new JLabel("Address");
		lblAddress.setFont(new Font("SansSerif", Font.PLAIN, 20));
		lblAddress.setBounds(6, 105, 92, 31);
		about.add(lblAddress);
		
		lblPhone = new JLabel("Phone");
		lblPhone.setFont(new Font("SansSerif", Font.PLAIN, 20));
		lblPhone.setBounds(6, 148, 92, 31);
		about.add(lblPhone);
		
		lblEmail = new JLabel("Email");
		lblEmail.setFont(new Font("SansSerif", Font.PLAIN, 20));
		lblEmail.setBounds(6, 191, 92, 31);
		about.add(lblEmail);
		
		lblPassword = new JLabel("Password");
		lblPassword.setFont(new Font("SansSerif", Font.PLAIN, 20));
		lblPassword.setBounds(6, 234, 92, 31);
		about.add(lblPassword);
		
		userID = new JLabel("");
		userID.setFont(new Font("SansSerif", Font.PLAIN, 20));
		userID.setBounds(130, 19, 210, 31);
		about.add(userID);
		
		userName = new JLabel("");
		userName.setFont(new Font("SansSerif", Font.PLAIN, 20));
		userName.setBounds(130, 62, 210, 31);
		about.add(userName);
		
		userAddress = new JLabel("");
		userAddress.setFont(new Font("SansSerif", Font.PLAIN, 20));
		userAddress.setBounds(130, 105, 210, 31);
		about.add(userAddress);
		
		userPhone = new JLabel("");
		userPhone.setFont(new Font("SansSerif", Font.PLAIN, 20));
		userPhone.setBounds(130, 148, 210, 31);
		about.add(userPhone);
		
		userEmail = new JLabel("");
		userEmail.setFont(new Font("SansSerif", Font.PLAIN, 20));
		userEmail.setBounds(130, 191, 210, 31);
		about.add(userEmail);
		
		userPassword = new JLabel("");
		userPassword.setFont(new Font("SansSerif", Font.PLAIN, 20));
		userPassword.setBounds(130, 234, 210, 31);
		about.add(userPassword);
		
		//---------User Information----------
		
		String name1 = null, address1 = null, phone1 = null, email1 = null, password1 = null;
		con = ConnectionManager.getConnection();

		String query = "select name, address, phone, email, password from member where id = "
				+ "'" + id + "'";

		try {
			stmt = con.createStatement();
			rs = stmt.executeQuery(query);
			while (rs.next()) {
				name1 = rs.getString("name");
				address1 = rs.getString("address");
				phone1 = rs.getString("phone");
				email1 = rs.getString("email");
				password1 = rs.getString("password");
			}
		} catch (SQLException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		ConnectionManager.close();

		userID.setText(id);
		userName.setText(name1);
		userAddress.setText(address1);
		userPhone.setText(phone1);
		userEmail.setText(email1);
		userPassword.setText(password1);
		
		JButton btnaddressEdit = new JButton("Edit");
		btnaddressEdit.setFont(new Font("SansSerif", Font.PLAIN, 20));
		btnaddressEdit.setBounds(344, 106, 74, 28);
		about.add(btnaddressEdit);
		
		JButton btnPhoneEdit = new JButton("Edit");
		btnPhoneEdit.setFont(new Font("SansSerif", Font.PLAIN, 20));
		btnPhoneEdit.setBounds(344, 151, 74, 28);
		about.add(btnPhoneEdit);
		
		JButton btnEmailEdit = new JButton("Edit");
		btnEmailEdit.setFont(new Font("SansSerif", Font.PLAIN, 20));
		btnEmailEdit.setBounds(344, 191, 74, 28);
		about.add(btnEmailEdit);
		
		JButton btnPasswordEdit = new JButton("Edit");
		btnPasswordEdit.setFont(new Font("SansSerif", Font.PLAIN, 20));
		btnPasswordEdit.setBounds(344, 234, 74, 28);
		about.add(btnPasswordEdit);
		
		JButton btnDone = new JButton("Update");
		btnDone.setFont(new Font("SansSerif", Font.PLAIN, 25));
		btnDone.setBounds(130, 305, 115, 44);
		about.add(btnDone);
		
		btnDone.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				//write
				Object[] button = { "Cancel", "Ok" };
				int n = JOptionPane.showOptionDialog(contentPane,
						"Are You Sure?", "Update User",
						JOptionPane.YES_NO_CANCEL_OPTION,
						JOptionPane.QUESTION_MESSAGE, null, button,
						button[1]);
				
				if (button[n].equals("Ok")) {
					
					String address = userAddress.getText();
					String phone = userPhone.getText();
					String email = userEmail.getText();
					String password = userPassword.getText();

					con = ConnectionManager.getConnection();
					
					String addressquery = "update member set address = "
							+ "'" + address + "'" + "  where id = ?";
					String phonequery = "update member set phone = " + "'"
							+ phone + "'" + "  where id = ?";
					String emailquery = "update member set email = " + "'"
							+ email + "'" + "  where id = ?";
					String passwordquery = "update member set password = " + "'"
							+ password + "'" + "  where id = ?";

					try {
						
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
					
				}else{
					String address1 = null, phone1 = null, email1 = null, password1 = null;
					con = ConnectionManager.getConnection();

					String query = "select address, phone, email, password from member where id = "
							+ "'" + id + "'";

					try {
						stmt = con.createStatement();
						rs = stmt.executeQuery(query);
						while (rs.next()) {
							address1 = rs.getString("address");
							phone1 = rs.getString("phone");
							email1 = rs.getString("email");
							password1 = rs.getString("password");
						}
					} catch (SQLException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
					ConnectionManager.close();

					userAddress.setText(address1);
					userPhone.setText(phone1);
					userEmail.setText(email1);
					userPassword.setText(password1);
				}
				

			}
		});
		
		btnPasswordEdit.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				//write
				String str = userPassword.getText();
				String response = JOptionPane.showInputDialog(null, "",
						"Enter your Password", JOptionPane.QUESTION_MESSAGE);
				if (!response.equals("")) {
					userPassword.setText(response);
				} else {
					userPassword.setText(str);
				}
			}
		});
		
		btnEmailEdit.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				//write
				String str = userEmail.getText();
				String response = JOptionPane.showInputDialog(null, "",
						"Enter your Email address",
						JOptionPane.QUESTION_MESSAGE);
				if (!response.equals("")) {
					userEmail.setText(response);
				} else {
					userEmail.setText(str);
				}
			}
		});
		
		btnPhoneEdit.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				//write
				String str = userPhone.getText();
				String response = JOptionPane
						.showInputDialog(null, "", "Enter Your Phone number",
								JOptionPane.QUESTION_MESSAGE);
				if (!response.equals("")) {
					userPhone.setText(response);
				} else {
					userPhone.setText(str);
				}
			}
		});
		
		btnaddressEdit.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				//write
				String str = userAddress.getText();

				String response = JOptionPane.showInputDialog(null, "",
						"Enter your Address", JOptionPane.QUESTION_MESSAGE);
				if (!response.equals("")) {
					userAddress.setText(response);
				} else {
					userAddress.setText(str);
				}
			}
		});

		// *************Message To Admin Panel***************

		messageToAdmin = new JPanel();
		tabbedPane.addTab("Message to Admin", null, messageToAdmin, null);
		messageToAdmin.setLayout(null);
	}
}
