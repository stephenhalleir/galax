package pojo_repo.eir_cdr_repository_backend;

import java.sql.Date;
import java.sql.ResultSet;
import java.sql.SQLException;

public class RefRatingSubtotalMap {

	private int id;
	private int ratingSubtotalTypeId;
	private int usageCounterTypeId;
	private Date effectiveStartDateTime;

	public RefRatingSubtotalMap() {

	}

	public RefRatingSubtotalMap(ResultSet rs) {
		try {
			id = rs.getInt("id");
			ratingSubtotalTypeId = rs.getInt("rating_subtotal_type_id");
			usageCounterTypeId = rs.getInt("usage_counter_type_id");
			effectiveStartDateTime = rs.getDate("effective_start_date_time");
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

	public int getRatingSubtotalTypeId() {
		return ratingSubtotalTypeId;
	}

	public void setRatingSubtotalTypeId(int ratingSubtotalTypeId) {
		this.ratingSubtotalTypeId = ratingSubtotalTypeId;
	}

	public int getUsageCounterTypeId() {
		return usageCounterTypeId;
	}

	public void setUsageCounterTypeId(int usageCounterTypeId) {
		this.usageCounterTypeId = usageCounterTypeId;
	}

	public Date getEffectiveStartDateTime() {
		return effectiveStartDateTime;
	}

	public void setEffectiveStartDateTime(Date effectiveStartDateTime) {
		this.effectiveStartDateTime = effectiveStartDateTime;
	}

}