package testCases.gomo.api;

import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertNotNull;
import static org.testng.Assert.assertNull;
import static org.testng.Assert.assertTrue;

import java.util.ArrayList;
import java.util.List;

import org.testng.ITestContext;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import external_systems.elavon.ElavonUtility;
import framework.basetest.BaseTest;
import framework.test_data.generic.StringUtil;
import io.restassured.path.json.JsonPath;
import microservices.backend.eir_address_finder_backend.AddressFinderDAO;
import microservices.backend.eir_address_finder_backend.data_model.AddressFinderAddress;
import microservices.backend.eir_catalog_core_backend.dao.CatalogCoreDAO;
import microservices.backend.eir_catalog_core_backend.data_model.Offer;
import microservices.backend.eir_elavon_facade_backend.api.HppResponse;
import microservices.backend.eir_logistics_backend.utility.Logistics;
import microservices.backend.eir_order_management_backend.dto.LogisticsDTO;
import microservices.backend.eir_order_management_backend.monitor.OrderManagementMonitor;
import microservices.backend.eir_prospect_backend.dao.ProspectDAO;
import microservices.backend.eir_prospect_backend.data_model.Prospect;
import microservices.backend.eir_prospect_backend.data_model.ProspectOffer;
import microservices.backend.eir_subscription_management_backend.SubscriptionManagementDAO;
import microservices.backend.eir_subscription_management_backend.data_model.NetworkElement;
import microservices.backend.eir_subscription_management_backend.data_model.Service;
import microservices.backend.eir_subscription_management_backend.data_model.UsageQuota;
import microservices.frontend.common_ui.response_objects.address_finder.ReturnedAddressFinderAddress;
import microservices.frontend.eir_eshop_frontend.api.GoMoEShopAPI;
import microservices.frontend.eir_eshop_frontend.dto.HppRequest;
import microservices.frontend.eir_eshop_frontend.dto.catalog_get_offers_request.GetOffersResponse;
import utilities.generic.api.APITransaction;
import utilities.generic.pojo_generation.pojo_to_json.PojoToJsonConverter;
import utilities.generic.time.WaitUtil;

public class TestGoMoEshopAPI extends BaseTest {

	private Prospect p;
	private HppResponse hppResponse;

	/*------------------------------------------------------------------
	 * Offer selection screen
	 ------------------------------------------------------------------- */
	/**
	 * Get available GoMo offers
	 * 
	 * Ref: ESH_A_OS_01
	 */
	@Test(description = "ESH_A_OS_01: GoMo eShop API: Get available offers")
	public void ESH_A_OS_01_testGetAvailableOffers(ITestContext iTestContext) {

		int offerId = 1003;

		// read the offer details from the database
		Offer offer = CatalogCoreDAO.getOffer(offerId);

		// make the API call to retrieve the offers
		APITransaction t = GoMoEShopAPI.getAvailableOffers();
		logPass(t.toString());
		assertEquals(200, t.getResponse().statusCode());

		// convert the json response into an object for processing
		JsonPath jsonPathEvaluator = t.getResponse().jsonPath();
		List<GetOffersResponse> response = jsonPathEvaluator.getList("", GetOffersResponse.class);

		// verify the response
		assertEquals(response.size(), 1);
		assertEquals(offer.getId(), response.get(0).getCatalogOfferId());
		assertEquals(offer.getDescription(), response.get(0).getDescription());
		assertEquals(response.get(0).getRecurringChargeAmount(), 1499);
		assertEquals(response.get(0).getOneOffChargeAmount(), 0);
		assertEquals(response.get(0).getUpFrontChargeAmount(), 1499);
	}

	/**
	 * Create prospect
	 * 
	 * Ref: ESH_A_OS_02
	 */
	@Test(description = "ESH_A_OS_02: GoMo eShop API: Create prospect")
	public void ESH_A_OS_02_testCreateProspect(ITestContext iTestContext) {

		logInfo("Creating prospect using the eShop API");

		// make a call to the API to create the prospect
		APITransaction t = GoMoEShopAPI.createProspect();
		logInfo(t.toString());
		assertEquals(200, t.getResponse().statusCode());

		// read the prospect uuid from the response
		JsonPath jsonPathEvaluator = t.getResponse().jsonPath();
		String prospectUuid = (String) jsonPathEvaluator.get("prospectUuid");
		String orderNumber = (String) jsonPathEvaluator.get("orderReference");

		// verify that the prospect uuid has been created
		assertNotNull(prospectUuid);

		// read the prospect object from the database
		p = ProspectDAO.getProspect(prospectUuid);

		// check that the PROSPECT details are correct in the database
		assertEquals(p.getStatus(), "IN_PROGRESS");
		assertEquals(p.getOrderNumber(), orderNumber);
		assertEquals(p.getBrand(), "GOMO");
		assertEquals(p.getChannelCode(), "ESHOP");
		assertEquals(p.getOfferType(), "POSTPAY");
		assertEquals(p.getStatus(), "IN_PROGRESS");
		assertEquals(p.getHasAgreedTermsAndConditions(), 0);
		assertEquals(p.getMaximumCartSubscriptions(), 4);

		// log output to the report
		logPass("Prospect successfully created: " + p.getUuid() + ", " + p.getOrderNumber() + ", " + p.getStatus());
	}

	/**
	 * Add offer to cart
	 * 
	 * Ref: ESH_A_OS_03
	 */
	@Test(dependsOnMethods = "ESH_A_OS_02_testCreateProspect", description = "ESH_A_OS_03: GoMo eShop API: Post offer to cart")
	public void ESH_A_OS_03_testAddToCart(ITestContext iTestContext) {

		// specify the offer ID to add to the basket
		int catalogOfferID = 1003;

		// read the list of offers before the test
		ArrayList<ProspectOffer> offersBeforeAdd = ProspectDAO.getOffersForProspect(p.getId());

		// make an API call to add the offer to the cart
		APITransaction t = GoMoEShopAPI.postOfferToCart(p.getUuid(), catalogOfferID);
		assertEquals(200, t.getResponse().statusCode());
		logPass(t.toString());

		// read the list of offers after the add
		ArrayList<ProspectOffer> offersAfterAdd = ProspectDAO.getOffersForProspect(p.getId());

		// remove all pre-existing offers from the list
		offersAfterAdd.removeAll(offersBeforeAdd);

		// confirm that only 1 offer has been added
		assertEquals(offersAfterAdd.size(), 1);

		// verify that the catalog code and other settings are correct
		ProspectOffer offerAdded = offersAfterAdd.get(0);
		assertEquals(catalogOfferID, offerAdded.getCatalogOfferId());
		assertEquals(0, offerAdded.getTopUpAmount());
		assertEquals("EXDIRECTORY", offerAdded.getNddPreference());

		// log output to the report
		logPass("Offer successfully added: " + offerAdded.getId() + ", " + offerAdded.getCatalogOfferId());
	}

	/*------------------------------------------------------------------
	 * Cart screen
	 ------------------------------------------------------------------- */
	/**
	 * Delete offer from cart
	 * 
	 * Ref: ESH_A_CT_01
	 */
	@Test(dependsOnMethods = "ESH_A_OS_03_testAddToCart", description = "ESH_A_CT_01: GoMo eShop API: Update customer details")
	public void ESH_A_CT_01_testDeleteOfferFromCart(ITestContext iTestContext) {

		ArrayList<ProspectOffer> offersInCartBeforeDelete = ProspectDAO.getOffersForProspect(p.getId());
		logInfo("Customer has " + offersInCartBeforeDelete.size() + " offers in their basket before add");

		if (offersInCartBeforeDelete.size() < 2) {
			// use the API to add an offer to the cart (so that we can remove it)
			APITransaction t = GoMoEShopAPI.postOfferToCart(p.getUuid(), 1003);
			assertEquals(200, t.getResponse().statusCode());
			t = GoMoEShopAPI.postOfferToCart(p.getUuid(), 1003);
		}

		// read the list of offers before the test
		offersInCartBeforeDelete = ProspectDAO.getOffersForProspect(p.getId());
		logInfo("Customer has " + offersInCartBeforeDelete.size() + " offers in their basket after add");

		// determine the ID of the item we want to remove
		int offerID = offersInCartBeforeDelete.get(0).getId();
		logInfo("Customer wishes to remove offer " + offerID + " from the cart");

		// make a call to the API to delete the offer from the cart
		APITransaction t = GoMoEShopAPI.deleteOfferFromCart(p.getUuid(), offerID);
		assertEquals(200, t.getResponse().statusCode());
		logPass(t.toString());

		// read the new list of offers
		ArrayList<ProspectOffer> offersInCartAfterDelete = ProspectDAO.getOffersForProspect(p.getId());
		logInfo("Customer now has " + offersInCartBeforeDelete.size() + " offers in their cart");

		// get the difference between the 2 lists
		assertEquals(1, offersInCartBeforeDelete.size() - offersInCartAfterDelete.size());
		logPass("1 item was deleted");

		assertEquals(offerID, offersInCartBeforeDelete.get(0).getId());
		logPass("Offer " + offerID + " was deleted successfully");
	}

	/**
	 * Get cart details
	 * 
	 * Ref: ESH_A_CT_02
	 */
	@Test(dependsOnMethods = "ESH_A_OS_03_testAddToCart", description = "ESH_A_CT_02: GoMo eShop API: Get cart details")
	public void ESH_A_CT_02_testGetCart(ITestContext iTestContext) {

		// read the list of offers before the test
		ArrayList<ProspectOffer> offersInCart = ProspectDAO.getOffersForProspect(p.getId());
		APITransaction t = GoMoEShopAPI.getCart(p.getUuid());
		assertEquals(200, t.getResponse().statusCode());
		logPass(t.toString());
	}

	/*------------------------------------------------------------------
	 * Customer Details screen
	 ------------------------------------------------------------------- */

	/**
	 * Update customer details
	 * 
	 * Ref: ESH_A_CD_01
	 */
	@Test(dependsOnMethods = "ESH_A_OS_03_testAddToCart", description = "ESH_A_CD_01: GoMo eShop API: Update customer details")
	public void ESH_A_CD_01_testUpdateCustomerDetails(ITestContext iTestContext) {
		APITransaction t = GoMoEShopAPI.updateCustomerDetails(p.getUuid());
		logPass(t.toString());
		assertEquals(200, t.getResponse().statusCode());
	}

	/**
	 * Eircode lookup
	 * 
	 * Ref: ESH_A_CD_02
	 */
	@Test(description = "ESH_A_CD_02: GoMo eShop API: Eircode lookup")
	public void ESH_A_CD_02_testEircodeLookup(ITestContext iTestContext) {

		String eircode = "D24YX53";

		// read the expected address from the database
		ArrayList<AddressFinderAddress> addressesFromDatabase = AddressFinderDAO.getAddresses(eircode);

		// make the call to the API to retrieve the address
		APITransaction t = GoMoEShopAPI.lookupEircode(eircode);
		assertEquals(200, t.getResponse().statusCode());
		logPass(t.toString());

		JsonPath jsonPathEvaluator = t.getResponse().jsonPath();
		List<ReturnedAddressFinderAddress> addressesFromAPI = jsonPathEvaluator.getList("addresses", ReturnedAddressFinderAddress.class);

		// verify that the correct number of addresses are returned
		assertEquals(addressesFromDatabase.size(), addressesFromAPI.size());
		logPass(addressesFromAPI.size() + " address(es) returned from the API as expected");

		// from each address returned, check that it's correct
		for (int i = 0; i < addressesFromAPI.size(); i++) {
			assertEquals(addressesFromAPI.get(i).getAddressLine1(), addressesFromDatabase.get(i).getAddressLine1());
			assertEquals(addressesFromAPI.get(i).getAddressLine2(), addressesFromDatabase.get(i).getAddressLine2());
			assertEquals(addressesFromAPI.get(i).getAddressLine3(), addressesFromDatabase.get(i).getAddressLine3());
			assertEquals(addressesFromAPI.get(i).getTown(), addressesFromDatabase.get(i).getTown());
			assertEquals(addressesFromAPI.get(i).getCounty(), addressesFromDatabase.get(i).getCounty());
			logPass("Address is correct: " + addressesFromAPI.get(i).getAddressLine1());
		}
	}

	/**
	 * Get customer details (cross sell)
	 * 
	 * Ref: ESH_A_CD_03
	 */
	@Test(dependsOnMethods = "ESH_A_CD_01_testUpdateCustomerDetails", description = "ESH_A_CD_03: GoMo eShop API: Get customer details")
	public void ESH_A_CD_03_getCustomerDetails(ITestContext iTestContext) {
		APITransaction t = GoMoEShopAPI.getCustomerDetails(p.getUuid());
		assertEquals(200, t.getResponse().statusCode());
		logPass(t.toString());
	}

	/**
	 * Accept terms & conditions
	 * 
	 * Ref: ESH_A_CD_04
	 */
	@Test(dependsOnMethods = "ESH_A_CD_01_testUpdateCustomerDetails", description = "ESH_A_CD_04: GoMo eShop API: Accept terms & conditions")
	public void ESH_A_CD_04_acceptTermsAndConditions(ITestContext iTestContext) {

		// confirm that the prospect has not yet accept terms and conditions
		p = ProspectDAO.getProspect(p.getUuid());
		assertEquals(p.getHasAgreedTermsAndConditions(), 0);
		logInfo("Prospect has not yet agreed to terms and conditions - flag = " + p.getHasAgreedTermsAndConditions());

		// wait 3 seconds to avoid SQL LockAcquisitionException errors we've been
		// encountering
		WaitUtil.waitForSeconds(3);

		// make a call to the API to accept T&Cs
		APITransaction t = GoMoEShopAPI.acceptTermsAndConditions(p.getUuid());
		logPass(t.toString());
		assertEquals(204, t.getResponse().statusCode());

		// read the updated prospect object from the database
		p = ProspectDAO.getProspect(p.getUuid());

		// confirm that the flag has changed to 1
		assertEquals(p.getHasAgreedTermsAndConditions(), 1);

		logPass("Prospect has agreed to terms and conditions - flag = " + p.getHasAgreedTermsAndConditions());
	}

	/*------------------------------------------------------------------
	 * Summary screen
	 ------------------------------------------------------------------- */
	/**
	 * Get hosted payment page
	 * 
	 * Ref: ESH_A_SU_01
	 */
	@Test(dependsOnMethods = "ESH_A_CD_04_acceptTermsAndConditions", description = "ESH_A_SU_01: GoMo eShop API: Generate hosted payment page (HPP)")
	public void ESH_A_SU_01_getHostedPaymentPage(ITestContext iTestContext) {

		// make a call to the API to get the hosted payment page
		APITransaction t = GoMoEShopAPI.getHostedPaymentPage(p.getUuid());
		assertEquals(200, t.getResponse().statusCode());
		logPass(t.toString());

		// convert the json response to a HPP object
		JsonPath jsonPathEvaluator = t.getResponse().jsonPath();
		HppRequest hpp = jsonPathEvaluator.getObject("hppRequest", HppRequest.class);

		// read the payment amount from the HPP
		int hppAmount = Integer.parseInt(hpp.getAmount());

		// convert the hppAmount into a currency String - e.g. 1499 --> "14.99"
		String amountString = StringUtil.toCurrency(hppAmount);

		logPass("HPP request created for " + amountString);

		// verify the details in the HPP request
		assertEquals(hpp.getCurrency(), "EUR");
		assertEquals(hpp.getAutoSettleFlag(), "0");
		assertEquals(hpp.getCardStorageEnable(), "1");
		assertEquals(hpp.getOfferSaveCard(), "0");
		assertEquals(hpp.getPayerExist(), "0");
		assertEquals(hpp.getHppChallengeRequesstIndicator(), "CHALLENGE_MANDATED");
		assertEquals(hpp.getPaymentSource(), "ESHOP");
		assertEquals(hpp.getHppStoredCredentialType(), "recurring");
	}

	/**
	 * Test method to simulate the payment on the HPP using Elavon's APIs
	 */
	@Test(dependsOnMethods = "ESH_A_SU_01_getHostedPaymentPage", description = "Make a payment using Elavon's internal APIs")
	public void simulatePayment3DS2(ITestContext iTestContext) {

		// make a call to the API to get the hosted payment page
		APITransaction t = GoMoEShopAPI.getHostedPaymentPage(p.getUuid());
		assertEquals(200, t.getResponse().statusCode());
		logPass(t.toString());

		// convert the json response to a HPP object
		JsonPath jsonPathEvaluator = t.getResponse().jsonPath();
		HppRequest hpp = jsonPathEvaluator.getObject("hppRequest", HppRequest.class);
		hppResponse = ElavonUtility.handleHpp(hpp);
	}

	/**
	 * Validate the GoMo prospect payment
	 * 
	 * Ref: ESH_A_SU_02
	 */
	@Test(dependsOnMethods = "simulatePayment3DS2", description = "ESH_A_SU_02: Validate prospect payment")
	public void ESH_A_SU_02_testValidateProspect(ITestContext iTestContext) {

		assertNotNull(hppResponse);

		// send the encoded HPP response to the /validate call
		APITransaction t = GoMoEShopAPI.validate(p.getUuid(), PojoToJsonConverter.getJSON(hppResponse));

		// verify that the response code is 204
		assertEquals(t.getResponse().statusCode(), 204);
		logPass(t.toString());

		// verify that the prospect is finalized
		p = ProspectDAO.getProspect(p.getUuid());
		assertEquals(p.getStatus(), "FINALIZED");
		logPass("Prospect " + p.getUuid() + " is in status " + p.getStatus());
	}

	@Test(dependsOnMethods = "ESH_A_SU_02_testValidateProspect", description = "Test: Verify order completion and provisioning")
	public void testOrderCompletion(ITestContext iTestContext) {

		String orderReference = p.getOrderNumber();

		String[] expectedUsageQuotas = { "EU_ROAMING_(ALL_CALLS,_TEXTS_&_10GB_DATA)", "ALL_DATA", "ALL_CALLS_ALL_TEXTS" };
		String[] expectedNetworkElements = { "ALLOW_LTE", "ALLOW_ROAMING", "ALLOW_INTERNATIONAL_CALLS", "ALLOW_PREMIUM_CALLS", "ALLOW_VOICE_MAILS",
				"WIFI_CALLING" };

		// wait for the order to reach the delivery step
		boolean orderAwaitingDelivery = OrderManagementMonitor.waitForOrderToReachStep(orderReference,"DELIVERY");
		assertTrue(orderAwaitingDelivery);

		// process logistics
		ArrayList<LogisticsDTO> dtos = Logistics.processLogisticsBackend(orderReference);

		// ArrayList<EquipmentPack> msisdns =
		// LogisticsOld.processLogisticsBackend(orderReference);
		boolean orderCompleted = OrderManagementMonitor.waitForOrderToComplete(orderReference);
		assertTrue(orderCompleted);

		// verify that the services are active
		for (LogisticsDTO dto : dtos) {
			assertTrue(SubscriptionManagementDAO.getServiceStatus(dto.getMsisdn()).equals("ACTIVE"));
			logPass("Service " + dto.getMsisdn() + " is now ACTIVE");
		}

		// verify usage quotas
		for (LogisticsDTO dto : dtos) {
			Service service = SubscriptionManagementDAO.getServiceByMSISDN(dto.getMsisdn());
			ArrayList<UsageQuota> usages = SubscriptionManagementDAO.getUsageQuotas(service.getId());
			assertEquals(usages.size(), expectedUsageQuotas.length);

			// for each usage qupta expected
			for (int i = 0; i < expectedUsageQuotas.length; i++) {
				boolean found = false;

				// cycle through the list of usage quotas on the subscription
				for (UsageQuota quota : usages) {

					// check for a match
					if (quota.getCatalogCode().equals(expectedUsageQuotas[i])) {
						found = true;
						assertNotNull(quota.getActivatedAt());
						assertNull(quota.getTerminatedAt());
						break;
					}
				}
				// check that the usage quota has been found
				assertTrue(found);
				logPass("Usage quota " + expectedUsageQuotas[i] + " found");
			}
		}

		// verify network elements
		for (LogisticsDTO dto : dtos) {
			Service service = SubscriptionManagementDAO.getServiceByMSISDN(dto.getMsisdn());
			ArrayList<NetworkElement> networkElements = SubscriptionManagementDAO.getNetworkElements(service.getId());
			assertEquals(networkElements.size(), expectedNetworkElements.length);

			// for each network element expected
			for (int i = 0; i < expectedUsageQuotas.length; i++) {
				boolean found = false;

				// cycle through the list of usage quotas on the subscription
				for (NetworkElement element : networkElements) {

					// check for a match
					if (element.getCatalogCode().equals(expectedNetworkElements[i])) {
						found = true;
						assertNotNull(element.getActivatedAt());
						assertNull(element.getTerminatedAt());
						break;
					}
				}
				// check that the network element has been found
				assertTrue(found);
				logPass("Network Element " + expectedNetworkElements[i] + " found");
			}
		}

		assertTrue(orderCompleted);
	}

	@BeforeClass
	public void setUp() {

	}

	@AfterClass
	public void tearDown() {

	}
}
