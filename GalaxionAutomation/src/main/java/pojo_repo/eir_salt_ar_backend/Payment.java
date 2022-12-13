package pojo_repo.eir_salt_ar_backend;

import java.sql.Date;
import java.sql.ResultSet;
import java.sql.SQLException;

public class Payment {

	private int amountAppliedToOverdue;
	private int backedOut;
	private Date creationDate;
	private Date creditDate;
	private String file;
	private Date paymentDate;
	private int paymentId;
	private String paymentSource;
	private String paymentType;
	private Date postHandlingDate;
	private int reallocated;
	private String reference;
	private String sourceProcess;
	private int transactionalCost;
	private int id;
	private String cardIssuer;
	private Date createTs;
	private Date modifTs;

	public Payment() {

	}

	public Payment(ResultSet rs) {
		try {
			amountAppliedToOverdue = rs.getInt("amount_applied_to_overdue");
			backedOut = rs.getInt("backed_out");
			creationDate = rs.getDate("creation_date");
			creditDate = rs.getDate("credit_date");
			file = rs.getString("file");
			paymentDate = rs.getDate("payment_date");
			paymentId = rs.getInt("payment_id");
			paymentSource = rs.getString("payment_source");
			paymentType = rs.getString("payment_type");
			postHandlingDate = rs.getDate("post_handling_date");
			reallocated = rs.getInt("reallocated");
			reference = rs.getString("reference");
			sourceProcess = rs.getString("source_process");
			transactionalCost = rs.getInt("transactional_cost");
			id = rs.getInt("id");
			cardIssuer = rs.getString("card_issuer");
			createTs = rs.getDate("create_ts");
			modifTs = rs.getDate("modif_ts");
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	public int getAmountAppliedToOverdue() {
		return amountAppliedToOverdue;
	}

	public void setAmountAppliedToOverdue(int amountAppliedToOverdue) {
		this.amountAppliedToOverdue = amountAppliedToOverdue;
	}

	public int getBackedOut() {
		return backedOut;
	}

	public void setBackedOut(int backedOut) {
		this.backedOut = backedOut;
	}

	public Date getCreationDate() {
		return creationDate;
	}

	public void setCreationDate(Date creationDate) {
		this.creationDate = creationDate;
	}

	public Date getCreditDate() {
		return creditDate;
	}

	public void setCreditDate(Date creditDate) {
		this.creditDate = creditDate;
	}

	public String getFile() {
		return file;
	}

	public void setFile(String file) {
		this.file = file;
	}

	public Date getPaymentDate() {
		return paymentDate;
	}

	public void setPaymentDate(Date paymentDate) {
		this.paymentDate = paymentDate;
	}

	public int getPaymentId() {
		return paymentId;
	}

	public void setPaymentId(int paymentId) {
		this.paymentId = paymentId;
	}

	public String getPaymentSource() {
		return paymentSource;
	}

	public void setPaymentSource(String paymentSource) {
		this.paymentSource = paymentSource;
	}

	public String getPaymentType() {
		return paymentType;
	}

	public void setPaymentType(String paymentType) {
		this.paymentType = paymentType;
	}

	public Date getPostHandlingDate() {
		return postHandlingDate;
	}

	public void setPostHandlingDate(Date postHandlingDate) {
		this.postHandlingDate = postHandlingDate;
	}

	public int getReallocated() {
		return reallocated;
	}

	public void setReallocated(int reallocated) {
		this.reallocated = reallocated;
	}

	public String getReference() {
		return reference;
	}

	public void setReference(String reference) {
		this.reference = reference;
	}

	public String getSourceProcess() {
		return sourceProcess;
	}

	public void setSourceProcess(String sourceProcess) {
		this.sourceProcess = sourceProcess;
	}

	public int getTransactionalCost() {
		return transactionalCost;
	}

	public void setTransactionalCost(int transactionalCost) {
		this.transactionalCost = transactionalCost;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getCardIssuer() {
		return cardIssuer;
	}

	public void setCardIssuer(String cardIssuer) {
		this.cardIssuer = cardIssuer;
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

}