package microservices.backend.tecrep.stock_management.utility;

import microservices.backend.eir_logistics_backend.file_objects.DELFile;
import microservices.backend.eir_logistics_backend.file_objects.DELLine;
import utilities.galaxion.ftp.IONFileUploader;
import utilities.generic.files.TextReader;
import utilities.generic.time.WaitUtil;

public class DELGenerator {
	
	/**
	 * Generate a DEL file for a batch of handsets and FTP it to the equipment-update pod, if required
	 * @param handsetCode
	 * @param quantity - quantity of handsets required
	 * @param sendFileToServer - true if we want to send the file to the server
	 * @return DEL file object
	 */
	public static DELFile generateHandsets(String handsetCode, int quantity, boolean includeHeader, boolean sendFileToServer) {
		
		// create a new DEL file object
		DELFile file = new DELFile();
		file.setIncludeHeader(includeHeader);
		
		// generate the data for the DEL file
		for(int i=0;i<quantity;i++) {
			String imei = "35"+System.currentTimeMillis();
			String packId = handsetCode + imei.substring(8);
			file.getDelLines().add(new DELLine(packId,imei));
			WaitUtil.waitForMilliSeconds(1);
		}
		
		// generate a new, unique filename
		String filename = DelFileNameGenerator.getDelFilename();
		file.setFilepath("files\\tecrep\\stock_management\\del_files\\"+filename);
		
		// write the data to a file in the local directory
		TextReader.writeFile(file.toString(), file.getFilepath());
		
		// send the file to the pod, if required
		if(sendFileToServer) {
			IONFileUploader.uploadFile(file.getFilepath(), "eir-sftp-backend", "/home/eir-tecrep-eq-management-backend/tecrep_em/import_del");
		}
		return file;
	}
}
