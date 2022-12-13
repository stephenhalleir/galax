package microservices.frontend.eir_eshop_frontend.dto.catalog_get_offers_request;

import java.util.List;

public class GetOffersResponse {

	private String description;
	private int displayOrder;
	private int catalogOfferId;
	private int recurringChargeAmount;
	private int oneOffChargeAmount;
	private int upFrontChargeAmount;
	private List<Usage> usages;

	public GetOffersResponse() {
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

	public int getCatalogOfferId() {
		return catalogOfferId;
	}

	public void setCatalogOfferId(int catalogOfferId) {
		this.catalogOfferId = catalogOfferId;
	}

	public int getRecurringChargeAmount() {
		return recurringChargeAmount;
	}

	public void setRecurringChargeAmount(int recurringChargeAmount) {
		this.recurringChargeAmount = recurringChargeAmount;
	}

	public int getOneOffChargeAmount() {
		return oneOffChargeAmount;
	}

	public void setOneOffChargeAmount(int oneOffChargeAmount) {
		this.oneOffChargeAmount = oneOffChargeAmount;
	}

	public int getUpFrontChargeAmount() {
		return upFrontChargeAmount;
	}

	public void setUpFrontChargeAmount(int upFrontChargeAmount) {
		this.upFrontChargeAmount = upFrontChargeAmount;
	}

	public List<Usage> getUsages() {
		return usages;
	}

	public void setUsages(List<Usage> usages) {
		this.usages = usages;
	}
}
