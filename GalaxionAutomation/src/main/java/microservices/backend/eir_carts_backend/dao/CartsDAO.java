package microservices.backend.eir_carts_backend.dao;

import java.sql.ResultSet;
import java.sql.SQLException;

import microservices.backend.eir_carts_backend.data_model.Cart;
import utilities.generic.database.GalaxionDBUtil;
import utilities.test.config_readers.ExcelSQLManager;

public class CartsDAO {
	public static Cart getCart(String cartUuid) {

		// build the query
		String query = ExcelSQLManager.getSQLQuery("CART", "GET_CART");
		query = query.replace("$cartUuid", cartUuid);

		try {
			// retrieve the result from the database
			ResultSet rs = GalaxionDBUtil.getQueryResultSet(query);

			if (rs.next()) {
				return new Cart(rs);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}

		return null;
	}
}
