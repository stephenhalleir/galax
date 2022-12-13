package utilities.simpack;

import java.io.InputStream;
import java.io.PrintStream;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;
import java.util.concurrent.TimeUnit;

import com.jcraft.jsch.Channel;
import com.jcraft.jsch.ChannelShell;
import com.jcraft.jsch.JSch;
import com.jcraft.jsch.Session;

public class SV_SSH_Utility {

	private Session session;
	private ChannelShell channel;
	private String username;
	private String password;
	private String hostname;
	private String user;
	private boolean connectedOk;

	public SV_SSH_Utility(String username) {

		connectedOk = true;

		if (username.toUpperCase().equals("ISVTST04") || username.toUpperCase().equals("ISVTST05") || username.toUpperCase().equals("ISVTST07")) {
			hostname = "10.105.22.14";
			this.username = "bfolan";
			password = "bfolan_1";
		} else if (username.toUpperCase().equals("ISVTST01") || username.toUpperCase().equals("ISVTST02") || username.toUpperCase().equals("ISVTST03")) {
			hostname = "10.118.118.69";
			this.username = "bfolan";
			password = "password_1";
		}
		user = username;
	}

	public SV_SSH_Utility(String user, String hostname, String username, String password) {
		this.user = user;
		this.hostname = hostname;
		this.username = username;
		this.password = password;
	}

	public Session getSession() {
		if (session == null || !session.isConnected()) {
			session = connect(hostname, username, password);
		}
		return session;
	}

	public Channel getChannel() {
		if (channel == null || !channel.isConnected()) {
			try {
				channel = (ChannelShell) getSession().openChannel("shell");
				channel.connect();

			} catch (Exception e) {
				System.out.println("Error while opening channel: " + e);
				connectedOk = false;
			}
		}
		return channel;
	}

	public Session connect(String hostname, String username, String password) {

		JSch jSch = new JSch();

		try {

			session = jSch.getSession(username, hostname, 22);
			Properties config = new Properties();
			config.put("StrictHostKeyChecking", "no");
			session.setConfig(config);
			session.setPassword(password);

			session.connect();
		} catch (Exception e) {
			System.out.println("An error occurred while connecting to " + hostname + ": " + e);
			connectedOk = false;
		}

		return session;
	}

	public boolean isConnectedOk() {
		return connectedOk;
	}

	public String executeCommands(List<String> commands) {

		try {
			Channel channel = getChannel();

			// System.out.println("Sending commands...");
			sendCommands(channel, commands);

			String s = readChannelOutput(channel);
			return s;

		} catch (Exception e) {
			System.out.println("An error ocurred during executeCommands: " + e);
			return null;
		}
	}

// execute a list of commands in Singl.eView
	public void sendCommands(Channel channel, List<String> commands) {

		try {
			PrintStream out = new PrintStream(channel.getOutputStream());
			out.println("sudo su - " + user);
			for (String command : commands) {
				System.out.println(command);
				TimeUnit.SECONDS.sleep(2);
				out.println(command);
			}
			commands = new ArrayList<String>();
			out.println("exit");

			out.flush();
		} catch (Exception e) {
			System.out.println("Error while sending commands: " + e);
		}
	}

// read the output of the command
	public static String readChannelOutput(Channel channel) {

		byte[] buffer = new byte[1024];
		String allOutput = "";
		try {
			InputStream in = channel.getInputStream();

			String line = "";
			while (true) {
				while (in.available() > 0) {
					int i = in.read(buffer, 0, 1024);
					if (i < 0) {
						break;
					}
					line = new String(buffer, 0, i);
					allOutput += line;
				}

				if (line.contains("logout")) {
					break;
				}

				if (channel.isClosed()) {
					break;
				}
				try {
					Thread.sleep(1000);
				} catch (Exception ee) {
				}
			}
			return allOutput;
		} catch (Exception e) {
			System.out.println("Error while reading channel output: " + e);
			return null;
		}

	}

	public void close() {
		channel.disconnect();
		session.disconnect();
		System.out.println("Disconnected channel and session");
	}

	public static void main(String[] args) {
		SV_SSH_Utility sv = new SV_SSH_Utility("ISVTST07");
		List<String> commands = new ArrayList<String>();
		// session=connect(hostname, username, password);
		commands.add("ls");
		commands.add("pwd");
		String s = sv.executeCommands(commands);
		System.out.println("*" + s);
		sv.close();
	}
}