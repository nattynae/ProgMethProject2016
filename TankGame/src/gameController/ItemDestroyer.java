package gameController;

import model.Item;

public class ItemDestroyer extends Thread{
	
	private Item item;
	
	public ItemDestroyer(Item item) {
		this.item = item;
	}
	
	@Override
	public void run() {
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
