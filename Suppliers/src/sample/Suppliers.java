package sample;

import java.util.Random;
import java.util.function.Supplier;

public class Suppliers {
	
	public static void main(String[] args) {
		// Demonstrate a random number supplier
		final Random r = new Random();
		Supplier<Integer> IntSupplier = () -> r.nextInt(100);
		
		/*Supplier<Integer> IntSupplier = new Supplier<Integer>(){

			@Override
			public Integer get() {
				return r.nextInt(100);
			}};*/
		
		System.out.println(IntSupplier.get());
	}

}
