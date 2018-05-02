package sample;

public class NamedExtendThread extends Thread {
	@Override
	public void run() {
		System.out.println(">This is a class extending Thread.");
	}

}
