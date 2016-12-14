/**
* @author Pornkrit Watcharasatianphan and Phanu Vajanopath
* @version 14 Dec 2016
* Project (1/2016) in 2110215 Prog Meth
*/
package utility;

public class Geometry {

	public static boolean isPointInRectangle(Point pt, Rectangle rect) {
		return rect.getX0() < pt.getX() && pt.getX() < rect.getX1() && rect.getY0() < pt.getY()
				&& pt.getY() < rect.getY1();
	}

	// this method is used for square only
	public static boolean isRectangleIntersect(Rectangle rect0, Rectangle rect1) {
		if (rect0.getArea() > rect1.getArea()) {
			Rectangle tmp0 = rect0;
			Rectangle tmp1 = rect1;
			rect0 = tmp1;
			rect1 = tmp0;
		}
		return isPointInRectangle(new Point(rect0.getX0(), rect0.getY0()), rect1)
				|| isPointInRectangle(new Point(rect0.getX0(), rect0.getYmid()), rect1)
				|| isPointInRectangle(new Point(rect0.getX0(), rect0.getY1()), rect1)
				|| isPointInRectangle(new Point(rect0.getXmid(), rect0.getY0()), rect1)
				|| isPointInRectangle(new Point(rect0.getXmid(), rect0.getYmid()), rect1)
				|| isPointInRectangle(new Point(rect0.getXmid(), rect0.getY1()), rect1)
				|| isPointInRectangle(new Point(rect0.getX1(), rect0.getY0()), rect1)
				|| isPointInRectangle(new Point(rect0.getX1(), rect0.getYmid()), rect1)
				|| isPointInRectangle(new Point(rect0.getX1(), rect0.getY1()), rect1);
	}

}
