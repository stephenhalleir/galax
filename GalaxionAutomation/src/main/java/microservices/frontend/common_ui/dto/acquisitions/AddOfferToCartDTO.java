package microservices.frontend.common_ui.dto.acquisitions;

public class AddOfferToCartDTO {
	
	private int catalogOfferId;
	
	public AddOfferToCartDTO(int catalogOfferId) {
		super();
		this.catalogOfferId = catalogOfferId;
	}

	public int getCatalogOfferId() {
		return catalogOfferId;
	}

	public void setCatalogOfferId(int catalogOfferId) {
		this.catalogOfferId = catalogOfferId;
	}
}
