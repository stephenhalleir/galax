package pojo_repo.eir_subscription_management_backend;

import java.sql.Date;
import java.sql.ResultSet;
import java.sql.SQLException;

public class MigTariffMap {

	private String cmpService;

	public MigTariffMap() {

	}

	public MigTariffMap(ResultSet rs) {
		try {
			cmpService = rs.getString("cmp_service");
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	public String getCmpService() {
		return cmpService;
	}

	public void setCmpService(String cmpService) {
		this.cmpService = cmpService;
	}

}