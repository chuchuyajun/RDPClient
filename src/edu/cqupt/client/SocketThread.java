package edu.cqupt.client;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;


public class SocketThread extends Thread{
	

	static Socket socket; 
	static ServerSocket serverSocket;   	 
    static DataOutputStream dos;  
    static DataInputStream dis;   
	private final int DEFAULT_PORT = 2015;
	String sendStr;	
	SendThread sendThread;	
	onBitmapReceiveCallback ReceivetCallback;	
	
	public SocketThread(onBitmapReceiveCallback ReceivetCallback){
		this.ReceivetCallback = ReceivetCallback;
		sendThread = new SendThread();
	}	
	
	 public static interface onBitmapReceiveCallback {
	        public void onReceived(Bitmap bitmap);
	    }
	 
	 public void send(String cmd){
		 sendStr = cmd;
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
	                         	sendThread.start();
	                         	
	            			} catch (IOException e) {  e.printStackTrace();   }
	            	
	    		}else{
	    			try {          		
    						int size = dis.available();    
    						byte[] data = new byte[size];  
    						dis.read(data);                           
    						Bitmap bitmap = BitmapFactory.decodeByteArray(data, 0, data.length);       						
    						ReceivetCallback.onReceived(bitmap);  					
    					} catch (IOException e) {
    						e.printStackTrace();    						
    					}
    		}
            	
    }	    
  }
   
     class SendThread extends Thread {
    	@Override
    	public void run() {    		
    			while (true) {   			 
    				if(socket == null && sendStr == null){	           		            		
             			try {          		
     						dos.writeBytes(sendStr);
     						dos.flush();  					
     					} catch (IOException e) {
     						e.printStackTrace();   			
     					}finally{  sendStr = null;  } 		
             	}             	
    		 }	        		
    	}
    }
}
