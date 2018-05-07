package sample;

//import java.util.List;
import java.util.Random;
import java.util.concurrent.ArrayBlockingQueue;
//import java.util.concurrent.locks.ReentrantLock;

public class PCProducer implements Runnable {
	//private List<String> buffer;
	//private ReentrantLock bufferLock;
	
	private ArrayBlockingQueue<String> buffer;
	
/*	public PCProducer(List<String> buffer, ReentrantLock bufferLock) {
		this.buffer = buffer;
		this.bufferLock = bufferLock;
	}*/
	
	public PCProducer(ArrayBlockingQueue<String> buffer) {
		this.buffer = buffer;
	}

	@Override
	public void run() {
		Random r = new Random();
		String[] nums = { "1", "2", "3", "4", "5" };
		
		for(String s : nums) {
			try {

				System.out.println("-Adding " + s + " to buffer");
				buffer.put(s); // Blocking queue
				/*synchronized (buffer) { // Synchronized way.
					buffer.add(s);
				}
				bufferLock.lock(); // ReentrantLock way
				try {
					buffer.add(s);
				} finally {
					bufferLock.unlock();
				}*/
				
				Thread.sleep(r.nextInt(1000));
			} catch (InterruptedException ie) {
				System.out.println("-Producer Interrupted.");
			}
		}

		System.out.println("-Adding end of file");
		try {
			buffer.put(ProducerConsumer.EOF);
		} catch (InterruptedException ie) {
			System.out.println("-Producer Interrupted");
		}
		/*synchronized (buffer) { // Synchronized way.
			buffer.add(ProducerConsumer.EOF);
		}*/
/*		bufferLock.lock(); // ReentrantLock way
		try {
			buffer.add(ProducerConsumer.EOF);
		} finally {
			bufferLock.unlock();
		}*/
		
	}

}
