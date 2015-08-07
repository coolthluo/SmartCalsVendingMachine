

import java.sql.ResultSet;

public class Item {
	private int code;
	private String name;
	private double price;
	private String type;
	private String info;
	private String picture;
	private String lastmod;

	public Item(int c, String n, String pi, String t, String i, double p, String d) {
		code = c;
		name = n;
		price = p;
		type = t;
		info = i;
		picture = pi;
		lastmod = d;
	}

	public Item(ResultSet rs) throws java.sql.SQLException {
		code = rs.getInt("ID");
		name = rs.getString("name");
		price = rs.getDouble("price");
		type = rs.getString("type");
		info = rs.getString("info");
		picture = rs.getString("picture");
		lastmod = rs.getDate("lastmod").toString();
	}

	public int getID() {
		return code;
	}

	public String getName() {
		return name;
	}

	public double getPrice() {
		return price;
	}

	public String getType() {
		return type;
	}

	public String getInfo() {
		return info;
	}
	
	public String getPic() {
		return picture;
	}

	public String getLastMod() {
		return lastmod;
	}
}