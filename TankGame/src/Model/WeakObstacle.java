package Model;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import javafx.scene.paint.Color;

public class WeakObstacle extends Entity implements Obstacle{
	
	public WeakObstacle(int x, int y) {
		super(1, x, y);
	}
	
	@Override
	public void draw(GraphicsContext gc, int x, int y) {
		Image image = IRenderableHolder.brickIcon;
		gc.drawImage(image, x-WIDTH/2, y-HEIGHT/2);
	}
	
	@Override
	public void hit(int dmg) {
		hp -= dmg;
	}
	
	@Override
	public int getZ() {
		return 2;
	}
	
}
