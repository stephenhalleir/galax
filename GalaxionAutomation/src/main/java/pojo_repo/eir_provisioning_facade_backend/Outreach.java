package pojo_repo.eir_provisioning_facade_backend;

import java.sql.Date;
import java.sql.ResultSet;
import java.sql.SQLException;

public class Outreach {

	private int id;
	private int networkId;
	private String imsi;
	private int refOutreachId;
	private String action;
	private String status;

	public Outreach() {

	}

	public Outreach(ResultSet rs) {
		try {
			id = rs.getInt("id");
			networkId = rs.getInt("network_id");
			imsi = rs.getString("imsi");
			refOutreachId = rs.getInt("ref_outreach_id");
			action = rs.getString("action");
			status = rs.getString("status");
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

	public int getNetworkId() {
		return networkId;
	}

	public void setNetworkId(int networkId) {
		this.networkId = networkId;
	}

	public String getImsi() {
		return imsi;
	}

	public void setImsi(String imsi) {
		this.imsi = imsi;
	}

	public int getRefOutreachId() {
		return refOutreachId;
	}

	public void setRefOutreachId(int refOutreachId) {
		this.refOutreachId = refOutreachId;
	}

	public String getAction() {
		return action;
	}

	public void setAction(String action) {
		this.action = action;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

}