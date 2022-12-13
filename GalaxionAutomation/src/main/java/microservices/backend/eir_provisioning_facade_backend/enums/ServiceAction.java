package microservices.backend.eir_provisioning_facade_backend.enums;

/**
 * This Enum represents the various service_action values for provisioning
 * 
 * This value can be found in provisioning-facade.service.service_action
 * 
 * @author stephenhall
 *
 */

public enum ServiceAction {
	CREATE_SUBSCRIBER_PROFILE,
	ADD_NETWORK_SERVICE,
	REMOVE_NETWORK_SERVICE,
	TERMINATE_SUBSCRIBER,
	SWAP_SIM,
	SWAP_MSISDN,
	UPDATE_SUBSCRIBER_PROFILE
}
