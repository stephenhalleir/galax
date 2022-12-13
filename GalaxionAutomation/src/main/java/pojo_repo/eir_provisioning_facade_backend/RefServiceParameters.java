package pojo_repo.eir_provisioning_facade_backend;

import java.sql.Date;
import java.sql.ResultSet;
import java.sql.SQLException;

public class RefServiceParameters {

	private int id;
	private int pcatNetworkElementId;
	private String serviceKey;

	public RefServiceParameters() {

	}

	public RefServiceParameters(ResultSet rs) {
		try {
			id = rs.getInt("id");
			pcatNetworkElementId = rs.getInt("pcat_network_element_id");
			serviceKey = rs.getString("service_key");
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

	public int getPcatNetworkElementId() {
		return pcatNetworkElementId;
	}

	public void setPcatNetworkElementId(int pcatNetworkElementId) {
		this.pcatNetworkElementId = pcatNetworkElementId;
	}

	public String getServiceKey() {
		return serviceKey;
	}

	public void setServiceKey(String serviceKey) {
		this.serviceKey = serviceKey;
	}

}