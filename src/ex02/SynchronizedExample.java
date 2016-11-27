package ex02;

public class SynchronizedExample {
	private int count = 0;

	public static void main(String[] args) {
		SynchronizedExample ex02 = new SynchronizedExample();
		ex02.doWork();
	}

	private void doWork() {
		Thread t1 = new Thread(() -> {
			for (int i = 0; i < 10000; i++) {
				increment();
			}
		});

		Thread t2 = new Thread(() -> {
			for (int i = 0; i < 10000; i++) {
				increment();
			}
		});

		t1.start();
		t2.start();

		System.out.println("Count is : " + count);
	}

	private void increment() {
		count++;
	}
}