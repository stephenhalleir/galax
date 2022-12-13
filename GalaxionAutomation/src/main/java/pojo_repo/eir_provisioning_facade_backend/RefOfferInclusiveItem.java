package pojo_repo.eir_provisioning_facade_backend;

import java.sql.Date;
import java.sql.ResultSet;
import java.sql.SQLException;

public class RefOfferInclusiveItem {

	private int id;
	private int pcatOfferId;
	private int pcatItemId;

	public RefOfferInclusiveItem() {

	}

	public RefOfferInclusiveItem(ResultSet rs) {
		try {
			id = rs.getInt("id");
			pcatOfferId = rs.getInt("pcat_offer_id");
			pcatItemId = rs.getInt("pcat_item_id");
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

	public int getPcatOfferId() {
		return pcatOfferId;
	}

	public void setPcatOfferId(int pcatOfferId) {
		this.pcatOfferId = pcatOfferId;
	}

	public int getPcatItemId() {
		return pcatItemId;
	}

	public void setPcatItemId(int pcatItemId) {
		this.pcatItemId = pcatItemId;
	}

}