//Sale class, holding the information of a sale transaction
import java.sql.ResultSet;

public class Sale {
	private int machineid;
	private int itemid;
	private double profit;
	private String date;

	public Sale(int mid, int iid, double p, String d) {
		machineid = mid;
		itemid = iid;
		profit = p;
		date = d;
	}

	public Sale(ResultSet rs) throws java.sql.SQLException {
		machineid = rs.getInt("machineid");
		itemid = rs.getInt("itemid");
		profit = rs.getDouble("profit");
		date = rs.getDate("date").toString();
	}

	public int getMachineId() {
		return machineid;
	}

	public int getItemId() {
		return itemid;
	}
	
	public double getProfit(){
		return profit;
	}

	public String getDate() {
		return date;
	}
}
