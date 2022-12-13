package microservices.backend.eir_bulk_backend.monitor;

import microservices.backend.eir_bulk_backend.dao.BulkDAO;
import microservices.backend.eir_bulk_backend.data_model.Bulk;
import microservices.backend.eir_bulk_backend.data_model.Flow;
import utilities.generic.time.WaitUtil;

public class BulkMonitor {

	private static int timeout=30;
	
	/**
	 * Wait for a record to be created in the BULK table
	 * @param filename
	 * @return the bulk object
	 */
	public static Bulk waitForBulkCreation(String filename) {
		
		long waitEndTime = System.currentTimeMillis() + (timeout * 1000);

		while (System.currentTimeMillis() < waitEndTime) {
			Bulk bulk=BulkDAO.getBulkFile(filename);
			if (bulk != null) {
				return bulk;
			} else {
				WaitUtil.waitForSeconds(5);
			}
		}

		return null;
	}
}
