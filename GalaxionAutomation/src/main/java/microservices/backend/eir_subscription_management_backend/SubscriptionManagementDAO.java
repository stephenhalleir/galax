package microservices.backend.eir_subscription_management_backend;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;

import external_systems.device_enrollment.enums.Provider;
import microservices.backend.eir_catalog_core_backend.enums.Brand;
import microservices.backend.eir_catalog_core_backend.enums.OfferCode;
import microservices.backend.eir_cdr_repository_backend.dao.CDRRepoDAO;
import microservices.backend.eir_cdr_repository_backend.data_model.ServiceDetail;
import microservices.backend.eir_subscription_management_backend.data_model.Account;
import microservices.backend.eir_subscription_management_backend.data_model.AccountContact;
import microservices.backend.eir_subscription_management_backend.data_model.AccountSubscription;
import microservices.backend.eir_subscription_management_backend.data_model.AddonBundle;
import microservices.backend.eir_subscription_management_backend.data_model.B2BAccount;
import microservices.backend.eir_subscription_management_backend.data_model.B2BAccountAttribute;
import microservices.backend.eir_subscription_management_backend.data_model.Company;
import microservices.backend.eir_subscription_management_backend.data_model.Contract;
import microservices.backend.eir_subscription_management_backend.data_model.DeviceEnrollment;
import microservices.backend.eir_subscription_management_backend.data_model.NetworkElement;
import microservices.backend.eir_subscription_management_backend.data_model.RefAccountCategory;
import microservices.backend.eir_subscription_management_backend.data_model.RefBillingCycle;
import microservices.backend.eir_subscription_management_backend.data_model.Service;
import microservices.backend.eir_subscription_management_backend.data_model.SimCard;
import microservices.backend.eir_subscription_management_backend.data_model.Subscription;
import microservices.backend.eir_subscription_management_backend.data_model.TaxDetails;
import microservices.backend.eir_subscription_management_backend.data_model.UsageQuota;
import microservices.backend.eir_subscription_management_backend.data_model.custom.B2BAccountAttributeSet;
import microservices.backend.eir_subscription_management_backend.data_model.custom.BillingDetailsSet;
import microservices.backend.eir_subscription_management_backend.data_model.custom.DeviceEnrollmentSet;
import microservices.backend.eir_subscription_management_backend.data_model.custom.SubscriptionServicePair;
import microservices.backend.eir_subscription_management_backend.enums.NDDSetting;
import microservices.frontend.common_ui.enums.CustomerType;
import pojo_repo.eir_subscription_management_backend.RefContactType;
import utilities.generic.database.GalaxionDBUtil;
import utilities.generic.time.Timestamp;
import utilities.generic.time.WaitUtil;
import utilities.test.config_readers.ExcelSQLManager;

/**
 * SubscriptionManagementDAO provides an interface into the
 * subscription-management-backend database
 * 
 * @author stephenhall
 *
 */
public class SubscriptionManagementDAO {

	/**
	 * Retrieve a billing account ID number for a specific MSISDN
	 * 
	 * @param msisdn
	 * @return billing account ID
	 */
	public static int getBillingAccountIDForMsisdn(String msisdn) {

		// read the SERVICE object based on msisdn
		Service service = getServiceByMSISDN(msisdn);
		
		Subscription subscription = getSubscriptionByID(service.getSubscriptionID());

		// read the details of the ACCOUNT
		Account acc = getAccountByID(subscription.getAccountId());

		return acc.getBillingAccountID();
	}

	/**
	 * Retrieve the Account object based on an account ID The Account object related
	 * to subs-management.ACCOUNT table
	 * 
	 * @param accountID
	 * @return the Account object
	 */
	public static Account getAccountByID(int accountID) {

		// build the query
		String query = ExcelSQLManager.getSQLQuery("SUBSCRIPTION_MANAGEMENT", "GET_ACCOUNT_BY_ACCOUNT_ID");
		query = query.replace("$id", Integer.toString(accountID));

		Account account = null;

		try {
			// retrieve the result from the database
			ResultSet rs = GalaxionDBUtil.getQueryResultSet(query);
			if (rs.next()) {
				account = new Account(rs);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}

		// return the company object
		return account;
	}

	public static Account getAccountByBillingAccountID(int billingAccountID) {
		String query = ExcelSQLManager.getSQLQuery("SUBSCRIPTION_MANAGEMENT", "GET_ACCOUNT_BY_BILLING_ACCOUNT_ID");
		query = query.replace("$billingAccountID", Integer.toString(billingAccountID));

		ResultSet rs;
		try {
			rs = GalaxionDBUtil.getQueryResultSet(query);
			if (rs.next()) {
				return new Account(rs);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null;
	}

	/**
	 * Retrieve the offer code linked to a subscription
	 * 
	 * @param msisdn
	 * @return offer code (e.g. GOMO3)
	 */
	public static String getOfferCodeForMsisdn(String msisdn) {
		Service service = SubscriptionManagementDAO.getServiceByMSISDN(msisdn);
		Subscription subscription = SubscriptionManagementDAO.getSubscriptionByID(service.getSubscriptionID());
		return subscription.getCatalogOfferCode();
	}

	/**
	 * Retrieve the status of a service
	 * 
	 * @param msisdn
	 * @return status
	 */
	public static String getServiceStatus(String msisdn) {
		Service service = SubscriptionManagementDAO.getServiceByMSISDN(msisdn);
		Subscription subscription = SubscriptionManagementDAO.getSubscriptionByID(service.getSubscriptionID());
		return subscription.getStatus();
	}

	/**
	 * Retrieve the account ID for a billing account
	 * 
	 * @param billingAccountID
	 * @return account.id
	 */
	public static int getAccountIDForBillingAccountID(int billingAccountID) {
		Account acc = getAccountByBillingAccountID(billingAccountID);
		return acc.getId();
	}

	/**
	 * Retrieve the billing account ID for an account ID
	 * 
	 * @param account ID
	 * @return billing account ID
	 */
	public static int getBillingAccountIDForAccountID(int accountID) {
		Account acc = SubscriptionManagementDAO.getAccountByID(accountID);
		return acc.getBillingAccountID();
	}

	/**
	 * Retrieve the contact_uuid for an account ID
	 * 
	 * @param account ID
	 * @return billing account ID
	 */
	public static String getContactUuidForAccountID(int accountID) {

		int billingAccountID = getBillingAccountIDForAccountID(accountID);
		ArrayList<AccountContact> contacts = SubscriptionManagementDAO.getAllAccountContacts(billingAccountID);
		for (AccountContact contact : contacts) {
			if (contact.getTypeId() == 1 && contact.getEndedAt() == null) {
				return contact.getUuid();
			}
		}
		return null;
	}

	public static String getContactOfType(int accountID, int typeID) {
		int billingAccountID = getBillingAccountIDForAccountID(accountID);
		ArrayList<AccountContact> contacts = SubscriptionManagementDAO.getAllAccountContacts(billingAccountID);
		for (AccountContact contact : contacts) {
			if (contact.getTypeId() == typeID && contact.getEndedAt() == null) {
				return contact.getUuid();
			}
		}
		return null;
	}

	public static String getContactUuidForBillingAccountID(int billingAccountID) {
		String query = ExcelSQLManager.getSQLQuery("SUBSCRIPTION_MANAGEMENT", "GET_CONTACT_UUID_BY_BILLING_ACCOUNT_ID").replace("$billingAccountID",
				Integer.toString(billingAccountID));

		try {

			// retrieve the result set
			ResultSet rs = GalaxionDBUtil.getQueryResultSet(query);

			if (rs.next()) {
				return rs.getString("uuid");
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}

		return null;
	}

	public static Service getServiceByMSISDN(String msisdn) {
		String query = ExcelSQLManager.getSQLQuery("SUBSCRIPTION_MANAGEMENT", "GET_SERVICE_BY_MSISDN");
		query = query.replace("$msisdn", msisdn);

		ResultSet rs;

		try {
			rs = GalaxionDBUtil.getQueryResultSet(query);

			if (rs.next()) {
				return new Service(rs);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}

		return null;
	}

	/**
	 * Retrieve the service ID for a given MSISDN
	 * 
	 * @param msisdn
	 * @return service ID
	 */
	public static int getServiceIDForMSISDN(String msisdn) {
		Service s = SubscriptionManagementDAO.getServiceByMSISDN(msisdn);
		return s.getId();
	}

	public static Service getService(int serviceID) {
		String query = ExcelSQLManager.getSQLQuery("SUBSCRIPTION_MANAGEMENT", "GET_SERVICE");
		query = query.replace("$id", Integer.toString(serviceID));

		ResultSet rs;

		try {
			rs = GalaxionDBUtil.getQueryResultSet(query);

			if (rs.next()) {
				Service service = new Service(rs);
				return service;
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}

		return null;
	}

	/**
	 * Retrieve the brand for a service ID
	 * 
	 * @param serviceID
	 * @return brand
	 */
	public static Brand getBrandForServiceID(int serviceID) {
		Service service = SubscriptionManagementDAO.getService(serviceID);
		int accountID = SubscriptionManagementDAO.getAccountIDForSubID(service.getSubscriptionID());
		Account acc = SubscriptionManagementDAO.getAccountByID(accountID);
		return Brand.valueOf(acc.getBrand());
	}

	/**
	 * Retrieve the brand for a MSISDN
	 * 
	 * @param msisdn
	 * @return brand
	 */
	public static Brand getBrandForMSISDN(String msisdn) {
		int serviceID = getServiceIDForMSISDN(msisdn);
		return getBrandForServiceID(serviceID);
	}

	public static boolean waitForServiceCreation(String msisdn, int timeout) {
		String status = null;

		long endTime = System.currentTimeMillis() + (1000 * timeout);

		while (status == null && System.currentTimeMillis() < endTime) {
			status = getServiceStatus(msisdn);
			WaitUtil.waitForSeconds(1);
		}

		return status.equals("INITIAL");
	}

	/**
	 * Wait for a specified period of time for the service to reach status ACTIVE
	 * return true if the subscription is activated, and false if not
	 */
	public static boolean waitForServiceActivation(String msisdn, int timeout) {
		String status = "";

		long endTime = System.currentTimeMillis() + (1000 * timeout);

		while (!status.equals("ACTIVE") && System.currentTimeMillis() < endTime) {
			status = getServiceStatus(msisdn);
			WaitUtil.waitForSeconds(10);
		}

		return status.equals("ACTIVE");
	}

	/**
	 * Retrieve the market segment for an account
	 * 
	 * @param accountID
	 * @return market segment
	 */
	public static String getMarketSegment(int accountID) {
		String query = ExcelSQLManager.getSQLQuery("SUBSCRIPTION_MANAGEMENT", "GET_MARKET_SEGMENT");
		query = query.replace("$accountID", Integer.toString(accountID));

		try {

			// retrieve the result set
			ResultSet rs = GalaxionDBUtil.getQueryResultSet(query);

			if (rs.next()) {
				return rs.getString("value");
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}

		return null;
	}

	/**
	 * Retrieve the B2BAccount object based on an account ID The B2BAccount object
	 * related to subs-management.B2B_ACCOUNT table
	 * 
	 * @param accountID
	 * @return the B2BAccount object
	 */
	public static B2BAccount getB2BAccountByID(int accountID) {

		// build the query
		String query = ExcelSQLManager.getSQLQuery("SUBSCRIPTION_MANAGEMENT", "GET_B2B_ACCOUNT");
		query = query.replace("$id", Integer.toString(accountID));

		B2BAccount acc = null;

		try {
			// retrieve the result from the database
			ResultSet rs = GalaxionDBUtil.getQueryResultSet(query);

			if (rs.next()) {
				// populate the account object
				acc = new B2BAccount(rs);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}

		// return the account object
		return acc;
	}

	public static ArrayList<B2BAccount> getB2BAccountsForParent(int parentID) {

		// build the query
		String query = ExcelSQLManager.getSQLQuery("SUBSCRIPTION_MANAGEMENT", "GET_B2B_ACCOUNTS_BY_PARENT_ID");
		query = query.replace("$parentID", Integer.toString(parentID));

		ArrayList<B2BAccount> childAccounts = new ArrayList<B2BAccount>();

		ResultSet rs;
		try {
			rs = GalaxionDBUtil.getQueryResultSet(query);
			while (rs.next()) {
				B2BAccount acc = new B2BAccount();
				acc.setId(rs.getInt("id"));
				acc.setName(rs.getString("name"));
				acc.setCreditClass(rs.getString("credit_class"));
				acc.setCompanyId(rs.getInt("company_id"));
				acc.setParentId(rs.getInt("parent_id"));
				childAccounts.add(acc);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}

		return childAccounts;
	}

	/**
	 * Retrieve the Company object based on an account ID The Company object related
	 * to subs-management.COMPANY table
	 * 
	 * @param accountID
	 * @return the Company object
	 */
	public static Company getCompanyByID(int accountID) {

		// build the query
		String query = ExcelSQLManager.getSQLQuery("SUBSCRIPTION_MANAGEMENT", "GET_COMPANY_BY_ID");
		query = query.replace("$id", Integer.toString(accountID));

		Company company = null;

		try {
			// retrieve the result from the database
			ResultSet rs = GalaxionDBUtil.getQueryResultSet(query);
			company = new Company();

			if (rs.next()) {
				// populate the account object
				company.setId(rs.getInt("id"));
				company.setName(rs.getString("name"));
				company.setRegistrationNumber(rs.getString("registration_number"));
			}

		} catch (SQLException e) {
			e.printStackTrace();
		}

		// return the company object
		return company;
	}

	/**
	 * Retrieve the TaxDetails object based on an Tax_Details ID The TaxDetails
	 * object related to subs-management.TAX_DETAILS table
	 * 
	 * @param taxDetailsID
	 * @return the TaxDetails object
	 */
	public static TaxDetails getTaxDetails(int taxDetailsID) {

		String query = ExcelSQLManager.getSQLQuery("SUBSCRIPTION_MANAGEMENT", "GET_TAX_DETAILS");
		query = query.replace("$id", Integer.toString(taxDetailsID));

		TaxDetails details = null;

		try {
			ResultSet rs = GalaxionDBUtil.getQueryResultSet(query);
			details = new TaxDetails();

			if (rs.next()) {
				details.setId(rs.getInt("id"));
				details.setCategory(rs.getString("category"));
				details.setCertNumber(rs.getString("cert_number"));
				details.setExemptionDocumentType(rs.getString("exemption_document_type"));
				details.setExemptionEndDate(rs.getDate("exemption_ended_at"));
				details.setExemptionStartDate(rs.getDate("exemption_started_at"));
				details.setNumber(rs.getString("number"));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}

		return details;
	}

	/**
	 * Retrieve the account ID (not billing account ID) for a subscription
	 * 
	 * @param subscription_id
	 * @return the account_ID
	 */
	public static int getAccountIDForSubID(int subscription_id) {
		String query = ExcelSQLManager.getSQLQuery("SUBSCRIPTION_MANAGEMENT", "GET_ACCOUNT_BY_SUB_ID");
		query = query.replace("$subscriptionId", Integer.toString(subscription_id));

		// retrieve the result set
		ResultSet rs;
		try {
			rs = GalaxionDBUtil.getQueryResultSet(query);
			if (rs.next()) {
				return rs.getInt("account_id");
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}

		return -1;
	}

	/**
	 * Retrieve a list of DeviceEnrollments The DeviceEnrollment object related to
	 * subs-management.DEVICE_ENROLLMENT table
	 * 
	 * @param accountID
	 * @return a list of DeviceEnrollment objects
	 */
	public static ArrayList<DeviceEnrollment> getDeviceEnrollments(int accountID) {

		ArrayList<DeviceEnrollment> deviceEnrollments = new ArrayList<DeviceEnrollment>();

		String query = ExcelSQLManager.getSQLQuery("SUBSCRIPTION_MANAGEMENT", "GET_DEVICE_ENROLLMENTS");
		query = query.replace("$accountID", Integer.toString(accountID));

		try {
			ResultSet rs = GalaxionDBUtil.getQueryResultSet(query);
			while (rs.next()) {
				try {

					DeviceEnrollment enrollment = new DeviceEnrollment();
					enrollment.setB2bAccountID(rs.getInt("b2b_account_id"));
					enrollment.setDeviceEnrollmentProvider(rs.getString("device_enrollment_provider"));
					enrollment.setDeviceEnrollmentRef(rs.getString("device_enrollment_ref"));
					enrollment.setId(rs.getInt("id"));
					deviceEnrollments.add(enrollment);
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}

		return deviceEnrollments;
	}

	public static DeviceEnrollmentSet getdeviceEnrollmentSet(int accountID) {

		// get the list of enrollments from the database
		ArrayList<DeviceEnrollment> enrollmentsList = getDeviceEnrollments(accountID);

		// create the object
		DeviceEnrollmentSet enrollments = new DeviceEnrollmentSet();

		// populate the object based on the information retrieved from the database
		for (DeviceEnrollment enrollment : enrollmentsList) {

			switch (enrollment.getDeviceEnrollmentProvider()) {
			case "GOOGLE":
				enrollments.setAndroidEnrollment(enrollment.getDeviceEnrollmentRef());
				break;
			case "SAMSUNG":
				enrollments.setSamsungEnrollment(enrollment.getDeviceEnrollmentRef());
				break;
			case "APPLE":
				enrollments.setAppleEnrollment(enrollment.getDeviceEnrollmentRef());
				break;
			}
		}

		// return the object
		return enrollments;

	}

	/**
	 * Retrieve a list of B2BAccountAttribute The B2BAccountAttribute object related
	 * to subs-management.B2B_ACCOUNT_ATTRIBUTE table
	 * 
	 * @param accountID
	 * @return a list of B2BAccountAttribute objects
	 */
	public static ArrayList<B2BAccountAttribute> getb2bAccountAttributes(int accountID) {
		ArrayList<B2BAccountAttribute> attributes = new ArrayList<B2BAccountAttribute>();
		String query = ExcelSQLManager.getSQLQuery("SUBSCRIPTION_MANAGEMENT", "GET_B2B_ACCOUNT_ATTRIBUTES");
		query = query.replace("$id", Integer.toString(accountID));

		try {
			ResultSet rs = GalaxionDBUtil.getQueryResultSet(query);

			while (rs.next()) {
				try {
					B2BAccountAttribute attribute = new B2BAccountAttribute();
					attribute.setId(rs.getInt("id"));
					attribute.setB2bAccountID(rs.getInt("b2b_account_id"));
					attribute.setAttributeKey(rs.getString("attribute_key"));
					attribute.setAttributeValue(rs.getString("attribute_value"));
					attributes.add(attribute);
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
			return attributes;

		} catch (SQLException e) {
			e.printStackTrace();
		}
		return attributes;
	}

	public static CustomerType getCustomerType(String msisdn) {
		int billingAccountID = getBillingAccountIDForMsisdn(msisdn);
		int accountID = getAccountIDForBillingAccountID(billingAccountID);
		Account account = getAccountByID(accountID);

		if (account.getBrand().equals("GOMO")) {
			return CustomerType.GOMO;
		} else if (account.getBrand().equals("EIR") && account.getType().equals("B2B")) {
			return CustomerType.EIR_B2B;
		} else if (account.getBrand().equals("EIR") && account.getType().equals("B2C")) {
			ServiceDetail serviceDetail = CDRRepoDAO.getServiceDetail(msisdn);
			if (serviceDetail.getServiceType().equals("PREPAY")) {
				return CustomerType.EIR_PREPAY;
			} else if (serviceDetail.getServiceType().equals("POSTPAY")) {
				return CustomerType.EIR_POSTPAY;
			}
		}

		return null;
	}

	public static CustomerType getCustomerType(int service_id) {
		Brand brand = getBrandForServiceID(service_id);

		if (brand == Brand.GOMO) {
			return CustomerType.GOMO;
		} else if (brand == Brand.EIR) {
			return CustomerType.EIR_PREPAY;
		}

		return null;
	}

	public static int getB2BAccountID(String accountName, String date) {

		String query = ExcelSQLManager.getSQLQuery("SUBSCRIPTION_MANAGEMENT", "GET_B2B_ACCOUNT_ID_BY_NAME_AND_DATE");
		query = query.replace("$account_name", accountName);
		query = query.replace("$startTime", date);

		ResultSet rs;
		try {
			rs = GalaxionDBUtil.getQueryResultSet(query);
			if (rs.next()) {
				return rs.getInt("id");
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}

		return -1;
	}

	public static String getB2BContactUuidForAccount(int accountID, String contactType) {
		String query = ExcelSQLManager.getSQLQuery("SUBSCRIPTION_MANAGEMENT", "GET_B2B_CONTACT_UUID");
		query = query.replace("$type", contactType);
		query = query.replace("$accountID", Integer.toString(accountID));

		try {
			ResultSet rs;
			rs = GalaxionDBUtil.getQueryResultSet(query);
			if (rs.next()) {
				return rs.getString("uuid");
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null;

	}

	public static ArrayList<SimCard> getSIMCardAddonsForService(int serviceId) {

		ArrayList<SimCard> cards = new ArrayList<SimCard>();

		String query = ExcelSQLManager.getSQLQuery("SUBSCRIPTION_MANAGEMENT", "GET_SIM_CARD_ADDONS");
		query = query.replace("$serviceId", Integer.toString(serviceId));

		try {
			ResultSet rs = GalaxionDBUtil.getQueryResultSet(query);
			while (rs.next()) {
				cards.add(new SimCard(rs));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}

		return cards;
	}

	public static SimCard getActiveSIMCardForMSISDN(int serviceId) {

		Service service = getService(serviceId);
		return getSimCardById(service.getSimCardId());
		/*
		// read the list of SIMs from the addons table
		ArrayList<SimCard> addonSimCards = getSIMCardAddonsForService(serviceId);

		// if no addon SIMs, return the one linked to the service
		if (addonSimCards.size() == 0) {
			return getActiveSIMCardForMSISDN(msisdn);
		}
		// else if there are addon sims, return the one that has an activation date but
		// no termination date
		else {
			for (SimCard simCard : addonSimCards) {
				if (simCard.getActivatedAt() != null && simCard.getTerminatedAt() == null) {
					return simCard;
				}
			}
		}
		return null;
		*/
	}

	public static Service getServiceBySubscriptionID(int subscriptionID) {
		String query = ExcelSQLManager.getSQLQuery("SUBSCRIPTION_MANAGEMENT", "GET_SERVICE_BY_SUBSCRIPTION_ID");
		query = query.replace("$id", Integer.toString(subscriptionID));

		try {
			ResultSet rs = GalaxionDBUtil.getQueryResultSet(query);

			if (rs.next()) {
				return new Service(rs);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}

		return null;
	}

	/**
	 * Get a subscription from subs management by ID
	 * 
	 * @param subscription id
	 * @return subscription object
	 */
	public static Subscription getSubscriptionByID(int id) {
		String query = ExcelSQLManager.getSQLQuery("SUBSCRIPTION_MANAGEMENT", "GET_SUBSCRIPTION");
		query = query.replace("$id", Integer.toString(id));

		try {
			ResultSet rs = GalaxionDBUtil.getQueryResultSet(query);

			if (rs.next()) {
				return new Subscription(rs);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null;
	}

	public static Subscription getSubscriptionByOrderReference(String orderReference) {
		String query = ExcelSQLManager.getSQLQuery("SUBSCRIPTION_MANAGEMENT", "GET_SUBSCRIPTION_BY_ORDER_REFERENCE");
		query = query.replace("$reference", orderReference);

		try {
			ResultSet rs = GalaxionDBUtil.getQueryResultSet(query);

			if (rs.next()) {
				return new Subscription(rs);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null;
	}

	public static String getDeviceEnrollmentRef(int accountID, Provider provider) {

		String providerString = "";

		if (provider == Provider.Android) {
			providerString = "GOOGLE";
		} else {
			providerString = provider.toString().toUpperCase();
		}

		String query = ExcelSQLManager.getSQLQuery("SUBSCRIPTION_MANAGEMENT", "GET_DEVICE_ENROLLMENT_BY ACCOUNT_ID_AND_PROVIDER");
		query = query.replace("$accountID", Integer.toString(accountID));
		query = query.replace("$provider", providerString);
		try {

			// retrieve the result set
			ResultSet rs = GalaxionDBUtil.getQueryResultSet(query);

			if (rs.next()) {
				return rs.getString("device_enrollment_ref");
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}

		return null;
	}

	/*
	 * /** Return the list of active subscription IDs on an account
	 * 
	 * @param billingAccountID
	 * 
	 * @return
	 */
	/*
	 * public static ArrayList<Integer>
	 * getActiveSubscriptionIDsForBillingAccountID(int billingAccountID) {
	 * 
	 * ArrayList<Integer> activeSubIDs=new ArrayList<Integer>();
	 * 
	 * ArrayList<Subscription> subscriptions =
	 * getActiveSubscriptions(billingAccountID);
	 * 
	 * for(Subscription sub:subscriptions) { if(sub.getStatus().equals("ACTIVE")) {
	 * activeSubIDs.add(sub.getId()); } } return activeSubIDs; }
	 */

	public static int getPricePlanCharge(int serviceID) {
		String query = ExcelSQLManager.getSQLQuery("SUBSCRIPTION_MANAGEMENT", "GET_PRICE_PLAN_CHARGE_FOR_SERVICE");
		query = query.replace("$service_id", Integer.toString(serviceID));

		try {
			ResultSet rs = GalaxionDBUtil.getQueryResultSet(query);

			while (rs.next()) {
				return rs.getInt("overriden_price");
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return -1;
	}

	/**
	 * Get a list of active subscriptions on an account
	 * 
	 * @param billingAccountID
	 * @return list of subscriptions
	 */
	public static ArrayList<Subscription> getActiveSubscriptions(int billingAccountID) {

		ArrayList<Subscription> subscriptions = new ArrayList<Subscription>();

		String query = ExcelSQLManager.getSQLQuery("SUBSCRIPTION_MANAGEMENT", "GET_SUBSCRIPTIONS_FOR_BILLING_ACCOUNT_ID");
		query = query.replace("$billing_account_id", Integer.toString(billingAccountID));

		try {
			ResultSet rs = GalaxionDBUtil.getQueryResultSet(query);

			while (rs.next()) {
				if (rs.getString("status").equals("ACTIVE")) {
					subscriptions.add(new Subscription(rs));
				}
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return subscriptions;
	}

	public static NDDSetting getNDDSetting(String msisdn) {
		String query = ExcelSQLManager.getSQLQuery("SUBSCRIPTION_MANAGEMENT", "GET_NDD_SETTING");
		query = query.replace("$msisdn", msisdn);

		try {

			// retrieve the result set
			ResultSet rs = GalaxionDBUtil.getQueryResultSet(query);

			if (rs.next()) {
				return NDDSetting.valueOf(rs.getString("ndd_preference"));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}

		return null;
	}

	public static NDDSetting getNDDSetting(int serviceID) {
		Service service = getService(serviceID);
		return getNDDSetting(service.getName());
	}

	/**
	 * Determine the MSISDN/service which ordered a pending replacement SIM
	 * 
	 * @param iccid
	 * @return the msisdn
	 */
	public static String getMsisdnForPendingReplacementSIM(String iccid) {
		String query = ExcelSQLManager.getSQLQuery("SUBSCRIPTION_MANAGEMENT", "GET_MSISDN_FOR_REPLACEMENT_SIM");
		query = query.replace("$iccid", iccid);

		try {

			// retrieve the result set
			ResultSet rs = GalaxionDBUtil.getQueryResultSet(query);

			if (rs.next()) {
				return rs.getString("name");
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}

		return null;
	}

	/**
	 * Update any pending replacement SIMs on an account to have a terminated_at
	 * date This is needed as a set up/cleardown step for replacement sim orders
	 * 
	 * @param msisdn
	 * @param time
	 */
	public static void updatePendingReplacementSIMs(String msisdn, String time) {
		String query = ExcelSQLManager.getSQLQuery("SUBSCRIPTION_MANAGEMENT", "TERMINATE_PENDING_REPLACEMENT_SIMS");
		query = query.replace("$time", time);
		query = query.replace("$msisdn", msisdn);

		try {
			GalaxionDBUtil.runUpdateQuery(query);
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	/**
	 * Select the first ACTIVE service on an account
	 * 
	 * @param billingAccountID
	 * @return the service object
	 */
	public static Service getActiveService(int billingAccountID) {

		// retrieve the list of subscriptions linked to the account
		ArrayList<Subscription> subscriptions = getActiveSubscriptions(billingAccountID);

		// read the service and subscription information from the database
		return getServiceBySubscriptionID(subscriptions.get(0).getId());
	}

	/**
	 * Get a list of child accounts for an account ID
	 * 
	 * @param accountID
	 * @return list of accounts
	 */
	public static ArrayList<B2BAccount> getChildAccounts(int accountID) {

		ArrayList<B2BAccount> childAccounts = new ArrayList<B2BAccount>();

		String query = ExcelSQLManager.getSQLQuery("SUBSCRIPTION_MANAGEMENT", "GET_CHILD_ACCOUNTS");
		query = query.replace("$id", Integer.toString(accountID));

		ResultSet rs;

		try {
			rs = GalaxionDBUtil.getQueryResultSet(query);

			while (rs.next()) {
				childAccounts.add(new B2BAccount(rs));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}

		return childAccounts;
	}

	/**
	 * Get a contract based on contract ID
	 * 
	 * @param id
	 * @return contract object
	 */
	public static Contract getContract(int id) {

		String query = ExcelSQLManager.getSQLQuery("SUBSCRIPTION_MANAGEMENT", "GET_CONTRACT");
		query = query.replace("$id", Integer.toString(id));

		ResultSet rs;

		try {
			rs = GalaxionDBUtil.getQueryResultSet(query);

			while (rs.next()) {
				return new Contract(rs);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}

		return null;
	}

	public static boolean isContractEnded(int contractID) {
		Contract c = getContract(contractID);
		return c.getEndAt().getTime() < System.currentTimeMillis();
	}

	public static SimCard getSimCardLinkedToService(String msisdn) {

		String query = ExcelSQLManager.getSQLQuery("SUBSCRIPTION_MANAGEMENT", "GET_SIM_CARD_LINKED_TO_SERVICE");
		query = query.replace("$msisdn", msisdn);

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

	/*
	public static ArrayList<SimCard> getReplacementSIMCardsOnService(String msisdn) {

		ArrayList<SimCard> simCards = new ArrayList<SimCard>();

		String query = ExcelSQLManager.getSQLQuery("SUBSCRIPTION_MANAGEMENT", "GET_ADDED_REPLACEMENT_SIMS");
		query = query.replace("$msisdn", msisdn);

		ResultSet rs;

		try {
			rs = GalaxionDBUtil.getQueryResultSet(query);

			while (rs.next()) {
				simCards.add(new SimCard(rs));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}

		return simCards;
	}
	*/
	
	public static ArrayList<AddonBundle> getReplacementSIMCardAddonsOnService(int serviceId) {

		ArrayList<AddonBundle> simCards = new ArrayList<AddonBundle>();

		String query = ExcelSQLManager.getSQLQuery("SUBSCRIPTION_MANAGEMENT", "GET_SIM_CARD_ADDONS_FOR_SERVICE");
		query = query.replace("$serviceId", Integer.toString(serviceId));

		ResultSet rs;

		try {
			rs = GalaxionDBUtil.getQueryResultSet(query);

			while (rs.next()) {
				simCards.add(new AddonBundle(rs));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}

		return simCards;
	}

	/**
	 * Retrieve the active sim card on a service
	 * 
	 * @param msisdn
	 * @return
	 */
	public static SimCard getActiveSimCard(String msisdn) {

		Service service = getServiceByMSISDN(msisdn);
		int simCardId = service.getSimCardId();
		
		/*
		// read the list of replacement cards linked to a service
		ArrayList<SimCard> simCards = SubscriptionManagementDAO.getReplacementSIMCardsOnService(msisdn);

		// check for an active sim card in the addons/replacements list
		for (SimCard replacementCard : simCards) {
			if (replacementCard.getActivatedAt() != null && replacementCard.getTerminatedAt() == null) {
				return replacementCard;
			}
		}

		// if no replacement sims are active, check the sim card linked in the
		// service_sim_card table
		SimCard card = getSimCardLinkedToService(msisdn);
		if (card.getActivatedAt() != null && card.getTerminatedAt() == null) {
			return card;
		}

		return null;
		*/
		return getSimCardById(simCardId);
	}

	/**
	 * Lookup a SIM_CARD based on ICCID
	 * 
	 * @param iccid
	 * @return the sim card object
	 */
	public static SimCard getSimCardByIccid(String iccid) {
		String query = ExcelSQLManager.getSQLQuery("SUBSCRIPTION_MANAGEMENT", "GET_SIM_CARD_BY_ICCID");
		query = query.replace("$iccid", iccid);

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
	
	/**
	 * Lookup a SIM_CARD based on ICCID
	 * 
	 * @param iccid
	 * @return the sim card object
	 */
	public static SimCard getSimCardById(int id) {
		String query = ExcelSQLManager.getSQLQuery("SUBSCRIPTION_MANAGEMENT", "GET_SIM_CARD");
		query = query.replace("$id", Integer.toString(id));

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

	public static RefBillingCycle getBillCycle(int billCycleID) {
		String query = ExcelSQLManager.getSQLQuery("SUBSCRIPTION_MANAGEMENT", "GET_REF_BILLING_CYCLE");
		query = query.replace("$billCycleID", Integer.toString(billCycleID));

		ResultSet rs;

		try {
			rs = GalaxionDBUtil.getQueryResultSet(query);

			while (rs.next()) {
				return new RefBillingCycle(rs);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}

		return null;
	}

	public static Brand getBrandForAccount(int billingAccountID) {
		int accountID = getAccountIDForBillingAccountID(billingAccountID);
		Account account = getAccountByID(accountID);
		return Brand.valueOf(account.getBrand());
	}

	/**
	 * Get the offer code on an active service
	 * 
	 * @param msisdn
	 * @return the offer code
	 */
	public static OfferCode getOfferCode(String msisdn) {
		Service service = SubscriptionManagementDAO.getServiceByMSISDN(msisdn);
		Subscription sub = SubscriptionManagementDAO.getSubscriptionByID(service.getSubscriptionID());
		return OfferCode.valueOf(sub.getCatalogOfferCode());
	}

	public static void setSubscriptionStartDate(int subscriptionId, String newDate) {
		String query = ExcelSQLManager.getSQLQuery("SUBSCRIPTION_MANAGEMENT", "UPDATE_SUBSCRIPTION_START_DATE").replace("$newDate", newDate)
				.replace("$subscription_id", Integer.toString(subscriptionId));

		try {
			GalaxionDBUtil.runUpdateQuery(query);
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	public static String setAge(int subscriptionId, int days) {

		// determine today's date
		Calendar cal = Calendar.getInstance();

		// add or subtract the number of days
		cal.add(Calendar.DATE, -days);
		Date thendate = cal.getTime();

		// convert to the appropriate date format for the DB update
		SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		String dateString = format.format(thendate);

		// make the db update
		setSubscriptionStartDate(subscriptionId, dateString);

		// return the date string
		return dateString;
	}

	public static AccountSubscription getAccountSubscriptionBySubscriptionID(int subscriptionID) {
		String query = ExcelSQLManager.getSQLQuery("SUBSCRIPTION_MANAGEMENT", "GET_ACCOUNT_SUBSCRIPTION_BY_SUBSCRIPTION_ID");
		query = query.replace("$subscriptionID", Integer.toString(subscriptionID));
		ResultSet rs;
		try {
			rs = GalaxionDBUtil.getQueryResultSet(query);
			try {
				if (rs.next()) {
					return new AccountSubscription(rs);
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}
		} catch (SQLException e1) {
			e1.printStackTrace();
		}

		return null;
	}

	public static ArrayList<AccountSubscription> getAccountSubscriptionsByAccountID(int accountID) {

		ArrayList<AccountSubscription> subscriptions = new ArrayList<AccountSubscription>();

		String query = ExcelSQLManager.getSQLQuery("SUBSCRIPTION_MANAGEMENT", "GET_ACCOUNT_SUBSCRIPTION_BY_ACCOUNT_ID");
		query = query.replace("$accountID", Integer.toString(accountID));

		ResultSet rs;
		try {
			rs = GalaxionDBUtil.getQueryResultSet(query);
			try {
				if (rs.next()) {
					subscriptions.add(new AccountSubscription(rs));
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}

		} catch (SQLException e1) {
			e1.printStackTrace();
		}

		return subscriptions;

	}

	public static ArrayList<AccountContact> getAllAccountContacts(int billingAccountID) {

		ArrayList<AccountContact> contacts = new ArrayList<AccountContact>();

		String query = ExcelSQLManager.getSQLQuery("SUBSCRIPTION_MANAGEMENT", "GET_ACCOUNT_CONTACTS");
		query = query.replace("$billingAccountID", Integer.toString(billingAccountID));

		ResultSet rs;
		try {
			rs = GalaxionDBUtil.getQueryResultSet(query);
			try {
				while (rs.next()) {
					contacts.add(new AccountContact(rs));
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}
		} catch (SQLException e1) {
			e1.printStackTrace();
		}

		return contacts;

	}
	
	public static ArrayList<AccountContact> getActiveAccountContacts(int billingAccountID) {

		ArrayList<AccountContact> allAccountContacts = getAllAccountContacts(billingAccountID);
		ArrayList<AccountContact> activeAccountContacts = new ArrayList<AccountContact>();

		for(AccountContact contact:allAccountContacts) {
			if(contact.getEndedAt()==null) {
				activeAccountContacts.add(contact);
			}
		}
		
		return activeAccountContacts;
	}
	
	public static AccountContact getAccountContact(int accountContactId) {

		String query = ExcelSQLManager.getSQLQuery("SUBSCRIPTION_MANAGEMENT", "GET_ACCOUNT_CONTACT");
		query = query.replace("$id", Integer.toString(accountContactId));

		ResultSet rs;
		try {
			rs = GalaxionDBUtil.getQueryResultSet(query);
			try {
				while (rs.next()) {
					AccountContact contact = new AccountContact(rs);
					return contact;
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		catch(SQLException e) {
			e.printStackTrace();
		}

		return null;
	}

	public static ArrayList<AccountContact> getAccountContactsEnhanced(int accountID) {

		ArrayList<AccountContact> contacts = new ArrayList<AccountContact>();

		String query = ExcelSQLManager.getSQLQuery("SUBSCRIPTION_MANAGEMENT", "GET_ACCOUNT_CONTACTS_ENHANCED");
		query = query.replace("$accountId", Integer.toString(accountID));

		ResultSet rs;
		try {
			rs = GalaxionDBUtil.getQueryResultSet(query);
			try {
				while (rs.next()) {
					AccountContact contact = new AccountContact(rs);
					contact.setName(rs.getString("name"));
					contact.setDescription(rs.getString("description"));
					contacts.add(contact);
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}
		} catch (SQLException e1) {
			e1.printStackTrace();
		}

		return contacts;

	}

	public static RefContactType getRefContactType(String name) {

		String query = ExcelSQLManager.getSQLQuery("SUBSCRIPTION_MANAGEMENT", "GET_REF_CONTACT_TYPE");
		query = query.replace("$name", name);

		ResultSet rs;
		try {
			rs = GalaxionDBUtil.getQueryResultSet(query);
			try {
				while (rs.next()) {
					return new RefContactType(rs);
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}

		} catch (SQLException e1) {
			e1.printStackTrace();
		}

		return null;
	}

	/**
	 * Return a subset of information for a list of subscriptions and services
	 * linked to a billing account ID
	 * 
	 * @param billingAccountID
	 * @return
	 */
	public static ArrayList<SubscriptionServicePair> getSubscriptionServiceInfoForBillingAccountID(int billingAccountID) {

		ArrayList<SubscriptionServicePair> subscriptions = new ArrayList<SubscriptionServicePair>();

		String query = ExcelSQLManager.getSQLQuery("SUBSCRIPTION_MANAGEMENT", "GET_SERVICE_AND_SUBSCRIPTION_INFO_FOR_BILLING_ACCOUNT_ID");
		query = query.replace("$billingAccountID", Integer.toString(billingAccountID));

		ResultSet rs;
		try {
			rs = GalaxionDBUtil.getQueryResultSet(query);
			try {
				if (rs.next()) {
					subscriptions.add(new SubscriptionServicePair(rs));
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}
		} catch (SQLException e1) {
			e1.printStackTrace();
		}

		return subscriptions;
	}

	public static ArrayList<Subscription> getSubscriptionsForBillingAccountID(int billingAccountID) {

		ArrayList<Subscription> subscriptions = new ArrayList<Subscription>();

		String query = ExcelSQLManager.getSQLQuery("SUBSCRIPTION_MANAGEMENT", "GET_SUBSCRIPTIONS_FOR_BILLING_ACCOUNT_ID");
		query = query.replace("$billing_account_id", Integer.toString(billingAccountID));

		ResultSet rs;
		try {
			rs = GalaxionDBUtil.getQueryResultSet(query);
			try {
				if (rs.next()) {
					subscriptions.add(new Subscription(rs));
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}
		} catch (SQLException e1) {
			e1.printStackTrace();
		}

		return subscriptions;
	}

	public static ArrayList<RefBillingCycle> getBillCycles(String customerType) {

		ArrayList<RefBillingCycle> billCycles = new ArrayList<RefBillingCycle>();

		String query = ExcelSQLManager.getSQLQuery("SUBSCRIPTION_MANAGEMENT", "GET_BILLING_CYCLES");
		query = query.replace("$customerType", customerType);

		ResultSet rs;
		try {
			rs = GalaxionDBUtil.getQueryResultSet(query);
			try {
				while (rs.next()) {
					billCycles.add(new RefBillingCycle(rs));
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}
		} catch (SQLException e1) {
			e1.printStackTrace();
		}

		return billCycles;
	}
	
	public static ArrayList<UsageQuota> getUsageQuotas(int serviceId) {

		ArrayList<UsageQuota> usages = new ArrayList<UsageQuota>();

		String query = ExcelSQLManager.getSQLQuery("SUBSCRIPTION_MANAGEMENT", "GET_USAGE_QUOTAS");
		query = query.replace("$serviceId", Integer.toString(serviceId));

		ResultSet rs;
		try {
			rs = GalaxionDBUtil.getQueryResultSet(query);
			try {
				while (rs.next()) {
					usages.add(new UsageQuota(rs));
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}
		} catch (SQLException e1) {
			e1.printStackTrace();
		}

		return usages;
	}
	
	public static ArrayList<NetworkElement> getNetworkElements(int serviceId) {

		ArrayList<NetworkElement> networkElements = new ArrayList<NetworkElement>();

		String query = ExcelSQLManager.getSQLQuery("SUBSCRIPTION_MANAGEMENT", "GET_NETWORK_ELEMENTS");
		query = query.replace("$serviceId", Integer.toString(serviceId));

		ResultSet rs;
		try {
			rs = GalaxionDBUtil.getQueryResultSet(query);
			try {
				while (rs.next()) {
					networkElements.add(new NetworkElement(rs));
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}
		} catch (SQLException e1) {
			e1.printStackTrace();
		}

		return networkElements;
	}

	public static ArrayList<RefAccountCategory> getAccountCategories(String type) {

		ArrayList<RefAccountCategory> values = new ArrayList<RefAccountCategory>();

		String query = ExcelSQLManager.getSQLQuery("SUBSCRIPTION_MANAGEMENT", "GET_REF_ACCOUNT_CATEGORY");
		query = query.replace("$type", type);

		ResultSet rs;
		try {
			rs = GalaxionDBUtil.getQueryResultSet(query);
			try {
				while (rs.next()) {
					values.add(new RefAccountCategory(rs));
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}
		} catch (SQLException e1) {
			e1.printStackTrace();
		}

		return values;
	}

	public static SubscriptionServicePair getServiceInformation(int billingAccountID, int serviceID) {
		ArrayList<SubscriptionServicePair> all = getSubscriptionServiceInfoForBillingAccountID(billingAccountID);

		for (SubscriptionServicePair service : all) {
			if (service.getServiceID() == serviceID) {
				return service;
			}
		}

		return null;
	}

	/**
	 * Make a B2B account inbillable by changing B2B_ACCOUNT.IS_INVOICE_OWNER to 1
	 * 
	 * @param accountID
	 */
	public static void makeAccountBillable(int accountID) {
		String query = ExcelSQLManager.getSQLQuery("SUBSCRIPTION_MANAGEMENT", "MAKE_ACCOUNT_BILLABLE");
		query = query.replace("$accountID", Integer.toString(accountID));
		try {
			GalaxionDBUtil.runUpdateQuery(query);
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	public static void updateAccountBrand(int billingAccountId, Brand brand) {
		String query = ExcelSQLManager.getSQLQuery("SUBSCRIPTION_MANAGEMENT", "UPDATE_BRAND");
		query = query.replace("$billingAccountId", Integer.toString(billingAccountId));
		query = query.replace("$brand", brand.toString());
		try {
			GalaxionDBUtil.runUpdateQuery(query);
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	/**
	 * Retrieve a list of B2BAccountAttribute The B2BAccountAttribute object related
	 * to subs-management.B2B_ACCOUNT_ATTRIBUTE table
	 * 
	 * @param accountID
	 * @return a list of B2BAccountAttribute objects
	 */
	public static B2BAccountAttributeSet getB2BAccountAttributeSet(int accountID) {

		B2BAccountAttributeSet attributes = new B2BAccountAttributeSet();

		String query = ExcelSQLManager.getSQLQuery("SUBSCRIPTION_MANAGEMENT", "GET_B2B_ACCOUNT_ATTRIBUTES");
		query = query.replace("$id", Integer.toString(accountID));

		try {
			ResultSet rs = GalaxionDBUtil.getQueryResultSet(query);

			while (rs.next()) {

				try {
					String attributeKey = rs.getString("attribute_key");
					String value = rs.getString("attribute_value");

					switch (attributeKey) {
					case "SALESFORCE_CUSTOMER_ID":
						attributes.setSalesforceCustomerId(value);
						break;
					case "GROUP_ICID":
						attributes.setGroupIcid(value);
						break;
					case "AGREEMENT_DURATION":
						attributes.setAgreementDuration(value);
						break;
					case "ACCOUNT_MANAGER":
						attributes.setAccountManager(value);
						break;
					case "CUSTOMER_COST_CENTER":
						attributes.setCustomerCostCenter(value);
						break;
					case "VPN_ACCOUNT_NUMBER":
						attributes.setVpnAccountNumber(value);
						break;
					case "SALESFORCE_CASE_NUMBER":
						attributes.setSalesforceCaseNumber(value);
						break;
					case "INDOOR_COVERAGE_SOLUTION_DATE":
						attributes.setIndoorCoverageSolutionDate(value);
						break;
					}
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return attributes;
	}

	public static BillingDetailsSet getBillingDetailsSet(int accountId) {
		Account account = getAccountByID(accountId);
		B2BAccount b2bAccount = getB2BAccountByID(accountId);

		BillingDetailsSet details = new BillingDetailsSet();
		details.setInvoiceDeliveryMethod(account.getInvoiceDeliveryMethod());
		details.setBillItemised(account.getItemisedInvoice() == 1);
		details.setBillAnalyserConsent(b2bAccount.getBillAnalyserConsent() == 1);
		details.setBillCycleId(account.getBillingCycleID());
		return details;
	}
	
	public static RefAccountCategory getRefAccountCategory(String value) {
		ArrayList<RefAccountCategory> categories = SubscriptionManagementDAO.getAccountCategories("MARKET_SEGMENT");
		
		for(RefAccountCategory c:categories) {
			if(c.getValue().equals(value)) {
				return c;
			}
		}
		
		return null;
	}
	
	public static void createUsage(int serviceID, int usageQuotaId, String usageCode) {
		
		String timestamp = Timestamp.getCurrentTimestamp("yyyy-MM-dd HH:mm:ss");
		
		String query = "INSERT INTO `eir-subscription-management-backend`.usage_quota (order_id, catalog_usage_quota_id, created_at, updated_at, activated_at, terminated_at, catalog_code, addon_bundle_id, service_id, is_paused)\r\n" + 
				"VALUES (90331, $usageQuotaId, '$timestamp','$timestamp','$timestamp',null,'$usageCode',null,$serviceId,0);";
		
		query = query.replace("$timestamp", timestamp);
		query = query.replace("$serviceId", Integer.toString(serviceID));
		query = query.replace("$usageCode", usageCode);
		query = query.replace("$usageQuotaId", Integer.toString(usageQuotaId));
		try {
			GalaxionDBUtil.runUpdateQuery(query);
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * Update the quartz trigger to the current time, based on the trigger name
	 * @param triggerName
	 */
	private static void rescheduleTerminationRequestQuartzTrigger(String triggerName) {
		String query = ExcelSQLManager.getSQLQuery("SUBSCRIPTION_MANAGEMENT", "RESCHEDULE_QUARTZ_TRIGGER_TO_NOW");
		query = query.replace("$triggerName", triggerName);
		

		try {
			GalaxionDBUtil.runUpdateQuery(query);
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	private static void updateTerminationRequestScheduledAtDate(int terminationId, String newTime) {
		String query = ExcelSQLManager.getSQLQuery("SUBSCRIPTION_MANAGEMENT", "UPDATE_REQUEST_SUBSCRIPTION_TERMINATION_DATE");
		query = query.replace("$terminationId", Integer.toString(terminationId));
		query = query.replace("$newTime", newTime);

		try {
			GalaxionDBUtil.runUpdateQuery(query);
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	public static void rescheduleTerminationRequestToNow(int pendingTerminationId) {
		updateTerminationRequestScheduledAtDate(pendingTerminationId,Timestamp.getTimestampMinus1Hour("yyyy-MM-dd HH:mm:ss"));
		rescheduleTerminationRequestQuartzTrigger(Integer.toString(pendingTerminationId));
		
		
	}
}