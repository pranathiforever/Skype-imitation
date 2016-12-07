package skype;
/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */


import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.DataInputStream;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.io.PrintStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.URL;
import java.io.File;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.PrintWriter;

/**
 *
 * @author listeruser
 */
class Transfer {
   // public final static int SOCKET_PORT = 13267;  // 
public String FILE_TO_SEND = null;//"/home/listeruser/Downloads/aishu.mp4";    
public static int counter = 0;
//private byte[] byteArray;
  /*  public SimpleFileServer(URL url) {
        FILE_TO_SEND = url.toString();
    }*/
public  void recv(String path,DataInputStream is) throws IOException {
     
    
                        System.out.println("path"+path);
                        String[] ext = new String[2];
                        ext = path.split("/");
                        DataInputStream in = is;
                        int length;
                        String filename = ext[ext.length-1];
                        String[] spl = filename.split("\\.");
                        String exten = spl[1];
                        String FILE_TO_RECEIVED = "/home/listeruser/Downloads/rec/"+counter+"."+exten;//.jpeg";+extn; 
                       counter++;
                       //int FILE_SIZE = 100; 
                       int bytesRead;
                       int current = 0;
                       length=100000;
                       System.out.println("length "+length);
                       FileOutputStream fos = null;
                       BufferedOutputStream bos = null;
                       //DataInputStream is =  new DataInputStream(sock.getInputStream());
                       //DataInputStream is =(DataInputStream) sock.getInputStream();
                       byte [] mybytearray  = new byte[length];
                       //mybytearray = null;
                       fos = new FileOutputStream(FILE_TO_RECEIVED);
                       bos = new BufferedOutputStream(fos);
                       bytesRead = in.read(mybytearray,0,length);
                     for(int i=0;i<mybytearray.length;i++)
                       System.out.println("\nhere "+mybytearray[i]);
                       current = bytesRead;
                       System.out.println("current "+current);
                       bos.write(mybytearray, 0 , current);
                       bos.flush();
                       System.out.println("File " + FILE_TO_RECEIVED
                           + " downloaded (" + current + " bytes read)");

                              fos.close();
                              bos.close();
                            //  in.close();
                              //is.skipBytes(bytesRead);
                                     
/*  do {
      //System.out.println("HIHERE3");
     bytesRead =
        is.read(mybytearray, current, (mybytearray.length-current));
     if(bytesRead >= 0) current += bytesRead;
  } while(bytesRead > -1);*/

}



    
    }
    