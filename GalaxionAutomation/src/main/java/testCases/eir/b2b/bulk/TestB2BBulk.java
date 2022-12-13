package testCases.eir.b2b.bulk;

import static org.testng.Assert.assertEquals;

import org.testng.ITestContext;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import framework.basetest.BaseTest;
import framework.test_data.galaxion.TestDataManager;
import microservices.backend.eir_adjustment_backend.enums.AdjustmentReason;
import microservices.backend.eir_bulk_backend.utilities.BulkProcessor;
import microservices.backend.eir_bulk_backend.utilities.BulkResult;

public class TestB2BBulk extends BaseTest {

	private BulkProcessor processor;
	
	@Test(enabled = true, description = "B2B > Bulk File: Adjustments")
	public void testB2BBulkAddSubscriptionAddons(ITestContext iTestContext) {

		// retrieve the test data
		int billingAccountID=TestDataManager.getB2BCorporateAccount();
		
		// generate and process a bulk ADJUSTMENT file containing 1 adjustment
		BulkResult result = processor.generateAndProcessAdjustmentFile(billingAccountID, AdjustmentReason.REFUND_MANUAL_TO_CUST, 1000);
		
		// check that the response was 204
		assertEquals(result.getResponse().statusCode(),204);
		logInfo(result.getFile().getFilename() + " processed successfully with response code " + result.getResponse().statusCode() + ":\n" + result.getFile().toString());
	}
	
	@BeforeClass
	public void setUp() {
		processor = new BulkProcessor(extentLogger,logger4j);
	}
}
