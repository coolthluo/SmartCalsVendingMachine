

import java.sql.*;
import java.util.ArrayList;
import java.util.Properties;

public class Database implements DBManager {
	private String connection;
	Properties property;

	public Database() {
		try {
			Class.forName("com.mysql.jdbc.Driver");
		} catch (ClassNotFoundException e1) {
		}
		connection = "jdbc:mysql://127.0.0.1/smartcalsvendingmachine";
		property = new Properties();
		property.put("user", "shan");
		property.put("password", "peach");
	}

	public Database(String connection, String user, String pwd) {
		try {
			Class.forName("com.mysql.jdbc.Driver");
		} catch (ClassNotFoundException e1) {
		}
		this.connection = connection;
		property = new Properties();
		property.put("user", user);
		property.put("password", pwd);
	}

	ResultSet select(String query) throws SQLException {
		Connection conn = DriverManager.getConnection(connection, property);
		Statement stmt = conn.createStatement(ResultSet.TYPE_FORWARD_ONLY,
				ResultSet.CONCUR_READ_ONLY);
		return stmt.executeQuery(query);
	}

	public int addItem(Item item) throws Exception {
		Connection conn = DriverManager.getConnection(connection, property);
		conn.setAutoCommit(false);
		Statement stmt = conn.createStatement(
				ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_UPDATABLE);
		ResultSet rs = stmt.executeQuery("SELECT MAX(ID) AS M FROM items");
		int code;
		if (rs.next()) {
			code = rs.getInt("M") + 1;
		} else {
			code = 1;
		}
		stmt.executeUpdate("INSERT INTO items VALUES (" + code
				+ ", '" + item.getName()
				+ "', '" + item.getType()
				+ "', '" + item.getInfo()
				+ "', " + item.getPrice()
				+ ", '" + item.getLastMod() + "');");
		conn.commit();
		conn.close();
		return code;
	}
	
	public void deleteItem(int code) throws Exception {
		Connection conn = DriverManager.getConnection(connection, property);
		conn.setAutoCommit(false);
		Statement stmt = conn.createStatement(
				ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_UPDATABLE);
		stmt.executeUpdate("DELETE FROM items WHERE ID=" + code + ";");
		conn.commit();
		conn.close();
	}

	public void updateItem(Item item) throws Exception {
		Connection conn = DriverManager.getConnection(connection, property);
		conn.setAutoCommit(false);
		Statement stmt = conn.createStatement(
				ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_UPDATABLE);
		stmt.executeUpdate("UPDATE items SET name = '" + item.getName()
				+ "', type='" + item.getType()
				+ "', info='" + item.getInfo()
				+ "', price=" + item.getPrice()
				+ ", lastmod='" + item.getLastMod()
				+ "' WHERE ID=" + item.getID() + ";");
		conn.commit();
		conn.close();
	}

	public ArrayList<Item> getAllItems() {
		ArrayList<Item> items = new ArrayList<Item>();
		try {
			ResultSet rs = select("SELECT * FROM items;");
			while (rs.next()) {
				items.add(new Item(rs));
			}
			rs.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return items;
	}

	public Item getItem(int code) {
		Item item = null;
		try {
			ResultSet rs = select("SELECT * FROM items WHERE ID=" + code + ";");
			if (rs.next()) {
				item = new Item(rs);
			}
			rs.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return item;
	}

	public int addEmployee(Employee e) throws Exception {
		Connection conn = DriverManager.getConnection(connection, property);
		conn.setAutoCommit(false);
		Statement stmt = conn.createStatement(
				ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_UPDATABLE);
		ResultSet rs = stmt.executeQuery("SELECT MAX(ID) AS M FROM employees;");
		int code;
		if (rs.next()) {
			code = rs.getInt("M") + 1;
		} else {
			code = 1;
		}
		stmt.executeUpdate("INSERT INTO employees VALUES ("
				+ code
				+ ", '" + e.getName()
				+ "', '" + e.getPassword()
				+ "', " + e.isManager() + ");");
		conn.commit();
		conn.close();
		return code;
	}
	
	public void deleteEmployee(int code) throws Exception {
		Connection conn = DriverManager.getConnection(connection, property);
		conn.setAutoCommit(false);
		Statement stmt = conn.createStatement(
				ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_UPDATABLE);
		stmt.executeUpdate("DELETE FROM employees WHERE ID=" + code + ";");
		conn.commit();
		conn.close();
	}
	
	public ArrayList<Employee> getAllEmployees() {
		ArrayList<Employee> employees = new ArrayList<Employee>();
		try {
			ResultSet rs = select("SELECT * FROM employees;");
			while (rs.next()) {
				employees.add(new Employee(rs));
			}
			rs.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return employees;
	}
	
	public Employee getEmployee(int code) {
		Employee employee = null;
		try {
			ResultSet rs = select("SELECT * FROM employees WHERE ID=" + code + ";");
			if (rs.next()) {
				employee = new Employee(rs);
			}
			rs.close();

		} catch (Exception ex) {
			ex.printStackTrace();
		}
		return employee;
	}

	public int addMachine(int code, String address, String startdate, String lastsync) throws Exception {
		Connection conn = DriverManager.getConnection(connection, property);
		conn.setAutoCommit(false);
		Statement stmt = conn.createStatement(
				ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_UPDATABLE);
		stmt.executeUpdate("INSERT INTO machines VALUES ("
				+ code + ", '" + address + "', '"
				+ startdate + "', '" + lastsync + "');");
		conn.commit();
		conn.close();
		return code;
	}
	
	public int checkMachine(int code) {
		int count = 0;
		try {
			ResultSet rs = select("SELECT COUNT(*) AS C FROM machines WHERE ID=" + code + ";");
			if (rs.next()) {
				count = rs.getInt("C");
			}
			rs.close();

		} catch (Exception ex) {
			ex.printStackTrace();
		}
		return count;
	}
	
	public void deleteMachine(int code) throws Exception {
		Connection conn = DriverManager.getConnection(connection, property);
		conn.setAutoCommit(false);
		Statement stmt = conn.createStatement(
				ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_UPDATABLE);
		stmt.executeUpdate("DELETE FROM machines WHERE ID=" + code + ";");
		conn.commit();
		conn.close();
	}
	
	public void updateMachineSyncDate(int code, String date) throws Exception {
		Connection conn = DriverManager.getConnection(connection, property);
		conn.setAutoCommit(false);
		Statement stmt = conn.createStatement(
				ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_UPDATABLE);
		stmt.executeUpdate("UPDATE machines SET lastsyne = '" + date
				+ "' WHERE ID=" + code + ";");
		conn.commit();
		conn.close();
	}

	public ArrayList<Machine> getAllMachines() {
		ArrayList<Machine> machines = new ArrayList<Machine>();
		try {
			ResultSet rs = select("SELECT * FROM machines;");
			while (rs.next()) {
				machines.add(new Machine(rs));
			}
			rs.close();

		} catch (Exception ex) {
			ex.printStackTrace();
		}
		return machines;
	}

	public Machine getMachine(int code) {
		Machine machine = null;
		try {
			ResultSet rs = select("SELECT * FROM machines WHERE ID=" + code + ";");
			if (rs.next()) {
				machine = new Machine(rs);
			}
			rs.close();

		} catch (Exception ex) {
			ex.printStackTrace();
		}
		return machine;
	}

	public void addItemToMachine(int machine, int item, int capacity, int quantity) throws Exception {
		Connection conn = DriverManager.getConnection(connection, property);
		conn.setAutoCommit(false);
		Statement stmt = conn.createStatement(
				ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_UPDATABLE);
		stmt.executeUpdate("INSERT INTO machine_item VALUES ("
				+ machine + ", " + item + ", " + capacity + ", " + quantity + ");");
		conn.commit();
		conn.close();
	}
	
	public void deleteItemFromMachine(int machine, int item) throws Exception {
		Connection conn = DriverManager.getConnection(connection, property);
		conn.setAutoCommit(false);
		Statement stmt = conn.createStatement(
				ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_UPDATABLE);
		stmt.executeUpdate("DELETE FROM machine_item WHERE machineid=" + machine + " AND itemid=" + item + ";");
		conn.commit();
		conn.close();
	}
	
	public void updateMachineItemQuantity(int machine, int item, int quantity) throws Exception {
		Connection conn = DriverManager.getConnection(connection, property);
		conn.setAutoCommit(false);
		Statement stmt = conn.createStatement(
				ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_UPDATABLE);
		stmt.executeUpdate("UPDATE machine_item SET quantity = " + quantity
				+ " WHERE machineid=" + machine + " AND itemid=" + item + ";");
		conn.commit();
		conn.close();
	}
	
	public ArrayList<MachineItem> getItemsOfMachine(int code) {
		ArrayList<MachineItem> items = new ArrayList<MachineItem>();
		try {
			ResultSet rs = select("SELECT * FROM machine_item WHERE machineid=" + code + ";");
			while (rs.next()) {
				items.add(new MachineItem(rs));
			}
			rs.close();

		} catch (Exception ex) {
			ex.printStackTrace();
		}
		return items;
	}
	
	public ArrayList<Item> getUpdatedItems (int code) {
		ArrayList<Item> items = new ArrayList<Item>();
		try {
			ResultSet rs = select("SELECT * FROM items, machine_item WHERE items.ID = machine_item.itemid AND machine_item.machineid=" + code
					+ " AND items.lastmod > (SELECT lastsync FROM machines WHERE ID=" + code + ");");
			while (rs.next()) {
				items.add(new Item(rs));
			}
			rs.close();

		} catch (Exception ex) {
			ex.printStackTrace();
		}
		return items;
	}

	public void addSale(int machine, int item, String date) throws Exception {
		Connection conn = DriverManager.getConnection(connection, property);
		conn.setAutoCommit(false);
		Statement stmt = conn.createStatement(
				ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_UPDATABLE);
		stmt.executeUpdate("INSERT INTO sales (machineid,itemid,date) VALUES ("
				+ machine + ", " + item + ", '" + date + "');");
		conn.commit();
		conn.close();
	}

	public int getNumberOfSales(int machine, int item, String startDate, String endDate) throws Exception {
		int count = 0;
		try {
			ResultSet rs = select("SELECT COUNT(*) AS C FROM sales WHERE machineid="
					+ machine + " AND itemid=" + item + " AND date>='" + startDate + "' AND date<='" + endDate + "';");
			if (rs.next()) {
				count = rs.getInt("C");
			}
			rs.close();

		} catch (Exception ex) {
			ex.printStackTrace();
		}
		return count;
	}
	
	public int addCard(double balance) throws Exception {
		Connection conn = DriverManager.getConnection(connection, property);
		conn.setAutoCommit(false);
		Statement stmt = conn.createStatement(
				ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_UPDATABLE);
		ResultSet rs = stmt.executeQuery("SELECT MAX(ID) AS M FROM cards;");
		int code;
		if (rs.next()) {
			code = rs.getInt("M") + 1;
		} else {
			code = 1;
		}
		stmt.executeUpdate("INSERT INTO cards VALUES ("
				+ code
				+ ", '" + balance + ");");
		conn.commit();
		conn.close();
		return code;
	}
	
	public double checkBalance(int card) {
		double balance = 0;
		try {
			ResultSet rs = select("SELECT balance FROM cards WHERE ID="	+ card  + ";");
			if (rs.next()) {
				balance = rs.getDouble("balance");
			}
			rs.close();

		} catch (Exception ex) {
			ex.printStackTrace();
		}
		return balance;
	}
	
	public double updateBalance(int card, double deduction) throws Exception {
		double oldBalance = checkBalance(card);
		double newBalance = oldBalance - deduction;
		Connection conn = DriverManager.getConnection(connection, property);
		conn.setAutoCommit(false);
		Statement stmt = conn.createStatement(
				ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_UPDATABLE);
		stmt.executeUpdate("UPDATE cards SET balance = " + newBalance
				+ " WHERE ID=" + card + ";");
		conn.commit();
		conn.close();
		return newBalance;
	}

}