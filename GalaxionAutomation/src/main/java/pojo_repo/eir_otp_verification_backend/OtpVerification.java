package pojo_repo.eir_otp_verification_backend;

import java.sql.Date;
import java.sql.ResultSet;
import java.sql.SQLException;

public class OtpVerification {

	private int id;
	private String msisdn;
	private String verificationCode;
	private Date expirationDatetime;
	private int retryCount;
	private Date validityStartDatetime;

	public OtpVerification() {

	}

	public OtpVerification(ResultSet rs) {
		try {
			id = rs.getInt("id");
			msisdn = rs.getString("msisdn");
			verificationCode = rs.getString("verification_code");
			expirationDatetime = rs.getDate("expiration_datetime");
			retryCount = rs.getInt("retry_count");
			validityStartDatetime = rs.getDate("validity_start_datetime");
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

	public Date getExpirationDatetime() {
		return expirationDatetime;
	}

	public void setExpirationDatetime(Date expirationDatetime) {
		this.expirationDatetime = expirationDatetime;
	}

	public int getRetryCount() {
		return retryCount;
	}

	public void setRetryCount(int retryCount) {
		this.retryCount = retryCount;
	}

	public Date getValidityStartDatetime() {
		return validityStartDatetime;
	}

	public void setValidityStartDatetime(Date validityStartDatetime) {
		this.validityStartDatetime = validityStartDatetime;
	}

}