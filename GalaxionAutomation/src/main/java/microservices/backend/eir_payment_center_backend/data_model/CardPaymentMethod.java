package microservices.backend.eir_payment_center_backend.data_model;

import java.sql.ResultSet;
import java.sql.SQLException;

public class CardPaymentMethod {
	
	private int id;
	private String type;
	private String cardholderName;
	private String expirationDate;
	private String partialDigits;
	private String providerRef;
	
	// enhance with a flag related to the payment_method cancellation field
	private boolean cancelled;
	
	public CardPaymentMethod() {
		
	}
	
	public CardPaymentMethod(ResultSet rs) {
		try {
			id=rs.getInt("id");
			type=rs.getString("type");
			cardholderName=rs.getString("cardholder_name");
			expirationDate=rs.getString("expiration_date");
			partialDigits=rs.getString("partial_digits");
			providerRef=rs.getString("provider_ref");
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

	public String getExpirationDate() {
		return expirationDate;
	}

	public void setExpirationDate(String expirationDate) {
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

	public boolean isCancelled() {
		return cancelled;
	}

	public void setCancelled(boolean cancelled) {
		this.cancelled = cancelled;
	}
	
	public String toString() {
		return cardholderName + ", " + partialDigits;
	}
}
