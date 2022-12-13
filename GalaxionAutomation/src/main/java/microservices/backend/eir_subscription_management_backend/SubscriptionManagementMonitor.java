package microservices.backend.eir_subscription_management_backend;

import microservices.backend.eir_subscription_management_backend.data_model.SimCard;
import microservices.backend.eir_subscription_management_backend.data_model.Subscription;
import utilities.generic.time.WaitUtil;

public class SubscriptionManagementMonitor {

	private static int timeout = 30;

	/**
	 * Wait for the billing account ID to update in subs management
	 * 
	 * @param msisdn
	 * @param destinationBillingAccountID
	 * @return true if the change has taken place, false if the change has not taken
	 *         place
	 */
	public static boolean waitForBillingAccountIDUpdated(String msisdn, int destinationBillingAccountID) {
		long endTime = System.currentTimeMillis() + (timeout * 1000);

		while (System.currentTimeMillis() < endTime) {
			int currentBillingAccountID = SubscriptionManagementDAO.getBillingAccountIDForMsisdn(msisdn);

			if (currentBillingAccountID == destinationBillingAccountID) {
				return true;
			} else {
				WaitUtil.waitForSeconds(5);
			}
		}

		return false;
	}

	public static boolean waitForSubscriptionToReachStatus(int subscriptionID, String status, int waitTime) {
		long waitEndTime = System.currentTimeMillis() + (waitTime * 1000);

		while (System.currentTimeMillis() < waitEndTime) {
			Subscription subscription = SubscriptionManagementDAO.getSubscriptionByID(subscriptionID);
			if (subscription.getStatus().equals(status)) {
				return true;
			} else {
				WaitUtil.waitForSeconds(5);
			}
		}

		return false;
	}

	public static boolean waitForSimCardToActivate(String iccid) {
		SimCard card;

		long waitEndTime = System.currentTimeMillis() + (60 * 1000);

		while (System.currentTimeMillis() < waitEndTime) {
			card = SubscriptionManagementDAO.getSimCardByIccid(iccid);
			if (card.getActivatedAt() != null && card.getTerminatedAt()==null) {
				return true;
			} else {
				WaitUtil.waitForSeconds(5);
			}
		}

		return false;

	}
}
