package microservices.backend.eir_barring_backend;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import microservices.backend.eir_barring_backend.data_model.custom.BarringDetail;
import microservices.backend.eir_barring_backend.enums.BarringType;
import utilities.generic.database.GalaxionDBUtil;
import utilities.test.config_readers.ExcelSQLManager;

public class BarringDAO {

	public static String getBarringStatus(int serviceID, BarringType barType, String timestamp) {
		String query = ExcelSQLManager.getSQLQuery("BARRING", "GET_BARRING_STATUS");
		query = query.replace("$serviceID", Integer.toString(serviceID));
		query = query.replace("$barringCode", barType.toString());

		if (timestamp != null) {
			query = query.replace("$startTime", timestamp);
		} else {
			query = query.replace("$startTime", "");
		}

		ResultSet rs;
		try {
			rs = GalaxionDBUtil.getQueryResultSet(query);
			if (rs.next()) {
				return rs.getString("status");
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}

		return null;

	}

	public static boolean isBarApplied(int serviceID, BarringType barType) {
		String barStatus = getBarringStatus(serviceID, barType, null);
		return barStatus != null && barStatus.equals("ACTIVE");
	}

	public static ArrayList<BarringDetail> getBarringDetail(int serviceID) {

		ArrayList<BarringDetail> barringDetails = new ArrayList<BarringDetail>();

		// get the barring details from the database
		String query = ExcelSQLManager.getSQLQuery("BARRING", "GET_BARRING_DETAILS");
		query = query.replace("$serviceID", Integer.toString(serviceID));

		ResultSet rs;

		try {
			rs = GalaxionDBUtil.getQueryResultSet(query);

			while (rs.next()) {
				barringDetails.add(new BarringDetail(rs));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}

		return barringDetails;
	}
}
