package Model;

import Utility.SoundUtility;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import javafx.scene.paint.Color;

public class HPItem extends Item{
	
	public HPItem(int x, int y) {
		super(x, y);
	}
	
	@Override
	public void draw(GraphicsContext gc, int x, int y) {
		gc.setFill(Color.CHARTREUSE);
		gc.fillRoundRect(x-WIDTH/2, y-HEIGHT/2, WIDTH, HEIGHT, 10, 10);
		gc.setStroke(Color.WHITE);
		gc.setLineWidth(1);
		gc.strokeRoundRect(x-WIDTH/2, y-HEIGHT/2, WIDTH, HEIGHT, 10, 10);
		Image image = IRenderableHolder.HPIcon;
		gc.drawImage(image, x-WIDTH/2, y-HEIGHT/2);
	}
	
	@Override
	public void collect(Player player) {
		player.increaseHP(1);
		hp = 0;
		SoundUtility.playSound("collect");
	}
}
