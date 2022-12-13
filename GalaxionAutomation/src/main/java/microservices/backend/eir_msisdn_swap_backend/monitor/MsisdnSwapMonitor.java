package microservices.backend.eir_msisdn_swap_backend.monitor;

import microservices.backend.eir_msisdn_swap_backend.dao.MsisdnSwapDAO;
import microservices.backend.eir_msisdn_swap_backend.data_model.MsisdnSwapRequest;
import utilities.generic.time.WaitUtil;

public class MsisdnSwapMonitor {

	public static boolean waitForMsisdnSwapToComplete(int msisdnSwapRequestId, String newMsisdn) {
		long endTime = System.currentTimeMillis()+60000;
		
		while(System.currentTimeMillis()<endTime) {
			MsisdnSwapRequest request = MsisdnSwapDAO.getMsisdnSwap(msisdnSwapRequestId);
			if(request != null && request.getStatus().equals("DONE")) {
				return true;
			}
			else {
				WaitUtil.waitForSeconds(5);
			}
		}
		
		return false;
	}
	
}
