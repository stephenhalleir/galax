package pojo_repo.eir_subscription_management_backend;

import java.sql.Date;
import java.sql.ResultSet;
import java.sql.SQLException;

public class MigAccountMapping {

	private int ionAccountId;
	private int ionBaid;
	private String sprOldSubId;
	private String msisdn;

	public MigAccountMapping() {

	}

	public MigAccountMapping(ResultSet rs) {
		try {
			ionAccountId = rs.getInt("ion_account_id");
			ionBaid = rs.getInt("ion_baid");
			sprOldSubId = rs.getString("spr_old_sub_id");
			msisdn = rs.getString("msisdn");
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	public int getIonAccountId() {
		return ionAccountId;
	}

	public void setIonAccountId(int ionAccountId) {
		this.ionAccountId = ionAccountId;
	}

	public int getIonBaid() {
		return ionBaid;
	}

	public void setIonBaid(int ionBaid) {
		this.ionBaid = ionBaid;
	}

	public String getSprOldSubId() {
		return sprOldSubId;
	}

	public void setSprOldSubId(String sprOldSubId) {
		this.sprOldSubId = sprOldSubId;
	}

	public String getMsisdn() {
		return msisdn;
	}

	public void setMsisdn(String msisdn) {
		this.msisdn = msisdn;
	}

}