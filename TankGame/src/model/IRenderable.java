/**
* @author Pornkrit Watcharasatianphan and Phanu Vajanopath
* @version 14 Dec 2016
* Project (1/2016) in 2110215 Prog Meth
*/
package model;

import javafx.scene.canvas.GraphicsContext;

public interface IRenderable {
	public int getZ();
	public void draw(GraphicsContext gc, int x , int y);
	public boolean isDestroyed();
}
