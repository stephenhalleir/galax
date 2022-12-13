package pojo_repo.eir_payment_center_backend;

import java.sql.Date;
import java.sql.ResultSet;
import java.sql.SQLException;

public class RefPaymentRun {

	private String brand;
	private String accountType;
	private String paymentMethod;
	private String type;
	private String approverEmail;
	private String devOpsEmail;
	private int daysBeforeDueDate;

	public RefPaymentRun() {

	}

	public RefPaymentRun(ResultSet rs) {
		try {
			brand = rs.getString("brand");
			accountType = rs.getString("account_type");
			paymentMethod = rs.getString("payment_method");
			type = rs.getString("type");
			approverEmail = rs.getString("approver_email");
			devOpsEmail = rs.getString("dev_ops_email");
			daysBeforeDueDate = rs.getInt("days_before_due_date");
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	public String getBrand() {
		return brand;
	}

	public void setBrand(String brand) {
		this.brand = brand;
	}

	public String getAccountType() {
		return accountType;
	}

	public void setAccountType(String accountType) {
		this.accountType = accountType;
	}

	public String getPaymentMethod() {
		return paymentMethod;
	}

	public void setPaymentMethod(String paymentMethod) {
		this.paymentMethod = paymentMethod;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getApproverEmail() {
		return approverEmail;
	}

	public void setApproverEmail(String approverEmail) {
		this.approverEmail = approverEmail;
	}

	public String getDevOpsEmail() {
		return devOpsEmail;
	}

	public void setDevOpsEmail(String devOpsEmail) {
		this.devOpsEmail = devOpsEmail;
	}

	public int getDaysBeforeDueDate() {
		return daysBeforeDueDate;
	}

	public void setDaysBeforeDueDate(int daysBeforeDueDate) {
		this.daysBeforeDueDate = daysBeforeDueDate;
	}

}