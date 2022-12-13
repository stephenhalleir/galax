package testCases.eir.b2b.bulk.adjustments;

import org.testng.ITestContext;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import framework.basetest.BaseTest;
import framework.test_data.galaxion.TestDataManager;
import microservices.backend.eir_adjustment_backend.enums.AdjustmentReason;
import microservices.backend.eir_bulk_backend.utilities.BulkProcessor;
import microservices.backend.eir_bulk_backend.utilities.BulkResult;

public class TestB2BBulkAdjustments extends BaseTest {

	private BulkProcessor processor;
	
	@Test(enabled=true, description = "eir B2B > Bulk > Adjustment")
	public void testB2BBulkAdjustment(ITestContext iTestContext) {

		// get the test data from the environments file
		int billingAccountID = TestDataManager.getB2BCorporateAccount();
		
		AdjustmentReason reason = AdjustmentReason.GOODWILL;
		BulkResult result = processor.generateAndProcessAdjustmentFile(billingAccountID, reason, 200);
		logInfo(result.toString());
	}
	
	@BeforeClass
	public void setUp() {
		processor = new BulkProcessor(extentLogger,logger4j);
	}
}
