package pojo_repo.eir_order_management_backend;

import java.sql.Date;
import java.sql.ResultSet;
import java.sql.SQLException;

public class Port {

	private int id;
	private String phoneNumber;
	private int otherNetworkId;
	private String otherNetworkAccountNumber;
	private Date portStartDateTime;

	public Port() {

	}

	public Port(ResultSet rs) {
		try {
			id = rs.getInt("id");
			phoneNumber = rs.getString("phone_number");
			otherNetworkId = rs.getInt("other_network_id");
			otherNetworkAccountNumber = rs.getString("other_network_account_number");
			portStartDateTime = rs.getDate("port_start_date_time");
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

	public String getPhoneNumber() {
		return phoneNumber;
	}

	public void setPhoneNumber(String phoneNumber) {
		this.phoneNumber = phoneNumber;
	}

	public int getOtherNetworkId() {
		return otherNetworkId;
	}

	public void setOtherNetworkId(int otherNetworkId) {
		this.otherNetworkId = otherNetworkId;
	}

	public String getOtherNetworkAccountNumber() {
		return otherNetworkAccountNumber;
	}

	public void setOtherNetworkAccountNumber(String otherNetworkAccountNumber) {
		this.otherNetworkAccountNumber = otherNetworkAccountNumber;
	}

	public Date getPortStartDateTime() {
		return portStartDateTime;
	}

	public void setPortStartDateTime(Date portStartDateTime) {
		this.portStartDateTime = portStartDateTime;
	}

}