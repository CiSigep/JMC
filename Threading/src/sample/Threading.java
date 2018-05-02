package sample;

public class Threading {

	public static void main(String[] args) {
		System.out.println("-This is the main thread.");

		new NamedExtendThread().start();
		new Thread(new NamedImplementRunnable()).start();
		
		Thread t = new Thread() {
			@Override
			public void run() {
				System.out.println("*This is an anonymous thread.");
				
				try {
					Thread.sleep(3000);
				} catch (InterruptedException e) {
					System.out.println("*This thread was awakened by interrupt.");
					return;
				}
				
				System.out.println("*This thread woke up on its own.");
			}
		};
		
		t.start();
		
		new Thread(() -> {
			System.out.println("$This is a lambda thread.");
			
			try {
				t.join();
				System.out.println("$This thread waited on the anonymous thread.");
			} catch (InterruptedException e) {
				System.out.println("$This thread failed to wait.");
			}
			
		}).start();
		
		System.out.println("-And the main thread is done.");
	}

}
