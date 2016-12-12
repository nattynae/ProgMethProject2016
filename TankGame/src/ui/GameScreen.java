package ui;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.List;

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
	private static int frameWidth = 500;
	private static int frameHeight = 500;
	public static int maxWidth = 1920 ; 
	public static int maxHeight = 1160;
	private int[] currentX, currentY , speed;
	
	public GameScreen(){
		super();
		currentX = new int[2];
		currentY = new int[2];
		speed = new int[2];
		this.setPrefSize(GameUtility.GAMESCREEN_WIDTH, GameUtility.GAMESCREEN_HEIGHT);
		
		// use background from IRenderableHolder
		this.bg = IRenderableHolder.bg;
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
		newX = player.getX() - 250;
		newY = player.getY() - 250;
		if(newX >= 0 && newX + frameWidth <= maxWidth){
			currentX[frameNuber] = newX;
		}
		if(newY >= 0 && newY + frameHeight <= maxHeight){
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
		gc.drawImage(shownFrame1, 550, 25);
		for(IRenderable r : IRenderableHolder.getInstance().getEntities()){
			Entity p = (Entity)r;
			
			if(isInFrame(p.getX(), p.getY(), currentX[0], currentY[0])){
				int x = 550 + p.getX() - currentX[0];
				int y = 25 +  p.getY() - currentY[0];
				p.draw(gc, x, y);
			}
		}
	}
	
	private void paintFrame2(GraphicsContext gc){//draw frame2 the left frame
		WritableImage shownFrame2 = new WritableImage(bg.getPixelReader(), currentX[1], currentY[1],frameWidth , frameHeight);
		gc.drawImage(shownFrame2, 25, 25);
		for(IRenderable r : IRenderableHolder.getInstance().getEntities()){
			if (r instanceof Entity) {
				Entity p = (Entity)r;
				
				if(isInFrame(p.getX(), p.getY(), currentX[1], currentY[1])){
					int x = 25 + p.getX() - currentX[1];
					int y = 25 +  p.getY() - currentY[1];
					p.draw(gc, x, y);
				}
			}
		}
	}
	
	private void paintUI(GraphicsContext gc){
		gc.setFill(Color.CHARTREUSE);
		gc.fillRect(0, 0, 1075, 25);
		gc.fillRect(0, 25, 25, 500);
		gc.fillRect(525, 25, 25, 500);
		gc.fillRect(1050, 25, 25, 500);
		gc.fillRect(0, 525, 1075, 125);
	}
	
	private void paintStatus(GraphicsContext gc){
		gc.setGlobalAlpha(0.7);
		gc.setFill(Color.WHITE);
		gc.fillRoundRect(25, 530, 500, 115 , 10, 10);
		gc.fillRoundRect(550, 530, 500, 115 , 10, 10);
		Font font = Font.font("Times New Roman", FontWeight.LIGHT, 20);
		gc.setFont(font);
		gc.setFill(Color.BLACK);
		gc.fillText(player2.getName(), 30, 550);
		gc.fillText(player1.getName(), 555, 550);
		Font little_font = Font.font("Times New Roman", FontWeight.LIGHT, 15);
		gc.setFont(little_font);
		gc.fillText("HP: "+player2.getHP()+"/20", 30, 580);
		gc.fillText("Speed: "+player2.getSpeed()+"/8", 30, 600);
		gc.fillText("ATK: "+player2.getATK()+"/5", 30, 620);
		gc.fillText("ATKSpeed: "+player2.getATKSpeed()+"/13", 280, 580);
		gc.fillText("Bullet: "+player2.getBullets()+"/5", 280, 600);
		
		gc.fillText("HP: "+player1.getHP()+"/20", 555, 580);
		gc.fillText("Speed: "+player1.getSpeed()+"/8", 555, 600);
		gc.fillText("ATK: "+player1.getATK()+"/5", 555, 620);
		gc.fillText("ATKSpeed: "+player1.getATKSpeed()+"/13", 805, 580);
		gc.fillText("Bullet: "+player1.getBullets()+"/5", 805, 600);
		gc.setGlobalAlpha(1);
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
				else if(currentX[i] + frameWidth > maxWidth) currentX[i] = maxWidth - frameWidth;
				if(currentY[i] < 0) currentY[i] = 0;
				else if(currentY[i] + frameHeight > maxHeight ) currentY[i] = maxHeight - frameHeight;
				
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
		if(x - currentX + 20 < 0 || x - currentX - 20 > frameWidth) return false;
		else if(y - currentY +20 < 0 || y - currentY -20 > frameHeight) return false;
		return true;
	}

	
	
	
}