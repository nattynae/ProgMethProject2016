/**
* @author Pornkrit Watcharasatianphan and Phanu Vajanopath
* @version 14 Dec 2016
* Project (1/2016) in 2110215 Prog Meth
*/
package utility;

import javafx.scene.image.Image;

public class ImageUtility {
	private static Image background, gameBackground;
	private static Image ATKSpeedIcon, ATKIcon, speedIcon, bulletIcon, HPIcon;
	private static Image waterIcon, rockIcon, brickIcon;

	// call this method first to load all images in a program
	public static void loadImages() {
		background = new Image(ClassLoader.getSystemResource("bg.png").toString());
		gameBackground = new Image(ClassLoader.getSystemResource("gameBG.png").toString());
		ATKSpeedIcon = new Image(ClassLoader.getSystemResource("ATKspeed.png").toString());
		ATKIcon = new Image(ClassLoader.getSystemResource("ATK.png").toString());
		speedIcon = new Image(ClassLoader.getSystemResource("Speed.png").toString());
		bulletIcon = new Image(ClassLoader.getSystemResource("Bullet.png").toString());
		HPIcon = new Image(ClassLoader.getSystemResource("HP.png").toString());
		waterIcon = new Image(ClassLoader.getSystemResource("waterIcon.png").toString());
		rockIcon = new Image(ClassLoader.getSystemResource("rockIcon.png").toString());
		brickIcon = new Image(ClassLoader.getSystemResource("brickIcon.png").toString());
	}

	public static Image getBackgroundImage() {
		return background;
	}

	public static Image getGameBackgroundImage() {
		return gameBackground;
	}

	public static Image getATKSpeedIconImage() {
		return ATKSpeedIcon;
	}

	public static Image getATKIconImage() {
		return ATKIcon;
	}

	public static Image getSpeedIconImage() {
		return speedIcon;
	}

	public static Image getBulletIconImage() {
		return bulletIcon;
	}

	public static Image getHPIconImage() {
		return HPIcon;
	}

	public static Image getWaterIconImage() {
		return waterIcon;
	}

	public static Image getRockIconImage() {
		return rockIcon;
	}

	public static Image getBrickIconImage() {
		return brickIcon;
	}

}
