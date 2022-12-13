package pojo_repo.eir_salt_ar_backend;

import java.sql.Date;
import java.sql.ResultSet;
import java.sql.SQLException;

public class AccountCreation {

	private int id;
	private Date createTs;

	public AccountCreation() {

	}

	public AccountCreation(ResultSet rs) {
		try {
			id = rs.getInt("id");
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

	public Date getCreateTs() {
		return createTs;
	}

	public void setCreateTs(Date createTs) {
		this.createTs = createTs;
	}

}