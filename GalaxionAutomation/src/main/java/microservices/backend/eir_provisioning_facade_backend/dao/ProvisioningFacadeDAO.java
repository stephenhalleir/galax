package microservices.backend.eir_provisioning_facade_backend.dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

import microservices.backend.eir_provisioning_facade_backend.data_model.MsisdnSwapInventory;
import microservices.backend.eir_provisioning_facade_backend.data_model.PfService;
import microservices.backend.eir_provisioning_facade_backend.data_model.PfRequest;
import microservices.backend.eir_provisioning_facade_backend.enums.ProvisioningAction;
import microservices.backend.eir_provisioning_facade_backend.enums.ServiceAction;
import utilities.generic.database.GalaxionDBUtil;
import utilities.test.config_readers.ExcelSQLManager;

public class ProvisioningFacadeDAO {

	public static ArrayList<PfService> getServices(String msisdn) {
		ArrayList<PfService> services = new ArrayList<PfService>();

		String query = ExcelSQLManager.getSQLQuery("PROVISIONING_FACADE", "GET_SERVICES_FOR_MSISDN");
		query = query.replace("$msisdn", msisdn);

		try {
			// execute the query and get the result set
			ResultSet rs = GalaxionDBUtil.getQueryResultSet(query);

			// for each row returned
			while (rs.next()) {

				// create a service object and add it to the list
				services.add(new PfService(rs));

			}
		} catch (SQLException e) {
			e.printStackTrace();
		}

		return services;

	}

	// return the row from the provisioning_facade SERVICE table for a particular
	// msisdn & action
	public static ArrayList<PfService> getProvisioningService(String msisdn, ServiceAction service_action, String startTime) {

		ArrayList<PfService> services = new ArrayList<PfService>();
		if (startTime == null) {
			startTime = "";
		}

		// retrieve and populate the query
		String query = ExcelSQLManager.getSQLQuery("PROVISIONING_FACADE", "GET_SERVICE");
		query = query.replace("$msisdn", msisdn).replace("$service_action", service_action.toString()).replace("$startTime", startTime);

		try {
			// execute the query and get the result set
			ResultSet rs = GalaxionDBUtil.getQueryResultSet(query);

			// for each row returned
			while (rs.next()) {

				// create a service object and add it to the list
				try {
					PfService service = new PfService();
					service.setId(rs.getInt("id"));
					service.setServiceID(rs.getInt("service_id"));
					service.setServiceName(rs.getString("service_name"));
					service.setServiceStatus(rs.getString("service_status"));
					service.setServiceAction(rs.getString("service_action"));
					service.setErrorCode(rs.getString("error_code"));
					service.setRequestID(rs.getInt("request_id"));
					services.add(service);
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}

		return services;
	}

	/**
	 * Load a MSISDN into provisioning-facade's msisdn_swap_inventory table as a
	 * pre-requisite to msisdn_swap testing
	 * 
	 * @param msisdn
	 */
	public static void loadMSISDNForMSISDNSwaps(String msisdn) {
		String query = ExcelSQLManager.getSQLQuery("PROVISIONING_FACADE", "LOAD_MSISDN");
		query = query.replace("$msisdn", msisdn);
		try {
			GalaxionDBUtil.runUpdateQuery(query);
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	public static ArrayList<PfService> getServices(ArrayList<PfService> s, String status) {

		ArrayList<PfService> services = new ArrayList<PfService>();

		for (PfService service : s) {
			if (service.getServiceStatus().equals(status)) {
				services.add(service);
			}
		}

		return services;
	}

	// get the last provisioning request ID
	public static int getLastProvisioningRequestId(String msisdn, String action) {

		// build the query
		String query = ExcelSQLManager.getSQLQuery("PROVISIONING_FACADE", "GET_LAST_PROVISIONING_REQUEST_ID");
		query = query.replace("$msisdn", msisdn);
		query = query.replace("$action", action);

		int requestId = -1;

		try {
			ResultSet rs = GalaxionDBUtil.getQueryResultSet(query);
			while (rs.next()) {
				;
				try {
					requestId = rs.getInt("id");
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}

		return requestId;
	}

	public static int getProvisioningRequestId(String msisdn, String status, String action, String errorCode) {

		int subscriptionId = 1369;

		String query = ExcelSQLManager.getSQLQuery("PROVISIONING_FACADE", "GET_REQUEST");
		query = query.replace("$subscription_id", Integer.toString(subscriptionId));
		query = query.replace("$status", status);
		query = query.replace("$request_action", action);
		query = query.replace("$error_code", errorCode);

		try {
			ResultSet rs = GalaxionDBUtil.getQueryResultSet(query);
			while (rs.next()) {
				System.err.println("getProvisioningRequestId(): " + rs.getInt("id"));
				try {
					return rs.getInt("id");
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}

		return -1;
	}

	public static MsisdnSwapInventory getNextAvailableReplacementMsisdn() {
		String query = ExcelSQLManager.getSQLQuery("PROVISIONING_FACADE", "GET_NEXT_AVAILABLE_MSISDN");

		try {
			ResultSet rs = GalaxionDBUtil.getQueryResultSet(query);
			while (rs.next()) {
				System.err.println("getProvisioningRequestId(): " + rs.getInt("id"));
				return new MsisdnSwapInventory(rs);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}

		return null;
	}

	public static ArrayList<PfRequest> getRequestsBySubIdAndAction(int subscriptionId, ProvisioningAction action) {
		ArrayList<PfRequest> requests = new ArrayList<PfRequest>();

		String query = ExcelSQLManager.getSQLQuery("PROVISIONING_FACADE", "GET_PROV_FACADE_SERVICES_BY_SUB_ID_AND_TYPE");
		query = query.replace("$subscriptionID", Integer.toString(subscriptionId));
		query = query.replace("$provisioningAction", action.toString());

		try {
			// execute the query and get the result set
			ResultSet rs = GalaxionDBUtil.getQueryResultSet(query);

			// for each row returned
			while (rs.next()) {

				// create a service object and add it to the list
				requests.add(new PfRequest(rs));

			}
		} catch (SQLException e) {
			e.printStackTrace();
		}

		return requests;
	}

	public static PfRequest getRequestById(int requestID) {
		String query = ExcelSQLManager.getSQLQuery("PROVISIONING_FACADE", "GET_REQUEST_BY_ID");
		query = query.replace("$id", Integer.toString(requestID));

		try {
			// execute the query and get the result set
			ResultSet rs = GalaxionDBUtil.getQueryResultSet(query);

			// for each row returned
			while (rs.next()) {
				return new PfRequest(rs);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null;
	}

	public static ArrayList<PfService> getServicesByRequestID(int requestID) {
		ArrayList<PfService> services = new ArrayList<PfService>();

		String query = ExcelSQLManager.getSQLQuery("PROVISIONING_FACADE", "GET_SERVICES_BY_REQUEST_ID");
		query = query.replace("$requestID", Integer.toString(requestID));

		try {
			// execute the query and get the result set
			ResultSet rs = GalaxionDBUtil.getQueryResultSet(query);

			// for each row returned
			while (rs.next()) {

				// create a service object and add it to the list
				services.add(new PfService(rs));

			}
		} catch (SQLException e) {
			e.printStackTrace();
		}

		return services;
	}

	/**
	 * Add a record into 'eir-provisioning-facade-backend'.batch
	 * 
	 * @param msisdn
	 * @param imsi
	 * @param catalogOfferCode
	 */
	public static void addToBatchTable(String msisdn, String imsi, String catalogOfferCode) {

		// create a new row in the 'eir-provisioning-facade-backend'.network table
		String query1 = "INSERT INTO `eir-provisioning-facade-backend`.network VALUES (NULL);";
		try {
			GalaxionDBUtil.runUpdateQuery(query1);
		} catch (SQLException e) {
			e.printStackTrace();
		}

		int lastId = -1;

		// get the highest ID from the 'network' table
		ResultSet rs;
		try {
			rs = GalaxionDBUtil.getQueryResultSet("select MAX(id) as last_id from `eir-provisioning-facade-backend`.network LIMIT 1;");

			// get the ID of the newly added row
			while (rs.next()) {
				try {
					lastId = rs.getInt("last_id");
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
		} catch (SQLException e1) {
			e1.printStackTrace();
		}

		// get time stamp
		String pattern = "yyyy-MM-dd HH.mm.ss";
		SimpleDateFormat simpleDateFormat = new SimpleDateFormat(pattern);
		String date = simpleDateFormat.format(new Date());

		// add the row into 'eir-provisioning-facade-backend'.batch
		String query2 = "INSERT INTO `eir-provisioning-facade-backend`.batch (msisdn, imsi, network_id, `status`, `catalog_offer_code`, created_date_time)"
				+ " VALUES ('$msisdn', '$imsi', $lastId, 'PREACTIVE', '$catalog_offer_code', '$timestamp');";

		query2 = query2.replace("$msisdn", msisdn).replace("$imsi", imsi).replace("$timestamp", date).replace("$lastId", "" + lastId)
				.replace("$catalog_offer_code", catalogOfferCode);
		System.out.println(query2);

		try {
			GalaxionDBUtil.runUpdateQuery(query2);
		} catch (SQLException e) {
			// e.printStackTrace();
		}
	}
}