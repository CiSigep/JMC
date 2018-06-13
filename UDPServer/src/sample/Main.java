package sample;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.SocketException;

public class Main {

	public static void main(String[] args) {
		try {
			DatagramSocket ds = new DatagramSocket(5000);
			
			while(true) {
				byte[] buffer = new byte[50];
				DatagramPacket dp = new DatagramPacket(buffer, buffer.length);
				ds.receive(dp);
				System.out.println("Data Received: " + new String(buffer, 0, dp.getLength()));
				String returnString = "Echo: " + new String(buffer, 0, dp.getLength());
				byte[] buffer2 = returnString.getBytes();
				InetAddress ia = dp.getAddress();
				int port = dp.getPort();
				DatagramPacket dpr = new DatagramPacket(buffer2, buffer2.length, ia, port);
				ds.send(dpr);
			}
		} catch(SocketException se) {
			System.err.println(se.getMessage());
		} catch(IOException ie) {
			System.err.println(ie.getMessage());
		}

	}

}
