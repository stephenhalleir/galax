package pojo_repo.eir_catalog_core_backend;

import java.sql.Date;
import java.sql.ResultSet;
import java.sql.SQLException;

public class DiscountChannel {

	private String discountCode;

	public DiscountChannel() {

	}

	public DiscountChannel(ResultSet rs) {
		try {
			discountCode = rs.getString("discount_code");
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	public String getDiscountCode() {
		return discountCode;
	}

	public void setDiscountCode(String discountCode) {
		this.discountCode = discountCode;
	}

}