package utilities.simpack;

import ch.ethz.ssh2.Connection;
import ch.ethz.ssh2.Session;
import ch.ethz.ssh2.StreamGobbler;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

public class Generic_SSH_Utility {

	private String hostname;
	private String username;
	private String password;
	private static Session sess;
	private static Connection conn;

	// Constructor
	public Generic_SSH_Utility(String hostname, String username, String password) {
		this.hostname = hostname;
		this.username = username;
		this.password = password;
	}
	
	// Getter method - returns Session
	public Session getActiveSession() {
		return sess;
	}

	// Getter method - returns Connection
	public Connection getActiveConnSession() {
		return conn;
	}

	/*
	 * Connect a new SSH Session based on the host and login values provided
	 * This must be called before any commands can be sent to the server
	 */
	public void connectNewSSH() {
		try {
			conn = new Connection(hostname);

			conn.connect();

			boolean isAuthenticated = conn.authenticateWithPassword(username,
					password);

			if (!isAuthenticated) {
				throw new IOException("Authentication failed.");
			}

			sess = conn.openSession();

		} catch (IOException e) {

			e.printStackTrace(System.err);
			System.exit(2);
		}
	}
	
	/*
	 * This method executes a single command on the server and returns the
	 * output as a String.
	 * Note that this does not get called explicitly - we should call runCommand()
	 */
	private String execCommand(String command) throws IOException {
		sess.execCommand(command);
		InputStream stdout = new StreamGobbler(sess.getStdout());

		BufferedReader br = new BufferedReader(new InputStreamReader(stdout));
		String output = "";
		for (;;) {
			String line = br.readLine();
			if (line == null)
				break;
			output = output + "\n";
			output = output + line;
		}

		br.close();

		return output;
	}

	
	// run a single command on the server and return the output.
	// Note that this will not handle consecutive commands - e.g. cd /tmp and then pwd and then ls
	public String runCommand(String ecsCommand) {
		Generic_SSH_Utility ssh = this;
		ssh.connectNewSSH();
		try {
			String s = ssh.execCommand(ecsCommand);
			ssh.getActiveConnSession().close();
			return s;
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}
	
	// Main (test) method
	public static void main(String [] args){
		System.out.println("Started.");
		Generic_SSH_Utility writer = new Generic_SSH_Utility("10.144.242.175","fworks","fworks123");
		writer.connectNewSSH();
		try {
			System.out.println(writer.runCommand("pwd;cd /tmp/terry/Test_Simulator;pwd;ls -altr *xml*"));
		} catch (Exception e) {
			e.printStackTrace();
		}
		System.out.println("Done.");
	}
}
