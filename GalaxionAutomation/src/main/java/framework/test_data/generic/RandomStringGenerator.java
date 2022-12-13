package framework.test_data.generic;

import java.io.File;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.Period;
import java.util.Date;
import java.util.Random;
import java.util.Scanner;
import java.util.UUID;
import java.util.concurrent.ThreadLocalRandom;

import microservices.backend.eir_address_finder_backend.AddressFinderDAO;
import microservices.backend.eir_address_finder_backend.data_model.AddressFinderAddress;
import microservices.backend.eir_payment_center_backend.dao.PaymentCenterDAO;
import microservices.backend.eir_payment_center_backend.data_model.RefBank;
import utilities.generic.time.Timestamp;

public class RandomStringGenerator {

	public static void main(String[] args) {

		String alphaNumericString = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvxyz1234567890 ";

		// test: print a random alphanumeric string
		String s = getRandomString(alphaNumericString, 10);
		System.out.println(s);

		// test: print a random date string
		for (int i = 0; i < 100; i++) {
			System.out.println(getRandomDOBString());
		}

		// test: print a random name
		for (int i = 0; i < 1000; ++i) {
			System.out.println(getRandomFirstName() + " " + getRandomLastName() + " " + getRandomDOBString() + " " + getRandomMobilePhoneNumber());
		}
	}

	
	public static String getRandomString(String str, int n) {
		// create StringBuffer size of AlphaNumericString
		StringBuilder sb = new StringBuilder(n);

		for (int i = 0; i < n; i++) {
			
			// generate a random number between
			// 0 to string variable length
			int index = (int) (str.length() * Math.random());

			// add Character one by one in end of sb
			sb.append(str.charAt(index));
		}

		String randomString = sb.toString();
		
		// do not let the string begin with a "0" as it causes json issues
		if(randomString.startsWith("0")) {
			str=str.replace("0", "");
			int index = (int) (str.length() * Math.random());
			randomString = str.charAt(index) + randomString.substring(1);
		}
		
		
		return randomString;
	}

	// return a random date string in format ddmmyyyy where the date is +18years ago
	public static String getRandomDOBString() {

		// generate a random date object at least 18 years from the current date
		LocalDate d = LocalDate.now().minus(Period.ofDays(365 * 18)).minus(Period.ofDays((new Random().nextInt(365 * 70))));

		// read the day of the month - append 0 to the start if single digits
		String day = Integer.toString(d.getDayOfMonth()); 
		if (d.getDayOfMonth() < 10) {
			day = "0" + day;
		}

		// read the month index - append 0 to the start if single digits
		String month = Integer.toString(d.getMonthValue());
		if (d.getMonthValue() < 10) {
			month = "0" + month;
		}

		// return the string
		return day + month + d.getYear();
	}

	public static String getRandomMobilePhoneNumber() {
		return "086" + getRandomString("0123456789", 7);
	}
	
	public static String getRandomLandlinePhoneNumber() {
		return "0" + getRandomString("0123456789", 8);
	}

	public static String getRandomValueFromTextFile(File f) {

		String result = null;

		try {

			Random rand = new Random();
			int n = 0;
			for (Scanner sc = new Scanner(f); sc.hasNext();) {
				++n;
				String line = sc.nextLine();
				if (rand.nextInt(n) == 0)
					result = line;
			}
		} catch (Exception e) {

		}

		return result;
	}

	// return a random first name from the text file, in sentence case
	public static String getRandomFirstName() {
		String name = getRandomValueFromTextFile(new File(System.getProperty("user.dir") + "/files/random_data/firstnames.txt"));
		return name.substring(0, 1).toUpperCase() + name.substring(1).toLowerCase();
	}

	// return a random last name from the text file, in sentence case
	public static String getRandomLastName() {
		String name = getRandomValueFromTextFile(new File(System.getProperty("user.dir") + "/files/random_data/lastnames.txt"));
		return name.substring(0, 1).toUpperCase() + name.substring(1).toLowerCase();
	}
	
	// get a random name
	public static String getRandomName() {
		return getRandomFirstName() + " " + getRandomLastName();
	}
	
	// return a random first name from the text file, in sentence case
	public static AddressFinderAddress getRandomAddressFromFile() {
		String randomEircode=getRandomValueFromTextFile(new File(System.getProperty("user.dir") + "/files/random_data/eircodes.txt"));
		return AddressFinderDAO.getAddresses(randomEircode).get(0);
	}

	public static String getRandomValueFromArray(String[] list) {
		int index=getRandomInteger(0, list.length-1);
		return list[index];
	} 
	
	public static int getRandomIntegerFromArray(int[] list) {
		int index=getRandomInteger(0, list.length-1);
		return list[index];
	} 
	
	public static int getRandomInteger(int min, int max) {
		Random rn = new Random();
		return rn.nextInt(max - min + 1) + min;
	}
	
	public static String getRandomDateInRange(Date startDate, Date endDate, SimpleDateFormat fdf) {
			long startMillis = startDate.getTime();
		    long endMillis = endDate.getTime();
		    long randomMillisSinceEpoch = ThreadLocalRandom
		      .current()
		      .nextLong(startMillis, endMillis);

		    return fdf.format(new Date(randomMillisSinceEpoch));
	}
	
	// generate a random company name
	public static String getRandomCompanyName() {
		
		String companyName;
		boolean named=false;
		
		// read a random industry from the text file
		String industry = getRandomValueFromTextFile(new File(System.getProperty("user.dir") + "/files/random_data/industries.txt"));

		// get a random number between 1 and 2
		int x = getRandomInteger(1,2);
		
		// in half of cases, include a lastname in the company name
		if(x==1) {
			String name = getRandomLastName();
			
			x = getRandomInteger(1,4);
			
			// in 1/4 of named cases, include " & Co"
			if(x==3) {
				companyName = name + " & Co. " + industry;
			}
			// in 1/4 of named cases, include "& Sons"
			else if(x==4) {
				companyName = name + " & Sons " + industry;
			}
			// in half of named cases, just name + industry
			else {
				companyName = name + " " + industry;
			}
			
			// indicate that the company name has a surname in it
			named=true;
		}
		// in the other half of cases, use a creative name
		else {
			String company = getRandomValueFromTextFile(new File(System.getProperty("user.dir") + "/files/random_data/companynames.txt"));
			companyName = company.replace("$industry",industry);
		}
		
		x = getRandomInteger(1,3);
		// in 1/3 of cases - if the name doesnt include a surname, include a suffix (inc, ltd or corp)
		if(x==3 && !named) {
			companyName = companyName + " " + getRandomValueFromTextFile(new File(System.getProperty("user.dir") + "/files/random_data/suffixes.txt"));
		}
		
		// return the company name
		return companyName;
	}
	
	public static String getRandomUUID() {
		UUID uuid = UUID.randomUUID();
		return uuid.toString();
	}
	
	public static Person getRandomPerson() {
		Person person = new Person();
		
		String firstName=getRandomFirstName();
		String lastName=getRandomLastName();
		person.setFirstName(firstName);
		person.setLastName(lastName);
		person.setContactPhoneNumber(getRandomLandlinePhoneNumber());
		person.setMobilePhoneNumber(getRandomMobilePhoneNumber());
		person.setBillingAddress(getRandomAddressFromFile());
		person.setDeliveryAddress(getRandomAddressFromFile());
		person.setDateOfBirth(RandomStringGenerator.getRandomDOBString());
		person.setEmailAddress(firstName.toLowerCase() + "." + lastName.toLowerCase() + "_" + Timestamp.getTimestamp("yyyyMMddhhmmss") + "@gomo.ie");
		person.setCreditCardNumber("4263970000005262");
		return person;
	}
	
	public static Person getRandomB2BContact(String companyName) {
		Person person = getRandomPerson();
		String emailSuffix = StringUtil.toEmailSuffix(companyName, RandomStringGenerator.getRandomDomain());
		person.setEmailAddress(person.getEmailPrefix()+"@"+emailSuffix);
		
		return person;
	}
	
	public static String getRandomItemFromArray(String[] array) {
		int rnd = new Random().nextInt(array.length);
	    return array[rnd];
	}
	
	public static String getRandomDomain() {
		String[] domains={".ie",".com",".net"};
	    return getRandomItemFromArray(domains);
	}
	
	public static String getRandomEmailDomain() {
		String[] providers={"gmail.com","yahoo.com","outlook.com","live.com","msn.com","yahoo.co.uk"};
	    return getRandomItemFromArray(providers);
	}
	
	/**
	 * Generate a random boolean value TRUE/FALSE
	 * @return the boolean
	 */
	public static boolean getRandomBoolean() {
		return Math.random() < 0.5;
	}
	
	/**
	 * Generate a random IBAN with check digits
	 * @return the Iban string
	 */
	public static Iban getRandomIrishIBAN() {
		
		// get a random bank from the DB
		RefBank randomBank = PaymentCenterDAO.getRandomIrishBank();
		String accountNumber = getRandomString("1234567890", 8);
		
		// create an iban object (with check digit)
		Iban iban = new Iban(randomBank.getCountryCode(),randomBank.getBankCode(),randomBank.getBranchCode(),accountNumber);

		// return the iban
		return iban;
	}
	
	/**
	 * Generate a random IBAN with check digits
	 * @return the Iban string
	 */
	public static String getRandomForeignIBAN() {
		
		String accountNumber = getRandomString("1234567890", 8);
		
		// create an iban object (with check digit)
		Iban iban = new Iban("GB","DEUT","405081",accountNumber);

		// return the iban string
		return iban.toString();
	}
	
	public static String getRandomHexString(int length) {
		String hexString = "ABCDEF1234567890";
		return getRandomString(hexString,length);
	}
	
	public static String getRandomNumericString(int length) {
		String hexString = "1234567890";
		return getRandomString(hexString,length);
	}
}
