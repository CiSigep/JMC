package sample;

//import java.util.List;
import java.util.concurrent.ArrayBlockingQueue;
//import java.util.concurrent.locks.ReentrantLock;

public class PCConsumer implements Runnable {
	
	//private List<String> buffer;
	//private ReentrantLock bufferLock;
	private ArrayBlockingQueue<String> buffer;
	private String prefix;
	
/*	public PCConsumer(List<String> buffer, String prefix, ReentrantLock bufferLock) {
		this.buffer = buffer;
		this.prefix = prefix;
		this.bufferLock = bufferLock;
	}*/
	
	public PCConsumer(ArrayBlockingQueue<String> buffer, String prefix) {
		this.buffer = buffer;
		this.prefix = prefix;
	}
	
	@Override
	public void run() {
		while(true) {
			try {
				synchronized (buffer) {
					if(buffer.isEmpty())
						continue;
					else if(ProducerConsumer.EOF.equals(buffer.peek())) {
						System.out.println(prefix + "EOF found, exiting...");
						break;
					} else {
						System.out.println(prefix + "Received " + buffer.take());
					}
				}
			} catch (InterruptedException ie) {
				System.out.println(prefix + "Consumer Interrupted");
			}
			/*synchronized (buffer) { // Synchronized way
				if(buffer.isEmpty())
					continue;
				else if (ProducerConsumer.EOF.equalsIgnoreCase(buffer.get(0))) {
					System.out.println(prefix + "EOF found, exiting...");
					break;
				}
				else {
					System.out.println(prefix + "Received " + buffer.remove(0));
				}
			}	*/
			/*bufferLock.lock(); // ReentrantLock way
			try {
				if(buffer.isEmpty()) {
					continue;
				}
				else if (ProducerConsumer.EOF.equalsIgnoreCase(buffer.get(0))) {
					System.out.println(prefix + "EOF found, exiting...");
					break;
				}
				else {
					System.out.println(prefix + "Received " + buffer.remove(0));
				}
			} finally {
				bufferLock.unlock();
			}*/
		}
	}
}
