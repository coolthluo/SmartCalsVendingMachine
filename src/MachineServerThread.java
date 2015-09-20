/* 
 * Subclass of ServerThread.
 * It directly communicates with each machine through socket and starts
 * a server stub thread whenever a connection is set up.
*/
public class MachineServerThread extends ServerThread {

	private static MachineServerService machineServer;
	
	public MachineServerThread(MachineServerService server){
		machineServer = server;
	}

	protected String getName(){
		return "machine server";
	}
	
	protected int getPort(){
		return Const.MACHINE_PORT;
	}
	
	protected SocketSession getServerStub(){
		return new MachineSocketSession(machineServer);
	}
}