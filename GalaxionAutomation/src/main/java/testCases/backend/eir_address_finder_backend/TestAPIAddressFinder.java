package testCases.backend.eir_address_finder_backend;

import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertTrue;

import java.util.List;

import org.testng.ITestContext;
import org.testng.annotations.Test;

import framework.basetest.BaseTest;
import microservices.backend.eir_address_finder_backend.AddressFinderDAO;
import microservices.backend.eir_address_finder_backend.AddressFinderUtil;
import microservices.backend.eir_address_finder_backend.data_model.AddressFinderAddress;

public class TestAPIAddressFinder extends BaseTest {

	@Test(description="API > Address Finder > Get Address")
	public void testAddressFinder(ITestContext iTestContext) {
		
		// specify the eircode to test
		String eircode="C15EF6E";
		
		// get the list of addresses from the API
		List<AddressFinderAddress> addressesFromAPI = AddressFinderUtil.getAddresses(eircode);
		
		// get the list of expected addresses from the database
		List<AddressFinderAddress> addressesFromDB = AddressFinderDAO.getAddresses(eircode);
		
		// verify that both lists are the same length
		assertEquals(addressesFromAPI.size(), addressesFromDB.size());
		
		// for each address in the list, check that it matches the expected address
		for(int i=0;i<addressesFromAPI.size();i++) {
			assertTrue(addressesFromAPI.get(i).equals(addressesFromDB.get(i)));
			logPass("Eircode: " + eircode + " - address " + addressesFromAPI.get(i).getAddressLine1() + " is a match.");
		}
	}
}