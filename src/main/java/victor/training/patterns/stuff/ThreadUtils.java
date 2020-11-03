package victor.training.patterns.stuff;

import java.util.Random;

public class ThreadUtils {
	
	public static void sleepq(long millis) {
		try {
			Thread.sleep(millis);
		} catch (InterruptedException e) {
			throw new RuntimeException(e);
		}		
	}
	public static void sleepr() {
		try {
			Thread.sleep(new Random().nextInt(500) + 100 );
		} catch (InterruptedException e) {
			throw new RuntimeException(e);
		}
	}

}
