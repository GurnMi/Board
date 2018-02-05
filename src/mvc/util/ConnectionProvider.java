package mvc.util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConnectionProvider {
	
	public static Connection getConnection() throws SQLException{
		String jdbcDriver = "jdbc:apache:commons:dbcp:board";
		return  DriverManager.getConnection(jdbcDriver);
	}
	
}
