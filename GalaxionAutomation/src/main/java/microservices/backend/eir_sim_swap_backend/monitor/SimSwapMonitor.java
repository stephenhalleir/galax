package microservices.backend.eir_sim_swap_backend.monitor;

import java.util.ArrayList;

import microservices.backend.eir_sim_swap_backend.dao.SimSwapDAO;
import microservices.backend.eir_sim_swap_backend.data_model.SimSwapRequest;
import utilities.generic.time.WaitUtil;

public class SimSwapMonitor {
	
	public static SimSwapRequest waitForSimSwapRequest(int serviceID, int previousId, int timeout) {
		long endTime = System.currentTimeMillis() + (timeout*1000);
		
		while(System.currentTimeMillis()<endTime) {
			
			// read the list of requests from the database
			ArrayList<SimSwapRequest> simSwapRequests = SimSwapDAO.getSimSwapRequests(serviceID);
			if (simSwapRequests.size() > 0 && simSwapRequests.get(0).getId() > previousId) {
				return simSwapRequests.get(0);
			}
			else {
				WaitUtil.waitForSeconds(3);
			}
		}
		
		return null;
	}
}
