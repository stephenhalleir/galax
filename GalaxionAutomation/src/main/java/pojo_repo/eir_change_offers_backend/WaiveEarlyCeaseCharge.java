package pojo_repo.eir_change_offers_backend;

import java.sql.Date;
import java.sql.ResultSet;
import java.sql.SQLException;

public class WaiveEarlyCeaseCharge {

	private String changeOfferUuid;
	private String approver;

	public WaiveEarlyCeaseCharge() {

	}

	public WaiveEarlyCeaseCharge(ResultSet rs) {
		try {
			changeOfferUuid = rs.getString("change_offer_uuid");
			approver = rs.getString("approver");
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	public String getChangeOfferUuid() {
		return changeOfferUuid;
	}

	public void setChangeOfferUuid(String changeOfferUuid) {
		this.changeOfferUuid = changeOfferUuid;
	}

	public String getApprover() {
		return approver;
	}

	public void setApprover(String approver) {
		this.approver = approver;
	}

}