package Logic;

import java.util.ArrayList;
import java.util.List;

public class ThreadHolder {
	private static ThreadHolder instance = new ThreadHolder();
	private List<Thread> threads;

	public ThreadHolder() {
		threads = new ArrayList<Thread>();
	}

	public List<Thread> getThreads() {
		return threads;
	}

	public void addThread(Thread t) {
		threads.add(t);
	}

	public void clear() {
		for (Thread t : threads) {
			t.interrupt();
		}
		threads.clear();
	}

	public static ThreadHolder getInstance() {
		return instance;
	}
}
