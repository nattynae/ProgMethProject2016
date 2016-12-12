package Logic;

import Model.Item;

public class ItemDestroyer extends Thread{
	
	private Item item;
	
	public ItemDestroyer(Item item) {
		this.item = item;
	}
	
	@Override
	public void run() {
		// TODO Auto-generated method stub
		try{
			sleep(50000);
		}catch(InterruptedException e) {
			e.printStackTrace();
			return;
		}
		item.destroy();
		System.out.println("Destroy Item");
	}
}
