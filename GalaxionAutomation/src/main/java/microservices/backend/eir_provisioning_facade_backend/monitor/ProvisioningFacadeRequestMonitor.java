package microservices.backend.eir_provisioning_facade_backend.monitor;

import java.util.ArrayList;

import microservices.backend.eir_provisioning_facade_backend.dao.ProvisioningFacadeDAO;
import microservices.backend.eir_provisioning_facade_backend.data_model.PfService;
import microservices.backend.eir_provisioning_facade_backend.objects.ProvisioningError;
import utilities.generic.time.WaitUtil;

public class ProvisioningFacadeRequestMonitor {

	private String msisdn;
	private ArrayList<PfService> services;
	
	public ProvisioningFacadeRequestMonitor(String msisdn) {
		this.msisdn=msisdn;
		this.refresh();
	}
	
	public void refresh() {
		services = ProvisioningFacadeDAO.getServices(msisdn);
	}
	
	public String getProvisioningStatus() {
		
		// handle case where no services are found
		if(services==null || services.size()==0) {
			return null;
		}
		
		// handle case where all requests are COMPLETED
		ArrayList<PfService> completedServices = ProvisioningFacadeDAO.getServices(services, "COMPLETED");
		
		if(completedServices.size()==services.size()) {
			return "COMPLETED";
		}
		
		// handle case where there's an error
		ArrayList<PfService> erroredServices = ProvisioningFacadeDAO.getServices(services, "ERROR");
		
		if(erroredServices.size()>0) {
			return "ERROR";
		}
		
		// handle the case where requests are in progress
		ArrayList<PfService> initialServices = ProvisioningFacadeDAO.getServices(services, "INITIAL");
		ArrayList<PfService> sentServices = ProvisioningFacadeDAO.getServices(services, "SENT");
		if(initialServices.size()>0 || sentServices.size()>0) {
			return "IN_PROGRESS";
		}
		
		return null;
	}
	
	public ArrayList<ProvisioningError> getErrors() {
		ArrayList<ProvisioningError> errors = new ArrayList<ProvisioningError>();
		ArrayList<PfService> erroredServices = ProvisioningFacadeDAO.getServices(services, "ERROR");
		for(PfService service:erroredServices) {
			errors.add(new ProvisioningError(service.getErrorCode(),service.getErrorDescription()));
		}
		return errors;
	}
	
	public boolean pollForProvisioningCompletion(int seconds) {
		long waitEndTime = System.currentTimeMillis() + (seconds * 1000);
		
		while(System.currentTimeMillis() < waitEndTime) {
			this.refresh();
			if(this.getProvisioningStatus()=="COMPLETED" || this.getErrors().size()>0) {
				return true;
			}
			WaitUtil.waitForSeconds(5);
		}
		
		return false;
	}
	
}
