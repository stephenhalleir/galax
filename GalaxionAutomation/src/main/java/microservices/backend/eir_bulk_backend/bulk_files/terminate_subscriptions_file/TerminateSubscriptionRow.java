package microservices.backend.eir_bulk_backend.bulk_files.terminate_subscriptions_file;

import microservices.backend.eir_subscription_management_backend.enums.TerminationReason;

public class TerminateSubscriptionRow {

	private String row;
	private String customerAccountNumber;
	private String msisdn;
	private String terminationReason;
	private String terminationDate;
	private String earlyCeaseCharge;
	private String approvedBy;

	public TerminateSubscriptionRow(String s) {

		// hard fix here
		s = s + " ";

		String[] parts = s.split(",", -1);
		for (int i = 0; i < parts.length; i++) {
			System.err.println(i + " - " + parts[i]);
		}

		row = parts[0].trim();
		customerAccountNumber = parts[1].trim();
		msisdn = parts[2].trim();
		terminationReason = parts[3].trim();
		terminationDate = parts[4].trim();
		earlyCeaseCharge = parts[5].trim();
		approvedBy = parts[6].trim();
		
	}

	public TerminateSubscriptionRow() {
		row = "";
		customerAccountNumber = "";
		msisdn = "";
		terminationReason = "";
		terminationDate = "";
		earlyCeaseCharge = "";
		approvedBy = "";
	}
	
	public TerminateSubscriptionRow(int billingAccountID, String msisdn, TerminationReason terminationReason,String terminationDate) {
		row = "";
		this.customerAccountNumber = Integer.toString(billingAccountID);
		this.msisdn = msisdn;
		this.terminationReason = terminationReason.toString();
		this.terminationDate = terminationDate;
		earlyCeaseCharge = "0";
		approvedBy = "Steve Testapprover";
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

	public String getTerminationReason() {
		return terminationReason;
	}

	public void setTerminationReason(String terminationReason) {
		this.terminationReason = terminationReason;
	}

	public String getTerminationDate() {
		return terminationDate;
	}

	public void setTerminationDate(String terminationDate) {
		this.terminationDate = terminationDate;
	}

	public String getEarlyCeaseCharge() {
		return earlyCeaseCharge;
	}

	public void setEarlyCeaseCharge(String earlyCeaseCharge) {
		this.earlyCeaseCharge = earlyCeaseCharge;
	}

	public String getApprovedBy() {
		return approvedBy;
	}

	public void setApprovedBy(String approvedBy) {
		this.approvedBy = approvedBy;
	}

	public String toString() {
		String[] fields = { row, customerAccountNumber, msisdn, terminationReason, terminationDate, earlyCeaseCharge, approvedBy};
		String s = fields[0];
		for (int i = 1; i < fields.length; i++) {
			s = s + "," + fields[i];
		}

		return s;
	}
}