package microservices.backend.eir_change_offers_backend.dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import microservices.backend.eir_change_offers_backend.data_model.ChangeOffer;
import utilities.generic.database.GalaxionDBUtil;
import utilities.generic.database.MariaDBConnection;
import utilities.test.config_readers.ExcelSQLManager;

public class ChangeOffersDAO {

	/**
	 * Get the record from the CHANGE_OFFER table based on uuid (order ref)
	 * 
	 * @param orderReference
	 * @return
	 */
	public static ChangeOffer getChangeOffer(String orderReference) {
		String query = ExcelSQLManager.getSQLQuery("CHANGE_OFFERS", "GET_CHANGE_OFFER");
		query = query.replace("$orderReference", orderReference);
		ResultSet rs;
		try {
			rs = GalaxionDBUtil.getQueryResultSet(query);
			while (rs.next()) {
				return new ChangeOffer(rs);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}

		return null;
	}

}
