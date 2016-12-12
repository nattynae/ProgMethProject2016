package Logic;

public class TimeCounter implements Runnable{
	
	private int time;
	
	
	public TimeCounter() {
		// TODO Auto-generated constructor stub
		time = 0;
		
	}
	
	@Override
	public void run() {
		// TODO Auto-generated method stub
		while(true) {
			try{
				Thread.sleep(1000);
			}catch (InterruptedException e) {
				e.printStackTrace();
				return;
			}
			time++;
			System.out.println(time);
		}
	}
	
	public int getTime() {
		return time;
	}
}