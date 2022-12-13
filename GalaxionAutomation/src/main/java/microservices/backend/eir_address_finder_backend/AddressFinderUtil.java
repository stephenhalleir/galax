package microservices.backend.eir_address_finder_backend;

import java.util.List;

import io.restassured.path.json.JsonPath;
import microservices.backend.eir_address_finder_backend.data_model.AddressFinderAddress;
import utilities.generic.api.APITransaction;

public class AddressFinderUtil {

	/**
	 * Get the list of addresses for a particular eircode
	 * 
	 * @param eircode
	 * @return list of Addresses
	 */
	public static List<AddressFinderAddress> getAddresses(String eircode) {

		// make a call to the address finder API to retrieve the response
		APITransaction result = AddressFinderAPI.getAddress(eircode);
		
		// get the data from the "addresses" node and convert it to a list of Address objects
		JsonPath jsonPathEvaluator = result.getResponse().jsonPath();
		List<AddressFinderAddress> addresses = jsonPathEvaluator.getList("addresses", AddressFinderAddress.class);

		// return the list of addresses
		return addresses;
	}
}
