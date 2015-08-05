

import java.sql.ResultSet;

public class MachineItem {
	private int machineId;
	private int itemId;
	private int capacity;
	private int quantity;

	public MachineItem(int m, int i, int c, int q) {
		machineId = m;
		itemId = i;
		capacity = c;
		quantity = q;
	}

	public MachineItem(ResultSet rs) throws java.sql.SQLException {
		machineId = rs.getInt("machineid");
		itemId = rs.getInt("itemid");
		capacity = rs.getInt("capacity");
		quantity = rs.getInt("quantity");
	}

	public int getMachineId() {
		return machineId;
	}

	public int getItemId() {
		return itemId;
	}
	
	public int getCapacity() {
		return capacity;
	}
	
	public int getQuantity() {
		return quantity;
	}

}
