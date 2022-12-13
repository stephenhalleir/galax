package microservices.backend.eir_provisioning_facade_backend.dto.retry;

import java.util.HashMap;
import java.util.Map;

import microservices.backend.eir_provisioning_facade_backend.enums.ActionType;
import utilities.generic.pojo_generation.pojo_to_json.PojoToJsonConverter;

public class ProvisioningRetryDTO {
	
	private String actionType;
	private Map<Integer, String> elements;
	
	public ProvisioningRetryDTO(ActionType actionType, int id, String retryCode) {
		this.actionType=actionType.toString();
		elements = new HashMap<Integer, String>();
		elements.put(id, retryCode);
	}

	public String getActionType() {
		return actionType;
	}

	public void setActionType(String actionType) {
		this.actionType = actionType;
	}
	
	public Map<Integer, String> getProvisioningServiceIdAndErrorMap() {
		return elements;
	}
	
	public String toString() {
		return PojoToJsonConverter.getJSON(this);
	}
}
