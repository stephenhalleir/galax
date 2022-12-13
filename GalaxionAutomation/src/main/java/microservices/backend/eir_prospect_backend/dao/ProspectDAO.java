package microservices.backend.eir_prospect_backend.dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import microservices.backend.eir_prospect_backend.data_model.ProspectAddonBundle;
import microservices.backend.eir_prospect_backend.data_model.Prospect;
import microservices.backend.eir_prospect_backend.data_model.ProspectOffer;
import microservices.backend.eir_prospect_backend.data_model.Sim;
import utilities.generic.database.GalaxionDBUtil;
import utilities.generic.database.MariaDBConnection;
import utilities.test.config_readers.ExcelSQLManager;

public class ProspectDAO {

	public static Prospect getProspect(String prospectUuid) {
		String query = ExcelSQLManager.getSQLQuery("PROSPECT", "GET_PROSPECT");
		query = query.replace("$prospectUuid", prospectUuid);

		ResultSet rs;

		try {
			rs = GalaxionDBUtil.getQueryResultSet(query);

			if (rs.next()) {
				return new Prospect(rs);
			}

		} catch (SQLException e) {
			e.printStackTrace();
		}

		return null;
	}
	
	public static Sim getSim(int id) {
		String query = ExcelSQLManager.getSQLQuery("PROSPECT", "GET_SIM");
		query = query.replace("$id", Integer.toString(id));

		ResultSet rs;

		try {
			rs = GalaxionDBUtil.getQueryResultSet(query);

			if (rs.next()) {
				return new Sim(rs);
			}

		} catch (SQLException e) {
			e.printStackTrace();
		}

		return null;
	}
	
	
	public static ArrayList<ProspectOffer> getOffersForProspect(int prospectId) {
		
		 ArrayList<ProspectOffer> offers = new ArrayList<ProspectOffer>();
		
		String query = ExcelSQLManager.getSQLQuery("PROSPECT", "GET_OFFERS_IN_CART");
		query = query.replace("$prospectId", Integer.toString(prospectId));

		ResultSet rs;

		try {
			rs = GalaxionDBUtil.getQueryResultSet(query);

			while (rs.next()) {
				offers.add(new ProspectOffer(rs));
			}

		} catch (SQLException e) {
			e.printStackTrace();
		}

		return offers;
	}
	
	public static ArrayList<ProspectAddonBundle> getAddonBundlesOnOffer(int offerId) {
		
		 ArrayList<ProspectAddonBundle> addons = new ArrayList<ProspectAddonBundle>();
		
		String query = ExcelSQLManager.getSQLQuery("PROSPECT", "GET_ADDONS_ON_OFFER");
		query = query.replace("$offerID", Integer.toString(offerId));

		ResultSet rs;

		try {
			rs = GalaxionDBUtil.getQueryResultSet(query);

			while (rs.next()) {
				addons.add(new ProspectAddonBundle(rs));
			}

		} catch (SQLException e) {
			e.printStackTrace();
		}

		return addons;
	}
	
	public static ProspectOffer getOffer(int offerId) {
		
		String query = ExcelSQLManager.getSQLQuery("PROSPECT", "GET_OFFER");
		query = query.replace("$offerID", Integer.toString(offerId));

		ResultSet rs;

		try {
			rs = GalaxionDBUtil.getQueryResultSet(query);
			if(rs.next()) {
				return new ProspectOffer(rs);
			}

		} catch (SQLException e) {
			e.printStackTrace();
		}

		return null;
	}

}
