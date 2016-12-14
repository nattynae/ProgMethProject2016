/**
* @author Pornkrit Watcharasatianphan and Phanu Vajanopath
* @version 14 Dec 2016
* Project (1/2016) in 2110215 Prog Meth
*/
package model;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import javafx.scene.paint.Color;
import utility.ImageUtility;

public class SpeedItem extends Item {

	public SpeedItem(int x, int y) {
		super(x, y);
	}

	@Override
	public void draw(GraphicsContext gc, int x, int y) {
		gc.setFill(Color.YELLOW);
		gc.fillRoundRect(x - WIDTH / 2, y - HEIGHT / 2, WIDTH, HEIGHT, ARCWIDTH, ARCHEIGHT);
		gc.setStroke(Color.WHITE);
		gc.setLineWidth(1);
		gc.strokeRoundRect(x - WIDTH / 2, y - HEIGHT / 2, WIDTH, HEIGHT, ARCWIDTH, ARCHEIGHT);
		Image image = ImageUtility.getSpeedIconImage();
		gc.drawImage(image, x - WIDTH / 2, y - HEIGHT / 2);
	}

	@Override
	protected void increasePlayerStatus(Player player) {
		player.increaseSpeed(1);
	}
}
