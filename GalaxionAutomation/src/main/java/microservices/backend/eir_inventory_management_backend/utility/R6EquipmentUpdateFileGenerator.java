package microservices.backend.eir_inventory_management_backend.utility;

import microservices.backend.eir_inventory_management_backend.objects.EquipmentUpdateFile;
import utilities.galaxion.ftp.IONFileUploader;
import utilities.generic.files.TextReader;

public class R6EquipmentUpdateFileGenerator {

	/**
	 * This method will generate an R6 equipment update file and send it to the pod for processing
	 * @param msisdn
	 * @param iccid
	 * @param id
	 * @param sendToPod
	 */
	public static void generateEquipmentUpdateFile(String msisdn, String iccid, int id, boolean sendToPod) {
		
		// generate the file object
		EquipmentUpdateFile file = new EquipmentUpdateFile(msisdn,iccid,id);
		
		// determine the file name
		String filename = "EQUIP_STATUS_CHG_AUTO_" + System.currentTimeMillis()+"_" + id + "_R6.csv_R6";
		String fullFilepath="files//tecrep//equipment_update//"+filename;
		
		// write the file to a local folder
		TextReader.writeFile(file.toString(), fullFilepath);
		System.err.println(file.toString());
		
		// send the file to the pod, if requested
		if(sendToPod) {
			IONFileUploader.uploadFile(fullFilepath, "eir-sftp-backend", "/home/eir-tecrep-eq-management-backend/tecrep_em/update_sim");
		}
	}
}
