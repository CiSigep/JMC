package sample;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;

public class Echoer implements Runnable {
	
	private Socket socket;

	public Echoer(Socket socket) {
		this.socket  = socket;
	}
	
	@Override
	public void run() {
		try {
			while(true) {
				BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
				PrintWriter out = new PrintWriter(socket.getOutputStream(), true);
				
				
				String echoString = in.readLine();
				if("exit".equalsIgnoreCase(echoString))
					break;
				
				out.println("Echo: " + echoString);
			}
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			try {
				socket.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}

}
