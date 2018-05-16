package challenge;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;



/**
 * Challenge 7 - We are given this code, there is an issue in the code, find and fix it.
 * 
 * Notes: Running the application seems to get an endless loop of "Account busy messages."
 *        
 *        Thread one sleeps, holding the lock to bank account one, thread two starts and sleeps,
 *        holding the lock to bank account two. Thread one wakes up and withdraws from bank account
 *        one and attempts to deposit to bank account two, but thread two is holding the lock. Thread
 *        two is trying to do the same but thread one has the lock on bank account one.
 *        
 *        There seems to be no lock release as well.
 *        
 *        Add lock release.
 */
public class ChallengeSeven {
    public static void main(String[] args) {
        BankAccount account1 = new BankAccount("12345-678", 500.00);
        BankAccount account2 = new BankAccount("98765-432", 1000.00);
 
        new Thread(new Transfer(account1, account2, 10.00), "Transfer1").start();
        new Thread(new Transfer(account2, account1, 55.88), "Transfer2").start();
    }
}
 
class BankAccount {
    private double balance;
    private String accountNumber;
    private Lock lock = new ReentrantLock();
 
    BankAccount(String accountNumber, double balance) {
        this.accountNumber = accountNumber;
        this.balance = balance;
    }
 
    public boolean withdraw(double amount) {
        if (lock.tryLock()) {
        	try {
	            try {
	                // Simulate database access
	                Thread.sleep(100);
	            }
	            catch (InterruptedException e) {
	            }
            
	            balance -= amount;
	            System.out.printf("%s: Withdrew %f\n", Thread.currentThread().getName(), amount);
	            return true;
            } finally {
            	lock.unlock(); // Lock release so other threads can lock it.
            }

        }
        return false;
    }
 
    public boolean deposit(double amount) {
        if (lock.tryLock()) {
        	try {
	            try {
	                // Simulate database access
	                Thread.sleep(100);
	            }
	            catch (InterruptedException e) {
	            }
            
	            balance += amount;
	            System.out.printf("%s: Deposited %f\n", Thread.currentThread().getName(), amount);
	            return true;
            } finally {
            	lock.unlock(); // Lock release so other threads can lock it.
            }
        }
        return false;
    }
 
    public boolean transfer(BankAccount destinationAccount, double amount) {
        if (withdraw(amount)) {
            if (destinationAccount.deposit(amount)) {
                return true;
            }
            else {
                // The deposit failed. Refund the money back into the account.
                System.out.printf("%s: Destination account busy. Refunding money\n",
                        Thread.currentThread().getName());
                deposit(amount);
            }
        }
 
        return false;
    }
}
 
class Transfer implements Runnable {
    private BankAccount sourceAccount, destinationAccount;
    private double amount;
 
    Transfer(BankAccount sourceAccount, BankAccount destinationAccount, double amount) {
        this.sourceAccount = sourceAccount;
        this.destinationAccount = destinationAccount;
        this.amount = amount;
    }
 
    public void run() {
        while (!sourceAccount.transfer(destinationAccount, amount))
            continue;
        System.out.printf("%s completed\n", Thread.currentThread().getName());
    }

}
