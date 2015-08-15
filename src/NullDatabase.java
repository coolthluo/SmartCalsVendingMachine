//database null object
import java.util.ArrayList;

public class NullDatabase implements DBManager {

	public ArrayList<Item> getAllItems() {
		ArrayList<Item> items = new ArrayList<Item>();
		return items;
	}

	public ArrayList<Item> getOtherItems(int machine){
		ArrayList<Item> items = new ArrayList<Item>();
		return items;
	}

	public Item getItem(int code) {
		Item item = new Item(0, "", "", "", 0, 0, "", 0, "");
		return item;
	}
	
	public Employee getEmployee(int code) {
		Employee employee = null;
		return employee;
	}

	public int addMachine(int code, String address, String startdate, String lastsync) throws Exception {
		return 0;
	}
	
	public int checkMachine(int code) {
		return 0;
	}
	
	public void updateMachineSyncDate(int code, String date) throws Exception {
	}

	public void addItemToMachine(int machine, int item, int capacity, int quantity) throws Exception {
	}
	
	public void deleteItemFromMachine(int machine, int item) throws Exception {
	}
	
	public void updateMachineItemQuantity(int machine, int item, int quantity) throws Exception {
	}
	
	public ArrayList<Item> getUpdatedItems (int code) {
		ArrayList<Item> items = new ArrayList<Item>();
		return items;
	}

	public void addSale(int machine, int item, double profit, String date) throws Exception {
	}
	
	public int addCard(double balance) throws Exception {
		return 0;
	}
	
	public double checkBalance(int card) {
		return 0;
	}
	
	public double updateBalance(int card, double deduction) throws Exception {
		return 0;
	}

}