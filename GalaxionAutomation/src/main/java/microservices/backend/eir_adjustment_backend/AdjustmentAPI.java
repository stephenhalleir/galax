package microservices.backend.eir_adjustment_backend;

import io.restassured.response.Response;
import microservices.backend.eir_adjustment_backend.dto.B2BCreateAdjustmentDTO;
import microservices.backend.eir_adjustment_backend.dto.CreateHardwareFundAdjustmentDTO;
import microservices.backend.eir_adjustment_backend.enums.AdjustmentReason;
import utilities.api.RestAssuredUtil;
import utilities.galaxion.environments.EnvironmentDirectory;
import utilities.generic.api.APITransaction;
import utilities.generic.pojo_generation.pojo_to_json.PojoToJsonConverter;

public class AdjustmentAPI {
	
	/**
	 * Use the ADJUSTMENT API to make a hardware fund adjustment
	 * @param token
	 * @param hardwareFundID
	 * @param amount
	 * @param reason
	 * @return APITransaction object containing the URL and response
	 */
	public static APITransaction makeHardwareFundAdjustmentNew(String token, int hardwareFundID,int amount, AdjustmentReason reason) {
		
		// create the json object
		CreateHardwareFundAdjustmentDTO dto = new CreateHardwareFundAdjustmentDTO(amount, reason, "Test hardware fund adjustment " + System.currentTimeMillis());
		
		// populate the URL
		String url=EnvironmentDirectory.getAPIAdjustment()+"/api/v1/private/auth/adjustment/hardware_fund/" + hardwareFundID;
				
		// post the adjustment to the API
		Response r = RestAssuredUtil.galaxionPost(url, PojoToJsonConverter.getJSON(dto), token);

		return new APITransaction(url,r);
	}
	
	/**
	 * Use the ADJUSTMENT API to make an adjustment on an account
	 * @param billingAccountID
	 * @param amount
	 * @param reason
	 * @return APITransaction object containing the URL and response
	 */
	public static APITransaction postAdjustment(String token, int billingAccountID, int amount,AdjustmentReason reason) {
		 
		// create the Json object
		B2BCreateAdjustmentDTO dto = new B2BCreateAdjustmentDTO(amount, reason);
		
		// populate the URL
		String url=EnvironmentDirectory.getAPIAdjustment()+"/api/v1/private/auth/adjustment/" + billingAccountID;
		
		// post the adjustment to the API
		Response r = RestAssuredUtil.galaxionPost(url, PojoToJsonConverter.getJSON(dto), token);

		return new APITransaction(url,r);
	}
}
