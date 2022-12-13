package utilities.galaxion.test_data.vouchers;

import java.util.ArrayList;

import utilities.generic.files.TextReader;

public class VoucherManager {
	
	/**
	 * Retrieve an return voucher from the vouchers file
	 * 
	 * @param amount
	 * @return a Voucher object
	 */
	public static Voucher getVoucher(int amount) {

		String fileLocation="files\\inventory\\Vouchers";
		String selectedVoucherString=null;
		
		// read the voucher file as an array of Strings
		ArrayList<String> voucherStrings = TextReader.getContentAsArrayList(fileLocation);
		
		// search the file for a voucher that matches the amount specified
		for(String voucherString:voucherStrings) {
			
			String[] voucherComponents = voucherString.split(",");
			if(Integer.parseInt(voucherComponents[2])==amount) {
				selectedVoucherString=voucherString;
				break;
			}
		}
		
		// if no voucher exists for the specified amount, throw an error
		if(selectedVoucherString==null) {
			System.err.println("Error: No voucher found for amount " + amount);
			return null;
		}
		
		// remove the used voucher from the list
		voucherStrings.remove(selectedVoucherString);
		
		// generate new file contents, without the used voucher
		String newFileContents="";
		for(String s:voucherStrings) {
			newFileContents = newFileContents + s + "\n";
		}
		
		newFileContents=newFileContents.trim();
		
		// write the contents back to the vouchers file, minus the used voucher
		TextReader.writeFile(newFileContents, fileLocation);
		
		// return the voucher object from the string
		String[] voucherComponents = selectedVoucherString.split(",");
		
		if(voucherComponents.length>2) {
			return new Voucher(voucherComponents[0], voucherComponents[1],Integer.parseInt(voucherComponents[2]));
		}
		else {
			System.err.println("Error: invalid voucher string - " + selectedVoucherString);
			return null;
		}
	}
	
}
