package microservices.backend.eir_order_management_backend.api;

import framework.data_providers.addons.B2BBillableSetting;
import framework.test_data.generic.Person;
import io.restassured.response.Response;
import microservices.backend.eir_catalog_core_backend.enums.B2BAccountType;
import microservices.backend.eir_subscription_management_backend.SubscriptionManagementDAO;
import microservices.frontend.eir_crm_ui_frontend.dto.reactivate.ReactivateServiceDTO;
import testCases.testObjects.orders.B2BOrder;
import utilities.api.RestAssuredUtil;
import utilities.galaxion.environments.EnvironmentDirectory;
import utilities.generic.pojo_generation.pojo_to_json.PojoToJsonConverter;
import utilities.test.config_readers.JSONRequestManager;

public class OrderManagementAPI {

	private static String requestFolder = "requests/json/acquisitions";

	public static String getUniqueOrderNumber() {
		String url = EnvironmentDirectory.getOrderManagementAPI() + "/order/number";
		Response createResp = RestAssuredUtil.galaxionPost(url, null, null);
		return createResp.asString();
	}

	public static String createB2BAccount(String parentAccountID, B2BBillableSetting orderType, B2BAccountType accountType) {

		String requestTemplate = null;

		int numSiblings;
		String parentName;

		// select the payload template based on the order type
		if (orderType == B2BBillableSetting.BILLABLE) {
			requestTemplate = requestFolder + "/b2b/b2b_create_billable.json";
		} else if (orderType == B2BBillableSetting.BILLABLE_NULL_PAYER) {
			requestTemplate = requestFolder + "/b2b/b2b_create_billable_nullpayer.json";
		} else if (orderType == B2BBillableSetting.UNBILLABLE) {
			requestTemplate = requestFolder + "/b2b/b2b_create_unbillable.json";
		}

		B2BOrder order = new B2BOrder();
		order.randomize();

		// if a parent account ID is provided, use the parent name
		if (parentAccountID != null) {
			parentName = SubscriptionManagementDAO.getB2BAccountByID(Integer.parseInt(parentAccountID)).getName();
			numSiblings = SubscriptionManagementDAO.getB2BAccountsForParent(Integer.parseInt(parentAccountID)).size();
			order.setCompanyName(SubscriptionManagementDAO.getCompanyByID(Integer.parseInt(parentAccountID)).getName());
			order.setAccountName(parentName + "." + (numSiblings + 1));
		}

		String marketSegment = null;
		String accountTypeString = null;

		if (accountType == B2BAccountType.GOVERNMENT) {
			accountTypeString = "GOVERNMENT";
			marketSegment = "GOVERNMENT_SME";
		} else if (accountType == B2BAccountType.CORPORATE_OR_EBU) {
			accountTypeString = "CORPORATE_OR_EBU";
			marketSegment = "ENTERPRISE_SME";
		}

		// generate the payload for OM
		String orderPayload = JSONRequestManager.getJSONRequest(requestTemplate);
		orderPayload = orderPayload.replace("$eventTime", order.getEventTime());
		orderPayload = orderPayload.replace("$accountName", order.getAccountName());
		orderPayload = orderPayload.replace("$reference", order.getOrderReference());
		orderPayload = orderPayload.replace("$companyName", order.getCompanyName());
		orderPayload = orderPayload.replace("$accountType", accountTypeString);
		orderPayload = orderPayload.replace("$marketSegment", marketSegment);

		if (parentAccountID == null) {
			orderPayload = orderPayload.replace("\"$accountID\"", "null");
		} else {
			orderPayload = orderPayload.replace("\"$accountID\"", parentAccountID);
		}

		// owner details
		Person owner = order.getOwner();
		orderPayload = orderPayload.replace("$ownerLandline", owner.getContactPhoneNumber());
		orderPayload = orderPayload.replace("$ownerMobile", owner.getMobilePhoneNumber());
		orderPayload = orderPayload.replace("$ownerFirstName", owner.getFirstName());
		orderPayload = orderPayload.replace("$ownerLastName", owner.getLastName());
		orderPayload = orderPayload.replace("$ownerEmail", owner.getEmailAddress());
		orderPayload = orderPayload.replace("$ownerDeliveryLine1", owner.getDeliveryAddress().getAddressLine1());
		orderPayload = orderPayload.replace("$ownerDeliveryLine2", owner.getDeliveryAddress().getAddressLine2Str());
		orderPayload = orderPayload.replace("$ownerDeliveryLine3", owner.getDeliveryAddress().getAddressLine3Str());
		orderPayload = orderPayload.replace("$ownerDeliveryTown", owner.getDeliveryAddress().getTown());
		orderPayload = orderPayload.replace("$ownerDeliveryCounty", owner.getDeliveryAddress().getCounty());
		orderPayload = orderPayload.replace("$ownerDeliveryEircode", owner.getDeliveryAddress().getEircode());

		// payer details
		Person payer = order.getPayer();
		orderPayload = orderPayload.replace("$payerLandline", payer.getContactPhoneNumber());
		orderPayload = orderPayload.replace("$payerMobile", payer.getMobilePhoneNumber());
		orderPayload = orderPayload.replace("$payerFirstName", payer.getFirstName());
		orderPayload = orderPayload.replace("$payerLastName", payer.getLastName());
		orderPayload = orderPayload.replace("$payerEmail", payer.getEmailAddress());
		orderPayload = orderPayload.replace("$payerBillingLine1", payer.getBillingAddress().getAddressLine1());
		orderPayload = orderPayload.replace("$payerBillingLine2", payer.getBillingAddress().getAddressLine2Str());
		orderPayload = orderPayload.replace("$payerBillingLine3", payer.getBillingAddress().getAddressLine3Str());
		orderPayload = orderPayload.replace("$payerBillingTown", payer.getBillingAddress().getTown());
		orderPayload = orderPayload.replace("$payerBillingCounty", payer.getBillingAddress().getCounty());
		orderPayload = orderPayload.replace("$payerBillingEircode", payer.getBillingAddress().getEircode());

		// account details
		orderPayload = orderPayload.replace("$customerCostCenter", order.getCustomerCostCenter());
		orderPayload = orderPayload.replace("$groupICID", order.getGroupICID());
		orderPayload = orderPayload.replace("\"$vpnAccountNumber\"", order.getVpnAccountNumber());
		orderPayload = orderPayload.replace("$salesforceCustomerId", order.getSalesforceCustomerId());
		orderPayload = orderPayload.replace("\"$salesforceCaseNumber\"", order.getSalesforceCaseNumber());

		// tax details
		orderPayload = orderPayload.replace("$taxNumber", order.getTaxNumber());
		orderPayload = orderPayload.replace("$taxCertificateNumber", order.getTaxCertNumber());
		orderPayload = orderPayload.replace("$companyRegistrationNumber", order.getCompanyRegistrationNumber());

		// device enrollment
		orderPayload = orderPayload.replace("$appleDeviceEnrollment", order.getAppleEnrollmentID());
		orderPayload = orderPayload.replace("$samsungDeviceEnrollment", order.getSamsungEnrollmentID());
		orderPayload = orderPayload.replace("$googleDeviceEnrollment", order.getGoogleEnrollmentID());

		String url = EnvironmentDirectory.getOrderManagementAPI() + "/event/product_order";

		// send the payload to the URL
		Response createResp = RestAssuredUtil.galaxionPost(url, orderPayload, null);
		System.out.println(createResp.asString());

		return order.getOrderReference();
	}

	
	public static Response reactivateSubscriber(int subscriptionID) {
		String url = EnvironmentDirectory.getOrderManagementAPI() + "/event/service";
		ReactivateServiceDTO dto = new ReactivateServiceDTO(subscriptionID);
		return RestAssuredUtil.galaxionPost(url, PojoToJsonConverter.getJSON(dto),null);
	}
}
