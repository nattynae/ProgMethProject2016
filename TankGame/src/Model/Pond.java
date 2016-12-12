package Model;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;

public class Pond extends Entity implements BulletPassable, Obstacle {
	
	public final static int WIDTH = 40;
	public final static int HEIGHT = 40;
	
	public Pond(int x, int y) {
		super(100, x, y);
	}
	
	@Override
	public int getZ() {
		return 0;
	}
	
	@Override
	public void hit(int dmg) {
		//nothing happen
	}
	
	@Override
	public void draw(GraphicsContext gc, int x, int y ) {
		gc.setFill(Color.BLUE);
		gc.fillRect(x - WIDTH/2 , y - HEIGHT/2, WIDTH, HEIGHT);
	}
}
