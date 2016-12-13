package Utility;

public class Geometry {
	
	public static boolean isPointInRectangle(Point pt, Rectangle rect) {
		return rect.getX0() < pt.getX() && pt.getX() < rect.getX1() 
				&& rect.getY0() < pt.getY() && pt.getY() < rect.getY1();
	}
	
	public static boolean isRectangleIntersect(Rectangle rect0, Rectangle rect1) { // for square only
		if (rect0.getArea() > rect1.getArea()) {
			Rectangle tmp0 = rect0;
			Rectangle tmp1 = rect1;
			rect0 = tmp0;
			rect1 = tmp1;
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
