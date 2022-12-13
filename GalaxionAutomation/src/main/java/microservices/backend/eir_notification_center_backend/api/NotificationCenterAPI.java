package microservices.backend.eir_notification_center_backend.api;

import microservices.backend.eir_notification_center_backend.dto.NotificationDTO;
import microservices.backend.eir_notification_center_backend.enums.WebMessageReference;
import microservices.backend.eir_notification_center_backend.objects.CardExpiryMessage;
import microservices.backend.eir_notification_center_backend.objects.Message;
import microservices.backend.eir_notification_center_backend.objects.PortInMessage;
import microservices.backend.eir_subscription_management_backend.SubscriptionManagementDAO;
import utilities.generic.pojo_generation.pojo_to_json.PojoToJsonConverter;
import utilities.rabbitmq.RabbitMQUtil;

public class NotificationCenterAPI {
	
	/**
	 * Post a web notification to notification center
	 * 
	 * @param billingAccountID
	 * @param reference
	 * @param message
	 */
	private static void publishWebMessage(int billingAccountID, WebMessageReference reference, Message message) {
		NotificationDTO dto = new NotificationDTO();
		dto.setReference(reference.toString());
		dto.setContactUuid(SubscriptionManagementDAO.getContactUuidForBillingAccountID(billingAccountID));
		dto.setSmsNotification(false);
		dto.setEmailNotification(false);
		dto.setWebNotification(true);
		dto.setMessage(message);
		RabbitMQUtil.publishMessage(PojoToJsonConverter.getJSON(dto), "notification.request");
	}
	
	/**
	 * Post a PORT_SCHEDULED web message to notification center for a customer
	 * 
	 * @param billingAccountID
	 */
	public static void publishWebMessagePortScheduled(int billingAccountID, String date, String number) {
		PortInMessage message = new PortInMessage(date, number);
		publishWebMessage(billingAccountID, WebMessageReference.PORT_SCHEDULED,message);
	}
	
	/**
	 * Post a ADJUSTMENT_APPLIED web message to notification center for a customer
	 * 
	 * @param billingAccountID
	 */
	public static void publishWebMessageAdjustmentApplied(int billingAccountID) {
		publishWebMessage(billingAccountID, WebMessageReference.ADJUSTMENT_APPLIED,null);
	}
	
	/**
	 * Post a CARD_EXPIRED web message to notification center for a customer
	 * 
	 * @param billingAccountID
	 */
	public static void publishWebMessageCardExpired(int billingAccountID) {
		CardExpiryMessage message = new CardExpiryMessage("426397xxxx5262","Steve");
		publishWebMessage(billingAccountID, WebMessageReference.CARD_EXPIRED,message);
	}
	
	// Main method - test the methods
	public static void main(String [] args) {
		
		int billingAccountID=89900060;
		
		publishWebMessagePortScheduled(billingAccountID,"14/03/2022","0871231232");
		publishWebMessageAdjustmentApplied(billingAccountID);
		publishWebMessageCardExpired(billingAccountID);
	}
	
}
