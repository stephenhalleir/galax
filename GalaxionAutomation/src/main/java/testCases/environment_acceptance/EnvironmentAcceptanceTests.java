package testCases.environment_acceptance;

import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertNotNull;

import java.util.ArrayList;
import java.util.HashMap;

import org.testng.ITestContext;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import framework.basetest.BaseTest;
import io.restassured.response.Response;
import microservices.backend.eir_subscription_management_backend.data_model.B2BAccount;
import microservices.backend.keycloak.enums.Microservice;
import microservices.backend.keycloak.enums.Realm;
import microservices.frontend.eir_b2b_crm_ui_frontend.B2BCRMAPI;
import testCases.environment_acceptance.test_objects.ApiCheck;
import testCases.environment_acceptance.test_objects.EATDataProvider;
import utilities.api.RestAssuredUtil;

public class EnvironmentAcceptanceTests extends BaseTest {

	private String token;
	private int billingAccountID;
	private int accountID;
	private String accountName;
	private int subscriptionID;
	private String msisdn;
	private B2BAccount b2bAccount;
	private int serviceID;
	private HashMap<String, String> tokenMap;
	
	@Test(description = "B2B CRM UI API: Authenticate")
	public void testLogin() {
		token = B2BCRMAPI.getToken();
		assertNotNull(token);
		logPass("Token generated: " + token.substring(1,200) + "...");
	}
	
	
	@Test(dependsOnMethods = "testLogin", dataProvider="apiTests",description = "Check API")
	public void testGetAddonsForGovernmentOffer(ApiCheck test,ITestContext iTestContext) {
		
		String token=tokenMap.get(test.getSystem());
		
		if(token==null) {
			//token = KeycloakUtil.getToken(Microservice.MYGOMO,Realm.gomo, "rickey.jackson_20210928111751@gomo.ie","Password123");
			tokenMap.put(test.getSystem(), token);
			System.err.println("Added token " + test.getSystem() + ", " + tokenMap.size());
		}
		
		if(test.getMethod().equals("GET")) {
			Response r = RestAssuredUtil.galaxionGet(test.getUrl(), token);
			assertEquals(test.getExpectedStatusCode(),r.statusCode());
		}
		
		System.out.println(test.getUrl());
	}

	@BeforeClass
	public void setUp() {
		tokenMap = new HashMap<String, String>();
	}
	
	@DataProvider(name = "apiTests")
	public Object[][] getApiTests() {
		ArrayList<ApiCheck> offers = EATDataProvider.getTests();
		
		Object[][] data = new Object[offers.size()][1];
		for(int i=0;i<offers.size();i++) {
			data[i] = new Object[] { offers.get(i) };
		}
		
		return data;
	}

	@AfterClass
	public void tearDown() {
		
	}
}
