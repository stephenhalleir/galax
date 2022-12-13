package pojo_repo.eir_subscription_management_backend;

import java.sql.Date;
import java.sql.ResultSet;
import java.sql.SQLException;

public class MigCorpStatus {

	private int corpAcc;

	public MigCorpStatus() {

	}

	public MigCorpStatus(ResultSet rs) {
		try {
			corpAcc = rs.getInt("corp_acc");
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	public int getCorpAcc() {
		return corpAcc;
	}

	public void setCorpAcc(int corpAcc) {
		this.corpAcc = corpAcc;
	}

}