package utilities.network;

import java.io.InputStream;
import java.io.PrintStream;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Properties;
import java.util.concurrent.TimeUnit;

import com.jcraft.jsch.Channel;
import com.jcraft.jsch.ChannelShell;
import com.jcraft.jsch.JSch;
import com.jcraft.jsch.Session;

import utilities.generic.files.TextReader;

public class SSHUtility {

	private Session session;
	private ChannelShell channel;
	private String username;
	private String password;
	private String hostname;
	private boolean connectedOk;

	public SSHUtility(String hostname, String username, String password) {
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
		// System.out.println("Disconnected channel and session");
	}

	public static void main(String[] args) {
		SSHUtility sv = new SSHUtility("10.51.11.33", "sdpuser", "sdpuser");
		List<String> commands = new ArrayList<String>();
		commands.add("cd /home/sdpuser/pranay/EC20OperationsTool");
		commands.add("./RunTool GetSubscriberDetails MSISDN=353858540503");
		String s = sv.executeCommands(commands);
		s = s.replace("[0m", "");
		s = s.replace("[42m", "");
		s = s.replace("[32m", "");
		s = s.replace("[36m", "");
		s = s.replace("[35m", "");
		s = s.replace("[30m", "");
		System.out.println("*" + s);

		List<String> items = Arrays.asList(s.split("\n"));

		// trim all strings
		for (String item : items) {
			if (item.contains("BarringType")) {
				item = item.replace("|", ",");
				System.err.println(item.split(",")[2].trim());
			}
		}

		TextReader.writeFile(s, "files//temp//output.txt");
		sv.close();
	}
}