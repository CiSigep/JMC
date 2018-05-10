package sample.synchromethod;

public class Deadlocks {
	
	public static void main(String[] args) { // Deadlocks by synchronized methods
		PolitePerson jane = new PolitePerson("Jane");
		PolitePerson jon = new PolitePerson("Jon");
		
		new Thread(new Runnable() {
			
			@Override
			public void run() {
				jane.sayHello(jon);
			}
		}).start();
		
		new Thread(new Runnable() {
			
			@Override
			public void run() {
				jon.sayHello(jane);
			}
		}).start();
		
		
	}

}
