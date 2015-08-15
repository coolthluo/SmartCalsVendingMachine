//Server stub which directly communicate with clients through socket, parsing all the incoming commands
import java.net.Socket;
import java.io.InputStream;
import java.io.OutputStream;

public class VMServerStub implements Runnable {
	Socket socket;
	CustomerServer customer;
	EmployeeServer employee;
	MachineServer machine;

	VMServerStub(Socket sk, CustomerServer c, EmployeeServer e, MachineServer m) {
		socket = sk;
		customer = c;
		employee = e;
		machine = m;
	}

	public void run() {
		try {
			InputStream in = socket.getInputStream();
			OutputStream out = socket.getOutputStream();
			String response;
			byte[] buffer = new byte[1024];
			int len;
			while ((len = in.read(buffer)) != -1) {
				String request = new String(buffer, 0, len);

				System.out.println(">" + request);
				String[] tokens = request.split(" ");
				String command = tokens[0];

				try {
					//customer commands
					if (command.equals(Const.BUY_CARD)) {
						int result = customer.buyCard(Double.parseDouble(tokens[1]));
						response = Const.OK + result;
					} else if (command.equals(Const.CHECK_BALANCE)) {
						double result = customer.checkBalance(Integer.parseInt(tokens[1]));
						response = Const.OK + result;
					} else if (command.equals(Const.UPDATE_BALANCE)) {
						double result = customer.updateBalance(Integer.parseInt(tokens[1]), 	//cardid
								Double.parseDouble(tokens[2]));									//deduct
						if (result < 0) {
							response = Const.ERROR;
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
					} else if (command.equals(Const.GET_OTHER_ITEM_IDS)) {
						String result = employee.getOtherItemIDs(Integer.parseInt(tokens[1]));		//machineid
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
					else if (command.equals(Const.GET_UPDATED_IDS)) {
						String result = machine.getUpdatedIDs(Integer
								.parseInt(tokens[1])); // machineid
						response = Const.OK + result;
					} else if (command.equals(Const.ADD_SALE)) {
						machine.addSale(Integer.parseInt(tokens[1]),		// machineid
								Integer.parseInt(tokens[2]),			// itemid
								Double.parseDouble(tokens[3]),			// profit
								tokens[4]);								// date
						response = Const.OK;
					} else if (command.equals(Const.UPDATE_SYNC_DATE)) {
						machine.updateSyncDate(Integer.parseInt(tokens[1]));		// machineid
						response = Const.OK;
					}

					//unknown command error
					else {
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
		}

	}

}