package microservices.backend.eir_subscription_management_backend.dto;

import utilities.generic.pojo_generation.pojo_to_json.PojoToJsonConverter;

/**
 * This class represents the DTO used for moving a subscription from one b2b account to another
 * @author stephenhall - 06/07/2021
 *
 */
public class SubsMgmtMoveSubscriptionDTO {
	
	private int newAccountId;
	
	public SubsMgmtMoveSubscriptionDTO(int newAccountId) {
		this.newAccountId=newAccountId;
	}

	public int getNewAccountId() {
		return newAccountId;
	}

	public void setNewAccountId(int newAccountId) {
		this.newAccountId = newAccountId;
	}
	
	public String toString() {
		return PojoToJsonConverter.getJSON(this);
	}
}
