package sample;

public class SharedThreadObject {
	
	private int value;
	
	public SharedThreadObject() {
		value = 1;
	}
	
	public synchronized int getValue() {
		return value;
	}

	public synchronized void setValue(int value) {
		this.value = value;
	}
	
	public synchronized void printValue() {
		System.out.print("Value: " + value + " "); // output can be split or value changed before print if not synchronized.
		System.out.println("Thread: " + Thread.currentThread().getName());
	}

}
