package Model;

import com.sun.javafx.tk.FontLoader;
import com.sun.javafx.tk.Toolkit;

import Utility.GameUtility;
import Utility.SoundUtility;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;

public class Player extends Entity implements Movable {
	public static final int WIDTH = 30;
	public static final int HEIGHT = 30;

	private static final int DEFAULT_HP = 10;
	private static final int DEFAULT_ATK = 1;
	private static final int DEFAULT_ATKSPEED = 9;
	private static final int DEFAULT_BULLETS = 1;
	private static final int DEFAULT_SPEED = 3;
	private static final Color DEFAULT_TANK_COLOR = Color.BLUE;
	private static final Color DEFAULT_GUN_COLOR = Color.RED;
	private static final int GUN_RADIUS = 8;
	private static final int GUN_WIDTH = 8;
	private static final int GUN_HEIGHT = 20;
	private static final int WHEEL_SIZE = 4;

	private String name;
	private int atk, atkspeed;
	private int bulletsLimit;
	private int speed;
	private int direction;
	private Color gunColor, tankColor;

	public Player(String name, int x, int y, int direction) {
		super(DEFAULT_HP, x, y);
		this.name = name;
		this.direction = direction;
		atk = DEFAULT_ATK;
		atkspeed = DEFAULT_ATKSPEED;
		bulletsLimit = DEFAULT_BULLETS;
		speed = DEFAULT_SPEED;
		this.tankColor = DEFAULT_TANK_COLOR;
		this.gunColor = DEFAULT_GUN_COLOR;
	}

	// customize tank & gun color
	public Player(String name, int x, int y, int direction, Color tankColor, Color gunColor) {
		super(DEFAULT_HP, x, y);
		this.name = name;
		this.direction = direction;
		atk = DEFAULT_ATK;
		atkspeed = DEFAULT_ATKSPEED;
		bulletsLimit = DEFAULT_BULLETS;
		speed = DEFAULT_SPEED;
		this.tankColor = tankColor;
		this.gunColor = gunColor;
	}

	@Override
	public void draw(GraphicsContext gc, int x, int y) {
		gc.setFill(this.tankColor);
		gc.fillRect(x - WIDTH / 2, y - HEIGHT / 2, WIDTH, HEIGHT);
		drawGun(gc, x, y);
		drawWheel(gc, x, y);
		drawName(gc, x, y);
	}

	private void drawGun(GraphicsContext gc, int x, int y) {
		gc.setFill(this.gunColor);
		gc.fillOval(x - GUN_RADIUS, y - GUN_RADIUS, 2 * GUN_RADIUS, 2 * GUN_RADIUS);
		if (direction == GameUtility.UP) {
			gc.fillRect(x - GUN_WIDTH / 2, y - GUN_HEIGHT, GUN_WIDTH, GUN_HEIGHT);
		} else if (direction == GameUtility.RIGHT) {
			gc.fillRect(x, y - GUN_WIDTH / 2, GUN_HEIGHT, GUN_WIDTH);
		} else if (direction == GameUtility.DOWN) {
			gc.fillRect(x - GUN_WIDTH / 2, y, GUN_WIDTH, GUN_HEIGHT);
		} else if (direction == GameUtility.LEFT) {
			gc.fillRect(x - GUN_HEIGHT, y - GUN_WIDTH / 2, GUN_HEIGHT, GUN_WIDTH);
		}
	}

	private void drawWheel(GraphicsContext gc, int x, int y) {
		gc.setLineWidth(1);
		gc.setStroke(Color.AZURE);
		if (direction == GameUtility.UP || direction == GameUtility.DOWN) {
			gc.strokeRect(x - WIDTH / 2, y - HEIGHT / 2, WHEEL_SIZE, HEIGHT);
			gc.strokeRect(x + WIDTH / 2 - WHEEL_SIZE, y - HEIGHT / 2, WHEEL_SIZE, HEIGHT);
		} else if (direction == GameUtility.RIGHT || direction == GameUtility.LEFT) {
			gc.strokeRect(x - WIDTH / 2, y - HEIGHT / 2, WIDTH, WHEEL_SIZE);
			gc.strokeRect(x - WIDTH / 2, y + HEIGHT / 2 - WHEEL_SIZE, WIDTH, WHEEL_SIZE);
		}
	}

	private void drawName(GraphicsContext gc, int x, int y) {
		Font font = Font.font("Times New Roman", FontWeight.LIGHT, 14);
		gc.setFont(font);
		FontLoader fontLoader = Toolkit.getToolkit().getFontLoader();
		double font_width = fontLoader.computeStringWidth(name, gc.getFont());
		gc.setFill(Color.BLACK);
		gc.fillText(name, x - font_width / 2, y + WIDTH);
	}

	@Override
	public int getZ() {
		return 5;
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

	public void attack() { // shoot the bullet
		if (bulletsLimit <= 0)
			return;
		Bullet bullet = new Bullet(this, x, y);
		IRenderableHolder.getInstance().addEntity(bullet);
		bulletsLimit--;
		SoundUtility.playShootingSound();
		;
	}

	public int getHP() {
		return hp;
	}

	public void increaseHP(int addHP) {
		hp += addHP;
		if (hp > 20)
			hp = 20;
	}

	public int getATK() {
		return atk;
	}

	public void increaseATK(int addATK) {
		atk += addATK;
		if (atk > 5)
			atk = 5;
	}

	public int getBullets() {
		return bulletsLimit;
	}

	public void increaseBullets() {
		bulletsLimit++;
		if (bulletsLimit > 5)
			bulletsLimit = 5;
	}

	public int getATKSpeed() {
		return atkspeed;
	}

	public void increaseATKSpeed(int addATKSpeed) {
		atkspeed += addATKSpeed;
		if (atkspeed > 13)
			atkspeed = 13;
	}

	public int getSpeed() {
		return speed;
	}

	public void increaseSpeed(int addSpeed) {
		speed += addSpeed;
		if (speed > 8)
			speed = 8;
	}

	public String getName() {
		return name;
	}

	public int getDirection() {
		return direction;
	}

	public void setDirection(int direction) {
		this.direction = direction;
	}

}
