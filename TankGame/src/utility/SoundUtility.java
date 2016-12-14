/**
* @author Pornkrit Watcharasatianphan and Phanu Vajanopath
* @version 14 Dec 2016
* Project (1/2016) in 2110215 Prog Meth
*/
package utility;

import javafx.scene.media.AudioClip;

public class SoundUtility {
	private static AudioClip shootingSound;
	private static AudioClip deathSound;
	private static AudioClip collectSound;

	public static void loadSound() {
		shootingSound = new AudioClip(ClassLoader.getSystemResource("shootingSound.wav").toString());
		deathSound = new AudioClip(ClassLoader.getSystemResource("deathSound.wav").toString());
		collectSound = new AudioClip(ClassLoader.getSystemResource("collect.wav").toString());
	}

	public static void playShootingSound() {
		shootingSound.play();
	}

	public static void playDeathSound() {
		deathSound.play();
	}

	public static void playCollectSound() {
		collectSound.play();
	}
}
