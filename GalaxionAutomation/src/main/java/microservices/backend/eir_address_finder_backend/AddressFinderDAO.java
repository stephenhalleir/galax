package microservices.backend.eir_address_finder_backend;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import microservices.backend.eir_address_finder_backend.data_model.AddressFinderAddress;
import utilities.generic.database.GalaxionDBUtil;
import utilities.test.config_readers.ExcelSQLManager;

public class AddressFinderDAO {
	
	/**
	 * Get addresses from the address finder database based on an eircode
	 * @param eircode
	 * @return list of Address objects
	 */
	public static ArrayList<AddressFinderAddress> getAddresses(String eircode) {
		
		ArrayList<AddressFinderAddress> addresses = new ArrayList<AddressFinderAddress>();
		
		String query = ExcelSQLManager.getSQLQuery("ADDRESS_FINDER", "GET_ADDRESS");
		query = query.replace("$eircode", eircode);

		ResultSet rs;
		try {
			rs = GalaxionDBUtil.getQueryResultSet(query);
			while(rs.next()) {
				addresses.add(new AddressFinderAddress(rs));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return addresses;
	}
	
	/**
	 * Retrieve a random address from the database
	 * @return the Address object
	 */
	public static AddressFinderAddress getRandomAddress() {
		String query = ExcelSQLManager.getSQLQuery("ADDRESS_FINDER", "GET_RANDOM_ADDRESS");

		ResultSet rs;
		try {
			rs = GalaxionDBUtil.getQueryResultSet(query);
			while(rs.next()) {
				return new AddressFinderAddress(rs);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return null;
	}
}
