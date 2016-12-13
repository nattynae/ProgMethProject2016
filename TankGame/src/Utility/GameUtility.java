package Utility;

import Model.Bullet;
import Model.Entity;
import Model.Item;
import Model.Obstacle;
import Model.Player;

public class GameUtility {

	// sort direction by clockwise
	public static final int UP = 0;
	public static final int RIGHT = 1;
	public static final int DOWN = 2;
	public static final int LEFT = 3;
	public static final int[] DIR_Y = { -1, 0, 1, 0 };
	public static final int[] DIR_X = { 0, 1, 0, -1 };

	// screen size
	public static final int GAMESCREEN_WIDTH = 1075;
	public static final int GAMESCREEN_HEIGHT = 650;
	public static final int FRAME_WIDTH = 500;
	public static final int FRAME_HEIGHT = 500;

	public static int getWidth(Entity e) {
		if (e instanceof Player) {
			return Player.WIDTH;
		} else if (e instanceof Bullet) {
			return Bullet.WIDTH;
		} else if (e instanceof Item) {
			return Item.WIDTH;
		} else { // if error occurs, return obstacle's size as default
			return Obstacle.WIDTH;
		}
	}

	public static int getHeight(Entity e) {
		if (e instanceof Player) {
			return Player.HEIGHT;
		} else if (e instanceof Bullet) {
			return Bullet.HEIGHT;
		} else if (e instanceof Item) {
			return Item.HEIGHT;
		} else { // if error occurs, return obstacle's size as default
			return Obstacle.HEIGHT;
		}
	}

}
