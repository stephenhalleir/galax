package testCases.testObjects.payments;

import java.sql.Date;

public class CreditCard {

	private int id;
	private String type;
	private String cardholderName;
	private Date expiryDate;
	private String partialDigits;
	private boolean defaultCard;
	private boolean cancelled;
	private String providerRef;
	
	public CreditCard() {
		id=-1;
		type="";
		cardholderName="";
		expiryDate=null;
		partialDigits="";
		providerRef="";
		defaultCard=false;
		cancelled=false;
	}

	public String toString() {
		return partialDigits + ", " + expiryDate + ", " + cardholderName;
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

	public Date getExpiryDate() {
		return expiryDate;
	}

	public void setExpiryDate(Date expiryDate) {
		this.expiryDate = expiryDate;
	}

	public String getPartialDigits() {
		return partialDigits;
	}

	public void setPartialDigits(String partialDigits) {
		this.partialDigits = partialDigits;
	}

	public boolean isDefaultCard() {
		return defaultCard;
	}

	public void setDefaultCard(boolean defaultCard) {
		this.defaultCard = defaultCard;
	}

	public boolean isCancelled() {
		return cancelled;
	}

	public void setCancelled(boolean cancelled) {
		this.cancelled = cancelled;
	}

	public String getProviderRef() {
		return providerRef;
	}

	public void setProviderRef(String providerRef) {
		this.providerRef = providerRef;
	}	
}
