package controller;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import dao.connectmysql;
import model.User;

public class SignupService {
	Connection cn = connectmysql.createConnect();
	public boolean saveData(String u,String p){

			PreparedStatement ps = null;
			String url = "insert into user (username,password,role) VALUES (?,?,?)";
			try {
				ps = cn.prepareStatement(url);
				ps.setString(1, u);
				ps.setString(2, p);
				ps.setString(3, "USER");
				int reg = ps.executeUpdate();
				if(reg!=0) {
					ps.close();
					cn.close();
					return true;
				}
				else {
					ps.close();
					cn.close();
					return false;
				}
				
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
			return false;
			
		}
}
