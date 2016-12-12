package Model;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;


import javafx.scene.image.Image;
import javafx.scene.media.AudioClip;

public class IRenderableHolder {
	
	private static final IRenderableHolder instance = new IRenderableHolder();
	private List<IRenderable> entities;
	private Comparator<IRenderable> comparator;
	public static Image bg, gameBG, ATKSpeedIcon, ATKIcon, speedIcon,
	bulletIcon, HPIcon, waterIcon, rockIcon, brickIcon;
	public static AudioClip shootingSound, deathSound, collectSound;
	private static String shoot = "shootingSound.wav";
	private static String death = "deathSound.wav" ;
	private static String collect = "collect.wav" ;
	
	public IRenderableHolder() {
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
	
	public synchronized  void addEntity(IRenderable e) {
		entities.add(e);
		Collections.sort(entities, comparator);
	}
	
	private static void loadResource() {
		// TODO Auto-generated method stub
		bg = new Image(ClassLoader.getSystemResource("bg.png").toString());
		gameBG = new Image(ClassLoader.getSystemResource("gameBG.png").toString());
		ATKSpeedIcon = new Image(ClassLoader.getSystemResource("ATKspeed.png").toString());
		ATKIcon = new Image(ClassLoader.getSystemResource("ATK.png").toString());
		speedIcon = new Image(ClassLoader.getSystemResource("Speed.png").toString());
		bulletIcon = new Image(ClassLoader.getSystemResource("Bullet.png").toString());
		HPIcon = new Image(ClassLoader.getSystemResource("HP.png").toString());
		waterIcon = new Image(ClassLoader.getSystemResource("waterIcon.png").toString());
		rockIcon = new Image(ClassLoader.getSystemResource("rockIcon.png").toString());
		brickIcon = new Image(ClassLoader.getSystemResource("brickIcon.png").toString());
		shootingSound = new AudioClip(ClassLoader.getSystemResource(shoot).toString());
		deathSound = new AudioClip(ClassLoader.getSystemResource(death).toString());
		collectSound = new AudioClip(ClassLoader.getSystemResource(collect).toString());
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
