package utilities.generic.time;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.concurrent.ThreadLocalRandom;
import java.util.concurrent.TimeUnit;

import org.joda.time.DateTimeZone;

public class TimeUtil {

	public static boolean isDST(Date d) {
		long milliseconds = d.getTime();
		DateTimeZone zone = DateTimeZone.forID("Europe/London");
		long next = zone.nextTransition(milliseconds);
		return !zone.isStandardOffset(next);
	}

	public static ArrayList<Date> getRandomDates(int amount, Date startDate, Date endDate) {

		ArrayList<Date> dates = new ArrayList<Date>();

		long[] times = new long[amount];
		long msBetween = endDate.getTime() - startDate.getTime();
		int daysBetween = (int) TimeUnit.DAYS.convert(msBetween, TimeUnit.MILLISECONDS);

		long msPerDay = 86400000;

		// populate the array with random Longs
		for (int i = 0; i < amount; i++) {
			times[i] = ThreadLocalRandom.current().nextLong(msPerDay * daysBetween);
		}

		// sort the array
		Arrays.sort(times);

		// add the dates to an array
		for (int i = 0; i < times.length; i++) {
			Date d = new Date(startDate.getTime() + times[i]);
			dates.add(d);
		}

		return dates;
	}
}
