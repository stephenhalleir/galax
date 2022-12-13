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
import microservices.backend.eir_prospect_backend.dao.ProspectDAO;
import microservices.backend.eir_prospect_backend.data_model.Prospect;
import microservices.frontend.eir_acquisitions_ui.api.EirAcquistionsAPI;
import utilities.generic.api.APITransaction;
import utilities.generic.files.TextReader;
import utilities.generic.time.Timestamp;

public class TestEirPAYGAcquisitionsApiRetail extends BaseTest {

	private String prospectUuid;
	private String token;


	@Test(description = "Eir PAYG - Acquisitions [Retail]: Authenticate")
	public void testLogin() {
		token = EirAcquistionsAPI.getRetailToken();
		assertNotNull(token);
		logPass("Token generated: length " + token.length() + ", " + token.substring(0,200) + "...");
		TextReader.writeFile(token, "token_" + Timestamp.getCurrentTimestamp("yyyyMMddHHmmss")+".txt" );
	}
	
	/**
	 * Create a new prospect via the RETAIL sales channel
	 */
	@Test(dependsOnMethods = "testLogin", description = "Eir PAYG - Acquisitions: Create RETAIL prospect")
	public void testCreateRetailProspect(ITestContext iTestContext) {
		
		// make a call to the API to create the prospect
		APITransaction t = EirAcquistionsAPI.createRetailProspect(token);
		logInfo(t.toString());
		assertEquals(200,t.getResponse().statusCode());
		
		// read the prospect uuid from the response
		JsonPath jsonPathEvaluator = t.getResponse().jsonPath();
		prospectUuid = (String) jsonPathEvaluator.get("prospectUuid");
		String orderNumber=(String) jsonPathEvaluator.get("orderReference");
		assertNotNull(prospectUuid);
		
		// read the prospect object from the database
		Prospect p = ProspectDAO.getProspect(prospectUuid);

		// check that the PROSPECT details are correct in the database
		assertEquals(p.getStatus(),"IN_PROGRESS");
		assertEquals(p.getOrderNumber(),orderNumber);
		assertEquals(p.getBrand(),"EIR");
		assertEquals(p.getChannelCode(),"RETAIL");
		assertEquals(p.getOfferType(),"PREPAY");
		assertEquals(p.getStatus(),"IN_PROGRESS");
		assertEquals(p.getHasAgreedTermsAndConditions(),0);
		assertEquals(p.getMaximumCartSubscriptions(),4);
		
		// log output to the report
		logPass("Prospect successfully created: " + p.getUuid());
	}
	
	@Test(dependsOnMethods = "testCreateRetailProspect", description = "Eir PAYG - Acquisitions: Get available offers")
	public void testGetAvailableOffers(ITestContext iTestContext) {
		APITransaction t = EirAcquistionsAPI.getAvailableOffers(token, prospectUuid);
		assertEquals(200,t.getResponse().statusCode());
		logPass(t.toString());
	}
	
	@Test(dependsOnMethods = "testCreateRetailProspect", description = "Eir PAYG - Acquisitions: Post offer to cart")
	public void testPostOfferToCart(ITestContext iTestContext) {
		APITransaction t = EirAcquistionsAPI.postOfferToCart(token, prospectUuid,2002);
		logPass(t.toString());
		assertEquals(200,t.getResponse().statusCode());
	}
	
	@Test(dependsOnMethods = "testCreateRetailProspect", description = "Eir PAYG - Acquisitions: Get available discounts")
	public void getAvailableDiscounts(ITestContext iTestContext) {
		APITransaction t = EirAcquistionsAPI.getAvailableDiscounts(token, prospectUuid);
		assertEquals(200,t.getResponse().statusCode());
		logPass(t.toString());
	}
	
	@Test(dependsOnMethods = "testCreateRetailProspect", description = "Eir PAYG - Acquisitions: Get available addons")
	public void getAvailableAddons(ITestContext iTestContext) {
		APITransaction t = EirAcquistionsAPI.getAvailableAddons(token, prospectUuid);
		assertEquals(200,t.getResponse().statusCode());
		logPass(t.toString());
	}
	
	@Test(dependsOnMethods = "testCreateRetailProspect", description = "Eir PAYG - Acquisitions: Get available handsets for offer 2001")
	public void getAvailableHandsetsForOffer2001(ITestContext iTestContext) {
		APITransaction t = EirAcquistionsAPI.getAvailableHandsetsForOffer(token, 2001);
		assertEquals(200,t.getResponse().statusCode());
		logPass(t.toString());
	}
	
	@Test(dependsOnMethods = "testCreateRetailProspect", description = "Eir PAYG - Acquisitions: Get available handsets for offer 2002")
	public void getAvailableHandsetsForOffer2002(ITestContext iTestContext) {
		APITransaction t = EirAcquistionsAPI.getAvailableHandsetsForOffer(token, 2002);
		assertEquals(200,t.getResponse().statusCode());
		logPass(t.toString());
	}
	
	@Test(dependsOnMethods = "testCreateRetailProspect", description = "Eir PAYG - Acquisitions: Get available handset manufacturers for offer 2001")
	public void getAvailableHandsetManufacturersForOffer2001(ITestContext iTestContext) {
		APITransaction t = EirAcquistionsAPI.getAvailableHandsetManufacturers(token, 2001);
		assertEquals(200,t.getResponse().statusCode());
		logPass(t.toString());
	}
	
	@Test(dependsOnMethods = "testCreateRetailProspect", description = "Eir PAYG - Acquisitions: Get available handset manufacturers for offer 2002")
	public void getAvailableHandsetManufacturersForOffer2002(ITestContext iTestContext) {
		APITransaction t = EirAcquistionsAPI.getAvailableHandsetManufacturers(token, 2002);
		assertEquals(200,t.getResponse().statusCode());
		logPass(t.toString());
	}
	
	@Test(dependsOnMethods = "testCreateRetailProspect", description = "Eir PAYG - Acquisitions: Get prospect")
	public void getProspect(ITestContext iTestContext) {
		APITransaction t = EirAcquistionsAPI.getProspect(token, prospectUuid);
		assertEquals(200,t.getResponse().statusCode());
		logPass(t.toString());
	}
	
	

	
	@Test(dependsOnMethods = "testLogin", description = "Eir PAYG - Acquisitions: Get ref security questions")
	public void testGetRefSecurityQuestions(ITestContext iTestContext) {
		APITransaction t = EirAcquistionsAPI.getRefSecurityQuestions(token);
		assertEquals(200,t.getResponse().statusCode());
		logPass(t.toString());
	}
	
	@Test(enabled=false,dependsOnMethods = "testLogin", dataProvider="getOfferIDs",description = "Eir PAYG - Acquisitions: Get available handset manufacturers for offer")
	public void testGetHandsetManufacturers(ITestContext iTestContext,Integer offerID) {
		APITransaction t = EirAcquistionsAPI.getAvailableHandsetManufacturers(token, offerID);
		assertEquals(t.getResponse().statusCode(),200);
		logPass(t.toString());
	}
	
	@DataProvider(name = "getOfferIDs")
	public Object[][] getPAYGOfferIDs() {
		
		Object[][] data = new Object[1][2];
		data[0][0]=new Integer(2001);
		data[0][1]=new Integer(2002);
		return data;
	}
	
	
	@BeforeClass
	public void setUp() {

	}

	
	@AfterClass
	public void tearDown() {
		
	}
}
