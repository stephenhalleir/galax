package microservices.backend.tecrep.utility;

import microservices.backend.tecrep.resource_management.dto.BlockNumberDtoV2;
import microservices.backend.tecrep.resource_management.utility.TecrepInventoryManagementAPI;

public class MsisdnGenerator {

	/**
	 * Generate a block number and interval
	 * 
	 * @param startNumber
	 * @param endNumber
	 */
	public static void generateMsisdns(String startNumber, String endNumber) {
		
		int blockPrefix=Integer.parseInt(startNumber.substring(0, 5));
		int countryCode=Integer.parseInt(startNumber.substring(0, 3));
		
		BlockNumberDtoV2 blockDto = TecrepInventoryManagementAPI.createNewBlockNumber(blockPrefix,countryCode,0,"SYSTEM");
		
		// create the interval
		TecrepInventoryManagementAPI.createInterval(blockDto, startNumber, endNumber);
	}
	
}
