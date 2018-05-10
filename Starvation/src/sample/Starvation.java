package sample;

import java.util.concurrent.locks.ReentrantLock;

public class Starvation {
	
	//private static Object lock = new Object();
	private static ReentrantLock lock = new ReentrantLock(true); // Fair Lock

	public static void main(String[] args) {
		Thread t1 = new Thread(new Worker("1"), "Priority 10");
		Thread t2 = new Thread(new Worker("2"), "Priority 8");
		Thread t3 = new Thread(new Worker("3"), "Priority 6");
		Thread t4 = new Thread(new Worker("4"), "Priority 4");
		Thread t5 = new Thread(new Worker("5"), "Priority 2");
		
		t1.setPriority(10);
		t2.setPriority(8);
		t3.setPriority(6);
		t4.setPriority(4);
		t5.setPriority(2);
		
		t1.start();
		t2.start();
		t3.start();
		t4.start();
		t5.start();

	}
	
	private static class Worker implements Runnable {
		private String prefix;
		
		public Worker(String prefix) {
			this.prefix = prefix;
		}
		
		@Override
		public void run() {
			for(int i = 1; i  <= 100; i++) {
/*				synchronized (lock) {
					System.out.format(prefix + "%s: i = %d\n", Thread.currentThread().getName(), i);
				}*/
				lock.lock(); // Fair Lock
				try {
					System.out.format(prefix + "%s: i = %d\n", Thread.currentThread().getName(), i);
				} finally {
					lock.unlock();
				}
			}
			
		}
	} 

}
