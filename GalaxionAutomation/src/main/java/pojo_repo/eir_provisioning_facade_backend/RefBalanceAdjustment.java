package pojo_repo.eir_provisioning_facade_backend;

import java.sql.Date;
import java.sql.ResultSet;
import java.sql.SQLException;

public class RefBalanceAdjustment {

	private int id;
	private int adjustmentCode;
	private String adjustmentName;

	public RefBalanceAdjustment() {

	}

	public RefBalanceAdjustment(ResultSet rs) {
		try {
			id = rs.getInt("id");
			adjustmentCode = rs.getInt("adjustment_code");
			adjustmentName = rs.getString("adjustment_name");
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

	public int getAdjustmentCode() {
		return adjustmentCode;
	}

	public void setAdjustmentCode(int adjustmentCode) {
		this.adjustmentCode = adjustmentCode;
	}

	public String getAdjustmentName() {
		return adjustmentName;
	}

	public void setAdjustmentName(String adjustmentName) {
		this.adjustmentName = adjustmentName;
	}

}