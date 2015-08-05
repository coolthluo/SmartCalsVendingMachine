

import java.sql.ResultSet;

public class Sale {
	private int machineid;
	private int itemid;
	private String date;

	public Sale(int mid, int iid, String d) {
		machineid = mid;
		itemid = iid;
		date = d;
	}

	public Sale(ResultSet rs) throws java.sql.SQLException {
		machineid = rs.getInt("machineid");
		itemid = rs.getInt("itemid");
		date = rs.getDate("date").toString();
	}

	public int getMachineId() {
		return machineid;
	}

	public int getItemId() {
		return itemid;
	}

	public String getDate() {
		return date;
	}
}
