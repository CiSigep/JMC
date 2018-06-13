package sample;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.net.SocketTimeoutException;
import java.util.Scanner;


public class Main {

	public static void main(String[] args) {
		try(Socket socket = new Socket("localhost", 5000)) {
			socket.setSoTimeout(5000);
			BufferedReader echo = new BufferedReader(new InputStreamReader(socket.getInputStream()));
			PrintWriter stringToEcho = new PrintWriter(socket.getOutputStream(), true);
			
			Scanner in = new Scanner(System.in);
			String echoString;
			String response;
			
			do {
				System.out.print("Enter String to be echoed: ");
				echoString = in.nextLine();
				
				stringToEcho.println(echoString);
				if(!"exit".equalsIgnoreCase(echoString)) {
					response = echo.readLine();
					System.out.println(response);
				}
			} while (!"exit".equalsIgnoreCase(echoString));		
			
		} catch (SocketTimeoutException e) {
			System.err.println("Socket Timeout");
		} catch (IOException e) {
			System.err.println("Client Error:" + e.getMessage());
		} 

	}

}
