package pojo_repo.eir_change_offers_backend;

import java.sql.Date;
import java.sql.ResultSet;
import java.sql.SQLException;

public class RefAcquisitionTypeGracePeriod {

	private String acquisitionType;

	public RefAcquisitionTypeGracePeriod() {

	}

	public RefAcquisitionTypeGracePeriod(ResultSet rs) {
		try {
			acquisitionType = rs.getString("acquisition_type");
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	public String getAcquisitionType() {
		return acquisitionType;
	}

	public void setAcquisitionType(String acquisitionType) {
		this.acquisitionType = acquisitionType;
	}

}