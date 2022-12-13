package microservices.frontend.common_ui.response_objects.b2b_crm;

public class GetRefBillCyclesResponse {
	
	private String label;
	private String value;

	public GetRefBillCyclesResponse() {
		super();
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
