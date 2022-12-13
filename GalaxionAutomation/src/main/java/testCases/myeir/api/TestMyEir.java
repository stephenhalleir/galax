package testCases.myeir.api;

import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertNotNull;

import org.testng.ITestContext;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import framework.basetest.BaseTest;
import microservices.backend.eir_subscription_management_backend.enums.NDDSetting;
import microservices.frontend.myeir.api.MyEirAPI;
import utilities.generic.api.APITransaction;

public class TestMyEir extends BaseTest {

	private String token;
	private String serviceID;
	private String url;
	private String emailAddress;
	private String password;

	@Test(description = "My Eir: Authenticate")
	public void testLogin() {
		token=MyEirAPI.getToken(emailAddress,password);
		assertNotNull(token);
		logPass("Token generated: " + token.substring(1,200) + "...");
	}
	
	
	@Test(dependsOnMethods = "testLogin", description = "My Eir: Get subscription and contact details")
	public void testGetMySubscriptionsAndContactDetails(ITestContext iTestContext) {
		APITransaction t = MyEirAPI.getMySubscriptionsAndContactDetails(token);
		assertEquals(200, t.getResponse().statusCode());
		logPass(t.toString());
	}
	
	@Test(dependsOnMethods = "testLogin", description = "My Eir: Get prepay balance & addons")
	public void testGetMyPrepayBalanceMyDetailsMyAddons(ITestContext iTestContext) {
		APITransaction t = MyEirAPI.getPrepayBalanceAndAddons(token,serviceID);
		assertEquals(200, t.getResponse().statusCode());
		logPass(t.toString());
	}
	
	@Test(dependsOnMethods = "testLogin", description = "My Eir: Get profile")
	public void testGetProfileDetails(ITestContext iTestContext) {
		APITransaction t = MyEirAPI.getProfileDetails(token,serviceID);
		assertEquals(200, t.getResponse().statusCode());
		logPass(t.toString());
	}
	
	@Test(dependsOnMethods = "testLogin", description = "My Eir: Reset password")
	public void testResetPassword(ITestContext iTestContext) {
		APITransaction t = MyEirAPI.resetPassword(emailAddress);
		assertEquals(200, t.getResponse().statusCode());
		logPass(t.toString());
	}
	
	@Test(dependsOnMethods = "testLogin", description = "My Eir: Update NDD setting")
	public void testUpdateNDD(ITestContext iTestContext) {
		APITransaction t = MyEirAPI.updateNDD(token,serviceID,NDDSetting.EXDIRECTORY);
		assertEquals(200, t.getResponse().statusCode());
		logPass(t.toString());
	}
	
	@Test(dependsOnMethods = "testLogin", description = "My Eir: Update contact permissions")
	public void testUpdateMyContactPermission(ITestContext iTestContext) {
		APITransaction t = MyEirAPI.updateMyContactPermission(token);
		assertEquals(200, t.getResponse().statusCode());
		logPass(t.toString());
	}
	
	@Test(dependsOnMethods = "testLogin", description = "My Eir: Get contact permissions")
	public void testGetContactPermissions(ITestContext iTestContext) {
		APITransaction t = MyEirAPI.getContactPermissions(token);
		assertEquals(200, t.getResponse().statusCode());
		logPass(t.toString());
	}
	
	@Test(dependsOnMethods = "testLogin", description = "My Eir: Send registration OTP")
	public void testSendRegistrationOTP(ITestContext iTestContext) {
		APITransaction t = MyEirAPI.sendRegistrationOTP("0852691196");
		assertEquals(200, t.getResponse().statusCode());
		logPass(t.toString());
	}
	
	@Test(dependsOnMethods = "testLogin", description = "My Eir: Validate registration OTP")
	public void testValidateRegistrationOTP(ITestContext iTestContext) {
		APITransaction t = MyEirAPI.validateRegistrationOTP("4f01cdab-131d-4585-a924-cdc39f0e6f54","894223");
		assertEquals(200, t.getResponse().statusCode());
		logPass(t.toString());
	}

	@BeforeClass
	public void setUp() {
		serviceID="1806";
		url="https://mytt01.eir.ie/graphql/";
		emailAddress="katetest15@gmail.com";
		password="Test@1234";
	}

	
	
	@AfterClass
	public void tearDown() {
		
	}
}
