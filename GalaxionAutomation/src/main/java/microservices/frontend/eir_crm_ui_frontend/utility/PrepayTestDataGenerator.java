package microservices.frontend.eir_crm_ui_frontend.utility;

import static org.testng.Assert.assertEquals;

import java.util.ArrayList;

import io.restassured.path.json.JsonPath;
import microservices.backend.eir_catalog_core_backend.dao.CatalogCoreDAO;
import microservices.backend.eir_catalog_core_backend.data_model.AddonBundle;
import microservices.backend.eir_catalog_core_backend.enums.ChannelCode;
import microservices.backend.eir_catalog_core_backend.enums.OfferType;
import microservices.backend.eir_catalog_core_backend.enums.SubscriptionAddonBundle;
import microservices.backend.eir_prospect_backend.dao.ProspectDAO;
import microservices.backend.eir_prospect_backend.data_model.ProspectOffer;
import microservices.frontend.eir_crm_ui_frontend.api.PAYGCRMAPI;
import utilities.generic.api.APITransaction;

public class PrepayTestDataGenerator {

	public static String generatePrepaySubscriber(String msisdn, String iccid, int topupAmount, SubscriptionAddonBundle addonCode) {
		
		// determine the addon code for the addon
		AddonBundle bundle = CatalogCoreDAO.getAddonBundleForCode(addonCode);
		int addonID=bundle.getId();
		
		// generate a token on the CRM UI
		String token = PAYGCRMAPI.getEirRetailToken();
		
		// create prospect
		APITransaction t = PAYGCRMAPI.createProspect(token, ChannelCode.EIR_RETAIL, OfferType.PREPAY);
		System.out.println(t.toString());
		assertEquals(t.getResponse().getStatusCode(),200);
		
		// get the prospect uuid
		JsonPath jsonPathEvaluator = t.getResponse().jsonPath();
		String uuid = jsonPathEvaluator.getString("prospectUuid");
		String orderReference = jsonPathEvaluator.getString("orderReference");
		
		// add an offer to the cart
		t = PAYGCRMAPI.addOfferToCart(token, uuid, 2002);
		System.out.println(t.toString());
		assertEquals(t.getResponse().getStatusCode(),200);
		
		// get the prospect and offer IDs from the database
		microservices.backend.eir_prospect_backend.data_model.Prospect prospect = ProspectDAO.getProspect(uuid);
		ArrayList<ProspectOffer> offers = ProspectDAO.getOffersForProspect(prospect.getId());
	
		// get the offer ID
		int offerID=offers.get(0).getId();
		
		// set the topup amount
		t = PAYGCRMAPI.setTopupAmountOnProspectOffer(token, uuid, offerID, topupAmount);
		System.out.println(t.toString());
		assertEquals(t.getResponse().getStatusCode(),200);
		
		// assign the SIM card
		t = PAYGCRMAPI.assignSIMToProspectOffer(token, uuid, offerID, iccid,msisdn);
		System.out.println(t.toString());
		assertEquals(t.getResponse().getStatusCode(),200);
		
		// add an addon to the offer in the cart
		t = PAYGCRMAPI.addAddonToProspectOffer(token, uuid, offerID, addonID);
		System.out.println(t.toString());
		assertEquals(t.getResponse().getStatusCode(),200);
		
		// set the prospect as anonymous
		t = PAYGCRMAPI.setProspectAsAnonymous(token, uuid);
		System.out.println(t.toString());
		assertEquals(t.getResponse().getStatusCode(),200);
		
		// accept T&Cs
		t = PAYGCRMAPI.acceptProspectTCs(token, uuid);
		System.out.println(t.toString());
		assertEquals(t.getResponse().getStatusCode(),204);
		
		// validate & submit
		t = PAYGCRMAPI.validateRetailProspect(token, uuid);
		System.out.println(t.toString());
		assertEquals(t.getResponse().getStatusCode(),204);
		
		return orderReference;
	}
	
}
