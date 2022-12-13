package microservices.backend.tecrep.resource_management.utility;

import java.math.BigInteger;
import java.util.List;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import io.github.sharelison.jsontojava.converter.JsonConverter;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import microservices.backend.eir_address_finder_backend.data_model.AddressFinderAddress;
import microservices.backend.tecrep.resource_management.dto.BlockNumberDtoV2;
import microservices.backend.tecrep.resource_management.dto.ChangeStateDTO;
import microservices.backend.tecrep.resource_management.dto.IntervalNumberDto;
import microservices.backend.tecrep.resource_management.enums.Action;
import utilities.api.RestAssuredUtil;
import utilities.generic.api.APITransaction;
import utilities.generic.pojo_generation.pojo_to_json.PojoToJsonConverter;

public class TecrepInventoryManagementAPI {

	private static String inventoryManagementURL = "https://eir-tecrep-resource-management-backend-webservice.e2e.ion.comhar.local";

	public static APITransaction setAction(String number, Action action) {
		String url = inventoryManagementURL + "/api/v2/private/auth/numbers/" + number + "/" + action.toString();
		ChangeStateDTO dto = new ChangeStateDTO();
		Response r = RestAssuredUtil.galaxionPatch(url, PojoToJsonConverter.getJSON(dto),null);
		return new APITransaction(url,r);
	}
	
	public static BlockNumberDtoV2 createNewBlockNumber(int blockPrefix, int countryCode, int localPrefix, String operator) {
		String url = inventoryManagementURL + "/api/v2/private/auth/blocknumbers";
		BlockNumberDtoV2 dto = new BlockNumberDtoV2();
		dto.setBlockId(1);
		dto.setBlockPrefix(blockPrefix);
		dto.setCountryCode(countryCode);
		dto.setLength(12);
		dto.setLocalPrefix(localPrefix);
		dto.setOperator(operator);
		dto.setPortability(true);
		Response r = RestAssuredUtil.galaxionPost(url, PojoToJsonConverter.getJSON(dto),null);
		ObjectMapper mapper = new ObjectMapper();

		try {
			dto = mapper.readValue(r.asString(),BlockNumberDtoV2.class);
		} catch (JsonMappingException e) {
			e.printStackTrace();
		} catch (JsonProcessingException e) {
			e.printStackTrace();
		}
		return dto;
	}
	
	public static BlockNumberDtoV2 getBlock(int blockId) {
		String url = inventoryManagementURL + "/api/v2/private/auth/blocknumbers/" + blockId;
		Response r = RestAssuredUtil.galaxionGet(url, null);

		ObjectMapper mapper = new ObjectMapper();
		BlockNumberDtoV2 dto = null;
		try {
			dto = mapper.readValue(r.asString(),BlockNumberDtoV2.class);
		} catch (JsonMappingException e) {
			e.printStackTrace();
		} catch (JsonProcessingException e) {
			e.printStackTrace();
		}

		return dto;
	}
	
	public static void createInterval(BlockNumberDtoV2 blockDto,String firstNumber, String lastNumber) {
		
		String url= inventoryManagementURL + "/api/v2/private/auth/intervalnumbers";
		IntervalNumberDto dto = new IntervalNumberDto();
		dto.setBlockNumber(blockDto);
		dto.setActivity("MOBILE");
		dto.setFirstNumber(new BigInteger(firstNumber));
		dto.setLastNumber(new BigInteger(lastNumber));
		dto.setIntervalId(1);
		System.err.println(PojoToJsonConverter.getJSON(dto));
		
		Response r = RestAssuredUtil.galaxionPost(url, PojoToJsonConverter.getJSON(dto), null);
		System.out.println(r.getStatusCode() + ", " + r.asString());
	}
	
	public static void deleteBlockNumber(int blockId) {
		String url = inventoryManagementURL + "/api/v2/private/auth/blocknumbers/" + blockId;
		RestAssuredUtil.galaxionDelete(url, null);
	}
	
	public static void main(String [] args) {
		//APITransaction t = TecrepInventoryManagementAPI.setAction("353850000000", Action.delete);
		//System.out.println(t);
	}
}
