package pojo_repo.eir_cdr_repository_backend;

import java.sql.Date;
import java.sql.ResultSet;
import java.sql.SQLException;

public class RefRatingSubtotalType {

	private int id;
	private String key;
	private String ratingSubtotalUsageType;
	private String ratingSubtotalDisplayName;
	private String ratingSubtotalLevel;
	private Date lastModifiedDateTime;

	public RefRatingSubtotalType() {

	}

	public RefRatingSubtotalType(ResultSet rs) {
		try {
			id = rs.getInt("id");
			key = rs.getString("key");
			ratingSubtotalUsageType = rs.getString("rating_subtotal_usage_type");
			ratingSubtotalDisplayName = rs.getString("rating_subtotal_display_name");
			ratingSubtotalLevel = rs.getString("rating_subtotal_level");
			lastModifiedDateTime = rs.getDate("last_modified_date_time");
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

	public String getKey() {
		return key;
	}

	public void setKey(String key) {
		this.key = key;
	}

	public String getRatingSubtotalUsageType() {
		return ratingSubtotalUsageType;
	}

	public void setRatingSubtotalUsageType(String ratingSubtotalUsageType) {
		this.ratingSubtotalUsageType = ratingSubtotalUsageType;
	}

	public String getRatingSubtotalDisplayName() {
		return ratingSubtotalDisplayName;
	}

	public void setRatingSubtotalDisplayName(String ratingSubtotalDisplayName) {
		this.ratingSubtotalDisplayName = ratingSubtotalDisplayName;
	}

	public String getRatingSubtotalLevel() {
		return ratingSubtotalLevel;
	}

	public void setRatingSubtotalLevel(String ratingSubtotalLevel) {
		this.ratingSubtotalLevel = ratingSubtotalLevel;
	}

	public Date getLastModifiedDateTime() {
		return lastModifiedDateTime;
	}

	public void setLastModifiedDateTime(Date lastModifiedDateTime) {
		this.lastModifiedDateTime = lastModifiedDateTime;
	}

}