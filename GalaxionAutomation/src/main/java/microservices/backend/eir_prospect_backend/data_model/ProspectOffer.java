package microservices.backend.eir_prospect_backend.data_model;

import java.sql.ResultSet;
import java.sql.SQLException;

public class ProspectOffer {

	private int id;
	private int catalogOfferId;
	private int prospectId;
	private int topUpAmount;
	private String nddPreference;

	public ProspectOffer() {

	}

	public ProspectOffer(ResultSet rs) {
		try {
			id = rs.getInt("id");
			catalogOfferId = rs.getInt("catalog_offer_id");
			prospectId = rs.getInt("prospect_id");
			topUpAmount = rs.getInt("top_up_amount");
			nddPreference=rs.getString("ndd_preference");
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	public String getNddPreference() {
		return nddPreference;
	}

	public void setNddPreference(String nddPreference) {
		this.nddPreference = nddPreference;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getCatalogOfferId() {
		return catalogOfferId;
	}

	public void setCatalogOfferId(int catalogOfferId) {
		this.catalogOfferId = catalogOfferId;
	}

	public int getProspectId() {
		return prospectId;
	}

	public void setProspectId(int prospectId) {
		this.prospectId = prospectId;
	}

	public int getTopUpAmount() {
		return topUpAmount;
	}

	public void setTopUpAmount(int topUpAmount) {
		this.topUpAmount = topUpAmount;
	}

}