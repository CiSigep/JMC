package sample;

import java.util.Random;

public class Message {
	private String msg;
	private boolean empty = true;
	
	public synchronized String read() {
		while(empty) {
			try {
				wait();
			} catch (InterruptedException e) {
			}
		}
		
		empty = true;
		notifyAll();
		return msg;
	}
	
	public synchronized void write(String msg) {
		while(!empty) {
			try {
				wait();
			} catch (InterruptedException e) {
			}
		}
		
		this.msg = msg;
		empty = false;
		notifyAll();
		
	}

}

class MessageReader implements Runnable {
	
	private Message m;
	
	public MessageReader(Message m) {
		this.m = m;
	}
	
	@Override
	public void run() {
		Random r = new Random();
		
		for(String latestMsg = m.read(); !"Done".equals(latestMsg); latestMsg = m.read()) {
			System.out.println(latestMsg);
			try {
				Thread.sleep(r.nextInt(2000));
			} catch (InterruptedException e) {

			}
		}
		
	}
}

class MessageWriter implements Runnable {
	
	private Message m;
	private String[] messages = { "Tu Fui, Ego Eris.",
			                      "Memento Mori.",
			                      "Elapsam semel occasionem non ipse potest Iuppiter reprehendere.",
			                      "Plaudite. Acta est fabula."};
	
	public MessageWriter(Message m) {
		this.m = m;
	}
	
	@Override
	public void run() {
		Random r = new Random();
		
		for(String msg : messages) {
			m.write(msg);
			try {
				Thread.sleep(r.nextInt(2000));
			} catch (InterruptedException e) {
			}
		}
		m.write("Done");
	}
	
	
	
}