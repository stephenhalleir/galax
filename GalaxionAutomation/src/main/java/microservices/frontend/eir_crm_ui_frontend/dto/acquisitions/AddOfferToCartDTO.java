package microservices.frontend.eir_crm_ui_frontend.dto.acquisitions;

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
