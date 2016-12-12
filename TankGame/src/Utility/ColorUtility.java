package Utility;

import javafx.scene.paint.Color;

public class ColorUtility {
	private static Color colorBodyPlayer1 = null;
	private static Color colorBodyPlayer2 = null;
	private static Color colorGunPlayer1 = null;
	private static Color colorGunPlayer2 = null;
	
	public static void setColorBodyPlayer1(Color color){
		colorBodyPlayer1 = color;
	}
	
	public static void setColorBodyPlayer2(Color color){
		colorBodyPlayer2 = color;
	}
	
	public static void setColorGunPlayer1(Color color){
		colorGunPlayer1 = color;
	}
	
	public static void setColorGunPlayer2(Color color){
		colorGunPlayer2 = color;
	}
	
	public static Color getColorBodyPlayer1(){
		return colorBodyPlayer1;
	}
	
	public static Color getColorBodyPlayer2(){
		return colorBodyPlayer2;
	}
	
	public static Color getColorGunPlayer1(){
		return colorGunPlayer1;
	}
	
	public static Color getColorGunPlayer2(){
		return colorGunPlayer2;
	}
	
}