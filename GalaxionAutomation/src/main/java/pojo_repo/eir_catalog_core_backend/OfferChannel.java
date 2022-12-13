package pojo_repo.eir_catalog_core_backend;

import java.sql.Date;
import java.sql.ResultSet;
import java.sql.SQLException;

public class OfferChannel {

	private String channelCode;

	public OfferChannel() {

	}

	public OfferChannel(ResultSet rs) {
		try {
			channelCode = rs.getString("channel_code");
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	public String getChannelCode() {
		return channelCode;
	}

	public void setChannelCode(String channelCode) {
		this.channelCode = channelCode;
	}

}