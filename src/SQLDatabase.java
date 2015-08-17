//concrete database: the real object
import java.sql.*;
import java.util.ArrayList;
import java.util.Properties;

public class SQLDatabase implements DBManager {
	private String connection;
	Properties property;

	public SQLDatabase() {
		try {
			Class.forName("com.mysql.jdbc.Driver");
		} catch (ClassNotFoundException e1) {
		}
//		connection = "jdbc:mysql://127.0.0.1/smartcalsvendingmachine";
		connection = "jdbc:mysql://127.0.0.1/smartcalsvendingmachine";
		property = new Properties();
		property.put("user", "root");
		property.put("password", "3hkyw198");
	}

	public SQLDatabase(String connection, String user, String pwd) {
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
	
	public ArrayList<Item> getOtherItems(int machine) {
		ArrayList<Item> items = new ArrayList<Item>();
		try {
			ResultSet rs = select("SELECT * FROM items where items.ID not in (select itemid from machine_item where machineid = " + machine + ");");
			while (rs.next()) {
				items.add(new Item(rs));
			}
			rs.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return items;
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
	
	public void updateMachineSyncDate(int code, String date) throws Exception {
		Connection conn = DriverManager.getConnection(connection, property);
		conn.setAutoCommit(false);
		Statement stmt = conn.createStatement(
				ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_UPDATABLE);
		stmt.executeUpdate("UPDATE machines SET lastsync = '" + date
				+ "' WHERE ID=" + code + ";");
		conn.commit();
		conn.close();
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
	
	public ArrayList<Item> getUpdatedItems (int code) {
		ArrayList<Item> items = new ArrayList<Item>();
		try {
			ResultSet rs = select("SELECT * FROM items, machine_item WHERE items.ID = machine_item.itemid AND machine_item.machineid=" + code
					+ " AND items.lastmod > (SELECT date(lastsync) FROM machines WHERE ID=" + code + ");");
			while (rs.next()) {
				items.add(new Item(rs));
			}
			rs.close();

		} catch (Exception ex) {
			ex.printStackTrace();
		}
		return items;
	}

	public void addSale(int machine, int item, double profit, String date) throws Exception {
		Connection conn = DriverManager.getConnection(connection, property);
		conn.setAutoCommit(false);
		Statement stmt = conn.createStatement(
				ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_UPDATABLE);
		stmt.executeUpdate("INSERT INTO sales (machineid,itemid,profit,date) VALUES ("
				+ machine + ", " + item + ", " + profit + ", '" + date + "');");
		conn.commit();
		conn.close();
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
				+ ", " + balance + ");");
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