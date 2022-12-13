package microservices.frontend.tecrep_monitor.api;

import java.io.File;
import java.math.BigInteger;

import io.restassured.response.Response;
import microservices.backend.keycloak.api.KeycloakAPI;
import microservices.backend.keycloak.dao.KeycloakDAO;
import microservices.backend.keycloak.data_model.Client;
import microservices.backend.keycloak.enums.LoginType;
import microservices.backend.keycloak.enums.Microservice;
import microservices.backend.keycloak.enums.Realm;
import microservices.backend.tecrep.resource_management.dto.AddNumberDTO;
import microservices.frontend.tecrep_monitor.dto.create_interval_numbers.CreateIntervalNumbersDTO;
import microservices.frontend.tecrep_monitor.dto.generate_number_block.GenerateNumberBlockDTO;
import microservices.frontend.tecrep_monitor.dto.generate_sim_card_batch.GenerateSimCardBatchDTO;
import microservices.frontend.tecrep_monitor.enums.InventoryPool;
import microservices.frontend.tecrep_monitor.enums.SimCardConfiguration;
import microservices.frontend.tecrep_monitor.responses.GetNumberBlockResponse;
import testCases.Services;
import utilities.api.RestAssuredUtil;
import utilities.galaxion.environments.EnvironmentDirectory;
import utilities.galaxion.environments.LoginCredentials;
import utilities.generic.api.APITransaction;
import utilities.generic.pojo_generation.pojo_to_json.PojoToJsonConverter;

public class TecrepMonitorAPI {

	/*------------------------------------------------------------------
	 *  Authentication
	 ------------------------------------------------------------------*/
	/**
	 * Generate a token for in order to access eir-tecrep-monitor-api functionality
	 * @return token string
	 */
	public static String getToken() {
		LoginCredentials login=EnvironmentDirectory.getTecrepAdminLogin();
		Client c = KeycloakDAO.getClient(Services.TECREP_MONITOR, Realm.eir);
		return KeycloakAPI.getToken(c.getClientId(), c.getSecret(), Realm.eir, login.getUsername(), login.getPassword());
	}
	
	/*------------------------------------------------------------------
	 *  Main screen
	 ------------------------------------------------------------------*/
	/**
	 * Retrieve the information for the resources dashboard
	 * @param token
	 * @return url and response
	 */
	public static APITransaction getResourcesDashboard(String token) {
		String url=EnvironmentDirectory.getTecrepMonotirAPI() + "/rsc/private/auth/resourcesDashboard";
		Response r = RestAssuredUtil.galaxionGet(url, token);
		return new APITransaction(url,r);
	}
	
	/**
	 * Retrieve the information for the equipments dashboard
	 * @param token
	 * @return url and response
	 */
	public static APITransaction getEquipmentsDashboard(String token) {
		String url=EnvironmentDirectory.getTecrepMonotirAPI() + "/eqm/private/auth/equipmentsDashboard";
		Response r = RestAssuredUtil.galaxionGet(url, token);
		return new APITransaction(url,r);
	}
	
	/*------------------------------------------------------------------
	 *  Resources: Numbers
	 ------------------------------------------------------------------*/
	
	/**
	 * Upload a batch of numbers in an .xlsx file
	 * 
	 * UI flow: Resources > Numbers > Import from file
	 * @param token
	 * @param filepath - local directory of the file to upload
	 * @return url and response
	 */
	public static APITransaction importNumbers(String token, String filepath) {
		String url=EnvironmentDirectory.getTecrepMonotirAPI() + "/rsc/private/auth/import/Number/start";
		Response r = RestAssuredUtil.galaxionPostFile(url, token, new File(filepath));
		return new APITransaction(url,r);
	}
	
	/**
	 * Get the list of inventory pools
	 * 
	 * UI flow: Resources > Numbers - triggered on page load
	 * @param token
	 * @return url and response
	 */
	public static APITransaction getInventoryPools(String token) {
		String url=EnvironmentDirectory.getTecrepMonotirAPI() + "/eqm/private/auth/inventorypools?page=0&size=9999999";
		Response r = RestAssuredUtil.galaxionGet(url, token);
		return new APITransaction(url,r);
	}
	
	/**
	 * Add a number
	 * 
	 * UI flow: Resources > Numbers > Add
	 * @param token
	 * @param number
	 * @param status
	 * @param vanityCategory
	 * @return
	 */
	public static APITransaction addNumber(String token, String number, String status, String vanityCategory) {
		AddNumberDTO dto = new AddNumberDTO(number, status,vanityCategory);
		String url=EnvironmentDirectory.getTecrepMonotirAPI() + "/rsc/api/v2/private/auth/numbers";
		Response r = RestAssuredUtil.galaxionPost(url, PojoToJsonConverter.getJSON(dto),token);
		return new APITransaction(url,r);
	}
	
	
	/**
	 * Get the list of existing numbers
	 * 
	 * UI flow: Resources > Numbers - triggered on page load
	 * @param token
	 * @return url and response
	 */
	public static APITransaction getNumbers(String token) {
		String url=EnvironmentDirectory.getTecrepMonotirAPI() + "/rsc/api/v2/private/auth/numbers/search?page=0&size=10&number=&status=&nature=&service=&order=";
		Response r = RestAssuredUtil.galaxionGet(url, token);
		return new APITransaction(url,r);
	}
	
	public static APITransaction getWarehouses(String token) {
		String url=EnvironmentDirectory.getTecrepMonotirAPI() + "/eqm/private/auth/warehouses?page=0&size=9999999";
		Response r = RestAssuredUtil.galaxionGet(url, token);
		return new APITransaction(url,r);
	}
	
	public static APITransaction getProviders(String token) {
		String url=EnvironmentDirectory.getTecrepMonotirAPI() + "/eqm/private/auth/providers?page=0&size=9999999";
		Response r = RestAssuredUtil.galaxionGet(url, token);
		return new APITransaction(url,r);
	}
	
	public static APITransaction getPLMNs(String token) {
		String url=EnvironmentDirectory.getTecrepMonotirAPI() + "/eqm/private/auth/plmns/search?page=0&size=9999999";
		Response r = RestAssuredUtil.galaxionGet(url, token);
		return new APITransaction(url,r);
	}
	
	public static APITransaction getSimCards(String token) {
		String url=EnvironmentDirectory.getTecrepMonotirAPI() + "/eqm/private/auth/simcards/search?page=0&size=10&sn=&status=&accessType=&imsi=&imsisn=&nature=&preactivated=&batchNumber=&externalNumber=&order=&service=&serviceId=";
		Response r = RestAssuredUtil.galaxionGet(url, token);
		return new APITransaction(url,r);
	}
	
	public static APITransaction getSimCardBatches(String token) {
		String url=EnvironmentDirectory.getTecrepMonotirAPI() + "/eqm/private/auth/batch?page=0&size=10&sort=batchNumber,desc";
		Response r = RestAssuredUtil.galaxionGet(url, token);
		return new APITransaction(url,r);
	}
	
	public static APITransaction getSimGenerationConfigurations(String token) {
		String url=EnvironmentDirectory.getTecrepMonotirAPI() + "/eqm/private/auth/simGenerationConfigurations?page=0&size=100";
		Response r = RestAssuredUtil.galaxionGet(url, token);
		return new APITransaction(url,r);
	}
	
	public static APITransaction getIntervalNumbers(String token) {
		String url=EnvironmentDirectory.getTecrepMonotirAPI() + "/rsc/api/v2/private/auth/intervalnumbers";
		Response r = RestAssuredUtil.galaxionGet(url, token);
		return new APITransaction(url,r);
	}
	
	public static APITransaction getNumberBlock(String token, int blockNumberId) {
		String url=EnvironmentDirectory.getTecrepMonotirAPI() + "/rsc/api/v2/private/auth/blocknumbers/"+ blockNumberId;
		Response r = RestAssuredUtil.galaxionGet(url, token);
		return new APITransaction(url,r);
	}
	
	public static APITransaction deleteNumberBlock(String token, int blockNumberId) {
		String url=EnvironmentDirectory.getTecrepMonotirAPI() + "/rsc/api/v2/private/auth/blocknumbers/"+ blockNumberId;
		Response r = RestAssuredUtil.galaxionDelete(url, token);
		return new APITransaction(url,r);
	}
	
	public static APITransaction generateNumberBlock(String token, String blockPrefix, String countryCode, String length, String localPrefix, String operator, boolean portability) {
		GenerateNumberBlockDTO dto = new GenerateNumberBlockDTO(blockPrefix,countryCode,length,localPrefix,operator,portability);
		String url=EnvironmentDirectory.getTecrepMonotirAPI() + "/rsc/api/v2/private/auth/blocknumbers";
		Response r = RestAssuredUtil.galaxionPost(url,PojoToJsonConverter.getJSON(dto),token);
		return new APITransaction(url,r);
	}
	
	public static APITransaction generateUnpairedSimCardBatch(String token, InventoryPool inventoryPoolCode,int quantity,SimCardConfiguration configurationName) {
		GenerateSimCardBatchDTO dto = new GenerateSimCardBatchDTO(inventoryPoolCode, false, quantity, configurationName);
		String url=EnvironmentDirectory.getTecrepMonotirAPI() + "/inventory/private/auth/inventory/export/simcards";
		Response r = RestAssuredUtil.galaxionPost(url,PojoToJsonConverter.getJSON(dto),token);
		return new APITransaction(url,r);
	}
	
	public static APITransaction generatePairedSimCardBatch(String token, InventoryPool inventoryPoolCode,int quantity,SimCardConfiguration configurationName,String firstNumber, String lastNumber) {
		GenerateSimCardBatchDTO dto = new GenerateSimCardBatchDTO(inventoryPoolCode, true, quantity, configurationName);
		dto.setPairingNumbers(new BigInteger(firstNumber), new BigInteger(lastNumber));
		String url=EnvironmentDirectory.getTecrepMonotirAPI() + "/inventory/private/auth/inventory/export/simcards";
		Response r = RestAssuredUtil.galaxionPost(url,PojoToJsonConverter.getJSON(dto),token);
		return new APITransaction(url,r);
	}
	
	public static APITransaction generateIntervalNumbers(String token, String firstNumber, String lastNumber, String activity, GetNumberBlockResponse blockNumber) {
		CreateIntervalNumbersDTO dto = new CreateIntervalNumbersDTO(firstNumber,lastNumber,activity, blockNumber);
		String url=EnvironmentDirectory.getTecrepMonotirAPI() + "/rsc/api/v2/private/auth/intervalnumbers";
		Response r = RestAssuredUtil.galaxionPost(url,PojoToJsonConverter.getJSON(dto),token);
		return new APITransaction(url,r);
	}
	
	public static APITransaction exportSimCardBatch(String token, String inpFilename) {
		String url=EnvironmentDirectory.getTecrepMonotirAPI() + "/inventory/private/auth/inventory/export/simcards/" + inpFilename;
		Response r = RestAssuredUtil.galaxionGet(url, token);
		return new APITransaction(url,r);
	}
	
	public static APITransaction importSimCardFile(String token, String filepath) {
		String url=EnvironmentDirectory.getTecrepMonotirAPI() + "/eqm/private/auth/import/SimCard/start?preactivated=false";
		Response r = RestAssuredUtil.galaxionPostFile(url, token, new File(filepath));
		return new APITransaction(url,r);
	}
	
	public static APITransaction proceedImport(String token, int batchId) {
		String url=EnvironmentDirectory.getTecrepMonotirAPI() + "/eqm/private/auth/simcards/import/batch/" + batchId;
		Response r = RestAssuredUtil.galaxionPatch(url, "{}", token);
		return new APITransaction(url,r);
	}
	
	public static APITransaction uploadSimCardBatch(String token, int batchId, String filepath) {
		String url=EnvironmentDirectory.getTecrepMonotirAPI() + "/eqm/private/auth/batch/" + batchId + "/upload-import";
		Response r = RestAssuredUtil.galaxionPostFile(url, token, new File(filepath));
		return new APITransaction(url,r);
	}
	
}
