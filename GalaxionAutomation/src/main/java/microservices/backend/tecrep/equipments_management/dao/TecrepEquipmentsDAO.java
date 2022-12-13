package microservices.backend.tecrep.equipments_management.dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import microservices.backend.tecrep.equipments_management.DBInventoryPool;
import utilities.generic.database.GalaxionDBUtil;
import utilities.generic.database.MariaDBConnection;
import utilities.test.config_readers.ExcelSQLManager;

public class TecrepEquipmentsDAO {

	public static ArrayList<DBInventoryPool> getInventoryPools(){

		ArrayList<DBInventoryPool> pools = new ArrayList<DBInventoryPool>();
		
		String query = ExcelSQLManager.getSQLQuery("TECREP", "GET_INVENTORY_POOLS");


		ResultSet rs;
		try {
			rs = GalaxionDBUtil.getQueryResultSet(query);
			while(rs.next()) {
				pools.add(new DBInventoryPool(rs));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return pools;
	}
}
