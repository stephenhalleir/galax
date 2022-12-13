package microservices.backend.eir_catalog_core_backend.dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import microservices.backend.eir_catalog_core_backend.data_model.AddonBundle;
import microservices.backend.eir_catalog_core_backend.data_model.Channel;
import microservices.backend.eir_catalog_core_backend.data_model.Equipment;
import microservices.backend.eir_catalog_core_backend.data_model.NetworkElement;
import microservices.backend.eir_catalog_core_backend.data_model.Offer;
import microservices.backend.eir_catalog_core_backend.data_model.PricePlan;
import microservices.backend.eir_catalog_core_backend.data_model.SimCard;
import microservices.backend.eir_catalog_core_backend.data_model.TariffPlan;
import microservices.backend.eir_catalog_core_backend.data_model.UsageQuota;
import microservices.backend.eir_catalog_core_backend.enums.B2BAccountType;
import microservices.backend.eir_catalog_core_backend.enums.OfferCode;
import microservices.backend.eir_catalog_core_backend.enums.SubscriptionAddonBundle;
import microservices.backend.eir_logistics_backend.enums.CatalogItemType;
import utilities.generic.database.GalaxionDBUtil;
import utilities.test.config_readers.ExcelSQLManager;

/**
 * The PcatDAO class provides an interface into the catalog-core database
 *
 * @author Stephen Hall
 * @version 1.0
 * @since 22/03/2021
 */

public class CatalogCoreDAO {

	/**
	 * This method retrieves a Tariff Plan object from catalog-core.tariff_plan
	 * based on a specified code - e.g. "GOVALLIN10GB-C"
	 * 
	 * @param tariffCode
	 * @return a TariffPlan object
	 */
	public static TariffPlan getTariffPlan(String tariffCode) {

		tariffCode = tariffCode.replace("_", "-");

		// create an empty object
		TariffPlan plan = new TariffPlan();

		// populate the SQL query
		String query = ExcelSQLManager.getSQLQuery("CATALOG_CORE", "GET_TARIFF_PLAN");
		query = query.replace("$tariffCode", tariffCode);

		try {

			// retrieve the result set
			ResultSet rs = GalaxionDBUtil.getQueryResultSet(query);

			if (rs.next()) {
				// populate the object from the result set
				plan.setCode(rs.getString("code"));
				plan.setDescription(rs.getString("description"));
				plan.setOfferCode(rs.getString("offer_code"));
				plan.setSimOnly(rs.getInt("sim_only"));
				plan.setCommitmentDuration(rs.getString("commitment_duration"));
				plan.setDiscountCode(rs.getString("discount_code"));
				plan.setDefaultSubsidy(rs.getInt("default_subsidy"));
			}
		} catch (SQLException e) {
			e.printStackTrace();
			return null;
		}

		// return the object
		return plan;
	}

	public static ArrayList<String> getB2BOfferCodes() {

		ArrayList<String> b2bOfferCodes = new ArrayList<String>();

		// populate the SQL query
		String query = ExcelSQLManager.getSQLQuery("CATALOG_CORE", "GET_B2B_OFFERS");

		try {

			// retrieve the result set
			ResultSet rs = GalaxionDBUtil.getQueryResultSet(query);

			while (rs.next()) {
				b2bOfferCodes.add(rs.getString("code"));
			}
		} catch (SQLException e) {
			e.printStackTrace();
			return null;
		}

		return b2bOfferCodes;
	}

	public static ArrayList<TariffPlan> getB2BTariffCodes() {

		ArrayList<TariffPlan> b2bOfferCodes = new ArrayList<TariffPlan>();

		// populate the SQL query
		String query = ExcelSQLManager.getSQLQuery("CATALOG_CORE", "GET_B2B_TARIFFS");

		try {

			// retrieve the result set
			ResultSet rs = GalaxionDBUtil.getQueryResultSet(query);

			while (rs.next()) {
				TariffPlan plan = new TariffPlan();
				plan.setOfferCode(rs.getString("offer_code"));
				plan.setCode(rs.getString("code"));
				plan.setCustomerTypeCode(rs.getString("customer_type_code"));
				b2bOfferCodes.add(plan);
			}
		} catch (SQLException e) {
			e.printStackTrace();
			return null;
		}

		return b2bOfferCodes;
	}

	public static ArrayList<Offer> getAllOffers() {

		ArrayList<Offer> offers = new ArrayList<Offer>();

		// populate the SQL query
		String query = ExcelSQLManager.getSQLQuery("CATALOG_CORE", "GET_ALL_OFFERS");

		try {

			// retrieve the result set
			ResultSet rs = GalaxionDBUtil.getQueryResultSet(query);

			while (rs.next()) {
				offers.add(new Offer(rs));
			}
		} catch (SQLException e) {
			e.printStackTrace();
			return null;
		}

		return offers;
	}

	public static ArrayList<TariffPlan> getB2BTariffs() {

		ArrayList<TariffPlan> b2bTariffs = new ArrayList<TariffPlan>();

		// populate the SQL query
		String query = ExcelSQLManager.getSQLQuery("CATALOG_CORE", "GET_B2B_TARIFFS");

		try {

			// retrieve the result set
			ResultSet rs = GalaxionDBUtil.getQueryResultSet(query);

			while (rs.next()) {
				TariffPlan plan = new TariffPlan();
				plan.setOfferCode(rs.getString("offer_code"));
				plan.setCode(rs.getString("code"));
				plan.setCustomerTypeCode(rs.getString("customer_type_code"));
				b2bTariffs.add(plan);
			}
		} catch (SQLException e) {
			e.printStackTrace();
			return null;
		}

		return b2bTariffs;
	}

	/**
	 * Filter a tariff list by the offer type (GOV or CORP)
	 * 
	 * @param fullTariffList
	 * @param type
	 * @return
	 */
	public static ArrayList<TariffPlan> getFilteredB2BTariffList(ArrayList<TariffPlan> fullTariffList, B2BAccountType type) {

		ArrayList<TariffPlan> subset = new ArrayList<TariffPlan>();

		for (TariffPlan plan : fullTariffList) {
			if (plan.getCustomerTypeCode().equalsIgnoreCase(type.toString())) {
				subset.add(plan);
			}
		}

		return subset;
	}

	/**
	 * Retrieve the list of B2B offers with 1 tariff plan per offer
	 * 
	 * @return
	 */
	public static ArrayList<TariffPlan> getDistinctOffers() {

		ArrayList<TariffPlan> b2bTariffs = new ArrayList<TariffPlan>();

		// populate the SQL query
		String query = ExcelSQLManager.getSQLQuery("CATALOG_CORE", "GET_B2B_OFFERS_WITH_1_TARIFF_EACH");

		try {

			// retrieve the result set
			ResultSet rs = GalaxionDBUtil.getQueryResultSet(query);

			while (rs.next()) {
				TariffPlan plan = new TariffPlan();
				plan.setOfferCode(rs.getString("offer_code"));
				plan.setCode(rs.getString("code"));
				plan.setCustomerTypeCode(rs.getString("customer_type_code"));
				b2bTariffs.add(plan);
			}
		} catch (SQLException e) {
			e.printStackTrace();
			return null;
		}

		return b2bTariffs;
	}

	public static ArrayList<TariffPlan> getB2BCorporateOffers() {
		ArrayList<TariffPlan> allOfferCodes = getDistinctOffers();
		ArrayList<TariffPlan> subset = getFilteredB2BTariffList(allOfferCodes, B2BAccountType.CORPORATE_OR_EBU);
		return subset;
	}

	public static ArrayList<TariffPlan> getB2BGovernmentOffers() {
		ArrayList<TariffPlan> allOfferCodes = getDistinctOffers();
		ArrayList<TariffPlan> subset = getFilteredB2BTariffList(allOfferCodes, B2BAccountType.GOVERNMENT);
		return subset;
	}

	public static ArrayList<TariffPlan> getB2BCorporateTariffs() {
		ArrayList<TariffPlan> allTariffCodes = getB2BTariffCodes();
		ArrayList<TariffPlan> subset = getFilteredB2BTariffList(allTariffCodes, B2BAccountType.CORPORATE_OR_EBU);
		return subset;
	}

	public static ArrayList<TariffPlan> getB2BGovernmentTariffs() {
		ArrayList<TariffPlan> allTariffCodes = getDistinctOffers();
		ArrayList<TariffPlan> subset = getFilteredB2BTariffList(allTariffCodes, B2BAccountType.GOVERNMENT);
		return subset;
	}

	public static String getSIMCardInventoryCodeByID(int id) {
		String query = ExcelSQLManager.getSQLQuery("CATALOG_CORE", "GET_SIM_CARD");
		query = query.replace("$id", Integer.toString(id));

		try {

			// retrieve the result set
			ResultSet rs = GalaxionDBUtil.getQueryResultSet(query);

			if (rs.next()) {
				return rs.getString("inventory_code");
			}
		} catch (SQLException e) {
			e.printStackTrace();
			return null;
		}

		return null;
	}

	public static String getEquipmentByID(int id) {
		String query = ExcelSQLManager.getSQLQuery("CATALOG_CORE", "GET_EQUIPMENT");
		query = query.replace("$id", Integer.toString(id));

		try {

			// retrieve the result set
			ResultSet rs = GalaxionDBUtil.getQueryResultSet(query);

			if (rs.next()) {
				return rs.getString("inventory_code");
			}
		} catch (SQLException e) {
			e.printStackTrace();
			return null;
		}

		return null;
	}

	public static CatalogItemType getCatalogItemType(int id) {
		if (getSIMCardInventoryCodeByID(id) != null) {
			return CatalogItemType.SIM_CARD;
		} else if (getEquipmentByID(id) != null) {
			return CatalogItemType.HANDSET;
		} else {
			return null;
		}
	}

	

	public static Offer getOffer(int id) {

		String query = ExcelSQLManager.getSQLQuery("CATALOG_CORE", "GET_OFFER_BY_ID");
		query = query.replace("$id", Integer.toString(id));

		ResultSet rs;
		try {
			rs = GalaxionDBUtil.getQueryResultSet(query);
			if (rs.next()) {
				Offer offer = new Offer();
				offer.setCode(rs.getString("code"));
				offer.setId(rs.getInt("id"));
				offer.setDescription(rs.getString("description"));
				offer.setStatus(rs.getString("status"));
				offer.setInvoiceDescription(rs.getString("invoice_description"));
				offer.setInvoiceType(rs.getString("invoice_type"));
				offer.setComment(rs.getString("comment"));
				return offer;
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}

		return null;
	}

	

	/**
	 * Get the addon bundle ID from the addon_bundle table using the code
	 * 
	 * @param code - addon code (e.g. 10GBDATA)
	 * @return the addon bundle ID
	 */
	public static int getAddonBundleIDForCode(String code) {
		AddonBundle a = getAddonBundleForCode(SubscriptionAddonBundle.valueOf(code));
		return a.getId();
	}

	public static AddonBundle getAddonBundleForCode(SubscriptionAddonBundle code) {
		String query = ExcelSQLManager.getSQLQuery("CATALOG_CORE", "GET_ADDON_BY_CODE");
		query = query.replace("$code", code.toString());

		ResultSet rs;

		try {
			rs = GalaxionDBUtil.getQueryResultSet(query);

			if (rs.next()) {
				AddonBundle bundle = new AddonBundle();
				bundle.setId(rs.getInt("id"));
				bundle.setDescription(rs.getString("description"));
				bundle.setItemGroupCode(rs.getString("item_group_code"));
				bundle.setCode(rs.getString("code"));
				bundle.setLevel(rs.getString("level"));
				return bundle;
			}
		} catch (SQLException e) {
			return null;
		}

		return null;
	}

	

	

	

	

	

	
	
	

	public static String getOfferDescriptionByCode(String code) {
		String query = ExcelSQLManager.getSQLQuery("CATALOG_CORE", "GET_USAGE_BY_USAGE_CODE");
		query = query.replace("$code", code);

		try {

			// retrieve the result set
			ResultSet rs = GalaxionDBUtil.getQueryResultSet(query);

			if (rs.next()) {
				return rs.getString("description");
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}

		return null;
	}


	public static String getTariffForOfferCode(String offerCode) {
		String query = ExcelSQLManager.getSQLQuery("CATALOG_CORE", "GET_TARIFF_PLAN_FOR_OFFER");
		query = query.replace("$offerCode", offerCode);
		ResultSet rs;
		try {
			rs = GalaxionDBUtil.getQueryResultSet(query);
			if (rs.next()) {
				return rs.getString("code");
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null;
	}

	public static String getEquipmentModelByInventoryCode(String inventoryCode) {
		String query = ExcelSQLManager.getSQLQuery("CATALOG_CORE", "GET_EQUIPMENT_BY_INVENTORY_CODE");
		query = query.replace("$inventoryCode", inventoryCode);

		ResultSet rs;
		try {
			rs = GalaxionDBUtil.getQueryResultSet(query);
			if (rs.next()) {
				return rs.getString("model");
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null;
	}

	/**
	 * Return addon bundle details from catalog-core.addon_bundle where the id is
	 * within a range
	 * 
	 * @param startId
	 * @param endID
	 * @return a list of addon_bundle objects
	 */
	public static ArrayList<AddonBundle> getAddonBundlesBetween(int startId, int endID) {

		ArrayList<AddonBundle> bundles = new ArrayList<AddonBundle>();

		String query = ExcelSQLManager.getSQLQuery("CATALOG_CORE", "GET_ADDON_BUNDLES_IN_RANGE");
		query = query.replace("$startID", Integer.toString(startId));
		query = query.replace("$endID", Integer.toString(endID));

		ResultSet rs;
		try {
			rs = GalaxionDBUtil.getQueryResultSet(query);
			while (rs.next()) {
				AddonBundle bundle = new AddonBundle();
				bundle.setId(rs.getInt("id"));
				bundle.setCode(rs.getString("code"));
				bundle.setDescription(rs.getString("description"));
				bundles.add(bundle);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}

		return bundles;
	}

	public static Offer getOfferByCode(String code) {

		String query = ExcelSQLManager.getSQLQuery("CATALOG_CORE", "GET_OFFER_BY_CODE");
		query = query.replace("$code", code);

		ResultSet rs;
		try {
			rs = GalaxionDBUtil.getQueryResultSet(query);
			if (rs.next()) {
				Offer offer = new Offer();
				offer.setCode(rs.getString("code"));
				offer.setId(rs.getInt("id"));
				offer.setDescription(rs.getString("description"));
				offer.setStatus(rs.getString("status"));
				offer.setInvoiceDescription(rs.getString("invoice_description"));
				offer.setInvoiceType(rs.getString("invoice_type"));
				offer.setComment(rs.getString("comment"));
				return offer;
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}

		return null;
	}

	
	

	public static SimCard getSimCardById(int simCardId) {
		
		String query = ExcelSQLManager.getSQLQuery("CATALOG_CORE", "GET_SIM_CARD_BY_ID");
		query = query.replace("$id", Integer.toString(simCardId));

		ResultSet rs;
		try {
			rs = GalaxionDBUtil.getQueryResultSet(query);
			while (rs.next()) {
				return new SimCard(rs);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}

		return null;
	}
	
	public static ArrayList<Channel> getAllChannels() {

		ArrayList<Channel> channels = new ArrayList<Channel>();
		String query = ExcelSQLManager.getSQLQuery("CATALOG_CORE", "GET_ALL_CHANNELS");

		ResultSet rs;
		try {
			rs = GalaxionDBUtil.getQueryResultSet(query);
			while (rs.next()) {
				channels.add(new Channel(rs));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}

		return channels;
	}

	public static ArrayList<String> getAllChannelGroups() {

		ArrayList<String> channelGroups = new ArrayList<String>();
		ArrayList<Channel> channels = getAllChannels();

		for (Channel channel : channels) {
			if (!channelGroups.contains(channel.getChannelGroup())) {
				channelGroups.add(channel.getChannelGroup());
			}
		}

		return channelGroups;
	}
	
	public static PricePlan getPricePlan(String code) {
		String query = ExcelSQLManager.getSQLQuery("CATALOG_CORE", "GET_PRICE_PLAN");
		query = query.replace("$code", code);

		ResultSet rs;
		try {
			rs = GalaxionDBUtil.getQueryResultSet(query);
			while (rs.next()) {
				return new PricePlan(rs);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}

		return null;
	}
	
	public static Equipment getRandomHandset() {
		String query = ExcelSQLManager.getSQLQuery("CATALOG_CORE", "GET_RANDOM_HANDSET");

		ResultSet rs;
		try {
			rs = GalaxionDBUtil.getQueryResultSet(query);
			while (rs.next()) {
				return new Equipment(rs);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}

		return null;
	}

}
