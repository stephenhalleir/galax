package microservices.backend.eir_order_management_backend.utility;

import framework.data_providers.addons.B2BBillableSetting;
import microservices.backend.eir_catalog_core_backend.enums.B2BAccountType;
import microservices.backend.eir_order_management_backend.api.OrderManagementAPI;
import microservices.backend.eir_order_management_backend.dao.OrderManagementDAO;
import microservices.backend.eir_order_management_backend.monitor.OrderManagementMonitor;
import microservices.backend.eir_subscription_management_backend.SubscriptionManagementDAO;

public class B2BBackendAccountCreator {

	/**
	 * Create a new B2B account via the order management API
	 * @param accountNumber (parent account number, or null if creating a root account)
	 * @param billableSetting - BILLABLE/NON_BILLABLE
	 * @param accountType - GOVERNMENT/CORPORATE_OR_EBU
	 * @return the billing account ID of the new account
	 */
	public static int createAccountViaBE(String accountNumber,B2BBillableSetting billableSetting,B2BAccountType accountType) {
		
		// create the order via the API and return the order reference
		String orderReference=OrderManagementAPI.createB2BAccount(accountNumber, billableSetting,accountType);
		
		// wait for the order to complete
		boolean orderCompleted=OrderManagementMonitor.waitForOrderToComplete(orderReference);
		
		// if the order does not complete, return 0
		if(!orderCompleted) {
			System.err.println("Error: Order " + orderReference + " did not complete within the expected time");
			return 0;
		}
		
		// read the account ID
		int accountID=OrderManagementDAO.getSubscriptionAccountIDForOrderReference(orderReference);
		int billingAccountID=SubscriptionManagementDAO.getBillingAccountIDForAccountID(accountID);
				
		return billingAccountID;
	}
}
