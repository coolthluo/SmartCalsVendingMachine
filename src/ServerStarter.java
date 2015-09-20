//StartServer class containing main() method to start the server
import java.util.Scanner;

public class ServerStarter {
	
	public static void main(String[] args){
		ServerFacade facade = new ServerFacade();
		facade.startSmartCalsServers();
		
		Scanner scanner = new Scanner(System.in);
		while(true){
			System.out.println("Enter C/c to change database or Q/q to quit.");
			String line = scanner.nextLine();
			if (line.charAt(0) == 'C' || line.charAt(0) == 'c') {
				System.out.println("Which database would you like to use? (Enter 1 for SQLDatabase, "
			    		+ "2 for VolatileDatabase, or 3 for NullDatabase)");
				line = scanner.nextLine();
				try{
					facade.switchDatabase(Integer.parseInt(line));
				} catch (NumberFormatException e){
					System.out.println("Wrong input!");
				}
			} else if (line.charAt(0) == 'Q' || line.charAt(0) == 'q') {
				facade.stopSmartCalsServers();
				scanner.close();
		    	System.out.println("Bye!");
		    	break;
		    }
		}
	}
}
