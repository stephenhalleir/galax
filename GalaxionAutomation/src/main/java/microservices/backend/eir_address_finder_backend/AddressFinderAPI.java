package microservices.backend.eir_address_finder_backend;

import io.restassured.response.Response;
import utilities.api.RestAssuredUtil;
import utilities.galaxion.environments.EnvironmentDirectory;
import utilities.generic.api.APITransaction;

/**
 * This class allows for access to the eir-address-finder-backend API
 * @author stephenhall
 *
 */
public class AddressFinderAPI {

	/**
	 * Get an address
	 * @param eircode
	 */
	public static APITransaction getAddress(String eircode) {
		String url=EnvironmentDirectory.getAPIAddressFinder() + "/public/address?eircode=" + eircode;
		Response r = RestAssuredUtil.galaxionGet(url, null);
		return new APITransaction(url,r);
	}
}
