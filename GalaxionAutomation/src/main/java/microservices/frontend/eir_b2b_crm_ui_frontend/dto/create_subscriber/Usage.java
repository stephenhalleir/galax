package microservices.frontend.eir_b2b_crm_ui_frontend.dto.create_subscriber;

public class Usage {
	private int catalogItemId;
	private String description;

	public Usage() {
		super();
	}

	public int getCatalogItemId() {
		return catalogItemId;
	}

	public void setCatalogItemId(int catalogItemId) {
		this.catalogItemId = catalogItemId;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}
}
