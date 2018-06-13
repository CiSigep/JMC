package sample;

//import java.io.BufferedReader;
import java.io.IOException;
//import java.io.InputStreamReader;
//import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;

public class Main {

	public static void main(String[] args) {
		try(ServerSocket ss = new ServerSocket(5000)) {
			
			while(true) {
				Socket socket = ss.accept();
				System.out.println("Client connected.");
				
				new Thread(new Echoer(socket)).start();
				
				/*BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
				PrintWriter out = new PrintWriter(socket.getOutputStream(), true);
				
				
				String echoString = in.readLine();
				if("exit".equalsIgnoreCase(echoString))
					break;
				
				out.println("Echo: " + echoString);*/
			}
			
		} catch (IOException e) {
			System.err.println("Exception: " + e.getMessage());
		}
	}

}
