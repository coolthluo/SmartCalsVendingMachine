//database interface
import java.util.ArrayList;

public interface DBManager {
	
	public ArrayList<Item> getAllItems();
	public ArrayList<Item> getOtherItems(int machine);
	public Item getItem(int code);
	public Employee getEmployee(int code);
	public int addMachine(int code, String address, String startdate, String lastsync) throws Exception;
	public int checkMachine(int code);
	public void updateMachineSyncDate(int code, String date) throws Exception;
	public void addItemToMachine(int machine, int item, int capacity, int quantity) throws Exception;
	public void deleteItemFromMachine(int machine, int item) throws Exception;
	public void updateMachineItemQuantity(int machine, int item, int quantity) throws Exception;	
	public ArrayList<Item> getUpdatedItems(int code);
	public void addSale(int machine, int item, double profit, String date) throws Exception;
	public int addCard(double balance) throws Exception;
	public double checkBalance(int code);
	public double updateBalance(int code, double deduction) throws Exception;
	public String getFile(String path) throws Exception;
	
}