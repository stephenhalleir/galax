package testCases.eir.b2c.api.deprecated;

import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertNotNull;

import org.testng.ITestContext;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import framework.basetest.BaseTest;
import io.restassured.path.json.JsonPath;
import microservices.backend.eir_catalog_core_backend.enums.Brand;
import microservices.backend.eir_catalog_core_backend.enums.ChannelCode;
import microservices.backend.eir_catalog_core_backend.enums.OfferType;
import microservices.backend.eir_prospect_backend.dao.ProspectDAO;
import microservices.backend.eir_prospect_backend.data_model.Prospect;
import microservices.frontend.eir_acquisitions_ui.api.EirAcquistionsAPI;
import microservices.frontend.eir_crm_ui_frontend.api.PAYGCRMAPI;
import utilities.generic.api.APITransaction;

public class TestEirPAYGAcquisitionsApiTelesales extends BaseTest {

	private String prospectUuid;
	private String token;

	@Test(description = "Eir PAYG - Acquisitions: Authenticate")
	public void testLogin() {
		token = PAYGCRMAPI.getEirTelesalesToken();
		assertNotNull(token);
		logPass("Token generated: " + token.substring(1, 200) + "...");
	}

	@Test(dependsOnMethods = "testLogin", description = "Eir PAYG - Acquisitions: Create telesales prospect")
	public void testCreateTelesalesProspect(ITestContext iTestContext) {
		APITransaction t = EirAcquistionsAPI.createProspect(token, Brand.EIR, ChannelCode.CSS, OfferType.PREPAY);
		assertEquals(200, t.getResponse().statusCode());
		logPass(t.toString());

		// read the prospect uuid from the response
		JsonPath jsonPathEvaluator = t.getResponse().jsonPath();
		prospectUuid = (String) jsonPathEvaluator.get("prospectUuid");
		String orderNumber = (String) jsonPathEvaluator.get("orderReference");
		assertNotNull(prospectUuid);

		// read the prospect object from the database
		Prospect p = ProspectDAO.getProspect(prospectUuid);

		// check that the PROSPECT details are correct in the database
		assertEquals(p.getStatus(), "IN_PROGRESS");
		assertEquals(p.getOrderNumber(), orderNumber);
		assertEquals(p.getBrand(), "EIR");
		assertEquals(p.getChannelCode(), "TELESALES");
		assertEquals(p.getOfferType(), "PREPAY");
		assertEquals(p.getStatus(), "IN_PROGRESS");
		assertEquals(p.getHasAgreedTermsAndConditions(), 0);
		assertEquals(p.getMaximumCartSubscriptions(), 4);

		// log output to the report
		logPass("Prospect successfully created: " + p.getUuid());
	}
	
	// --------------------------------------------------------------

	@Test(dependsOnMethods = "testCreateTelesalesProspect", description = "Eir PAYG - Acquisitions: Get available offers")
	public void testGetAvailableOffers(ITestContext iTestContext) {
		APITransaction t = EirAcquistionsAPI.getAvailableOffers(token, prospectUuid);
		assertEquals(200, t.getResponse().statusCode());
		logPass(t.toString());
	}

	@Test(dependsOnMethods = "testCreateTelesalesProspect", description = "Eir PAYG - Acquisitions: Post offer to cart")
	public void testPostOfferToCart(ITestContext iTestContext) {
		APITransaction t = EirAcquistionsAPI.postOfferToCart(token, prospectUuid, 2001);
		assertEquals(200, t.getResponse().statusCode());
		logPass(t.toString());
	}

	@Test(dependsOnMethods = "testCreateTelesalesProspect", description = "Eir PAYG - Acquisitions: Get available discounts")
	public void getAvailableDiscounts(ITestContext iTestContext) {
		APITransaction t = EirAcquistionsAPI.getAvailableDiscounts(token, prospectUuid);
		assertEquals(200, t.getResponse().statusCode());
		logPass(t.toString());
	}

	@Test(dependsOnMethods = "testCreateTelesalesProspect", description = "Eir PAYG - Acquisitions: Get available addons")
	public void getAvailableAddons(ITestContext iTestContext) {
		APITransaction t = EirAcquistionsAPI.getAvailableAddons(token, prospectUuid);
		assertEquals(200, t.getResponse().statusCode());
		logPass(t.toString());
	}

	@Test(dependsOnMethods = "testCreateTelesalesProspect", description = "Eir PAYG - Acquisitions: Get available handsets for offer 2001")
	public void getAvailableHandsetsForOffer2001(ITestContext iTestContext) {
		APITransaction t = EirAcquistionsAPI.getAvailableHandsetsForOffer(token, 2001);
		assertEquals(200, t.getResponse().statusCode());
		logPass(t.toString());
	}

	@Test(dependsOnMethods = "testCreateTelesalesProspect", description = "Eir PAYG - Acquisitions: Get available handsets for offer 2002")
	public void getAvailableHandsetsForOffer2002(ITestContext iTestContext) {
		APITransaction t = EirAcquistionsAPI.getAvailableHandsetsForOffer(token, 2002);
		assertEquals(200, t.getResponse().statusCode());
		logPass(t.toString());
	}

	@Test(dependsOnMethods = "testCreateTelesalesProspect", description = "Eir PAYG - Acquisitions: Get available handset manufacturers for offer 2001")
	public void getAvailableHandsetManufacturersForOffer2001(ITestContext iTestContext) {
		APITransaction t = EirAcquistionsAPI.getAvailableHandsetManufacturers(token, 2001);
		assertEquals(200, t.getResponse().statusCode());
		logPass(t.toString());
	}
	
	@Test(dependsOnMethods = "testCreateTelesalesProspect", description = "Eir PAYG - Acquisitions: Get Apple handsets")
	public void getAppleHandsets(ITestContext iTestContext) {
		APITransaction t = EirAcquistionsAPI.getHandsetsByOfferIDAndManufacturer(token,2002,"Apple");
		assertEquals(200, t.getResponse().statusCode());
		logPass(t.toString());
	}
	
	@Test(dependsOnMethods = "testCreateTelesalesProspect", description = "Eir PAYG - Acquisitions: Get hosted payment page")
	public void getHostedPaymentPage(ITestContext iTestContext) {
		APITransaction t = EirAcquistionsAPI.getHostedPaymentPage(token,prospectUuid);
		assertEquals(200, t.getResponse().statusCode());
		logPass(t.toString());
	}

	@Test(dependsOnMethods = "testCreateTelesalesProspect", description = "Eir PAYG - Acquisitions: Get available handset manufacturers for offer 2002")
	public void getAvailableHandsetManufacturersForOffer2002(ITestContext iTestContext) {
		APITransaction t = EirAcquistionsAPI.getAvailableHandsetManufacturers(token, 2002);
		assertEquals(200, t.getResponse().statusCode());
		logPass(t.toString());
	}

	@Test(dependsOnMethods = "testCreateTelesalesProspect", description = "Eir PAYG - Acquisitions: Get prospect")
	public void getProspect(ITestContext iTestContext) {
		APITransaction t = EirAcquistionsAPI.getProspect(token, prospectUuid);
		assertEquals(200, t.getResponse().statusCode());
		logPass(t.toString());
	}

	@Test(dependsOnMethods = "testPostOfferToCart", description = "Eir PAYG - Acquisitions: Get cart details")
	public void testGetCartDetails(ITestContext iTestContext) {
		APITransaction t = EirAcquistionsAPI.getCartDetails(token, prospectUuid);
		assertEquals(200, t.getResponse().statusCode());
		logPass(t.toString());
	}

	@Test(dependsOnMethods = "testPostOfferToCart", description = "Eir PAYG - Acquisitions: Eircode lookup")
	public void testEircodeLookup(ITestContext iTestContext) {
		APITransaction t = EirAcquistionsAPI.eircodeLookup(token, "C15EF6E");
		assertEquals(200, t.getResponse().statusCode());
		logPass(t.toString());
	}

	@Test(dependsOnMethods = "testPostOfferToCart", description = "Eir PAYG - Acquisitions: Get ref security questions")
	public void testGetRefSecurityQuestions(ITestContext iTestContext) {
		APITransaction t = EirAcquistionsAPI.getRefSecurityQuestions(token);
		assertEquals(200, t.getResponse().statusCode());
		logPass(t.toString());
	}

	@Test(enabled = false, dependsOnMethods = "testLogin", dataProvider = "getOfferIDs", description = "Eir PAYG - Acquisitions: Get available handset manufacturers for offer")
	public void testGetHandsetManufacturers(ITestContext iTestContext, Integer offerID) {
		APITransaction t = EirAcquistionsAPI.getAvailableHandsetManufacturers(token, offerID);
		assertEquals(t.getResponse().statusCode(), 200);
		logPass(t.toString());
	}

	@DataProvider(name = "getOfferIDs")
	public Object[][] getPAYGOfferIDs() {

		Object[][] data = new Object[1][2];
		data[0][0] = new Integer(2001);
		data[0][1] = new Integer(2002);
		return data;
	}

	@BeforeClass
	public void setUp() {

	}

	@AfterClass
	public void tearDown() {

	}
}
