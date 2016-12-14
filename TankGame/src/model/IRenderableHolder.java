/**
* @author Pornkrit Watcharasatianphan and Phanu Vajanopath
* @version 14 Dec 2016
* Project (1/2016) in 2110215 Prog Meth
*/
package model;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

public class IRenderableHolder {

	private static final IRenderableHolder instance = new IRenderableHolder();
	private List<IRenderable> entities;
	private Comparator<IRenderable> comparator;

	public IRenderableHolder() {
		entities = new ArrayList<>();
//		entities = Collections.synchronizedList(new ArrayList<IRenderable>());
		comparator = (IRenderable o1, IRenderable o2) -> {
			if (o1.getZ() > o2.getZ())
				return 1;
			return -1;
		};
	}

	public synchronized void addEntity(IRenderable e) {
		entities.add(e);
		Collections.sort(entities, comparator);
	}

	public synchronized List<IRenderable> getEntities() {
		return entities;
	}

	public static IRenderableHolder getInstance() {
		return instance;
	}

	public synchronized void clear() {
		entities.clear();
	}
}
