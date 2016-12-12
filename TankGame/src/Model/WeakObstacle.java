package Model;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;

public class WeakObstacle extends Entity implements Obstacle{
	
	public WeakObstacle(int x, int y) {
		super(1, x, y);
	}
	
	@Override
	public void draw(GraphicsContext gc, int x, int y) {
		gc.setFill(Color.BROWN);
		gc.fillRect(x - WIDTH/2 , y - HEIGHT/2, WIDTH, HEIGHT);
		gc.setStroke(Color.BLACK);
		gc.setLineWidth(1);
		gc.strokeRect(x - WIDTH/2 , y - HEIGHT/2, WIDTH, HEIGHT);
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
