package microservices.backend.eir_bulk_backend.bulk_files.adjustments_file;

import microservices.backend.eir_adjustment_backend.enums.AdjustmentReason;

public class AdjustmentsRow {

	private String row;
	private String customerAccountNumber;
	private String adjustmentReason;
	private String adjustmentAmount;
	private String comment;
	private String isNotificationRequired;
	

	public AdjustmentsRow(String s) {

		String[] parts = s.split(",", -1);
		for (int i = 0; i < parts.length; i++) {
			System.err.println(i + " - " + parts[i]);
		}

		row = parts[0].trim();
		customerAccountNumber = parts[1].trim();
		adjustmentReason=parts[2].trim();
		adjustmentAmount=parts[3].trim();
		comment=parts[4].trim();
		isNotificationRequired=parts[5].trim();		
	}
	
	public AdjustmentsRow(String customerAccountNumber, AdjustmentReason adjustmentReason,String adjustmentAmount, String comment, boolean isNotificationRequired) {
		this.customerAccountNumber=customerAccountNumber;
		this.adjustmentReason=adjustmentReason.toString();
		this.adjustmentAmount=adjustmentAmount;
		this.comment = comment;
		this.isNotificationRequired=Boolean.toString(isNotificationRequired).toUpperCase();
	}

	public AdjustmentsRow() {
		
		row = "";
		customerAccountNumber="";
		adjustmentReason="";
		adjustmentAmount="";
		comment="";
		isNotificationRequired="";
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

	public String getAdjustmentReason() {
		return adjustmentReason;
	}

	public void setAdjustmentReason(String adjustmentReason) {
		this.adjustmentReason = adjustmentReason;
	}

	public String getAdjustmentAmount() {
		return adjustmentAmount;
	}

	public void setAdjustmentAmount(String adjustmentAmount) {
		this.adjustmentAmount = adjustmentAmount;
	}

	public String getComment() {
		return comment;
	}

	public void setComment(String comment) {
		this.comment = comment;
	}

	public String getIsNotificationRequired() {
		return isNotificationRequired;
	}

	public void setIsNotificationRequired(String isNotificationRequired) {
		this.isNotificationRequired = isNotificationRequired;
	}

	public String toString() {
		String[] fields = { row,customerAccountNumber, adjustmentReason, adjustmentAmount, comment, isNotificationRequired};
		String s = fields[0];
		for (int i = 1; i < fields.length; i++) {
			s = s + "," + fields[i];
		}

		return s;
	}
}