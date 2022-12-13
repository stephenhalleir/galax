package pojo_repo.eir_adjustment_backend;

import java.sql.Date;
import java.sql.ResultSet;
import java.sql.SQLException;

public class LimitationConfig {

	private String roleName;
	private int maxAmount;
	private int numberAdjustment;
	private String adjustmentType;

	public LimitationConfig() {

	}

	public LimitationConfig(ResultSet rs) {
		try {
			roleName = rs.getString("role_name");
			maxAmount = rs.getInt("max_amount");
			numberAdjustment = rs.getInt("number_adjustment");
			adjustmentType = rs.getString("adjustment_type");
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	public String getRoleName() {
		return roleName;
	}

	public void setRoleName(String roleName) {
		this.roleName = roleName;
	}

	public int getMaxAmount() {
		return maxAmount;
	}

	public void setMaxAmount(int maxAmount) {
		this.maxAmount = maxAmount;
	}

	public int getNumberAdjustment() {
		return numberAdjustment;
	}

	public void setNumberAdjustment(int numberAdjustment) {
		this.numberAdjustment = numberAdjustment;
	}

	public String getAdjustmentType() {
		return adjustmentType;
	}

	public void setAdjustmentType(String adjustmentType) {
		this.adjustmentType = adjustmentType;
	}

}