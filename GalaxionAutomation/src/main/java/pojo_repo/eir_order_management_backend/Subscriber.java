package pojo_repo.eir_order_management_backend;

import java.sql.Date;
import java.sql.ResultSet;
import java.sql.SQLException;

public class Subscriber {

	private int id;
	private int offerId;
	private String name;
	private int isVip;

	public Subscriber() {

	}

	public Subscriber(ResultSet rs) {
		try {
			id = rs.getInt("id");
			offerId = rs.getInt("offer_id");
			name = rs.getString("name");
			isVip = rs.getInt("is_vip");
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

	public int getOfferId() {
		return offerId;
	}

	public void setOfferId(int offerId) {
		this.offerId = offerId;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getIsVip() {
		return isVip;
	}

	public void setIsVip(int isVip) {
		this.isVip = isVip;
	}

}