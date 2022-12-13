package pojo_repo.eir_catalog_core_backend;

import java.sql.Date;
import java.sql.ResultSet;
import java.sql.SQLException;

public class Handset {

	private String capacity;
	private String networkCompatibility;
	private String simCardSize;

	public Handset() {

	}

	public Handset(ResultSet rs) {
		try {
			capacity = rs.getString("capacity");
			networkCompatibility = rs.getString("network_compatibility");
			simCardSize = rs.getString("sim_card_size");
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	public String getCapacity() {
		return capacity;
	}

	public void setCapacity(String capacity) {
		this.capacity = capacity;
	}

	public String getNetworkCompatibility() {
		return networkCompatibility;
	}

	public void setNetworkCompatibility(String networkCompatibility) {
		this.networkCompatibility = networkCompatibility;
	}

	public String getSimCardSize() {
		return simCardSize;
	}

	public void setSimCardSize(String simCardSize) {
		this.simCardSize = simCardSize;
	}

}