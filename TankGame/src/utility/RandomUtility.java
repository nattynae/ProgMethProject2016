/**
* @author Pornkrit Watcharasatianphan and Phanu Vajanopath
* @version 14 Dec 2016
* Project (1/2016) in 2110215 Prog Meth
*/
package utility;

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