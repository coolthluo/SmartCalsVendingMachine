/* 
 * Subclass of ServerThread.
 * It directly communicates with each customer through socket and starts
 * a server stub thread whenever a connection is set up.
*/
public class CustomerServerThread extends ServerThread {

	private static CustomerServerService customerServer;
	
	public CustomerServerThread(CustomerServerService server){
		customerServer = server;
	}

	protected String getName(){
		return "customer server";
	}
	
	protected int getPort(){
		return Const.CUSTOMER_PORT;
	}
	
	protected SocketSession getServerStub(){
		return new CustomerSocketSession(customerServer);
	}
}
