package microservices.backend.eir_customer_offer_backend.api;

import io.restassured.response.Response;
import microservices.backend.eir_subscription_management_backend.SubscriptionManagementDAO;
import microservices.backend.eir_subscription_management_backend.data_model.Service;
import microservices.backend.eir_subscription_management_backend.enums.TerminationReason;
import microservices.backend.keycloak.enums.Microservice;
import microservices.frontend.common_ui.enums.CustomerType;
import utilities.api.RestAssuredUtil;
import utilities.galaxion.environments.EnvironmentDirectory;
import utilities.generic.api.APITransaction;
import utilities.test.config_readers.JSONRequestManager;

/**
* The CustomerOfferAPI class provides an interface
* into the customer-offer-backend
*
* @author  Stephen Hall
* @version 1.0
* @since   22/03/2021
*/
public class CustomerOfferAPI {

	/**
	 * This method will use the route /private/auth/service/{service_id}/terminate
	 * to schedule a cancellation for a particular customer.
	 * 
	 * @param serviceID This is the service ID for the subscriber
	 * @param reason 	This is the cancellation reason
	 * @return 			The response object
	 */
	public static APITransaction scheduleCancellationPrivate(int serviceID, TerminationReason reason, String token) {
		
		// populate the endpoint for the URL
		String url = EnvironmentDirectory.getAPICustomerOffer() + "/private/auth/service/$serviceID/terminate";
		url = url.replace("$serviceID", Integer.toString(serviceID));

		// generate the payload from the file
		String payload = JSONRequestManager.getJSONRequest("requests/json/cancellations/cancellation_request.json");
		payload = payload.replace("$reason", reason.toString());
		payload = payload.replace("$otherReason", reason + " " + System.currentTimeMillis());

		System.out.println("Payload: " + payload + "\nURL: " + url);

		// trigger the cancellation request
		Response r = RestAssuredUtil.galaxionPost(url, payload, token);

		System.out.println(r.getStatusCode() + ": " + r.asString());
		
		// return the response object
		return new APITransaction(url,r);
	}
	
	
	public static Response reactivateSubscriberPrivate(int serviceID, String token) {
		
		// populate the endpoint for the URL
		String url = EnvironmentDirectory.getAPICustomerOffer() + "/private/auth/service/$serviceId/reactivate";
		url = url.replace("$serviceId", Integer.toString(serviceID));

		// fire the cancellation request
		Response r = RestAssuredUtil.galaxionPost(url, null, token);

		System.out.println(r.getStatusCode() + ": " + r.asString());
		
		return r;
	}

	// test route
	// https://eir-customer-offer-backend-webservice/private/auth/service/{service_id}/activate/replace_sim
	public static boolean eirRetailCompleteSIMSwap(int serviceID, String iccid, String token) {

		// populate the json request
		String payload = JSONRequestManager.getJSONRequest("requests/json/simswaps/eir/retail/activate_sim_replacement.json");
		payload = payload.replace("$iccid", iccid);

		// populate the url
		String url = EnvironmentDirectory.getAPICustomerOffer() + "/private/auth/service/$service_id/activate/replace_sim";
		url = url.replace("$service_id", Integer.toString(serviceID));

		System.out.println(url + "\n" + payload);

		// fire the cancellation request
		Response r = RestAssuredUtil.galaxionPost(url, payload, token);

		System.out.println(r.getStatusCode() + ": " + r.asString());
		return true;
	}


	public static Response activateReplacementSIM(int serviceID, String puk, String token) {
		
		// determine the customer type
		CustomerType customerType = SubscriptionManagementDAO.getCustomerType(serviceID);
		
		String realm_id=null;
		
		// populate the json request
		String payload = JSONRequestManager.getJSONRequest("requests/json/simswaps/eir/telesales/activate_replacement_sim.json");
		payload = payload.replace("$puk", puk);
		
		if(customerType==CustomerType.GOMO) {
			payload = payload.replace("$brand", "GOMO");
			payload = payload.replace("$channelName", "ESHOP");
			payload = payload.replace("$channelType", "INDIRECT");
			realm_id="gomo";
		}
		else if(customerType==CustomerType.EIR_PREPAY){
			payload = payload.replace("$brand", "EIR");
			payload = payload.replace("$channelName", "TELESALES");
			payload = payload.replace("$channelType", "DIRECT");
			realm_id="eir"; 
		}
		else {
			return null;
		}

		// populate the url
		String url = EnvironmentDirectory.getAPICustomerOffer() + "/private/auth/service/$service_id/activate/replace_sim";
		url = url.replace("$service_id", Integer.toString(serviceID));

		System.out.println(url + "\n" + payload);

		// fire the cancellation request
		Response r = RestAssuredUtil.galaxionPost(url, payload, token);

		System.err.println(payload);
		
		System.out.println(r.getStatusCode() + ": " + r.asString());
		return r;
	}
}
