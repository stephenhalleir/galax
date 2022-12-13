package pojo_repo.eir_salt_ar_backend;

import java.sql.Date;
import java.sql.ResultSet;
import java.sql.SQLException;

public class CreditLost {

	private String reasonCode;
	private int id;
	private Date createTs;

	public CreditLost() {

	}

	public CreditLost(ResultSet rs) {
		try {
			reasonCode = rs.getString("reason_code");
			id = rs.getInt("id");
			createTs = rs.getDate("create_ts");
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	public String getReasonCode() {
		return reasonCode;
	}

	public void setReasonCode(String reasonCode) {
		this.reasonCode = reasonCode;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public Date getCreateTs() {
		return createTs;
	}

	public void setCreateTs(Date createTs) {
		this.createTs = createTs;
	}

}