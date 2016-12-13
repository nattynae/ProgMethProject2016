package Utility;

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
