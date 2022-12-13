package pojo_repo.eir_mnp_facade_backend;

import java.sql.Date;
import java.sql.ResultSet;
import java.sql.SQLException;

public class RefPortDays {

	private int id;
	private String brand;
	private String channel;
	private int minDay;

	public RefPortDays() {

	}

	public RefPortDays(ResultSet rs) {
		try {
			id = rs.getInt("id");
			brand = rs.getString("brand");
			channel = rs.getString("channel");
			minDay = rs.getInt("min_day");
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

	public String getBrand() {
		return brand;
	}

	public void setBrand(String brand) {
		this.brand = brand;
	}

	public String getChannel() {
		return channel;
	}

	public void setChannel(String channel) {
		this.channel = channel;
	}

	public int getMinDay() {
		return minDay;
	}

	public void setMinDay(int minDay) {
		this.minDay = minDay;
	}

}