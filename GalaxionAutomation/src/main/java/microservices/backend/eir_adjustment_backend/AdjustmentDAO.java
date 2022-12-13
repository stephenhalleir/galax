package microservices.backend.eir_adjustment_backend;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import microservices.backend.eir_adjustment_backend.data_model.Adjustment;
import microservices.backend.eir_adjustment_backend.data_model.AdjustmentFinancialType;
import microservices.backend.eir_adjustment_backend.data_model.CustomerCode;
import microservices.backend.eir_adjustment_backend.data_model.RefAdjustmentReason;
import utilities.generic.database.GalaxionDBUtil;
import utilities.test.config_readers.ExcelSQLManager;

public class AdjustmentDAO {

	public static Adjustment getAdjustmentByComment(String comment) {
		String query = ExcelSQLManager.getSQLQuery("ADJUSTMENT", "GET_ADJUSTMENT_BY_COMMENT");
		query = query.replace("$comment", comment);

		ResultSet rs;

		try {
			rs = GalaxionDBUtil.getQueryResultSet(query);

			if (rs.next()) {
				return new Adjustment(rs);
			}

		} catch (SQLException e) {
			e.printStackTrace();
		}

		return null;
	}

	public static void updateAgentUsernamesOnAdjustments(int billingAccountID, String newUsername) {
		String query = ExcelSQLManager.getSQLQuery("ADJUSTMENT", "UPDATE_AGENT_USERNAME");
		query = query.replace("$billingAccountID", Integer.toString(billingAccountID));
		query = query.replace("$newUsername", newUsername);

		try {
			GalaxionDBUtil.runUpdateQuery(query);
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	public static RefAdjustmentReason getRefAdjustmentReason(String reasonKey) {
		String query = ExcelSQLManager.getSQLQuery("ADJUSTMENT", "GET_REF_ADJUSTMENT_REASON");
		query = query.replace("$reasonKey", reasonKey);

		ResultSet rs;

		try {
			rs = GalaxionDBUtil.getQueryResultSet(query);

			if (rs.next()) {
				return new RefAdjustmentReason(rs);
			}

		} catch (SQLException e) {
			e.printStackTrace();
		}

		return null;
	}

	public static ArrayList<RefAdjustmentReason> getRefAdjustmentReasons() {

		ArrayList<RefAdjustmentReason> reasons = new ArrayList<RefAdjustmentReason>();

		String query = ExcelSQLManager.getSQLQuery("ADJUSTMENT", "GET_ALL_REF_ADJUSTMENT_REASONS");

		ResultSet rs;

		try {
			rs = GalaxionDBUtil.getQueryResultSet(query);

			while (rs.next()) {
				reasons.add(new RefAdjustmentReason(rs));
			}

		} catch (SQLException e) {
			e.printStackTrace();
		}

		return reasons;
	}

	public static ArrayList<Adjustment> getAdjustmentsForBillingAccountID(int billingAccountID) {

		ArrayList<Adjustment> adjustments = new ArrayList<Adjustment>();

		String query = ExcelSQLManager.getSQLQuery("ADJUSTMENT", "GET_ADJUSTMENTS_FOR_BILLING_ACCOUNT_ID");
		query = query.replace("$billingAccountID", Integer.toString(billingAccountID));

		ResultSet rs;

		try {
			rs = GalaxionDBUtil.getQueryResultSet(query);

			while (rs.next()) {
				adjustments.add(new Adjustment(rs));
			}

		} catch (SQLException e) {
			e.printStackTrace();
		}

		return adjustments;
	}
	
	public static ArrayList<Adjustment> getAdjustmentsForServiceID(int serviceID) {

		ArrayList<Adjustment> adjustments = new ArrayList<Adjustment>();

		String query = ExcelSQLManager.getSQLQuery("ADJUSTMENT", "GET_ADJUSTMENTS_FOR_SERVICE_ID");
		query = query.replace("$serviceID", Integer.toString(serviceID));

		ResultSet rs;

		try {
			rs = GalaxionDBUtil.getQueryResultSet(query);

			while (rs.next()) {
				adjustments.add(new Adjustment(rs));
			}

		} catch (SQLException e) {
			e.printStackTrace();
		}

		return adjustments;
	}
	
	public static ArrayList<Adjustment> getAdjustmentsForHardwareFundID(int hardwareFundID) {

		ArrayList<Adjustment> adjustments = new ArrayList<Adjustment>();

		String query = ExcelSQLManager.getSQLQuery("ADJUSTMENT", "GET_ADJUSTMENTS_FOR_HARDWARE_FUND_ID");
		query = query.replace("$hardwareFundID", Integer.toString(hardwareFundID));

		ResultSet rs;

		try {
			rs = GalaxionDBUtil.getQueryResultSet(query);

			while (rs.next()) {
				adjustments.add(new Adjustment(rs));
			}

		} catch (SQLException e) {
			e.printStackTrace();
		}

		return adjustments;
	}
}
