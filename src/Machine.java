//Machine class, holding the information of a machine
import java.sql.ResultSet;

public class Machine {
	private int code;
	private String address;
	private String startdate;
	private String lastsync;

	public Machine(int c, String a, String sd, String ls) {
		code = c;
		address = a;
		startdate = sd;
		lastsync = ls;
	}

	public Machine(ResultSet rs) throws java.sql.SQLException {
		code = rs.getInt("ID");
		address = rs.getString("address");
		startdate = rs.getDate("startdate").toString();
		lastsync = rs.getDate("lastsync").toString();
	}
	
	public void setSyncDate(String date){
		lastsync = date;
	}

	public int getID() {
		return code;
	}

	public String getAddress() {
		return address;
	}

	public String getStartDate() {
		return startdate;
	}

	public String getLastSync() {
		return lastsync;
	}
}
