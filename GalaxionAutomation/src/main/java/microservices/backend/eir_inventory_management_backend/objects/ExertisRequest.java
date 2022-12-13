package microservices.backend.eir_inventory_management_backend.objects;

public class ExertisRequest {

	private int orderItemId;
	private String offerCode;
	private int itemID; // refers to order-management.item.item_id (links to catalog)
	private boolean hasHandset;
	private boolean hasSIM;

	public ExertisRequest() {
		super();
		this.orderItemId = -1;
		this.offerCode = "";
		this.itemID=-1;
		this.hasHandset=false;
		this.hasSIM=false;
	}

	public ExertisRequest(int orderItemId, String offerCode) {
		super();
		this.orderItemId = orderItemId;
		this.offerCode = offerCode;
		this.hasHandset=false;
		this.hasSIM=false;
	}

	public int getOrderItemId() {
		return orderItemId;
	}

	public void setOrderItemId(int orderItemId) {
		this.orderItemId = orderItemId;
	}

	public String getOfferCode() {
		return offerCode;
	}

	public void setOfferCode(String offerCode) {
		this.offerCode = offerCode;
	}

	public int getItemID() {
		return itemID;
	}

	public void setItemID(int itemID) {
		this.itemID = itemID;
	}

	public boolean hasHandset() {
		return hasHandset;
	}

	public void setHandset(boolean hasHandset) {
		this.hasHandset = hasHandset;
	}

	public boolean isHasHandset() {
		return hasHandset;
	}

	public void setHasHandset(boolean hasHandset) {
		this.hasHandset = hasHandset;
	}

	public boolean isHasSIM() {
		return hasSIM;
	}

	public void setHasSIM(boolean hasSIM) {
		this.hasSIM = hasSIM;
	}
}
