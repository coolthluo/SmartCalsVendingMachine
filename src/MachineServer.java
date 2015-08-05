
public interface MachineServer extends Server {

	public String getUpdatedItems(int machineid) throws Exception;

	public String getFile(String path) throws Exception;
	
	public void updateMachineItemQuantity(int machineid, int itemid,
			int quantity) throws Exception;
	
	public void addSale(int machineid, int itemid, String date) throws Exception;
	
}
