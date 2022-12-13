package microservices.frontend.eir_eshop_frontend.dto.catalog_get_offers_request;

public class Usage {
	private String description;
	private int displayOrder;

	public Usage() {
		super();
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public int getDisplayOrder() {
		return displayOrder;
	}

	public void setDisplayOrder(int displayOrder) {
		this.displayOrder = displayOrder;
	}

}
