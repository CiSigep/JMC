package sample;

//Demonstrating a LiveLock
public class PoliteWorker {

	public static void main(String[] args) {
		final Worker worker1 = new Worker("WorkerOne", true);
		final Worker worker2 = new Worker("WorkerTwo", true);
		
		final SharedResource resource = new SharedResource(worker1);
		
		new Thread(new Runnable() {
			
			@Override
			public void run() {
				worker1.work(resource, worker2);
				
			}
		}).start();
		new Thread(new Runnable() {
			
			@Override
			public void run() {
				worker2.work(resource, worker1);
				
			}
		}).start();
	}

}
