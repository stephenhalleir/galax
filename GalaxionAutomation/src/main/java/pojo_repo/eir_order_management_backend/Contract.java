package pojo_repo.eir_order_management_backend;

import java.sql.Date;
import java.sql.ResultSet;
import java.sql.SQLException;

public class Contract {

	private int id;
	private int isRecontract;
	private String approvedBy;
	private String changeReason;

	public Contract() {

	}

	public Contract(ResultSet rs) {
		try {
			id = rs.getInt("id");
			isRecontract = rs.getInt("is_recontract");
			approvedBy = rs.getString("approved_by");
			changeReason = rs.getString("change_reason");
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

	public int getIsRecontract() {
		return isRecontract;
	}

	public void setIsRecontract(int isRecontract) {
		this.isRecontract = isRecontract;
	}

	public String getApprovedBy() {
		return approvedBy;
	}

	public void setApprovedBy(String approvedBy) {
		this.approvedBy = approvedBy;
	}

	public String getChangeReason() {
		return changeReason;
	}

	public void setChangeReason(String changeReason) {
		this.changeReason = changeReason;
	}

}