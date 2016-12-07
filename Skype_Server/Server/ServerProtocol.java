package com.lister.Server;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.sql.SQLException;
import java.util.List;

import com.lister.service.AccountService;

//import com.mysql.jdbc.Connection;


public class ServerProtocol {
	//List<clientThread> t = new ArrayList<clientThread>();
	Pipeline p = new Pipeline();
	
	private final String CREATE_ACCOUNT_KEY = "1fff2C";
	private final String USER_LOGIN_KEY = "2fff2C";
	private final String GET_GROUP_MEMBERS_AS_STRING="3fff2C";
	private final String GET_USER_PROFILE="4fff2C";
	private final String UPDATE_PROFILE = "5fff2C";
	private final String START_CHAT = "6fff2C";
	private final String STOP_CHAT = "7fff2C";
	private final String LOGOUT = "8fff2C";
	private final String CREATE_GROUP="9fff2C";
	private final String GET_SKYPENAME_BY_NAME= "Afff2C";
	private final String GET_GROUPMSG_BY_GRPNAME= "Bfff2C";
	private final String GET_MESSAGE_BY_SKYPENAME= "Cfff2C";
	private final String GET_GROUPS_BY_SKYPENAME= "Dfff2C";
	private final String GET_ALL_CONTACTS= "Efff2C";
	
	//private 
	//private final String DYNAMIC_INFO="10fff2C";
	
	
	public String processInput(String request,Socket clientSocket) throws IOException {
		
	    String outputString= "";

		if(request.contains(USER_LOGIN_KEY)) {
			outputString = validateUser(request);
			String details[] = request.split(" ");
			String skypename =details[0];
			if(outputString.contains("sucess")) {
				p.startChat(skypename,clientSocket);
			}
				
		}
		if(request.contains(CREATE_ACCOUNT_KEY)) {
			
			outputString = createAccount(request);
		}
		
		if(request.contains(START_CHAT)) {
			//Pipeline p = new Pipeline();
			p.startConversation(request);
		}
		if(request.contains(UPDATE_PROFILE)) {
			outputString = updateProfile(request);
			
		}
		if(request.contains(STOP_CHAT)) {
			//outputString = updateProfile(request);
			p.stopConversation(request);
			
		}
		if(request.contains(LOGOUT)) {
			outputString = p.removeThread(request);
		}
		if(request.contains(CREATE_GROUP)) {
			outputString = createGroup(request);
		}
		if(request.contains(GET_GROUP_MEMBERS_AS_STRING)) {
			outputString = getGroupMembers(request);
		}
		if(request.contains(GET_USER_PROFILE)) {
			outputString = getUserProfile(request);
			System.out.println(outputString);
		}
		if(request.contains(GET_MESSAGE_BY_SKYPENAME)) {
			outputString = getMessageBySkypename(request);
		}
		if(request.contains(GET_SKYPENAME_BY_NAME)) {
			outputString = getSkypenameByName(request);
		}
		if(request.contains(GET_GROUPMSG_BY_GRPNAME)) {
			outputString = getGroupMsgByGrpName(request);
		}
		if(request.contains(GET_GROUPS_BY_SKYPENAME)) {
			outputString = getGroupBySkypeName(request);
		}
	return outputString;
	}
	
	

	



	/*public String processAfterLogin(String request,Socket clientSocket) throws IOException {
		 String outputString= "";
			
	}*/
	
	private String getGroupMsgByGrpName(String request)  {
		String[] details = request.split(" ");
		String groupname = details[0];
		FileReaderByFileName fr = new FileReaderByFileName();
		 try {
			return fr.readGroupMsg(groupname);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			System.out.println("Everything is okay.Keep going");
			return " ";
		}
	}
	
	private String getSkypenameByName(String request) {
		AccountService newService = new AccountService();
		try {
			String res= "";
			res  = newService.getSkypenameByName(request);
			List<clientThread> onlineThreads = clientThread.getOnlineList();
			for(int i=0;i<onlineThreads.size();i++) {
				System.out.println("Online clients: "+onlineThreads.get(i).clientIdentity);
			}
			for(int i=0;i<onlineThreads.size();i++) {
				if(onlineThreads.get(i).clientIdentity.equalsIgnoreCase(res)) {
					return res+" online";
				}
			}
			return res+" offline";
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return "Error While creating profile";
		}
		
	}
	
	private String getMessageBySkypename(String request)  {
		String details[] = request.split(" ");
		String sender = details[0];
		String receiver = details[1];
		String filename = sender+"-"+receiver;
		String filename2 = receiver+"-"+sender;
		FileReaderByFileName fr = new FileReaderByFileName();
		try {
			return fr.readFileAsString(filename,filename2);
		} catch (IOException e) {
			e.printStackTrace();
			System.out.println("Everything is okay.Keep going");
			return " ";
			
		}
	}
	private String updateProfile(String request) {
		AccountService newService = new AccountService();
		try {
			return newService.updateProfile(request);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return "Error While creating profile";
		}
	}
		
		private String getUserProfile(String request) throws IOException {
			AccountService newService = new AccountService();
			try {
				return newService.getUserProfile(request);
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
				return "Error While reading profile";
			}
	}
	private String getGroupMembers(String groupName) throws IOException {
		String details[] = groupName.split(" ");
		String groupname = details[0];
				AccountService newService = new AccountService();
		try {
			return newService.getGroupMembersByGroupName(groupname);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return "Error While creating profile";
		}
		
	}
	
	private String createGroup(String request) throws IOException {
		AccountService newService = new AccountService();
		try {
			newService.createGroup(request);
			return "Group Created Succesfully";
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return e.getMessage();
		}
		
	}
	
	private String validateUser(String request) {
		String details[] = request.split(" ");
		String skypename =details[0];
		String password = details[1];
		AccountService newService = new AccountService();
		try {
			return newService.validateUser(skypename, password);
		}
		catch(Exception e) {
			e.printStackTrace();
			return "Invalid User. Exception occurred. Try again";
		}
	}
	private String createAccount(String request) {
		AccountService newService = new AccountService();
		try {
			newService.createAccount(request);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return e.getMessage();
		}
		return "Account Created Succesfully";
	}
private String getGroupBySkypeName(String request) {
	FileReaderByFileName fr = new FileReaderByFileName();
	String[] details = request.split(" ");
	String skypename = details[0];
	try {
	return fr.readGroupOfUser(skypename);
	}catch(IOException e) {
		System.out.println("No groups for this user");
		return "no groups for this user";
	}
	}
	
}
