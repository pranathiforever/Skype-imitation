package com.lister.Server;

import java.net.*;
import java.io.*;
public class Server  {
	public static void main(String[] args) throws IOException {
		ServerSocket serverSocket;
		Socket clientSocket;

		if (args.length != 1) {
			System.err.println("Error with the port number");
			System.exit(1);
		}

		int portNumber = Integer.parseInt(args[0]);

		try {
			 serverSocket = new ServerSocket(portNumber);
			
			while(true){
			 clientSocket = serverSocket.accept();
			 ClientThreadControl th=new ClientThreadControl(serverSocket,clientSocket);
			th.start();
			}

		} catch (IOException e) {
			System.out
					.println("Exception caught when trying to listen on port "
							+ portNumber + " or listening for a connection");
			System.out.println(e.getMessage());
		}
	}
}
	 class ClientThreadControl  extends Thread{
		 ServerSocket serverSocket;
			Socket clientSocket;
			ClientThreadControl(ServerSocket server,Socket client) {
				this.serverSocket=server;
				this.clientSocket = client;
				//this.run();
			}
		
		public void run()  {
			try {
			PrintWriter out = new PrintWriter(
					clientSocket.getOutputStream(), true);
			BufferedReader in = new BufferedReader(new InputStreamReader(
					clientSocket.getInputStream()));
			
			String inputLine, outputLine;
			String request = "";

			ServerProtocol kkp = new ServerProtocol();

			System.out.println("Echo");
			System.out.println("hfEcho");
			while((inputLine=in.readLine())!=null){
			//inputLine="";
			request="";
			//System.out.println();
			//inputLine = in.readLine();
			request = inputLine;
			outputLine = kkp.processInput(request, clientSocket);
			out.println(outputLine);
			//sleep(100);
			}
}catch(Exception r){
				
			}
			//}
			
		}
		
	}

	