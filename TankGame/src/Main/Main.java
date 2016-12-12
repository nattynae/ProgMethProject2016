package Main;

import java.util.ArrayList;
import java.util.List;

import Logic.GameManager;
import Logic.ItemProducer;
import Logic.ThreadsHolder;
import Utility.GameUtility;
import Utility.RandomUtility;
import javafx.animation.AnimationTimer;
import javafx.application.Application;
import javafx.application.Platform;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.image.Image;
import javafx.scene.image.WritableImage;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.GridPane;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import javafx.stage.WindowEvent;
import ui.GameScreen;
import ui.StartScreen;

public class Main extends Application{
	public static Main instance;
	private Stage primaryStage;
	private Scene startScene;
	private Scene gameScene;
	
	private StartScreen startScreen;
	private GameScreen gameScreen ;
	public AnimationTimer animation, startAnimation;
	private static long tm = 0;
	
	private boolean isGameSceneShown = false;
	
	public static void main(String args[]) {
		launch(args);
	}
	
	@Override
	public void start(Stage primaryStage) throws Exception{
		// TODO: ...
		instance = this;
		RandomUtility.init();
		this.primaryStage = primaryStage;
		gameScreen = new GameScreen();
		
		gameScene = new Scene(gameScreen,GameUtility.GAMESCREEN_WIDTH, GameUtility.GAMESCREEN_HEIGHT);
		startScreen = new StartScreen(gameScreen);
		startScene = new Scene(startScreen,GameUtility.GAMESCREEN_WIDTH, GameUtility.GAMESCREEN_HEIGHT);
		

		gameScene.setOnKeyPressed((KeyEvent e) ->{
			System.out.println(e.getCode().toString());
			gameScreen.keyPressed(e.getCode());
		});
		
		
		gameScene.setOnKeyReleased((KeyEvent e) ->{
			System.out.println("           "+e.getCode().toString());
			gameScreen.keyReleased(e.getCode());
		});
		
		animation = new AnimationTimer() {
			
			@Override
			public void handle(long now) {
				// TODO Auto-generated method stub
				gameScreen.update();
				gameScreen.paintComponenet();
				List<Thread> threads = ThreadsHolder.instance.getThreads();
				for (int i=threads.size()-1; i>=0; i--) {
					if (!threads.get(i).isAlive()) {
						threads.remove(i);
					}
				}
				tm++;
				if(tm%500==0) {
					ItemProducer.produce();
				}
			}
		};
		
		startAnimation = new AnimationTimer() {
			
			@Override
			public void handle(long now) {
				// TODO Auto-generated method stub
				startScreen.movementUpdate();
				startScreen.paintComponents();
				System.out.println("Start Screen is running");
			}
		};
		startAnimation.start();
		
		primaryStage.setOnCloseRequest(new EventHandler<WindowEvent>() {
			
			@Override
			public void handle(WindowEvent event) {
				// TODO Auto-generated method stub
				for (Thread t: ThreadsHolder.instance.getThreads()) {
					t.interrupt();
				}
				ThreadsHolder.instance.getThreads().clear();
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
	
	public synchronized void ChangeScene(){
		System.out.println("Change Scene");
		if (this.isGameSceneShown){
			this.primaryStage.setScene(startScene);
			startAnimation.start();
			System.out.println("To Config Screen");
		}
		else{
			this.primaryStage.setScene(gameScene);
			animation.start();
			//ItemProducer ip = new ItemProducer();
			//ThreadsHolder.instance.addThread(ip);
			//ip.start();
			System.out.println("To Game Screen");
		}
		this.isGameSceneShown = !this.isGameSceneShown;
	} 
	
}
