package microservices.backend.eir_adjustment_backend;

import org.apache.log4j.Logger;

import com.aventstack.extentreports.ExtentTest;

import io.restassured.path.json.JsonPath;
import microservices.backend.eir_adjustment_backend.enums.AdjustmentReason;
import microservices.backend.eir_subscription_management_backend.SubscriptionManagementDAO;
import microservices.backend.eir_subscription_management_backend.data_model.B2BAccount;
import selenium.flows.base_flow.BaseAPIFlow;
import utilities.generic.api.APITransaction;

public class AdjustmentAPIFlow extends BaseAPIFlow {

	/*
	 * Constructors
	 */
	public AdjustmentAPIFlow(ExtentTest logger, Logger logger4j) {
		super(logger, logger4j);
	}

	public AdjustmentAPIFlow() {
		super();
	}

	/**
	 * Post an account adjustment using the Adjustment API
	 * 
	 * @param billingAccountID
	 * @param amount
	 * @param reason
	 * @return the adjustment reference
	 */
	public String postAdjustment(String token, int billingAccountID, int amount, AdjustmentReason reason) {

		APITransaction transaction = AdjustmentAPI.postAdjustment(token, billingAccountID, amount, reason);

		// read and return the adjustment reference
		JsonPath jsonPathEvaluator = transaction.getResponse().jsonPath();
		return (String) jsonPathEvaluator.get("adjustmentReference");
	}

	/**
	 * Post a hardware fund adjustment using the Adjustment API
	 * @param billingAccountID
	 * @param amount
	 * @param reason
	 * @return
	 */
	public String postHardwareFundAdjustment(String token, int billingAccountID, int amount, AdjustmentReason reason) {
		
		// read the account details, including hardware fund ID
		int accountID=SubscriptionManagementDAO.getAccountIDForBillingAccountID(70124903);
		B2BAccount account = SubscriptionManagementDAO.getB2BAccountByID(accountID);

		// make the adjustment
		APITransaction transaction = AdjustmentAPI.makeHardwareFundAdjustmentNew(token, account.getHardwareBalanceID(), amount, reason);

		// read and return the adjustment reference
		JsonPath jsonPathEvaluator = transaction.getResponse().jsonPath();
		return (String) jsonPathEvaluator.get("adjustmentReference");
	}

}