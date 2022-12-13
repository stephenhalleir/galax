package microservices.backend.eir_bulk_backend.bulk_files.payments_file;


public class PaymentRow {

	private String customerAccountNumber;
	private String paymentAmount;
	private String paymentDate;
	private String invoiceNumber;
	private String remark;
	private String documentType;
	private String payItem;

	public PaymentRow(String s) {

		// hard fix here
		s = s + " ";

		String[] parts = s.split(",", -1);
		for (int i = 0; i < parts.length; i++) {
			System.err.println(i + " - " + parts[i]);
		}

		customerAccountNumber = parts[0].trim();
		paymentAmount = parts[1].trim();
		paymentDate = parts[2].trim();
		invoiceNumber = parts[3].trim();
		remark = parts[4].trim();
		documentType = parts[5].trim();
		payItem = parts[6].trim();
	}

	public PaymentRow() {
		customerAccountNumber = "";
		paymentAmount="";
		paymentDate="";
		invoiceNumber="";
		remark="";
		documentType="";
		payItem="";
	}

	public void randomize() {

	}

	public String getCustomerAccountNumber() {
		return customerAccountNumber;
	}

	public void setCustomerAccountNumber(String customerAccountNumber) {
		this.customerAccountNumber = customerAccountNumber;
	}

	public String getPaymentAmount() {
		return paymentAmount;
	}

	public void setPaymentAmount(String paymentAmount) {
		this.paymentAmount = paymentAmount;
	}

	public String getPaymentDate() {
		return paymentDate;
	}

	public void setPaymentDate(String paymentDate) {
		this.paymentDate = paymentDate;
	}

	public String getInvoiceNumber() {
		return invoiceNumber;
	}

	public void setInvoiceNumber(String invoiceNumber) {
		this.invoiceNumber = invoiceNumber;
	}

	public String getRemark() {
		return remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}

	public String getDocumentType() {
		return documentType;
	}

	public void setDocumentType(String documentType) {
		this.documentType = documentType;
	}

	public String getPayItem() {
		return payItem;
	}

	public void setPayItem(String payItem) {
		this.payItem = payItem;
	}

	public String toString() {
		String[] fields = { customerAccountNumber,paymentAmount,paymentDate, invoiceNumber, remark, documentType, payItem};
		String s = fields[0];
		for (int i = 1; i < fields.length; i++) {
			s = s + "," + fields[i];
		}

		return s + ",";
	}
}