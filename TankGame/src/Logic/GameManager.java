package Logic;

import java.util.List;

import Main.Main;
import Model.Bullet;
import Model.BulletPassable;
import Model.Entity;
import Model.IRenderable;
import Model.IRenderableHolder;
import Model.Item;
import Model.Player;
import Utility.GameUtility;
import Utility.Geometry;
import Utility.Rectangle;
import javafx.event.EventHandler;
import javafx.scene.control.Alert;
import javafx.scene.control.DialogEvent;
import javafx.scene.control.Alert.AlertType;

public class GameManager {
	
	public static boolean isCollide(Entity e1, Entity e2) {
		int dx1 = GameUtility.getWidth(e1)/2;
		int dy1 = GameUtility.getHeight(e1)/2;
		int dx2 = GameUtility.getWidth(e2)/2;
		int dy2 = GameUtility.getHeight(e2)/2;
//		if (Geometry.isPointInRect(e1.getX() - dx1, e1.getY() - dy1, e2.getX() - dx2, e2.getY() - dy2, e2.getX() + dx2, e2.getY() + dy2)) {
//			return true;
//		}
//		if (Geometry.isPointInRect(e1.getX() - dx1, e1.getY() + dy1, e2.getX() - dx2, e2.getY() - dx2, e2.getX() + dy2, e2.getY() + dy2)) {
//			return true;
//		}
//		if (Geometry.isPointInRect(e1.getX() + dx1, e1.getY() - dy1, e2.getX() - dx2, e2.getY() - dx2, e2.getX() + dy2, e2.getY() + dy2)) {
//			return true;
//		}
//		if (Geometry.isPointInRect(e1.getX() + dx1, e1.getY() + dy1, e2.getX() - dx2, e2.getY() - dx2, e2.getX() + dy2, e2.getY() + dy2)) {
//			return true;
//		}
//		if (Geometry.isPointInRect(e1.getX() - dx1, e1.getY(), e2.getX() - dx2, e2.getY() - dy2, e2.getX() + dx2, e2.getY() + dy2)) {
//			return true;
//		}
//		if (Geometry.isPointInRect(e1.getX() + dx1, e1.getY() + dy1, e2.getX() - dx2, e2.getY() - dx2, e2.getX() + dy2, e2.getY() + dy2)) {
//			return true;
//		}
//		if (Geometry.isPointInRect(e1.getX(), e1.getY() - dy1, e2.getX() - dx2, e2.getY() - dx2, e2.getX() + dy2, e2.getY() + dy2)) {
//			return true;
//		}
//		if (Geometry.isPointInRect(e1.getX(), e1.getY() + dy1, e2.getX() - dx2, e2.getY() - dx2, e2.getX() + dy2, e2.getY() + dy2)) {
//			return true;
//		}
//		return false;
		Rectangle rect1 = new Rectangle(e1.getX() - dx1, e1.getY() - dy1, e1.getX() + dx1, e1.getY() + dy1);
		Rectangle rect2 = new Rectangle(e2.getX() - dx2, e2.getY() - dy2, e2.getX() + dx2, e2.getY() + dy2);
		return Geometry.isRectangleIntersect(rect1, rect2);
	}
	
	private static void collide(Entity e1, Entity e2) {
		System.out.println("Collide");
		if (e1 instanceof Bullet) {
			System.out.println("Bullet1");
			if (e2 instanceof BulletPassable) {
				// nothing happen
				return;
			}
			else if (e2 instanceof Player) {
				if (((Bullet) e1).getOwner().getName().equals(((Player) e2).getName())) {
					return;
				}
				int dmg = ((Bullet)e1).getDamage();
				e1.hit(dmg);
				e2.hit(dmg);
			}
			else {
				int dmg = ((Bullet)e1).getDamage();
				e1.hit(dmg);
				e2.hit(dmg);
			}
		}
		else if (e2 instanceof Bullet) {
			System.out.println("Bullet2");
			if (e1 instanceof BulletPassable) {
				// nothing happen
				return;
			}
			else if (e1 instanceof Player) {
				if (((Bullet) e2).getOwner().getName().equals(((Player) e1).getName())) {
					return;
				}
				int dmg = ((Bullet)e2).getDamage();
				e1.hit(dmg);
				e2.hit(dmg);
			}
			else {
				int dmg = ((Bullet)e2).getDamage();
				e1.hit(dmg);
				e2.hit(dmg);
			}
		}
		else if (e1 instanceof Player) {
			if (e2 instanceof Item) {
				((Item) e2).collect((Player)e1);
			}
			else if (e2 instanceof Player) {
				return;
			}
			else if (e2 instanceof Entity) {
				//TODO: wait for width and size of each entity
				doCollision((Player) e1, e2);
			}
		}
		else if (e2 instanceof Player) {
			if (e1 instanceof Item) {
				((Item) e1).collect((Player)e2);
			}
			else if (e1 instanceof Player) {
				return;
			}
			else if (e1 instanceof Entity) {
				doCollision((Player) e2, e1);
			}
		}
	}
	
	public static void checkCollision() {
		List<IRenderable> entities = IRenderableHolder.getInstance().getEntities();
		for (int i=0; i<entities.size(); i++) {
			for (int j=i+1; j<entities.size(); j++) {
				Entity e1 = (Entity)entities.get(i);
				Entity e2 = (Entity)entities.get(j);
				if(e1.isDestroyed() || e2.isDestroyed()) continue;
				if (isCollide(e1, e2)){
					collide(e1, e2);
				}
			}
		}
	}
	
	// call this method when a player collide with an entity
	private static void doCollision(Player e1, Entity e2) {
		int dx1 = GameUtility.getWidth(e1)/2;
		int dy1 = GameUtility.getHeight(e1)/2;
		int dx2 = GameUtility.getWidth(e2)/2;
		int dy2 = GameUtility.getHeight(e2)/2;
		if (e1.getDirection() == GameUtility.UP) {
			e1.setY(e2.getY() + dy1 + dy2);
		}
		else if (e1.getDirection() == GameUtility.RIGHT) {
			e1.setX(e2.getX() - dx1 - dx2);
		}
		else if (e1.getDirection() == GameUtility.DOWN) {
			e1.setY(e2.getY() - dy1 - dy2);
		}
		else if (e1.getDirection() == GameUtility.LEFT) {
			e1.setX(e2.getX() + dy1 + dy2);
		}
	}
	
	public static void endGame(String message) {
		// clear eveything in this game
		Main.instance.animation.stop();
		ThreadHolder.getInstance().clear();
		IRenderableHolder.getInstance().clear();
		
		// notify game ending
		Alert alert = new Alert(AlertType.INFORMATION);
		alert.setTitle("GAME END!!");
		alert.setHeaderText(null);
		alert.setContentText(message);
		alert.setOnCloseRequest(new EventHandler<DialogEvent>() {
			
			@Override
			public void handle(DialogEvent event) {
				alert.close();
				Main.instance.ChangeScene();
			}
		});
		alert.showAndWait();
	}
	
}