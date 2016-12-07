package com.lister.Server;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

public class FileReaderByFileName {

	public String readFileAsString(String filename,String filename2) throws IOException {
		String finalFile= "";
		File file= new File("ChatMessage/"+filename);
		if(file.exists()) {
			finalFile = filename;
		}
		else 
			finalFile = filename2;
		BufferedReader br = new BufferedReader(new FileReader("ChatMessage/"+finalFile));
		String line = "";
		String res="";
		while((line=br.readLine())!=null) {
			res += line;
			res+="$$";
		}
		br.close();
		return res;
	}

	public String readGroupMsg(String groupname) throws IOException {
		File file= new File("GroupMessage/"+groupname);
		BufferedReader br = new BufferedReader(new FileReader(file));
		String line = "";
		String res="";
		while((line=br.readLine())!=null) {
			res += line;
			res+="$$";
		}
		br.close();
		return res;
	}
	
	public String readGroupOfUser(String skypename) throws IOException {
		String filename = "grouplistof"+skypename+".txt";
		File file= new File("UserGroups/"+filename);
		BufferedReader br = new BufferedReader(new FileReader(file));
		String line = "";
		String res="";
		while((line=br.readLine())!=null) {
			res += line;
			res+=" ";
		}
		br.close();
		return res;
	}
	

}
