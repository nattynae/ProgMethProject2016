package gameController;

import java.util.List;

import model.ATKItem;
import model.ATKSpeedItem;
import model.BulletItem;
import model.Entity;
import model.HPItem;
import model.IRenderable;
import model.IRenderableHolder;
import model.Item;
import model.SpeedItem;
import utility.GameUtility;
import utility.RandomUtility;

public class ItemProducer implements Runnable {

	@Override
	public void run() {
		while (true) {
			for (int i = 0; i < 4; i++) {
				System.out.println("Produce Item");
				Item item = buildItem();
				if (item == null)
					continue;
				IRenderableHolder.getInstance().addEntity(item);
				Thread t = new Thread(new ItemDestroyer(item));
				ThreadHolder.getInstance().addThread(t);
				t.start();
			}
			try {
				Thread.sleep(7500);
			} catch (InterruptedException e) {
				e.printStackTrace();
				return;
			}
		}
	}

	private static Item buildItem() {
		int x = RandomUtility.random(GameUtility.BOARD_WIDTH - Item.WIDTH) + Item.WIDTH / 2;
		int y = RandomUtility.random(GameUtility.BOARD_HEIGHT - Item.HEIGHT) + Item.HEIGHT / 2;
		while (!canPlaceAt(x, y)) { // TODO: check if can place here
			x = RandomUtility.random(GameUtility.BOARD_WIDTH - Item.WIDTH) + Item.WIDTH / 2;
			y = RandomUtility.random(GameUtility.BOARD_HEIGHT - Item.HEIGHT) + Item.HEIGHT / 2;

		}
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
			return null;
		}
		return item;
	}

	private static boolean canPlaceAt(int x, int y) {
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
