package com.lister.dao;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class ConnectionDAO {
	String database = "skype";
	String user = "root";
	String password = "lister";
	String host = "localhost"; // By default

	private Connection con;

	public Connection connect() {
		try {

			Class.forName("com.mysql.jdbc.Driver").newInstance();
			con = DriverManager.getConnection("jdbc:mysql://" + host + "/"
					+ database + "?user=" + user + "&password=" + password);
			// Set connectionStatus
			System.out.println("Connected");

			return con;
		} catch (Exception E) {
			System.out.println("Not Connected");
			return null;
		}
	}
	private boolean validateDetails(String skypename) throws SQLException{
		Connection conn = connect();
		PreparedStatement stmt = null;
		String sql = "select skypename from user";
		stmt = conn.prepareStatement(sql);
		ResultSet rs = stmt.executeQuery();
		while(rs.next()) {
			if(rs.getString("skypename").equalsIgnoreCase(skypename))
				return false;
		}
		return true;
	}

	public void createNewAccount(String userDetails) throws SQLException {
		String[] details = userDetails.split(" ");
		String skypename = details[0];
		String fname = details[1];
		String lname = details[2];
		String password = details[3];
		String email = details[4];
		if(!validateDetails(skypename))
		{
			throw new SQLException("Choose different skype name");
		}
		

		Connection conn = null;
	
		conn = connect();

		PreparedStatement stmt = null;
		String insert_user = "insert into user(skypename,fname,lname,password,email) values (?,?,?,?,?)";
		
			stmt = conn.prepareStatement(insert_user);

			stmt.setString(1, skypename);
			stmt.setString(2, fname);
			stmt.setString(3, lname);
			stmt.setString(4, password);
			stmt.setString(5, email);

			stmt.executeUpdate();
			insertProfile(skypename);
			stmt.close();
			conn.close();

	}
	
	public String validateUser(String skypename, String password) throws SQLException {
		Connection conn = null;
		conn = connect();
		PreparedStatement stmt = null;
		String sql = "select password from user where skypename= ?";
		stmt =  conn.prepareStatement(sql);
		stmt.setString(1, skypename);
		ResultSet rs = stmt.executeQuery();
		rs.next();
		if(rs.getString("password").equals(password))
			return "login sucess";
		else
			return "incorrect password";
	}
	
	public void insertProfile(String skypeName) throws SQLException {
		//String details[] = request.split(" ");
		//String skypeName =  details[0];
		String profilePic = "defaultImage.jpg";
		//String status = " ";
		//String mood = " ";
		Connection conn = null;
		conn = connect();
		PreparedStatement stmt = null;
		int userId = getUserId(skypeName);
		String sql = "insert into profile(user_id,pic) values(?,?)";
		stmt =  conn.prepareStatement(sql);
		stmt.setInt(1, userId);
		stmt.setString(2,  profilePic);
		//stmt.setString(3, status);
		//stmt.setString(4, mood);
		stmt.executeUpdate();
	}
	
	public String updateProfile(String request) throws SQLException {
		String details[] = request.split(" ");
		String skypeName =  details[0];
		String profilePic = details[1];
		String status = details[2];
		String mood = details[3];
		Connection conn = null;
		conn = connect();
		PreparedStatement stmt = null;
		int userId = getUserId(skypeName);
		String sql = "update profile set pic=?,status=?,mood=? where user_id=?";
		stmt =  conn.prepareStatement(sql);
		
		stmt.setString(1, profilePic);
		stmt.setString(2, status);
		stmt.setString(3, mood);
		stmt.setInt(4, userId);
		stmt.executeUpdate();
		return "Profile Updated Success";
	}
	
	public String createGroup(String request) throws SQLException, IOException {
		String details[] = request.split(" ");
		String groupName =  details[0];
		String no_of_members=details[1];
		int no=Integer.parseInt(no_of_members);
		ArrayList<String> group_members= new ArrayList<String>();
		for(int i=0;i<details.length-2;i++)
		{
			if(details[i+2].equalsIgnoreCase("online")||details[i+2].equalsIgnoreCase("offline"))
				continue;
			group_members.add(details[i+2]);
		}
		
		try{
          		File file =new File("Group/"+groupName+".txt");
    	  		if(!file.exists()){
    	 			file.createNewFile();
			}
    	  		FileWriter fw = new FileWriter(file,true);
    	  		//BufferedWriter bw = new BufferedWriter(fw);
    	  		PrintWriter pw = new PrintWriter(fw);
             		//pw.println("");
			for(int i=0;i<group_members.size();i++){
				pw.print(group_members.get(i)+" ");
			}
			pw.println("\n");
			pw.close();
			
		}
		catch(IOException ioe){
    	   		System.out.println("Exception occurred:");
    	   		ioe.printStackTrace();
      		}
		
		//for(int i=0;i<no;i++){
			
		Connection conn = null;
		conn = connect();
		PreparedStatement stmt = null;
		String sql = "insert into groupchat(member,groupname) values(?,?)";
		//stmt.setInt(1, groupid);
		
		stmt =  conn.prepareStatement(sql);
		stmt.setString(1,"Group/"+groupName+".txt");
		stmt.setString(2,groupName);
		stmt.executeUpdate();
		addGroupName(groupName,group_members);
		return "Group Created Successfully";
		
	}
	
	private void addGroupName(String gname, ArrayList<String> gmembers)
			throws SQLException, IOException {
		Connection conn = null;
		conn = connect();
		PreparedStatement stmt = null;
		for (int i = 0; i < gmembers.size(); i++) {
			File file = new File("UserGroups/grouplistof" + gmembers.get(i)
					+ ".txt");

			if (!file.exists()) {
				file.createNewFile();
			}

			FileWriter fw = new FileWriter(file, true);
			// BufferedWriter bw = new BufferedWriter(fw);
			PrintWriter pw = new PrintWriter(fw);
			// pw.println("");
			pw.println(gname);
			// pw.println("\n");
			String sql = "update user set grouplist= ? where skypename=?";
			stmt = conn.prepareStatement(sql);
			stmt.setString(1, "UserGroups/grouplistof" + gmembers.get(i)
					+ ".txt");
			stmt.setString(2, gmembers.get(i));
			stmt.executeUpdate();
			fw.close();
			pw.close();
		}
		
	}
	
	
	private int getUserId(String skypename) throws SQLException {
		Connection conn = null;
		conn = connect();
		PreparedStatement stmt = null;
		String sql = "select user_id from user where skypename = ?";
		stmt= conn.prepareStatement(sql);
		stmt.setString(1, skypename);
		ResultSet rs = stmt.executeQuery();
		rs.next();
		return rs.getInt("user_id");
	}
	
	public String getGroupMembersByGroupName(String groupName) throws SQLException, IOException {
	    Connection conn= null;
	    conn = connect();
	    PreparedStatement stmt = null;
	    String sql = "select member from groupchat where groupname = ?";
	    stmt = conn.prepareStatement(sql);
	    stmt.setString(1, groupName);
	    ResultSet rs  = stmt.executeQuery();
	    rs.next();
	    String url = rs.getString("member");
	    return readFile(url);
	    
	}
	public String getProfileDetails(String request) throws SQLException, IOException{
		String details[] = request.split(" ");
		String skypename=details[0];
		Connection conn = null;
		conn = connect();
		PreparedStatement stmt = null;
		String sql="select user_id,fname,lname,email,grouplist from user where skypename=?";
		stmt =  conn.prepareStatement(sql);
		stmt.setString(1,skypename);
		ResultSet rs = stmt.executeQuery();
		rs.next();
		String fname = "NULL";
		String lname = "NULL";
		String email="NULL";
		String url = "NULL";
		String groups = "NULL";
		String pic= "NULL";
		String status = "NULL";
		String mood ="NULL";
		String contacts = "NULL";
		
		int userid=rs.getInt("user_id");
		 fname = rs.getString("fname");
		 lname = rs.getString("lname");
		 email  = rs.getString("email");
		 url = rs.getString("grouplist");
		if(url!=null)
		groups = readGroupFile(url);
		String sql2="select * from profile where user_id=?";
		stmt=  conn.prepareStatement(sql2);
		stmt.setInt(1,userid);
		ResultSet rs2 = stmt.executeQuery();
		rs2.next();
		 pic=rs2.getString("pic");
		 status=rs2.getString("status");
		 mood=rs2.getString("mood");
		 contacts=getAllContacts(skypename);
		String result =fname.trim()+" "+lname.trim()+" "+email.trim()+" "+pic.trim()+" "+status.trim()+" "+mood.trim()+" "+groups.trim()+contacts;
		//System.out.println("Dao: "+result);
		return result;
	}
	private String readFile(String url) throws IOException {
		File file  = new File(url);
		BufferedReader br = new BufferedReader(new FileReader(file));
		//FileReader fr = new FileReader(file);
		//int out;
		return br.readLine();
		
		}
	private String readGroupFile(String url)  {
		File file  = new File(url);
		try {
		BufferedReader br = new BufferedReader(new FileReader(file));
		String groupname= "";
		String line= "";
		while((line = (br.readLine()))!=null) {
			groupname = groupname  + line +":";
		}
		return groupname;
		}catch(Exception e) {
			return " ";
		}
	}
	public String getSkypenameByName(String request) throws SQLException {
		String[] details = request.split(" ");
		String fname = details[0];
		String lname = details[1];
		Connection conn = null;
		conn = connect();
		PreparedStatement stmt = null;
		String sql="select skypename from user where fname= ? and lname = ?";
		stmt =  conn.prepareStatement(sql);
		stmt.setString(1, fname);
		stmt.setString(2, lname);
		ResultSet rs = stmt.executeQuery();
		rs.next();
		return rs.getString("skypename");
		
	}
	private String getAllContacts(String skypename) throws SQLException {
		Connection conn = null;
		conn = connect();
		PreparedStatement stmt = null;
		String sql="select fname,lname from user where skypename <> ? ";
		stmt =  conn.prepareStatement(sql);
		stmt.setString(1, skypename);
		ResultSet rs = stmt.executeQuery();
		String contacts =" ";
		while(rs.next()) {
			contacts = contacts+rs.getString("fname");
			contacts +="*";
			contacts = contacts+rs.getString("lname");
			contacts = contacts+"$";
		}
		return contacts; 
		
	}
}
