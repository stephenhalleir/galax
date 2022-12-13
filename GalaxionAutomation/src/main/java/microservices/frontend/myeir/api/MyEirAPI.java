package microservices.frontend.myeir.api;

import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import microservices.backend.eir_subscription_management_backend.enums.NDDSetting;
import microservices.frontend.myeir.dto.MyEirGraphQLRequest;
import utilities.api.RestAssuredUtil;
import utilities.generic.api.APITransaction;
import utilities.generic.pojo_generation.pojo_to_json.PojoToJsonConverter;

public class MyEirAPI {

	static String url="https://mytt01.eir.ie/graphql/";
	
	public static String getToken(String email, String password) {
		
		// build the request
		MyEirGraphQLRequest request = new MyEirGraphQLRequest();
		String query="query validateLogin($username: String!, $password: String!) {\n  login(username: $username, password: $password) {\n    access_token\n    expires_in\n    refresh_expires_in\n    refresh_token\n    __typename\n  }\n}\n";
		request.setOperationName("validateLogin");
		request.setQuery(query);
		request.getVariables().setUsername(email);
		request.getVariables().setPassword(password);
		
		// get the response
		Response r = RestAssuredUtil.galaxionPost(url, PojoToJsonConverter.getJSON(request), null);
		JsonPath jsonPathEvaluator = r.jsonPath();
		
		// return the value of "access_token"
		return (String) jsonPathEvaluator.get("data.login.access_token");	
	}
	
	public static APITransaction getMySubscriptionsAndContactDetails(String token) {
		
		// build the request
		MyEirGraphQLRequest request = new MyEirGraphQLRequest();
		String query="query getMySubscriptionsAndContactDetails($token: String!) {\n  getMySubscriptions(token: $token) {\n    subscriptions {\n      subscriptionId\n      catalogOfferId\n      services {\n        name\n        id\n        status\n        domain\n        __typename\n      }\n      __typename\n    }\n    __typename\n  }\n  getMyContactDetails(token: $token) {\n    contact {\n      person {\n        firstName\n        __typename\n      }\n      phoneNumbers {\n        phoneNumber\n        __typename\n      }\n      __typename\n    }\n    __typename\n  }\n}\n";
		request.setOperationName("getMySubscriptionsAndContactDetails");
		request.setQuery(query);
		request.getVariables().setToken(token);

		// get the response
		Response r = RestAssuredUtil.galaxionPost(url, PojoToJsonConverter.getJSON(request), token);
		return new APITransaction(url,r);
	}
	
	public static APITransaction getPrepayBalanceAndAddons(String token, String serviceID) {
		
		// build the request
		MyEirGraphQLRequest request = new MyEirGraphQLRequest();
		String query="query getMyPrepayBalanceMyDetailsMyAddons($token: String!, $serviceId: String!) {\n  getMyPrepayBalance(token: $token, serviceId: $serviceId) {\n    balance\n    __typename\n  }\n  getMyDetails(token: $token, serviceId: $serviceId) {\n    customerOfferDTO {\n      offerName\n      offerStatus\n      activationDate\n      msisdn\n      __typename\n    }\n    pin\n    puk\n    __typename\n  }\n  getMyAddons(token: $token, serviceId: $serviceId) {\n    description\n    __typename\n  }\n}\n";
		request.setOperationName("getMyPrepayBalanceMyDetailsMyAddons");
		request.setQuery(query);
		request.getVariables().setToken(token);
		request.getVariables().setServiceId(serviceID);
		
		// get the response
		Response r = RestAssuredUtil.galaxionPost(url, PojoToJsonConverter.getJSON(request), token);
		return new APITransaction(url,r);
	}
	
	public static APITransaction getProfileDetails(String token, String serviceID) {
		
		// build the request
		MyEirGraphQLRequest request = new MyEirGraphQLRequest();
		String query="query getMyPrepayBalanceMyDetailsMyAddons($token: String!, $serviceId: String!) {\n  getMyPrepayBalance(token: $token, serviceId: $serviceId) {\n    balance\n    __typename\n  }\n  getMyDetails(token: $token, serviceId: $serviceId) {\n    customerOfferDTO {\n      offerName\n      offerStatus\n      activationDate\n      msisdn\n      __typename\n    }\n    pin\n    puk\n    __typename\n  }\n  getMyAddons(token: $token, serviceId: $serviceId) {\n    description\n    __typename\n  }\n}\n";
		request.setOperationName("getMyPrepayBalanceMyDetailsMyAddons");
		request.setQuery(query);
		request.getVariables().setToken(token);
		request.getVariables().setServiceId(serviceID);
		
		// get the response
		Response r = RestAssuredUtil.galaxionPost(url, PojoToJsonConverter.getJSON(request), token);
		return new APITransaction(url,r);
	}
	
	public static APITransaction resetPassword(String emailAddress) {
		
		// build the request
		MyEirGraphQLRequest request = new MyEirGraphQLRequest();
		String query="mutation resetPassword($emailAddress: String!) {\n  resetPassword(emailAddress: $emailAddress) {\n    message\n    __typename\n  }\n}\n";
		request.setOperationName("resetPassword");
		request.setQuery(query);
		request.getVariables().setEmailAddress(emailAddress);
		
		// get the response
		Response r = RestAssuredUtil.galaxionPost(url, PojoToJsonConverter.getJSON(request), null);
		return new APITransaction(url,r);
	}
	
	public static APITransaction updateNDD(String token, String serviceID, NDDSetting preference) {
		
		// build the request
		MyEirGraphQLRequest request = new MyEirGraphQLRequest();
		String query="mutation nddPreference($token: String!, $serviceId: Int!, $nddPreference: String!) {\n  updateMyNDD(token: $token, serviceId: $serviceId, nddPreference: $nddPreference)\n}\n";
		request.setOperationName("nddPreference");
		request.setQuery(query);
		request.getVariables().setToken(token);
		request.getVariables().setServiceId(serviceID);
		request.getVariables().setNddPreference(preference.toString());
		
		// update the payload service ID from "1234" to 1234 (as serviceID in this request is an integer!)
		String payload = PojoToJsonConverter.getJSON(request);
		payload=payload.replace("\""+serviceID + "\"",serviceID);
		
		// get the response
		Response r = RestAssuredUtil.galaxionPost(url, payload, null);
		return new APITransaction(url,r);
	}
	
	public static APITransaction updateMyContactPermission(String token) {
		// TODO this one needs work
		MyEirGraphQLRequest request = new MyEirGraphQLRequest();
		String query="mutation updateMyContactPermission($token: String!, $allowThirdParty: Boolean!, $activeGroupInput: PermissionGroupResponseInput!, $inactiveGroupInput: PermissionGroupResponseInput!) {\n  updateMyContactPermission(\n    token: $token\n    allowThirdParty: $allowThirdParty\n    activeGroupInput: $activeGroupInput\n    inactiveGroupInput: $inactiveGroupInput\n  ) {\n    name\n    permissionGroup\n    permissions {\n      name\n      enabled\n      permission\n      __typename\n    }\n    __typename\n  }\n}\n";
		request.setOperationName("updateMyContactPermission");
		request.setQuery(query);
		request.getVariables().setToken(token);
		String payload="{\"operationName\":\"updateMyContactPermission\",\"variables\":{\"token\":\"$$token\",\"allowThirdParty\":true,\"activeGroupInput\":{\"permissionGroup\":\"ACTIVE_CUSTOMER\",\"permissions\":[{\"enabled\":true,\"name\":\"Email\",\"permission\":\"ALLOW_EMAIL_CONTACT\"},{\"enabled\":false,\"name\":\"SMS\",\"permission\":\"ALLOW_SMS_CONTACT\"},{\"enabled\":false,\"name\":\"Phone\",\"permission\":\"ALLOW_PHONE_CONTACT\"},{\"enabled\":false,\"name\":\"FOTS\",\"permission\":\"ALLOW_FOTS_CONTACT\"},{\"enabled\":false,\"name\":\"Direct mail\",\"permission\":\"ALLOW_DIRECT_MAIL_CONTACT\"}]},\"inactiveGroupInput\":{\"permissionGroup\":\"NO_LONGER_CUSTOMER\",\"permissions\":[{\"enabled\":true,\"name\":\"Email\",\"permission\":\"ALLOW_EMAIL_CONTACT\"},{\"enabled\":true,\"name\":\"SMS\",\"permission\":\"ALLOW_SMS_CONTACT\"},{\"enabled\":false,\"name\":\"Phone\",\"permission\":\"ALLOW_PHONE_CONTACT\"},{\"enabled\":false,\"name\":\"FOTS\",\"permission\":\"ALLOW_FOTS_CONTACT\"},{\"enabled\":false,\"name\":\"Direct mail\",\"permission\":\"ALLOW_DIRECT_MAIL_CONTACT\"}]}},\"query\":\"mutation updateMyContactPermission($token: String!, $allowThirdParty: Boolean!, $activeGroupInput: PermissionGroupResponseInput!, $inactiveGroupInput: PermissionGroupResponseInput!) {\\n  updateMyContactPermission(\\n    token: $token\\n    allowThirdParty: $allowThirdParty\\n    activeGroupInput: $activeGroupInput\\n    inactiveGroupInput: $inactiveGroupInput\\n  ) {\\n    name\\n    permissionGroup\\n    permissions {\\n      name\\n      enabled\\n      permission\\n      __typename\\n    }\\n    __typename\\n  }\\n}\\n\"}";
		payload=payload.replace("$$token", token);
		System.out.println(payload);
		Response r = RestAssuredUtil.galaxionPost(url, payload, null);
		return new APITransaction(url,r);
	}
	
	public static APITransaction getContactPermissions(String token) {
		String payload="{\"operationName\":\"permission\",\"variables\":{\"token\":\"$$token\"},\"query\":\"query permission($token: String!) {\\n  getMyPermissionDetails(token: $token) {\\n    permissionGroupResponse {\\n      name\\n      permissions {\\n        name\\n        enabled\\n        permission\\n        __typename\\n      }\\n      __typename\\n    }\\n    allowThirdParty\\n    __typename\\n  }\\n}\\n\"}\r\n";
		payload=payload.replace("$$token", token);
		Response r = RestAssuredUtil.galaxionPost(url, payload, null);
		return new APITransaction(url,r);
	}
	
	public static APITransaction sendRegistrationOTP(String msisdn) {
		String payload="{\"operationName\":\"requestEmailRecovery\",\"variables\":{\"msisdn\":\"$$msisdn\"},\"query\":\"query requestEmailRecovery($msisdn: String!) {\\n  requestEmailRecovery(msisdn: $msisdn) {\\n    otpUuid\\n    __typename\\n  }\\n}\\n\"}\r\n";
		payload=payload.replace("$$msisdn", msisdn);
		Response r = RestAssuredUtil.galaxionPost(url, payload, null);
		return new APITransaction(url,r);
	}
	
	public static APITransaction validateRegistrationOTP(String otpUuid, String otpCode) {
		String payload="{\"operationName\":\"validateOTPForEmailRecovery\",\"variables\":{\"otpUuid\":\"$$otpUuid\",\"otpCode\":\"$$otpCode\"},\"query\":\"mutation validateOTPForEmailRecovery($otpUuid: String!, $otpCode: String!) {\\n  validateOTPForEmailRecovery(otpUuid: $otpUuid, otpCode: $otpCode) {\\n    email\\n    verified\\n    __typename\\n  }\\n}\\n\"}\r\n";
		payload=payload.replace("$$otpUuid", otpUuid);
		payload=payload.replace("$$otpCode", otpCode);
		Response r = RestAssuredUtil.galaxionPost(url, payload, null);
		return new APITransaction(url,r);
	}
	
	
	// validate otp
	// {"operationName":"validateOTPForEmailRecovery","variables":{"otpUuid":"d0fe0992-1d20-458a-a1e8-8db3bd985b7d","otpCode":"840014"},"query":"mutation validateOTPForEmailRecovery($otpUuid: String!, $otpCode: String!) {\n  validateOTPForEmailRecovery(otpUuid: $otpUuid, otpCode: $otpCode) {\n    email\n    verified\n    __typename\n  }\n}\n"}

}
