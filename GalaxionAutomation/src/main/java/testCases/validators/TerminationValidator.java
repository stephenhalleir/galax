package testCases.validators;

import external_systems.mobile_network.utilities.Service;
import microservices.backend.eir_order_management_backend.dao.OrderManagementDAO;
import microservices.backend.eir_provisioning_facade_backend.api.ProvisioningFacadeAPI;
import microservices.backend.eir_provisioning_facade_backend.dao.ProvisioningFacadeDAO;
import microservices.backend.eir_provisioning_facade_backend.data_model.PfService;
import microservices.backend.eir_provisioning_facade_backend.enums.ServiceAction;
import microservices.backend.eir_subscription_management_backend.SubscriptionManagementDAO;
import microservices.backend.eir_subscription_management_backend.SubscriptionManagementMonitor;
import utilities.generic.time.Timestamp;
import utilities.generic.time.WaitUtil;

public class TerminationValidator {

	private String startTime;
	private static String msisdn;

	public TerminationValidator(String msisdn) {
		this.msisdn = msisdn;
		startTime = Timestamp.getTimestamp("yyyy-MM-dd HH:mm:ss");
	} 


	public boolean isTerminationScheduled() {

		// 30 seconds
		long pollStopTime = System.currentTimeMillis() + 30000;

		// query the database every second to check for the cancellation
		while (System.currentTimeMillis() < pollStopTime) {
			if (OrderManagementDAO.isCancellationScheduled(msisdn, startTime)) {
				return true;
			}
			WaitUtil.waitForSeconds(1);
		}

		// if the time elapses, return false
		return false;
	}

	public boolean isServiceInStatus(String status) {

		// 30 seconds
		long pollStopTime = System.currentTimeMillis() + 30000;

		// query the database every second to check the service status
		while (System.currentTimeMillis() < pollStopTime) {
			if (SubscriptionManagementDAO.getServiceStatus(msisdn).equalsIgnoreCase(status)) {
				return true;
			}
			WaitUtil.waitForSeconds(1);
		}

		// if the time elapses, return false
		return false;
	}

	public boolean retryError(PfService error) {
		System.out.println("Retrying error " + error.getErrorCode());

		// adopt an MMSC error
		if (error.getErrorCode().equals("TerminateSubscriber:[MMSC][Adopt]")) {
			return ProvisioningFacadeAPI.adoptProvisioningError(error.getId()).getResponse().statusCode()==204;
		} else {
			System.out.println("Not MMSC error. Please investigate");
			return false;
		}
	}

	// wait for the request to either complete or error
	// if it errors, handle the errors
	public boolean manageProvisioningRequest() {

		PfService provisioningFacadeService = null;

		// wait 60 seconds for the service to be either COMPLETED or ERROR
		long pollStopTime = System.currentTimeMillis() + 60000;

		System.out.println("Searching for the request in eir-provisioning-facade-backend.service");

		// query the database every second to check the service status
		while (provisioningFacadeService == null && System.currentTimeMillis() < pollStopTime) {
			provisioningFacadeService = ProvisioningFacadeDAO.getProvisioningService(msisdn, ServiceAction.TERMINATE_SUBSCRIBER, startTime).get(0);
			WaitUtil.waitForSeconds(1);
		}

		// if the service is COMPLETED, return true/ok
		if (provisioningFacadeService != null && provisioningFacadeService.getServiceStatus().equalsIgnoreCase("COMPLETED")) {
			System.out.println("Service is in status COMPLETED");
			return true;
		}
		// else if there are errors, clean them up
		else if (provisioningFacadeService != null && provisioningFacadeService.getServiceStatus().equalsIgnoreCase("ERROR")) {

			System.out.println("Service is in status ERROR with error code " + provisioningFacadeService.getErrorCode());

			// clean up the errors
			boolean retried = retryError(provisioningFacadeService);

			// if the retry was ok, wait for the request to go go COMPLETED
			if (retried) {
				pollStopTime = System.currentTimeMillis() + 30000;

				// wait for the status to change to COMPLETED
				while (System.currentTimeMillis() < pollStopTime && provisioningFacadeService.getServiceStatus().equals("COMPLETED")) {
					provisioningFacadeService = ProvisioningFacadeDAO.getProvisioningService(msisdn, ServiceAction.TERMINATE_SUBSCRIBER, startTime).get(0);
					WaitUtil.waitForSeconds(1);
				}
			} else {
				System.out.println("The error could not be retried. Please investigate.");
				return false;
			}
		}

		return false;
	}
}
