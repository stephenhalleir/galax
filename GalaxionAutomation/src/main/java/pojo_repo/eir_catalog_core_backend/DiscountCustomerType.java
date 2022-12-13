package pojo_repo.eir_catalog_core_backend;

import java.sql.Date;
import java.sql.ResultSet;
import java.sql.SQLException;

public class DiscountCustomerType {

	private String discountCode;

	public DiscountCustomerType() {

	}

	public DiscountCustomerType(ResultSet rs) {
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