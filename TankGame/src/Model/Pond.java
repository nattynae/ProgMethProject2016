package Model;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import javafx.scene.paint.Color;

public class Pond extends Entity implements BulletPassable, Obstacle {
	public final static int WIDTH = 40;
	public final static int HEIGHT = 40;
	
	public Pond(int x, int y) {
		super(100,x, y);
	}
	
	@Override
	public int getZ() {
		return 0;
	}
	
	@Override
	public void draw(GraphicsContext gc, int x, int y ) {
		Image image = IRenderableHolder.waterIcon;
		gc.drawImage(image, x-WIDTH/2, y-HEIGHT/2);
		
	}

	@Override
	public void hit(int dmg) {
		// TODO Auto-generated method stub
		
	}
}
