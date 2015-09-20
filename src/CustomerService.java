//CustomerServer interface, defining an interface for customer-server communication
public interface CustomerService {

	public int buyCard(double balance) throws Exception;
	
	public double checkBalance(int card) throws Exception;
	
	public double updateBalance(int card, double deduction) throws Exception;
	
}
