package pojo_repo.eir_cdr_repository_backend;

import java.sql.Date;
import java.sql.ResultSet;
import java.sql.SQLException;

public class RefAdjustmentDetail {

	private int adjustmentCode;

	public RefAdjustmentDetail() {

	}

	public RefAdjustmentDetail(ResultSet rs) {
		try {
			adjustmentCode = rs.getInt("adjustment_code");
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	public int getAdjustmentCode() {
		return adjustmentCode;
	}

	public void setAdjustmentCode(int adjustmentCode) {
		this.adjustmentCode = adjustmentCode;
	}

}