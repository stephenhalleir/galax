package pojo_repo.eir_subscription_management_backend;

import java.sql.Date;
import java.sql.ResultSet;
import java.sql.SQLException;

public class MigOfferMap {

	private String cmpTariff;

	public MigOfferMap() {

	}

	public MigOfferMap(ResultSet rs) {
		try {
			cmpTariff = rs.getString("cmp_tariff");
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	public String getCmpTariff() {
		return cmpTariff;
	}

	public void setCmpTariff(String cmpTariff) {
		this.cmpTariff = cmpTariff;
	}

}