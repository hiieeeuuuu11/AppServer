package dao;

import java.sql.Connection;
import java.sql.DriverManager;

public class connectmysql {
	public static Connection createConnect(){
		Connection cn = null;


		try {
			Class.forName("com.mysql.jdbc.Driver");
			cn=DriverManager.getConnection( ConfigBTL.getDbUrl(),ConfigBTL.getDbUsername(),ConfigBTL.getDbPassword());
			
		} catch (Exception e) {
			e.printStackTrace();
		}  
		
		return cn;
	}
}