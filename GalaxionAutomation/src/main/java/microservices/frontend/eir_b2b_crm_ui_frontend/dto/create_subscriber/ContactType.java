package microservices.frontend.eir_b2b_crm_ui_frontend.dto.create_subscriber;

public class ContactType {
	private String label;
	private String value;

	public ContactType(String label, String value) {
		super();
		this.label = label;
		this.value = value;
	}

	public String getLabel() {
		return label;
	}

	public void setLabel(String label) {
		this.label = label;
	}

	public String getValue() {
		return value;
	}

	public void setValue(String value) {
		this.value = value;
	}
}
