package utility;

public class Rectangle {

	private int x0, y0; // top left point
	private int x1, y1; // bottom right point

	public Rectangle(int x0, int y0, int x1, int y1) {
		this.x0 = x0;
		this.y0 = y0;
		this.x1 = x1;
		this.y1 = y1;
	}

	public int getX0() {
		return x0;
	}

	public int getY0() {
		return y0;
	}

	public int getX1() {
		return x1;
	}

	public int getY1() {
		return y1;
	}

	public int getXmid() {
		return (x0 + x1) / 2;
	}

	public int getYmid() {
		return (y0 + y1) / 2;
	}

	public int getArea() {
		return (x1 - x0) * (y1 - y0);
	}

}
