import java.util.ArrayList;

public class ServerFacade {
	
	public final static int SQL_DATABASE = 1;
	public final static int VOLATILE_DATABASE = 2;
	public final static int NULL_DATABASE = 3;
	
	private ArrayList<ServerThread> serverList;

	public ServerFacade(){
		serverList = new ArrayList<>();
	}
	
	public void startSmartCalsServers(){
		ServerProxy server = ServerProxy.instance();
		serverList.add(new CustomerServerThread(server));
		serverList.add(new EmployeeServerThread(server));
		serverList.add(new MachineServerThread(server));
		for(int i = 0; i < serverList.size(); i++){
			(new Thread(serverList.get(i))).start();
		}
		server.setDatabase(new SQLDatabase());
		System.out.println("MySQL database is used.");
	}
	
	public void switchDatabase(int choice){
		ServerProxy server = ServerProxy.instance();
		switch (choice) {
		case SQL_DATABASE:
			server.setDatabase(new SQLDatabase());
			System.out.println("MySQL database is used.");
			break;
		case VOLATILE_DATABASE:
			server.setDatabase(new VolatileDatabase());
			System.out.println("Volatile database is used.");
			break;
		case NULL_DATABASE:
			server.setDatabase(new NullDatabase());
			System.out.println("Null database is used.");
			break;
		default:
			System.out.println("Failed! No database matches.");
			break;
		}
	}
	
	public void stopSmartCalsServers(){
		for(int i = 0; i < serverList.size(); i++){
			serverList.get(i).stopServer();
		}
		serverList.clear();
	}
}
