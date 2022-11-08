package com.tms.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.tms.dbconnection.GetConnection;
import com.tms.exceptions.TenderManagementSystemException;

public class AdministratorDaoImlp implements AdministratorDao{

	@Override
	public String administratorLogin(String email, String password)throws TenderManagementSystemException {

		String message = "We cannot find your account";
		
		try(Connection conn = GetConnection.provideConnection()) {
			
			PreparedStatement ps = conn.prepareStatement("select * from administrator");
			
			ResultSet rs = ps.executeQuery();
			
			if(rs.next()) {
				if(rs.getString("adminemail").equals(email) && rs.getString("adminpassword").equals(password))
				message = "Login Successful";
			}
			
			if(message.equals("We cannot find your account")){
				throw new TenderManagementSystemException(message);
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return message;
	}

}
