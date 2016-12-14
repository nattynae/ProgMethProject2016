/**
* @author Pornkrit Watcharasatianphan and Phanu Vajanopath
* @version 14 Dec 2016
* Project (1/2016) in 2110215 Prog Meth
*/
package model;

import javafx.scene.canvas.GraphicsContext;

public abstract class Entity implements IRenderable{
	
	protected int hp;
	protected int x, y;
	
	public Entity(int hp, int x,int y) {
		this.hp = hp;
		this.x = x;
		this.y = y;
	}
	
	@Override
	public abstract int getZ();
	
	@Override
	public abstract void draw(GraphicsContext gc, int x, int y);
	
	@Override
	public boolean isDestroyed() {
		return hp <= 0;
	}
	
	public abstract void hit(int dmg);
	
	public int getX(){
		return x;
	}
	
	public int getY(){
		return y;
	}
	
	public void setX(int x){
		this.x = x;
	}
	
	public void setY(int y){
		this.y = y;
	}
	
	
}
