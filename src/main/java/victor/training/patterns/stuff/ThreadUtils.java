package victor.training.patterns.stuff;

import java.util.Random;

public class ThreadUtils {

	/**
	 * Sleeps without throwing exceptions
	 */
	public static void sleepq(long millis) {
		try {
			Thread.sleep(millis);
		} catch (InterruptedException e) {
			throw new RuntimeException(e);
		}
	}

	/**
	 * Sleeps a bit of random time
	 */
	public static void sleepABit() {
		try {
			Thread.sleep(new Random().nextInt(500) + 100);
		} catch (InterruptedException e) {
			throw new RuntimeException(e);
		}
	}

}
