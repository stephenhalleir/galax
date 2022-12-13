package external_systems.elavon;

import java.math.BigDecimal;
import java.util.UUID;

import com.global.api.ServicesContainer;
import com.global.api.entities.Address;
import com.global.api.entities.Customer;
import com.global.api.entities.ThreeDSecure;
import com.global.api.entities.Transaction;
import com.global.api.entities.exceptions.ApiException;
import com.global.api.entities.exceptions.ConfigurationException;
import com.global.api.paymentMethods.CreditCardData;
import com.global.api.paymentMethods.RecurringPaymentMethod;
import com.global.api.serviceConfigs.GatewayConfig;
import com.global.api.services.Secure3dService;

import framework.test_data.generic.StringUtil;
import microservices.backend.eir_elavon_facade_backend.api.HppResponse;
import microservices.frontend.eir_eshop_frontend.dto.HppRequest;
import utilities.config.ConfigReader;
import utilities.generic.pojo_generation.pojo_to_json.PojoToJsonConverter;

public class ElavonUtility {

	private static String account = "internet3DS2";
	private static String merchantId = "eirtest";
	private static String secret = "secret";

	public static HppResponse handleHpp(HppRequest hpp, String payerName, String cardNumber, int expMonth, int expYear) {
		
		// read the payment amount from the HPP
		int hppAmount = Integer.parseInt(hpp.getAmount());

		// convert the hppAmount into a currency String - e.g. 1499 --> "14.99"
		String amountString = StringUtil.toCurrency(hppAmount);

		// create the payer via the elavon api
		Customer payer = ElavonUtility.payerNew();
		System.out.println("ElavonUtility has created a new payer: " + payer.getKey());

		// create the payment method via the elavon api
		Transaction transaction = ElavonUtility.cardNew(payer.getKey(), cardNumber, expMonth, expYear, payerName, amountString, hpp.getOrderID());

		// compose the HPP response object based on the payer, patment method and
		// payment objects
		HppResponse hppResponse = new HppResponse(hpp, payer, transaction);
		System.out.println("ElavonUtility has created the HppResponse: " + PojoToJsonConverter.getJSON(hppResponse));

		// encode the response values to base64
		hppResponse.encode();

		return hppResponse;
	}
	
	public static HppResponse handleHpp(HppRequest hpp) {
		return handleHpp(hpp, "Steve Test", "4263970000005262", 12, 2025);
	}

	/**
	 * Create a new payer
	 * 
	 * Ref: https://developer.elavonpaymentgateway.com/#!/api/card-storage/payer-new
	 */
	private static Customer payerNew() {

		System.setProperty("https.proxyHost", ConfigReader.getConfigValue("PROXY_HOST"));
		System.setProperty("https.proxyPort", ConfigReader.getConfigValue("PROXY_PORT"));

		// configure client & request settings
		GatewayConfig config = new GatewayConfig();
		config.setMerchantId(merchantId);
		config.setAccountId(account);
		config.setSharedSecret(secret);
		config.setServiceUrl("https://api.sandbox.elavonpaymentgateway.com/remote");

		try {
			ServicesContainer.configureService(config);
		} catch (ConfigurationException e) {
			e.printStackTrace();
		}

		// supply the the payer/customer details
		Address address = new Address();
		address.setStreetAddress1("EIR HOSTING SERVICES");
		address.setStreetAddress2("UNIT 4050");
		address.setStreetAddress3("KINGSWOOD AVENUE");
		address.setCity("CITYWEST BUSINESS CAMPUS");
		address.setProvince("West Yorkshire");
		address.setPostalCode("DUBLIN 24");
		address.setCountry("Ireland");

		Customer customer = new Customer();
		customer.setKey(UUID.randomUUID().toString());
		customer.setTitle("Mr.");
		customer.setFirstName("Steve");
		customer.setLastName("Test");
		customer.setCompany("eir Ltd");
		customer.setAddress(address);
		customer.setMobilePhone("0881234567");
		customer.setEmail("steve_test@eir.ie");
		customer.setComments("Elavon test");

		try {
			Customer response = customer.create();
			// get the stored payer/customer reference for future transactions
			String payerRef = response.getKey();
			// TODO: add a card/payment method to the payer, see next step

			System.out.println("Payer ref = " + payerRef);
			return response;
		}

		catch (ApiException exce) {
			// TODO: add your error handling here
			exce.printStackTrace();
		}
		return null;
	}

	private static Transaction cardNew(String payerRef, String cardNumber, int expMonth, int expYear, String cardholderName, String amount, String orderID) {

		System.out.println("Calling cardNew: " + payerRef);

		System.setProperty("https.proxyHost", ConfigReader.getConfigValue("PROXY_HOST"));
		System.setProperty("https.proxyPort", ConfigReader.getConfigValue("PROXY_PORT"));

		// configure client & request settings
		GatewayConfig config = new GatewayConfig();
		config.setMerchantId(merchantId);
		config.setAccountId(account);
		config.setSharedSecret(secret);
		config.setServiceUrl("https://api.sandbox.elavonpaymentgateway.com/remote");

		try {
			ServicesContainer.configureService(config);
		} catch (ConfigurationException e) {
			e.printStackTrace();
		}

		// supply the the existing payer/customer reference
		Customer customer = new Customer();
		customer.setKey(payerRef);

		// create a new card/payment method reference
		String paymentMethodRef = UUID.randomUUID().toString();

		// create the card object
		CreditCardData card = new CreditCardData();
		card.setNumber(cardNumber);
		card.setExpMonth(expMonth);
		card.setExpYear(expYear);
		card.setCardHolderName(cardholderName);

		// Add obtained 3D Secure 2 authentication data
		ThreeDSecure threeDSecureData = new ThreeDSecure();
		threeDSecureData.setAuthenticationValue("ODQzNjgwNjU0ZjM3N2JmYTg0NTM=");
		threeDSecureData.setDirectoryServerTransactionId("c272b04f-6e7b-43a2-bb78-90f4fb94aa25");
		threeDSecureData.setEci("05");
		threeDSecureData.setMessageVersion("2.1.0");
		threeDSecureData.setIssuerAcsUrl("https://acs2p.test.gpe.cz/tds/challenge/brw/116faac0-b1c4-41ec-8bc4-ed721421a7ec");
		threeDSecureData.setAuthenticationSource("BROWSER");
		threeDSecureData.setServerTransactionId("1086abd7-e379-4230-ba74-1f4202e87d5f");
		threeDSecureData.setAcsTransactionId("116faac0-b1c4-41ec-8bc4-ed721421a7ec");

		// Add the 3D Secure 2 data to the card object
		card.setThreeDSecure(threeDSecureData);

		Transaction transaction = null;
		try {

			// trigger a hold on the card
			transaction = card.authorize(new BigDecimal(amount)).withCurrency("EUR").withOrderId(orderID).execute();
		} catch (ApiException exec) {
		}

		if (transaction != null) {
			String result = transaction.getResponseCode(); // 00 == Success
			String message = transaction.getResponseMessage(); // [ test system ] AUTHORISED

			// get the details to save to the DB for future requests
			String orderId = transaction.getOrderId(); // ezJDQjhENTZBLTdCNzNDQw
			String authCode = transaction.getAuthorizationCode(); // 12345
			String paymentsReference = transaction.getTransactionId(); // pasref
			String schemeReferenceData = transaction.getSchemeId(); // MMC0F00YE4000000715
			System.err.println("*******RESPONSE**********\n" + transaction.toString());
		}

		// add the card/payment method to the payer/customer
		RecurringPaymentMethod paymentMethod = customer.addPaymentMethod(paymentMethodRef, card);
		RecurringPaymentMethod response2 = null;
		try {
			// store the card
			response2 = paymentMethod.create();
			System.err.println(PojoToJsonConverter.getJSON(response2));

			// return the payer ref
			return transaction;
		} catch (ApiException exce) {
			exce.printStackTrace();
		}

		return null;
	}

	/**
	 * Verify a card without making a payment
	 * 
	 * Reference:
	 * https://developer.elavonpaymentgateway.com/#!/api/card-storage/receipt-in-otb
	 * 
	 * @param payerRef
	 * @param paymentMethodRef
	 * @return
	 */
	private static Transaction receiptInOTB(String payerRef, String paymentMethodRef) {

		System.out.println("Calling receiptInOTB: " + payerRef + ", " + paymentMethodRef);

		System.setProperty("https.proxyHost", ConfigReader.getConfigValue("PROXY_HOST"));
		System.setProperty("https.proxyPort", ConfigReader.getConfigValue("PROXY_PORT"));

		System.out.println("Calling receiptInOTB: " + payerRef + ", " + paymentMethodRef);

		// configure client & request settings
		GatewayConfig config = new GatewayConfig();
		config.setMerchantId(merchantId);
		config.setAccountId(account);
		config.setSharedSecret(secret);
		config.setServiceUrl("https://api.sandbox.elavonpaymentgateway.com/remote");
		try {
			ServicesContainer.configureService(config);
		} catch (ConfigurationException e) {
			e.printStackTrace();
		}

		// existing customer/payer ref
		String customerId = payerRef;
		// existing card/payment method ref
		String paymentId = paymentMethodRef;
		// create the payment method object
		RecurringPaymentMethod paymentMethod = new RecurringPaymentMethod(customerId, paymentId);

		Transaction response = null;

		try {
			// verify the stored card/payment method is valid and active
			response = paymentMethod.verify().withCvn("222").execute();

			// get the response details to update the DB
			String result = response.getResponseCode(); // 00 == Success
			String message = response.getResponseMessage(); // [ test system ] AUTHORISED
			System.err.println("receiptInOTB: " + PojoToJsonConverter.getJSON(response));
		}

		catch (ApiException exce) {
			exce.printStackTrace();
		}

		return response;
	}

	/**
	 * Charge a saved card on the elavon sandbox
	 * 
	 * Reference:
	 * https://developer.elavonpaymentgateway.com/#!/api/card-storage/receipt-in
	 * 
	 * @param payerRef         - uuid
	 * @param paymentMethodRef - uuid
	 * @param amount           - "14.99"
	 * @return
	 */
	private static Transaction receiptIn(String payerRef, String paymentMethodRef, String amount, String orderId) {

		System.out.println("Calling receiptIn: " + payerRef + ", " + paymentMethodRef);

		System.setProperty("https.proxyHost", ConfigReader.getConfigValue("PROXY_HOST"));
		System.setProperty("https.proxyPort", ConfigReader.getConfigValue("PROXY_PORT"));

		System.out.println("Calling receiptInOTB: " + payerRef + ", " + paymentMethodRef);

		// configure client & request settings
		GatewayConfig config = new GatewayConfig();
		config.setMerchantId(merchantId);
		config.setAccountId(account);
		config.setSharedSecret(secret);
		config.setServiceUrl("https://api.sandbox.elavonpaymentgateway.com/remote");
		try {
			ServicesContainer.configureService(config);
		} catch (ConfigurationException e) {
			e.printStackTrace();
		}

		// -----------------------------------------
		ThreeDSecure threeDSecureData = null;

		try {
			threeDSecureData = Secure3dService.getAuthenticationData().withServerTransactionId("6c8c286b-9842-4c74-b298-da640da6ebbd").execute();
		}

		catch (ApiException exce) {
			// TODO: add your error handling here
		}

		if (threeDSecureData != null) {
			String status = threeDSecureData.getStatus(); // for example AUTHENTICATION_SUCCESSFUL or AUTHENTICATION_FAILED
			// Data required for authorization or database record
			String authenticationValue = threeDSecureData.getAuthenticationValue(); // ODQzNjgwNjU0ZjM3N2JmYTg0NTM=
			String dsTransId = threeDSecureData.getDirectoryServerTransactionId(); // c272b04f-6e7b-43a2-bb78-90f4fb94aa25
			String messageVersion = threeDSecureData.getMessageVersion(); // 2.1.0
			String eci = threeDSecureData.getEci(); // 5
			// Additional response data
			String acsTransId = threeDSecureData.getAcsTransactionId(); // 13c701a3-5a88-4c45-89e9-ef65e50a8bf9
			String statusReason = threeDSecureData.getStatusReason(); // LOW_CONFIDENCE
			String authenticationSource = threeDSecureData.getAuthenticationSource(); // BROWSER
			String messageCategory = threeDSecureData.getMessageCategory(); // PAYMENT_AUTHENTICATION
		}
		// ------------------------------------------

		// existing customer/payer ref
		String customerId = payerRef;

		// existing card/payment method ref
		String paymentId = paymentMethodRef;

		// create the payment method object
		RecurringPaymentMethod paymentMethod = new RecurringPaymentMethod(customerId, paymentId);

		Transaction response = null;

		try {
			// verify the stored card/payment method is valid and active
			response = paymentMethod.charge(new BigDecimal(amount)).withCurrency("EUR").withOrderId(orderId).withCvn("222")
					// .withTransactionId(System.currentTimeMillis()+"")
					.execute();

			System.err.println("receiptIn: " + PojoToJsonConverter.getJSON(response));
		}

		catch (ApiException exce) {
			exce.printStackTrace();
		}

		return response;
	}
}
