package Model;

import javafx.scene.canvas.GraphicsContext;

public interface IRenderable {
	public int getZ();
	public void draw(GraphicsContext gc, int x , int y);
	public boolean isDestroyed();
}
