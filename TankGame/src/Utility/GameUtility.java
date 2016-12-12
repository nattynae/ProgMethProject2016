package Utility;

import Model.Bullet;
import Model.Entity;
import Model.Item;
import Model.Obstacle;
import Model.Player;

public class GameUtility {
	//CLOCKWISE
	public static final int UP = 0;
	public static final int RIGHT = 1;
	public static final int DOWN = 2;
	public static final int LEFT = 3;
	public static final int[] DIR_Y = {-1,0,1,0};
	public static final int[] DIR_X = {0,1,0,-1};
	public static final int CLOCKWISE = 1;
	public static final int COUNTERCLOCKWISE = -1;
	public static final int PLAYERSIZE = 30*30;
	public static final int BULLET = 12*12;
	public static final int OBSTACLE = 40*40;
	public static final int GAMESCREEN_WIDTH = 1075;
	public static final int GAMESCREEN_HEIGHT = 650;
	
	public static int getWidth(Entity e) {
		if (e instanceof Player) {
			return Player.WIDTH;
		}
		else if (e instanceof Bullet) {
			return Bullet.WIDTH;
		}
		else if (e instanceof Item) {
			return Item.WIDTH;
		}
		else if (e instanceof Obstacle) {
			return Obstacle.WIDTH;
		}
		else return 40;
	}
	
	public static int getHeight(Entity e) {
		if (e instanceof Player) {
			return Player.HEIGHT;
		}
		else if (e instanceof Bullet) {
			return Bullet.HEIGHT;
		}
		else if (e instanceof Item) {
			return Item.HEIGHT;
		}
		else if (e instanceof Obstacle) {
			return Obstacle.HEIGHT;
		}
		else return 40;
	}
}
