package com.lister.service;

import java.io.IOException;
import java.sql.SQLException;

import com.lister.dao.ConnectionDAO;

public class AccountService {
	public void createAccount(String userDetails) throws SQLException{
		ConnectionDAO dao = new ConnectionDAO();
		dao.createNewAccount(userDetails);
	}
	public String validateUser(String skypename, String password) throws SQLException {
		ConnectionDAO dao = new ConnectionDAO();
		return dao.validateUser(skypename, password);
	}
	/*public String insertProfile(String request) throws SQLException {
		ConnectionDAO dao = new ConnectionDAO();
		return dao.insertProfile(request);
	}
	*/
	public String updateProfile(String request) throws SQLException {
		ConnectionDAO dao = new ConnectionDAO();
		return dao.updateProfile(request);
	}
	public String getUserProfile(String request) throws SQLException, IOException {
		ConnectionDAO dao = new ConnectionDAO();
		return dao.getProfileDetails(request);
	}
	public String createGroup(String request) throws SQLException, IOException {
		ConnectionDAO dao = new ConnectionDAO();
		return dao.createGroup(request);
	} 
	public String getGroupMembersByGroupName(String groupName) throws SQLException, IOException {
		ConnectionDAO dao = new ConnectionDAO();
		return dao.getGroupMembersByGroupName(groupName);
	}
	public String getSkypenameByName(String request) throws SQLException {
		ConnectionDAO dao = new ConnectionDAO();
		return dao.getSkypenameByName(request);
	}
}
