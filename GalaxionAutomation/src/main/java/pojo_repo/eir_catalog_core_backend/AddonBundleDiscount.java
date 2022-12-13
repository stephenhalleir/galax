package pojo_repo.eir_catalog_core_backend;

import java.sql.Date;
import java.sql.ResultSet;
import java.sql.SQLException;

public class AddonBundleDiscount {

	private String discountCode;

	public AddonBundleDiscount() {

	}

	public AddonBundleDiscount(ResultSet rs) {
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