package microservices.frontend.eir_b2b_crm_ui_frontend.dto.create_account;

public class TaxDetails {
	private String taxCategory;
	
	public TaxDetails(String taxCategory) {
		super();
		this.taxCategory = taxCategory;
	}

	public String getTaxCategory() {
		return taxCategory;
	}

	public void setTaxCategory(String taxCategory) {
		this.taxCategory = taxCategory;
	}
}
