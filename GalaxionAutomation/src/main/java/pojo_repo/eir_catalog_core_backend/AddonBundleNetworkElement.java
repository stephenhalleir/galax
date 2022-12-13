package pojo_repo.eir_catalog_core_backend;

import java.sql.Date;
import java.sql.ResultSet;
import java.sql.SQLException;

public class AddonBundleNetworkElement {

	private String networkElementCode;

	public AddonBundleNetworkElement() {

	}

	public AddonBundleNetworkElement(ResultSet rs) {
		try {
			networkElementCode = rs.getString("network_element_code");
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	public String getNetworkElementCode() {
		return networkElementCode;
	}

	public void setNetworkElementCode(String networkElementCode) {
		this.networkElementCode = networkElementCode;
	}

}