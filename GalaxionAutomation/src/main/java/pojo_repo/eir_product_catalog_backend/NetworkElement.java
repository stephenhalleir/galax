package pojo_repo.eir_product_catalog_backend;

import java.sql.Date;
import java.sql.ResultSet;
import java.sql.SQLException;

public class NetworkElement {

	private int id;
	private String networkType;
	private String networkName;

	public NetworkElement() {

	}

	public NetworkElement(ResultSet rs) {
		try {
			id = rs.getInt("id");
			networkType = rs.getString("network_type");
			networkName = rs.getString("network_name");
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getNetworkType() {
		return networkType;
	}

	public void setNetworkType(String networkType) {
		this.networkType = networkType;
	}

	public String getNetworkName() {
		return networkName;
	}

	public void setNetworkName(String networkName) {
		this.networkName = networkName;
	}

}