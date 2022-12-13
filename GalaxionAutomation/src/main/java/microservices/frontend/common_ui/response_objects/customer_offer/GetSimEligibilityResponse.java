package microservices.frontend.common_ui.response_objects.customer_offer;

public class GetSimEligibilityResponse {

	private int id;
	private String name;
	private int amount;

	public GetSimEligibilityResponse() {
		super();
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getAmount() {
		return amount;
	}

	public void setAmount(int amount) {
		this.amount = amount;
	}
}