/**
* @author Pornkrit Watcharasatianphan and Phanu Vajanopath
* @version 14 Dec 2016
* Project (1/2016) in 2110215 Prog Meth
*/
package ui;

import java.util.List;

import com.sun.javafx.tk.FontLoader;
import com.sun.javafx.tk.Toolkit;

import game.GameManager;
import javafx.application.Platform;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import javafx.scene.image.WritableImage;
import javafx.scene.input.KeyCode;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import model.Bullet;
import model.Entity;
import model.IRenderable;
import model.IRenderableHolder;
import model.Player;
import utility.GameUtility;
import utility.ImageUtility;
import utility.InputUtility;

public class GameScreen extends StackPane {

	private Canvas canvas;
	private Image bg;
	private Player player1, player2;
	private static int frameWidth = GameUtility.FRAME_WIDTH;
	private static int frameHeight = GameUtility.FRAME_HEIGHT;
	private static int maxWidth = GameUtility.BOARD_WIDTH;
	private static int maxHeight = GameUtility.BOARD_HEIGHT;
	public static int arcWidth = 10;
	public static int arcHeight = 10;
	private static double alpha = 0.7;
	private static int smallVGap = 5;
	private static int largeVGap = 25;
	private static int centerHgap = 35;
	private static int statusFrameHeight = 115;
	private static int statusNameX = 30;
	private static int statusNameY = 550;
	private static int firstBarX2 = 80;
	private static int firstBarX1 = 615;
	private static int secondBarX2 = 305;
	private static int secondBarX1 = 840;
	private static int hpBarY = 570;
	private static int statusLabelGap = 25;
	private static int statusBarWidth = 140;
	private static int startDrawingX1 = 555;
	private static int startDrawingX2 = 20;
	private static int startDrawingY = 25;
	private static int frameExceed = 15;
	private static int maxHP = 20;
	private static int maxSpeed = 8;
	private static int maxATK = 5;
	private static int maxATKSpeed = 13;
	private static int maxBullet = 5;
	private static Font font = Font.font("Times New Roman", FontWeight.LIGHT, 20);
	private static Font font_little = Font.font("Times New Roman", FontWeight.LIGHT, 15);
	private int[] currentX, currentY, speed; // for rendering each frame

	public GameScreen() {
		super();
		currentX = new int[2];
		currentY = new int[2];
		speed = new int[2];
		this.setPrefSize(GameUtility.GAMESCREEN_WIDTH, GameUtility.GAMESCREEN_HEIGHT);

		this.bg = ImageUtility.getGameBackgroundImage();

		// create canvas
		this.canvas = new Canvas(GameUtility.GAMESCREEN_WIDTH, GameUtility.GAMESCREEN_HEIGHT);
		canvas.setVisible(true);

		// add canvas to gameScreen
		this.getChildren().add(canvas);

	}

	public void update() {
		updateFrame(player1, 0);
		updateFrame(player2, 1);

		updatePlayerFromInput();
		updateEntities();

		checkGameEnd();
	}

	private void updatePlayerFromInput() {
		if (InputUtility.getKeyDown1()) {
			player1.setDirection(GameUtility.DOWN);
			player1.move();
		} else if (InputUtility.getKeyLeft1()) {
			player1.setDirection(GameUtility.LEFT);
			player1.move();
		} else if (InputUtility.getKeyRight1()) {
			player1.setDirection(GameUtility.RIGHT);
			player1.move();
		} else if (InputUtility.getKeyUp1()) {
			player1.setDirection(GameUtility.UP);
			player1.move();
		}
		if (InputUtility.getKeyDown2()) {
			player2.setDirection(GameUtility.DOWN);
			player2.move();
		} else if (InputUtility.getKeyLeft2()) {
			player2.setDirection(GameUtility.LEFT);
			player2.move();
		} else if (InputUtility.getKeyRight2()) {
			player2.setDirection(GameUtility.RIGHT);
			player2.move();
		} else if (InputUtility.getKeyUp2()) {
			player2.setDirection(GameUtility.UP);
			player2.move();
		}
		if (InputUtility.getKeyShoot1()) {
			if (!InputUtility.getTriggeredKeyShoot1()) {
				InputUtility.setTriggeredKeyShoot1(true);
				player1.attack();
			}
		}
		if (InputUtility.getKeyShoot2()) {
			if (!InputUtility.getTriggeredKeyShoot2()) {
				InputUtility.setTriggeredKeyShoot2(true);
				player2.attack();
			}
		}
	}

	private void updateEntities() {
		List<IRenderable> entities = IRenderableHolder.getInstance().getEntities();
		// Move all bullets. Don't care isDestroyed here.
		for (int i = entities.size() - 1; i >= 0; i--) {
			if (entities.get(i) instanceof Bullet) {
				((Bullet) entities.get(i)).move();
			}
		}

		// check all collisions and remove destroyed one from the list
		GameManager.checkCollision();
		for (int i = entities.size() - 1; i >= 0; i--) {
			if (entities.get(i).isDestroyed()) {
				entities.remove(i);
			}
		}
	}

	private void checkGameEnd() {
		Platform.runLater(() -> {
			// check screen status (when list is empty means it is start screen now)
			if (IRenderableHolder.getInstance().getEntities().isEmpty())
				return;

			// end game and announce the winner
			if (player1.isDestroyed() && player2.isDestroyed()) {
				GameUtility.setWinner("");
				GameManager.endGame(GameUtility.getWinner() + "DRAW");
			} else if (player1.isDestroyed()) {
				GameUtility.setWinner(player2.toString());
				GameManager.endGame(GameUtility.getWinner() + " WINS");
			} else if (player2.isDestroyed()) {
				GameUtility.setWinner(player1.toString());
				GameManager.endGame(GameUtility.getWinner() + " WINS");
			}
		});
	}

	public void keyPressed(KeyCode code) {
		if (code.toString().equals("UP")) {
			InputUtility.setKeyUp1(true);
		} else if (code.toString().equals("DOWN")) {
			InputUtility.setKeyDown1(true);
		} else if (code.toString().equals("RIGHT")) {
			InputUtility.setKeyRight1(true);
		} else if (code.toString().equals("LEFT")) {
			InputUtility.setKeyLeft1(true);
		}
		if (code.toString().equals("W")) {
			InputUtility.setKeyUp2(true);
		} else if (code.toString().equals("S")) {
			InputUtility.setKeyDown2(true);
		} else if (code.toString().equals("D")) {
			InputUtility.setKeyRight2(true);
		} else if (code.toString().equals("A")) {
			InputUtility.setKeyLeft2(true);
		}
		if (code.toString().equals("ENTER")) {
			InputUtility.setKeyShoot1(true);
		}
		if (code.toString().equals("SPACE")) {
			InputUtility.setKeyShoot2(true);
		}
	}

	public void keyReleased(KeyCode code) {
		if (code.toString().equals("UP")) {
			InputUtility.setKeyUp1(false);
		} else if (code.toString().equals("DOWN")) {
			InputUtility.setKeyDown1(false);
		} else if (code.toString().equals("RIGHT")) {
			InputUtility.setKeyRight1(false);
		} else if (code.toString().equals("LEFT")) {
			InputUtility.setKeyLeft1(false);
		}
		if (code.toString().equals("W")) {
			InputUtility.setKeyUp2(false);
		} else if (code.toString().equals("S")) {
			InputUtility.setKeyDown2(false);
		} else if (code.toString().equals("D")) {
			InputUtility.setKeyRight2(false);
		} else if (code.toString().equals("A")) {
			InputUtility.setKeyLeft2(false);
		}
		if (code.toString().equals("ENTER")) {
			InputUtility.setKeyShoot1(false);
			InputUtility.setTriggeredKeyShoot1(false);
		}
		if (code.toString().equals("SPACE")) {
			InputUtility.setKeyShoot2(false);
			InputUtility.setTriggeredKeyShoot2(false);
		}
	}

	public void updateFrame(Player player, int frameNuber) {
		int newX = currentX[frameNuber];
		int newY = currentY[frameNuber];
		newX = player.getX() - frameWidth / 2;
		newY = player.getY() - frameHeight / 2;
		if (newX >= 0 && newX + frameWidth <= maxWidth) {
			currentX[frameNuber] = newX;
		}
		if (newY >= 0 && newY + frameHeight <= maxHeight) {
			currentY[frameNuber] = newY;
		}
	}

	public void paintComponenet() {
		GraphicsContext gc = this.canvas.getGraphicsContext2D();
		gc.clearRect(0, 0, GameUtility.GAMESCREEN_WIDTH, GameUtility.GAMESCREEN_HEIGHT);
		paintFrame1(gc);
		paintFrame2(gc);
		paintUI(gc);
		paintStatus(gc);
	}

	// draw frame1 the right frame
	private void paintFrame1(GraphicsContext gc) {
		WritableImage shownFrame1 = new WritableImage(bg.getPixelReader(), currentX[0], currentY[0], frameWidth,
				frameHeight);
		gc.drawImage(shownFrame1, 555, 25);
		for (IRenderable r : IRenderableHolder.getInstance().getEntities()) {
			Entity p = (Entity) r;

			if (isInFrame(p.getX(), p.getY(), currentX[0], currentY[0])) {
				int x = 555 + p.getX() - currentX[0];
				int y = 25 + p.getY() - currentY[0];
				p.draw(gc, x, y);
			}
		}
	}

	// draw frame2 the left frame
	private void paintFrame2(GraphicsContext gc) {
		WritableImage shownFrame2 = new WritableImage(bg.getPixelReader(), currentX[1], currentY[1], frameWidth,
				frameHeight);
		gc.drawImage(shownFrame2, 20, 25);
		for (IRenderable r : IRenderableHolder.getInstance().getEntities()) {
			Entity p = (Entity) r;

			if (isInFrame(p.getX(), p.getY(), currentX[1], currentY[1])) {
				int x = 20 + p.getX() - currentX[1];
				int y = 25 + p.getY() - currentY[1];
				p.draw(gc, x, y);
			}
		}
	}

	private void paintUI(GraphicsContext gc) {
		gc.drawImage(ImageUtility.getGameScreenBackgroundImage(), 0, 0);
	}

	private void paintStatus(GraphicsContext gc) {
		// paint white frame
		gc.setGlobalAlpha(alpha);
		gc.setFill(Color.WHITE);
		gc.fillRoundRect(startDrawingX2, startDrawingY + frameHeight + smallVGap, frameWidth, statusFrameHeight,
				arcWidth, arcHeight);
		gc.fillRoundRect(startDrawingX1, startDrawingY + frameHeight + smallVGap, frameWidth, statusFrameHeight,
				arcWidth, arcHeight);
		gc.setGlobalAlpha(1);

		// paint name
		gc.setFont(font);
		gc.setFill(Color.BLACK);
		gc.fillText(player2.toString(), statusNameX, statusNameY);
		gc.fillText(player1.toString(), statusNameX + frameWidth + centerHgap, statusNameY);

		// paint status
		gc.setFont(font_little);
		FontLoader fontLoader = Toolkit.getToolkit().getFontLoader();
		double font_height = fontLoader.getFontMetrics(gc.getFont()).getLineHeight();
		double adjustedHeight = font_height - 3;

		// paint status bar
		gc.setStroke(Color.BLACK);
		gc.setFill(Color.CHARTREUSE);
		// paint HP bar
		gc.fillRoundRect(firstBarX2, hpBarY - adjustedHeight, player2.getHP() * statusBarWidth / 20, font_height,
				arcWidth, arcHeight);
		gc.strokeRoundRect(firstBarX2, hpBarY - adjustedHeight, statusBarWidth, font_height, arcWidth, arcHeight);
		gc.fillRoundRect(firstBarX1, hpBarY - adjustedHeight, player1.getHP() * statusBarWidth / 20, font_height,
				arcWidth, arcHeight);
		gc.strokeRoundRect(firstBarX1, hpBarY - adjustedHeight, statusBarWidth, font_height, arcWidth, arcHeight);
		// paint Speed bar
		gc.setFill(Color.YELLOW);
		gc.fillRoundRect(firstBarX2, hpBarY + largeVGap - adjustedHeight, player2.getSpeed() * statusBarWidth / 8,
				font_height, arcWidth, arcHeight);
		gc.strokeRoundRect(firstBarX2, hpBarY + largeVGap - adjustedHeight, statusBarWidth, font_height, arcWidth,
				arcHeight);
		gc.fillRoundRect(firstBarX1, hpBarY + largeVGap - adjustedHeight, player1.getSpeed() * statusBarWidth / 8,
				font_height, arcWidth, arcHeight);
		gc.strokeRoundRect(firstBarX1, hpBarY + largeVGap - adjustedHeight, statusBarWidth, font_height, arcWidth,
				arcHeight);
		// paint ATK bar
		gc.setFill(Color.RED);
		gc.fillRoundRect(firstBarX2, hpBarY + 2 * largeVGap - adjustedHeight, player2.getATK() * statusBarWidth / 5,
				font_height, arcWidth, arcHeight);
		gc.strokeRoundRect(firstBarX2, hpBarY + 2 * largeVGap - adjustedHeight, statusBarWidth, font_height, arcWidth,
				arcHeight);
		gc.fillRoundRect(firstBarX1, hpBarY + 2 * largeVGap - adjustedHeight, player1.getATK() * statusBarWidth / 5,
				font_height, arcWidth, arcHeight);
		gc.strokeRoundRect(firstBarX1, hpBarY + 2 * largeVGap - adjustedHeight, statusBarWidth, font_height, arcWidth,
				arcHeight);
		// paint ATKSpeed bar
		gc.setFill(Color.ORANGE);
		gc.fillRoundRect(secondBarX2, hpBarY - adjustedHeight, player2.getATKSpeed() * statusBarWidth / 13, font_height,
				arcWidth, arcHeight);
		gc.strokeRoundRect(secondBarX2, hpBarY - adjustedHeight, statusBarWidth, font_height, arcWidth, arcHeight);
		gc.fillRoundRect(secondBarX1, hpBarY - adjustedHeight, player1.getATKSpeed() * statusBarWidth / 13, font_height,
				arcWidth, arcHeight);
		gc.strokeRoundRect(secondBarX1, hpBarY - adjustedHeight, statusBarWidth, font_height, arcWidth, arcHeight);
		// paint bullet bar
		gc.setFill(Color.GREEN);
		gc.fillRoundRect(secondBarX2, hpBarY + largeVGap - adjustedHeight, player2.getBullets() * statusBarWidth / 5,
				font_height, arcWidth, arcHeight);
		gc.strokeRoundRect(secondBarX2, hpBarY + largeVGap - adjustedHeight, statusBarWidth, font_height, arcWidth,
				arcHeight);
		gc.fillRoundRect(secondBarX1, hpBarY + largeVGap - adjustedHeight, player1.getBullets() * statusBarWidth / 5,
				font_height, arcWidth, arcHeight);
		gc.strokeRoundRect(secondBarX1, hpBarY + largeVGap - adjustedHeight, statusBarWidth, font_height, arcWidth,
				arcHeight);

		// paint status font
		gc.setFill(Color.BLACK);
		gc.fillText("HP: " + player2.getHP() + "/" + maxHP, firstBarX2 + statusLabelGap, hpBarY);
		gc.fillText("Speed: " + player2.getSpeed() + "/" + maxSpeed, firstBarX2 + statusLabelGap, hpBarY + largeVGap);
		gc.fillText("ATK: " + player2.getATK() + "/" + maxATK, firstBarX2 + statusLabelGap, hpBarY + 2 * largeVGap);
		gc.fillText("ATKSpeed: " + player2.getATKSpeed() + "/" + maxATKSpeed, secondBarX2 + statusLabelGap, hpBarY);
		gc.fillText("Bullet: " + player2.getBullets() + "/" + maxBullet, secondBarX2 + statusLabelGap,
				hpBarY + largeVGap);

		gc.fillText("HP: " + player1.getHP() + "/" + maxHP, firstBarX1 + statusLabelGap, hpBarY);
		gc.fillText("Speed: " + player1.getSpeed() + "/" + maxSpeed, firstBarX1 + statusLabelGap, hpBarY + largeVGap);
		gc.fillText("ATK: " + player1.getATK() + "/" + maxATK, firstBarX1 + statusLabelGap, hpBarY + 2 * largeVGap);
		gc.fillText("ATKSpeed: " + player1.getATKSpeed() + "/" + maxATKSpeed, secondBarX1 + statusLabelGap, hpBarY);
		gc.fillText("Bullet: " + player1.getBullets() + "/" + maxBullet, secondBarX1 + statusLabelGap,
				hpBarY + largeVGap);
	}

	public void findPlayer() { // use to find player and capture in the frame
		int i = 0;
		for (IRenderable r : IRenderableHolder.getInstance().getEntities()) {
			if (r instanceof Player) {
				Player p = (Player) r;
				currentX[i] = p.getX() - frameWidth / 2;
				currentY[i] = p.getY() - frameHeight / 2;
				speed[i] = p.getSpeed();

				if (currentX[i] < 0)
					currentX[i] = 0;
				else if (currentX[i] + frameWidth > maxWidth)
					currentX[i] = maxWidth - frameWidth;
				if (currentY[i] < 0)
					currentY[i] = 0;
				else if (currentY[i] + frameHeight > maxHeight)
					currentY[i] = maxHeight - frameHeight;

				if (i == 0) {
					player1 = p;
				} else if (i == 1) {
					player2 = p;
				}
				i++;
			}
		}
	}

	public boolean isInFrame(int x, int y, int currentX, int currentY) {
		if (x - currentX + frameExceed < 0 || x - currentX - frameExceed > frameWidth)
			return false;
		else if (y - currentY + frameExceed < 0 || y - currentY - frameExceed > frameHeight)
			return false;
		return true;
	}

}