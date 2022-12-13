package pojo_repo.eir_subscription_management_backend;

import java.sql.Date;
import java.sql.ResultSet;
import java.sql.SQLException;

public class ServiceAttributeHistoryOld {

	private int rev;
	private int revtype;
	private int attributeId;

	public ServiceAttributeHistoryOld() {

	}

	public ServiceAttributeHistoryOld(ResultSet rs) {
		try {
			rev = rs.getInt("rev");
			revtype = rs.getInt("revtype");
			attributeId = rs.getInt("attribute_id");
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	public int getRev() {
		return rev;
	}

	public void setRev(int rev) {
		this.rev = rev;
	}

	public int getRevtype() {
		return revtype;
	}

	public void setRevtype(int revtype) {
		this.revtype = revtype;
	}

	public int getAttributeId() {
		return attributeId;
	}

	public void setAttributeId(int attributeId) {
		this.attributeId = attributeId;
	}

}