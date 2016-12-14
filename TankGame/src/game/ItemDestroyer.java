/**
* @author Pornkrit Watcharasatianphan and Phanu Vajanopath
* @version 14 Dec 2016
* Project (1/2016) in 2110215 Prog Meth
*/
package game;

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
