package microservices.backend.eir_inventory_management_backend.api;

import io.restassured.response.Response;
import microservices.backend.eir_inventory_management_backend.objects.SimDetails;
import utilities.api.RestAssuredUtil;
import utilities.galaxion.environments.EnvironmentDirectory;
import utilities.generic.api.APITransaction;
import utilities.generic.api.HTTPMethod;

public class InventoryManagementAPI {

	/**
	 * Retrieve the imsi, pin and puk from the inventory management API
	 * @param iccid
	 * @return an object containing the imsi, pin1, pin2, puk1 and puk2
	 */
	public static SimDetails getSimDetailsFromInventory(String iccid) {
		
		// construct the URL
		String url = EnvironmentDirectory.getAPIInventory() + "/inventory/sim?iccid=" + iccid;
		
		// make a GET call to the inventory management API
		Response response=RestAssuredUtil.galaxionGet(url, null);
		
		System.err.println(url + "\n" + response.asString());
		
		// build and return the result object
		SimDetails securityDetails = new SimDetails();
		securityDetails.setPin1((String) response.jsonPath().get("pin1"));
		securityDetails.setPin2((String) response.jsonPath().get("pin2"));
		securityDetails.setPuk1((String) response.jsonPath().get("puk1"));
		securityDetails.setPuk2((String) response.jsonPath().get("puk2"));
		securityDetails.setImsi((String) response.jsonPath().get("imsi"));
		securityDetails.setIccid(iccid);
		return securityDetails;
	}
	
	public static APITransaction getHandset(String imei) {
		// construct the URL
		String url = EnvironmentDirectory.getAPIInventory() + "/private/auth/handsets/serial/" + imei;
				
		// make a GET call to the inventory management API
		Response response=RestAssuredUtil.galaxionGet(url, null);
		
		return new APITransaction(url,response);
	}
}
