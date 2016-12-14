/**
* @author Pornkrit Watcharasatianphan and Phanu Vajanopath
* @version 14 Dec 2016
* Project (1/2016) in 2110215 Prog Meth
*/
package utility;

import model.Bullet;
import model.Entity;
import model.Item;
import model.Obstacle;
import model.Player;

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
	public static final int BOARD_WIDTH = 1920;
	public static final int BOARD_HEIGHT = 1160;

	public static final String[] MAP_DATA = { "StrongObstacle 20 20", "WeakObstacle 100 20", "WeakObstacle 220 20",
			"WeakObstacle 380 20", "WeakObstacle 500 20", "WeakObstacle 540 20", "StrongObstacle 580 20",
			"WeakObstacle 1020 20", "WeakObstacle 1060 20", "Pond 1580 20", "Pond 1780 20", "WeakObstacle 100 60",
			"WeakObstacle 220 60", "WeakObstacle 380 60", "WeakObstacle 500 60", "WeakObstacle 540 60",
			"StrongObstacle 580 60", "WeakObstacle 740 60", "StrongObstacle 780 60", "WeakObstacle 820 60",
			"StrongObstacle 860 60", "StrongObstacle 900 60", "StrongObstacle 940 60", "StrongObstacle 980 60",
			"Pond 1020 60", "Pond 1060 60", "StrongObstacle 1100 60", "StrongObstacle 1140 60",
			"StrongObstacle 1180 60", "StrongObstacle 1220 60", "StrongObstacle 1260 60", "StrongObstacle 1300 60",
			"Pond 1340 60", "StrongObstacle 1380 60", "WeakObstacle 1420 60", "StrongObstacle 1620 60",
			"WeakObstacle 1820 60", "WeakObstacle 1860 60", "StrongObstacle 100 100", "WeakObstacle 220 100",
			"StrongObstacle 300 100", "StrongObstacle 340 100", "StrongObstacle 380 100", "StrongObstacle 420 100",
			"StrongObstacle 460 100", "StrongObstacle 500 100", "StrongObstacle 540 100", "StrongObstacle 580 100",
			"WeakObstacle 780 100", "WeakObstacle 980 100", "WeakObstacle 1420 100", "StrongObstacle 1660 100",
			"WeakObstacle 1860 100", "StrongObstacle 140 140", "StrongObstacle 180 140", "WeakObstacle 220 140",
			"Pond 380 140", "StrongObstacle 580 140", "WeakObstacle 780 140", "WeakObstacle 980 140",
			"WeakObstacle 1420 140", "Pond 1540 140", "Pond 1580 140", "Pond 1620 140", "StrongObstacle 1700 140",
			"Pond 1900 140", "WeakObstacle 20 180", "WeakObstacle 60 180", "WeakObstacle 100 180",
			"WeakObstacle 140 180", "StrongObstacle 180 180", "StrongObstacle 220 180", "StrongObstacle 260 180",
			"StrongObstacle 300 180", "StrongObstacle 340 180", "StrongObstacle 380 180", "StrongObstacle 420 180",
			"StrongObstacle 460 180", "WeakObstacle 500 180", "StrongObstacle 700 180", "StrongObstacle 740 180",
			"StrongObstacle 780 180", "StrongObstacle 820 180", "StrongObstacle 860 180", "StrongObstacle 900 180",
			"StrongObstacle 940 180", "StrongObstacle 1180 180", "StrongObstacle 1220 180", "StrongObstacle 1260 180",
			"StrongObstacle 1300 180", "StrongObstacle 1340 180", "StrongObstacle 1380 180", "StrongObstacle 1420 180",
			"Pond 1540 180", "Pond 1580 180", "Pond 1620 180", "WeakObstacle 1740 180", "WeakObstacle 20 220",
			"Pond 60 220", "WeakObstacle 180 220", "StrongObstacle 340 220", "WeakObstacle 500 220",
			"StrongObstacle 700 220", "WeakObstacle 940 220", "StrongObstacle 980 220", "WeakObstacle 1020 220",
			"WeakObstacle 1100 220", "WeakObstacle 1140 220", "WeakObstacle 1180 220", "WeakObstacle 1300 220",
			"StrongObstacle 1460 220", "WeakObstacle 1500 220", "Pond 1540 220", "Pond 1580 220", "Pond 1620 220",
			"WeakObstacle 1740 220", "WeakObstacle 1780 220", "WeakObstacle 20 260", "Pond 60 260",
			"WeakObstacle 180 260", "StrongObstacle 340 260", "StrongObstacle 500 260", "WeakObstacle 540 260",
			"WeakObstacle 580 260", "WeakObstacle 620 260", "WeakObstacle 660 260", "StrongObstacle 700 260",
			"StrongObstacle 940 260", "StrongObstacle 980 260", "WeakObstacle 1060 260", "WeakObstacle 1100 260",
			"WeakObstacle 1140 260", "WeakObstacle 1180 260", "StrongObstacle 1300 260", "StrongObstacle 1460 260",
			"WeakObstacle 1660 260", "StrongObstacle 1820 260", "WeakObstacle 20 300", "Pond 60 300",
			"StrongObstacle 180 300", "Player 300 300", "StrongObstacle 340 300", "StrongObstacle 500 300",
			"StrongObstacle 700 300", "StrongObstacle 900 300", "StrongObstacle 980 300", "WeakObstacle 1020 300",
			"WeakObstacle 1100 300", "WeakObstacle 1140 300", "WeakObstacle 1180 300", "StrongObstacle 1300 300",
			"StrongObstacle 1460 300", "StrongObstacle 1540 300", "WeakObstacle 1660 300", "StrongObstacle 1860 300",
			"WeakObstacle 20 340", "Pond 60 340", "WeakObstacle 100 340", "WeakObstacle 140 340",
			"StrongObstacle 180 340", "StrongObstacle 220 340", "StrongObstacle 260 340", "StrongObstacle 300 340",
			"StrongObstacle 340 340", "WeakObstacle 380 340", "WeakObstacle 420 340", "WeakObstacle 460 340",
			"StrongObstacle 500 340", "StrongObstacle 580 340", "WeakObstacle 660 340", "WeakObstacle 700 340",
			"StrongObstacle 860 340", "StrongObstacle 980 340", "StrongObstacle 1140 340", "StrongObstacle 1300 340",
			"WeakObstacle 1340 340", "WeakObstacle 1380 340", "WeakObstacle 1420 340", "StrongObstacle 1460 340",
			"StrongObstacle 1580 340", "WeakObstacle 1660 340", "Pond 1900 340", "WeakObstacle 20 380", "Pond 60 380",
			"StrongObstacle 180 380", "WeakObstacle 340 380", "StrongObstacle 500 380", "StrongObstacle 580 380",
			"WeakObstacle 660 380", "WeakObstacle 700 380", "WeakObstacle 780 380", "StrongObstacle 820 380",
			"StrongObstacle 980 380", "StrongObstacle 1140 380", "WeakObstacle 1300 380", "WeakObstacle 1460 380",
			"WeakObstacle 1500 380", "WeakObstacle 1540 380", "WeakObstacle 1580 380", "StrongObstacle 1620 380",
			"WeakObstacle 20 420", "Pond 60 420", "WeakObstacle 180 420", "WeakObstacle 340 420",
			"WeakObstacle 460 420", "WeakObstacle 500 420", "StrongObstacle 580 420", "WeakObstacle 660 420",
			"WeakObstacle 700 420", "StrongObstacle 780 420", "StrongObstacle 980 420", "StrongObstacle 1140 420",
			"WeakObstacle 1300 420", "WeakObstacle 1460 420", "StrongObstacle 1580 420", "StrongObstacle 1660 420",
			"WeakObstacle 20 460", "Pond 60 460", "WeakObstacle 180 460", "WeakObstacle 340 460",
			"WeakObstacle 500 460", "StrongObstacle 580 460", "WeakObstacle 660 460", "WeakObstacle 700 460",
			"StrongObstacle 740 460", "WeakObstacle 980 460", "StrongObstacle 1140 460", "Pond 1220 460",
			"Pond 1260 460", "WeakObstacle 1300 460", "StrongObstacle 1340 460", "StrongObstacle 1380 460",
			"WeakObstacle 1460 460", "StrongObstacle 1540 460", "WeakObstacle 1580 460", "StrongObstacle 1700 460",
			"WeakObstacle 20 500", "WeakObstacle 60 500", "StrongObstacle 100 500", "WeakObstacle 140 500",
			"StrongObstacle 180 500", "WeakObstacle 220 500", "StrongObstacle 260 500", "StrongObstacle 300 500",
			"StrongObstacle 340 500", "StrongObstacle 380 500", "StrongObstacle 420 500", "StrongObstacle 460 500",
			"WeakObstacle 500 500", "StrongObstacle 580 500", "WeakObstacle 660 500", "WeakObstacle 700 500",
			"StrongObstacle 740 500", "StrongObstacle 780 500", "StrongObstacle 820 500", "StrongObstacle 860 500",
			"StrongObstacle 900 500", "WeakObstacle 940 500", "WeakObstacle 980 500", "WeakObstacle 1020 500",
			"WeakObstacle 1060 500", "WeakObstacle 1100 500", "StrongObstacle 1140 500", "StrongObstacle 1220 500",
			"StrongObstacle 1260 500", "WeakObstacle 1300 500", "Pond 1340 500", "Pond 1380 500",
			"WeakObstacle 1460 500", "WeakObstacle 1580 500", "StrongObstacle 1740 500", "StrongObstacle 100 540",
			"StrongObstacle 180 540", "WeakObstacle 540 540", "StrongObstacle 580 540", "WeakObstacle 620 540",
			"WeakObstacle 660 540", "WeakObstacle 700 540", "StrongObstacle 740 540", "WeakObstacle 980 540",
			"StrongObstacle 1020 540", "WeakObstacle 1060 540", "StrongObstacle 1100 540", "StrongObstacle 1140 540",
			"WeakObstacle 1460 540", "WeakObstacle 1500 540", "WeakObstacle 1540 540", "Pond 1580 540",
			"StrongObstacle 1780 540", "WeakObstacle 1820 540", "WeakObstacle 1860 540", "WeakObstacle 1900 540",
			"StrongObstacle 100 580", "StrongObstacle 180 580", "WeakObstacle 540 580", "StrongObstacle 580 580",
			"WeakObstacle 660 580", "WeakObstacle 700 580", "StrongObstacle 780 580", "WeakObstacle 980 580",
			"WeakObstacle 1020 580", "WeakObstacle 1060 580", "WeakObstacle 1100 580", "StrongObstacle 1140 580",
			"WeakObstacle 1300 580", "WeakObstacle 1460 580", "WeakObstacle 1580 580", "StrongObstacle 1740 580",
			"WeakObstacle 20 620", "WeakObstacle 60 620", "StrongObstacle 100 620", "WeakObstacle 140 620",
			"StrongObstacle 180 620", "WeakObstacle 220 620", "WeakObstacle 260 620", "WeakObstacle 300 620",
			"WeakObstacle 340 620", "WeakObstacle 380 620", "WeakObstacle 420 620", "WeakObstacle 460 620",
			"WeakObstacle 500 620", "WeakObstacle 660 620", "WeakObstacle 700 620", "StrongObstacle 820 620",
			"WeakObstacle 1020 620", "Pond 1060 620", "WeakObstacle 1100 620", "StrongObstacle 1140 620",
			"WeakObstacle 1300 620", "WeakObstacle 1420 620", "WeakObstacle 1460 620", "StrongObstacle 1540 620",
			"WeakObstacle 1580 620", "StrongObstacle 1700 620", "WeakObstacle 1780 620", "StrongObstacle 100 660",
			"StrongObstacle 180 660", "WeakObstacle 260 660", "Pond 380 660", "WeakObstacle 500 660",
			"StrongObstacle 700 660", "StrongObstacle 860 660", "WeakObstacle 1020 660", "WeakObstacle 1060 660",
			"WeakObstacle 1100 660", "StrongObstacle 1140 660", "WeakObstacle 1300 660", "StrongObstacle 1460 660",
			"StrongObstacle 1580 660", "StrongObstacle 1660 660", "StrongObstacle 1820 660", "StrongObstacle 1860 660",
			"StrongObstacle 100 700", "StrongObstacle 180 700", "WeakObstacle 260 700", "Pond 340 700",
			"StrongObstacle 380 700", "Pond 420 700", "WeakObstacle 500 700", "WeakObstacle 540 700",
			"WeakObstacle 580 700", "WeakObstacle 620 700", "WeakObstacle 660 700", "StrongObstacle 700 700",
			"StrongObstacle 900 700", "StrongObstacle 1140 700", "WeakObstacle 1180 700", "WeakObstacle 1220 700",
			"StrongObstacle 1260 700", "Pond 1300 700", "StrongObstacle 1340 700", "WeakObstacle 1380 700",
			"WeakObstacle 1420 700", "StrongObstacle 1460 700", "WeakObstacle 1580 700", "StrongObstacle 1620 700",
			"WeakObstacle 1900 700", "StrongObstacle 100 740", "StrongObstacle 180 740", "WeakObstacle 260 740",
			"Pond 300 740", "StrongObstacle 340 740", "WeakObstacle 380 740", "StrongObstacle 420 740", "Pond 460 740",
			"WeakObstacle 500 740", "StrongObstacle 700 740", "StrongObstacle 940 740", "StrongObstacle 1060 740",
			"StrongObstacle 1140 740", "StrongObstacle 1460 740", "WeakObstacle 1500 740", "WeakObstacle 1540 740",
			"StrongObstacle 1580 740", "WeakObstacle 1660 740", "StrongObstacle 100 780", "StrongObstacle 180 780",
			"WeakObstacle 260 780", "Pond 340 780", "StrongObstacle 380 780", "Pond 420 780", "WeakObstacle 500 780",
			"StrongObstacle 700 780", "Pond 820 780", "StrongObstacle 980 780", "StrongObstacle 1140 780",
			"StrongObstacle 1460 780", "StrongObstacle 1540 780", "WeakObstacle 1580 780", "WeakObstacle 1700 780",
			"StrongObstacle 1900 780", "StrongObstacle 100 820", "StrongObstacle 180 820", "WeakObstacle 260 820",
			"Pond 380 820", "WeakObstacle 500 820", "StrongObstacle 700 820", "WeakObstacle 980 820",
			"StrongObstacle 1140 820", "StrongObstacle 1460 820", "Player 1500 820", "WeakObstacle 1580 820",
			"StrongObstacle 1660 820", "WeakObstacle 1740 820", "WeakObstacle 1780 820", "WeakObstacle 1860 820",
			"StrongObstacle 100 860", "StrongObstacle 180 860", "WeakObstacle 260 860", "WeakObstacle 300 860",
			"WeakObstacle 340 860", "WeakObstacle 380 860", "WeakObstacle 420 860", "WeakObstacle 460 860",
			"StrongObstacle 500 860", "StrongObstacle 620 860", "StrongObstacle 700 860", "WeakObstacle 980 860",
			"StrongObstacle 1180 860", "StrongObstacle 1220 860", "StrongObstacle 1260 860", "StrongObstacle 1300 860",
			"StrongObstacle 1340 860", "StrongObstacle 1380 860", "StrongObstacle 1420 860", "WeakObstacle 1460 860",
			"WeakObstacle 1500 860", "WeakObstacle 1540 860", "WeakObstacle 1580 860", "StrongObstacle 1660 860",
			"StrongObstacle 1820 860", "WeakObstacle 20 900", "WeakObstacle 60 900", "WeakObstacle 100 900",
			"WeakObstacle 140 900", "WeakObstacle 180 900", "WeakObstacle 380 900", "StrongObstacle 500 900",
			"StrongObstacle 620 900", "WeakObstacle 780 900", "WeakObstacle 820 900", "WeakObstacle 860 900",
			"WeakObstacle 900 900", "WeakObstacle 940 900", "WeakObstacle 1220 900", "WeakObstacle 1380 900",
			"StrongObstacle 1660 900", "StrongObstacle 1700 900", "StrongObstacle 1740 900", "Pond 1780 900",
			"StrongObstacle 1820 900", "WeakObstacle 220 940", "StrongObstacle 500 940", "StrongObstacle 620 940",
			"StrongObstacle 780 940", "Pond 820 940", "StrongObstacle 860 940", "Pond 900 940",
			"StrongObstacle 940 940", "WeakObstacle 1260 940", "WeakObstacle 1380 940", "StrongObstacle 1540 940",
			"WeakObstacle 1620 940", "StrongObstacle 1820 940", "WeakObstacle 260 980", "StrongObstacle 420 980",
			"StrongObstacle 460 980", "StrongObstacle 500 980", "Pond 540 980", "Pond 580 980",
			"StrongObstacle 620 980", "StrongObstacle 660 980", "StrongObstacle 700 980", "StrongObstacle 780 980",
			"Pond 820 980", "StrongObstacle 860 980", "Pond 900 980", "StrongObstacle 940 980",
			"StrongObstacle 1100 980", "StrongObstacle 1140 980", "StrongObstacle 1180 980", "StrongObstacle 1220 980",
			"StrongObstacle 1260 980", "StrongObstacle 1300 980", "StrongObstacle 1340 980", "StrongObstacle 1380 980",
			"StrongObstacle 1420 980", "StrongObstacle 1460 980", "StrongObstacle 1500 980", "StrongObstacle 1540 980",
			"StrongObstacle 1580 980", "Pond 1860 980", "StrongObstacle 260 1020", "WeakObstacle 300 1020",
			"WeakObstacle 340 1020", "StrongObstacle 380 1020", "StrongObstacle 420 1020", "StrongObstacle 700 1020",
			"WeakObstacle 780 1020", "WeakObstacle 820 1020", "WeakObstacle 860 1020", "WeakObstacle 900 1020",
			"WeakObstacle 940 1020", "WeakObstacle 1060 1020", "WeakObstacle 1100 1020", "WeakObstacle 1140 1020",
			"WeakObstacle 1180 1020", "WeakObstacle 1220 1020", "WeakObstacle 1260 1020", "WeakObstacle 1300 1020",
			"WeakObstacle 1340 1020", "WeakObstacle 1380 1020", "WeakObstacle 1420 1020", "WeakObstacle 1460 1020",
			"WeakObstacle 1500 1020", "StrongObstacle 1540 1020", "StrongObstacle 1580 1020",
			"StrongObstacle 1620 1020", "StrongObstacle 1660 1020", "StrongObstacle 1700 1020",
			"StrongObstacle 1900 1020", "StrongObstacle 140 1060", "WeakObstacle 180 1060", "StrongObstacle 220 1060",
			"StrongObstacle 260 1060", "StrongObstacle 420 1060", "WeakObstacle 700 1060", "WeakObstacle 860 1060",
			"StrongObstacle 1100 1060", "StrongObstacle 1140 1060", "StrongObstacle 1180 1060",
			"StrongObstacle 1220 1060", "StrongObstacle 1260 1060", "StrongObstacle 1300 1060",
			"StrongObstacle 1340 1060", "StrongObstacle 1380 1060", "StrongObstacle 1420 1060",
			"StrongObstacle 1460 1060", "StrongObstacle 1500 1060", "StrongObstacle 1540 1060",
			"StrongObstacle 1580 1060", "WeakObstacle 1700 1060", "StrongObstacle 60 1100", "WeakObstacle 100 1100",
			"StrongObstacle 140 1100", "StrongObstacle 260 1100", "WeakObstacle 420 1100", "WeakObstacle 700 1100",
			"WeakObstacle 860 1100", "WeakObstacle 1100 1100", "WeakObstacle 1140 1100", "WeakObstacle 1180 1100",
			"WeakObstacle 1220 1100", "WeakObstacle 1260 1100", "WeakObstacle 1300 1100", "WeakObstacle 1340 1100",
			"StrongObstacle 1540 1100", "WeakObstacle 1700 1100", "StrongObstacle 60 1140", "WeakObstacle 140 1140",
			"WeakObstacle 260 1140", "WeakObstacle 420 1140", "WeakObstacle 540 1140", "WeakObstacle 580 1140",
			"StrongObstacle 700 1140", "StrongObstacle 780 1140", "StrongObstacle 860 1140", "StrongObstacle 940 1140",
			"WeakObstacle 1100 1140", "WeakObstacle 1140 1140", "WeakObstacle 1180 1140", "WeakObstacle 1220 1140",
			"WeakObstacle 1260 1140", "WeakObstacle 1300 1140", "WeakObstacle 1340 1140", "Pond 1540 1140",
			"StrongObstacle 1740 1140", "WeakObstacle 1780 1140", "StrongObstacle 1820 1140", "WeakObstacle 1860 1140",
			"StrongObstacle 1900 1140" };

	// winner from past round
	private static String winner = "";

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

	public static String getWinner() {
		return winner;
	}

	public static void setWinner(String winner) {
		GameUtility.winner = winner;
	}

}
