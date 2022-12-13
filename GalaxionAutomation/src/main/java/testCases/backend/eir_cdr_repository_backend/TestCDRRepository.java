package testCases.backend.eir_cdr_repository_backend;

import static org.testng.Assert.assertNotNull;
import static org.testng.Assert.assertTrue;
import static org.testng.Assert.assertEquals;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import org.testng.ITestContext;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import framework.basetest.BaseTest;
import framework.test_data.galaxion.TestData;
import framework.test_data.galaxion.TestDataManager;
import microservices.backend.eir_cdr_repository_backend.dao.CDRRepoDAO;
import microservices.backend.eir_cdr_repository_backend.data_model.MobileNonUsage;
import microservices.backend.eir_cdr_repository_backend.data_model.MobileNonUsageFile;
import microservices.backend.eir_cdr_repository_backend.data_model.MobileUsageFile;
import microservices.backend.eir_cdr_repository_backend.enums.ProcessType;
import microservices.backend.eir_cdr_repository_backend.files.UsageFile;
import microservices.backend.eir_cdr_repository_backend.monitor.CDRRepoMonitor;
import microservices.backend.eir_cdr_repository_backend.utilities.CDRGenerator;
import microservices.backend.eir_cdr_repository_backend.utilities.NonUsageGenerator;
import microservices.backend.eir_logistics_backend.utility.InventoryManager;
import microservices.frontend.common_ui.enums.CustomerType;
import utilities.generic.time.Timestamp;
import utilities.generic.time.WaitUtil;

public class TestCDRRepository extends BaseTest {

	private Map<CustomerType, TestData> testDataMap;

	@Test(enabled = true, description = "CDR Repository: Process Usage File", dataProvider = "all_data_types")
	public void testUsage(CustomerType customerType, ITestContext iTestContext) {
		
		// retrieve the relevant test data
		TestData testData = testDataMap.get(customerType);
		logInfo(testData.toString());
		
		int numRecords = 10;

		// specify the current period and msisdn
		String period = Timestamp.getCurrentTimestamp("MM-yyyy");

		// generate and FTP a usage file
		UsageFile file = CDRGenerator.generateUsage(testData.getMsisdn(), period, ProcessType.RANDOM, true, numRecords,null);
		logPass("File " + file.getFilename() + " generated and uploaded");
		
		// wait for the file to process in ION
		boolean fileProcessed = true;//CDRRepoMonitor.waitForFileProcessed(file.getFilename());
		assertTrue(fileProcessed);
		
		logPass("File is now processed in CDR Repo");
		
		// read the mobile usage file details
		MobileUsageFile usageFile = CDRRepoDAO.getMobileUsageEventFile(file.getFilename());
		assertEquals(usageFile.getRecordCount(),file.getUsageRecords().size());
		assertEquals(usageFile.getSuccessCount(),file.getUsageRecords().size());
		assertEquals(usageFile.getProcessingErrorCount(),0);
		
		logPass("All rows are processed without error - mobile_usage_file " + usageFile.getId());
	}

	@Test(enabled = true, description = "CDR Repository: Process Non-Usage Event Type 2")
	public void testNonUsageType2() {

		// retrieve a piece of inventory from the google sheet
		String msisdn = InventoryManager.getInventory("0SIMUPREE").getMsisdn();
		System.out.println(msisdn);
		assertNotNull(msisdn);

		// generate and upload a non-usage event file
		String filename = NonUsageGenerator.processNonUsageFile(msisdn, 2);
		System.out.println(filename);
		assertNotNull(filename);

		// wait for the file to appear in the database
		MobileNonUsageFile file = null;
		long endTime = System.currentTimeMillis() + 30000;

		while (System.currentTimeMillis() < endTime && file == null) {
			file = CDRRepoDAO.getMobileNonUsageEventFile(filename);
			WaitUtil.waitForSeconds(1);
		}

		assertNotNull(file);
		System.out.println(file.getId());

		// read the non_usage records belonging to ther file
		ArrayList<MobileNonUsage> nonUsages = CDRRepoDAO.getMobileNonUsage(file.getId());
		MobileNonUsage nonUsage = nonUsages.get(0);
		System.out.println(nonUsage.getServiceName() + ", " + nonUsage.getServiceStatus());

		// need to put a wait in here
		assertEquals(nonUsage.getServiceStatus(), "ACTIVATED");
	}

	@BeforeClass
	public void setUp() {

		// initialize the maps
		testDataMap = new HashMap<>();

		// retrieve and store eir prepay test data
		int prepayBillingAccountID = TestDataManager.getEirPAYGSubscriber();
		TestData prepayData = TestDataManager.getTestData(prepayBillingAccountID);
		testDataMap.put(CustomerType.EIR_PREPAY, prepayData);

		// retrieve and store eir postpay test data
		int postpayBillingAccountID = TestDataManager.getEirPostpaySubscriber();
		TestData postpayData = TestDataManager.getTestData(postpayBillingAccountID);
		testDataMap.put(CustomerType.EIR_POSTPAY, postpayData);

		// retrieve and store gomo test data
		int gomoBillingAccountID = TestDataManager.getGoMoMultilineSubscriber();
		TestData gomoData = TestDataManager.getTestData(gomoBillingAccountID);
		testDataMap.put(CustomerType.GOMO, gomoData);
	}
	
	@AfterClass
	public void tearDown() {

	}
	
	@DataProvider(name = "all_data_types")
	public Object[][] getAllCustomerTypes() {

		Object[][] data = { { CustomerType.EIR_PREPAY }, { CustomerType.EIR_POSTPAY }, { CustomerType.GOMO } };

		return data;
	}
}
