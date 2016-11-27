package ex01;

import java.util.Scanner;

public class VolatileExSimplified {
	public static void main(String[] args) throws InterruptedException {
		CustomThread customThread = new CustomThread();
		Scanner scanner = new Scanner(System.in);
		customThread.start();
		scanner.nextLine();
		customThread.shutdown();
		customThread.join();
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
			}
		}

		void shutdown() {
			running = false;
		}
	}
}
