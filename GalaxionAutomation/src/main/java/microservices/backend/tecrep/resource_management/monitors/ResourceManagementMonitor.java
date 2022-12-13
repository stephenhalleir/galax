package microservices.backend.tecrep.resource_management.monitors;

import microservices.backend.tecrep.resource_management.dao.ResourceManagementDAO;
import microservices.backend.tecrep.resource_management.data_model.Number;
import microservices.backend.tecrep.resource_management.enums.Action;

public class ResourceManagementMonitor {

	public static String getNumberStatus(String number) {
		Number n = ResourceManagementDAO.getNumber(number);
		if(n==null) {
			return null;
		}
		else {
			return n.getStatus();
		}
	}
	
	public String getExpectedNumberStatus(Action action) {
		switch (action.toString()) {
		case "pair":
			return "PAIRED";
		case "unpair":
			return "AVAILABLE";
		case "assign":
			return "ASSIGNED";
		case "lockOperator":
			return "LOCKEDOPERATOR";
		case "unlockOperator":
			return "AVAILABLE";
		case "portedIn":
			return "PORTEDIN";
		case "portedOut":
			return "PORTEDOUT";
		case "book":
			return "BOOKED";
		case "activate":
			return "ACTIVATED";
		case "rollbackDeactivate":
			return "ACTIVATED";
		case "deactivate":
			return "DEACTIVATED";
		case "free":
			return "AVAILABLE";
		case "repatriate":
			return "AVAILABLE";
		case "unlockUser":
			return "AVAILABLE";
		case "lockUser":
			return "LOCKEDUSER";
		default:
			return null;
		}
	}
}
