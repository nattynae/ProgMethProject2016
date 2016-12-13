package Utility;

import java.util.Random;

public class RandomUtility {
	private static Random rand;

	public static void init() {
		rand = new Random();
	}

	public static int random(int bound) { // [0, bound)
		return rand.nextInt(bound);
	}

	// [lower, upper)
	public static int random(int lower_bound, int upper_bound) {
		return rand.nextInt(upper_bound - lower_bound) + lower_bound;
	}
}