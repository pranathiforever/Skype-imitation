package com.lister.Server;

import java.io.*;
import java.net.*;
import java.util.*;

public class Pipeline {
	//List<clientThread> t = new ArrayList<clientThread>();
	public void startChat(String skypename,Socket clientSocket) {
				clientThread th = new clientThread(clientSocket,skypename);
				clientThread.addMe(th);
				//t.add(th);
				//th.start();
	}
	
	/*public void startChat(String skypename,Socket clientSocket) {
		clientThread th = new clientThread(clientSocket,skypename);
		clientThread.addMe(th);
		//t.add(th);
		//th.start();
}*/
	public void startConversation(String request) {
		
	    List<clientThread>t = clientThread.getOnlineList();
		String details[]  = request.split(" ");
		String sender =  details[0];
		String receiver = details[1];
		for(int i=0;i<t.size();i++) {
			if(t.get(i).clientIdentity.equalsIgnoreCase(sender)) {
				System.out.println(sender+" : "+receiver);
				t.get(i).destClient = receiver;
				t.get(i).run();
				
			}
		}
	}
	
	public void stopConversation(String request) throws IOException {
		 List<clientThread>t = clientThread.getOnlineList();
		String details[]  = request.split(" ");
		String sender =  details[0];
		String receiver = details[1];
		for(int i=0;i<t.size();i++) {
			if(t.get(i).clientIdentity.equalsIgnoreCase(sender)||t.get(i).clientIdentity.equalsIgnoreCase(receiver)) {
				t.get(i).is.close();
				t.get(i).os.close();
			}
		}
		
	}
	public String removeThread(String request)  {
		 List<clientThread>t = clientThread.getOnlineList();
		String details[]  = request.split(" ");
		String closingThread = details[0];
		for(int i=0;i<t.size();i++) {
			if(t.get(i).clientIdentity.equalsIgnoreCase(closingThread)) {
				
				
				if(t.get(i)!=null){
					if(t.get(i).isAlive())
						t.get(i).suspend();
					
				}
				t.remove(i);	
			}
		}
		
		return "logged out";
		
	}
}

	