package sample.synchroblock;

public class Deadlocks {
	
	public static Object lock1 = new Object();
	public static Object lock2 = new Object();

	public static void main(String[] args) { //Demonstrate a deadlock. This can be fixed by making the locks be acquired in the same order.
		Thread t1 = new Thread() {
			
			@Override
			public void run() {
				synchronized (lock1) {
					System.out.println("-I have lock1");
					try {
						Thread.sleep(100);
					} catch (InterruptedException ie) {
						System.out.println("-I was interrupted");
					}
					System.out.println("-Waiting for lock2");
					synchronized(lock2) {
						System.out.println("-I have lock1 and lock2");
					}
					System.out.println("-Released lock2");
				}
				System.out.println("-Released lock1");
			}
		};
		Thread t2 = new Thread() {
			@Override
			public void run() {
				synchronized (lock2) {
					System.out.println("~I have lock2");
					try {
						Thread.sleep(100);
					} catch (InterruptedException ie) {
						System.out.println("~I was interrupted");
					}
					System.out.println("~Waiting for lock1");
					synchronized(lock1) {
						System.out.println("~I have lock1 and lock2");
					}
					System.out.println("~Released lock1");
				}
				System.out.println("~Released lock2");
			}
		};
		
		t1.start();
		t2.start();

	}

}
