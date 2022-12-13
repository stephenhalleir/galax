package microservices.backend.tecrep.equipments_management;

import java.sql.ResultSet;
import java.sql.SQLException;

public class DBInventoryPool {
	
	private int id;
	private String code;
	private int mvno;
	private String simProfile;
	private String description;
	
	public DBInventoryPool(ResultSet rs) {
		try {
			id=rs.getInt("id");
			code=rs.getString("code");
			mvno=rs.getInt("mvno");
			simProfile=rs.getString("sim_profile");
			description=rs.getString("description");
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

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public int getMvno() {
		return mvno;
	}

	public void setMvno(int mvno) {
		this.mvno = mvno;
	}

	public String getSimProfile() {
		return simProfile;
	}

	public void setSimProfile(String simProfile) {
		this.simProfile = simProfile;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}
}
