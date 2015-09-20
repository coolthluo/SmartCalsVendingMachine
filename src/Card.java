//Card class, holding the information of a card
import java.sql.ResultSet;

public class Card {
	private int id;
	private double balance;
	
	Card (int id, double b){
		this.id = id;
		balance = b;
	}
	
	Card (ResultSet rs) throws java.sql.SQLException {
	      id = rs.getInt("ID");
	      balance = rs.getDouble("double");
	}
	
	public void setBalance(double b){
		balance = b;
	}
	
	public int getId(){
		return id;
	}
	
	public double getBalance(){
		return balance;
	}
}
