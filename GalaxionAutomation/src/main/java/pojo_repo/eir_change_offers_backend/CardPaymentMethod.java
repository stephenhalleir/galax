package pojo_repo.eir_change_offers_backend;

import java.sql.Date;
import java.sql.ResultSet;
import java.sql.SQLException;

public class CardPaymentMethod {

	private String changeOfferUuid;
	private String providerReference;
	private String paymentMethodType;
	private String type;
	private String cardholderName;
	private Date expirationDate;
	private String partialDigits;

	public CardPaymentMethod() {

	}

	public CardPaymentMethod(ResultSet rs) {
		try {
			changeOfferUuid = rs.getString("change_offer_uuid");
			providerReference = rs.getString("provider_reference");
			paymentMethodType = rs.getString("payment_method_type");
			type = rs.getString("type");
			cardholderName = rs.getString("cardholder_name");
			expirationDate = rs.getDate("expiration_date");
			partialDigits = rs.getString("partial_digits");
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	public String getChangeOfferUuid() {
		return changeOfferUuid;
	}

	public void setChangeOfferUuid(String changeOfferUuid) {
		this.changeOfferUuid = changeOfferUuid;
	}

	public String getProviderReference() {
		return providerReference;
	}

	public void setProviderReference(String providerReference) {
		this.providerReference = providerReference;
	}

	public String getPaymentMethodType() {
		return paymentMethodType;
	}

	public void setPaymentMethodType(String paymentMethodType) {
		this.paymentMethodType = paymentMethodType;
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

}