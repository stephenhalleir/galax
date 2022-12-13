package microservices.backend.eir_cdr_repository_backend.dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import microservices.backend.eir_cdr_repository_backend.data_model.ChargedUsageGoMoPostpay;
import microservices.backend.eir_cdr_repository_backend.data_model.MobileNonUsage;
import microservices.backend.eir_cdr_repository_backend.data_model.MobileNonUsageFile;
import microservices.backend.eir_cdr_repository_backend.data_model.MobileUsageFile;
import microservices.backend.eir_cdr_repository_backend.data_model.RatingSubtotal;
import microservices.backend.eir_cdr_repository_backend.data_model.RefRatingSubtotalType;
import microservices.backend.eir_cdr_repository_backend.data_model.ServiceDetail;
import microservices.backend.eir_cdr_repository_backend.data_model.UsagePartition;
import utilities.generic.database.GalaxionDBUtil;
import utilities.test.config_readers.ExcelSQLManager;

/**
 * CDRRepoDAO provides an interface into the cdr-repository-backend database
 * 
 * @author stephenhall
 *
 */
public class CDRRepoDAO {

	private static String tabName = "CDR_REPO";

	/**
	 * Retrieve the details from the cdr-repository-backend.MOBILE_USAGE_FILE table
	 * for a particular file name
	 * 
	 * @param filename
	 * @return the MobileUsageFile object
	 */
	public static MobileUsageFile getMobileUsageEventFile(String filename) {

		String query = ExcelSQLManager.getSQLQuery(tabName, "GET_MOBILE_USAGE_FILE");
		query = query.replace("$filename", filename);

		ResultSet rs;
		try {
			rs = GalaxionDBUtil.getQueryResultSet(query);
			if (rs.next()) {
				return new MobileUsageFile(rs);
			}

		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null;
	}

	/**
	 * Retrieve the details from the cdr-repository-backend.MOBILE_NON_USAGE_FILE
	 * table for a particular file name
	 * 
	 * @param filename
	 * @return the MobileNonUsageFile object
	 */
	public static MobileNonUsageFile getMobileNonUsageEventFile(String filename) {

		String query = ExcelSQLManager.getSQLQuery(tabName, "GET_MOBILE_NON_USAGE_FILE");
		query = query.replace("$filename", filename);

		MobileNonUsageFile file = null;

		ResultSet rs;
		try {
			rs = GalaxionDBUtil.getQueryResultSet(query);

			if (rs.next()) {
				return new MobileNonUsageFile(rs);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}

		return file;
	}

	/**
	 * Retrieve the details from the cdr-repository-backend.MOBILE_NON_USAGE table
	 * for a particular file id
	 * 
	 * @param non usage file ID
	 * @return list of MobileNonUsage objects
	 */
	public static ArrayList<MobileNonUsage> getMobileNonUsage(int file_id) {

		ArrayList<MobileNonUsage> nonUsages = new ArrayList<MobileNonUsage>();

		String query = ExcelSQLManager.getSQLQuery("CDR_REPO", "GET_MOBILE_NON_USAGE");
		query = query.replace("$file_id", Integer.toString(file_id));

		ResultSet rs;
		try {
			rs = GalaxionDBUtil.getQueryResultSet(query);

			while (rs.next()) {
				nonUsages.add(new MobileNonUsage(rs));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}

		return nonUsages;
	}

	public static ServiceDetail getServiceDetail(String msisdn) {
		String query = ExcelSQLManager.getSQLQuery("CDR_REPO", "GET_SERVICE_DETAIL");
		query = query.replace("$msisdn", msisdn);

		ResultSet rs;
		try {
			rs = GalaxionDBUtil.getQueryResultSet(query);

			if (rs.next()) {
				return new ServiceDetail(rs);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}

		return null;
	}

	/*
	public static ArrayList<RatingSubtotal> getRatfingSubtotals(int serviceID, int partitionID) {

		// read in the list of rating subtotal types
		ArrayList<RefRatingSubtotalType> types = getAllRatingSubtotalTypes();

		ArrayList<RatingSubtotal> subtotals = new ArrayList<RatingSubtotal>();
		String query = ExcelSQLManager.getSQLQuery("CDR_REPO", "GET_RATING_SUBTOTALS");
		query = query.replace("$serviceID", Integer.toString(serviceID));
		query = query.replace("$partitionID", Integer.toString(partitionID));

		ResultSet rs;
		try {
			rs = GalaxionDBUtil.getQueryResultSet(query);

			while (rs.next()) {
				RatingSubtotal subtotal = new RatingSubtotal(rs);

				// assign the relevant subtotal type
				for (RefRatingSubtotalType type : types) {
					if (type.getId() == subtotal.getRatingSubtotalTypeID()) {
						subtotal.setRatingSubtotalType(type);
					}
				}

				// add the subtotal to the list
				subtotals.add(subtotal);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}

		return subtotals;
	}
	*/

	public static ArrayList<RatingSubtotal> getRatingSubtotalsGoMo(int serviceID, int partitionID) {

		// read in the list of rating subtotal types
		ArrayList<RefRatingSubtotalType> types = getAllRatingSubtotalTypes();

		ArrayList<RatingSubtotal> subtotals = new ArrayList<RatingSubtotal>();
		String query = ExcelSQLManager.getSQLQuery("CDR_REPO", "GET_RATING_SUBTOTALS_GOMO");
		query = query.replace("$serviceID", Integer.toString(serviceID));
		query = query.replace("$partitionID", Integer.toString(partitionID));

		ResultSet rs;
		try {
			rs = GalaxionDBUtil.getQueryResultSet(query);

			while (rs.next()) {
				RatingSubtotal subtotal = new RatingSubtotal(rs);

				// assign the relevant subtotal type
				for (RefRatingSubtotalType type : types) {
					if (type.getId() == subtotal.getRatingSubtotalTypeID()) {
						subtotal.setRatingSubtotalType(type);
					}
				}

				// add the subtotal to the list
				subtotals.add(subtotal);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}

		return subtotals;
	}

	public static RefRatingSubtotalType getRatingSubtotalType(int id) {
		String query = ExcelSQLManager.getSQLQuery("CDR_REPO", "GET_REF_SUBTOTAL_TYPE");
		query = query.replace("$id", Integer.toString(id));

		ResultSet rs;
		try {
			rs = GalaxionDBUtil.getQueryResultSet(query);

			if (rs.next()) {
				return new RefRatingSubtotalType(rs);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}

		return null;
	}

	/**
	 * Get all rows from the Ref_Rating_Subtotal_Type table
	 * 
	 * @return list
	 */
	public static ArrayList<RefRatingSubtotalType> getAllRatingSubtotalTypes() {

		ArrayList<RefRatingSubtotalType> subtotalTypes = new ArrayList<RefRatingSubtotalType>();

		String query = ExcelSQLManager.getSQLQuery("CDR_REPO", "GET_REF_SUBTOTAL_TYPES");

		ResultSet rs;
		try {
			rs = GalaxionDBUtil.getQueryResultSet(query);

			while (rs.next()) {
				subtotalTypes.add(new RefRatingSubtotalType(rs));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}

		return subtotalTypes;
	}

	public static ArrayList<ChargedUsageGoMoPostpay> getChargedUsage(String msisdn, int partitionID) {
		ArrayList<ChargedUsageGoMoPostpay> usageList = new ArrayList<ChargedUsageGoMoPostpay>();

		String query = ExcelSQLManager.getSQLQuery("CDR_REPO", "GET_CHARGED_USAGE");
		query = query.replace("$msisdn", msisdn);
		query = query.replace("$partitionID", Integer.toString(partitionID));

		ResultSet rs;
		try {
			rs = GalaxionDBUtil.getQueryResultSet(query);

			while (rs.next()) {
				usageList.add(new ChargedUsageGoMoPostpay(rs));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}

		return usageList;
	}

	public static ArrayList<ChargedUsageGoMoPostpay> getChargedUsageGoMo(String msisdn, int partitionID) {
		ArrayList<ChargedUsageGoMoPostpay> usageList = new ArrayList<ChargedUsageGoMoPostpay>();

		String query = ExcelSQLManager.getSQLQuery("CDR_REPO", "GET_CHARGED_USAGE_GOMO");
		query = query.replace("$msisdn", msisdn);
		query = query.replace("$partitionID", Integer.toString(partitionID));

		ResultSet rs;
		try {
			rs = GalaxionDBUtil.getQueryResultSet(query);

			while (rs.next()) {
				usageList.add(new ChargedUsageGoMoPostpay(rs));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}

		return usageList;
	}

	public static UsagePartition getUsagePartition(String billingPeriod, String billCycle) {

		String query = ExcelSQLManager.getSQLQuery("CDR_REPO", "GET_USAGE_PARTITION");
		query = query.replace("$billingPeriod", billingPeriod);
		query = query.replace("$billCycle", billCycle);

		ResultSet rs;
		try {
			rs = GalaxionDBUtil.getQueryResultSet(query);

			while (rs.next()) {
				return new UsagePartition(rs);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}

		return null;
	}
}
