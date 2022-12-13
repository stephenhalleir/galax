package utilities.results;

import java.util.ArrayList;

import microservices.backend.eir_carts_backend.dao.CartsDAO;
import microservices.backend.eir_carts_backend.data_model.Cart;
import microservices.backend.eir_change_offers_backend.dao.ChangeOffersDAO;
import microservices.backend.eir_change_offers_backend.data_model.ChangeOffer;
import microservices.backend.eir_order_management_backend.dao.OrderManagementDAO;
import microservices.backend.eir_order_management_backend.data_model.Event;
import microservices.backend.eir_order_management_backend.data_model.ProductOrder;
import microservices.backend.eir_provisioning_facade_backend.dao.ProvisioningFacadeDAO;
import microservices.backend.eir_provisioning_facade_backend.data_model.PfService;
import microservices.backend.eir_provisioning_facade_backend.data_model.PfRequest;
import microservices.backend.eir_provisioning_facade_backend.enums.ProvisioningAction;
import microservices.backend.eir_subscription_management_backend.SubscriptionManagementDAO;
import microservices.backend.eir_subscription_management_backend.data_model.Service;

public class TestResultExtractor {

	public static void printChangeOfferResults(String orderRef) {
		ChangeOffer changeOffer = ChangeOffersDAO.getChangeOffer(orderRef);
		Service service = SubscriptionManagementDAO.getServiceBySubscriptionID(changeOffer.getSubscriptionId());
		ProductOrder order=OrderManagementDAO.getOrderByReference(orderRef);
		Event event = OrderManagementDAO.getEvent(order.getEventId());
		ArrayList<PfRequest> requests = ProvisioningFacadeDAO.getRequestsBySubIdAndAction(service.getId(), ProvisioningAction.UPDATE_SUBSCRIBER_PROFILE);
		PfRequest request = requests.get(0);
		ArrayList<PfService> services = ProvisioningFacadeDAO.getServicesByRequestID(request.getId());
		Cart cart = CartsDAO.getCart(changeOffer.getCartUuid());
		
		System.out.println("change-offer-backend: Change offer " + orderRef + " created with cart UUID " + changeOffer.getCartUuid());
		System.out.println("carts-backend: Cart " + cart.getUuid() + " is created: " + cart.getChannel() + ", " + cart.getStatus() + ", " + cart.getAcquisitionType());
		System.out.println("order-management-backend: Order "+ order.getId() + ", " + orderRef + " created in status " + order.getStatus() + " and event ID " + order.getEventId());
		System.out.println("order-management-backend: Event " + order.getEventId() + " created: " + event.getType() + ", " + event.getStatus() + ", " + event.getSource());
		System.out.println("provisioning-facade-backend: Request " + request.getId() + ", " + request.getRequestAction() + ", " + request.getRequestStatus());
		System.out.println("provisioning-facade-backend: Services " + services.get(0).getId() + " to " + services.get(services.size()-1).getId() + " have been created");
		boolean provisioningOk=true;
		for(PfService s:services) {
			if(!s.getServiceStatus().equals("COMPLETED")) {
				provisioningOk=false;
			}
		}
		
		if(provisioningOk) {
			System.out.println("provisioning-facade-backend: All services are in status COMPLETED");
		}
		else {
			System.err.println("Error: provisioning-facade-backend: Services are not all COMPLETED");
		}
	}
	
}
