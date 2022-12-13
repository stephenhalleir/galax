package microservices.backend.eir_subscription_management_backend.data_model;

import java.sql.Date;
import java.sql.ResultSet;
import java.sql.SQLException;

public class SimCard {
	private int id;
	private int orderID;
	private int catalogEquipmentID;
	private Date creationDateTime;
	private Date activatedAt;
	private Date terminatedAt;
	private String iccid;
	private String imsi;
	private String catalogCode;
	
	public SimCard() {
		
	}
	
	public SimCard(ResultSet rs) {
		try {
			id=rs.getInt("id");
			orderID=rs.getInt("order_id");
			catalogEquipmentID=rs.getInt("catalog_equipment_id");
			creationDateTime=rs.getDate("created_at");
			activatedAt=rs.getDate("activated_at");
			terminatedAt=rs.getDate("terminated_at");
			iccid=rs.getString("iccid");
			imsi=rs.getString("imsi");
			catalogCode=rs.getString("catalog_code");
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

	public int getOrderID() {
		return orderID;
	}

	public void setOrderID(int orderID) {
		this.orderID = orderID;
	}

	public int getCatalogEquipmentID() {
		return catalogEquipmentID;
	}

	public void setCatalogEquipmentID(int catalogEquipmentID) {
		this.catalogEquipmentID = catalogEquipmentID;
	}

	public Date getCreationDateTime() {
		return creationDateTime;
	}

	public void setCreationDateTime(Date creationDateTime) {
		this.creationDateTime = creationDateTime;
	}

	public Date getActivatedAt() {
		return activatedAt;
	}

	public void setActivatedAt(Date activatedAt) {
		this.activatedAt = activatedAt;
	}

	public Date getTerminatedAt() {
		return terminatedAt;
	}

	public void setTerminatedAt(Date terminatedAt) {
		this.terminatedAt = terminatedAt;
	}

	public String getIccid() {
		return iccid;
	}

	public void setIccid(String iccid) {
		this.iccid = iccid;
	}

	public String getImsi() {
		return imsi;
	}

	public void setImsi(String imsi) {
		this.imsi = imsi;
	}

	public String getCatalogCode() {
		return catalogCode;
	}

	public void setCatalogCode(String catalogCode) {
		this.catalogCode = catalogCode;
	}
}
