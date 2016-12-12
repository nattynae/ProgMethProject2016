package Utility;

public class Geometry {
	public static boolean isPointInRect(int ptX, int ptY, int rectX0, int rectY0, int rectX1, int rectY1) {
		return rectX0 < ptX && ptX < rectX1 && rectY0 < ptY && ptY < rectY1;

	}
}
