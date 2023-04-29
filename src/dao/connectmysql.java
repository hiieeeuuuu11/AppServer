package dao;

import java.sql.Connection;
import java.sql.DriverManager;

public class connectmysql {
	public static Connection createConnect(){
		Connection cn = null;

		try {
			Class.forName("com.mysql.jdbc.Driver");
			cn=DriverManager.getConnection( "jdbc:mysql://localhost:3306/btljava","root","root");
			
		} catch (Exception e) {
			e.printStackTrace();
		}  
		
		return cn;
	}
}