package microservices.backend.eir_bulk_backend.enums;

/**
 * This enum relates to the flows used by the bulk microservice
 * They are based on the values stored within eir-bulk-backend.REF_FLOW
 * 
 * @author stephenhall
 *
 */

public enum BulkRefFlow {
	CREATE_SUBSCRIPTION,
	CHANGE_OFFER,
	ADD_SUBSCRIPTION_ADDON,
	ADJUSTMENT,
	BAR_SUBSCRIPTION,
	MOVE_SUBSCRIPTION,
	PAYMENT,
	REMOVE_SUBSCRIPTION_ADDON,
	SUBSCRIPTION_ACTIVATION,
	SUBSCRIPTION_PORT_IN,
	TERMINATE_SUBSCRIPTION
}
