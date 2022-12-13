package pojo_repo.eir_payment_center_backend;

import java.sql.Date;
import java.sql.ResultSet;
import java.sql.SQLException;

public class CardPaymentMethod {

	private int id;
	private String type;
	private String cardholderName;
	private Date expirationDate;
	private String partialDigits;
	private String providerRef;

	public CardPaymentMethod() {

	}

	public CardPaymentMethod(ResultSet rs) {
		try {
			id = rs.getInt("id");
			type = rs.getString("type");
			cardholderName = rs.getString("cardholder_name");
			expirationDate = rs.getDate("expiration_date");
			partialDigits = rs.getString("partial_digits");
			providerRef = rs.getString("provider_ref");
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

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getCardholderName() {
		return cardholderName;
	}

	public void setCardholderName(String cardholderName) {
		this.cardholderName = cardholderName;
	}

	public Date getExpirationDate() {
		return expirationDate;
	}

	public void setExpirationDate(Date expirationDate) {
		this.expirationDate = expirationDate;
	}

	public String getPartialDigits() {
		return partialDigits;
	}

	public void setPartialDigits(String partialDigits) {
		this.partialDigits = partialDigits;
	}

	public String getProviderRef() {
		return providerRef;
	}

	public void setProviderRef(String providerRef) {
		this.providerRef = providerRef;
	}

}