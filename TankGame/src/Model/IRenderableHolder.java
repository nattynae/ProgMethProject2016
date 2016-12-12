package Model;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import javafx.scene.image.Image;
import javafx.scene.media.AudioClip;
import javafx.stage.FileChooser;

public class IRenderableHolder {
	
	private static final IRenderableHolder instance = new IRenderableHolder();
	private List<IRenderable> entities;
	private Comparator<IRenderable> comparator;
	public static Image bg, ATKSpeedIcon, ATKIcon, speedIcon, bulletIcon, HPIcon;
	public static AudioClip shootingSound;
	public static AudioClip deathSound;
	private static String shoot = "shootingSound.wav";
	private static String death = "deathSound.wav" ;
	
	public IRenderableHolder(){
		entities = new ArrayList<>();
		comparator = (IRenderable o1, IRenderable o2) -> {
			if (o1.getZ() > o2.getZ())
				return 1;
			return -1;
		};
	}
	static{
		loadResource();
	}
	
	public synchronized void addEntity(IRenderable e) {
		entities.add(e);
		Collections.sort(entities, comparator);
	}
	
	private static void loadResource(){
		// TODO Auto-generated method stub
		System.out.println(ClassLoader.getSystemResource("bg.png").toString());
		bg = new Image(ClassLoader.getSystemResource("bg.png").toString());
		ATKSpeedIcon = new Image(ClassLoader.getSystemResource("ATKspeed.png").toString());
		ATKIcon = new Image(ClassLoader.getSystemResource("ATK.png").toString());
		speedIcon = new Image(ClassLoader.getSystemResource("Speed.png").toString());
		bulletIcon = new Image(ClassLoader.getSystemResource("Bullet.png").toString());
		HPIcon = new Image(ClassLoader.getSystemResource("HP.png").toString());
		shootingSound = new AudioClip(ClassLoader.getSystemResource(shoot).toString());
		deathSound = new AudioClip(ClassLoader.getSystemResource(death).toString());
	}

	public List<IRenderable> getEntities() {
		return entities;
	}
	
	public static IRenderableHolder getInstance(){
		return instance;
	}
	
	public void clear(){
		entities.clear();
	}
}
