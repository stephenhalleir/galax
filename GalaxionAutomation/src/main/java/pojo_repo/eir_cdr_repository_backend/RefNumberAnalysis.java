package pojo_repo.eir_cdr_repository_backend;

import java.sql.Date;
import java.sql.ResultSet;
import java.sql.SQLException;

public class RefNumberAnalysis {

	private String numberPrefix;
	private String countryCode;
	private String description;
	private String nationalZone;

	public RefNumberAnalysis() {

	}

	public RefNumberAnalysis(ResultSet rs) {
		try {
			numberPrefix = rs.getString("number_prefix");
			countryCode = rs.getString("country_code");
			description = rs.getString("description");
			nationalZone = rs.getString("national_zone");
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	public String getNumberPrefix() {
		return numberPrefix;
	}

	public void setNumberPrefix(String numberPrefix) {
		this.numberPrefix = numberPrefix;
	}

	public String getCountryCode() {
		return countryCode;
	}

	public void setCountryCode(String countryCode) {
		this.countryCode = countryCode;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getNationalZone() {
		return nationalZone;
	}

	public void setNationalZone(String nationalZone) {
		this.nationalZone = nationalZone;
	}

}