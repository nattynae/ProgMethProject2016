/**
* @author Pornkrit Watcharasatianphan and Phanu Vajanopath
* @version 14 Dec 2016
* Project (1/2016) in 2110215 Prog Meth
*/
package model;

import javafx.scene.canvas.GraphicsContext;
import utility.SoundUtility;

public abstract class Item extends Entity implements BulletPassable {
	public static final int WIDTH = 30;
	public static final int HEIGHT = 30;
	public static final int ARCWIDTH = 10;
	public static final int ARCHEIGHT = 10;

	public Item(int x, int y) {
		super(1, x, y);
	}

	@Override
	public abstract void draw(GraphicsContext gc, int x, int y);

	@Override
	public int getZ() {
		return 1;
	}

	@Override
	public void hit(int dmg) {
		// nothing happen
	}

	protected abstract void increasePlayerStatus(Player player);

	public void collect(Player player) {
		if (isDestroyed())
			return;
		increasePlayerStatus(player);
		hp = 0;
		SoundUtility.playCollectSound();
	}

	public synchronized void destroy() {
		hp = 0;
	}
}
