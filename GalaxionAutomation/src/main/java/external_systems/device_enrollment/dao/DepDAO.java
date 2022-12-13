package external_systems.device_enrollment.dao;

import java.sql.ResultSet;
import java.sql.SQLException;

import external_systems.device_enrollment.data_model.AndroidDevice;
import external_systems.device_enrollment.data_model.AndroidResponse;
import external_systems.device_enrollment.data_model.AppleDevice;
import external_systems.device_enrollment.data_model.AppleResponse;
import utilities.generic.database.rework.MariaDBConn;
import utilities.test.config_readers.ExcelSQLManager;

public class DepDAO {

	private static String host = "10.237.4.163";
	private static String port = "3306";
	private static String username = "dep-admin";
	private static String password = "auF8Xoo3";

	public static AndroidDevice getAndroidDeviceByOrderNumber(String orderNumber) {
		String query = ExcelSQLManager.getSQLQuery("DEVICE_ENROLLMENT", "GET_ANDROID_DEVICE");
		query = query.replace("$orderNumber", orderNumber);
		MariaDBConn db = new MariaDBConn(host, port, username, password);
		ResultSet rs;

		try {
			rs = db.getQueryResultSet(query);
			if (rs.next()) {
				AndroidDevice device = new AndroidDevice();
				device.setId(rs.getInt("id"));
				device.setCompanyId(rs.getString("company_id"));
				device.setDeviceId(rs.getString("device_id"));
				device.setOrderNumber(rs.getString("order_number"));
				device.setResellerId(rs.getString("reseller_id"));
				return device;
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}

		return null;
	}

	public static AndroidResponse getAndroidResponseByOrderNumber(String orderNumber) {
		String query = ExcelSQLManager.getSQLQuery("DEVICE_ENROLLMENT", "GET_ANDROID_RESPONSE");
		query = query.replace("$orderNumber", orderNumber);
		MariaDBConn db = new MariaDBConn(host, port, username, password);
		ResultSet rs;

		try {
			rs = db.getQueryResultSet(query);
			if (rs.next()) {
				AndroidResponse response = new AndroidResponse();
				response.setId(rs.getInt("id"));
				response.setOperationId(rs.getString("operation_id"));
				response.setJsonRequest(rs.getBlob("json_request"));
				response.setJsonResponse(rs.getBlob("json_response"));
				response.setOrderNumber(rs.getString("order_number"));
				response.setDeviceId(rs.getString("device_id"));
				response.setOperationType(rs.getString("operation_type"));
				response.setStatusCode(rs.getString("status_code"));
				response.setManufacturerType(rs.getString("manufacturer_type"));
				response.setStatusDescription(rs.getString("status_description"));
				return response;
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}

		return null;
	}

	public static AppleDevice getAppleDeviceByOrderNumber(String orderNumber) {
		String query = ExcelSQLManager.getSQLQuery("DEVICE_ENROLLMENT", "GET_APPLE_DEVICE");
		query = query.replace("$orderNumber", orderNumber);
		MariaDBConn db = new MariaDBConn(host, port, username, password);
		ResultSet rs;

		try {
			rs = db.getQueryResultSet(query);
			if (rs.next()) {
				AppleDevice device = new AppleDevice();
				device.setId(rs.getInt("id"));
				device.setTransactionId(rs.getString("transaction_id"));
				device.setDeviceId(rs.getString("device_id"));
				device.setOrderNumber(rs.getString("order_number"));
				device.setResellerId(rs.getString("reseller_id"));
				device.setOrderType(rs.getString("order_type"));
				device.setCustomerId(rs.getString("customer_id"));
				device.setPoNumber(rs.getString("po_number"));
				return device;
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}

		return null;
	}

	/**
	 * Get a row from the device enrollment platform 'apple_response' table
	 * @param orderNumber
	 * @return the row object
	 */
	public static AppleResponse getAppleResponseByOrderNumber(String orderNumber) {
		String query = ExcelSQLManager.getSQLQuery("DEVICE_ENROLLMENT", "GET_APPLE_RESPONSE");
		query = query.replace("$orderNumber", orderNumber);
		MariaDBConn db = new MariaDBConn(host, port, username, password);
		ResultSet rs;

		try {
			rs = db.getQueryResultSet(query);
			if (rs.next()) {
				AppleResponse response = new AppleResponse();
				response.setId(rs.getInt("id"));
				response.setDeviceEnrollmentTransactionId(rs.getString("device_enrollment_transaction_id"));
				response.setDeviceId(rs.getString("device_id"));
				response.setJsonRequest(rs.getBlob("json_request"));
				response.setJsonResponse(rs.getBlob("json_response"));
				response.setOrderNumber(rs.getString("order_number"));
				response.setStatusCode(rs.getString("status_code"));
				response.setDevicePostStatus(rs.getString("device_post_status"));
				response.setDevicePostStatusMessage(rs.getString("device_post_status_message"));
				return response;
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}

		return null;
	}
}
