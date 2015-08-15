//start server with real database object
public class StartServer {
	public static void main(String[] args){
	    VendingMachineServer market = new VendingMachineServer();
	    (new Thread(market)).start();
	    new BasicServer(new SQLDatabase());
	}
}
