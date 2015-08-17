/* 
 * VendingMachineServer, holding a singleton for each type of servers and is in charge of 
 * starting a server stub to directly communicate with each client through socket
*/
import java.net.ServerSocket;
import java.net.Socket;

public class VendingMachineServer implements Runnable {

	static CustomerServer customerSingleton;
	static EmployeeServer employeeSingleton;
	static MachineServer machineSingleton;

	static void setCustomerInstance(CustomerServer instance) {
		customerSingleton = instance;
	}

	static void setEmployeeInstance(EmployeeServer instance) {
		employeeSingleton = instance;
	}

	static void setMachineInstance(MachineServer instance) {
		machineSingleton = instance;
	}

	public static CustomerServer getCustomerInstance() {
		return customerSingleton;
	}

	public static EmployeeServer getEmployeeInstance() {
		return employeeSingleton;
	}

	public static MachineServer getMachineInstance() {
		return machineSingleton;
	}

	public void run() {
		try {
			ServerSocket ss = new ServerSocket(Const.SERVER_PORT);
			while (true) {
				Socket sk = ss.accept();
				System.out.println("Connection Ok");
				VMServerStub stub = new VMServerStub(sk, getCustomerInstance(), 
						getEmployeeInstance(), getMachineInstance());
				(new Thread(stub)).start();
				
			}
			
		} catch (Exception ex) {
			System.out.println("Error: " + ex);
		}
	}
}