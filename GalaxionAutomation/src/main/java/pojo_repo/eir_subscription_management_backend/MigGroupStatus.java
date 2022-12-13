package pojo_repo.eir_subscription_management_backend;

import java.sql.Date;
import java.sql.ResultSet;
import java.sql.SQLException;

public class MigGroupStatus {

	private String groupName;

	public MigGroupStatus() {

	}

	public MigGroupStatus(ResultSet rs) {
		try {
			groupName = rs.getString("group_name");
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	public String getGroupName() {
		return groupName;
	}

	public void setGroupName(String groupName) {
		this.groupName = groupName;
	}

}