
public interface MachineServer extends Server {

	public String getUpdatedIDs(int machineid) throws Exception;
	
	public String getItem(int item) throws Exception;

	public String getFile(String path) throws Exception;
	
	public void updateMachineItemQuantity(int machineid, int itemid,
			int quantity) throws Exception;
	
	public void addSale(int machineid, int itemid, double profit, String date) throws Exception;
	
}
