package pojo_repo.eir_salt_ar_backend;

import java.sql.Date;
import java.sql.ResultSet;
import java.sql.SQLException;

public class Invoice {

	private Date billDate;
	private int invoiceId;
	private Date periodEnd;
	private Date periodStart;
	private int vatAmount;
	private int vatExcludedAmount;
	private int id;
	private Date createTs;
	private Date modifTs;
	private String paymentTerm;
	private String billCycleName;

	public Invoice() {

	}

	public Invoice(ResultSet rs) {
		try {
			billDate = rs.getDate("bill_date");
			invoiceId = rs.getInt("invoice_id");
			periodEnd = rs.getDate("period_end");
			periodStart = rs.getDate("period_start");
			vatAmount = rs.getInt("vat_amount");
			vatExcludedAmount = rs.getInt("vat_excluded_amount");
			id = rs.getInt("id");
			createTs = rs.getDate("create_ts");
			modifTs = rs.getDate("modif_ts");
			paymentTerm = rs.getString("payment_term");
			billCycleName = rs.getString("bill_cycle_name");
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	public Date getBillDate() {
		return billDate;
	}

	public void setBillDate(Date billDate) {
		this.billDate = billDate;
	}

	public int getInvoiceId() {
		return invoiceId;
	}

	public void setInvoiceId(int invoiceId) {
		this.invoiceId = invoiceId;
	}

	public Date getPeriodEnd() {
		return periodEnd;
	}

	public void setPeriodEnd(Date periodEnd) {
		this.periodEnd = periodEnd;
	}

	public Date getPeriodStart() {
		return periodStart;
	}

	public void setPeriodStart(Date periodStart) {
		this.periodStart = periodStart;
	}

	public int getVatAmount() {
		return vatAmount;
	}

	public void setVatAmount(int vatAmount) {
		this.vatAmount = vatAmount;
	}

	public int getVatExcludedAmount() {
		return vatExcludedAmount;
	}

	public void setVatExcludedAmount(int vatExcludedAmount) {
		this.vatExcludedAmount = vatExcludedAmount;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public Date getCreateTs() {
		return createTs;
	}

	public void setCreateTs(Date createTs) {
		this.createTs = createTs;
	}

	public Date getModifTs() {
		return modifTs;
	}

	public void setModifTs(Date modifTs) {
		this.modifTs = modifTs;
	}

	public String getPaymentTerm() {
		return paymentTerm;
	}

	public void setPaymentTerm(String paymentTerm) {
		this.paymentTerm = paymentTerm;
	}

	public String getBillCycleName() {
		return billCycleName;
	}

	public void setBillCycleName(String billCycleName) {
		this.billCycleName = billCycleName;
	}

}