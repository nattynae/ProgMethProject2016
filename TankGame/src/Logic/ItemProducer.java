package Logic;

import Model.ATKItem;
import Model.ATKSpeedItem;
import Model.BulletItem;
import Model.Entity;
import Model.HPItem;
import Model.IRenderable;
import Model.IRenderableHolder;
import Model.Item;
import Model.SpeedItem;
import Utility.GameUtility;
import Utility.RandomUtility;
import ui.GameScreen;

public class ItemProducer extends Thread{
	
	@Override
	public void run(){
		// TODO Auto-generated method stub
		while(true) {
			for (int i=0; i<4; i++) {
				System.out.println("Produce Item");
				Item item = buildItem();
				if (item == null) continue;
				IRenderableHolder.getInstance().addEntity(item);
				Thread t = new ItemDestroyer(item);
				ThreadsHolder.instance.addThread(t);
				t.start();
			}
			try{
				sleep(10000);
			}catch(InterruptedException e) {
				e.printStackTrace();
				return;
			}
		}
	}
	
	private static Item buildItem() {
		//TODO: wait for width and size of each entity & number of obstacle (range)
		int x = RandomUtility.random(GameScreen.maxWidth - 40) + 20;
		int y = RandomUtility.random(GameScreen.maxHeight - 40) + 20;
		//int x = RandomUtility.random(460) + 20;
		//int y = RandomUtility.random(460) + 20;
		while (!canPlaceAt(x, y)) { // TODO: check if can place here
			x = RandomUtility.random(GameScreen.maxWidth - 40) + 20;
			y = RandomUtility.random(GameScreen.maxHeight - 40) + 20;
			//x = RandomUtility.random(460) + 20;
			//y = RandomUtility.random(460) + 20;
			
		}
		int type = RandomUtility.random(1,6);
		Item item;
		switch(type) {
		case 1: item = new ATKItem(x, y); break;
		case 2: item = new ATKSpeedItem(x, y); break;
		case 3: item = new BulletItem(x, y); break;
		case 4: item = new HPItem(x, y); break;
		case 5: item = new SpeedItem(x, y); break;
		default: return null;
		}
		return item;
	}
	
	private static boolean canPlaceAt(int x,int y) {
		Item tmp = new HPItem(x, y);
		for(IRenderable r: IRenderableHolder.getInstance().getEntities()) {
			Entity e = (Entity)r;
			if (GameManager.isCollide(e, tmp) || GameManager.isCollide(tmp, e)) {
				return false;
			}
		}
		return true;
	}
}
