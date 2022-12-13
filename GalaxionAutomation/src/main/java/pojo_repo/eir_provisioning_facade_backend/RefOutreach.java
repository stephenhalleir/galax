package pojo_repo.eir_provisioning_facade_backend;

import java.sql.Date;
import java.sql.ResultSet;
import java.sql.SQLException;

public class RefOutreach {

	private int id;
	private int offerId;
	private String groupName;

	public RefOutreach() {

	}

	public RefOutreach(ResultSet rs) {
		try {
			id = rs.getInt("id");
			offerId = rs.getInt("offer_id");
			groupName = rs.getString("group_name");
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

	public int getOfferId() {
		return offerId;
	}

	public void setOfferId(int offerId) {
		this.offerId = offerId;
	}

	public String getGroupName() {
		return groupName;
	}

	public void setGroupName(String groupName) {
		this.groupName = groupName;
	}

}