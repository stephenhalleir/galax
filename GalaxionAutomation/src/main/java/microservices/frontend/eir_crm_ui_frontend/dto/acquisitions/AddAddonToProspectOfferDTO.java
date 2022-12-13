package microservices.frontend.eir_crm_ui_frontend.dto.acquisitions;

public class AddAddonToProspectOfferDTO {
	private String catalogAddonId;

	public AddAddonToProspectOfferDTO(String catalogAddonId) {
		super();
		this.catalogAddonId = catalogAddonId;
	}

	public AddAddonToProspectOfferDTO(int catalogAddonId) {
		super();
		this.catalogAddonId = Integer.toString(catalogAddonId);
	}

	public String getCatalogAddonId() {
		return catalogAddonId;
	}

	public void setCatalogAddonId(String catalogAddonId) {
		this.catalogAddonId = catalogAddonId;
	}

}
