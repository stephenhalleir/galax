package pojo_repo.eir_user_management_backend;

import java.sql.Date;
import java.sql.ResultSet;
import java.sql.SQLException;

public class RecoverEmailRequest {

	private int id;
	private String otpUuid;
	private String msisdn;

	public RecoverEmailRequest() {

	}

	public RecoverEmailRequest(ResultSet rs) {
		try {
			id = rs.getInt("id");
			otpUuid = rs.getString("otp_uuid");
			msisdn = rs.getString("msisdn");
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getOtpUuid() {
		return otpUuid;
	}

	public void setOtpUuid(String otpUuid) {
		this.otpUuid = otpUuid;
	}

	public String getMsisdn() {
		return msisdn;
	}

	public void setMsisdn(String msisdn) {
		this.msisdn = msisdn;
	}

}