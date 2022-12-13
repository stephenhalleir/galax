package pojo_repo.eir_catalog_core_backend;

import java.sql.Date;
import java.sql.ResultSet;
import java.sql.SQLException;

public class IncompatibilityGroupItemGroups {

	private int incompatibilityGroupId;

	public IncompatibilityGroupItemGroups() {

	}

	public IncompatibilityGroupItemGroups(ResultSet rs) {
		try {
			incompatibilityGroupId = rs.getInt("incompatibility_group_id");
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	public int getIncompatibilityGroupId() {
		return incompatibilityGroupId;
	}

	public void setIncompatibilityGroupId(int incompatibilityGroupId) {
		this.incompatibilityGroupId = incompatibilityGroupId;
	}

}