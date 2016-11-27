package ex03;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.ReentrantLock;

public class ReentrantLockExample {
	private static Account account = new Account();

	public static void main(String[] args) throws InterruptedException {
		ExecutorService executor = Executors.newCachedThreadPool();

		for (int i = 0; i < 100; i++) {
			executor.execute(new AddAPennyTask());
		}

		executor.shutdown();

		executor.awaitTermination(Integer.MAX_VALUE, TimeUnit.DAYS);

		System.out.println("What is balance ? " + account.getBalance());
	}

	public static class AddAPennyTask implements Runnable {
		public void run() {
			account.deposit(1);
		}
	}

	public static class Account {
		ReentrantLock depositLock = new ReentrantLock();
		private int balance = 0;

		int getBalance() {
			return balance;
		}

		void deposit(int amount) {
			depositLock.lock();
			try {
				int newBalance = balance + amount;

				// To make the error more visible
				Thread.sleep(5);

				balance = newBalance;
			} catch (InterruptedException ex) {
				ex.printStackTrace();
			} finally {
				depositLock.unlock();
			}
		}
	}
}
