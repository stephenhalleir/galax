package testCases.eir.b2c.api;

import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertNotNull;

import java.util.ArrayList;

import org.testng.ITestContext;
import org.testng.annotations.AfterClass;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import external_systems.elavon.ElavonUtility;
import framework.basetest.BaseTest;
import io.restassured.path.json.JsonPath;
import microservices.backend.eir_catalog_core_backend.dao.CatalogCoreDAO;
import microservices.backend.eir_catalog_core_backend.data_model.AddonBundle;
import microservices.backend.eir_catalog_core_backend.enums.ChannelCode;
import microservices.backend.eir_catalog_core_backend.enums.OfferType;
import microservices.backend.eir_catalog_core_backend.enums.SubscriptionAddonBundle;
import microservices.backend.eir_elavon_facade_backend.api.HppResponse;
import microservices.backend.eir_prospect_backend.dao.ProspectDAO;
import microservices.backend.eir_prospect_backend.data_model.Prospect;
import microservices.backend.eir_prospect_backend.data_model.ProspectAddonBundle;
import microservices.backend.eir_prospect_backend.data_model.ProspectOffer;
import microservices.backend.eir_prospect_backend.data_model.Sim;
import microservices.backend.eir_subscription_management_backend.enums.NDDSetting;
import microservices.frontend.eir_crm_ui_frontend.api.PAYGCRMAPI;
import microservices.frontend.eir_eshop_frontend.dto.HppRequest;
import utilities.generic.api.APITransaction;
import utilities.generic.pojo_generation.pojo_to_json.PojoToJsonConverter;

public class TestEirCrmAcquisitionsApi extends BaseTest {

	private String token;
	private Prospect p;
	private HppResponse hppResponse;

	/*------------------------------------------------------------------
	 * Authentication
	 ------------------------------------------------------------------- */
	@Test(description = "Eir CRM UI API: Authenticate")
	public void testLogin() {
		token = PAYGCRMAPI.getEirRetailToken();
		assertNotNull(token);
		logPass("Token generated: " + token.substring(1, 200) + "...");
	}

	
	/**
	 * Create a prospect
	 * 
	 * Ref: CRM_A_AQ_01
	 */
	/*
	@Test(dependsOnMethods = "testLogin", description = "CRM_A_AQ_01: Eir Acquisitions > Create Prospect [RETAIL]")
	public void CRM_A_AQ_01_testCreateProspect() {

		// make a call to the API to create the RETAIL prospect
		APITransaction t = PAYGCRMAPI.createProspect(token, ChannelCode.EIR_RETAIL, OfferType.PREPAY);
		assertEquals(t.getResponse().getStatusCode(), 200);

		// get the prospect uuid and order ID from the response
		JsonPath jsonPathEvaluator = t.getResponse().jsonPath();
		String prospectUuid = jsonPathEvaluator.getString("prospectUuid");
		String orderNumber = jsonPathEvaluator.getString("orderReference");

		// read the prospect object from the database
		p = ProspectDAO.getProspect(prospectUuid);

		// verify that the prospect details are correct
		// check that the PROSPECT details are correct in the database
		assertEquals(p.getStatus(), "IN_PROGRESS");
		assertEquals(p.getOrderNumber(), orderNumber);
		assertEquals(p.getBrand(), "EIR");
		assertEquals(p.getChannelCode(), "EIR_RETAIL");
		assertEquals(p.getOfferType(), "PREPAY");
		assertEquals(p.getStatus(), "IN_PROGRESS");
		assertEquals(p.getHasAgreedTermsAndConditions(), 0);
		assertEquals(p.getMaximumCartSubscriptions(), 4);

		// log output to the report
		logPass("Prospect successfully created: " + p.getId() + ", " + p.getUuid());
	}
	*/
	/**
	 * Create a prospect
	 * 
	 * Ref: CRM_A_AQ_01
	 */
	@Test(dependsOnMethods = "testLogin", description = "CRM_A_AQ_01: Eir Acquisitions > Create Prospect")
	@Parameters("channel")
	public void CRM_A_AQ_01_testCreateProspect(String channelName) {

		ChannelCode channelCode = ChannelCode.valueOf(channelName);
		
		// make a call to the API to create the TELESALES prospect
		APITransaction t = PAYGCRMAPI.createProspect(token, channelCode, OfferType.PREPAY);
		assertEquals(t.getResponse().getStatusCode(), 200);

		// get the prospect uuid and order ID from the response
		JsonPath jsonPathEvaluator = t.getResponse().jsonPath();
		String prospectUuid = jsonPathEvaluator.getString("prospectUuid");
		String orderNumber = jsonPathEvaluator.getString("orderReference");

		// read the prospect object from the database
		p = ProspectDAO.getProspect(prospectUuid);

		// verify that the prospect details are correct
		// check that the PROSPECT details are correct in the database
		assertEquals(p.getStatus(), "IN_PROGRESS");
		assertEquals(p.getOrderNumber(), orderNumber);
		assertEquals(p.getBrand(), "EIR");
		assertEquals(p.getChannelCode(), channelCode.toString());
		assertEquals(p.getOfferType(), "PREPAY");
		assertEquals(p.getStatus(), "IN_PROGRESS");
		assertEquals(p.getHasAgreedTermsAndConditions(), 0);
		assertEquals(p.getMaximumCartSubscriptions(), 4);

		// log output to the report
		logPass("Prospect successfully created: " + p.getId() + ", " + p.getUuid());
	}

	/**
	 * Add an offer to the cart
	 * 
	 * Ref: CRM_A_AQ_02
	 */
	@Test(dependsOnMethods = "CRM_A_AQ_01_testCreateProspect", description = "CRM_A_AQ_02: Eir Acquisitions > Add offer to cart", alwaysRun=true)
	public void CRM_A_AQ_02_testAddOfferToCart() {

		// specify the offer ID
		int offerID = 2002;

		// make a call to the API to add the offer to the cart
		APITransaction t = PAYGCRMAPI.addOfferToCart(token, p.getUuid(), offerID);
		assertEquals(t.getResponse().getStatusCode(), 200);

		// read the prospect and offer details from the database
		ArrayList<ProspectOffer> offers = ProspectDAO.getOffersForProspect(p.getId());
		assertEquals(offers.size(), 1);

		// verify that the offer details in the database are correct
		ProspectOffer offer = offers.get(0);
		assertEquals(offer.getCatalogOfferId(), offerID);
		assertEquals(offer.getNddPreference(), "EXDIRECTORY");
		logPass("Offer successfully added to cart: " + offer.getId() + ", " + offer.getCatalogOfferId() + ", " + offer.getNddPreference());
	}

	/**
	 * Get customer details
	 * 
	 * Ref: CRM_A_AQ_03
	 */
	@Test(dependsOnMethods = "CRM_A_AQ_01_testCreateProspect", description = "CRM_A_AQ_03: Eir Acquisitions > Get customer details")
	public void CRM_A_AQ_03_testGetCustomerDetails() {

		// make a call to the API to add the offer to the cart
		APITransaction t = PAYGCRMAPI.getCustomerDetails(token, p.getUuid());
		logInfo(t.toString());
		assertEquals(t.getResponse().getStatusCode(), 200);

	}

	/**
	 * Get cart details
	 * 
	 * Ref: CRM_A_AQ_04
	 */
	@Test(dependsOnMethods = "CRM_A_AQ_02_testAddOfferToCart", description = "CRM_A_AQ_04: Eir Acquisitions > Get cart details")
	public void CRM_A_AQ_04_testGetCartDetails(ITestContext iTestContext) {
		APITransaction t = PAYGCRMAPI.getCartDetails(token, p.getUuid());
		assertEquals(200, t.getResponse().statusCode());
		logPass(t.toString());
	}

	/**
	 * Get available offers
	 * 
	 * Ref: CRM_A_AQ_05
	 */
	@Test(dependsOnMethods = "CRM_A_AQ_01_testCreateProspect", description = "CRM_A_AQ_05: Eir Acquisitions > Get available offers")
	public void CRM_A_AQ_05_testGetAvailableOffers(ITestContext iTestContext) {
		APITransaction t = PAYGCRMAPI.getAvailableOffers(token, p.getUuid());
		assertEquals(200, t.getResponse().statusCode());
		logPass(t.toString());
	}

	/**
	 * Get available addons
	 * 
	 * Ref: CRM_A_AQ_06
	 */
	@Test(dependsOnMethods = "CRM_A_AQ_01_testCreateProspect", description = "CRM_A_AQ_06: Eir Acquisitions > Get available addons")
	public void CRM_A_AQ_06_testGetAvailableAddons(ITestContext iTestContext) {

		int offerID = 2002;

		APITransaction t = PAYGCRMAPI.getAvailableAddons(token, p.getUuid(), offerID);
		assertEquals(200, t.getResponse().statusCode());
		logPass(t.toString());
	}

	/**
	 * Add addon to offer
	 * 
	 * Ref: CRM_A_AQ_07
	 */
	@Test(dependsOnMethods = "CRM_A_AQ_02_testAddOfferToCart", description = "CRM_A_AQ_07: Eir Acquisitions > Add addon to offer")
	public void CRM_A_AQ_07_testAddAddonToOffer(ITestContext iTestContext) {

		// specify the addon code
		SubscriptionAddonBundle addonCode = SubscriptionAddonBundle.AO_SIMPLY_ULTD_DATA_CALLS_TEXTS;

		// read the list of offers linked to the prospect
		ArrayList<ProspectOffer> offers = ProspectDAO.getOffersForProspect(p.getId());

		// get the offer ID
		int offerID = offers.get(0).getId();

		// determine the addon code for the addon
		AddonBundle bundle = CatalogCoreDAO.getAddonBundleForCode(addonCode);
		int addonID = bundle.getId();

		logInfo("Adding addon " + addonID + " to offer " + offerID);

		// make an API call to add the addon to the offer
		APITransaction t = PAYGCRMAPI.addAddonToProspectOffer(token, p.getUuid(), offerID, addonID);
		assertEquals(200, t.getResponse().statusCode());
		logPass(t.toString());

		// read the addons linked to the offer
		ArrayList<ProspectAddonBundle> addonsOnOffer = ProspectDAO.getAddonBundlesOnOffer(offerID);

		// verfiy tyhat 1 addon is added
		assertEquals(addonsOnOffer.size(), 1);

		// read the addon from the database
		ProspectAddonBundle addonAdded = addonsOnOffer.get(0);

		// verify that the addon is added correctly
		assertEquals(addonAdded.getCatalogId(), addonID);

		// log output to the report
		logPass(addonsOnOffer.size() + " addon(s) added to offer " + offerID + ": " + addonAdded.getId() + ", " + addonAdded.getCatalogId());
	}

	/**
	 * Get SIM details
	 * 
	 * Ref: CRM_A_AQ_08
	 */
	@Test(dependsOnMethods = "CRM_A_AQ_02_testAddOfferToCart", description = "CRM_A_AQ_08: Eir Acquisitions > Get SIM details")
	public void CRM_A_AQ_08_testGetSimDetails(ITestContext iTestContext) {
		// make an API call to add the addon to the offer
		APITransaction t = PAYGCRMAPI.getSIMDetails(token, "0851871051");
		logPass(t.toString());
		assertEquals(200, t.getResponse().statusCode());

	}

	/**
	 * Update NDD preference on offer
	 * 
	 * Ref: CRM_A_AQ_09
	 */
	@Test(dependsOnMethods = "CRM_A_AQ_02_testAddOfferToCart", description = "CRM_A_AQ_09: Eir Acquisitions > Update NDD on offer")
	public void CRM_A_AQ_09_testUpdateNddOnOffer(ITestContext iTestContext) {

		NDDSetting newNDDSetting = NDDSetting.LISTED;

		// read the list of offers linked to the prospect
		ArrayList<ProspectOffer> offers = ProspectDAO.getOffersForProspect(p.getId());

		// get the offer ID
		int offerID = offers.get(0).getId();
		logPass("Before test: Offer " + offerID + " has NDD set to " + offers.get(0).getNddPreference());

		// make an API call to add the addon to the offer
		APITransaction t = PAYGCRMAPI.setNDDOnProspectOffer(token, p.getUuid(), offerID, newNDDSetting);
		assertEquals(200, t.getResponse().statusCode());
		logPass(t.toString());

		// read the updated offer from the database
		ProspectOffer offer = ProspectDAO.getOffer(offerID);

		// verify that the NDD is udpated as expected
		assertEquals(offer.getNddPreference(), newNDDSetting.toString());
		logPass("After test: Offer " + offerID + " now has NDD set to " + offer.getNddPreference() + " as expected");
	}

	/**
	 * Add topup to offer
	 * 
	 * Ref: CRM_A_AQ_10
	 */
	@Test(dependsOnMethods = "CRM_A_AQ_02_testAddOfferToCart", description = "CRM_A_AQ_10: Eir Acquisitions > Add topup to offer")
	public void CRM_A_AQ_10_testAddTopupToOffer(ITestContext iTestContext) {

		int topupAmount = 2000;

		// read the list of offers linked to the prospect
		ArrayList<ProspectOffer> offers = ProspectDAO.getOffersForProspect(p.getId());

		// get the offer ID
		int offerID = offers.get(0).getId();
		logPass("Before test: Offer " + offerID + " has topup amount " + offers.get(0).getTopUpAmount());

		// make an API call to add the addon to the offer
		APITransaction t = PAYGCRMAPI.setTopupAmountOnProspectOffer(token, p.getUuid(), offerID, topupAmount);
		assertEquals(200, t.getResponse().statusCode());
		logPass(t.toString());

		// read the updated offer from the database
		ProspectOffer offer = ProspectDAO.getOffer(offerID);

		// verify that the topup amouunt is udpated as expected
		assertEquals(offer.getTopUpAmount(), topupAmount);
		logPass("After test: Offer " + offerID + " now has topup set to " + offer.getTopUpAmount() + " as expected");
	}

	/**
	 * Assign a SIM to the order
	 * 
	 * Ref: CRM_A_AQ_11
	 */
	@Test(dependsOnMethods = "CRM_A_AQ_02_testAddOfferToCart", description = "CRM_A_AQ_11: Eir Acquisitions > Assign SIM to offer")
	public void CRM_A_AQ_11_testAssignSIMToOrder(ITestContext iTestContext) {

		// get the sim details
		String msisdn = "0851871051";
		String iccid = "893530305243682742";

		// read the list of offers linked to the prospect
		ArrayList<ProspectOffer> offers = ProspectDAO.getOffersForProspect(p.getId());

		// get the offer ID of the first offer on the prospect
		int offerID = offers.get(0).getId();
		logInfo("Linking SIM " + msisdn + ", " + iccid + " to offer " + offerID);
		
		
		// make an API call to assign the sim card to the offer
		APITransaction t = PAYGCRMAPI.assignSIMToProspectOffer(token, p.getUuid(), offerID, iccid, msisdn);
		logPass(t.toString());
		assertEquals(200, t.getResponse().statusCode());
		
		// read the SIM linked to the order and verify that the details are correct
		Sim sim = ProspectDAO.getSim(offerID);
		assertNotNull(sim);
		assertEquals(sim.getIccid(),iccid);
		assertEquals(sim.getMsisdn(),msisdn);
		logPass("Sim " + sim.getId() + " created: " + sim.getIccid() + ", " + sim.getMsisdn());
	}
	
	@Test(dependsOnMethods = "CRM_A_AQ_02_testAddOfferToCart", description = "CRM_A_AQ_11: Eir Acquisitions > Set the customer to Anonymous")
	public void CRM_A_AQ_12_testSetOrderAsAnonymous(ITestContext iTestContext) {

		// make an API call to set as anonymous
		APITransaction t = PAYGCRMAPI.setProspectAsAnonymous(token, p.getUuid());
		logPass(t.toString());
		assertEquals(200, t.getResponse().statusCode());
	}
	
	
	/**
	 * Accept terms & conditions
	 * 
	 * Ref: CRM_A_AQ_14
	 */
	@Test(dependsOnMethods = "CRM_A_AQ_12_testSetOrderAsAnonymous", description = "CRM_A_AQ_14: Eir Acquisitions > Accept T&Cs")
	public void CRM_A_AQ_14_testAcceptTermsAndConditions(ITestContext iTestContext) {

		// make an API call to accept terms and conditions
		APITransaction t = PAYGCRMAPI.acceptProspectTCs(token, p.getUuid());
		logPass(t.toString());
		assertEquals(204, t.getResponse().statusCode());
		
		// verify that the flag is set to 1 on the prospect record
		p = ProspectDAO.getProspect(p.getUuid());
		assertEquals(p.getHasAgreedTermsAndConditions(),1);
		logPass("prospect.has_agreed_terms_and_conditions = " + p.getHasAgreedTermsAndConditions());
	}
	
	@Test(dependsOnMethods = "CRM_A_AQ_14_testAcceptTermsAndConditions", description = "CRM_A_AQ_15: Eir Acquisitions > Validate prospect")
	public void CRM_A_AQ_15_testValidateProspectRetail(ITestContext iTestContext) {

		// make an API call to add the addon to the offer
		APITransaction t = PAYGCRMAPI.validateRetailProspect(token, p.getUuid());
		logPass(t.toString());
		assertEquals(204, t.getResponse().statusCode());
		
		// verify that the prospect is finalized
		p = ProspectDAO.getProspect(p.getUuid());
		assertEquals(p.getStatus(),"FINALIZED");
		logPass("Prospect " + p.getUuid() + " is in status " + p.getStatus());
	}
	
	@Test(dependsOnMethods = "CRM_A_AQ_14_testAcceptTermsAndConditions", description = "CRM_A_AQ_16: Eir Acquisitions > Get HPP")
	public void CRM_A_AQ_16_testGetHpp(ITestContext iTestContext) {

		// make an API call to add the addon to the offer
		APITransaction t = PAYGCRMAPI.getHostedPaymentPage(token, p.getUuid());
		logPass(t.toString());
		assertEquals(200, t.getResponse().statusCode());
	}
	
	@Test(dependsOnMethods = "CRM_A_AQ_16_testGetHpp", description = "Make a payment using Elavon's internal APIs")
	public void simulatePayment3DS2(ITestContext iTestContext) {

		// make a call to the API to get the hosted payment page
		APITransaction t = PAYGCRMAPI.getHostedPaymentPage(token, p.getUuid());
		assertEquals(200, t.getResponse().statusCode());
		logPass(t.toString());

		// convert the json response to a HPP object
		JsonPath jsonPathEvaluator = t.getResponse().jsonPath();
		HppRequest hpp = jsonPathEvaluator.getObject("hppRequest", HppRequest.class);
		hppResponse = ElavonUtility.handleHpp(hpp);
	}
	
	/**
	 * CRM_A_AQ_17 - Validate Prospect
	 */
	@Test(dependsOnMethods = "simulatePayment3DS2", description = "CRM_A_AQ_17: Validate prospect payment")
	public void CRM_A_AQ_17_testValidateProspectTelesales(ITestContext iTestContext) {

		assertNotNull(hppResponse);

		// send the encoded HPP response to the /validate call
		APITransaction t = PAYGCRMAPI.validateTelesalesProspect(token, p.getUuid(), PojoToJsonConverter.getJSON(hppResponse));
		logPass(t.toString());
		
		// verify that the response code is 204
		assertEquals(t.getResponse().statusCode(), 204);
		
		// verify that the prospect is finalized
		p = ProspectDAO.getProspect(p.getUuid());
		assertEquals(p.getStatus(),"FINALIZED");
		logPass("Prospect " + p.getUuid() + " is in status " + p.getStatus());
	}
	
	@Test(dependsOnMethods = "testLogin", description = "Eir CRM UI API: Acquisitions > Place sales order")
	public void testCreateRetailSalesOrder() {
		createRetailOrder(1002, SubscriptionAddonBundle.AO_SIMPLY_ULTD_DATA_CALLS_TEXTS, "0853460064");

	}

	public String createRetailOrder(int offerId, SubscriptionAddonBundle addonCode, String msisdn) {

		// Create the prosoect
		APITransaction t = PAYGCRMAPI.createProspect(token, ChannelCode.EIR_RETAIL, OfferType.PREPAY);
		assertEquals(t.getResponse().getStatusCode(), 200);

		// read the prospect UUID
		JsonPath jsonPathEvaluator = t.getResponse().jsonPath();
		String prospectUuid = jsonPathEvaluator.getString("prospectUuid");
		String orderNumber = jsonPathEvaluator.getString("orderReference");

		// read the prospect object from the database
		p = ProspectDAO.getProspect(prospectUuid);

		// Add the offer to the cart
		t = PAYGCRMAPI.addOfferToCart(token, p.getUuid(), 2002);
		assertEquals(t.getResponse().getStatusCode(), 200);

		// read the prospect and offer details from the database
		ArrayList<ProspectOffer> offers = ProspectDAO.getOffersForProspect(p.getId());
		assertEquals(offers.size(), 1);

		// get the offer object
		ProspectOffer offer = offers.get(0);

		if (addonCode != null) {

			// determine the addon code for the addon
			AddonBundle bundle = CatalogCoreDAO.getAddonBundleForCode(addonCode);
			int addonID = bundle.getId();

			logInfo("Adding addon " + addonID + " to offer " + offer.getId());

			// add the addon to the offer
			t = PAYGCRMAPI.addAddonToProspectOffer(token, p.getUuid(), offer.getId(), addonID);
			assertEquals(200, t.getResponse().statusCode());
			logPass(t.toString());
		}

		// SIM lookup

		t = PAYGCRMAPI.getSIMDetails(orderNumber, msisdn);
		logPass(t.toString());
		assertEquals(200, t.getResponse().statusCode());

		jsonPathEvaluator = t.getResponse().jsonPath();
		String iccid = jsonPathEvaluator.getString("_embedded.simcards[0].serialNumber");
		System.err.println(iccid);

		iccid = "893530305243664547";

		t = PAYGCRMAPI.assignSIMToProspectOffer(orderNumber, prospectUuid, offer.getId(), iccid, msisdn);
		logPass(t.toString());
		assertEquals(200, t.getResponse().statusCode());

		return orderNumber;
	}

	@AfterClass
	public void tearDown() {

	}
}
