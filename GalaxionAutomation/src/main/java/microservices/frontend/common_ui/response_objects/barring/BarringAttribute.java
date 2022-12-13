package microservices.frontend.common_ui.response_objects.barring;

public class BarringAttribute {

	private String barringAllowed;
	private boolean readOnly;

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
