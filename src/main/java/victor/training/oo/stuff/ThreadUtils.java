package victor.training.oo.stuff;

public class ThreadUtils {
	
	public static void sleepq(long millis) {
		try {
			Thread.sleep(millis);
		} catch (InterruptedException e) {
			throw new RuntimeException(e);
		}		
	}
	
}
