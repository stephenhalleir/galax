package pojo_repo.eir_salt_ar_backend;

import java.sql.Date;
import java.sql.ResultSet;
import java.sql.SQLException;

public class BillingAccountId {

	private int id;

	public BillingAccountId() {

	}

	public BillingAccountId(ResultSet rs) {
		try {
			id = rs.getInt("id");
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

}