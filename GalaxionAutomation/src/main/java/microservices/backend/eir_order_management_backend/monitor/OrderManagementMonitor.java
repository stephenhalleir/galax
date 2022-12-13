package microservices.backend.eir_order_management_backend.monitor;

import microservices.backend.eir_order_management_backend.dao.OrderManagementDAO;
import microservices.backend.eir_order_management_backend.data_model.Event;
import microservices.backend.eir_order_management_backend.data_model.OmService;
import microservices.backend.eir_order_management_backend.data_model.OrderManagementAccount;
import microservices.backend.eir_order_management_backend.enums.EventType;
import utilities.generic.time.WaitUtil;

public class OrderManagementMonitor {

	private static int timeout = 30;

	// wait for the order to move to a COMPLETED state
	public static boolean waitForOrderToComplete(String orderNumber) {

		long endTime = System.currentTimeMillis() + 300000;

		String status = "";

		while (System.currentTimeMillis() < endTime && !status.equals("COMPLETED")) {
			status = OrderManagementDAO.getOrderStatus(orderNumber);

			if (status.equals("ERROR")) {
				System.err.println("Order " + orderNumber + " is in status ERROR");
				return false;
			}

			// wait 5 seconds before repolling
			WaitUtil.waitForSeconds(5);
		}

		return status.equalsIgnoreCase("COMPLETED");
	}

	public static boolean waitForOrderToReachStep(String orderReference, String step) {

		long endTime = System.currentTimeMillis() + 300000;

		while (System.currentTimeMillis() < endTime) {

			if (OrderManagementDAO.isOrderInStep(orderReference, step)) {
				return true;
			} else {
				// wait 5 seconds before repolling
				WaitUtil.waitForSeconds(5);
			}
		}
		return false;
	}

	/**
	 * Wait for an order to get created in order management with a specific external
	 * reference
	 * 
	 * @param external_id
	 * @param timeout     (seconds)
	 * @return true/false to indicate whether the order is found
	 */
	public static String waitForOrderByExternalReference(String external_id, int timeout) {

		long endTime = System.currentTimeMillis() + (1000 * timeout);

		String orderReference = null;

		while (orderReference == null && System.currentTimeMillis() < endTime) {
			orderReference = OrderManagementDAO.getOrderReferenceForExternalID(external_id);
			WaitUtil.waitForSeconds(1);
		}

		return orderReference;
	}

	/**
	 * Wait for an event to get added to the order management EVENT table with a
	 * given type and an ID greater than that provided The ID provided will indicate
	 * the previous order before the test case in progress - to avoid the test from
	 * confusing a previous order with the current order
	 * 
	 * @return true if the event is found
	 */
	public static Event waitForEventOfTypeAndLaterThanNamedEventID(EventType eventType, int previousOrderID) {

		long endTime = System.currentTimeMillis() + (1000 * timeout);

		// poll the DB for 30 seconds
		while (System.currentTimeMillis() < endTime) {
			Event event = OrderManagementDAO.getMostRecentEventOfType(eventType);

			if (event.getId() > previousOrderID) {
				return event;
			}

			// wait 2 seconds
			WaitUtil.waitForSeconds(2);
		}

		return null;
	}

	public static OmService waitForOrderServiceLaterThanNamedServiceID(int smServiceID, int previousServiceID) {

		long endTime = System.currentTimeMillis() + (1000 * timeout);

		// poll the DB for 30 seconds
		while (System.currentTimeMillis() < endTime) {
			OmService omService = OrderManagementDAO.getMostRecentOrderService(smServiceID);

			if (omService.getId() > previousServiceID) {
				return omService;
			}

			// wait 2 seconds
			WaitUtil.waitForSeconds(2);
		}

		return null;
	}

	public static boolean waitForEventToComplete(int eventID) {

		long endTime = System.currentTimeMillis() + (1000 * timeout);

		// poll the DB for 30 seconds
		while (System.currentTimeMillis() < endTime) {
			Event event = OrderManagementDAO.getEvent(eventID);
			if (event.getStatus().equals("COMPLETED")) {
				return true;
			}

			// wait 2 seconds
			WaitUtil.waitForSeconds(2);
		}

		return false;
	}

	public static OrderManagementAccount waitForB2BAccountCreatedForOrder(String reference) {

		long endTime = System.currentTimeMillis() + (1000 * timeout);

		// poll the DB for 30 seconds
		while (System.currentTimeMillis() < endTime) {
			OrderManagementAccount account = OrderManagementDAO.getSubsAccountIDForB2BCreateAccountOrder(reference);

			if (account != null) {
				return account;
			} else {
				WaitUtil.waitForSeconds(5);
			}
		}

		return null;
	}
}
