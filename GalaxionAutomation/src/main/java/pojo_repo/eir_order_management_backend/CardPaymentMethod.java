package pojo_repo.eir_order_management_backend;

import java.sql.Date;
import java.sql.ResultSet;
import java.sql.SQLException;

public class CardPaymentMethod {

	private int paymentId;
	private String cardHolderName;
	private String type;
	private String partialDigits;
	private String providerReference;

	public CardPaymentMethod() {

	}

	public CardPaymentMethod(ResultSet rs) {
		try {
			paymentId = rs.getInt("payment_id");
			cardHolderName = rs.getString("card_holder_name");
			type = rs.getString("type");
			partialDigits = rs.getString("partial_digits");
			providerReference = rs.getString("provider_reference");
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	public int getPaymentId() {
		return paymentId;
	}

	public void setPaymentId(int paymentId) {
		this.paymentId = paymentId;
	}

	public String getCardHolderName() {
		return cardHolderName;
	}

	public void setCardHolderName(String cardHolderName) {
		this.cardHolderName = cardHolderName;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getPartialDigits() {
		return partialDigits;
	}

	public void setPartialDigits(String partialDigits) {
		this.partialDigits = partialDigits;
	}

	public String getProviderReference() {
		return providerReference;
	}

	public void setProviderReference(String providerReference) {
		this.providerReference = providerReference;
	}

}