package pojo_repo.eir_catalog_core_backend;

import java.sql.Date;
import java.sql.ResultSet;
import java.sql.SQLException;

public class OfferCustomerType {

	private String customerTypeCode;

	public OfferCustomerType() {

	}

	public OfferCustomerType(ResultSet rs) {
		try {
			customerTypeCode = rs.getString("customer_type_code");
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	public String getCustomerTypeCode() {
		return customerTypeCode;
	}

	public void setCustomerTypeCode(String customerTypeCode) {
		this.customerTypeCode = customerTypeCode;
	}

}