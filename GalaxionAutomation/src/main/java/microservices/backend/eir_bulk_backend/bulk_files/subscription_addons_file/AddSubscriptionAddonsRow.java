package microservices.backend.eir_bulk_backend.bulk_files.subscription_addons_file;


public class AddSubscriptionAddonsRow {

	private String row;
	private String customerAccountNumber;
	private String msisdn;
	private String addon1;
	private String addon1Charge;
	private String addon2;
	private String addon2Charge;
	private String addon3;
	private String addon3Charge;
	private String addon4;
	private String addon4Charge;
	private String addon5;
	private String addon5Charge;

	public AddSubscriptionAddonsRow(String s) {

		// hard fix here
		s = s + " ";

		String[] parts = s.split(",", -1);
		for (int i = 0; i < parts.length; i++) {
			System.err.println(i + " - " + parts[i]);
		}

		row = parts[0].trim();
		customerAccountNumber = parts[1].trim();
		msisdn = parts[2].trim();
		addon1 = parts[3].trim();
		addon1Charge = parts[4].trim();
		addon2 = parts[5].trim();
		addon2Charge = parts[6].trim();
		addon3 = parts[7].trim();
		addon3Charge = parts[8].trim();
		addon4 = parts[9].trim();
		addon4Charge = parts[10].trim();
		addon5 = parts[11].trim();
		addon5Charge = parts[12].trim();
	}

	public AddSubscriptionAddonsRow() {
		row = "";
		customerAccountNumber = "";
		msisdn = "";
		addon1 = "";
		addon1Charge = "";
		addon2 = "";
		addon2Charge = "";
		addon3 = "";
		addon3Charge = "";
		addon4 = "";
		addon4Charge = "";
		addon5 = "";
		addon5Charge = "";
	}
	
	public AddSubscriptionAddonsRow(int billingAccountID, String msisdn) {
		row = "";
		this.customerAccountNumber = Integer.toString(billingAccountID);
		this.msisdn = msisdn;
		addon1 = "";
		addon1Charge = "";
		addon2 = "";
		addon2Charge = "";
		addon3 = "";
		addon3Charge = "";
		addon4 = "";
		addon4Charge = "";
		addon5 = "";
		addon5Charge = "";
	}

	public void randomize() {

	}

	
	public String getRow() {
		return row;
	}

	public void setRow(String row) {
		this.row = row;
	}

	public String getCustomerAccountNumber() {
		return customerAccountNumber;
	}

	public void setCustomerAccountNumber(String customerAccountNumber) {
		this.customerAccountNumber = customerAccountNumber;
	}
	

	public String getMsisdn() {
		return msisdn;
	}

	public void setMsisdn(String msisdn) {
		this.msisdn = msisdn;
	}

	public String getAddon1() {
		return addon1;
	}

	public void setAddon1(String addon1) {
		this.addon1 = addon1;
	}

	public String getAddon1Charge() {
		return addon1Charge;
	}

	public void setAddon1Charge(String addon1Charge) {
		this.addon1Charge = addon1Charge;
	}

	public String getAddon2() {
		return addon2;
	}

	public void setAddon2(String addon2) {
		this.addon2 = addon2;
	}

	public String getAddon2Charge() {
		return addon2Charge;
	}

	public void setAddon2Charge(String addon2Charge) {
		this.addon2Charge = addon2Charge;
	}

	public String getAddon3() {
		return addon3;
	}

	public void setAddon3(String addon3) {
		this.addon3 = addon3;
	}

	public String getAddon3Charge() {
		return addon3Charge;
	}

	public void setAddon3Charge(String addon3Charge) {
		this.addon3Charge = addon3Charge;
	}

	public String getAddon4() {
		return addon4;
	}

	public void setAddon4(String addon4) {
		this.addon4 = addon4;
	}

	public String getAddon4Charge() {
		return addon4Charge;
	}

	public void setAddon4Charge(String addon4Charge) {
		this.addon4Charge = addon4Charge;
	}

	public String getAddon5() {
		return addon5;
	}

	public void setAddon5(String addon5) {
		this.addon5 = addon5;
	}

	public String getAddon5Charge() {
		return addon5Charge;
	}

	public void setAddon5Charge(String addon5Charge) {
		this.addon5Charge = addon5Charge;
	}
	
	/*
	 * Add the addon into the next available addon slot - addon1, addon2, addon3, etc
	 */
	public void addAddon(String addon) {
		if(addon1.equals("")) {
			addon1=addon;
		}
		else if(addon2.equals("")) {
			addon2=addon;
		}
		else if(addon3.equals("")) {
			addon3=addon;
		}
		else if(addon4.equals("")) {
			addon4=addon;
		}
		else if(addon5.equals("")) {
			addon5=addon;
		}
	}
	
	/*
	 * Add the addon into the next available addon slot - addon1, addon2, addon3, etc
	 */
	public void addAddon(String addon,String newCharge) {
		
		if(newCharge==null || newCharge.equals("")) {
			addAddon(addon);
		}
		else {
			if(addon1.equals("")) {
				addon1=addon;
				addon1Charge=newCharge;
			}
			else if(addon2.equals("")) {
				addon2=addon;
				addon2Charge=newCharge;
			}
			else if(addon3.equals("")) {
				addon3=addon;
				addon3Charge=newCharge;
			}
			else if(addon4.equals("")) {
				addon4=addon;
				addon4Charge=newCharge;
			}
			else if(addon5.equals("")) {
				addon5=addon;
				addon5Charge=newCharge;
			}
			else {
				System.err.println("Addon " + addon + " not added as all 5 addon slots are used");
			}
		}
		
	}

	public String toString() {
		String[] fields = { row, customerAccountNumber, msisdn, addon1, addon1Charge, addon2, addon2Charge, addon3, addon3Charge, addon4, addon4Charge, addon5, addon5Charge};
		String s = fields[0];
		for (int i = 1; i < fields.length; i++) {
			s = s + "," + fields[i];
		}

		return s;
	}
}