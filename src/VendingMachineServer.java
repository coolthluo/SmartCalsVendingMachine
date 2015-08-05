
import java.net.ServerSocket;
import java.net.Socket;

public class VendingMachineServer implements Runnable {

	static CustomerServer customerSingleton;
	static EmployeeServer employeeSingleton;
	static MachineServer machineSingleton;
	static ManagerServer managerSingleton;

	static void setCustomerInstance(CustomerServer instance) {
		customerSingleton = instance;
	}

	static void setEmployeeInstance(EmployeeServer instance) {
		employeeSingleton = instance;
	}

	static void setMachineInstance(MachineServer instance) {
		machineSingleton = instance;
	}

	static void setManagerInstance(ManagerServer instance) {
		managerSingleton = instance;
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

	public static ManagerServer getManagerInstance() {
		return managerSingleton;
	}

	public void run() {
		try {
			ServerSocket ss = new ServerSocket(Const.SERVER_PORT);
			while (true) {
				Socket sk = ss.accept();
				VMServerStub stub = new VMServerStub(sk, getCustomerInstance(), 
						getEmployeeInstance(), getMachineInstance(), getManagerInstance());
				(new Thread(stub)).start();
			}
		} catch (Exception ex) {
			System.out.println("Error: " + ex.getMessage());
		}
	}
}