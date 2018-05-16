package challenge.model;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.ReentrantLock;

/*
 * Starting class for the concurrency challenges
 * Challenge 1 - Two people using a bank account with $1000 in it, one deposits $300 and withdraws $50, the other
 *               deposits $203.75 and withdraws $100. Create two threads to represent this.
 *               
 * Challenge 2 - Make this class thread safe using the "synchronized" keyword.
 * 
 * Challenge 3 - The getAccountNumber and displayAccountNumber were added, make the class thread safe again with
 *               the "synchronized" keyword. - Not necessary, we're not changing the data, just fetching it.
 *               
 * Challenge 4 - Use the ReentrantLock class to synchronize this class.
 * 
 * Challenge 5 - Use tryLock with a timeout of 1 second. If it fails to get the lock, print out "Could not get the lock."
 * 
 * Challenge 6 - Make the status variable in the withdraw and deposit methods thread safe - Not necessary, status is a local variable and
 *               each thread will have its own status variable.
 * */

public class BankAccount {
 
    private double balance;
    private String accountNumber;
    private ReentrantLock lock;
 
/*    public BankAccount(String accountNumber, double initialBalance) {
        this.accountNumber = accountNumber;
        this.balance = initialBalance;
    }*/
    
    public BankAccount(String accountNumber, double balance, ReentrantLock lock) {
		this.balance = balance;
		this.accountNumber = accountNumber;
		this.lock = lock;
	}
 
    // Challenge 2
/*  public synchronized void deposit(double amount) { // Method synchronization
        balance += amount;
    }
 
    public synchronized void withdraw(double amount) {
        balance -= amount;
    }

	public void deposit(double amount) { // Synchronized blocks
    	synchronized (this) {
            balance += amount;
		}
    }
 
    public void withdraw(double amount) {
    	synchronized (this) {
            balance -= amount;
		}
    }*/
    
    // Challenge 4
    public void deposit(double amount) {
    	boolean status = false; // Challenge 6
    	
    	try {
			if(lock.tryLock(1L, TimeUnit.SECONDS)) {
		    	try {
		    		balance += amount;
		    		status = true;
		    	} finally {
		    		lock.unlock();
		    	}
	    	}
			else
				System.out.println("Could not get the lock");
	    		
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    	
    	System.out.println("Transaction status: " + status);
    }
 
    public void withdraw(double amount) {
    	boolean status = false; // Challenge 6
    	
    	try {
			if(lock.tryLock(1L, TimeUnit.SECONDS)) {
		    	try {
		    		balance -= amount;
		    		status = true;
		    	} finally {
		    		lock.unlock();
		    	}
	    	}
			else
				System.out.println("Could not get the lock");
	    		
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    	System.out.println("Transaction status: " + status);
    }
  
    public void displayAccountNumber() {
    	System.out.println("Acct. Number: " + accountNumber);
    }

	public String getAccountNumber() {
		return accountNumber;
	}
    
    public void displayBalance() { // See what is happening
    	System.out.println("Balance: $" + balance);
    }
	
}