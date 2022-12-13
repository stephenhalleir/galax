package utilities.generic.time;

public class WaitUtil {

	public static void waitForSeconds(int seconds) {
		System.out.println("WaitUtil: Waiting " + seconds + " seconds...");
		waitForMilliSeconds(seconds*1000);
	}
	
	public static void waitForMilliSeconds(int ms) {
		try {
			Thread.sleep(ms);
		} catch (InterruptedException ex) {
			Thread.currentThread().interrupt();
		}
	}
}
