package pojo_repo.eir_contact_management_backend;

import java.sql.Date;
import java.sql.ResultSet;
import java.sql.SQLException;

public class CommunicationPreference {

	private int id;
	private String channel;
	private String language;
	private String email;

	public CommunicationPreference() {

	}

	public CommunicationPreference(ResultSet rs) {
		try {
			id = rs.getInt("id");
			channel = rs.getString("channel");
			language = rs.getString("language");
			email = rs.getString("email");
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

	public String getChannel() {
		return channel;
	}

	public void setChannel(String channel) {
		this.channel = channel;
	}

	public String getLanguage() {
		return language;
	}

	public void setLanguage(String language) {
		this.language = language;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

}