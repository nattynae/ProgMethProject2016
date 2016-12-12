package Model;

import com.sun.javafx.tk.FontLoader;
import com.sun.javafx.tk.Toolkit;

import Utility.GameUtility;
import Utility.SoundUtility;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;

public class Player extends Entity implements Movable{
	public final static int WIDTH = 30;
	public final static int HEIGHT = 30;
	private String name;
	private int atk, atkspeed;
	private int bulletsLimit;
	private int speed;
	private int direction;
	private Color gunColor, tankColor;
	
	public Player(String name, int x, int y,int direction) {
		super(10, x, y); // 10 is now initial HP
		this.name = name;
		this.direction = direction;
		atk = 1;
		atkspeed = 9;
		bulletsLimit = 1;
		speed = 3;
		this.tankColor = Color.BLUE;
		this.gunColor = Color.RED;
	}
	
	public Player(String name, int x, int y,int direction, Color tankColor, Color gunColor) {
		super(10, x, y); // 10 is now initial HP
		this.name = name;
		this.direction = direction;
		atk = 1;
		atkspeed = 9;
		bulletsLimit = 1;
		speed = 3;
		this.tankColor = tankColor;
		this.gunColor = gunColor;
	}
	
	@Override
	public void draw(GraphicsContext gc, int x, int y) {
		gc.setFill(this.tankColor);
		gc.fillRect(x - WIDTH/2 , y - HEIGHT/2, 30, 30);
		drawGun(gc, x, y);
		drawWheel(gc, x, y);
		drawName(gc,x,y);
	}
	
	private void drawGun(GraphicsContext gc, int x, int y){
		gc.setFill(this.gunColor);
		gc.fillOval(x - 7.5, y - 7.5, 16, 16);
		if(direction == GameUtility.UP){
			gc.fillRect(x - 4 , y - 20, 8, 20);
		}else if(direction == GameUtility.RIGHT){
			gc.fillRect(x, y-4, 20, 8);
		}else if(direction == GameUtility.DOWN){
			gc.fillRect(x - 4, y, 8, 20);
		}else if(direction == GameUtility.LEFT){
			gc.fillRect(x - 20, y - 4 , 20, 8);
		}
	}
	
	private void drawWheel(GraphicsContext gc, int x, int y){
		gc.setLineWidth(1);
		gc.setStroke(Color.AZURE);
		if(direction == GameUtility.UP || direction == GameUtility.DOWN){
			gc.strokeRect(x - WIDTH/2, y- HEIGHT/2, 4, HEIGHT);
			gc.strokeRect(x + WIDTH/2 - 4, y - HEIGHT/2, 4 , HEIGHT);
		}else if(direction == GameUtility.RIGHT || direction == GameUtility.LEFT){
			gc.strokeRect(x - WIDTH/2, y - HEIGHT/2, WIDTH, 4);
			gc.strokeRect(x - WIDTH/2d, y + HEIGHT/2 - 4, WIDTH , 4);
		}
	}
	
	private void drawName(GraphicsContext gc, int x, int y){
		Font font = Font.font("Times New Roman", FontWeight.LIGHT, 14);
		gc.setFont(font);
		FontLoader fontLoader = Toolkit.getToolkit().getFontLoader();
		double font_width = fontLoader.computeStringWidth(name, gc.getFont());
		gc.setFill(Color.YELLOW);
		gc.fillText(name, x - font_width/2, y + WIDTH);
	}
	
	@Override
	public int getZ() {
		return 5; //except Pond has z = 0.
	}
	
	@Override
	public void hit(int dmg) {
		hp -= dmg;
		if (hp < 0) {
			hp = 0;
		}
	}
	
	@Override
	public void move() {
		x += GameUtility.DIR_X[direction] * speed;
		y += GameUtility.DIR_Y[direction] * speed;
	}
	
	@Override
	public void rotate(int dir) {
		direction = dir;
	}
	
	public void attack() { // shoot the bullet
		if (bulletsLimit <= 0) return;
		Bullet bullet = new Bullet(this, x, y);
		IRenderableHolder.getInstance().addEntity(bullet);
		bulletsLimit--;
		SoundUtility.playSound("shoot");
	}
	
	public int getHP() {
		return hp;
	}
	
	public void increaseHP(int addHP) {
		hp += addHP;
		if (hp > 20) hp = 20;
	}
	
	public int getATK() {
		return atk;
	}
	
	public void increaseATK(int addATK) {
		atk += addATK;
		if (atk > 5) atk = 5;
	}
	
	public int getBullets() {
		return bulletsLimit;
	}
	
	public void increaseBullets() {
		bulletsLimit++;
		if (bulletsLimit > 5) bulletsLimit = 5;
	}
	
	public int getATKSpeed() {
		return atkspeed;
	}
	
	public void increaseATKSpedd(int addATKSpeed) {
		atkspeed += addATKSpeed;
		if (atkspeed > 13) atkspeed = 13;
	}
	
	public int getSpeed() {
		return speed;
	}
	
	public void increaseSpeed(int addSpeed) {
		speed += addSpeed;
		if (speed > 8) speed = 8;
	}
	
	public String getName(){
		return name;
	}
	
	public void setDirection(int direction){
		this.direction = direction;
	}
	
	public int getDirection(){
		return direction;
	}
}
