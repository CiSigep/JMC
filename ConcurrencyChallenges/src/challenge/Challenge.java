package challenge;

import java.util.concurrent.locks.ReentrantLock;

import challenge.model.BankAccount;

public class Challenge {

	// Challenge 1
	/*public static void main(String[] args) {
		final BankAccount ba = new BankAccount("12345-678", 1000.00);
		
		new Thread(new Runnable() {
			
			@Override
			public void run() {
				// Deposit $300
				ba.deposit(300.00);
				
				ba.displayBalance();
				
				// Withdraw $50
				ba.withdraw(50.00);
				
				ba.displayBalance();
			}
		}).start();
		
		new Thread(new Runnable() {
			
			@Override
			public void run() {
				// Deposit $203.75
				ba.deposit(203.75);
				
				ba.displayBalance();
				
				// Withdraw $100
				ba.withdraw(100.00);
				
				ba.displayBalance();
			}
		}).start();
	}*/
	
	// Challenge 4 
	public static void main(String[] args) {
		ReentrantLock lock = new ReentrantLock();
		final BankAccount ba = new BankAccount("12345-678", 1000.00, lock);
		
		new Thread(new Runnable() {
			
			@Override
			public void run() {
				// Deposit $300
				ba.deposit(300.00);
				
				ba.displayBalance();
				
				// Withdraw $50
				ba.withdraw(50.00);
				
				ba.displayBalance();
			}
		}).start();
		
		new Thread(new Runnable() {
			
			@Override
			public void run() {
				// Deposit $203.75
				ba.deposit(203.75);
				
				ba.displayBalance();
				
				// Withdraw $100
				ba.withdraw(100.00);
				
				ba.displayBalance();
			}
		}).start();
	}
}
