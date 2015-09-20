//server real object, implementing the same interfaces as the proxies
//a singleton
//handles requests by talking to database layer
//uses a bridge pattern to allow usage of different database implementations
import java.util.ArrayList;
import org.json.JSONObject;

public class ServerProxy implements CustomerServerService, EmployeeServerService, MachineServerService {
	
	private static ServerProxy singleton = new ServerProxy();
	private DBManager dbm;
	
	//protected constructor
	protected ServerProxy() {}
	
	//instance() method to get a CentralServer singleton
	public static ServerProxy instance(){
		return singleton;
	}
	
	//set database implementation
	public void setDatabase(DBManager db) {
		dbm = db;
	}

	//methods in CustomerServer
	public int buyCard(double balance) throws Exception {
		return dbm.addCard(balance);
	}

	public double checkBalance(int card) {
		return dbm.checkBalance(card);
	}
	
	public double updateBalance(int card, double deduction) throws Exception {
		double balance = checkBalance(card);
		if(balance < deduction){
			return -1;
		}
		return dbm.updateBalance(card, deduction);
	}
	
	//methods in EmployeeServer
	public String authenticate(int code, String password){
		Employee employee=dbm.getEmployee(code);
		if(employee!=null && employee.getPassword().equals(password)){
			return employee.getName();
		}
		return null;
	}
	
	public int checkMachine(int machine) {
		return dbm.checkMachine(machine);
	}
	
	public String getItemIDs() {
		ArrayList<Item> items = dbm.getAllItems();
		String result = "";
		for (int i = 0; i < items.size(); i++){
			result += items.get(i).getID() + " ";
		}
		return result.substring(0, result.length()-1);
	}
	
	public String getOtherItemIDs(int machine) {
		ArrayList<Item> items = dbm.getOtherItems(machine);
		String result = "";
		for (int i = 0; i < items.size(); i++){
			result += items.get(i).getID() + " ";
		}
		return result.substring(0, result.length()-1);
	}
	
	public String getItem(int code) throws Exception {
		Item item = dbm.getItem(code);
		JSONObject obj = new JSONObject();
		obj.put("id", item.getID());
		obj.put("name", item.getName());
		obj.put("price", item.getPrice());
		obj.put("type", item.getType());
		obj.put("calories", item.getCalories());
		obj.put("sugar", item.getSugar());
		obj.put("info", item.getInfo());
		obj.put("pic", item.getPic());
		return obj.toString();
	}

	public int addMachine(int machine, String address) throws Exception {
		return dbm.addMachine(machine, address, getTime(), getTime());
	}

	public void addItemToMachine(int machineid, int itemid, int capacity, int quantity) throws Exception {
		dbm.addItemToMachine(machineid, itemid, capacity, quantity);
	}
	
	public String getFile(String path) throws Exception {
		return dbm.getFile(path);
	}
	
	public void updateMachineItemQuantity(int machineid, int itemid, int quantity) throws Exception {
		dbm.updateMachineItemQuantity(machineid, itemid, quantity);
	}

	public void deleteItemFromMachine(int machineid, int itemid) throws Exception {
		dbm.deleteItemFromMachine(machineid, itemid);
	}

	//methods in MachineServer
	public String getUpdatedIDs(int machineid) throws Exception {
		ArrayList<Item> items = dbm.getUpdatedItems(machineid);
		if(items.size() == 0){
			return "";
		}
		String result = "";
		for (int i = 0; i < items.size(); i++){
			result += items.get(i).getID() + " ";
		}
		return result.substring(0, result.length()-1);
	}
	
	public void addSale(int machineid, int itemid, double profit, String date) throws Exception {
		dbm.addSale(machineid, itemid, profit, date);
	}
	
	public void updateSyncDate(int machine) throws Exception {
		dbm.updateMachineSyncDate(machine, getTime());
	}

	//private methods
	private String getTime() {
		java.util.Date dt = new java.util.Date();
		java.text.SimpleDateFormat sdf = new java.text.SimpleDateFormat(
				"yyyy-MM-dd HH:mm:ss");
		return sdf.format(dt);
	}

}