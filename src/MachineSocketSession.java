//Machine server stub subclass, which handles all machine related commands
public class MachineSocketSession extends SocketSession {
	private MachineServerService server;

	//constructor
	public MachineSocketSession(MachineServerService m) {
		server = m;
	}

	////to implement the abstract method in super class
	protected String handleRequest(String request){
		String response;
		String[] tokens = request.split(" ");
		String command = tokens[0];

		try {
			//machine commands
			if (command.equals(Const.CHECK_MACHINE)) {
				int result = server.checkMachine(Integer.parseInt(tokens[1]));
				response = Const.OK + result;
			} else if (command.equals(Const.GET_UPDATED_IDS)) {
				String result = server.getUpdatedIDs(Integer
						.parseInt(tokens[1])); // machineid
				response = Const.OK + result;
			} else if (command.equals(Const.GET_ITEM)) {
				String result = server.getItem(Integer.parseInt(tokens[1])); //itemid
				response = Const.OK + result;
			} else if (command.equals(Const.GET_FILE)) {
				String result = server.getFile(tokens[1]);		//path
				response = Const.OK + result;
			} else if (command
					.equals(Const.UPDATE_MACHINE_ITEM_QUANTITY)) {
				server.updateMachineItemQuantity(
						Integer.parseInt(tokens[1]),	// machineid
						Integer.parseInt(tokens[2]),	// itemid
						Integer.parseInt(tokens[3]));	// quantity
				response = Const.OK;
			} else if (command.equals(Const.ADD_SALE)) {
				server.addSale(Integer.parseInt(tokens[1]),		// machineid
						Integer.parseInt(tokens[2]),			// itemid
						Double.parseDouble(tokens[3]),			// profit
						tokens[4]);								// date
				response = Const.OK;
			} else if (command.equals(Const.UPDATE_SYNC_DATE)) {
				server.updateSyncDate(Integer.parseInt(tokens[1]));		// machineid
				response = Const.OK;
			} else {
				response = Const.ERROR + "Unknown command";
			}
		} catch (Exception e) {
			response = Const.ERROR + e.getMessage();
		}
		
		return response;
	}
}