package microservices.frontend.eir_b2b_crm_ui_frontend;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import json.common.AddressDTO;
import microservices.backend.eir_catalog_core_backend.enums.B2BAccountType;
import microservices.backend.eir_catalog_core_backend.enums.OfferCode;
import microservices.backend.eir_contact_management_backend.enums.ContactType;
import microservices.backend.eir_document_management_backend.enums.DocumentType;
import microservices.backend.eir_subscription_management_backend.dto.SubsMgmtMsisdnSwapDTO;
import microservices.backend.eir_subscription_management_backend.enums.CreditClass;
import microservices.backend.eir_subscription_management_backend.enums.MarketSegment;
import microservices.backend.eir_subscription_management_backend.enums.TerminationReason;
import microservices.backend.keycloak.api.KeycloakAPI;
import microservices.backend.keycloak.dao.KeycloakDAO;
import microservices.backend.keycloak.data_model.Client;
import microservices.backend.keycloak.enums.Realm;
import microservices.frontend.common_ui.dto.b2b_crm.ChangeOfferB2BCrmUiDTO;
import microservices.frontend.common_ui.dto.b2b_crm.UpdateContactTypeDTO;
import microservices.frontend.eir_b2b_crm_ui_frontend.dto.TriggerSimSwapB2BDTO;
import microservices.frontend.eir_b2b_crm_ui_frontend.dto.account_level_barring.AccountBarringDTO;
import microservices.frontend.eir_b2b_crm_ui_frontend.dto.create_account.CreateAccountDTO;
import microservices.frontend.eir_b2b_crm_ui_frontend.dto.create_contact.CreateContactDTO;
import microservices.frontend.eir_b2b_crm_ui_frontend.dto.create_hardware_fund.CreateHardwareFundDTO;
import microservices.frontend.eir_b2b_crm_ui_frontend.dto.create_subscriber.CatalogOffer;
import microservices.frontend.eir_b2b_crm_ui_frontend.dto.create_subscriber.Contact;
import microservices.frontend.eir_b2b_crm_ui_frontend.dto.create_subscriber.CreateSubscriptionDTO;
import microservices.frontend.eir_b2b_crm_ui_frontend.dto.create_subscriber.SubscriberDetails;
import microservices.frontend.eir_b2b_crm_ui_frontend.dto.renew_hardware_fund.RenewHardwareFundDTO;
import microservices.frontend.eir_b2b_crm_ui_frontend.dto.schedule_termination.ScheduleTerminationDTO;
import microservices.frontend.eir_b2b_crm_ui_frontend.dto.update_account_general_details.UpdateGeneralDetailsDTO;
import microservices.frontend.eir_b2b_crm_ui_frontend.dto.update_billing_details.UpdateBillingDetailsDTO;
import microservices.frontend.eir_b2b_crm_ui_frontend.dto.update_company_details.UpdateCompanyDetailsDTO;
import microservices.frontend.eir_b2b_crm_ui_frontend.dto.update_device_enrollment.DeviceEnrollmentDTO;
import microservices.frontend.eir_b2b_crm_ui_frontend.dto.update_main_account_details.UpdateAccountDetailsDTO;
import microservices.frontend.eir_b2b_crm_ui_frontend.dto.update_payment_term.UpdatePaymentTermDTO;
import microservices.frontend.eir_b2b_crm_ui_frontend.dto.update_tax_details.TaxDetailsDTO;
import microservices.frontend.eir_crm_ui_frontend.dto.requests.customer_history.AddCommentDTO;
import microservices.frontend.eir_crm_ui_frontend.dto.requests.customer_history.AddNoteDTO;
import testCases.Services;
import testCases.eir.b2b.requests.validate_iban.AddSepaPaymentMethodDTO;
import testCases.eir.b2b.requests.validate_iban.ValidateIbanRequestDTO;
import utilities.api.RestAssuredUtil;
import utilities.galaxion.environments.EnvironmentDirectory;
import utilities.galaxion.environments.LoginCredentials;
import utilities.generic.api.APITransaction;
import utilities.generic.api.HTTPMethod;
import utilities.generic.pojo_generation.pojo_to_json.PojoToJsonConverter;

public class B2BCRMAPI {

	/**
	 * Generate an authentication token for a B2B user
	 * 
	 * @return token string
	 */
	public static String getToken() {
		LoginCredentials login=EnvironmentDirectory.getB2BAgentLogin();
		Client c = KeycloakDAO.getClient(Services.B2B_CRM_UI, Realm.eir);
		return KeycloakAPI.getToken(c.getClientId(), c.getSecret(), Realm.eir, login.getUsername(), login.getPassword());
	}

	/*------------------------------------------------------------------
	 * Search screen
	 ------------------------------------------------------------------- */

	/**
	 * Account Search
	 * 
	 * Ref: B2B_A_SE_01
	 */
	public static APITransaction search(String token, String customerAccountName, String firstName, String lastName, String contactPhoneNumber,
			String contactMobileNumber, String mobileSubscriptionNumber, String billingAccountId, String email, String iccid) {

		// construct the URL
		String baseURL = EnvironmentDirectory.getB2BCRMAPI() + "/api/v1/search/account?";

		String url = baseURL;

		if (customerAccountName != null) {
			url = url + "&customerAccountName=" + customerAccountName;
		}

		if (firstName != null) {
			url = url + "&firstName=" + firstName;
		}

		if (lastName != null) {
			url = url + "&lastName=" + lastName;
		}

		if (contactPhoneNumber != null) {
			url = url + "&contactPhoneNumber=" + contactPhoneNumber;
		}

		if (contactMobileNumber != null) {
			url = url + "&contactMobileNumber=" + contactMobileNumber;
		}

		if (mobileSubscriptionNumber != null) {
			url = url + "&mobileSubscriptionNumber=" + mobileSubscriptionNumber;
		}

		if (billingAccountId != null) {
			url = url + "&billingAccountId=" + billingAccountId;
		}

		if (email != null) {
			url = url + "&email=" + email;
		}

		if (iccid != null) {
			url = url + "&iccid=" + iccid;
		}

		url = url.replace("?&", "?");

		// trigger the GET request
		return new APITransaction(url, RestAssuredUtil.galaxionGet(url, token));
	}

	/**
	 * Account Search
	 * 
	 * Ref: B2B_A_SE_01 (alternative)
	 */
	public static APITransaction search(String token, String customerAccountName, String firstName, String lastName, String contactPhoneNumber,
			String contactMobileNumber, String mobileSubscriptionNumber, int billingAccountId, String email, String iccid) {
		return search(token, customerAccountName, firstName, lastName, contactPhoneNumber, contactMobileNumber, mobileSubscriptionNumber,
				Integer.toString(billingAccountId), email, iccid);
	}

	/**
	 * Order Search
	 * 
	 * Ref: B2B_A_SE_02
	 */
	public static APITransaction orderSearch(String token, String orderReference) {
		String url = EnvironmentDirectory.getB2BCRMAPI() + "/api/v1/orders?reference=" + orderReference + "&page=0";
		Response r = RestAssuredUtil.galaxionGet(url, token);
		return new APITransaction(url, r);
	}

	/*------------------------------------------------------------------
	 * Account: General tab
	 ------------------------------------------------------------------- */
	/**
	 * Get account hierarchy
	 * 
	 * Ref: B2B_A_GE_01
	 */
	public static APITransaction getAccountHierarchy(String token, int accountID) {
		String url = EnvironmentDirectory.getB2BCRMAPI() + "/api/v1/account/" + accountID + "/hierarchy";
		return triggerGetRequest(url, token);
	}

	/**
	 * Get account
	 * 
	 * Ref: B2B_A_GE_02
	 */
	public static APITransaction getAccount(String token, int accountID) {
		String url = EnvironmentDirectory.getB2BCRMAPI() + "/api/v1/account/" + accountID;
		return triggerGetRequest(url, token);
	}

	/**
	 * Set account to unbillable
	 * 
	 * Ref: B2B_A_GE_03
	 */
	public static APITransaction setAccountToUnbillable(String token, int accountID) {
		String url = EnvironmentDirectory.getB2BCRMAPI() + "/api/v1/account/" + accountID + "/billing/disable";
		return new APITransaction(url, RestAssuredUtil.galaxionPut(url, null, token));
	}

	/**
	 * Update account main details
	 * 
	 * Ref: B2B_A_GE_04
	 */
	public static APITransaction updateMainAccountDetails(String token, int accountID, CreditClass creditClass, MarketSegment marketSegment,
			String customerAccountName, B2BAccountType accountType) {
		String url = EnvironmentDirectory.getB2BCRMAPI() + "/api/v1/account/" + accountID + "/main-details";
		UpdateAccountDetailsDTO dto = new UpdateAccountDetailsDTO(creditClass, marketSegment, customerAccountName, accountType);
		String payload = PojoToJsonConverter.getJSON(dto);
		Response r = RestAssuredUtil.galaxionPut(url, payload, token);
		return new APITransaction(url, payload, r);
	}

	/**
	 * Update account general details
	 * 
	 * Ref: B2B_A_GE_05
	 */
	public static APITransaction updateAccountGeneralDetails(String token, int accountID, String accountManager, String agreementDuration,
			String customerCostCenter, String groupIcid, String indoorCoverageSolutionDate, String salesforceCaseNumber, String salesforceCustomerId,
			String vpnAccountNumber) {
		String url = EnvironmentDirectory.getB2BCRMAPI() + "/api/v1/account/" + accountID + "/attributes";
		UpdateGeneralDetailsDTO dto = new UpdateGeneralDetailsDTO(accountManager, agreementDuration, customerCostCenter, groupIcid, indoorCoverageSolutionDate,
				salesforceCaseNumber, salesforceCustomerId, vpnAccountNumber);
		String payload = PojoToJsonConverter.getJSON(dto);
		Response r = RestAssuredUtil.galaxionPut(url, payload, token);
		return new APITransaction(url, payload, r);
	}

	/**
	 * Update company details
	 * 
	 * Ref: B2B_A_GE_06
	 */
	public static APITransaction updateCompanyDetails(String token, int companyId, String name, String registrationNumber) {
		String url = EnvironmentDirectory.getB2BCRMAPI() + "/api/v1/company/" + companyId;
		UpdateCompanyDetailsDTO dto = new UpdateCompanyDetailsDTO(name, registrationNumber);
		String payload = PojoToJsonConverter.getJSON(dto);
		Response r = RestAssuredUtil.galaxionPut(url, payload, token);
		return new APITransaction(url, payload, r);
	}

	/**
	 * Update device enrollment
	 * 
	 * Ref: B2B_A_GE_07
	 */
	public static APITransaction updateDeviceEnrollment(String token, int accountID, String appleEnrollment, String samsungEnrollment,
			String googleEnrollment) {

		// construct the URL
		String url = EnvironmentDirectory.getB2BCRMAPI() + "/api/v1/account/" + accountID + "/device-enrollment";

		// populate the list with the input values
		ArrayList<DeviceEnrollmentDTO> enrollments = new ArrayList<DeviceEnrollmentDTO>();
		enrollments.add(new DeviceEnrollmentDTO("APPLE", appleEnrollment));
		enrollments.add(new DeviceEnrollmentDTO("SAMSUNG", samsungEnrollment));
		enrollments.add(new DeviceEnrollmentDTO("GOOGLE", googleEnrollment));

		// convert the list to a json message
		String payload = PojoToJsonConverter.getJSON(enrollments);

		// put and return
		Response r = RestAssuredUtil.galaxionPut(url, payload, token);
		return new APITransaction(url, payload, r);
	}

	/**
	 * Update tax details
	 * 
	 * Ref: B2B_A_GE_08
	 */
	public static APITransaction updateTaxDetails(String token, int accountID, String taxNumber, String taxCertificateNumber, String taxCategory,
			String taxExemptionDocumentType, String taxExemptionStartDate, String taxExemptionEndDate) {

		// construct the URL
		String url = EnvironmentDirectory.getB2BCRMAPI() + "/api/v1/account/" + accountID + "/tax-details";

		TaxDetailsDTO dto = new TaxDetailsDTO(taxNumber, taxCertificateNumber, taxCategory, taxExemptionDocumentType, taxExemptionStartDate,
				taxExemptionEndDate);

		// convert the list to a json message
		String payload = PojoToJsonConverter.getJSON(dto);

		// put and return
		Response r = RestAssuredUtil.galaxionPut(url, payload, token);
		return new APITransaction(url, payload, r);
	}

	/*------------------------------------------------------------------
	 * Account: Payments & Billing tab
	 ------------------------------------------------------------------- */
	/**
	 * Get payment methods
	 * 
	 * Ref: B2B_A_PY_01
	 */
	public static APITransaction getPaymentMethods(String token, int billingAccountID) {
		String url = EnvironmentDirectory.getB2BCRMAPI() + "/api/v1/payment/" + billingAccountID + "/payment/methods";
		return triggerGetRequest(url, token);
	}

	/**
	 * Update billing details
	 * 
	 * Ref: B2B_A_PY_02
	 */
	public static APITransaction updateBillingDetails(String token, int accountID, String billDeliveryType, boolean billItemised, int billCycleId,
			boolean billAnalyserConsent) {
		String url = EnvironmentDirectory.getB2BCRMAPI() + "/api/v1/account/" + accountID + "/billing";

		UpdateBillingDetailsDTO dto = new UpdateBillingDetailsDTO(billDeliveryType, billItemised, billCycleId, billAnalyserConsent);

		// convert the object to a json message
		String payload = PojoToJsonConverter.getJSON(dto);

		// put and return
		Response r = RestAssuredUtil.galaxionPut(url, payload, token);
		return new APITransaction(url, payload, r);
	}

	/**
	 * Update payment term
	 * 
	 * Ref: B2B_A_PY_03
	 */
	public static APITransaction updatePaymentTerm(String token, int accountID, int paymentTerm) {
		String url = EnvironmentDirectory.getB2BCRMAPI() + "/api/v1/account/" + accountID + "/payment/term";

		UpdatePaymentTermDTO dto = new UpdatePaymentTermDTO(paymentTerm);

		// convert the object to a json message
		String payload = PojoToJsonConverter.getJSON(dto);

		// put and return
		Response r = RestAssuredUtil.galaxionPut(url, payload, token);
		return new APITransaction(url, payload, r);
	}

	/**
	 * Validate IBAN
	 * 
	 * Ref: B2B_A_PY_04
	 */
	public static APITransaction validateIBAN(String token, String iban) {
		String url = EnvironmentDirectory.getB2BCRMAPI() + "/api/v1/payment/iban/validate";
		ValidateIbanRequestDTO dto = new ValidateIbanRequestDTO(iban);
		String payload = PojoToJsonConverter.getJSON(dto);
		Response r = RestAssuredUtil.galaxionPost(url, payload, token);
		return new APITransaction(url, r);
	}

	/**
	 * Add SEPA payment method
	 * 
	 * Ref: B2B_A_PY_05
	 */
	public static APITransaction addSepaPaymentMethod(String token, int billingAccountId, String accountOwner, String bankName, String bic, String branchName,
			String iban, String mandateSignedAt) {
		String url = EnvironmentDirectory.getB2BCRMAPI() + "/api/v1/payment/" + billingAccountId + "/payment-method";
		AddSepaPaymentMethodDTO dto = new AddSepaPaymentMethodDTO(accountOwner, bankName, bic, branchName, iban, mandateSignedAt);
		String payload = PojoToJsonConverter.getJSON(dto);
		Response r = RestAssuredUtil.galaxionPost(url, payload, token);
		return new APITransaction(url, payload, r);
	}

	/**
	 * Change default payment method
	 * 
	 * Ref: B2B_A_PY_06
	 */
	public static APITransaction updateDefaultPaymentMethod(String token, int billingAccountId, int paymentMethodId) {
		String url = EnvironmentDirectory.getB2BCRMAPI() + "/api/v1/payment/" + billingAccountId + "/default/" + paymentMethodId;
		String payload = null;
		Response r = RestAssuredUtil.galaxionPut(url, payload, token);
		return new APITransaction(HTTPMethod.PUT, url, payload, r);
	}

	/**
	 * Delete SEPA payment method
	 * 
	 * Ref: B2B_A_PY_07
	 */
	public static APITransaction deleteSepaPaymentMethod(String token, int billingAccountId, int paymentMethodId) {
		String url = EnvironmentDirectory.getB2BCRMAPI() + "/api/v1/payment/" + billingAccountId + "/payment-method/" + paymentMethodId;
		Response r = RestAssuredUtil.galaxionDelete(url, token);
		return new APITransaction(url, r);
	}

	/*------------------------------------------------------------------
	 * Account: Hardware Fund tab
	 ------------------------------------------------------------------- */
	/**
	 * Get hardware fund
	 * 
	 * Ref: B2B_A_HW_01
	 */
	public static APITransaction getHardwareFund(String token, int hardwareFundID) {
		String url = EnvironmentDirectory.getB2BCRMAPI() + "/api/v1/hardware-fund/" + hardwareFundID;
		return triggerGetRequest(url, token);
	}

	/**
	 * Get hardware fund adjustment reasons
	 * 
	 * Ref: B2B_A_HW_02
	 */
	public static APITransaction getRefHardwareFundAdjustmentReasons(String token, B2BAccountType type) {
		String url = EnvironmentDirectory.getB2BCRMAPI() + "/api/v1/adjustments/reasons?adjustmentType=HARDWARE_POSTPAY&customerTypeCode=" + type;
		return triggerGetRequest(url, token);
	}

	/**
	 * Create hardware fund
	 * 
	 * Ref: B2B_A_HW_03
	 */
	public static APITransaction createHardwareFund(String token, int accountID, int amount, int term, String comment) {
		String url = EnvironmentDirectory.getB2BCRMAPI() + "/api/v1/account/" + accountID + "/hardware-fund/create";
		CreateHardwareFundDTO dto = new CreateHardwareFundDTO(amount, term, comment);
		String payload = PojoToJsonConverter.getJSON(dto);
		Response r = RestAssuredUtil.galaxionPost(url, payload, token);
		return new APITransaction(HTTPMethod.POST, url, payload, r);
	}

	/**
	 * Renew hardware fund
	 * 
	 * Ref: B2B_A_HW_04
	 */
	public static APITransaction renewHardwareFund(String token, int accountID, int amount, int term, int hardwareFundId) {
		String url = EnvironmentDirectory.getB2BCRMAPI() + "/api/v1/account/" + accountID + "/hardware-fund/renew";
		String comment = "Auto hardware fund renewal " + System.currentTimeMillis();
		RenewHardwareFundDTO dto = new RenewHardwareFundDTO(amount, term, comment, hardwareFundId);
		Response r = RestAssuredUtil.galaxionPost(url, PojoToJsonConverter.getJSON(dto), token);
		return new APITransaction(url, r);
	}

	/*------------------------------------------------------------------
	 * Account: Transactions tab
	 ------------------------------------------------------------------- */
	/**
	 * Get transaction history
	 * 
	 * Ref: B2B_A_TR_01
	 * 
	 */
	public static APITransaction getTransactionHistory(String token, int billingAccountID) {
		String url = EnvironmentDirectory.getB2BCRMAPI() + "/api/v1/transactions/" + billingAccountID + "/search?page=0";
		return triggerGetRequest(url, token);
	}

	/**
	 * Get ref adjustment reason codes
	 * 
	 * Ref: B2B_A_TR_02
	 * 
	 */
	public static APITransaction getRefAdjustmentReasons(String token, B2BAccountType type) {
		String url = EnvironmentDirectory.getB2BCRMAPI() + "/api/v1/adjustments/reasons?adjustmentType=POSTPAY&customerTypeCode=" + type;
		return triggerGetRequest(url, token);
	}

	/**
	 * Get adjustment
	 * 
	 * Ref: B2B_A_TR_03
	 * 
	 */
	public static APITransaction getAdjustment(String token, String adjustmentReference) {
		String url = EnvironmentDirectory.getB2BCRMAPI() + "/api/v1/transactions/" + adjustmentReference + "/details/adjustment";
		return triggerGetRequest(url, token);
	}

	/*------------------------------------------------------------------
	 * Account: Documents tab
	 ------------------------------------------------------------------- */
	/**
	 * Get documents for an account
	 * 
	 * Ref: B2B_A_DO_01
	 */
	public static APITransaction getDocuments(String token, int accountID) {
		String url = EnvironmentDirectory.getB2BCRMAPI() + "/api/v1/documents/search?page=0&query=accountId==" + accountID;
		return triggerGetRequest(url, token);
	}

	/**
	 * Get document types
	 * 
	 * Ref: B2B_A_DO_02
	 */
	public static APITransaction getRefDocumentTypes(String token) {
		String url = EnvironmentDirectory.getB2BCRMAPI() + "/api/v1/documents/types";
		return triggerGetRequest(url, token);
	}

	/**
	 * Upload a document
	 * 
	 * Ref: B2B_A_DO_03
	 */
	public static APITransaction uploadDocument(String token, int accountID, DocumentType documentType, String filepath) {

		// create a file object
		File file = new File(filepath);

		// construct the URL
		String endpoint = EnvironmentDirectory.getB2BCRMAPI() + "/api/v1/documents/" + accountID + "?documentType=" + documentType.toString();

		System.out.println("DocumentUploader posting file " + filepath + " to \n --> " + endpoint);

		// call the API to post the file
		Response r = RestAssuredUtil.galaxionPostFile(endpoint, token, file);

		return new APITransaction(endpoint, r);
	}

	/**
	 * Delete a document
	 * 
	 * Ref: B2B_A_DO_04
	 */
	public static APITransaction deleteDocument(String token, String documentName) {
		String url = EnvironmentDirectory.getB2BCRMAPI() + "/api/v1/documents/" + documentName;
		Response r = RestAssuredUtil.galaxionDelete(url, token);
		return new APITransaction(url, r);
	}

	/**
	 * Download a document
	 * 
	 * Ref: B2B_A_DO_05
	 */
	public static APITransaction downloadDocument(String token, String documentName) {
		String url = EnvironmentDirectory.getB2BCRMAPI() + "/api/v1/documents/download/" + documentName;
		return triggerGetRequest(url, token);
	}

	/*------------------------------------------------------------------
	 * Account: Customer History
	 ------------------------------------------------------------------- */
	/**
	 * Get customer history filters
	 * 
	 * Ref: B2B_A_CH_01
	 */
	public static APITransaction getCustomerHistoryFilters(String token, int accountID) {
		String url = EnvironmentDirectory.getB2BCRMAPI() + "/api/v1/customer-history/histories/" + accountID + "/filters";
		return triggerGetRequest(url, token);
	}

	/**
	 * Get customer history
	 * 
	 * Ref: B2B_A_CH_02
	 */
	public static APITransaction getCustomerHistory(String token, int accountID) {
		String url = EnvironmentDirectory.getB2BCRMAPI() + "/api/v1/customer-history/histories/search?query=(accountId==" + accountID
				+ ");createdAt%3E=2021-10-08&page=0";
		return new APITransaction(url, RestAssuredUtil.galaxionGetNoUrlEncoding(url, token));
	}

	/**
	 * Add a note
	 * 
	 * Ref: B2B_A_CH_03
	 */
	public static APITransaction addNote(String token, int accountID, String content, String serviceId) {
		String url = EnvironmentDirectory.getB2BCRMAPI() + "/api/v1/customer-history/notes";
		AddNoteDTO dto = new AddNoteDTO(accountID, content, serviceId);
		String payload = PojoToJsonConverter.getJSON(dto);
		Response r = RestAssuredUtil.galaxionPost(url, payload, token);
		return new APITransaction(HTTPMethod.POST, url, payload, r);
	}

	/**
	 * Get customer history item
	 * 
	 * Ref: B2B_A_CH_04
	 */
	public static APITransaction getCustomerHistoryItem(String token, int customerHistoryId) {
		String url = EnvironmentDirectory.getB2BCRMAPI() + "/api/v1/customer-history/histories/" + customerHistoryId;
		return triggerGetRequest(url, token);
	}

	/**
	 * Get email notification subjects
	 * 
	 * Ref: B2B_A_CH_05
	 */
	public static APITransaction getEmailNotificationSubjects(String token, int accountId) {
		String url = EnvironmentDirectory.getB2BCRMAPI() + "/api/v1/customer-history/email/" + accountId + "/subjects";
		return triggerGetRequest(url, token);
	}

	/**
	 * Get email notifications
	 * 
	 * Ref: B2B_A_CH_06
	 * 
	 * fromDate should be in format "YYYY-MM-dd"
	 */
	public static APITransaction getEmailNotifications(String token, int accountId, String fromDate) {
		String url = EnvironmentDirectory.getB2BCRMAPI() + "/api/v1/customer-history/email/" + accountId + "?from=" + fromDate + "&page=0";
		return triggerGetRequest(url, token);
	}

	/**
	 * Get SMS notifications
	 * 
	 * Ref: B2B_A_CH_07
	 */
	public static APITransaction getSmsNotifications(String token, int accountId, String fromDate) {
		String url = EnvironmentDirectory.getB2BCRMAPI() + "/api/v1/customer-history/sms/" + accountId + "?from=" + fromDate + "&page=0";
		return triggerGetRequest(url, token);
	}

	/**
	 * Add a comment to history item
	 * 
	 * Ref: B2B_A_CH_08
	 */
	public static APITransaction addCommentToHistoryItem(String token, int historyId, String comment) {
		String url = EnvironmentDirectory.getB2BCRMAPI() + "/api/v1/customer-history/comments";
		AddCommentDTO dto = new AddCommentDTO(historyId, comment);
		String payload = PojoToJsonConverter.getJSON(dto);
		Response r = RestAssuredUtil.galaxionPost(url, payload, token);
		return new APITransaction(HTTPMethod.POST, url, payload, r);
	}

	/*------------------------------------------------------------------
	 * Account: Manage Addons
	 ------------------------------------------------------------------- */

	/**
	 * Get active account level addons
	 * 
	 * Ref: B2B_A_AO_01
	 */
	public static APITransaction getActiveAccountLevelAddons(String token, int accountID) {
		String url = EnvironmentDirectory.getB2BCRMAPI() + "/api/v1/account/" + accountID + "/addons";
		return triggerGetRequest(url, token);
	}

	/**
	 * Get available account level addons
	 * 
	 * Ref: B2B_A_AO_02
	 */
	public static APITransaction getAvailableAccountLevelAddons(String token, B2BAccountType type) {
		String url = EnvironmentDirectory.getB2BCRMAPI() + "/api/v1/account/addons/available?customerType=" + type;
		return triggerGetRequest(url, token);
	}

	/*------------------------------------------------------------------
	 * Account: Manage Equipments
	 ------------------------------------------------------------------- */
	/**
	 * Get available handsets
	 * 
	 * Ref: B2B_A_EQ_01
	 */
	public static APITransaction getAvailableHandsets(String token) {
		String url = EnvironmentDirectory.getB2BCRMAPI() + "/api/v1/handset?itemTypes=HANDSET";
		return triggerGetRequest(url, token);
	}

	/**
	 * Get available handsets and dongles
	 * 
	 * Ref: B2B_A_EQ_02
	 */
	public static APITransaction getAvailableHandsetsAndDongles(String token) {
		String url = EnvironmentDirectory.getB2BCRMAPI() + "/api/v1/handset?itemTypes=HANDSET&itemTypes=DONGLE";
		return triggerGetRequest(url, token);
	}

	/*------------------------------------------------------------------
	 * Account: Manage account level barring
	 ------------------------------------------------------------------- */

	/**
	 * Add network level barring
	 * 
	 * Ref: B2B_A_BR_01
	 */
	public static APITransaction addAccountLevelFullNetworkBarring(String token, int accountID) {
		String url = EnvironmentDirectory.getB2BCRMAPI() + "/api/v1/account/" + accountID + "/barring";
		AccountBarringDTO dto = new AccountBarringDTO("BAR_FULL_NETWORK","ADD");
		Response r = RestAssuredUtil.galaxionPost(url, PojoToJsonConverter.getJSON(dto), token);
		return new APITransaction(url, r);
	}
	
	/**
	 * Remove network level barring
	 * 
	 * Ref: B2B_A_BR_02
	 */
	public static APITransaction removeAccountLevelFullNetworkBarring(String token, int accountID) {
		String url = EnvironmentDirectory.getB2BCRMAPI() + "/api/v1/account/" + accountID + "/barring";
		AccountBarringDTO dto = new AccountBarringDTO("BAR_FULL_NETWORK","REMOVE");
		Response r = RestAssuredUtil.galaxionPost(url, PojoToJsonConverter.getJSON(dto), token);
		return new APITransaction(url, r);
	}

	/*------------------------------------------------------------------
	 * Account: Orders
	 ------------------------------------------------------------------- */
	/**
	 * Get order history
	 * 
	 * Ref: B2B_A_OR_01
	 */
	public static APITransaction getOrderHistory(String token, int accountID) {
		String url = EnvironmentDirectory.getB2BCRMAPI() + "/api/v1/orders?page=0&accountId=" + accountID;
		return triggerGetRequest(url, token);
	}

	/**
	 * Get filtered order history
	 * 
	 * Ref: B2B_A_OR_02
	 */
	public static APITransaction getFilteredOrderHistory(String token, int accountID) {
		String url = EnvironmentDirectory.getB2BCRMAPI() + "/api/v1/orders?eventType=CREATE_SERVICE_ORDER&page=0&accountId=" + accountID;
		return triggerGetRequest(url, token);
	}

	/**
	 * Get order
	 * 
	 * Ref: B2B_A_OR_03
	 */
	public static APITransaction getOrder(String token, String orderReference) {
		String url = EnvironmentDirectory.getB2BCRMAPI() + "/api/v1/orders/" + orderReference;
		return triggerGetRequest(url, token);
	}

	/*------------------------------------------------------------------
	 * Account: Contacts
	 ------------------------------------------------------------------- */

	/**
	 * Get account contacts
	 * 
	 * Ref: B2B_A_CO_01
	 */
	public static APITransaction getAccountContacts(String token, int accountID) {
		String url = EnvironmentDirectory.getB2BCRMAPI() + "/api/v1/account/" + accountID + "/contacts?page=0";
		return triggerGetRequest(url, token);
	}

	/**
	 * Get ref contact types
	 * 
	 * Ref: B2B_A_CO_02
	 */
	public static APITransaction getRefContactTypes(String token) {
		String url = EnvironmentDirectory.getB2BCRMAPI() + "/api/v1/contact-type";
		return triggerGetRequest(url, token);
	}

	/**
	 * Get all contacts
	 * 
	 * Ref: B2B_A_CO_03
	 */
	public static APITransaction getAllContacts(String token, int accountID) {
		String url = EnvironmentDirectory.getB2BCRMAPI() + "/api/v1/account/" + accountID + "/contacts/all";
		return triggerGetRequest(url, token);
	}

	/**
	 * Delete a contact
	 * 
	 * Ref: B2B_A_CO_04
	 */
	public static APITransaction deleteContact(String token, int accountID, String contactUuid) {
		String url = EnvironmentDirectory.getB2BCRMAPI() + "/api/v1/account/" + accountID + "/contact/" + contactUuid;
		Response r = RestAssuredUtil.galaxionDelete(url, token);
		return new APITransaction(url, r);
	}

	// TODO Ref: B2B_A_CO_05 - Update contact details

	/**
	 * Add a contact
	 * 
	 * Ref: B2B_A_CO_06
	 */
	public static APITransaction addContact(String token, int accountID, String firstName, String lastName, String email, String phoneNumber,
			ContactType contactType, AddressDTO address) {
		String url = EnvironmentDirectory.getB2BCRMAPI() + "/api/v1/account/" + accountID + "/contact";
		CreateContactDTO dto = new CreateContactDTO(firstName, lastName, email, phoneNumber, contactType, address);
		Response r = RestAssuredUtil.galaxionPost(url, PojoToJsonConverter.getJSON(dto), token);
		return new APITransaction(url, r);
	}

	/**
	 * Update contact type
	 * 
	 * Ref: B2B_A_CO_07
	 */
	public static APITransaction updateContactType(String token, int accountID, String contactUuid, ContactType contactType) {
		String url = EnvironmentDirectory.getB2BCRMAPI() + "/api/v1/account/" + accountID + "/contact/" + contactUuid + "/type";
		UpdateContactTypeDTO dto = new UpdateContactTypeDTO(contactType.toString());
		Response r = RestAssuredUtil.galaxionPost(url, PojoToJsonConverter.getJSON(dto), token);
		return new APITransaction(url, r);
	}

	/**
	 * Update address: Eircode lookup
	 * 
	 * Ref: B2B_A_CO_08
	 */
	public static APITransaction eircodeLookup(String token, String eircode) {
		String url = EnvironmentDirectory.getB2BCRMAPI() + "/api/v1/address?eircode=" + eircode;
		return triggerGetRequest(url, token);
	}

	/*------------------------------------------------------------------
	 * Account: Subscriptions
	 ------------------------------------------------------------------- */
	/**
	 * Get subscriptions
	 * 
	 * Ref: B2B_A_SU_01
	 */
	public static APITransaction getSubscriptions(String token, int accountID) {
		String url = EnvironmentDirectory.getB2BCRMAPI() + "/api/v1/search/subscription/" + accountID;
		return triggerGetRequest(url, token);
	}

	/*------------------------------------------------------------------
	 * Service: General tab
	 ------------------------------------------------------------------- */
	/**
	 * Get subscription
	 * 
	 * Ref: B2B_S_GE_01
	 */
	public static APITransaction getSubscription(String token, int subscriptionID) {
		String url = EnvironmentDirectory.getB2BCRMAPI() + "/api/v1/subscription/" + subscriptionID;
		return triggerGetRequest(url, token);
	}

	/**
	 * Get SIM details
	 * 
	 * Ref: B2B_A_SU_02
	 */
	public static APITransaction getSimDetails(String token, String iccid) {
		String url = EnvironmentDirectory.getB2BCRMAPI() + "/api/v1/sim/" + iccid;
		return triggerGetRequest(url, token);
	}

	/*------------------------------------------------------------------
	 * Service: Usage tab
	 ------------------------------------------------------------------- */

	/**
	 * Get ref usage types
	 * 
	 * Ref: B2B_S_US_01
	 */
	public static APITransaction getRefUsageTypes(String token) {
		String url = EnvironmentDirectory.getB2BCRMAPI() + "/api/v1/usage/types";
		return triggerGetRequest(url, token);
	}

	/**
	 * Get usage for service
	 * 
	 * input "period" should be in the format YYYYMM
	 * 
	 * Ref: B2B_S_US_02
	 */
	public static APITransaction getUsage(String token, int serviceId, String period) {
		String url = EnvironmentDirectory.getB2BCRMAPI() + "/api/v1/service/" + serviceId + "/usage/" + period + "?page=0";
		return triggerGetRequest(url, token);
	}

	/**
	 * Get usage summary for service
	 * 
	 * input "period" should be in the format YYYYMM
	 * 
	 * Ref: B2B_S_US_03
	 */
	public static APITransaction getUsageSummary(String token, int serviceId, String period) {
		String url = EnvironmentDirectory.getB2BCRMAPI() + "/api/v1/service/" + serviceId + "/usage/" + period + "/summary";
		return triggerGetRequest(url, token);
	}

	/*------------------------------------------------------------------
	 * Service: MSISDN Swap
	 ------------------------------------------------------------------- */
	/**
	 * Get the next replacement MSISDN
	 * 
	 * Ref: B2B_S_MS_01
	 */
	public static APITransaction getNextMsisdn(String token) {
		String url = EnvironmentDirectory.getB2BCRMAPI() + "/api/v1/next-msisdn";
		Response r = RestAssuredUtil.galaxionGet(url, token);
		return new APITransaction(url, r);
	}

	/**
	 * Submit MSISDN swap
	 * 
	 * Ref: B2B_S_MS_02
	 */
	public static APITransaction submitMsisdnSwap(String token, int serviceID, String newMsisdn) {
		String url = EnvironmentDirectory.getB2BCRMAPI() + "/api/v1/services/" + serviceID + "/swap/msisdn";
		SubsMgmtMsisdnSwapDTO dto = new SubsMgmtMsisdnSwapDTO(newMsisdn);
		Response r = RestAssuredUtil.galaxionPost(url, PojoToJsonConverter.getJSON(dto), token);
		return new APITransaction(url, r);
	}

	/*------------------------------------------------------------------
	 * Service: Manage Add-ons
	 ------------------------------------------------------------------- */
	/**
	 * Get available service level addons
	 * 
	 * Ref: B2B_S_AO_01
	 */
	public static APITransaction getAvailableServiceLevelAddons(String token, B2BAccountType accountType, OfferCode offerCode) {
		String url = EnvironmentDirectory.getB2BCRMAPI() + "/api/v1/subscription/addons/available?customerType=" + accountType + "&offerCode=" + offerCode;
		Response r = RestAssuredUtil.galaxionGet(url, token);
		return new APITransaction(url, r);
	}

	/**
	 * Get active service level addons
	 * 
	 * Ref: B2B_S_AO_02
	 */
	public static APITransaction getActiveServiceLevelAddons(String token, int serviceID) {
		String url = EnvironmentDirectory.getB2BCRMAPI() + "/api/v1/services/" + serviceID + "/addons";
		Response r = RestAssuredUtil.galaxionGet(url, token);
		return new APITransaction(url, r);
	}

	/*------------------------------------------------------------------
	 * Service: Barring
	 ------------------------------------------------------------------- */
	/**
	 * Get barring
	 * 
	 * Ref: B2B_S_BR_01
	 */
	public static APITransaction getBarrings(String token, int serviceID) {
		String url = EnvironmentDirectory.getB2BCRMAPI() + "/api/v1/barring/" + serviceID;
		Response r = RestAssuredUtil.galaxionGet(url, token);
		return new APITransaction(url, r);
	}

	/*------------------------------------------------------------------
	 * Service: Change Offer
	 ------------------------------------------------------------------- */
	/**
	 * Get available offers for a change offer
	 * 
	 * Ref: B2B_S_CH_01
	 */
	public static APITransaction getAvailableOffersForChangeOffer(String token, B2BAccountType accountType) {
		String url = EnvironmentDirectory.getB2BCRMAPI() + "/api/v1/offers?customerType=" + accountType.toString() + "&serviceGroupCode=MOBILE";
		Response r = RestAssuredUtil.galaxionGet(url, token);
		return new APITransaction(url, r);
	}

	/**
	 * Get change offers on a subscription
	 * 
	 * Ref: B2B_S_CH_02
	 */
	public static APITransaction getChangeOfferEligibility(String token, int subscriptionId) {
		String url = EnvironmentDirectory.getB2BCRMAPI() + "/api/v1/subscription/" + subscriptionId + "/change-offer";
		Response r = RestAssuredUtil.galaxionGet(url, token);
		return new APITransaction(url, r);
	}

	/*------------------------------------------------------------------
	 * Service: Porting
	 ------------------------------------------------------------------- */
	/**
	 * Get porting times
	 * 
	 * Ref: B2B_S_PO_01
	 */
	public static APITransaction getPortingTimes(String token) {
		String url = EnvironmentDirectory.getB2BCRMAPI() + "/api/v1/public/port/time/EIR/TELESALES_B2B";
		Response r = RestAssuredUtil.galaxionGet(url, token);
		return new APITransaction(url, r);
	}

	/**
	 * Get porting history
	 * 
	 * Ref: B2B_S_PO_02
	 */
	public static APITransaction getPortingHistory(String token, int serviceID) {
		String url = EnvironmentDirectory.getB2BCRMAPI() + "/api/v1/port/" + serviceID + "/port_history";
		return triggerGetRequest(url, token);
	}

	/**
	 * Get incomplete ports
	 * 
	 * Ref: B2B_S_PO_03
	 */
	public static APITransaction getIncompletePorts(String token, int serviceID) {
		String url = EnvironmentDirectory.getB2BCRMAPI() + "/api/v1/port/service/uncompleted/" + serviceID;
		return triggerGetRequest(url, token);
	}

	/*------------------------------------------------------------------
	 * Service: Terminations
	 ------------------------------------------------------------------- */
	/**
	 * Get pending terminations
	 * 
	 * Ref: B2B_S_CH_01
	 */
	public static APITransaction getPendingTerminations(String token, int serviceId) {
		String url = EnvironmentDirectory.getB2BCRMAPI() + "/api/v1/services/" + serviceId + "/pending-termination-request";
		Response r = RestAssuredUtil.galaxionGet(url, token);
		return new APITransaction(url, r);
	}

	/**
	 * Schedule termination
	 * 
	 * Ref: B2B_S_CH_02
	 */
	public static APITransaction scheduleTermination(String token, int serviceID, TerminationReason reason, String approvedBy, int earlyCeaseCharge,
			String terminationDate) {
		ScheduleTerminationDTO dto = new ScheduleTerminationDTO(reason.toString(), approvedBy, earlyCeaseCharge, terminationDate);
		String url = EnvironmentDirectory.getB2BCRMAPI() + "/api/v1/services/" + serviceID + "/terminate";
		Response r = RestAssuredUtil.galaxionPost(url, PojoToJsonConverter.getJSON(dto), token);
		return new APITransaction(url, r);
	}

	/**
	 * Cancel pending termination
	 * 
	 * Ref: B2B_S_CH_03
	 */
	public static APITransaction cancelTerminationRequest(String token, int serviceID) {
		String url = EnvironmentDirectory.getB2BCRMAPI() + "/api/v1/services/" + serviceID + "/pending-termination-request";
		Response r = RestAssuredUtil.galaxionDelete(url, token);
		return new APITransaction(url, r);
	}

	/*------------------------------------------------------------------
	 * Service: Reactivation
	 ------------------------------------------------------------------- */
	/**
	 * Reactivate subscription
	 * 
	 * Ref: B2B_S_RE_01
	 */
	public static APITransaction reactivateSubscription(String token, int serviceID) {
		String url = EnvironmentDirectory.getB2BCRMAPI() + "/api/v1/services/" + serviceID + "/reactivate";
		Response r = RestAssuredUtil.galaxionPost(url, null, token);
		return new APITransaction(url, r);
	}

	/*------------------------------------------------------------------
	 * Account: Create Account
	 ------------------------------------------------------------------- */

	/**
	 * Get ref market segments
	 * 
	 * Ref: B2B_A_CA_01
	 */
	public static APITransaction getRefMarketSegments(String token) {
		String url = EnvironmentDirectory.getB2BCRMAPI() + "/api/v1/market-segment";
		return triggerGetRequest(url, token);
	}

	/**
	 * Get ref billing cycles
	 * 
	 * Ref: B2B_A_CA_02
	 */
	public static APITransaction getRefBillCycles(String token) {
		String url = EnvironmentDirectory.getB2BCRMAPI() + "/api/v1/billing-cycle";
		return triggerGetRequest(url, token);
	}

	/**
	 * Generate new order number
	 * 
	 * Ref: B2B_A_CA_03
	 */
	public static APITransaction generateNewOrderNumber(String token) {
		String url = EnvironmentDirectory.getB2BCRMAPI() + "/api/v1/orders/reference";
		Response r = RestAssuredUtil.galaxionPost(url, null, token);
		return new APITransaction(url, r);
	}

	/**
	 * Create an account
	 * 
	 * Ref: B2B_A_CA_04
	 */
	public static APITransaction createAccount(String token, int parentAccountID, String companyName, B2BAccountType accountType, String orderReference) {
		String url = EnvironmentDirectory.getB2BCRMAPI() + "/api/v1/orders";
		CreateAccountDTO dto = new CreateAccountDTO(orderReference, 0, companyName, accountType);
		Response r = RestAssuredUtil.galaxionPost(url, PojoToJsonConverter.getJSON(dto), token);
		return new APITransaction(url, r);
	}

	/*------------------------------------------------------------------
	 * Account: Create Subscription
	 ------------------------------------------------------------------- */

	/**
	 * Get available offers for customer type
	 * 
	 * Ref: B2B_A_CS_01
	 */
	public static APITransaction getOffersForCustomerType(String token, B2BAccountType type) {
		String url = EnvironmentDirectory.getB2BCRMAPI() + "/api/v1/offers?customerType=" + type;
		return triggerGetRequest(url, token);
	}

	/**
	 * Get available tariffs for offer code
	 * 
	 * Ref: B2B_A_CS_02
	 */
	public static APITransaction getTariffPlansForOfferCode(String token, OfferCode offerCode) {
		String url = EnvironmentDirectory.getB2BCRMAPI() + "/api/v1/tariff-plans?offerCode=" + offerCode;
		return triggerGetRequest(url, token);
	}

	/**
	 * Get available addons for offer
	 * 
	 * Ref: B2B_A_CS_03
	 */
	public static APITransaction getAvailableAddonsForOffer(String token, B2BAccountType type, OfferCode offerCode) {
		String url = EnvironmentDirectory.getB2BCRMAPI() + "/api/v1/subscription/addons/available?customerType=" + type + "&offerCode=" + offerCode;
		return triggerGetRequest(url, token);
	}

	/**
	 * Activate a subscription
	 * 
	 * Ref: B2B_A_CS_05
	 */
	public static APITransaction activateSubscription(String token, int subscriptionID) {
		String url = EnvironmentDirectory.getB2BCRMAPI() + "/api/v1/subscription/" + subscriptionID + "/activate";
		Response r = RestAssuredUtil.galaxionPost(url, null, token);
		return new APITransaction(url, r);
	}

	/*------------------------------------------------------------------
	 * Subscription: SIM Swaps
	 ------------------------------------------------------------------- */

	/**
	 * Submit a SIM swap
	 * 
	 * Ref: B2B_S_SS_01
	 */
	public static APITransaction submitSimSwap(String token, int serviceID, String iccid) {
		String url = EnvironmentDirectory.getB2BCRMAPI() + "/api/v1/services/" + serviceID + "/sim/activate";
		TriggerSimSwapB2BDTO dto = new TriggerSimSwapB2BDTO(iccid);
		Response r = RestAssuredUtil.galaxionPost(url, PojoToJsonConverter.getJSON(dto), token);
		return new APITransaction(url, r);
	}

	public static APITransaction getAccountTransactions(String token, int billingAccountID) {
		String url = EnvironmentDirectory.getB2BCRMAPI() + "/api/v1/transactions/" + billingAccountID + "/search?page=0";
		return triggerGetRequest(url, token);
	}

	public static APITransaction createSubscription(String token, int accountID, int offerID, String tariffPlanCode, String emailAddress) {

		// first get all offers from the catalog
		APITransaction t = getOffersForCustomerType(token, B2BAccountType.CORPORATE_OR_EBU);
		JsonPath jsonPathEvaluator = t.getResponse().jsonPath();
		List<CatalogOffer> offers = jsonPathEvaluator.getList("", CatalogOffer.class);

		CatalogOffer offer = null;

		// retrieve the relevant offer from the GET /offers response
		for (CatalogOffer thisOffer : offers) {
			if (thisOffer.getId() == offerID) {
				offer = thisOffer;
				System.out.println("Offer found: " + offer.getCode());
				break;
			}
		}

		if (offer == null) {
			System.err.println("Error: No offer found in the list with ID " + offerID);
			return null;
		}

		// set the tariff plan code
		offer.setTariffPlanCode(tariffPlanCode);

		// populate the DTO
		CreateSubscriptionDTO dto = new CreateSubscriptionDTO(70124903, offer);
		dto.setSubscriberDetails(new SubscriberDetails("Steve Test", false));
		dto.setContact(new Contact());
		dto.getContact().setEmail(emailAddress);

		// make the API request
		String url = EnvironmentDirectory.getB2BCRMAPI() + "/api/v1/account/" + accountID + "/subscription/create";
		Response r = RestAssuredUtil.galaxionPost(url, PojoToJsonConverter.getJSON(dto), token);
		return new APITransaction(url, r);
	}

	public static APITransaction submitChangeOffer(String token, int subscriptionID, String offerCode, String tariffCode) {
		String url = EnvironmentDirectory.getB2BCRMAPI() + "/api/v1/subscription/" + subscriptionID + "/change-offer";
		ChangeOfferB2BCrmUiDTO dto = new ChangeOfferB2BCrmUiDTO();

		dto.setApprovedBy("Steve Test");
		dto.setContact(null);
		dto.setDeductedFromHardwareFund(false);
		dto.setDevice(null);
		dto.setEarlyCeaseChargeAmount(0);
		dto.setOfferCode(offerCode);
		dto.setReContract(true);
		dto.getTariffPlan().setOfferCode(tariffCode);
		dto.getTariffPlan().setAmount(2000);
		Response r = RestAssuredUtil.galaxionPost(url, PojoToJsonConverter.getJSON(dto), token);
		return new APITransaction(url, r);
	}

	private static APITransaction triggerGetRequest(String url, String token) {
		return new APITransaction(url, RestAssuredUtil.galaxionGet(url, token));
	}
}
