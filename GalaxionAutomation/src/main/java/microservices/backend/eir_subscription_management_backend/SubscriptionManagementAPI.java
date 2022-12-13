package microservices.backend.eir_subscription_management_backend;

import io.restassured.response.Response;
import microservices.backend.eir_subscription_management_backend.data_model.Service;
import microservices.backend.eir_subscription_management_backend.dto.SubsMgmtMoveSubscriptionDTO;
import utilities.api.RestAssuredUtil;
import utilities.galaxion.environments.EnvironmentDirectory;
import utilities.generic.api.APITransaction;

/**
* The SubscriptionManagementAPI class provides an interface
* into the subscription-management-backend
*
* @author  Stephen Hall
* @version 1.0
* @since   22/03/2021
*/
public class SubscriptionManagementAPI {
	
	/**
	 * This method moves a B2B subscription to a new account
	 * via the subscription management route:
	 * /api/v1/account_subscription/subscription/{subscription_id}
	 * 
	 * @param subscriptionID This is the subscription ID
	 * @param newAccountID	 This is the ID of the destination account
	 * @return	the Response object
	 */
	public static APITransaction moveSubscriptionToNewAccount(int subscriptionID,int newAccountID) {
		
		// create the request object
		SubsMgmtMoveSubscriptionDTO dto = new SubsMgmtMoveSubscriptionDTO(newAccountID);

		// construct the URL
		String endpoint = EnvironmentDirectory.getSubscriptionManagementAPI()+"/api/v1/account_subscription/subscription/" + subscriptionID;

		// trigger the PATCH request
		Response r = RestAssuredUtil.galaxionPatch(endpoint, dto.toString(),null);
		
		// return the response
		return new APITransaction(endpoint, r);
	}
}
