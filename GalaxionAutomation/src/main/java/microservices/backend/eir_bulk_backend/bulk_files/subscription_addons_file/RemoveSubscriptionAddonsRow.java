package microservices.backend.eir_bulk_backend.bulk_files.subscription_addons_file;


public class RemoveSubscriptionAddonsRow {

	private String row;
	private String customerAccountNumber;
	private String msisdn;
	private String addon1;
	private String addon2;
	private String addon3;
	private String addon4;
	private String addon5;

	public RemoveSubscriptionAddonsRow(int billingAccountID, String msisdn) {
		row = "";
		this.customerAccountNumber = Integer.toString(billingAccountID);
		this.msisdn = msisdn;
		addon1 = "";
		addon2 = "";
		addon3 = "";
		addon4 = "";
		addon5 = "";
	}
	
	public RemoveSubscriptionAddonsRow(String s) {

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
		addon2 = parts[4].trim();
		addon3 = parts[5].trim();
		addon4 = parts[6].trim();
		addon5 = parts[7].trim();
	}

	public RemoveSubscriptionAddonsRow() {
		row = "";
		customerAccountNumber = "";
		msisdn = "";
		addon1 = "";
		addon2 = "";
		addon3 = "";
		addon4 = "";
		addon5 = "";
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

	public String getAddon2() {
		return addon2;
	}

	public void setAddon2(String addon2) {
		this.addon2 = addon2;
	}

	public String getAddon3() {
		return addon3;
	}

	public void setAddon3(String addon3) {
		this.addon3 = addon3;
	}

	public String getAddon4() {
		return addon4;
	}

	public void setAddon4(String addon4) {
		this.addon4 = addon4;
	}

	public String getAddon5() {
		return addon5;
	}

	public void setAddon5(String addon5) {
		this.addon5 = addon5;
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
	
	public String toString() {
		String[] fields = { row, customerAccountNumber, msisdn, addon1, addon2, addon3, addon4, addon5};
		String s = fields[0];
		for (int i = 1; i < fields.length; i++) {
			s = s + "," + fields[i];
		}

		return s;
	}
}