package microservices.backend.eir_msisdn_swap_backend.dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import microservices.backend.eir_msisdn_swap_backend.data_model.MsisdnSwapRequest;
import utilities.generic.database.GalaxionDBUtil;
import utilities.test.config_readers.ExcelSQLManager;

public class MsisdnSwapDAO {

	private static String tabName = "MSISDN_SWAP";

	public static MsisdnSwapRequest getMsisdnSwap(int serviceId, String newMsisdn) {
		String query = ExcelSQLManager.getSQLQuery(tabName, "GET_MSISDN_SWAP");
		query = query.replace("$serviceId", Integer.toString(serviceId));
		query = query.replace("$msisdn", newMsisdn);
		ResultSet rs;

		try {
			rs = GalaxionDBUtil.getQueryResultSet(query);
			if (rs.next()) {
				return new MsisdnSwapRequest(rs);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}

		return null;
	}
	
	/**
	 * Get a specific MSISDN swap erquest from msisdn-swap-backend.msisdn_swap_request
	 * @param requestId
	 * @return request
	 */
	public static MsisdnSwapRequest getMsisdnSwap(int requestId) {
		String query = ExcelSQLManager.getSQLQuery(tabName, "GET_MSISDN_SWAP_BY_ID");
		query = query.replace("$requestId", Integer.toString(requestId));
		ResultSet rs;

		try {
			rs = GalaxionDBUtil.getQueryResultSet(query);
			if (rs.next()) {
				return new MsisdnSwapRequest(rs);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}

		return null;
	}
	
	/**
	 * Get the list of MSISDN swap requests from msisdn-swap-backend.msisdn_swap_request
	 * @param serviceId
	 * @return list of requests
	 */
	public static ArrayList<MsisdnSwapRequest> getMsisdnSwaps(int serviceId) {
		
		ArrayList<MsisdnSwapRequest> msisdnSwaps = new ArrayList<MsisdnSwapRequest>();
		
		String query = ExcelSQLManager.getSQLQuery(tabName, "GET_MSISDN_SWAPS_FOR_SERVICE");
		query = query.replace("$serviceId", Integer.toString(serviceId));
		ResultSet rs;

		try {
			rs = GalaxionDBUtil.getQueryResultSet(query);
			if (rs.next()) {
				msisdnSwaps.add(new MsisdnSwapRequest(rs));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}

		return msisdnSwaps;
	}
}
