package pojo_repo.eir_mnp_facade_backend;

import java.sql.Date;
import java.sql.ResultSet;
import java.sql.SQLException;

public class RefPortHoliday {

	private int id;

	public RefPortHoliday() {

	}

	public RefPortHoliday(ResultSet rs) {
		try {
			id = rs.getInt("id");
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

}