package ex01;

import java.util.Scanner;

public class VolatileEx {
	public static void main(String[] args) throws InterruptedException {
		CustomThread customThread = new CustomThread();
		Scanner scanner = new Scanner(System.in);
		customThread.start();
		System.out.println("Main thread : waiting ...");
		System.out.println("Main thread : Scanner.nextLine() is a blocking call, waiting ...");
		scanner.nextLine();
		System.out.println("Main thread : continues");
		customThread.shutdown();
		customThread.join();
		System.out.println("Main thread : ended");

	}

	static class CustomThread extends Thread {
		private volatile boolean running = true;

		@Override
		public void run() {
			while (running) {
				try {
					Thread.sleep(1000);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
				System.out.println("Hello from another thread");
			}

			System.out.println("Thread finished because shutdown() called, bye");
		}

		void shutdown() {
			running = false;
		}
	}
}
