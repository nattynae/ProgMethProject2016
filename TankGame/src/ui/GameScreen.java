package ui;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.List;

import com.sun.javafx.tk.FontLoader;
import com.sun.javafx.tk.Toolkit;

import Logic.GameManager;
import Main.Main;
import Model.ATKItem;
import Model.ATKSpeedItem;
import Model.Bullet;
import Model.BulletItem;
import Model.Entity;
import Model.IRenderable;
import Model.IRenderableHolder;
import Model.Player;
import Model.Pond;
import Model.SpeedItem;
import Model.StrongObstacle;
import Model.WeakObstacle;
import Utility.GameUtility;
import Utility.ImageUtility;
import Utility.InputUtility;
import javafx.application.Platform;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.image.Image;
import javafx.scene.image.WritableImage;
import javafx.scene.input.KeyCode;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;


public class GameScreen extends StackPane{
	
	private Canvas canvas;
	private Image bg;
	private Player player1, player2;
	private static final int frameWidth = 500;
	private static final int frameHeight = 500;
	private int[] currentX, currentY , speed;
	
	public GameScreen(){
		super();
		currentX = new int[2];
		currentY = new int[2];
		speed = new int[2];
		this.setPrefSize(GameUtility.GAMESCREEN_WIDTH, GameUtility.GAMESCREEN_HEIGHT);
		
		// use background from IRenderableHolder
		this.bg = ImageUtility.getGameBackgroundImage();
		// create canvas
		this.canvas = new Canvas(GameUtility.GAMESCREEN_WIDTH, GameUtility.GAMESCREEN_HEIGHT);
		canvas.setVisible(true);			
		
		// add canvas to gameScreen
		this.getChildren().add(canvas);
		
	}
	
	public void update(){
		frameUpdate(player1,0);
		frameUpdate(player2,1);
		
		if(InputUtility.getKeyDown1()){
			player1.setDirection(GameUtility.DOWN);
			player1.move();
		}
		else if(InputUtility.getKeyLeft1()){
			player1.setDirection(GameUtility.LEFT);
			player1.move();
		}
		else if(InputUtility.getKeyRight1()){
			player1.setDirection(GameUtility.RIGHT);
			player1.move();
		}
		else if(InputUtility.getKeyUp1()){
			player1.setDirection(GameUtility.UP);
			player1.move();
		}
		if(InputUtility.getKeyDown2()){
			player2.setDirection(GameUtility.DOWN);
			player2.move();
		}
		else if(InputUtility.getKeyLeft2()){
			player2.setDirection(GameUtility.LEFT);
			player2.move();
		}
		else if(InputUtility.getKeyRight2()){
			player2.setDirection(GameUtility.RIGHT);
			player2.move();
		}
		else if(InputUtility.getKeyUp2()){
			player2.setDirection(GameUtility.UP);
			player2.move();
		}
		if(InputUtility.getKeyShoot1()){
			if(!InputUtility.getTriggeredKeyShoot1()){
				InputUtility.setTriggeredKeyShoot1(true);
				player1.attack();
			}
		}
		if(InputUtility.getKeyShoot2()){
			if(!InputUtility.getTriggeredKeyShoot2()){
				InputUtility.setTriggeredKeyShoot2(true);
				player2.attack();
			}
		}
		
		List<IRenderable> entities = IRenderableHolder.getInstance().getEntities();
		for (int i = entities.size()-1; i>=0; i--) {
			if (entities.get(i) instanceof Bullet) {
				((Bullet) entities.get(i)).move();
			}
		}
		GameManager.checkCollision();
		for (int i = entities.size()-1; i>=0; i--) {
			if (entities.get(i).isDestroyed()) {
				entities.remove(i);
			}
		}
		
		Platform.runLater(()->{
			if (IRenderableHolder.getInstance().getEntities().isEmpty()) return;
			if (player1.isDestroyed() && player2.isDestroyed()) {
				GameManager.endGame("DRAW");
			}
			else if (player1.isDestroyed()) {
				GameManager.endGame(player2.getName() + " WINS");
			}
			else if (player2.isDestroyed()) {
				GameManager.endGame(player1.getName() + " WINS");
			}
		});
	}
	
	public void keyPressed(KeyCode code) {
		// TODO Auto-generated method stub
		if(code.toString().equals("UP")){
			InputUtility.setKeyUp1(true);
		}else if(code.toString().equals("DOWN")){
			InputUtility.setKeyDown1(true);
		}else if(code.toString().equals("RIGHT")){
			InputUtility.setKeyRight1(true);
		}else if(code.toString().equals("LEFT")){
			InputUtility.setKeyLeft1(true);
		}
		if(code.toString().equals("W")){
			InputUtility.setKeyUp2(true);
		}else if(code.toString().equals("S")){
			InputUtility.setKeyDown2(true);
		}else if(code.toString().equals("D")){
			InputUtility.setKeyRight2(true);
		}else if(code.toString().equals("A")){
			InputUtility.setKeyLeft2(true);
		}
		if(code.toString().equals("ENTER")){
			InputUtility.setKeyShoot1(true);
		}
		if(code.toString().equals("SPACE")){
			InputUtility.setKeyShoot2(true);
		}
	}
	
	public void keyReleased(KeyCode code){
		if(code.toString().equals("UP")){
			InputUtility.setKeyUp1(false);
		}else if(code.toString().equals("DOWN")){
			InputUtility.setKeyDown1(false);
		}else if(code.toString().equals("RIGHT")){
			InputUtility.setKeyRight1(false);
		}else if(code.toString().equals("LEFT")){
			InputUtility.setKeyLeft1(false);
		}
		if(code.toString().equals("W")){
			InputUtility.setKeyUp2(false);
		}else if(code.toString().equals("S")){
			InputUtility.setKeyDown2(false);
		}else if(code.toString().equals("D")){
			InputUtility.setKeyRight2(false);
		}else if(code.toString().equals("A")){
			InputUtility.setKeyLeft2(false);
		}
		if(code.toString().equals("ENTER")){
			InputUtility.setKeyShoot1(false);
			InputUtility.setTriggeredKeyShoot1(false);
		}
		if(code.toString().equals("SPACE")){
			InputUtility.setKeyShoot2(false);
			InputUtility.setTriggeredKeyShoot2(false);
		}
	}
	
	public void frameUpdate(Player player,int frameNuber){
		int newX = currentX[frameNuber];
		int newY = currentY[frameNuber];
		newX = player.getX() - GameUtility.BOARD_WIDTH/2;
		newY = player.getY() - GameUtility.BOARD_HEIGHT/2;
		if(newX >= 0 && newX + GameUtility.FRAME_WIDTH <= GameUtility.BOARD_WIDTH){
			currentX[frameNuber] = newX;
		}
		if(newY >= 0 && newY + GameUtility.FRAME_HEIGHT <= GameUtility.BOARD_HEIGHT){
			currentY[frameNuber] = newY;
		}
	}
	
	public void paintComponenet(){
		GraphicsContext gc = this.canvas.getGraphicsContext2D();
		gc.clearRect(0, 0, GameUtility.GAMESCREEN_WIDTH, GameUtility.GAMESCREEN_HEIGHT);
		paintFrame1(gc);
		paintFrame2(gc);
		paintUI(gc);
		paintStatus(gc);
	}
	
	private void paintFrame1(GraphicsContext gc){ //draw frame1 the right frame
		WritableImage shownFrame1 = new WritableImage(bg.getPixelReader(), currentX[0], currentY[0],frameWidth , frameHeight);
		gc.drawImage(shownFrame1, 555, 25);
		for(IRenderable r : IRenderableHolder.getInstance().getEntities()){
			Entity p = (Entity)r;
			
			if(isInFrame(p.getX(), p.getY(), currentX[0], currentY[0])){
				int x = 555 + p.getX() - currentX[0];
				int y = 25 +  p.getY() - currentY[0];
				p.draw(gc, x, y);
			}
		}
	}
	
	private void paintFrame2(GraphicsContext gc){//draw frame2 the left frame
		WritableImage shownFrame2 = new WritableImage(bg.getPixelReader(), currentX[1], currentY[1],frameWidth , frameHeight);
		gc.drawImage(shownFrame2, 20, 25);
		for(IRenderable r : IRenderableHolder.getInstance().getEntities()){
			Entity p = (Entity)r;
			
			if(isInFrame(p.getX(), p.getY(), currentX[1], currentY[1])){
				int x = 20 + p.getX() - currentX[1];
				int y = 25 +  p.getY() - currentY[1];
				p.draw(gc, x, y);
			}
		}
	}
	
	private void paintUI(GraphicsContext gc){
		gc.setFill(Color.CHARTREUSE);
		gc.fillRect(0, 0, 1075, 25);
		gc.fillRect(0, 25, 20, 500);
		gc.fillRect(520, 25, 35, 500);
		gc.fillRect(1055, 25, 20, 500);
		gc.fillRect(0, 525, 1075, 125);
	}
	
	private void paintStatus(GraphicsContext gc){
		// paint white frame
		gc.setGlobalAlpha(0.7);
		gc.setFill(Color.WHITE);
		gc.fillRoundRect(25, 530, 500, 115 , 10, 10);
		gc.fillRoundRect(550, 530, 500, 115 , 10, 10);
		gc.setGlobalAlpha(1);
		
		// paint name
		Font font = Font.font("Times New Roman", FontWeight.LIGHT, 20);
		gc.setFont(font);
		gc.setFill(Color.BLACK);
		gc.fillText(player2.getName(), 30, 550);
		gc.fillText(player1.getName(), 555, 550);
		
		//paint status
		Font little_font = Font.font("Times New Roman", FontWeight.LIGHT, 15);
		gc.setFont(little_font);
		FontLoader fontLoader = Toolkit.getToolkit().getFontLoader();
		double font_height = fontLoader.getFontMetrics(gc.getFont()).getLineHeight();
		
		
		// paint status bar
		
		gc.setStroke(Color.BLACK);
		gc.setFill(Color.CHARTREUSE);
		gc.fillRoundRect(80, 570-font_height+3, player2.getHP()*7, font_height, 10, 10);
		gc.strokeRoundRect(80, 570-font_height+3, 140, font_height, 10, 10);
		gc.fillRoundRect(605, 570-font_height+3, player1.getHP()*7, font_height, 10, 10);
		gc.strokeRoundRect(605, 570-font_height+3, 140, font_height, 10, 10);
		
		gc.setFill(Color.YELLOW);
		gc.fillRoundRect(80, 595-font_height+3, player2.getSpeed()*140/8, font_height, 10, 10);
		gc.strokeRoundRect(80, 595-font_height+3, 140, font_height, 10, 10);
		gc.fillRoundRect(605, 595-font_height+3, player1.getSpeed()*140/8, font_height, 10, 10);
		gc.strokeRoundRect(605, 595-font_height+3, 140, font_height, 10, 10);
		
		gc.setFill(Color.RED);
		gc.fillRoundRect(80, 620-font_height+3, player2.getATK()*140/5, font_height, 10, 10);
		gc.strokeRoundRect(80, 620-font_height+3, 140, font_height, 10, 10);
		gc.fillRoundRect(605, 620-font_height+3, player1.getATK()*140/5, font_height, 10, 10);
		gc.strokeRoundRect(605, 620-font_height+3, 140, font_height, 10, 10);
		
		gc.setFill(Color.ORANGE);
		gc.fillRoundRect(305, 570-font_height+3, player2.getATKSpeed()*140/13, font_height, 10, 10);
		gc.strokeRoundRect(305, 570-font_height+3, 140, font_height, 10, 10);
		gc.fillRoundRect(830, 570-font_height+3, player1.getATKSpeed()*140/13, font_height, 10, 10);
		gc.strokeRoundRect(830, 570-font_height+3, 140, font_height, 10, 10);
		
		gc.setFill(Color.GREEN);
		gc.fillRoundRect(305, 595-font_height+3, player2.getBullets()*140/5, font_height, 10, 10);
		gc.strokeRoundRect(305, 595-font_height+3, 140, font_height, 10, 10);
		gc.fillRoundRect(830, 595-font_height+3, player1.getBullets()*140/5, font_height, 10, 10);
		gc.strokeRoundRect(830, 595-font_height+3, 140, font_height, 10, 10);
		
		// paint status font
		gc.setFill(Color.BLACK);
		gc.fillText("HP: "+player2.getHP()+"/20", 125, 570);
		gc.fillText("Speed: "+player2.getSpeed()+"/8", 125, 595);
		gc.fillText("ATK: "+player2.getATK()+"/5", 125, 620);
		gc.fillText("ATKSpeed: "+player2.getATKSpeed()+"/13", 325, 570);
		gc.fillText("Bullet: "+player2.getBullets()+"/5", 325, 595);
		
		gc.fillText("HP: "+player1.getHP()+"/20", 650, 570);
		gc.fillText("Speed: "+player1.getSpeed()+"/8", 650, 595);
		gc.fillText("ATK: "+player1.getATK()+"/5", 650, 620);
		gc.fillText("ATKSpeed: "+player1.getATKSpeed()+"/13", 850, 570);
		gc.fillText("Bullet: "+player1.getBullets()+"/5", 850, 595);
	}	
	
	public void findPlayer(){ //use to find player and capture in the frame
		int i = 0;
		for(IRenderable r : IRenderableHolder.getInstance().getEntities()){
			
			if(r instanceof Player){
				Player p = (Player) r;
				currentX[i] = p.getX()- frameWidth/2;
				currentY[i] = p.getY()- frameHeight/2;
				speed[i] = p.getSpeed();
				
				if(currentX[i] < 0) currentX[i] = 0;
				else if(currentX[i] + GameUtility.FRAME_WIDTH > GameUtility.BOARD_WIDTH) currentX[i] = GameUtility.BOARD_WIDTH - GameUtility.FRAME_WIDTH;
				if(currentY[i] < 0) currentY[i] = 0;
				else if(currentY[i] + GameUtility.FRAME_HEIGHT > GameUtility.BOARD_HEIGHT ) currentY[i] = GameUtility.BOARD_HEIGHT - GameUtility.FRAME_HEIGHT;
				
				if(i == 0){
					player1 = p;
				}else if(i == 1){
					player2 = p;
				}
				i++;
			}
		}
	}
	
	public boolean isInFrame(int x, int y, int currentX, int currentY){
		if(x - currentX + 12 < 0 || x - currentX - 12 > frameWidth) return false;
		else if(y - currentY + 12 < 0 || y - currentY - 12 > frameHeight) return false;
		return true;
	}

}