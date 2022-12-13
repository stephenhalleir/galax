package microservices.backend.eir_otp_verification_backend.monitor;

import microservices.backend.eir_otp_verification_backend.dao.OtpDAO;
import microservices.backend.eir_otp_verification_backend.data_model.OtpVerification;
import utilities.generic.time.WaitUtil;

public class OTPMonitor {

	private final static int timeout=30;
	
	/**
	 * Poll the OTP database for 30 seconds for an OTP code for a specified MSISDN
	 * @param msisdn
	 * @return the OTP code
	 */
	public static String waitForOtpCode(String msisdn) {

		// wait up to 30 seconds for the OTP code
		long waitEndTime = System.currentTimeMillis() + (timeout * 1000);
		while (System.currentTimeMillis() < waitEndTime) {
			try {

				// poll the DB for the OTP code
				OtpVerification otp = OtpDAO.getOTP(msisdn);
				if (otp != null) {
					return otp.getVerificationCode();
				} else {
					WaitUtil.waitForSeconds(5);
				}
			} catch (Exception e) {
				e.printStackTrace();
			}
		}

		return null;
	}

}
