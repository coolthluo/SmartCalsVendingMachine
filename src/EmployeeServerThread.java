/* 
 * Subclass of ServerThread.
 * It directly communicates with each employee through socket and starts
 * a server stub thread whenever a connection is set up.
*/
public class EmployeeServerThread extends ServerThread {

	private static EmployeeServerService employeeServer;
	
	public EmployeeServerThread(EmployeeServerService server){
		employeeServer = server;
	}
	
	protected String getName(){
		return "employee server";
	}
	
	protected int getPort(){
		return Const.EMPLOYEE_PORT;
	}
	
	protected SocketSession getServerStub(){
		return new EmployeeSocketSession(employeeServer);
	}
}