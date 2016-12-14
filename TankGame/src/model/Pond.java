/**
* @author Pornkrit Watcharasatianphan and Phanu Vajanopath
* @version 14 Dec 2016
* Project (1/2016) in 2110215 Prog Meth
*/
package model;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import utility.ImageUtility;

public class Pond extends Entity implements BulletPassable, Obstacle {
	public static final int WIDTH = 40;
	public static final int HEIGHT = 40;

	public Pond(int x, int y) {
		super(100, x, y); //hp can be any number
	}

	@Override
	public int getZ() {
		return 0;
	}

	@Override
	public void draw(GraphicsContext gc, int x, int y) {
		Image image = ImageUtility.getWaterIconImage();
		gc.drawImage(image, x - WIDTH / 2, y - HEIGHT / 2);

	}

	@Override
	public void hit(int dmg) {
		// nothing happen
	}
}
