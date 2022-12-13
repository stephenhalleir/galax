package microservices.backend.eir_provisioning_facade_backend.monitor;

import java.util.ArrayList;

import microservices.backend.eir_provisioning_facade_backend.dao.ProvisioningFacadeDAO;
import microservices.backend.eir_provisioning_facade_backend.data_model.PfRequest;
import microservices.backend.eir_provisioning_facade_backend.enums.ProvisioningAction;
import utilities.generic.time.WaitUtil;

public class ProvisioningFacadeMonitor {

	public static PfRequest waitForNewProvisioningRequest(int subscriptionId, ProvisioningAction action, int idToIgnore) {
		
		long waitEndTime=System.currentTimeMillis() + 30000;
		
		// read the list of requests
		ArrayList<PfRequest> requests;
		
		while(System.currentTimeMillis() < waitEndTime) {
			requests = ProvisioningFacadeDAO.getRequestsBySubIdAndAction(subscriptionId, ProvisioningAction.TERMINATE_SUBSCRIBER);
			
			for(PfRequest request:requests) {
				if(request.getId()>idToIgnore) {
					return request;
				}
			}
			
			WaitUtil.waitForSeconds(5);
		}
		
		return null;
	}
	
	public static String waitForRequestToErrorOrComplete(int requestID) {
		long waitEndTime=System.currentTimeMillis() + 60000;
		
		while(System.currentTimeMillis() < waitEndTime) {
			PfRequest request = ProvisioningFacadeDAO.getRequestById(requestID);
			if(request.getRequestStatus().equals("COMPLETED") || request.getRequestStatus().equals("ERROR")) {
				
			}
			else {
				WaitUtil.waitForSeconds(5);
			}
		}
		
		// if time elapses, just return the current status
		return ProvisioningFacadeDAO.getRequestById(requestID).getRequestStatus();
	}
	
	public static boolean waitForRequestToComplete(int requestID) {
		long waitEndTime=System.currentTimeMillis() + 60000;
		
		while(System.currentTimeMillis() < waitEndTime) {
			PfRequest request = ProvisioningFacadeDAO.getRequestById(requestID);
			if(request.getRequestStatus().equals("COMPLETED")) {
				return true;
			}
			else {
				WaitUtil.waitForSeconds(5);
			}
		}
		
		// if time elapses, just return the current status
		return false;
	}
	
}
