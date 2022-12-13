package microservices.frontend.eir_myaccount_frontend.dto.responses.get_replacement_sim_charge;

import java.util.List;

public class GetReplacementSimChargeDTO {
	private boolean ongoingRequest;
	private List<Charge> charges;

	public GetReplacementSimChargeDTO() {
		super();
	}

	public boolean isOngoingRequest() {
		return ongoingRequest;
	}

	public void setOngoingRequest(boolean ongoingRequest) {
		this.ongoingRequest = ongoingRequest;
	}

	public List<Charge> getCharges() {
		return charges;
	}

	public void setCharges(List<Charge> charges) {
		this.charges = charges;
	}
}
