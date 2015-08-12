import java.util.ArrayList;

public class NullDatabase implements DBManager {
	public NullDatabase() {
	}

	public NullDatabase(String connection, String user, String pwd) {
	}
	
	public int addItem(Item item) throws Exception {
		return 0;
	}
	
	public void deleteItem(int code) throws Exception {
	}

	public void updateItem(Item item) throws Exception {
	}

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

	public int addEmployee(Employee e) throws Exception {
		return 0;
	}
	
	public void deleteEmployee(int code) throws Exception {
	}
	
	public ArrayList<Employee> getAllEmployees() {
		ArrayList<Employee> employees = new ArrayList<Employee>();
		return employees;
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
	
	public void deleteMachine(int code) throws Exception {
	}
	
	public void updateMachineSyncDate(int code, String date) throws Exception {
	}

	public ArrayList<Machine> getAllMachines() {
		ArrayList<Machine> machines = new ArrayList<Machine>();
		return machines;
	}

	public Machine getMachine(int code) {
		Machine machine = null;
		return machine;
	}

	public void addItemToMachine(int machine, int item, int capacity, int quantity) throws Exception {
	}
	
	public void deleteItemFromMachine(int machine, int item) throws Exception {
	}
	
	public void updateMachineItemQuantity(int machine, int item, int quantity) throws Exception {
	}
	
	public ArrayList<MachineItem> getItemsOfMachine(int code) {
		ArrayList<MachineItem> items = new ArrayList<MachineItem>();
		return items;
	}
	
	public ArrayList<Item> getUpdatedItems (int code) {
		ArrayList<Item> items = new ArrayList<Item>();
		return items;
	}

	public void addSale(int machine, int item, double profit, String date) throws Exception {
	}

	public int getNumberOfSales(int machine, int item, String startDate, String endDate) throws Exception {
		return 0;
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