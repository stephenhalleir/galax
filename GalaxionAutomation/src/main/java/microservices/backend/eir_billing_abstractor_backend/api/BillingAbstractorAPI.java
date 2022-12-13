package microservices.backend.eir_billing_abstractor_backend.api;

import io.restassured.response.Response;
import utilities.api.RestAssuredUtil;
import utilities.galaxion.environments.EnvironmentDirectory;
import utilities.generic.api.APITransaction;

public class BillingAbstractorAPI {
	
	public static APITransaction getUsageSummary(int serviceID, String billingPeriod) {
		String url=EnvironmentDirectory.getAPIBillingAbstractor() + "/service/" + serviceID + "/usage_summary?billing_period=" + billingPeriod;
		Response r = RestAssuredUtil.galaxionGet(url,null);
		
		return new APITransaction(url,r);
	}
	
	public static APITransaction getUsage(int serviceID, String billingPeriod) {
		String url=EnvironmentDirectory.getAPIBillingAbstractor() + "/service/" + serviceID + "/usage?billing_period=" + billingPeriod;
		Response r = RestAssuredUtil.galaxionGet(url,null);
		
		return new APITransaction(url,r);
	}
	
	public static APITransaction getOneOffCharges(int serviceID, String billingPeriod) {
		String url=EnvironmentDirectory.getAPIBillingAbstractor() + "/service/" + serviceID + "/usage?billing_period=" + billingPeriod;
		Response r = RestAssuredUtil.galaxionGet(url,null);
		
		return new APITransaction(url,r);
	}
}
