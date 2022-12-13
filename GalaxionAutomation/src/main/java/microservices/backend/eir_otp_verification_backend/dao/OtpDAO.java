package microservices.backend.eir_otp_verification_backend.dao;

import java.sql.ResultSet;
import java.sql.SQLException;

import microservices.backend.eir_otp_verification_backend.data_model.OtpVerification;
import utilities.generic.database.GalaxionDBUtil;
import utilities.generic.database.MariaDBConnection;
import utilities.test.config_readers.ExcelSQLManager;

public class OtpDAO {
	
	public static OtpVerification getOTP(String msisdn) {
		String query = ExcelSQLManager.getSQLQuery("OTP", "GET_OTP");
		query=query.replace("$msisdn", msisdn);
		ResultSet rs;
		
		try {
			rs=GalaxionDBUtil.getQueryResultSet(query);
			if(rs.next()) {
				return new OtpVerification(rs);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return null;
	}
	
	public static OtpVerification getOTPByUuid(String otpUuid) {
		String query = ExcelSQLManager.getSQLQuery("OTP", "GET_OTP_BY_UUID");
		query=query.replace("$otpUuid", otpUuid);

		ResultSet rs;
		
		try {
			rs=GalaxionDBUtil.getQueryResultSet(query);
			if(rs.next()) {
				return new OtpVerification(rs);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return null;
	}
	
	/**
	 * Clear OTP codes from the database for a msisdn
	 * @param msisdn
	 */
	public static void clearOTPCodes(String msisdn) {
		String query = ExcelSQLManager.getSQLQuery("OTP", "CLEAR_OTP");
		query=query.replace("$msisdn", msisdn);
		try {
			GalaxionDBUtil.runUpdateQuery(query);
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
}
