package com.lister.Server;

import java.io.DataInputStream;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintStream;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.ArrayList;
import java.util.List;

import com.lister.service.AccountService;

class clientThread extends Thread {

	DataInputStream is = null;
	byte[] byteArray = null;
	String line;
	String destClient = "";
	String name;
	PrintStream os = null;
	Socket clientSocket = null;
	static List<clientThread> t = new ArrayList<clientThread>();
	String clientIdentity;

	public static List<clientThread> getOnlineList() {
		return t;
	}

	public static void addMe(clientThread temp) {
		t.add(temp);
	}

	public clientThread(Socket clientSocket, String skypename) {
		this.clientSocket = clientSocket;
		this.clientIdentity = skypename;
		try {
			is = new DataInputStream(clientSocket.getInputStream());
		} catch (IOException e) {
			e.printStackTrace();
			System.out.print("error");
		}

		try {
			os = new PrintStream(clientSocket.getOutputStream());
		} catch (IOException e) {
			e.printStackTrace();
			System.out.print("error");
		}

	}

	public synchronized void run() {
		try {
			while (true) {
				line = is.readLine();
				
				if (line == null)  /// no response
					continue;
				
				if (line != null && line.contains("videochat")) {
					videoChat();
				}

				// For group chat the line should be "groupchat groupname"///
				else if (line != null && line.contains("groupchat")) {
					// get group members by group name.
					groupChat();
				}
				// for navigation
				else if (line.contains("1fff2C") || line.contains("2fff2C")
						|| line.contains("3fff2C") || line.contains("4fff2C")
						|| line.contains("5fff2C") || line.contains("6fff2C")
						|| line.contains("7fff2C") || line.contains("8fff2C")
						|| line.contains("9fff2C") || line.contains("Afff2C")
						|| line.contains("Bfff2C") || line.contains("Cfff2C")
						|| line.contains("Dfff2C")) {
					System.out.println(line);
					ServerProtocol sp = new ServerProtocol();
					String response = sp.processInput(line,this.clientSocket);
					System.out.println("Response for nav " + response);
					System.out.println(response);
					this.os.println(" " + response);
				}

				else {
					singleChatConversation();
				}
			}

		} catch (Exception e) {
			// TODO Auto-generated catc) {
		}

	}

	private void singleChatConversation() throws IOException {
		String path = "";
		if (line != null && line.equals("file share")) {
			path = is.readLine();
			System.out.println("path" + path);
			FileRecv rc = new FileRecv();
			byteArray = rc.getFromSender(is);
			System.out.println("inside client thread going to write"
					+ byteArray.toString());
		}

		if (t.size() > 1) {
			for (int i = 0; i < t.size(); i++) {
				if ((t.get(i)) != this) {
					if ((t.get(i)).clientIdentity.equalsIgnoreCase(destClient)&&(this.clientIdentity).equalsIgnoreCase(t.get(i).destClient)) {
						System.out.println("writing:" + line);
						// if(!line.equals(null))
						if (byteArray == null) {
							writeConversationToFile(i);
							System.out.println("Single Chat: "
									+ t.get(i).clientIdentity);
							(t.get(i)).os.println("\n\n" + this.clientIdentity
									+ " : " + line);
						} else { // sending file
							System.out.println("Single File: "
									+ t.get(i).clientIdentity);
							t.get(i).os.println("file share");
							t.get(i).os.println(path);
							byte[] temp = new byte[byteArray.length + 1];
							for (int l = 0; l < byteArray.length; l++)
								temp[l] = byteArray[l];
							byteArray = null;
							t.get(i).os.write(temp);

						}
						break;
					}

				}
			}

		}

	}

	private void writeConversationToFile(int i) throws IOException {
		String fileName = t.get(i).clientIdentity + "-" + t.get(i).destClient;
		String fileName2 = t.get(i).destClient + "-" + t.get(i).clientIdentity;
		File file = new File("ChatMessage/" + fileName);
		File file1 = new File("ChatMessage/" + fileName2);
		if (file.exists()) {
			PrintWriter pw = new PrintWriter(new FileWriter(file, true));
			pw.println(t.get(i).destClient + " : " + line);
			pw.close();
		} else if (file1.exists()) {
			PrintWriter pw = new PrintWriter(new FileWriter(file1, true));
			pw.println(t.get(i).destClient + " : " + line);
			pw.close();
		} else {
			file.createNewFile();
			PrintWriter pw = new PrintWriter(new FileWriter(file, true));
			pw.println(t.get(i).destClient + " : " + line);
			pw.close();
		}

	}

	private void groupChat() {
		String path = "";
		String details[] = line.split(" ");
		String groupname = details[1];
		AccountService newService = new AccountService();
		try {
			String members = newService.getGroupMembersByGroupName(groupname);
			System.out.println(members);
			for (int i = 0; i < t.size(); i++) {
				System.out.println("Test: " + t.get(i).clientIdentity);
			}

			line = is.readLine();

			// /file share
			if (line != null && line.equals("file share")) {
				path = is.readLine();
				System.out.println("path" + path);
				System.out.println("file share man");
				FileRecv rc = new FileRecv();
				byteArray = rc.getFromSender(is);
				System.out.println("inside clientthread going to write"
						+ byteArray.toString());
			}
			if (t.size() > 1) {
				for (int i = 0; i < t.size(); i++) {
					System.out.println("inside " + t.get(i).clientIdentity);
					if ((t.get(i)) != this) {
						if (members.toLowerCase().contains(
								(t.get(i)).clientIdentity.toLowerCase())) {
							System.out.println("inside "
									+ t.get(i).clientIdentity);
							if (byteArray != null) {
								t.get(i).os.println("file share");
								t.get(i).os.println(path);
								t.get(i).os.write(byteArray);

							} else {
								File file = new File("GroupMessage/"
										+ groupname);
								PrintWriter pw = new PrintWriter(
										new FileWriter(file, true));
								pw.println(t.get(i).clientIdentity + " : "
										+ line);
								pw.close();

								(t.get(i)).os.println("\n\n"
										+ this.clientIdentity + " : " + line);
							}
						}
					}

				}
			}
			byteArray = null;

		} catch (Exception e) {
			System.out.println("Exception " + e);
			e.printStackTrace();
		}

	}

	private void videoChat() {
		String clientAddress = " ";
		String details[] = line.split(" ");
		String receiver = details[1];
		String senderIpAddress = this.clientSocket.getRemoteSocketAddress()
				.toString();
		for (int i = 0; i < t.size(); i++) {
			if (t.get(i).clientIdentity.equalsIgnoreCase(receiver)) {

				clientAddress = t.get(i).clientSocket.getRemoteSocketAddress()
						.toString();
				t.get(i).os.println("videorec " + senderIpAddress + " "
						+ clientAddress);
			}
		}
		System.out.println("going to write " + clientAddress);
		this.os.println("videosender " + clientAddress + " " + senderIpAddress);

	}

}
