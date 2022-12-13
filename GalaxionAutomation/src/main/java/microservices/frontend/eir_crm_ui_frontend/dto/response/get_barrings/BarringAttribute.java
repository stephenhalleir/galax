package microservices.frontend.eir_crm_ui_frontend.dto.response.get_barrings;

public class BarringAttribute {
	private String barringAllowed;
	private boolean readOnly;

	public BarringAttribute(String barringAllowed, boolean readOnly) {
		super();
		this.barringAllowed = barringAllowed;
		this.readOnly = readOnly;
	}
	
	public BarringAttribute() {
		super();
	}

	public String getBarringAllowed() {
		return barringAllowed;
	}

	public void setBarringAllowed(String barringAllowed) {
		this.barringAllowed = barringAllowed;
	}

	public boolean isReadOnly() {
		return readOnly;
	}

	public void setReadOnly(boolean readOnly) {
		this.readOnly = readOnly;
	}
}
