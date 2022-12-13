package framework.test_data.galaxion;

import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertNotNull;
import static org.testng.Assert.assertTrue;

import java.util.ArrayList;

import external_systems.elavon.ElavonUtility;
import io.restassured.path.json.JsonPath;
import microservices.backend.eir_elavon_facade_backend.api.HppResponse;
import microservices.backend.eir_logistics_backend.utility.Logistics;
import microservices.backend.eir_order_management_backend.dto.LogisticsDTO;
import microservices.backend.eir_order_management_backend.monitor.OrderManagementMonitor;
import microservices.backend.eir_subscription_management_backend.SubscriptionManagementDAO;
import microservices.frontend.eir_eshop_frontend.api.GoMoEShopAPI;
import microservices.frontend.eir_eshop_frontend.dto.HppRequest;
import utilities.galaxion.test_data.accounts.DataSetupUtil;
import utilities.generic.api.APITransaction;
import utilities.generic.pojo_generation.pojo_to_json.PojoToJsonConverter;

public class TestDataGenerator {

	public static ArrayList<String> generateGoMoTestData(int offers) {

		ArrayList<String> msisdns = new ArrayList<String>();
		
		// specify the catalog code ID
		int catalogOfferID=1003;
		
		// create the prospect
		APITransaction t = GoMoEShopAPI.createProspect();
		System.out.println(t.toString());
		assertEquals(200, t.getResponse().statusCode());

		// read the prospect uuid from the response
		JsonPath jsonPathEvaluator = t.getResponse().jsonPath();
		String prospectUuid = (String) jsonPathEvaluator.get("prospectUuid");
		String orderNumber = (String) jsonPathEvaluator.get("orderReference");

		// verify that the prospect uuid has been created
		assertNotNull(prospectUuid);

		// add the offers to the cart
		for(int i=0;i<offers;i++) {
			t = GoMoEShopAPI.postOfferToCart(prospectUuid, catalogOfferID);
			assertEquals(200, t.getResponse().statusCode());	
		}
		
		// update customer details
		t = GoMoEShopAPI.updateCustomerDetails(prospectUuid);
		assertEquals(t.getResponse().statusCode(),200);

		// accept T&Cs
		t = GoMoEShopAPI.acceptTermsAndConditions(prospectUuid);
		assertEquals(204, t.getResponse().statusCode());
		
		// handle the payment
		t = GoMoEShopAPI.getHostedPaymentPage(prospectUuid);
		assertEquals(200, t.getResponse().statusCode());
		jsonPathEvaluator = t.getResponse().jsonPath();
		HppRequest hpp = jsonPathEvaluator.getObject("hppRequest", HppRequest.class);
		HppResponse hppResponse = ElavonUtility.handleHpp(hpp);
		
		// validate the prospect
		t = GoMoEShopAPI.validate(prospectUuid, PojoToJsonConverter.getJSON(hppResponse));

		// verify that the response code is 204
		assertEquals(t.getResponse().statusCode(), 204);
		
		// wait for the order to reach the delivery step
		boolean orderAwaitingDelivery = OrderManagementMonitor.waitForOrderToReachStep(orderNumber,"DELIVERY");
		assertTrue(orderAwaitingDelivery);

		// process logistics
		ArrayList<LogisticsDTO> dtos = Logistics.processLogisticsBackend(orderNumber);

		boolean orderCompleted = OrderManagementMonitor.waitForOrderToComplete(orderNumber);
		assertTrue(orderCompleted);

		// verify that the services are active
		for (LogisticsDTO dto : dtos) {
			assertTrue(SubscriptionManagementDAO.getServiceStatus(dto.getMsisdn()).equals("ACTIVE"));
			System.out.println("Service " + dto.getMsisdn() + " is now ACTIVE");
			msisdns.add(dto.getMsisdn());
		}
		
		return msisdns;
	}
	
	public static ArrayList<String> generateEirB2CTestData(int offers) {

		ArrayList<String> msisdns = TestDataGenerator.generateGoMoTestData(offers);
		for(String msisdn:msisdns) {
			DataSetupUtil.convertToB2C(msisdn);
		}
		return msisdns;
	}
}
