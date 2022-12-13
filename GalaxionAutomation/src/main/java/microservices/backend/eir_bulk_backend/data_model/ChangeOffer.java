package microservices.backend.eir_bulk_backend.data_model;

import java.sql.ResultSet;
import java.sql.SQLException;

public class ChangeOffer {
	
	private int id;
	private String orderReference;
	
	public ChangeOffer() {
		
	}
	
	public ChangeOffer(ResultSet rs) {
		try {
			id=rs.getInt("id");
			orderReference=rs.getString("order_reference");
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

	public String getOrderReference() {
		return orderReference;
	}

	public void setOrderReference(String orderReference) {
		this.orderReference = orderReference;
	}
}
