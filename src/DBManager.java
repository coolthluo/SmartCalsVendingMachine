

import java.util.ArrayList;

public interface DBManager {
   
	public int addItem(Item item) throws Exception;
	public void deleteItem(int code) throws Exception;
	public void updateItem(Item item) throws Exception;
	public ArrayList<Item> getAllItems();
	public Item getItem(int code);
	public int addEmployee(Employee e) throws Exception;
	public void deleteEmployee(int code) throws Exception;
	public ArrayList<Employee> getAllEmployees();
	public Employee getEmployee(int code);
	public int addMachine(int code, String address, String startdate, String lastsync) throws Exception;
	public int checkMachine(int code);
	public void deleteMachine(int code) throws Exception;
	public void updateMachineSyncDate(int code, String date) throws Exception;
	public ArrayList<Machine> getAllMachines();
	public Machine getMachine(int code);
	public void addItemToMachine(int a, int b, int c, int d) throws Exception;
	public void deleteItemFromMachine(int machine, int item) throws Exception;
	public void updateMachineItemQuantity(int machine, int item, int quantity) throws Exception;	
	public ArrayList<MachineItem> getItemsOfMachine(int code);	
	public ArrayList<Item> getUpdatedItems(int code);
	public void addSale(int machine, int item, double profit, String date) throws Exception;
	public int getNumberOfSales(int machine, int item, String startDate, String endDate) throws Exception;
	public int addCard(double balance) throws Exception;
	public double checkBalance(int card);
	public double updateBalance(int card, double deduction) throws Exception;
	
}