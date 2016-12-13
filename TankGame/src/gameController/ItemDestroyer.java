package gameController;

import model.Item;

public class ItemDestroyer implements Runnable {

	private Item item;

	public ItemDestroyer(Item item) {
		this.item = item;
	}

	@Override
	public void run() {
		try {
			Thread.sleep(50000);
			item.destroy();
			System.out.println("Destroy Item");
		} catch (InterruptedException e) {
			e.printStackTrace();
			return;
		}
	}
}
