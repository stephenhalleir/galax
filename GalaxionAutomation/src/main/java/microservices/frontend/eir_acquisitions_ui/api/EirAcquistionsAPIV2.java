package microservices.frontend.eir_acquisitions_ui.api;

import io.restassured.response.Response;
import microservices.backend.eir_catalog_core_backend.enums.Brand;
import microservices.backend.eir_catalog_core_backend.enums.OfferType;
import microservices.backend.keycloak.api.KeycloakAPI;
import microservices.backend.keycloak.dao.KeycloakDAO;
import microservices.backend.keycloak.data_model.Client;
import microservices.backend.keycloak.enums.Realm;
import microservices.frontend.eir_crm_ui_frontend.dto.requests.create_prospect.CreateProspectDTO;
import testCases.Services;
import utilities.api.RestAssuredUtil;
import utilities.galaxion.environments.EnvironmentDirectory;
import utilities.galaxion.environments.LoginCredentials;
import utilities.generic.api.APITransaction;
import utilities.generic.pojo_generation.pojo_to_json.PojoToJsonConverter;

public class EirAcquistionsAPIV2 {
	
	/**
	 * Generate a retail token for the eir CRM UI
	 * @return token string
	 */
	public static String getAcquisitionRetailToken() {
		LoginCredentials login=EnvironmentDirectory.getEirPAYGRetailLogin();
		Client c = KeycloakDAO.getClient(Services.PAYG_CRM_UI, Realm.eir);
		return KeycloakAPI.getToken(c.getClientId(), c.getSecret(), Realm.eir, login.getUsername(), login.getPassword());
		//return KeycloakUtil.getPrivateAuthToken(Microservice.ACQUISITIONS_UI, LoginType.EIR_B2C_RETAIL);
	}

	/**
	 * Generate a telesales token for the eir CRM UI
	 * @return token string
	 */
	public static String getAcquisitionTelesalesToken() {
		LoginCredentials login=EnvironmentDirectory.getEirPAYGTelesalesLogin();
		Client c = KeycloakDAO.getClient(Services.PAYG_CRM_UI, Realm.eir);
		return KeycloakAPI.getToken(c.getClientId(), c.getSecret(), Realm.eir, login.getUsername(), login.getPassword());
	}
	
	/**
	 * Create a prospect on the eir CRM UI for acquisitions journey
	 * @param token
	 * @param brand
	 * @param offerType
	 * @return url and response
	 */
	public static APITransaction createProspect(String token,Brand brand, OfferType offerType) {
		String url = EnvironmentDirectory.getPAYGCRMAPI() + "/prospect/private/auth/prospect";

		// generate the payload
		CreateProspectDTO dto = new CreateProspectDTO(brand,offerType);
		String payload=PojoToJsonConverter.getJSON(dto);
		
		// trigger the request
		Response r = RestAssuredUtil.galaxionPost(url, payload, token);

		return new APITransaction(url, payload, r);
	}
	
	
}
