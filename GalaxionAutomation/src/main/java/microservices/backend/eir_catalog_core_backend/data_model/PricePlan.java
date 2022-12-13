package microservices.backend.eir_catalog_core_backend.data_model;

import java.sql.Date;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * Represents a row in the catalog-core: price_plan table
 * 
 * @author stephenhall
 *
 */
public class PricePlan {

	private int id;
	private Date validFrom;
	private Date validTo;
	private String code;
	private int defaultPrice;
	private int maxPrice;
	private int minPrice;
	private String chargeCode;

	public PricePlan() {

	}

	public PricePlan(ResultSet rs) {
		try {
			id = rs.getInt("id");
			validFrom = rs.getDate("valid_from");
			validTo = rs.getDate("valid_to");
			code = rs.getString("code");
			defaultPrice = rs.getInt("default_price");
			maxPrice = rs.getInt("max_price");
			minPrice = rs.getInt("min_price");
			chargeCode = rs.getString("charge_code");
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

	public Date getValidFrom() {
		return validFrom;
	}

	public void setValidFrom(Date validFrom) {
		this.validFrom = validFrom;
	}

	public Date getValidTo() {
		return validTo;
	}

	public void setValidTo(Date validTo) {
		this.validTo = validTo;
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public int getDefaultPrice() {
		return defaultPrice;
	}

	public void setDefaultPrice(int defaultPrice) {
		this.defaultPrice = defaultPrice;
	}

	public int getMaxPrice() {
		return maxPrice;
	}

	public void setMaxPrice(int maxPrice) {
		this.maxPrice = maxPrice;
	}

	public int getMinPrice() {
		return minPrice;
	}

	public void setMinPrice(int minPrice) {
		this.minPrice = minPrice;
	}

	public String getChargeCode() {
		return chargeCode;
	}

	public void setChargeCode(String chargeCode) {
		this.chargeCode = chargeCode;
	}
}