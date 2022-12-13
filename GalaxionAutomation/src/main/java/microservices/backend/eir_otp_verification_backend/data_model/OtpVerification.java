package microservices.backend.eir_otp_verification_backend.data_model;

import java.sql.ResultSet;
import java.sql.SQLException;

public class OtpVerification {
	private int id;
	private String msisdn;
	private String verificationCode;
	private int retryCount;
	private String uuid;

	public OtpVerification(ResultSet rs) {
		try {
			id = rs.getInt("id");
			msisdn = rs.getString("msisdn");
			verificationCode = rs.getString("verification_code");
			retryCount = rs.getInt("retry_count");
			uuid=rs.getString("uuid");
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

	public String getMsisdn() {
		return msisdn;
	}

	public void setMsisdn(String msisdn) {
		this.msisdn = msisdn;
	}

	public String getVerificationCode() {
		return verificationCode;
	}

	public void setVerificationCode(String verificationCode) {
		this.verificationCode = verificationCode;
	}

	public int getRetryCount() {
		return retryCount;
	}

	public void setRetryCount(int retryCount) {
		this.retryCount = retryCount;
	}

	public String getUuid() {
		return uuid;
	}

	public void setUuid(String uuid) {
		this.uuid = uuid;
	}
}
