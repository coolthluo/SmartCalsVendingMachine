
public interface ManagerServer extends Server {
	
	public String authenticateManager(int code, String password) throws Exception;
	
}
