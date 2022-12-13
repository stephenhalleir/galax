package microservices.backend.eir_barring_backend.data_model.custom;

import java.sql.ResultSet;
import java.sql.SQLException;

public class BarringDetail {
	
	private int barringID;
	private String name;
	private String barringStatus;
	private String barringLevel;
	private int readOnly;
	private int pcatID;
	
	public BarringDetail(ResultSet rs) {
		try {
			barringID=rs.getInt("barring_id");
			name=rs.getString("name");
			barringStatus=rs.getString("barring_status");
			barringLevel=rs.getString("barring_level");
			readOnly=rs.getInt("read_only");
			pcatID=rs.getInt("pcatId");
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	public int getBarringID() {
		return barringID;
	}
	public void setBarringID(int barringID) {
		this.barringID = barringID;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getBarringStatus() {
		return barringStatus;
	}
	public void setBarringStatus(String barringStatus) {
		this.barringStatus = barringStatus;
	}
	public String getBarringLevel() {
		return barringLevel;
	}
	public void setBarringLevel(String barringLevel) {
		this.barringLevel = barringLevel;
	}
	public int getReadOnly() {
		return readOnly;
	}
	public void setReadOnly(int readOnly) {
		this.readOnly = readOnly;
	}
	public int getPcatID() {
		return pcatID;
	}
	public void setPcatID(int pcatID) {
		this.pcatID = pcatID;
	}
	
	
	
}
