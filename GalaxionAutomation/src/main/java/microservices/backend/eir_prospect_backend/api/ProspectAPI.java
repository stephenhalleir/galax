package microservices.backend.eir_prospect_backend.api;

import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import microservices.backend.eir_prospect_backend.enums.ProspectType;
import microservices.backend.eir_prospect_backend.objects.Prospect;
import utilities.api.RestAssuredUtil;
import utilities.galaxion.environments.EnvironmentDirectory;
import utilities.test.config_readers.JSONRequestManager;

public class ProspectAPI {

	private static String requestFolder = "requests/json/acquisitions";

	// create a prospect and return the RESPONSE object
	public static Response createProspectBE(String brand, String channelCode, String offerType) {
		String url = EnvironmentDirectory.getAPIProspect() + "/public/prospect";
		String payload = JSONRequestManager.getJSONRequest(requestFolder + "/create_prospect.json");

		payload = payload.replace("$brand", brand);
		payload = payload.replace("$offerType", offerType);
		payload = payload.replace("$channelCode", channelCode);
		Response r = RestAssuredUtil.galaxionPost(url, payload, null);
		return r;
	}


	// create a prospect and return the PROSPECT object
	public static Prospect createAndReturnProspect(String brand, String channelCode, String offerType) {
		Prospect p = new Prospect(brand, channelCode, offerType);
		Response r = createProspectBE(brand, channelCode, offerType);
		JsonPath jsonPathEvaluator = r.jsonPath();
		p.setUuid((String) jsonPathEvaluator.get("prospectUuid"));
		p.setOrderNumber((String) jsonPathEvaluator.get("orderReference"));

		return p;
	}
	
	/**
	 * Create a prospect based on prospect type
	 * @param prospectType
	 * @return Prospect object
	 */
	public static Prospect createProspect(ProspectType prospectType) {
		
		Prospect prospect = new Prospect(prospectType);
		
		String url = EnvironmentDirectory.getAPIProspect() + "/public/prospect";
		Response r = RestAssuredUtil.galaxionPost(url, prospect.toJSONString(), null);
		
		JsonPath jsonPathEvaluator = r.jsonPath();
		prospect.setUuid((String) jsonPathEvaluator.get("prospectUuid"));
		prospect.setOrderNumber((String) jsonPathEvaluator.get("orderReference"));

		return prospect;
	}
	
	
	
	public static void main(String [] args){
		
		// test the creation of a gomo prospect
		Prospect gomoProspect = createProspect(ProspectType.GOMO);
		System.out.println(gomoProspect);
		
		// test the creation of an eir prepay telesales prospect
		Prospect eirPaygTelesalesProspect = createProspect(ProspectType.EIR_PREPAY_TELESALES);
		System.out.println(eirPaygTelesalesProspect);
		
		// test the creation of an eir prepay retail prospect
		Prospect eirPaygRetailProspect = createProspect(ProspectType.EIR_PREPAY_RETAIL);
		System.out.println(eirPaygRetailProspect);
		
		// test the creation of an eir postpay retail prospect
		Prospect eirPostpayRetailProspect = ProspectAPI.createProspect(ProspectType.EIR_POSTPAY_RETAIL);
		System.out.println(eirPostpayRetailProspect);
	}
}
