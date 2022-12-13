package microservices.backend.eir_elavon_facade_backend.api;

import io.restassured.path.json.JsonPath;
import microservices.frontend.eir_eshop_frontend.dto.HppRequest;
import utilities.generic.api.APITransaction;

public class ElavonFacadeUtility {

	public static HppRequest getHppRequest(String uuid) {
		APITransaction t = ElavonFacadeAPI.generateHPP(uuid);
		System.out.println(t);
		
		JsonPath jsonPathEvaluator = t.getResponse().jsonPath();
		HppRequest hpp = jsonPathEvaluator.getObject("hppRequest", HppRequest.class);
		return hpp;
	}
	
}
