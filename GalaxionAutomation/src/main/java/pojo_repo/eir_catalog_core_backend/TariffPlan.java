package pojo_repo.eir_catalog_core_backend;

import java.sql.Date;
import java.sql.ResultSet;
import java.sql.SQLException;

public class TariffPlan {

	private String code;
	private String description;
	private String offerCode;
	private int simOnly;
	private String commitmentDuration;
	private String channelCode;
	private String discountCode;

	public TariffPlan() {

	}

	public TariffPlan(ResultSet rs) {
		try {
			code = rs.getString("code");
			description = rs.getString("description");
			offerCode = rs.getString("offer_code");
			simOnly = rs.getInt("sim_only");
			commitmentDuration = rs.getString("commitment_duration");
			channelCode = rs.getString("channel_code");
			discountCode = rs.getString("discount_code");
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getOfferCode() {
		return offerCode;
	}

	public void setOfferCode(String offerCode) {
		this.offerCode = offerCode;
	}

	public int getSimOnly() {
		return simOnly;
	}

	public void setSimOnly(int simOnly) {
		this.simOnly = simOnly;
	}

	public String getCommitmentDuration() {
		return commitmentDuration;
	}

	public void setCommitmentDuration(String commitmentDuration) {
		this.commitmentDuration = commitmentDuration;
	}

	public String getChannelCode() {
		return channelCode;
	}

	public void setChannelCode(String channelCode) {
		this.channelCode = channelCode;
	}

	public String getDiscountCode() {
		return discountCode;
	}

	public void setDiscountCode(String discountCode) {
		this.discountCode = discountCode;
	}

}