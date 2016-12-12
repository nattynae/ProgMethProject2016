package Model;

import javafx.scene.canvas.GraphicsContext;
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
		return 1; //except Pond has z = 0.
	}
	
	@Override
	public void draw(GraphicsContext gc, int x, int y ) {
		gc.setFill(Color.GRAY);
		gc.fillRect(x - WIDTH/2 , y - HEIGHT/2, WIDTH, HEIGHT);
		gc.setStroke(Color.BLACK);
		gc.setLineWidth(1);
		gc.strokeRect(x - WIDTH/2 , y - HEIGHT/2, WIDTH, HEIGHT);
	}
	
	@Override
	public boolean isDestroyed() {
		return false;
	}
}
