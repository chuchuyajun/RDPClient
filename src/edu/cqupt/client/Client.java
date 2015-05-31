
package edu.cqupt.client;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.ServerSocket;
import java.net.Socket;

import android.app.Activity;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.util.Log;
import android.widget.ImageView;
import android.widget.TextView;

public class Client extends Activity{
	
	static ImageView imageView;	
	static Bitmap bitmap;
	
    static TextView cmd;
    static DataOutputStream dos;
    static ServerSocket serverSocket;
    
    static DataInputStream dis;
    
    byte[] getBytes;
    
   
    
    static Handler handler = new Handler(){
        @Override
        public void handleMessage(Message msg) {
            cmd.setText((CharSequence) msg.obj);
            
          //  Toast.makeText(getApplication(),data.length, Toast.LENGTH_SHORT).show();

            if(bitmap != null){
            	imageView.setImageBitmap(bitmap);  
            }
            
        }
    };

  
	protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.client);
        
        cmd = (TextView) findViewById(R.id.status);
        imageView = (ImageView)findViewById(R.id.imageView);
        
        Thread serverSocketListen = new Thread(new MySocketServer());
        serverSocketListen.start();
        
        new UdpClient("CQUPT").start();
      
	}
	
	public class UdpClient extends Thread  {

		private String LOG_TAG = "UDP Broadcast";
		
		public static final int DEFAULT_PORT = 2011;
		private static final int MAX_DATA_PACKET_LENGTH = 40;
		private byte[] buffer = new byte[MAX_DATA_PACKET_LENGTH];
		
		private String dataString ="CQUPT";
		private DatagramSocket udpSocket;
		
		public UdpClient(String dataString) {
			this.dataString = dataString;
		}
		
		public void run() {
			DatagramPacket dataPacket = null;

			try {
				udpSocket = new DatagramSocket(DEFAULT_PORT);

				dataPacket = new DatagramPacket(buffer, MAX_DATA_PACKET_LENGTH);
				byte[] data = dataString.getBytes();
				dataPacket.setData(data);
				dataPacket.setLength(data.length);
				dataPacket.setPort(DEFAULT_PORT);

				InetAddress broadcastAddr = InetAddress.getByName("255.255.255.255");
				dataPacket.setAddress(broadcastAddr);
			} catch (Exception e) {
				Log.e(LOG_TAG, e.toString());
			}
			
			try {
				udpSocket.send(dataPacket);
				sleep(10);
			} catch (Exception e) {
				Log.e(LOG_TAG, e.toString());
			}

			udpSocket.close();
		}

	}
	
	    static class MySocketServer implements Runnable {
	    	
	        private static final String TAG="MySocketServer";
	        private static final int serverListenPort = 2011;

	     
	        @Override
	        public void run() {

	        	
	                while (true) {
	                	try {
                		
	                	serverSocket = new ServerSocket(serverListenPort); 
	                    Socket socket = serverSocket.accept();    
		                 
	                
		                 if (socket != null) {
		                	 
		                	 dis = new DataInputStream(socket.getInputStream());  
		                	 dos = new DataOutputStream(socket.getOutputStream());
		                	 int size = dis.available();    
			                 byte[] data = new byte[size];  
			                 dis.read(data);
			                 
			                 Bitmap bitmapTemp = null;
			                  
			                   bitmapTemp = BitmapFactory.decodeByteArray(data, 0, data.length); 
			                   
			                   bitmap = bitmapTemp;
	           
			                   Log.e(TAG," RemoteSocketAddress:" + String.valueOf(socket.getRemoteSocketAddress()));		                
			                    Message.obtain(handler, 0,"byte read : "+ size).sendToTarget();
		                   
			                    dis.close();
		                    Log.e(TAG, "port:" + serverSocket.getLocalPort());
		                 }
		                 
 
	            } catch (IOException e) {
	                e.printStackTrace();
	                Log.e(TAG, "failed -- errors" );
	            }
	        }	    
	      }
	 }

}
