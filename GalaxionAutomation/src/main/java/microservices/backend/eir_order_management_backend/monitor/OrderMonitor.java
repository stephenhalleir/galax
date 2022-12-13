package microservices.backend.eir_order_management_backend.monitor;

import microservices.backend.eir_order_management_backend.dao.OrderManagementDAO;
import utilities.generic.time.WaitUtil;

public class OrderMonitor {

	/**
	 * Wait for an order to get created in the product_order table with a particular
	 * external reference
	 * 
	 * @param externalRef
	 * @return
	 */
	public static String waitForOrderCreation(String externalRef) {
		long waitEndTime = System.currentTimeMillis() + 30000;

		while (System.currentTimeMillis() < waitEndTime) {
			String orderRef = OrderManagementDAO.getOrderReferenceForExternalID(externalRef);
			if (orderRef != null) {
				return orderRef;
			} else {
				WaitUtil.waitForSeconds(5);
			}
		}

		return null;
	}

	// wait for the order to move to a COMPLETED state
	public static boolean waitForOrderToComplete(String orderReference) {

		long endTime = System.currentTimeMillis() + 300000;

		String status = "";

		while (System.currentTimeMillis() < endTime) {
			status = OrderManagementDAO.getOrderStatus(orderReference);

			if (status.equals("COMPLETED")) {
				return true;
			} else if (status.equals("ERROR")) {
				System.err.println("Order " + orderReference + " is in status ERROR");
				return false;
			} else {
				// wait 5 seconds before repolling
				WaitUtil.waitForSeconds(5);
			}
		}

		return false;
	}

	// wait for an order to progress through to the DELIVERY step
	public static boolean waitForOrderToReachDeliveryStep(String orderReference) {
		
		long endTime = System.currentTimeMillis() + 300000;

		while (System.currentTimeMillis() < endTime) {
			String deliveryStatus = OrderManagementDAO.getTaskStatus(orderReference, "DELIVERY");
			if(deliveryStatus.equals("IN_PROGRESS")) {
				return true;
			}
			else {
				WaitUtil.waitForSeconds(5);
			}
		}
		return false;

	}
}
