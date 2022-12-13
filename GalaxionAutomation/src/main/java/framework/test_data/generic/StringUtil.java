package framework.test_data.generic;

import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.sql.Blob;
import java.sql.SQLException;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Formatter;
import java.util.List;

import org.apache.commons.net.util.Base64;

public class StringUtil {

	/**
	 * Convert an integer in cents to a euro value
	 * 
	 * @param amount - e.g. "500"
	 * @return the currency amount - e.g. "5.00"
	 */
	public static String toCurrency(int amount) {

		// divide the cents amount by 100
		double adjustmentAmountEuros = amount / (double) 100;

		// convert it to 2 decimal places
		DecimalFormat df = new DecimalFormat("#.00");
		String adjustmentAmountFormated = df.format(adjustmentAmountEuros);

		// if < 1.00, add a leading zero
		if (adjustmentAmountFormated.startsWith(".")) {
			adjustmentAmountFormated = "0" + adjustmentAmountFormated;
		}

		// return the amount
		return adjustmentAmountFormated;
	}

	public static String toCurrency(long amount) {

		// divide the cents amount by 100
		double adjustmentAmountEuros = amount / (double) 100;

		// convert it to 2 decimal places
		DecimalFormat df = new DecimalFormat("#.00");
		String adjustmentAmountFormated = df.format(adjustmentAmountEuros);

		// if < 1.00, add a leading zero
		if (adjustmentAmountFormated.startsWith(".")) {
			adjustmentAmountFormated = "0" + adjustmentAmountFormated;
		}

		// return the amount
		return adjustmentAmountFormated;
	}

	/**
	 * Return the currency amount with a leading currency symbol
	 * 
	 * @param amount in cents - e.g. "500"
	 * @param symbol - currency symbol e.g. "€", "£", "$"
	 * @return string like "€5.00"
	 */
	public static String toCurrency(int amount, String symbol) {
		return symbol + toCurrency(amount);
	}

	public static String toCurrency(long amount, String symbol) {
		return symbol + toCurrency(amount);
	}

	public static String base64Encode(String originalInput) {
		Base64 base64 = new Base64();
		return new String(base64.encode(originalInput.getBytes()));
	}

	public static String base64Decode(String encodedString) {
		return new String(Base64.decodeBase64(encodedString.getBytes()));
	}

	public static String sha1Encrypt(String password) {
		String sha1 = "";
		try {
			MessageDigest crypt = MessageDigest.getInstance("SHA-1");
			crypt.reset();
			crypt.update(password.getBytes("UTF-8"));
			sha1 = byteToHex(crypt.digest());
		} catch (NoSuchAlgorithmException e) {
			e.printStackTrace();
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}
		return sha1;
	}

	private static String byteToHex(final byte[] hash) {
		Formatter formatter = new Formatter();
		for (byte b : hash) {
			formatter.format("%02x", b);
		}
		String result = formatter.toString();
		formatter.close();
		return result;
	}

	/**
	 * Return a database Blob value as a String
	 * 
	 * @param blob
	 * @return String equivalent
	 */
	public static String getStringFromBlob(Blob blob) {
		byte[] bdata;
		try {
			bdata = blob.getBytes(1, (int) blob.length());
			return new String(bdata);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null;
	}

	/**
	 * Sort an arraylist of strings
	 * 
	 * @param list
	 * @return the sorted list
	 */
	public static ArrayList<String> sortList(ArrayList<String> list) {
		String[] array = list.toArray(new String[0]);
		Arrays.sort(array);
		return new ArrayList<String>(Arrays.asList(array));
	}

	/**
	 * Get the (alphabetically) minimum String from a list
	 * 
	 * @param list
	 * @return the minimum value
	 */
	public static String getMinString(ArrayList<String> list) {
		List<String> sortedList = sortList(list);
		return sortedList.get(0);
	}

	/**
	 * Get the (alphabetically) maximum String from a list
	 * 
	 * @param list
	 * @return the maximum value
	 */
	public static String getMaxString(ArrayList<String> list) {
		List<String> sortedList = sortList(list);
		return sortedList.get(sortedList.size() - 1);
	}

	/**
	 * Generate an email address suffix from a company name
	 * 
	 * @param companyName - e.g. "AIB Bank"
	 * @param domain      - e.g. ".ie"
	 * @return email suffix like "aibbank.ie"
	 */
	public static String toEmailSuffix(String companyName, String domain) {

		String suffix = companyName.toLowerCase();
		String[] charsToRemove = { ",", ".", " ", "ltd" };

		for (int i = 0; i < charsToRemove.length; i++) {
			suffix = suffix.replace(charsToRemove[i], "");
		}

		suffix = suffix.replace("&", "and");

		return suffix + domain;
	}

	public static List<String> splitToArrayList(String s, String separator) {
		String[] arr = s.split(separator, -1);
		return Arrays.asList(arr);
	}

	public static ArrayList<String> stringToArrayList(String s) {
		ArrayList<String> list = new ArrayList<String>();
		list.add(s);
		return list;
	}

	public static int stringCompare(String str1, String str2) {

		int l1 = str1.length();
		int l2 = str2.length();
		int lmin = Math.min(l1, l2);

		for (int i = 0; i < lmin; i++) {
			int str1_ch = (int) str1.charAt(i);
			int str2_ch = (int) str2.charAt(i);

			if (str1_ch != str2_ch) {
				return str1_ch - str2_ch;
			}
		}

		if (l1 != l2) {
			return l1 - l2;
		}

		else {
			return 0;
		}
	}

	/**
	 * Determine whether a < b
	 * 
	 */
	public static boolean lessThan(String a, String b) {
		return stringCompare(a, b) == -1;
	}

	/**
	 * Determine whether a > b
	 */
	public static boolean greaterThan(String a, String b) {
		return stringCompare(a, b) == 1;
	}

	/**
	 * Print a time string from a number of seconds e.g. 66 seconds will print
	 * 00:01:06
	 * 
	 * @param totalSecs
	 * @return
	 */
	public static String getHoursMinutesSeconds(int totalSecs) {
		int hours = totalSecs / 3600;
		int minutes = (totalSecs % 3600) / 60;
		int seconds = totalSecs % 60;

		return String.format("%02d:%02d:%02d", hours, minutes, seconds);
	}

	public static String getHppSHA1HASH(String timestamp, String merchantID, String orderID, String result, String message, String pasRef, String authCode,
			String secret) {
		String tempHash = timestamp + "." + merchantID + "." + orderID + "." + result + "." + message + "." + pasRef + "." + authCode;
		return StringUtil.sha1Encrypt(StringUtil.sha1Encrypt(tempHash) + "." + secret);
	}
}
