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

public class UpdateBills extends JFrame {

	private JPanel contentPane;
	private JTextField houseRent;
	private JTextField ElectricBills;
	private JTextField gasBills;
	private JTextField waterBills;
	private JTextField chefBills;
	private JTextField garbaseBills;
	private JTextField servantBills;
	private JTextField internetBills;

	private Connection con = null;
	private Statement stmt = null;
	private PreparedStatement preStat = null;
	private ResultSet rs;

	private String query;

	/**
	 * Create the frame.
	 */
	public UpdateBills() {
		setTitle("Update Bills");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 414, 491);
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

		// ------END Menu--------

		JLabel lblNewLabel = new JLabel("Enter All Information");
		lblNewLabel.setFont(new Font("SansSerif", Font.PLAIN, 25));
		lblNewLabel.setBounds(87, 6, 238, 35);
		contentPane.add(lblNewLabel);

		JLabel lblNewLabel_1 = new JLabel("House Rent");
		lblNewLabel_1.setFont(new Font("SansSerif", Font.PLAIN, 20));
		lblNewLabel_1.setBounds(21, 53, 134, 29);
		contentPane.add(lblNewLabel_1);

		JLabel lblElectricBills = new JLabel("Electric Bills");
		lblElectricBills.setFont(new Font("SansSerif", Font.PLAIN, 20));
		lblElectricBills.setBounds(21, 94, 134, 29);
		contentPane.add(lblElectricBills);

		JLabel lblGasBills = new JLabel("Gas Bills");
		lblGasBills.setFont(new Font("SansSerif", Font.PLAIN, 20));
		lblGasBills.setBounds(21, 135, 134, 29);
		contentPane.add(lblGasBills);

		JLabel lblWaterBills = new JLabel("Water Bills");
		lblWaterBills.setFont(new Font("SansSerif", Font.PLAIN, 20));
		lblWaterBills.setBounds(21, 176, 134, 29);
		contentPane.add(lblWaterBills);

		JLabel lblChefBills = new JLabel("Chef Bills");
		lblChefBills.setFont(new Font("SansSerif", Font.PLAIN, 20));
		lblChefBills.setBounds(21, 217, 134, 29);
		contentPane.add(lblChefBills);

		JLabel lblGarbaseBills = new JLabel("Garbase Bills");
		lblGarbaseBills.setFont(new Font("SansSerif", Font.PLAIN, 20));
		lblGarbaseBills.setBounds(21, 258, 134, 29);
		contentPane.add(lblGarbaseBills);

		JLabel lblServantBills = new JLabel("Servant Bills");
		lblServantBills.setFont(new Font("SansSerif", Font.PLAIN, 20));
		lblServantBills.setBounds(21, 299, 134, 29);
		contentPane.add(lblServantBills);

		JLabel lblInternetBills = new JLabel("Internet Bills");
		lblInternetBills.setFont(new Font("SansSerif", Font.PLAIN, 20));
		lblInternetBills.setBounds(21, 340, 134, 29);
		contentPane.add(lblInternetBills);

		houseRent = new JTextField();
		houseRent.setFont(new Font("SansSerif", Font.PLAIN, 20));
		houseRent.setBounds(191, 53, 182, 29);
		contentPane.add(houseRent);
		houseRent.setColumns(10);
		houseRent.setUI(new HintTextFieldUI("Enter House Rent", true));

		ElectricBills = new JTextField();
		ElectricBills.setFont(new Font("SansSerif", Font.PLAIN, 20));
		ElectricBills.setColumns(10);
		ElectricBills.setBounds(191, 94, 182, 29);
		contentPane.add(ElectricBills);
		ElectricBills.setUI(new HintTextFieldUI("Enter Electric Bills", true));

		gasBills = new JTextField();
		gasBills.setFont(new Font("SansSerif", Font.PLAIN, 20));
		gasBills.setColumns(10);
		gasBills.setBounds(191, 135, 182, 29);
		contentPane.add(gasBills);
		gasBills.setUI(new HintTextFieldUI("Enter Gas Bills", true));

		waterBills = new JTextField();
		waterBills.setFont(new Font("SansSerif", Font.PLAIN, 20));
		waterBills.setColumns(10);
		waterBills.setBounds(191, 176, 182, 29);
		contentPane.add(waterBills);
		waterBills.setUI(new HintTextFieldUI("Enter Water Bills", true));

		chefBills = new JTextField();
		chefBills.setFont(new Font("SansSerif", Font.PLAIN, 20));
		chefBills.setColumns(10);
		chefBills.setBounds(191, 217, 182, 29);
		contentPane.add(chefBills);
		chefBills.setUI(new HintTextFieldUI("Enter Chef Bills", true));

		garbaseBills = new JTextField();
		garbaseBills.setFont(new Font("SansSerif", Font.PLAIN, 20));
		garbaseBills.setColumns(10);
		garbaseBills.setBounds(191, 258, 182, 29);
		contentPane.add(garbaseBills);
		garbaseBills.setUI(new HintTextFieldUI("Enter Garbase Bills", true));

		servantBills = new JTextField();
		servantBills.setFont(new Font("SansSerif", Font.PLAIN, 20));
		servantBills.setColumns(10);
		servantBills.setBounds(191, 299, 182, 29);
		contentPane.add(servantBills);
		servantBills.setUI(new HintTextFieldUI("Enter Servant Bills", true));

		internetBills = new JTextField();
		internetBills.setFont(new Font("SansSerif", Font.PLAIN, 20));
		internetBills.setColumns(10);
		internetBills.setBounds(191, 340, 182, 29);
		contentPane.add(internetBills);
		internetBills.setUI(new HintTextFieldUI("Enter Internet Bills", true));

		JButton btnDone = new JButton("Done");
		btnDone.setFont(new Font("SansSerif", Font.PLAIN, 25));
		btnDone.setBounds(302, 381, 90, 35);
		contentPane.add(btnDone);

		JButton btnClear = new JButton("Clear");
		btnClear.setFont(new Font("SansSerif", Font.PLAIN, 25));
		btnClear.setBounds(201, 381, 90, 35);
		contentPane.add(btnClear);

		btnDone.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {

				String a = houseRent.getText().toString();
				String b = ElectricBills.getText().toString();
				String c = gasBills.getText().toString();
				String d = waterBills.getText().toString();
				String e = chefBills.getText().toString();
				String f = garbaseBills.getText().toString();
				String g = servantBills.getText().toString();
				String h = internetBills.getText().toString();

				if (!a.equals("") && !b.equals("") && !c.equals("")
						&& !d.equals("") && !e.equals("") && !f.equals("")
						&& !g.equals("") && !h.equals("")) {

					int houseRent1 = Integer.parseInt(a);
					int electricbills1 = Integer.parseInt(b);
					int gasBills1 = Integer.parseInt(c);
					int waterBills1 = Integer.parseInt(d);
					int chefBills1 = Integer.parseInt(e);
					int garbaseBills1 = Integer.parseInt(f);
					int servantBills1 = Integer.parseInt(g);
					int internetBills1 = Integer.parseInt(h);

					con = ConnectionManager.getConnection();
					query = "select house_rent from bills";
					int bills = 0;

					try {
						stmt = con.createStatement();
						rs = stmt.executeQuery(query);
						while (rs.next()) {

							bills = rs.getInt("house_rent");
						}
					} catch (SQLException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
					ConnectionManager.close();

					// ----First delete existing data----

					con = ConnectionManager.getConnection();
					query = "delete bills where house_rent = ?";

					try {

						preStat = con.prepareStatement(query);
						preStat.setInt(1, bills);
						preStat.executeUpdate();

					} catch (SQLException e2) {
						System.out.println(e2.getMessage());
					}
					ConnectionManager.close();

					// -----Insert Into Database-------------

					String insertSql = "INSERT INTO bills VALUES(?,?,?,?,?,?,?,?)";
					con = ConnectionManager.getConnection();
					try {
						preStat = con.prepareStatement(insertSql);

						preStat.setInt(1, houseRent1);
						preStat.setInt(2, electricbills1);
						preStat.setInt(3, gasBills1);
						preStat.setInt(4, waterBills1);
						preStat.setInt(5, chefBills1);
						preStat.setInt(6, garbaseBills1);
						preStat.setInt(7, servantBills1);
						preStat.setInt(8, internetBills1);

						preStat.executeQuery();

						ConnectionManager.close();
						JOptionPane.showMessageDialog(contentPane,
								"All Bills are Updated..");

					} catch (SQLException e2) {
						// TODO Auto-generated catch block

					}

				} else {
					JOptionPane.showMessageDialog(contentPane,
							"Enter All Information First!");
				}

			}
		});

		btnClear.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				houseRent.setText("");
				ElectricBills.setText("");
				gasBills.setText("");
				waterBills.setText("");
				chefBills.setText("");
				garbaseBills.setText("");
				servantBills.setText("");
				internetBills.setText("");

			}
		});
	}
}
