package microservices.backend.eir_catalog_core_backend.data_model;

import java.sql.ResultSet;
import java.sql.SQLException;

public class Channel {

	private int id;
	private String displayName;
	private String code;
	private String channelGroup;

	public Channel() {

	}

	public Channel(ResultSet rs) {
		try {
			id = rs.getInt("id");
			displayName = rs.getString("display_name");
			code=rs.getString("code");
			channelGroup=rs.getString("channel_group");
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

	public String getDisplayName() {
		return displayName;
	}

	public void setDisplayName(String displayName) {
		this.displayName = displayName;
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public String getChannelGroup() {
		return channelGroup;
	}

	public void setChannelGroup(String channelGroup) {
		this.channelGroup = channelGroup;
	}
}