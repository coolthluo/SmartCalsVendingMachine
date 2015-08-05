

import java.sql.ResultSet;

public class Item {
	private int code;
	private String name;
	private String type;
	private String info;
	private String picture;
	private double price;
	private String lastmod;

	public Item(int c, String n, String t, String i, String pi, double p, String d) {
		code = c;
		name = n;
		type = t;
		info = i;
		picture = pi;
		price = p;
		lastmod = d;
	}

	public Item(ResultSet rs) throws java.sql.SQLException {
		code = rs.getInt("ID");
		name = rs.getString("name");
		type = rs.getString("type");
		info = rs.getString("info");
		picture = rs.getString("picture");
		price = rs.getDouble("price");
		lastmod = rs.getDate("lastmod").toString();
	}

	public int getID() {
		return code;
	}

	public String getName() {
		return name;
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

	public double getPrice() {
		return price;
	}

	public String getLastMod() {
		return lastmod;
	}
}