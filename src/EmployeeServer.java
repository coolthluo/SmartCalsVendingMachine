
public interface EmployeeServer extends Server {
	
	public String authenticate(int employee, String password) throws Exception;

	public int checkMachine(int machine) throws Exception;
	
	public String getItemIDs() throws Exception;
	
	public String getItem(int item) throws Exception;

	public int addMachine(int machine, String address)
			throws Exception;

	public void addItemToMachine(int machineid, int itemid, int capacity,
			int quantity) throws Exception;
	
	public String getFile(String path) throws Exception;

	public void updateMachineItemQuantity(int machineid, int itemid,
			int quantity) throws Exception;
	
	public void deleteItemFromMachine(int machineid, int itemid)
			throws Exception;

}
