package pojo_repo.eir_catalog_core_backend;

import java.sql.Date;
import java.sql.ResultSet;
import java.sql.SQLException;

public class TariffPlanSubsidy {

	private String tariffPlanCode;

	public TariffPlanSubsidy() {

	}

	public TariffPlanSubsidy(ResultSet rs) {
		try {
			tariffPlanCode = rs.getString("tariff_plan_code");
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	public String getTariffPlanCode() {
		return tariffPlanCode;
	}

	public void setTariffPlanCode(String tariffPlanCode) {
		this.tariffPlanCode = tariffPlanCode;
	}

}