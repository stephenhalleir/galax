package microservices.backend.tecrep.equipments_management.utility;

import java.util.ArrayList;

import microservices.backend.tecrep.equipments_management.files.InpFile;
import microservices.backend.tecrep.equipments_management.files.OutFile;
import microservices.frontend.tecrep_monitor.api.TecrepMonitorAPI;
import utilities.galaxion.kubernetes.Kubernetes;
import utilities.generic.api.APITransaction;
import utilities.generic.files.TextReader;

public class TecrepEquipmentsUtility {

	// Get the MMC file from the equipments directory
	public static String getMMCFileContentsFromServer(String filename) {

		Kubernetes kubernetes = new Kubernetes();

		String podName = kubernetes.getRunningPod("eir-sftp-backend");
		System.err.println(podName);
		String directory = "home/eir-tecrep-eq-management-backend/tecrep_em/export_sim";

		kubernetes.downloadFileToHomeDirectory(podName + ":" + directory + "/" + filename);

		String response = kubernetes.getResponseFromServer("cat  " + filename);
		return response.trim();
	}

	// Get the MMC file from the API
	public static String getMMCFileContentsFromAPI(String filename) {
		String token = TecrepMonitorAPI.getToken();
		APITransaction t = TecrepMonitorAPI.exportSimCardBatch(token, filename);
		return t.getResponse().asString().trim();
	}

	public static InpFile getInpFile(String inpContents) {
		return new InpFile(inpContents);
	}

	public static InpFile getInpFileFromAPI(String filename) {
		return new InpFile(getMMCFileContentsFromAPI(filename));
	}

	public static String generateOutFile(String inpFileName) {

		// specify the directory and filename
		String localDirectory = "files\\tecrep\\equipment";
		String outFileName = inpFileName.replace(".inp", ".out");

		// save the .inp file
		InpFile inpFileObj = getInpFileFromAPI(inpFileName);
		TextReader.writeFile(inpFileObj.toString(), localDirectory + "\\" + inpFileName);

		// create the .out file
		OutFile outFile = new OutFile(inpFileObj);
		TextReader.writeFile(outFile.toString(), localDirectory + "\\" + outFileName);
		return localDirectory + "\\" + outFileName;
	}

	/**
	 * Generate .out file for a paired SIM request
	 * 
	 * @param inpFileName
	 * @param msisdns
	 * @return
	 */
	public static String generateOutFile(String inpFileName, ArrayList<String> msisdns) {

		// specify the directory and filename
		String localDirectory = "files\\tecrep\\equipment";
		String outFileName = inpFileName.replace(".inp", ".out");

		// save the .inp file
		InpFile inpFileObj = getInpFileFromAPI(inpFileName);
		TextReader.writeFile(inpFileObj.toString(), localDirectory + "\\" + inpFileName);

		// create the .out file
		OutFile outFile = new OutFile(inpFileObj, msisdns);
		TextReader.writeFile(outFile.toString(), localDirectory + "\\" + outFileName);
		return localDirectory + "\\" + outFileName;
	}
}
