package microservices.backend.eir_provisioning_facade_backend.api;

import io.restassured.response.Response;
import microservices.backend.eir_provisioning_facade_backend.dto.retry.ProvisioningRetryDTO;
import microservices.backend.eir_provisioning_facade_backend.enums.ActionType;
import utilities.api.RestAssuredUtil;
import utilities.galaxion.environments.EnvironmentDirectory;
import utilities.generic.api.APITransaction;

public class ProvisioningFacadeAPI {
	
	/**
	 * Perform a named action on a provisioning error via the provisioning facade API
	 * @param actionType - RETRY or ADOPT
	 * @param id - the ID from provisioning-facade's SERVICE table
	 * @param retryCode - the retry code
	 * @return API transaction object containing the URL and API response
	 */
	private static APITransaction actionProvisioningError(ActionType actionType, int id, String retryCode) {
		
		// construct the URL
		String url=EnvironmentDirectory.getAPIProvFacade() + "/rest/provisioning/retry";
		
		// build the request object
		ProvisioningRetryDTO dto = new ProvisioningRetryDTO(actionType, id, retryCode);

		// complete the API request
		Response response = RestAssuredUtil.galaxionPut(url, dto.toString(), null);
		return new APITransaction(url,response);
	}
	
	/**
	 * Use the provisioning facade API to adopt a provisioning error
	 * @param id
	 * @return API transaction object containing the URL and API response
	 */
	public static APITransaction adoptProvisioningError(int id) {
		return actionProvisioningError(ActionType.ADOPT, id, null);
	}
	
	/**
	 * Retry a provisioning error via the provisioning facade API
	 * @param id - the ID from provisioning-facade's SERVICE table
	 * @param retryCode
	 * @return API transaction object containing the URL and API response
	 */
	public static APITransaction retryProvisioningError(int id, String retryCode) {
		return actionProvisioningError(ActionType.RETRY, id, retryCode);
	}
	
	public static APITransaction getMsisdnSwapInventory(String token) {
		
		// construct the URL
		String url=EnvironmentDirectory.getAPIProvFacade() + "/private/auth/msisdn_swap_inventory";
		Response r = RestAssuredUtil.galaxionGet(url, token);
		return new APITransaction(url,r);
	}
}