package controller;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import dao.connectmysql;
import model.User;

public class LogInService {
	Connection cn = connectmysql.createConnect();
	public User login(String u,String p) {
		User user;
		PreparedStatement ps = null;
		String url = "select * from user";
		try {
			ps = cn.prepareStatement(url);
			
			ResultSet rs = ps.executeQuery();
			while(rs.next()) {
				int id = rs.getInt("id");
				String un = rs.getString("username");
				String pw = rs.getString("password");
				if(un.equals(u) && pw.equals(p)) {
					user = new User(id, un, pw, "USER");
					return user;
				};
			}
			ps.close();
			rs.close();
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}
}
