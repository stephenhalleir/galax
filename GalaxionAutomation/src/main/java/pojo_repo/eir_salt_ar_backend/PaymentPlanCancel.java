package pojo_repo.eir_salt_ar_backend;

import java.sql.Date;
import java.sql.ResultSet;
import java.sql.SQLException;

public class PaymentPlanCancel {

	private int id;
	private String csrUserName;
	private Date createTs;

	public PaymentPlanCancel() {

	}

	public PaymentPlanCancel(ResultSet rs) {
		try {
			id = rs.getInt("id");
			csrUserName = rs.getString("csr_user_name");
			createTs = rs.getDate("create_ts");
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

	public String getCsrUserName() {
		return csrUserName;
	}

	public void setCsrUserName(String csrUserName) {
		this.csrUserName = csrUserName;
	}

	public Date getCreateTs() {
		return createTs;
	}

	public void setCreateTs(Date createTs) {
		this.createTs = createTs;
	}

}