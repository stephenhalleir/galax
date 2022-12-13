package pojo_repo.eir_order_management_backend;

import java.sql.Date;
import java.sql.ResultSet;
import java.sql.SQLException;

public class SubOfferTmp {

	private int id;
	private int subOfferId;
	private int orderOfferId;
	private int orderServiceId;

	public SubOfferTmp() {

	}

	public SubOfferTmp(ResultSet rs) {
		try {
			id = rs.getInt("id");
			subOfferId = rs.getInt("sub_offer_id");
			orderOfferId = rs.getInt("order_offer_id");
			orderServiceId = rs.getInt("order_service_id");
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

	public int getSubOfferId() {
		return subOfferId;
	}

	public void setSubOfferId(int subOfferId) {
		this.subOfferId = subOfferId;
	}

	public int getOrderOfferId() {
		return orderOfferId;
	}

	public void setOrderOfferId(int orderOfferId) {
		this.orderOfferId = orderOfferId;
	}

	public int getOrderServiceId() {
		return orderServiceId;
	}

	public void setOrderServiceId(int orderServiceId) {
		this.orderServiceId = orderServiceId;
	}

}