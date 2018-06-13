package sample;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.SocketTimeoutException;
import java.util.Scanner;

public class Main {

	public static void main(String[] args) {
		try {
			InetAddress ia = InetAddress.getLocalHost(); //getByName()
			DatagramSocket ds = new DatagramSocket();
			
			Scanner in = new Scanner(System.in);
			String echoString;
			
			do {
				System.out.print("Enter String to be echoed: ");
				echoString = in.nextLine();
				
				byte[] buffer = echoString.getBytes();
				
				DatagramPacket dp = new DatagramPacket(buffer, buffer.length, ia, 5000);
				ds.send(dp);
				
				byte[] buffer2 = new byte[50];
				
				dp = new DatagramPacket(buffer2, buffer2.length);
				ds.receive(dp);
				System.out.println(new String(buffer2, 0, dp.getLength()));
				
			} while(!"exit".equalsIgnoreCase(echoString));
			
		} catch (SocketTimeoutException ste) {
			System.err.println("Socket Timeout");
		} catch (IOException e) {
			System.err.println("Client Exception: " + e.getMessage());
		}
	}

}
