package pojo_repo.eir_product_catalog_backend;

import java.sql.Date;
import java.sql.ResultSet;
import java.sql.SQLException;

public class ItemGroupChannel {

	private int groupId;

	public ItemGroupChannel() {

	}

	public ItemGroupChannel(ResultSet rs) {
		try {
			groupId = rs.getInt("group_id");
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	public int getGroupId() {
		return groupId;
	}

	public void setGroupId(int groupId) {
		this.groupId = groupId;
	}

}