package Model;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import javafx.scene.paint.Color;

public class StrongObstacle extends Entity implements Obstacle{

	public StrongObstacle(int x, int y) {
		super(1, x, y);
	}
	
	@Override
	public void hit(int dmg) {
		//nothing happen
	}
	
	@Override
	public int getZ() {
		return 3; //except Pond has z = 0.
	}
	
	@Override
	public void draw(GraphicsContext gc, int x, int y ) {
		Image image = IRenderableHolder.rockIcon;
		gc.drawImage(image, x-WIDTH/2, y-HEIGHT/2);
	}
	
	@Override
	public boolean isDestroyed() {
		return false;
	}
}