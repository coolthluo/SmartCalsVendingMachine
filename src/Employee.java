//Employee class, holding the information of an employee
import java.sql.ResultSet;

public class Employee {
   private int code;
   private String name;
   private String password;

   Employee(int c, String n, String p){
      code = c;
      name = n;
      password = p;
   }
   
   Employee(ResultSet rs) throws java.sql.SQLException {
      code = rs.getInt("ID");
      name = rs.getString("name");
      password = rs.getString("password");
   }
   
	public int getID() {
		return code;
	}

	public String getName() {
		return name;
	}

	public String getPassword() {
		return password;
	}
}