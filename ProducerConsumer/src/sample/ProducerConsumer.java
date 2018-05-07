package sample;

//import java.util.ArrayList;
//import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Future;
//import java.util.concurrent.locks.ReentrantLock;

public class ProducerConsumer {
	
	public static final String EOF = "EOF";
	
	public static void main(String[] args) {
		//ArrayList<String> buffer = new ArrayList<>();
		//ReentrantLock bufferLock = new ReentrantLock();
		
		ArrayBlockingQueue<String> buffer = new ArrayBlockingQueue<String>(6);
		
		ExecutorService exService = Executors.newFixedThreadPool(4); // Executor way.
		
/*		exService.execute(new PCProducer(buffer, bufferLock));
		exService.execute(new PCConsumer(buffer, "~", bufferLock));
		exService.execute(new PCConsumer(buffer, "+", bufferLock));*/
		
		exService.execute(new PCProducer(buffer));
		exService.execute(new PCConsumer(buffer, "~"));
		exService.execute(new PCConsumer(buffer, "+"));
		
		
		Future<String> f = exService.submit(new Callable<String>() {

			@Override
			public String call() throws Exception {
				System.out.println("$Printed from the Callable Class.");
				return "This is a callable result";
			}
			
		});
		
		try {
			System.out.println(f.get());
		} catch (ExecutionException ee) {
			System.out.println("Exception in Execution");
		}
		catch (InterruptedException ie) {
			System.out.println("Thread interrupted.");
		}
		
		exService.shutdown();

		
/*		Thread pThread = new Thread(new PCProducer(buffer, bufferLock)); // Threading way.
		Thread cThread = new Thread(new PCConsumer(buffer, "~", bufferLock));
		Thread c2Thread = new Thread(new PCConsumer(buffer, "+", bufferLock));
		
		pThread.setName("Producer Thread");
		cThread.setName("Consumer1 Thread");
		c2Thread.setName("Consumer2 Thread");
		
		pThread.start();
		cThread.start();
		c2Thread.start();*/
		
	}
}
