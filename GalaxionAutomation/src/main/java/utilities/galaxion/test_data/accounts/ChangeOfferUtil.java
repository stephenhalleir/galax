package utilities.galaxion.test_data.accounts;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.UUID;

import external_systems.mmw.MMWUtility;
import external_systems.mobile_network.nodes.spr.SPRProfile;
import io.restassured.response.Response;
import microservices.backend.eir_catalog_core_backend.enums.Brand;
import microservices.backend.eir_contact_management_backend.dao.ContactManagementDAO;
import microservices.backend.eir_credit_score_backend.CreditScoreDAO;
import microservices.backend.eir_order_management_backend.api.OrderManagementAPI;
import microservices.backend.eir_order_management_backend.monitor.OrderManagementMonitor;
import microservices.backend.eir_subscription_management_backend.SubscriptionManagementDAO;
import microservices.backend.eir_subscription_management_backend.data_model.Account;
import microservices.backend.eir_subscription_management_backend.data_model.Service;
import microservices.backend.eir_subscription_management_backend.data_model.Subscription;
import utilities.api.RestAssuredUtil;
import utilities.galaxion.environments.EnvironmentDirectory;
import utilities.generic.database.GalaxionDBUtil;
import utilities.generic.files.TextReader;
import utilities.generic.time.Timestamp;
import utilities.generic.time.WaitUtil;
import utilities.network.SOAPUtility;
import utilities.rabbitmq.RabbitMQUtil;

public class ChangeOfferUtil {

	public static void reprovisionAsEirNetworkProfile(String msisdn) {
		Service service = SubscriptionManagementDAO.getServiceByMSISDN(msisdn);
		Subscription subscription = SubscriptionManagementDAO.getSubscriptionByID(service.getSubscriptionID());
		Account account = SubscriptionManagementDAO.getAccountByID(subscription.getAccountId());
		System.out.println(service.getName() + ", " + service.getId() + ", " + account.getBillingAccountID());

		// read the SPR profile
		SPRProfile sprProfile = MMWUtility.getSprProfile(msisdn);

		String imsi = sprProfile.getImsi();
		String subID = sprProfile.getSubID();

		// terminate from the network
		terminateSubscriber(msisdn, Integer.toString(service.getId()), "ION:910324-0");

		// wait for the termination to take effect
		long sprPollEndTime = System.currentTimeMillis() + 30000;

		while (System.currentTimeMillis() < sprPollEndTime) {
			sprProfile = MMWUtility.getSprProfile(msisdn);

			if (!sprProfile.isRetrievalOk()) {
				break;
			} else {
				WaitUtil.waitForSeconds(5);
			}
		}

		// check whether the SPR profile has been deleted
		sprProfile = MMWUtility.getSprProfile(msisdn);

		if (!sprProfile.isRetrievalOk()) {

			WaitUtil.waitForSeconds(5);
			// reprovision the subscriber with the eir profile
			provisionToNetwork(msisdn, subID, "ION_" + account.getBillingAccountID(), "ION:910328-1", "PostPaid", "Bill Pay Connect EUR25 - 12 Months", imsi);
		} else {
			System.err.println("Error: Subscriber is still active on the SPR");
		}
	}

	public static void provisionToNetwork(String msisdn, String subscriberId, String customerAccountID, String eventContext, String cfspProfile,
			String tariffClass, String imsi) {

		String endpoint = "http://mwatst01.eircom.ie:7022/Interceptor/ProvisioningProxy";
		String payload = TextReader.getContent("requests\\soap\\mmw\\CreateSubscriberProfile");
		payload = payload.replace("$msisdn", msisdn);
		payload = payload.replace("$subscriberId", subscriberId);
		payload = payload.replace("$customerAccountID", customerAccountID);
		payload = payload.replace("$eventContext", eventContext);
		payload = payload.replace("$cfspProfile", cfspProfile);
		payload = payload.replace("$tariffClass", tariffClass);
		payload = payload.replace("$imsi", imsi);

		String response = SOAPUtility.getPostResponse(endpoint, payload);
		System.out.println(response);
	}

	public static void terminateSubscriber(String msisdn, String serviceID, String eventContext) {
		String endpoint = "http://mwatst01.eircom.ie:7022/Interceptor/ProvisioningProxy";
		String payload = TextReader.getContent("requests\\soap\\mmw\\TerminateSubscriberProfile");
		payload = payload.replace("$msisdn", msisdn);
		payload = payload.replace("$subscriberId", serviceID);
		payload = payload.replace("$eventContext", eventContext);
		String response = SOAPUtility.getPostResponse(endpoint, payload);
		System.out.println(response);
	}

	public static void createContract(String msisdn) {
		
		// read the service and subscription information
		Service service = SubscriptionManagementDAO.getServiceByMSISDN(msisdn);
		Subscription subscription = SubscriptionManagementDAO.getSubscriptionByID(service.getSubscriptionID());

		// create a contract
		String queryCreateContract = "INSERT INTO `eir-subscription-management-backend`.contract (start_at,end_at) VALUES ('2021-11-29 00:00:00','2022-11-29 23:59:59');";
		String getContractIdQuery = "SELECT MAX(id) AS \"id\" from `eir-subscription-management-backend`.contract";
		String queryUpdateContractID = "UPDATE `eir-subscription-management-backend`.subscription SET contract_id='$contractID' WHERE subscription.id='$subscriptionID';";

		// create a new contract
		try {
			GalaxionDBUtil.runUpdateQuery(queryCreateContract);
		} catch (SQLException e1) {
			e1.printStackTrace();
		}

		int contractId = 0;

		try {
			ResultSet rs = GalaxionDBUtil.getQueryResultSet(getContractIdQuery);
			if (rs.next()) {
				contractId = rs.getInt("id");
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}

		if (contractId == 0) {
			System.out.println("Error: contract ID is 0");
		}

		// link the contract to the subscription
		queryUpdateContractID = queryUpdateContractID.replace("$contractID", Integer.toString(contractId));
		queryUpdateContractID = queryUpdateContractID.replace("$subscriptionID", Integer.toString(subscription.getId()));

		try {
			GalaxionDBUtil.runUpdateQuery(queryUpdateContractID);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		subscription = SubscriptionManagementDAO.getSubscriptionByID(service.getSubscriptionID());

		if (subscription.getContractID() != contractId) {
			System.out.println("Error: contract ID is " + subscription.getContractID());
		}

		// update the database
		String updateQuery = "UPDATE `eir-subscription-management-backend`.`subscription` SET `contract_id`='$contractId' WHERE  `id`=$subscriptionId;";
		updateQuery = updateQuery.replace("$subscriptionId", Integer.toString(service.getSubscriptionID()));
		updateQuery = updateQuery.replace("$contractId", Integer.toString(contractId));
		try {
			GalaxionDBUtil.runUpdateQuery(updateQuery);
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	public static String submitChangeOffer(String msisdn) {

		String orderRef = OrderManagementAPI.getUniqueOrderNumber();
		if (orderRef == null || orderRef.length() != 8) {
			System.err.println("Error: Invalid order reference " + orderRef);
		}

		// read the service and subscription information
		Service service = SubscriptionManagementDAO.getServiceByMSISDN(msisdn);
		String url = EnvironmentDirectory.getOrderManagementAPI() + "/event/product_order";

		int billingAccountId = SubscriptionManagementDAO.getBillingAccountIDForMsisdn(msisdn);
		int subscriptionAccountId = SubscriptionManagementDAO.getAccountIDForBillingAccountID(billingAccountId);
		int subscriptionServiceId = service.getId();

		String json = TextReader.getContent("requests\\json\\change_offer\\change_offer_workaround.json");
		json = json.replace("$reference", orderRef);
		json = json.replace("$externalRef", "change_offer_" + Timestamp.getCurrentTimestamp("yyyyMMddHHmmss"));
		json = json.replace("$subscriptionAccountId", Integer.toString(subscriptionAccountId));
		json = json.replace("$subscriptionServiceId", Integer.toString(subscriptionServiceId));
		json = json.replace("$billingAccountId", Integer.toString(billingAccountId));
		json = json.replace("$serviceName", msisdn);
		System.out.println(json);

		Response r = RestAssuredUtil.galaxionPost(url, json, null);
		System.err.println(orderRef + ", " + r.statusCode() + ", " + r.asString());

		return orderRef;
	}

	/*
	public static boolean completeChangeOffer(String orderReference) {

		if (orderReference == null || orderReference.length() > 9) {
			System.err.println("Error: completeChangeOffer has invalid order reference");
			return false;
		}

		boolean orderInUpdateSubscriber = OrderManagementMonitor.waitForOrderToReachStep(orderReference, "UPDATE_SUBSCRIBER_PROFILE");

		String query = "SELECT po.reference  , pr.reference requestId, pr.`action`\r\n" + "FROM `eir-order-management-backend`.product_order po\r\n"
				+ "left JOIN `eir-order-management-backend`.offer o ON po.id = o.order_id\r\n"
				+ "INNER JOIN `eir-order-management-backend`.provisioning_reference pr ON pr.offer_id = o.id\r\n" + "WHERE 1 =1\r\n"
				+ "AND po.reference = '$reference'";
		query = query.replace("$reference", orderReference);

		String requestId = "";
		String action = "";
		int networkId = 123456;

		try {
			ResultSet rs = GalaxionDBUtil.getQueryResultSet(query);
			if (rs.next()) {
				action = rs.getString("action");
				requestId = rs.getString("requestId");
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}

		System.err.println(requestId + ", " + action + ", " + networkId);


		String payload = "{\r\n" + "  \"requestorId\": \"$requestId\",\r\n" + " \"action\" : \"$action\",\r\n" + " \"networkId\" : $networkId\r\n" + "}";

		payload = payload.replace("$networkId", Integer.toString(networkId));
		payload = payload.replace("$action", action);
		payload = payload.replace("$requestId", requestId);

		boolean messagePosted = RabbitMQUtil.publishMessage(payload, "order.event.provisioned");
		System.out.println(payload);

		return OrderManagementMonitor.waitForOrderToComplete(orderReference);
	}
	*/

	public static void updateEmailAndCreditScore(String msisdn) {

		// read the contact and billing account information
		int billingAccountId = SubscriptionManagementDAO.getBillingAccountIDForMsisdn(msisdn);
		String contactUuid = SubscriptionManagementDAO.getContactUuidForBillingAccountID(billingAccountId);
		String email = ContactManagementDAO.getEmailAddressForUUID(contactUuid);

		// set email.validated=1
		ContactManagementDAO.markEmailAddressAsVerified(email);

		// insert a record into the credit_score table
		CreditScoreDAO.createCreditScore(2, "RETIRED", "OWNER", UUID.randomUUID().toString(), contactUuid);

		// update the account brand to EIR
		SubscriptionManagementDAO.updateAccountBrand(billingAccountId, Brand.EIR);

		// update the customer type to be CONSUMER
		String query = "update `eir-subscription-management-backend`.`account`\r\n" + "SET customer_type='RESIDENTIAL'\r\n"
				+ "WHERE billing_account_id='$billingAccountId';";
		query = query.replace("$billingAccountId", Integer.toString(billingAccountId));
		try {
			GalaxionDBUtil.runUpdateQuery(query);
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	// update the payer brand to be EIR
	public static void updatePayerBrand(int billingAccountID, String brand) {
		String query = "UPDATE `eir-payment-center-backend`.payer SET brand='$brand' WHERE billing_account_id='$billingAccountId';";
		query = query.replace("$billingAccountId", Integer.toString(billingAccountID));
		query = query.replace("$brand", brand);

		try {
			GalaxionDBUtil.runUpdateQuery(query);
		} catch (SQLException e1) {
			e1.printStackTrace();
		}
	}
}
