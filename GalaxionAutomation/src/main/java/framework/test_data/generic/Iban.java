package framework.test_data.generic;

import java.math.BigInteger;

public class Iban {

	private String country_code;
	private String bank_code;
	private String branch_code;
	private String bank_account_number;
	private String checkDigitPlaceholder = "XX";
	private String checkDigit;

	/**
	 * Constructor for the Iban, generating the check digit
	 * 
	 * @param country_code
	 * @param bank_code
	 * @param branch_code
	 * @param bank_account_number
	 */
	public Iban(String country_code, String bank_code, String branch_code, String bank_account_number) {
		
		// assign the values
		this.country_code = country_code;
		this.bank_code = bank_code;
		this.branch_code = branch_code;
		this.bank_account_number = bank_account_number;

		// calculate the check digits
		String initialIban = country_code + checkDigitPlaceholder + bank_code + branch_code + bank_account_number;

		String preCheckIban = initialIban;

		// replace the two placeholder digits by 00 (e.g., GB00 for the UK)
		preCheckIban = preCheckIban.replace(checkDigitPlaceholder, "00");

		// move the four initial characters to the end of the string
		preCheckIban = preCheckIban.substring(4) + preCheckIban.substring(0, 4);

		String numericString = "";

		// replace the letters in the string with digits
		for (int i = 0; i < preCheckIban.length(); i++) {
			final int numericValue = Character.getNumericValue(preCheckIban.charAt(i));
			numericString += numericValue;
		}

		// convert the long string to an integer
		BigInteger b = new BigInteger(numericString);

		// calculate modulus 97
		BigInteger mod = b.mod(new BigInteger("97"));
		int modValue = mod.intValue();

		// subtract the result from 98
		modValue = 98 - modValue;

		checkDigit = Integer.toString(modValue);

		// if the result is single digits, add a leading "0"
		if (modValue < 10) {
			checkDigit = "0" + checkDigit;
		}
	}

	public String getCountry_code() {
		return country_code;
	}

	public void setCountry_code(String country_code) {
		this.country_code = country_code;
	}

	public String getBank_code() {
		return bank_code;
	}

	public void setBank_code(String bank_code) {
		this.bank_code = bank_code;
	}

	public String getBranch_code() {
		return branch_code;
	}

	public void setBranch_code(String branch_code) {
		this.branch_code = branch_code;
	}

	public String getBank_account_number() {
		return bank_account_number;
	}

	public void setBank_account_number(String bank_account_number) {
		this.bank_account_number = bank_account_number;
	}

	public String toString() {
		return country_code + checkDigit + bank_code + branch_code + bank_account_number;
	}
}
