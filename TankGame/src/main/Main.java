/**
* @author Pornkrit Watcharasatianphan and Phanu Vajanopath
* @version 14 Dec 2016
* Project (1/2016) in 2110215 Prog Meth
*/
package main;

import java.util.List;

import game.ItemProducer;
import game.ThreadHolder;
import javafx.animation.AnimationTimer;
import javafx.application.Application;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.input.KeyEvent;
import javafx.stage.Stage;
import javafx.stage.WindowEvent;
import ui.GameScreen;
import ui.StartScreen;
import utility.GameUtility;
import utility.ImageUtility;
import utility.RandomUtility;
import utility.SoundUtility;

public class Main extends Application {
	private static Main instance;
	private Stage primaryStage;
	private Scene startScene;
	private Scene gameScene;

	private StartScreen startScreen;
	private GameScreen gameScreen;
	private AnimationTimer animation, startAnimation;

	private boolean isGameSceneShown = false;

	public static void main(String args[]) {
		launch(args);
	}

	@Override
	public void start(Stage primaryStage) throws Exception {
		instance = this;
		RandomUtility.init();
		this.primaryStage = primaryStage;
		loadResource();
		gameScreen = new GameScreen();

		gameScene = new Scene(gameScreen, GameUtility.GAMESCREEN_WIDTH, GameUtility.GAMESCREEN_HEIGHT);
		startScreen = new StartScreen(gameScreen);
		startScene = new Scene(startScreen, GameUtility.GAMESCREEN_WIDTH, GameUtility.GAMESCREEN_HEIGHT);

		gameScene.setOnKeyPressed((KeyEvent e) -> {
			System.out.println(e.getCode().toString());
			gameScreen.keyPressed(e.getCode());
		});

		gameScene.setOnKeyReleased((KeyEvent e) -> {
			System.out.println("           " + e.getCode().toString());
			gameScreen.keyReleased(e.getCode());
		});

		animation = new AnimationTimer() {
			
			@Override
			public void handle(long now) {
				gameScreen.update();
				gameScreen.paintComponenet();
				List<Thread> threads = ThreadHolder.getInstance().getThreads();
				for (int i = threads.size() - 1; i >= 0; i--) {
					if (!threads.get(i).isAlive()) {
						threads.remove(i);
					}
				}
			}
		};

		startAnimation = new AnimationTimer() {

			@Override
			public void handle(long now) {
				startScreen.movementUpdate();
				startScreen.paintComponents();
			}
		};
		startAnimation.start();

		primaryStage.setOnCloseRequest(new EventHandler<WindowEvent>() {

			@Override
			public void handle(WindowEvent event) {
				ThreadHolder.getInstance().clear();
				startAnimation.stop();
				animation.stop();
			}

		});

		primaryStage.setTitle("TankGame 1 vs 1");
		primaryStage.setResizable(false);
		primaryStage.setScene(startScene);
		primaryStage.sizeToScene();
		primaryStage.show();

	}

	private void loadResource() {
		ImageUtility.loadImages();
		SoundUtility.loadSound();
	}

	public synchronized void changeScene() {
		System.out.println("Change Scene");
		if (this.isGameSceneShown) {
			this.primaryStage.setScene(startScene);
			startAnimation.start();
			System.out.println("To Config Screen");
		} else {
			this.primaryStage.setScene(gameScene);
			startItemProducer();
			animation.start();
			System.out.println("To Game Screen");
		}
		this.isGameSceneShown = !this.isGameSceneShown;
	}

	private void startItemProducer() {
		Thread t = new Thread(new ItemProducer());
		ThreadHolder.getInstance().addThread(t);
		t.start();
	}

	public static Main getInstance() {
		return instance;
	}

	public AnimationTimer getAnimation() {
		return animation;
	}

	public AnimationTimer getStartAnimation() {
		return startAnimation;
	}
}