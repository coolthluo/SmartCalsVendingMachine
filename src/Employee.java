

import java.sql.ResultSet;

public class Employee {
   private int code;
   private String name;
   private String password;
   private boolean manager;

   Employee(int c, String n, String p){
      code = c;
      name = n;
      password = p;
      manager = false;
   }

   Employee(int c, String n, String p, boolean isMan){
      this(c, n, p);
      manager = isMan;
   }

   Employee(ResultSet rs) throws java.sql.SQLException {
      code = rs.getInt("ID");
      name = rs.getString("name");
      password = rs.getString("password");
      manager = rs.getBoolean("isManager");
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

	public boolean isManager() {
		return manager;
	}
}