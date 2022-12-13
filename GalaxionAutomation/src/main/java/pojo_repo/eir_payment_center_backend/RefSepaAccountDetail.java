package pojo_repo.eir_payment_center_backend;

import java.sql.Date;
import java.sql.ResultSet;
import java.sql.SQLException;

public class RefSepaAccountDetail {

	private String brand;
	private String accountType;
	private String type;
	private String currency;
	private String bic;
	private String iban;
	private String name;
	private String uniqueIdPrefix;

	public RefSepaAccountDetail() {

	}

	public RefSepaAccountDetail(ResultSet rs) {
		try {
			brand = rs.getString("brand");
			accountType = rs.getString("account_type");
			type = rs.getString("type");
			currency = rs.getString("currency");
			bic = rs.getString("bic");
			iban = rs.getString("iban");
			name = rs.getString("name");
			uniqueIdPrefix = rs.getString("unique_id_prefix");
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	public String getBrand() {
		return brand;
	}

	public void setBrand(String brand) {
		this.brand = brand;
	}

	public String getAccountType() {
		return accountType;
	}

	public void setAccountType(String accountType) {
		this.accountType = accountType;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getCurrency() {
		return currency;
	}

	public void setCurrency(String currency) {
		this.currency = currency;
	}

	public String getBic() {
		return bic;
	}

	public void setBic(String bic) {
		this.bic = bic;
	}

	public String getIban() {
		return iban;
	}

	public void setIban(String iban) {
		this.iban = iban;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getUniqueIdPrefix() {
		return uniqueIdPrefix;
	}

	public void setUniqueIdPrefix(String uniqueIdPrefix) {
		this.uniqueIdPrefix = uniqueIdPrefix;
	}

}