package com.lister.Server;

import java.io.BufferedOutputStream;
import java.io.DataInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.net.Socket;

public class FileRecv {
	
	public byte[] getFromSender(DataInputStream is) throws IOException {
		
		byte[] mybytearray;
		//int FILE_SIZE = is.available();
		int FILE_SIZE = 1000000;
		System.out.println("FILE_SIZE"+FILE_SIZE);
		int bytesRead;
		int current = 0;
		mybytearray = new byte[FILE_SIZE];
		bytesRead = is.read(mybytearray, 0, mybytearray.length);
		current = bytesRead;
		System.out.println("File transferred (" + current + " bytes read)"
				+ mybytearray.toString());
		return mybytearray;

	}

	
}
