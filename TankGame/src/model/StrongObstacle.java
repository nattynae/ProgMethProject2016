/**
* @author Pornkrit Watcharasatianphan and Phanu Vajanopath
* @version 14 Dec 2016
* Project (1/2016) in 2110215 Prog Meth
*/
package model;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import utility.ImageUtility;

public class StrongObstacle extends Entity implements Obstacle {

	public StrongObstacle(int x, int y) {
		super(1, x, y);
	}

	@Override
	public void hit(int dmg) {
		// nothing happen
	}

	@Override
	public int getZ() {
		return 3;
	}

	@Override
	public void draw(GraphicsContext gc, int x, int y) {
		Image image = ImageUtility.getRockIconImage();
		gc.drawImage(image, x - WIDTH / 2, y - HEIGHT / 2);
	}

	@Override
	public boolean isDestroyed() {
		return false;
	}
}