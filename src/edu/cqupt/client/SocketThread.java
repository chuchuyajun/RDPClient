package edu.cqupt.client;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.util.Log;


public class SocketThread extends Thread{
	
	private static String TAG = "TCP/IP Socket Client ";
	

	static Bitmap bitmap;
	
	static Socket socket; 
	 static ServerSocket serverSocket;   
	 
    static DataOutputStream dos;  
    static DataInputStream dis;
    
   
    byte[] getBytes;
    
	private static final int DEFAULT_PORT = 2015;
	
	public Bitmap getBitmap(){		
		return bitmap;		
	}
	
    @Override
    public void run() {
         
            while (true) {
            	
            	if(socket == null){	
            		
	            		try {                		
	                    		serverSocket = new ServerSocket(DEFAULT_PORT); 
	                    		socket = serverSocket.accept();    
	                         	dis = new DataInputStream(socket.getInputStream());  
	                         	dos = new DataOutputStream(socket.getOutputStream());  
       
	            			} catch (IOException e) {
	            				e.printStackTrace();
	            				Log.e(TAG, "failed -- errors" );
	            			}
	            	
	    		}else{
	    			try {          		
    						int size = dis.available();    
    						byte[] data = new byte[size];  
    						dis.read(data);                           
    						bitmap = BitmapFactory.decodeByteArray(data, 0, data.length);   
   					
    					} catch (IOException e) {
    						e.printStackTrace();
    						Log.e(TAG, "failed -- errors" );
    					}
    		}
            	
    }	    
  }
   
    static class SendThread extends Thread {
    	@Override
    	public void run() {
    		
    	
    		
    	}
    }
}
