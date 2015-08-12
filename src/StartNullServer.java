//start server with null database object to test socket connection
public class StartNullServer {
	public static void main(String[] args){
	    VendingMachineServer market = new VendingMachineServer();
	    (new Thread(market)).start();
	    new BasicServer(new NullDatabase());
	}
}