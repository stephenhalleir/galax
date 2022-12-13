package microservices.backend.keycloak.dao;

import java.sql.ResultSet;
import java.sql.SQLException;

import microservices.backend.keycloak.data_model.Client;
import microservices.backend.keycloak.enums.Realm;
import utilities.generic.database.GalaxionDBUtil;
import utilities.test.config_readers.ExcelSQLManager;

public class KeycloakDAO {
	
	/**
	 * Get CLIENT information based on Realm and Client ID
	 * @param client_id
	 * @param realm
	 * @return keycloak.client object
	 */
	public static Client getClient(String client_id, Realm realm) {
		String query = ExcelSQLManager.getSQLQuery("KEYCLOAK", "GET_CLIENT");
		query = query.replace("$client_id", client_id).replace("$realm_id", realm.toString());
		ResultSet rs;
		try {
			rs = GalaxionDBUtil.getQueryResultSet(query);
			if(rs.next()) {
				return new Client(rs);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null;
	}
}
