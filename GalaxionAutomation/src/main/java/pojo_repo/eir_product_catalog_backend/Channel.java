package pojo_repo.eir_product_catalog_backend;

import java.sql.Date;
import java.sql.ResultSet;
import java.sql.SQLException;

public class Channel {

	private int id;
	private String channelName;
	private String brand;

	public Channel() {

	}

	public Channel(ResultSet rs) {
		try {
			id = rs.getInt("id");
			channelName = rs.getString("channel_name");
			brand = rs.getString("brand");
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

	public String getChannelName() {
		return channelName;
	}

	public void setChannelName(String channelName) {
		this.channelName = channelName;
	}

	public String getBrand() {
		return brand;
	}

	public void setBrand(String brand) {
		this.brand = brand;
	}

}