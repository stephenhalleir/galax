package microservices.backend.eir_cdr_repository_backend.monitor;

import microservices.backend.eir_cdr_repository_backend.dao.CDRRepoDAO;
import microservices.backend.eir_cdr_repository_backend.data_model.MobileUsageFile;
import microservices.backend.eir_cdr_repository_backend.data_model.ServiceDetail;
import utilities.generic.time.WaitUtil;

public class CDRRepoMonitor {

	private static int timeout=30;
	
	/**
	 * Poll the CDR repo MOBILE_USAGE_FILE table until a file is PROCESSED
	 * @param filename
	 * @return true/false
	 */
	public static boolean waitForFileProcessed(String filename) {
		MobileUsageFile file;
		
		long waitEndTime=System.currentTimeMillis() + 30000;
		
		while(System.currentTimeMillis()<waitEndTime) {
			file = CDRRepoDAO.getMobileUsageEventFile(filename);
			if(file != null && file.getFileStatus().equals("PROCESSED")) {
				return true;
			}
			else {
				WaitUtil.waitForSeconds(5);
			}
		}
		
		return false;
	}
	
	/**
	 * Wait for CDR repository to update with the new billing account ID
	 * @param msisdn
	 * @param destinationBillingAccountID
	 * @return true if the change has taken place, false if the change has not taken place
	 */
	public static boolean waitForCdrRepoUpdated(String msisdn, int destinationBillingAccountID) {
		long endTime=System.currentTimeMillis() + (timeout*1000);
		
		while(System.currentTimeMillis()<endTime) {
			ServiceDetail cdrServiceDetail = CDRRepoDAO.getServiceDetail(msisdn);
			int currentBillingAccountID=cdrServiceDetail.getBillingAccountID();
			
			if(currentBillingAccountID==destinationBillingAccountID) {
				return true;
			}
			else {
				WaitUtil.waitForSeconds(5);
			}
		}
		
		return false;
	}
	
}
