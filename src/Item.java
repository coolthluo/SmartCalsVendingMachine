//Item class, holding information of an item
import java.sql.ResultSet;

public class Item {
	private int code;
	private String name;
	private double price;
	private String type;
	private int calories;
	private int sugar;
	private String info;
	private String picture;
	private String lastmod;

	public Item(int c, String n, String pi, String t, int ca, int s, String i, double p, String d) {
		code = c;
		name = n;
		price = p;
		type = t;
		calories = ca;
		sugar = s;
		info = i;
		picture = pi;
		lastmod = d;
	}

	public Item(ResultSet rs) throws java.sql.SQLException {
		code = rs.getInt("ID");
		name = rs.getString("name");
		price = rs.getDouble("price");
		type = rs.getString("type");
		calories = rs.getInt("calories");
		sugar = rs.getInt("sugar");
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

	public int getCalories( ) {
		return calories;
	}
	
	public int getSugar() {
		return sugar;
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