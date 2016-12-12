package Model;

import javafx.scene.canvas.GraphicsContext;

public abstract class Item extends Entity implements BulletPassable{
	public final static int WIDTH = 30;
	public final static int HEIGHT = 30;
	public Item(int x, int y) {
		super(1, x, y);
	}
	
	@Override
	public void draw(GraphicsContext gc, int x, int y) {
		
	}
	
	@Override
	public int getZ() {
		return 1;
	}
	
	@Override
	public void hit(int dmg) {
		//nothing happen
	}
	
	public abstract void collect(Player player);
	
	public synchronized void destroy() {
		hp = 0;
	}
}
