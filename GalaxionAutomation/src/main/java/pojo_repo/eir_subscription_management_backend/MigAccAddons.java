package pojo_repo.eir_subscription_management_backend;

import java.sql.Date;
import java.sql.ResultSet;
import java.sql.SQLException;

public class MigAccAddons {

	private int account;
	private String service;
	private String serviceDesc;
	private Date effectiveDate;
	private int recurring;

	public MigAccAddons() {

	}

	public MigAccAddons(ResultSet rs) {
		try {
			account = rs.getInt("account");
			service = rs.getString("service");
			serviceDesc = rs.getString("service_desc");
			effectiveDate = rs.getDate("effective_date");
			recurring = rs.getInt("recurring");
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	public int getAccount() {
		return account;
	}

	public void setAccount(int account) {
		this.account = account;
	}

	public String getService() {
		return service;
	}

	public void setService(String service) {
		this.service = service;
	}

	public String getServiceDesc() {
		return serviceDesc;
	}

	public void setServiceDesc(String serviceDesc) {
		this.serviceDesc = serviceDesc;
	}

	public Date getEffectiveDate() {
		return effectiveDate;
	}

	public void setEffectiveDate(Date effectiveDate) {
		this.effectiveDate = effectiveDate;
	}

	public int getRecurring() {
		return recurring;
	}

	public void setRecurring(int recurring) {
		this.recurring = recurring;
	}

}