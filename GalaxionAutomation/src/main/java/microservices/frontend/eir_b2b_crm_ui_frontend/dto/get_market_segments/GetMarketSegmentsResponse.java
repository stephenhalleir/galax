package microservices.frontend.eir_b2b_crm_ui_frontend.dto.get_market_segments;

public class GetMarketSegmentsResponse {
	private String value;
	private String label;
	
	public GetMarketSegmentsResponse() {
		super();
	}
	public String getValue() {
		return value;
	}
	public void setValue(String value) {
		this.value = value;
	}
	public String getLabel() {
		return label;
	}
	public void setLabel(String label) {
		this.label = label;
	}
	
	
}
