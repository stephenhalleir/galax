package pojo_repo.eir_change_offers_backend;

import java.sql.Date;
import java.sql.ResultSet;
import java.sql.SQLException;

public class RefWaivingReason {

	private String reasonKey;

	public RefWaivingReason() {

	}

	public RefWaivingReason(ResultSet rs) {
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