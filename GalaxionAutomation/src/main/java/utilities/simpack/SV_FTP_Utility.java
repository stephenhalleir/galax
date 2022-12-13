package utilities.simpack;

import java.io.File;
import java.util.Hashtable;
import com.jcraft.jsch.Channel;
import com.jcraft.jsch.ChannelSftp;
import com.jcraft.jsch.JSch;
import com.jcraft.jsch.JSchException;
import com.jcraft.jsch.Session;
import com.jcraft.jsch.SftpException;

public class SV_FTP_Utility {

	static Session session = null;
	static Channel channel = null;
	private String username;
	private String ip;
	private String password;

	public SV_FTP_Utility(String username){
		
		if(username.toUpperCase().equals("ISVTST04")||username.toUpperCase().equals("ISVTST05")||username.toUpperCase().equals("ISVTST07")){
			ip="10.105.22.14";
			this.username="bfolan";
			password="bfolan_1";
		}
		else if(username.toUpperCase().equals("ISVTST01")||username.toUpperCase().equals("ISVTST02")||username.toUpperCase().equals("ISVTST03")){
			ip="10.118.118.69";
			this.username="bfolan";
			password="password_1";
		}
		System.out.println(username + ": " + ip + " " + this.username + " " + password);
	}
	
	/*
	 * Method to FTP the file to the Singl.eView server
	 * 
	 */
	public void sendFile(String filename, String location) {
		System.out.println("Attempting to send file "+filename);
		try {
			Hashtable config = new Hashtable();
			config.put("StrictHostKeyChecking", "no");
			JSch.setConfig(config);

			// establish a session on the server
			JSch ssh = new JSch();
			session = ssh.getSession(username, ip, 22);
			session.setPassword(password);
			session.connect();
			
			// open a channel for the FTP
			channel = session.openChannel("sftp");
			channel.connect();
			ChannelSftp sftp = (ChannelSftp) channel;
			
			System.err.println(location);
			System.err.println(filename);
			
			// ftp the file to the server
			sftp.put("\\"+filename,location);
		} 
		// handle exceptions
		catch (JSchException e1) {
			e1.printStackTrace();
		} catch (SftpException e2) {
			e2.printStackTrace();
		} finally {
			if (channel != null) {
				channel.disconnect();
			}
			if (session != null) {
				session.disconnect();
			}
		}
	}
	
	/*
	 * Method to retrieve a file from the Singl.eView server
	 */
	public String retrieveFile(String sourceFile,String destinationFile) {
		try {
			Hashtable config = new Hashtable();
			config.put("StrictHostKeyChecking", "no");
			JSch.setConfig(config);

			// establish a session on the server
			JSch ssh = new JSch();
			session = ssh.getSession(username, ip, 22);
			session.setPassword(password);
			session.connect();
			
			// open a channel for the FTP
			channel = session.openChannel("sftp");
			channel.connect();
			ChannelSftp sftp = (ChannelSftp) channel;
			System.out.println("Searching for file " + sourceFile);
			sftp.get(sourceFile, destinationFile);
		} 
		// handle exceptions
		catch (JSchException e1) {
			e1.printStackTrace();
			return null;
		} catch (SftpException e2) {
			e2.printStackTrace();
			return null;
		} finally {
			if (channel != null) {
				channel.disconnect();
			}
			if (session != null) {
				session.disconnect();
			}
		}
		
		File file = new File(sourceFile);
		return destinationFile + "/" + file.getName();
	}
	
	
	public static void main(String [] args){
		SV_FTP_Utility fileWriter = new SV_FTP_Utility("isvtst01");
		fileWriter.retrieveFile("/isv/isvtst04/data/server/capc/transfer/PPR0083501.int", "PPR0083501.int");
		System.out.println("Done");
		fileWriter.retrieveFile("/tmp/AutoProv/ISVTST05/msisdn_counter_isvtst05.txt", "msisdn_counter_isvtst05.txt");
		//fileWriter.retrieveFile("/tmp/AutoProv/ISVTST07/msisdn_counter_isvtst07.txt", "msisdn_counter_isvtst07.txt");
		//fileWriter.sendFile("DEL007890100","/tmp/");
		fileWriter = new SV_FTP_Utility("isvtst02");
		fileWriter.sendFile("DEL007890100","/tmp/");
		fileWriter = new SV_FTP_Utility("isvtst03");
		fileWriter.sendFile("DEL007890100","/tmp/");
		fileWriter = new SV_FTP_Utility("isvtst04");
		fileWriter.sendFile("DEL007890100","/tmp/");
		fileWriter = new SV_FTP_Utility("isvtst05");
		fileWriter.sendFile("DEL007890100","/tmp/");
		fileWriter = new SV_FTP_Utility("isvtst07");
		fileWriter.sendFile("DEL007890100","/tmp/");
		
	}
}
