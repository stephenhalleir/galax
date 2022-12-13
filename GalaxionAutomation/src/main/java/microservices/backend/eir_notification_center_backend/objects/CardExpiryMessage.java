package microservices.backend.eir_notification_center_backend.objects;

public class CardExpiryMessage extends Message {
	private String partialDigits;
	private String firstName;

	public CardExpiryMessage(String partialDigits, String firstName) {
		super();
		this.partialDigits = partialDigits;
		this.firstName = firstName;
	}

	public String getPartialDigits() {
		return partialDigits;
	}

	public void setPartialDigits(String partialDigits) {
		this.partialDigits = partialDigits;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}
}
