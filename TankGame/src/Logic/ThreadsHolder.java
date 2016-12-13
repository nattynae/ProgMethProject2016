package Logic;

import java.util.ArrayList;
import java.util.List;

public class ThreadsHolder {
	public static ThreadsHolder instance = new ThreadsHolder();
	private List<Thread> threads;
	
	public ThreadsHolder() {
		threads = new ArrayList<Thread>();
	}
	
	public List<Thread> getThreads() {
		return threads;
	}
	
	public void addThread(Thread t) {
		threads.add(t);
	}
}
