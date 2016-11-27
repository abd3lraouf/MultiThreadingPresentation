package ex01;

import java.util.Scanner;

public class VolatileExSimplified {
	public static void main(String[] args) {
		CustomThread customThread = new CustomThread();
		Scanner scanner = new Scanner(System.in);
		customThread.start();
		scanner.nextLine();
		customThread.shutdown();
	}

	static class CustomThread extends Thread {
		private boolean running = true;

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
