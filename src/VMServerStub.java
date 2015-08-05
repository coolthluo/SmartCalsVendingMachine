import java.net.Socket;
import java.io.InputStream;
import java.io.OutputStream;

public class VMServerStub implements Runnable {
	Socket socket;
	CustomerServer customer;
	EmployeeServer employee;
	MachineServer machine;
	ManagerServer manager;

	VMServerStub(Socket sk, CustomerServer c, EmployeeServer e, MachineServer mc, ManagerServer mg) {
		socket = sk;
		customer = c;
		employee = e;
		machine = mc;
		manager = mg;
	}

	public void run() {
		try {
			InputStream in = socket.getInputStream();
			OutputStream out = socket.getOutputStream();
			String response;
			try {

				byte[] buffer = new byte[1024];
				int len;
				while ((len = in.read(buffer)) != -1) {
					String request = new String(buffer, 0, len);

					System.out.println(">" + request);
					String[] tokens = request.split(" ");
					String command = tokens[0];

					try {
						//customer commands
						if (command.equals(Const.ADD_CARD)) {
							int result = customer.addCard(Double.parseDouble(tokens[1]));
							response = Const.OK + result;
						} else if (command.equals(Const.CHECK_BALANCE)) {
							double result = customer.checkBalance(Integer.parseInt(tokens[1]));
							if (result < 0) {
								response = Const.ERROR + "Check balance failed";
							} else {
								response = Const.OK + result;
							}
						} else if (command.equals(Const.UPDATE_BALANCE)) {
							double result = customer.updateBalance(Integer.parseInt(tokens[1]), 	//cardid
									Double.parseDouble(tokens[2]));									//deduct
							if (result < 0) {
								response = Const.ERROR + "Check balance failed";
							} else {
								response = Const.OK + result;
							}
						} 
						
						//employee commands
						else if (command.equals(Const.AUTHENTICATE)) {
							String result = employee.authenticate(
									Integer.parseInt(tokens[1]),		// employeeid
									tokens[2]); 						// password
							if (result == null) {
								response = Const.ERROR
										+ "Wrong employee or password";
							} else {
								response = Const.OK + result;
							}
						} else if (command.equals(Const.CHECK_MACHINE)) {
							int result = employee.checkMachine(Integer.parseInt(tokens[1]));
							response = Const.OK + result;
						} else if (command.equals(Const.GET_ITEM_IDS)) {
							String result = employee.getItemIDs();
							response = Const.OK + result;
						} else if (command.equals(Const.GET_ITEM)) {
							String result = employee.getItem(Integer.parseInt(tokens[1])); //itemid
							response = Const.OK + result;
						} else if (command.equals(Const.ADD_MACHINE)) {
							int result = employee.addMachine(Integer.parseInt(tokens[1]), tokens[2]); //code, address
							response = Const.OK + result;
						} else if (command.equals(Const.ADD_ITEM_TO_MACHINE)) {
							employee.addItemToMachine(
									Integer.parseInt(tokens[1]),	// machineid
									Integer.parseInt(tokens[2]),	// itemid
									Integer.parseInt(tokens[3]),	// capacity
									Integer.parseInt(tokens[4]));	// quantity
							response = Const.OK;
						} else if (command.equals(Const.GET_FILE)) {
							String result = employee.getFile(tokens[1]);		//path
							response = Const.OK + result;
						} else if (command
								.equals(Const.UPDATE_MACHINE_ITEM_QUANTITY)) {
							employee.updateMachineItemQuantity(
									Integer.parseInt(tokens[1]),	// machineid
									Integer.parseInt(tokens[2]),	// itemid
									Integer.parseInt(tokens[3]));	// quantity
							response = Const.OK;
						} else if (command
								.equals(Const.DELETE_ITEM_FROM_MACHINE)) {
							employee.deleteItemFromMachine(
									Integer.parseInt(tokens[1]),	// machineid
									Integer.parseInt(tokens[2]));	// itemid
							response = Const.OK;
						} 
						
						//machine commands
						else if (command.equals(Const.GET_UPDATED_ITEMS)) {
							String result = machine.getUpdatedItems(Integer
									.parseInt(tokens[1])); // machineid
							response = Const.OK + result;
						} else if (command.equals(Const.ADD_SALE)) {
							machine.addSale(Integer.parseInt(tokens[1]),		// machineid
									Integer.parseInt(tokens[2]),			// itemid
									tokens[3]);								// date
							response = Const.OK;
						} 
						
						//manager commands
						else if (command.equals(Const.AUTHENTICATE_MANAGER)) {
							String result = manager.authenticateManager(
									Integer.parseInt(tokens[1]), 		// employeeid
									tokens[2]); 						// password
							if (result == null) {
								response = Const.ERROR
										+ "Wrong employee or password";
							} else {
								response = Const.OK + result;
							}
						} else {
							response = Const.ERROR + "Unknown command";
						}
					} catch (Exception e) {
						response = Const.ERROR + e.getMessage();
					}
					System.out.println("<" + response);
					out.write((response).getBytes());
					out.flush();
				}
			} catch (Exception ex) {
				ex.printStackTrace();
				response = Const.ERROR + ex.toString();
				out.write((response).getBytes());
				out.flush();
			}
		} catch (Exception ex) {
			ex.printStackTrace();
		}

	}

}