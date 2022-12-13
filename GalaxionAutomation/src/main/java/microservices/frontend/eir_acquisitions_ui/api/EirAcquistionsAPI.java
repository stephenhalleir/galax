package microservices.frontend.eir_acquisitions_ui.api;

import io.restassured.response.Response;
import microservices.backend.eir_catalog_core_backend.enums.Brand;
import microservices.backend.eir_catalog_core_backend.enums.ChannelCode;
import microservices.backend.eir_catalog_core_backend.enums.OfferType;
import microservices.backend.keycloak.api.KeycloakAPI;
import microservices.backend.keycloak.dao.KeycloakDAO;
import microservices.backend.keycloak.data_model.Client;
import microservices.backend.keycloak.enums.Realm;
import microservices.frontend.common_ui.dto.acquisitions.CreateProspectDTO;
import testCases.Services;
import utilities.api.RestAssuredUtil;
import utilities.galaxion.environments.EnvironmentDirectory;
import utilities.galaxion.environments.LoginCredentials;
import utilities.generic.api.APITransaction;
import utilities.generic.pojo_generation.pojo_to_json.PojoToJsonConverter;

public class EirAcquistionsAPI {
	
	/*
	public static String getAcquisitionRetailToken() {
		return KeycloakUtil.getPrivateAuthToken(Microservice.ACQUISITIONS_UI, LoginType.EIR_B2C_RETAIL);
	}
	*/

	public static String getAcquisitionTelesalesToken() {
		LoginCredentials login=EnvironmentDirectory.getEirPAYGTelesalesLogin();
		Client c = KeycloakDAO.getClient(Services.PAYG_CRM_UI, Realm.eir);
		return KeycloakAPI.getToken(c.getClientId(), c.getSecret(), Realm.eir, login.getUsername(), login.getPassword());
	}
	
	public static APITransaction createRetailProspect(String token) {
		return createProspect(token, Brand.EIR, ChannelCode.EIR_RETAIL, OfferType.PREPAY);
	}
	
	public static APITransaction postOfferToCart(String token,String prospectUuid, int offerID) {
		String url=EnvironmentDirectory.getPAYGCRMAPI() + "/prospect/private/auth/prospect/" + prospectUuid + "/cart/offer";
		String addOfferPayload="{catalogOfferId: " + offerID + "}";
		Response r = RestAssuredUtil.galaxionPost(url,addOfferPayload,null);
		return new APITransaction(url, addOfferPayload, r);
	}
	
	public static APITransaction getProspect(String token, String prospectUuid) {
		return triggerGetRequest(token,EnvironmentDirectory.getPAYGAcquisitionAPI() + "/prospect/private/auth/prospect/" + prospectUuid);
	}
	
	public static APITransaction getCustomerDetails(String token, String prospectUuid) {
		return triggerGetRequest(token,EnvironmentDirectory.getPAYGAcquisitionAPI() + "/prospect/private/auth/prospect/" + prospectUuid + "/customer_details");
	}
	
	public static APITransaction getCartDetails(String token, String prospectUuid) {
		return triggerGetRequest(token,EnvironmentDirectory.getPAYGAcquisitionAPI() + "/prospect/private/auth/prospect/" + prospectUuid + "/cart");
	}
	
	public static APITransaction getAvailableOffers(String token, String prospectUuid) {
		return triggerGetRequest(token,EnvironmentDirectory.getPAYGAcquisitionAPI() + "/prospect/private/auth/offer?prospectUuid=" + prospectUuid);
	}
	
	public static APITransaction getAvailableDiscounts(String token, String prospectUuid) {
		return triggerGetRequest(token,EnvironmentDirectory.getPAYGAcquisitionAPI() + "/prospect/private/auth/offer/2002/discount?prospectUuid=" + prospectUuid);
	}
	
	public static APITransaction getAvailableAddons(String token, String prospectUuid) {
		return triggerGetRequest(token,EnvironmentDirectory.getPAYGAcquisitionAPI() + "/prospect/private/auth/offer/2002/addon?prospectUuid=" + prospectUuid);
	}
	
	public static APITransaction getAvailableHandsetsForOffer(String token, int offerID) {
		return triggerGetRequest(token,EnvironmentDirectory.getPAYGAcquisitionAPI() + "/prospect/private/auth/offer/" + offerID + "/handset?page=0");
	}
	
	public static APITransaction getAvailableHandsetManufacturers(String token, int offerID) {
		return triggerGetRequest(token,EnvironmentDirectory.getPAYGAcquisitionAPI() + "/prospect/private/auth/offer/" + offerID + "/handset/manufacturer");
	}
	
	public static APITransaction getRefSecurityQuestions(String token) {
		return triggerGetRequest(token,EnvironmentDirectory.getPAYGAcquisitionAPI() + "/security-question/private/auth/question/reference");
	}
	
	public static APITransaction eircodeLookup(String token, String eircode) {
		return triggerGetRequest(token,EnvironmentDirectory.getPAYGAcquisitionAPI() + "/address-finder/public/address?eircode="+eircode);
	}
	
	public static APITransaction getHandsetsByOfferIDAndManufacturer(String token, int offerCatalogID, String manufacturer) {
		return triggerGetRequest(token,EnvironmentDirectory.getPAYGAcquisitionAPI() + "/prospect/private/auth/offer/" + offerCatalogID + "/handset?page=0&manufacturers=" + manufacturer);
	}
	
	public static APITransaction getHostedPaymentPage(String token, String prospectUuid) {
		return triggerGetRequest(token,EnvironmentDirectory.getPAYGAcquisitionAPI() + "/prospect/private/auth/payment/" + prospectUuid + "/hosted_payment_page");
	}
	
	
	
	/**
	 * Create a prospect via the PAYG Acquisitions UI API
	 * 
	 * @param brand
	 * @param channelCode
	 * @param channelType
	 * @param offerType
	 * @return
	 */
	public static APITransaction createProspect(String token, Brand brand, ChannelCode channelCode, OfferType offerType) {

		// construct the URL
		String url = EnvironmentDirectory.getPAYGCRMAPI() + "/prospect/private/auth/prospect";

		CreateProspectDTO dto = new CreateProspectDTO(brand, channelCode, offerType);

		// trigger the request
		Response r = RestAssuredUtil.galaxionPost(url, PojoToJsonConverter.getJSON(dto), token);

		return new APITransaction(url, r);
	}
	
	public static String getRetailToken() {
		LoginCredentials login=EnvironmentDirectory.getEirPAYGRetailLogin();
		Client c = KeycloakDAO.getClient(Services.PAYG_CRM_UI, Realm.eir);
		return KeycloakAPI.getToken(c.getClientId(), c.getSecret(), Realm.eir, login.getUsername(), login.getPassword());
	}
	
	public static String getTelesalesToken() {
		LoginCredentials login=EnvironmentDirectory.getEirPAYGTelesalesLogin();
		Client c = KeycloakDAO.getClient(Services.PAYG_CRM_UI, Realm.eir);
		return KeycloakAPI.getToken(c.getClientId(), c.getSecret(), Realm.eir, login.getUsername(), login.getPassword());
	}
	
	private static APITransaction triggerGetRequest(String token,String url) {
		return new APITransaction(url, RestAssuredUtil.galaxionGet(url, token));
	}
}
