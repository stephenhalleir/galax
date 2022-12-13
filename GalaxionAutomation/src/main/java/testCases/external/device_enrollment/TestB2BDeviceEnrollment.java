package testCases.external.device_enrollment;

import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertNotNull;

import org.testng.ITestContext;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import external_systems.device_enrollment.dao.DepDAO;
import external_systems.device_enrollment.data_model.AndroidDevice;
import external_systems.device_enrollment.data_model.AndroidResponse;
import external_systems.device_enrollment.data_model.AppleDevice;
import external_systems.device_enrollment.data_model.AppleResponse;
import external_systems.device_enrollment.enums.Provider;
import external_systems.device_enrollment.file.DEPFile;
import external_systems.device_enrollment.utility.DeviceEnroller;
import framework.basetest.BaseTest;
import framework.test_data.galaxion.TestDataManager;
import microservices.backend.eir_subscription_management_backend.SubscriptionManagementDAO;
import utilities.generic.time.WaitUtil;

public class TestB2BDeviceEnrollment extends BaseTest {

	public int accountID;
	
	@Test(enabled = true, description = "Device Enrollment [Apple]", invocationCount = 1)
	public void testDeviceEnrollmentApple(ITestContext iTestContext) {
		Provider provider = Provider.Apple;
		
		// generate and process the file
		DEPFile file = DeviceEnroller.processDEPFile(accountID, provider);
		logInfo("File processed in device enrollment platform:\n" + file.getFilename() + ":  \n" + file.toString());
		
		WaitUtil.waitForSeconds(15);
		
		// determine the expected enrollment ID
		String expectedEnrollmentID = SubscriptionManagementDAO.getDeviceEnrollmentRef(accountID, provider);
		
		// read the row from the android_devices table
		AppleDevice device = DepDAO.getAppleDeviceByOrderNumber(file.getOrderNumber());
		
		// check that the row is found
		assertNotNull(device);
		logPass("Record " + device.getId() + " found in 'apple_devices' table");

		// check that the company ID is correct
		assertEquals(device.getCustomerId(),expectedEnrollmentID);
		logPass("Enrollment ID " + expectedEnrollmentID + " is correct in 'apple_devices' table");

		// check that the IMEI is correct in the device table
		assertEquals(device.getDeviceId(),file.getImei());
		logPass("IMEI " + file.getImei() + " is correct");

		// read the response object
		AppleResponse andResp = DepDAO.getAppleResponseByOrderNumber(file.getOrderNumber());
		
		// check that the response row has been found in the table
		assertNotNull(andResp);
		logPass("Record " + andResp.getId() + " found in 'apple_response' table");
		
		System.err.println(andResp.getDeviceId());
		System.err.println(file.getImei());
		
		// check that the IMEI is correct in the response table
		assertEquals(andResp.getDeviceId(),file.getImei());
		logPass("IMEI " + andResp.getDeviceId() + " is correct in 'apple_response' table");	

	}

	@Test(enabled = true, description = "Device Enrollment [Android]", invocationCount = 1)
	public void testDeviceEnrollmentAndroid(ITestContext iTestContext) {
		Provider provider = Provider.Android;

		DEPFile file = DeviceEnroller.processDEPFile(accountID, provider);
		logInfo("File processed in device enrollment platform:\n" + file.getFilename() + ":  \n" + file.toString());

		WaitUtil.waitForSeconds(15);

		this.validateAndroid(file,provider);

	}

	@Test(enabled = true, description = "Device Enrollment [Samsung]", invocationCount = 1)
	public void testDeviceEnrollmentSamsung(ITestContext iTestContext) {
		Provider provider = Provider.Samsung;
		DEPFile file = DeviceEnroller.processDEPFile(accountID, provider);
		logInfo("File processed in device enrollment platform:\n" + file.toString());

		WaitUtil.waitForSeconds(15);

		this.validateAndroid(file,provider);
	}

	/**
	 * Validate that an Android/Samsung file has been successfully processed
	 * @param file
	 * @param provider
	 */
	public void validateAndroid(DEPFile file,Provider provider) {
		
		// read the row from the android_devices table
		AndroidDevice device = DepDAO.getAndroidDeviceByOrderNumber(file.getOrderNumber());
		
		// check that the row is found
		assertNotNull(device);
		logPass("Record " + device.getId() + " found in 'android_devices' table");

		String expectedEnrollmentID = SubscriptionManagementDAO.getDeviceEnrollmentRef(accountID, provider);
		
		// check that the company ID is correct
		assertEquals(device.getCompanyId(),expectedEnrollmentID);
		logPass("Enrollment ID " + expectedEnrollmentID + " is correct in 'android_devices' table");

		// check that the IMEI is correct
		assertEquals(device.getDeviceId(),file.getImei());
		logPass("IMEI " + file.getImei() + " is correct");

		// read the response object
		AndroidResponse andResp = DepDAO.getAndroidResponseByOrderNumber(file.getOrderNumber());
		
		assertNotNull(andResp);
		logPass("Record " + andResp.getId() + " found in 'android_response' table");
		
		assertEquals(andResp.getDeviceId(),file.getImei());
		logPass("IMEI " + andResp.getDeviceId() + " is correct in 'android_response' table");	
	}
	
	@BeforeMethod
	public void setUp() {
		
		// specify the test data
		int billingAccountID=TestDataManager.getB2BCorporateAccount();
		accountID=SubscriptionManagementDAO.getAccountByBillingAccountID(billingAccountID).getId();
	}

	@AfterMethod
	public void tearDown() {

	}
}
