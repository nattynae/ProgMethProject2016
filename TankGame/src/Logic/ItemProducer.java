package Logic;

import java.util.List;

import Model.ATKItem;
import Model.ATKSpeedItem;
import Model.BulletItem;
import Model.Entity;
import Model.HPItem;
import Model.IRenderable;
import Model.IRenderableHolder;
import Model.Item;
import Model.SpeedItem;
import Utility.GameUtility;
import Utility.RandomUtility;

public class ItemProducer implements Runnable {

	@Override
	public void run() {
		while (true) {
			try {
				for (int i = 0; i < 4; i++) {
					System.out.println("Produce Item");
					Item item = buildItem();
					if (item == null)
						continue;
					IRenderableHolder.getInstance().addEntity(item);
					Thread t = new Thread(new ItemDestroyer(item));
					ThreadsHolder.instance.addThread(t);
					t.start();
				}
				Thread.sleep(7500);
			} catch (InterruptedException e) {
				e.printStackTrace();
				return;
			}
		}
	}

	private static Item buildItem() {
		// find location to place item
		int x = RandomUtility.random(GameUtility.BOARD_WIDTH - Item.WIDTH) + Item.WIDTH/2;
		int y = RandomUtility.random(GameUtility.BOARD_HEIGHT - Item.HEIGHT) +Item.HEIGHT/2;
		while (!canPlaceItemAt(x, y)) {
			x = RandomUtility.random(GameUtility.BOARD_WIDTH - Item.WIDTH) + Item.WIDTH/2;
			y = RandomUtility.random(GameUtility.BOARD_HEIGHT - Item.HEIGHT) +Item.HEIGHT/2;
		}

		// random type of item
		int type = RandomUtility.random(6);
		Item item;
		switch (type) {
		case 1:
			item = new ATKItem(x, y);
			break;
		case 2:
			item = new ATKSpeedItem(x, y);
			break;
		case 3:
			item = new BulletItem(x, y);
			break;
		case 4:
			item = new HPItem(x, y);
			break;
		case 5:
			item = new SpeedItem(x, y);
			break;
		default:
			return null; // maybe not produce any item
		}
		return item;
	}

	private static boolean canPlaceItemAt(int x, int y) {
		Item dummy = new HPItem(x, y); // dummy item
		List<IRenderable> entities = IRenderableHolder.getInstance().getEntities();
		for (int i = 0; i < entities.size(); i++) {
			if (entities.get(i) instanceof Entity) {
				Entity e = (Entity) entities.get(i);
				if (GameManager.isCollide(e, dummy)) {
					return false;
				}
			}
		}
		return true;
	}
}
