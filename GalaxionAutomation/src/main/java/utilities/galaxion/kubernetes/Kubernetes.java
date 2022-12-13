package utilities.galaxion.kubernetes;

import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.StringReader;
import java.nio.file.Files;
import java.nio.file.Paths;

import javax.swing.JOptionPane;

import com.jcraft.jsch.ChannelExec;
import com.jcraft.jsch.ChannelSftp;
import com.jcraft.jsch.JSch;
import com.jcraft.jsch.JSchException;
import com.jcraft.jsch.Session;

import utilities.galaxion.environments.EnvironmentDirectory;

public class Kubernetes {

	private Session session = null;
	private ChannelExec openChannel = null;
	private String username=null;

	private ChannelSftp sftpChannel;

	public Kubernetes() {
		
		// read connection details from the config file
		String hostname=EnvironmentDirectory.getKubernetesHostname();
		String username=EnvironmentDirectory.getKubernetesUsername();
		String privateKeyPath=EnvironmentDirectory.getKubernetesPrivateKeyPath();
		
		// establish the connection
		establishSshConnection(hostname, username,privateKeyPath);
		
		System.out.println("SSH connection established for username " + username);
	}

	private void establishSshConnection(String host, String user, String keyLocation) {

		try { 
			JSch jsch = new JSch();
			if (new File(keyLocation).exists()) {
				session = jsch.getSession(user, host);
				jsch.addIdentity(keyLocation);

				java.util.Properties config = new java.util.Properties();
				config.put("StrictHostKeyChecking", "no");
				session.setConfig(config);
				session.connect();

				sftpChannel = (ChannelSftp) session.openChannel("sftp");
				sftpChannel.connect();
			} else {
				System.out.println("The private key was not found");
				System.exit(1);
			}
		} catch (JSchException e) {
			e.printStackTrace();
			JOptionPane.showMessageDialog(null, "Wrong Username or private key (.ppk).\nPlease note the username is case sensitive.", "Authentication Failed",
					1);
			try {
				Files.deleteIfExists(Paths.get(System.getProperty("user.dir") + "\\" + "IonLogs_Exe_Errors.txt"));
			} catch (IOException ee) {
				e.printStackTrace();
			}
			try {
				Files.write(Paths.get(System.getProperty("user.dir") + "\\" + "IonLogs_Exe_Errors.txt"), e.getMessage().getBytes());
			} catch (IOException eb) {
				e.printStackTrace();
			}
			System.exit(0);
		}
	}

	public void uploadFile(String filePath) {
		username=EnvironmentDirectory.getKubernetesUsername();
		
		try {
			System.err.println("Upload: USERNAME=" + username);
			sftpChannel.put(filePath, "/home/" + username + "/");
			System.out.println("uploaded " + filePath + " to " + "/home/" + username + "/");
		} catch (Exception ex) {
			ex.printStackTrace();
		}
	}
	
	public void downloadFileToHomeDirectory(String filePath) {
		username=EnvironmentDirectory.getKubernetesUsername();
		
		File f = new File(filePath);
		
		// download the file from the pod to your home directory
		String command = "kubectl cp " + filePath + " " + f.getName();
		executeCommand(command);
		
		// determine the file name
		
		filePath = "/home/" + username + "/" + f.getName();
		System.out.println("Currently at " + executeCommand("pwd"));
		System.out.println(executeCommand("cat " + filePath));
		System.out.println(filePath);
		try {
			sftpChannel.get(filePath);
		} catch (Exception ex) {
			ex.printStackTrace();
		}
	}

	public void deleteFile(String filePath) {
		try {
			sftpChannel.rm(filePath);
		} catch (Exception ex) {
			ex.printStackTrace();
		}
	}

	public String executeCommand(String command) {
		System.out.println("Executing command: " + command);
		String commandResult = "";
		try {
			openChannel = (ChannelExec) session.openChannel("exec");
			openChannel.setCommand(command);
			openChannel.connect();

			InputStream in = openChannel.getInputStream();
			BufferedReader reader = new BufferedReader(new InputStreamReader(in));
			String buf = null;
			while ((buf = reader.readLine()) != null) {
				commandResult += buf + "\n";
			}
		} catch (IOException e) {
			e.printStackTrace();
		} catch (JSchException e) {
			e.printStackTrace();
		}

		return commandResult;

	}
	
	
	public String getResponseFromServer(String command) {
		System.out.println("getResponseFromServer(): Running command: " + command);
		BufferedReader bufReader = new BufferedReader(new StringReader(executeCommand(command)));
		String output = "";
		String line = null;

		try {
			while ((line = bufReader.readLine()) != null) {
				System.out.println("Line found: " + line);
				output = output + "\n" + line;
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
		System.out.println("getResponseFromServer(): Returning: " + output);

		if (output.trim().equals("")) {
			return null;
		} else {
			return output.trim();
		}
	}

	public void closeSshConnection() {
		if (openChannel != null && !openChannel.isClosed()) {
			openChannel.disconnect();
		}
		if (session != null && session.isConnected()) {
			session.disconnect();
		}
	}

	public String getRunningPod(String podName) {
		try {
			openChannel = (ChannelExec) session.openChannel("exec");

			// switch to the relevant context (depending on environment) and list the pods

			// command will be similar to: kubectl config use e2e-context;kubectl get pods |
			// grep sftp
			openChannel.setCommand("kubectl config use " + EnvironmentDirectory.getKubernetesContext() + ";kubectl get pods | grep " + podName);
			openChannel.connect();

			InputStream in = openChannel.getInputStream();
			BufferedReader reader = new BufferedReader(new InputStreamReader(in));

			String buf = null;

			while ((buf = reader.readLine()) != null) {
				if (buf.contains("Running")) {
					return buf.substring(0, buf.indexOf(" "));
				}
			}
		} catch (IOException e) {
			e.printStackTrace();
		} catch (JSchException e) {
			e.printStackTrace();
		}

		return null;

	}
}