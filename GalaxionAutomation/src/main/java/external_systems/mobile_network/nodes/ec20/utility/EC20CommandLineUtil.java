package external_systems.mobile_network.nodes.ec20.utility;

import java.util.ArrayList;
import java.util.List;

import utilities.generic.files.TextReader;
import utilities.network.SSHUtility;

public class EC20CommandLineUtil {

	private static String section;
	
	public static void process(String line, String section) {
		System.out.println(section + ":" + line);
	}
	
	public static void main(String [] args) {
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

		
		String txtFile="files//temp//output.txt";
		TextReader.writeFile(s, txtFile);
		ArrayList<String> lines = TextReader.getContentAsArrayList(txtFile);
		
		for(String line:lines) {
			if(!line.startsWith("+")  && !line.trim().equals("")) {
				if(line.contains("SUBSCRIBER DETAILS")) {
					section="SUBSCRIBER DETAILS";
				}
				else if(line.contains("PAM DETAILS")) {
					section="PAM DETAILS";
				}
				else if(line.contains("OFFER DETAILS")) {
					section="OFFER DETAILS";
				}
				process(line,section);
			}
			
		}
		
		sv.close();
	}
}
