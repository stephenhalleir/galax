package pojo_repo.eir_cdr_repository_backend;

import java.sql.Date;
import java.sql.ResultSet;
import java.sql.SQLException;

public class RefCarrierDetail {

	private String carrierCode;
	private String countryCode;
	private String network;
	private String pmnCode;

	public RefCarrierDetail() {

	}

	public RefCarrierDetail(ResultSet rs) {
		try {
			carrierCode = rs.getString("carrier_code");
			countryCode = rs.getString("country_code");
			network = rs.getString("network");
			pmnCode = rs.getString("pmn_code");
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	public String getCarrierCode() {
		return carrierCode;
	}

	public void setCarrierCode(String carrierCode) {
		this.carrierCode = carrierCode;
	}

	public String getCountryCode() {
		return countryCode;
	}

	public void setCountryCode(String countryCode) {
		this.countryCode = countryCode;
	}

	public String getNetwork() {
		return network;
	}

	public void setNetwork(String network) {
		this.network = network;
	}

	public String getPmnCode() {
		return pmnCode;
	}

	public void setPmnCode(String pmnCode) {
		this.pmnCode = pmnCode;
	}

}