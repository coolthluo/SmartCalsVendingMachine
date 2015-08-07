import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;

import org.json.JSONObject;

public class BasicServer implements CustomerServer, EmployeeServer, MachineServer, ManagerServer {
	
	DBManager dbm;
	
	public BasicServer(DBManager db) {
		dbm = db;
		VendingMachineServer.setCustomerInstance(this);
		VendingMachineServer.setEmployeeInstance(this);
		VendingMachineServer.setMachineInstance(this);
		VendingMachineServer.setManagerInstance(this);
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
		obj.put("type", item.getType());
		obj.put("info", item.getInfo());
		obj.put("pic", item.getPic());
		obj.put("price", item.getPrice());
		return obj.toString();
	}

	public int addMachine(int machine, String address) throws Exception {
		return dbm.addMachine(machine, address, getTime(), getTime());
	}

	public void addItemToMachine(int machineid, int itemid, int capacity, int quantity) throws Exception {
		dbm.addItemToMachine(machineid, itemid, capacity, quantity);
	}
	
	public String getFile(String path) throws Exception {
		return new String(Files.readAllBytes(Paths.get(path)));
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
		
//		JSONObject obj = new JSONObject();
//		JSONArray list = new JSONArray();
//		Item item;
//		for (int i = 0; i < items.size(); i++){
//			item = items.get(i);
//			JSONObject itemJson = new JSONObject();
//			itemJson.put("id", item.getID());
//			itemJson.put("name", item.getName());
//			itemJson.put("type", item.getType());
//			itemJson.put("info", item.getInfo());
//			itemJson.put("pic", item.getPic());
//			itemJson.put("price", item.getPrice());
//			list.put(itemJson);
//		}
//		obj.put("items", list);
//		return obj.toString();
	}
	
	public void addSale(int machineid, int itemid, double profit, String date) throws Exception {
		dbm.addSale(machineid, itemid, profit, date);
	}
	
	public void updateSyncDate(int machine) throws Exception {
		dbm.updateMachineSyncDate(machine, getTime());
	}
	
	//methods in ManagerServer
	public String authenticateManager(int code, String password)
	{
		Employee employee=dbm.getEmployee(code);
		if (employee!=null && employee.isManager() && employee.getPassword().equals(password))
			return employee.getName();
		return null;
	}

	//private methods
	private String getTime() {
		java.util.Date dt = new java.util.Date();
		java.text.SimpleDateFormat sdf = new java.text.SimpleDateFormat(
				"yyyy-MM-dd HH:mm:ss");
		return sdf.format(dt);
	}

}