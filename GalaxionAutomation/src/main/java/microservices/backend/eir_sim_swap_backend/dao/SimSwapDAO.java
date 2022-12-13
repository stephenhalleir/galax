package microservices.backend.eir_sim_swap_backend.dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import microservices.backend.eir_sim_swap_backend.data_model.Charge;
import microservices.backend.eir_sim_swap_backend.data_model.SimSwapRequest;
import utilities.generic.database.GalaxionDBUtil;
import utilities.test.config_readers.ExcelSQLManager;

public class SimSwapDAO {

	private static String tabName = "SIM_SWAP";

	/**
	 * Get all records from the SIM_SWAP_REQUEST table based on a service ID
	 * 
	 * @param serviceId
	 * @return
	 */
	public static ArrayList<SimSwapRequest> getSimSwapRequests(int serviceId) {
		ArrayList<SimSwapRequest> requests = new ArrayList<SimSwapRequest>();

		String query = ExcelSQLManager.getSQLQuery(tabName, "GET_SIM_SWAP_REQUEST");
		query = query.replace("$serviceId", Integer.toString(serviceId));

		ResultSet rs;

		try {
			rs = GalaxionDBUtil.getQueryResultSet(query);

			while (rs.next()) {
				requests.add(new SimSwapRequest(rs));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}

		return requests;
	}
	
	public static SimSwapRequest getSimSwapRequest(int simSwapRequestId) {

		String query = ExcelSQLManager.getSQLQuery(tabName, "GET_SIM_SWAP_REQUEST_BY_ID");
		query = query.replace("$id", Integer.toString(simSwapRequestId));

		ResultSet rs;

		try {
			rs = GalaxionDBUtil.getQueryResultSet(query);

			while (rs.next()) {
				return new SimSwapRequest(rs);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}

		return null;
	}

	public static Charge getSimSwapCharge(int simSwapRequestId) {

		String query = ExcelSQLManager.getSQLQuery(tabName, "GET_CHARGE");
		query = query.replace("$simSwapRequestId", Integer.toString(simSwapRequestId));

		ResultSet rs;

		try {
			rs = GalaxionDBUtil.getQueryResultSet(query);

			while (rs.next()) {
				return new Charge(rs);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}

		return null;
	}

	public static void markSIMSwapsAsDone(int serviceId) {

		String query = ExcelSQLManager.getSQLQuery(tabName, "CLEANUP_PENDING_REQUESTS");
		System.err.println(query + ", " + serviceId);
		query = query.replace("$serviceId", Integer.toString(serviceId));

		try {
			GalaxionDBUtil.runUpdateQuery(query);
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
}
