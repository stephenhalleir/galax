package utilities.galaxion.ftp;

/**
 * This utility class will send a file from
 * local machine into one of the ION pods
 *
 * @author Stephen Hall
 * @date   08/03/2021
 * 
 */

import java.io.File;

import utilities.galaxion.environments.EnvironmentDirectory;
import utilities.galaxion.kubernetes.Kubernetes;

public class IONFileUploader {

	/**
	 * 
	 * @param localDirectory - Directory on the local machine where the file is located
	 * @param pod - partial pod name				
	 * @param destinationDirectory - destination directory within the pod
	 * 
	 */
	public static void uploadFile(String localDirectory, String pod, String destinationDirectory) {
		
		// create a file object
		File file = new File(localDirectory);

		// connect to the server
		String username = EnvironmentDirectory.getKubernetesUsername();
		Kubernetes kubernetes = new Kubernetes();
		pod = kubernetes.getRunningPod(pod);
		
		// upload the file
		kubernetes.uploadFile(localDirectory);

		// move the file to the target directory
		String command = "kubectl cp " + file.getName() + " " + pod + ":" + destinationDirectory;
		kubernetes.executeCommand(command);

		// delete the source file
		kubernetes.deleteFile("/home/" + username + "/" + file.getName());
		
		System.out.println("Upload complete.");
	}
}
