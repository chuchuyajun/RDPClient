package edu.cqupt.client;

import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;

import android.util.Log;

public class UdpBroadCast  extends Thread  {
	
	private static String LOG_TAG = "UDP Broadcast";
	public static final int DEFAULT_PORT = 2011;
	private static final int MAX_DATA_PACKET_LENGTH = 40;
	private byte[] buffer = new byte[MAX_DATA_PACKET_LENGTH];
	
	private String dataString;
	private DatagramSocket udpSocket;
	
	public UdpBroadCast(String dataString) {
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

