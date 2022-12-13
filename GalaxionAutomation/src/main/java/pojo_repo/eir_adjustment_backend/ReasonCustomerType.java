package pojo_repo.eir_adjustment_backend;

import java.sql.Date;
import java.sql.ResultSet;
import java.sql.SQLException;

public class ReasonCustomerType {

	private String reasonKey;

	public ReasonCustomerType() {

	}

	public ReasonCustomerType(ResultSet rs) {
		try {
			reasonKey = rs.getString("reason_key");
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	public String getReasonKey() {
		return reasonKey;
	}

	public void setReasonKey(String reasonKey) {
		this.reasonKey = reasonKey;
	}

}