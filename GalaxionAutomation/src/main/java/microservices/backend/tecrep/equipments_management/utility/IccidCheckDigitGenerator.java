package microservices.backend.tecrep.equipments_management.utility;

public class IccidCheckDigitGenerator {

	public static int getCheckDigit(String iccid) {
		
		// remove the L and F characters from the ICCID
		iccid=iccid.replace("L", "");
		iccid=iccid.replace("F", "");
		
		// check that the ICCID is 18 characters long, else return -1
		if(iccid.length()!=18) {
			System.err.println("Error: Unable to calculate check digit for ICCID of length " + iccid.length() + ": " + iccid);
			return -1;
		}
		
		// split the check digit string into characters
		char[] digits=iccid.toCharArray();
		
		int total = 0;
		
		// for every numeric string character...
		for(int i=0;i<digits.length;i++) {
			
			// get the numeric value
			int thisDigit=Integer.parseInt(String.valueOf(digits[i]));
			
			// multiply every second number by 2
			if(i%2==1) {
				thisDigit=thisDigit*2;
			}
			
			// if the number is 2 digits, add the digits together
			if(thisDigit>=10) {
				thisDigit=1+thisDigit%10;
			}

			// add it to the total
			total = total + thisDigit*9;
		}
		
		// get the last digit in the number (check digit)
		int checkDigit=total%10;
		
		// return the check digit
		return checkDigit;
	}
	
}
