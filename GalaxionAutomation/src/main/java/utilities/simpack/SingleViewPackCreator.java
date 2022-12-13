package utilities.simpack;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.math.BigInteger;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import microservices.backend.eir_inventory_management_backend.objects.EquipmentPack;
import microservices.backend.eir_provisioning_facade_backend.utilities.DBLoader;
import utilities.generic.files.TextReader;
import utilities.generic.time.WaitUtil;

public class SingleViewPackCreator {

	//private String startMsisdn;
	//private String endMsisdn;
	private String command;
	private BigInteger quantity;
	private String output;
	private String tariffCode = "1513";
	private String environment;
	//private String inpFileLocation;
	private String awkFileLocation;
	private String simManufacturingResponseLocation;
	private String simAllotmentLocation;
	private ArrayList<String> packs;
	private SV_SSH_Utility sv;
	private String capcFileLocation;
	private List<String> commands;
	private String packType = "1SIMBILLA";
	private boolean runningOk = true;
	private SV_DB_Utility dao;

	public SingleViewPackCreator(String env) {

		environment = env;
		//inpFileLocation = "/isv/" + user
		//		+ "/data/server/sim_manufacturing/meteor/pair/transfer/";
		awkFileLocation = "/isv/" + env + "/work/lmooney/";
		simManufacturingResponseLocation = "/isv/" + env
				+ "/data/server/sim_manufacture_response/transfer/";
		simAllotmentLocation = "/isv/" + env
				+ "/data/server/sim_allotment/transfer/";
		capcFileLocation = "/isv/" + env + "/data/server/capc/transfer/";
		dao = new SV_DB_Utility(env);
		packs = new ArrayList<String>();
	}
	
	public String getSimAllotmentLocation(){
		return simAllotmentLocation;
	}
	
	public void startSSH(){
		sv = new SV_SSH_Utility(environment);
	}
	
	public boolean sshConnectedOk(){
		return sv.isConnectedOk();
	}
	
	public String getCapcFileLocation(){
		return capcFileLocation;
	}
	
	public String getBatchIDForTask(String taskID){
		return dao.getBatchIDForTask(taskID);
	}
	
	
	public boolean openDBConnection(){
		return dao.openDBConnection();
	}
	
	public String getNextMsisdn() {
		// get a new commands list
		commands = new ArrayList<String>();

		command = "cat /tmp/AutoProv/" + environment.toUpperCase()
				+ "/msisdn_counter_" + environment.toLowerCase() + ".txt";
		commands.add(command);

		// execute the commands and return the output text
		output = sv.executeCommands(commands);

		// parse the output text from the task to determine the task ID
		for (int i = 0; i < output.split("\n").length; i++) {
			if (output.split("\n")[i].contains("087")) {
				return output.split("\n")[i].substring(2).trim();
			}
		}
		
		return null;
	}

	/*
	 * Method to run the "Generate & Load MSISDNs" CB server task via SSH
	 */
	public String runGenerateAndLoadMSISDNs(String startMsisdn, String endMsisdn, int configItem) {
		/*
		// determine the quantity of packs required, based on the difference
		// between the start & end MSISDNs specified
		*/

		// get a new commands list
		commands = new ArrayList<String>();
		String taskID = null;
		boolean idFound = false;

		// Add Task 1: Generate and Load MSISDNs to the commands list
		command = "runtask 'Generate & Load MSISDNs' $startMsisdn $endMsisdn $configItem 1";
		command = command.replace("$startMsisdn", startMsisdn).replace("$endMsisdn",endMsisdn).replace("$configItem", Integer.toString(configItem));
		commands.add(command);

		// execute the commands and return the output text
		output = sv.executeCommands(commands);

		// parse the output text from the task to determine the task ID
		for (int i = 0; i < output.split("\n").length && !idFound; i++) {
			if (output.split("\n")[i].contains("Inserted task ")) {
				taskID = output.split("\n")[i].substring(23).trim();
				idFound = true;
			}
		}

		// return the task ID for the task
		return taskID;
	}

	/*
	 * Method to run the task "Generate SIMs & Manufacturing Request" on the CB
	 * server via SSH
	 */
	public String runGenerateSIMsAndManufacturingRequest(int quantity, String startMsisdn, String endMsisdn, int inventoryPool, String configItem) {
		String taskID = null;
		boolean idFound = false;

		// get a new commands list
		commands = new ArrayList<String>();

		// Add Task 2: Generate SIMs & Manufacturing Request to the commands
		// list
//		command = "runtask 'Generate SIMs & Manufacturing Request' " + quantity
//				+ " 1 1 " + startMsisdn + " " + endMsisdn
//				+ " 1172 'METEOR_PAIR_ALL_IN_ONE_LTE'";
		
		command = "runtask 'Generate SIMs & Manufacturing Request' $quantity $inventoryPool 1 $startMsisdn $endMsisdn 1172 '$configItem'";
		command = command.replace("$quantity", Integer.toString(quantity));
		command = command.replace("$inventoryPool", Integer.toString(inventoryPool));
		command = command.replace("$startMsisdn", startMsisdn);
		command = command.replace("$endMsisdn", endMsisdn);
		command = command.replace("$configItem", configItem);
		
		commands.add(command);

		// execute the commands and return the output text
		output = sv.executeCommands(commands);

		// parse the output text from the task to determine the task ID
		for (int i = 0; i < output.split("\n").length && !idFound; i++) {
			if (output.split("\n")[i].contains("Inserted task ")) {
				taskID = output.split("\n")[i].substring(23).trim();
				idFound = true;
			}
		}
		return taskID;
	}

	/*
	 * Method to read the name of the .inp file generated by the task
	 * "Generate SIMs & Manufacturing Request"
	 */
	public String getInpFilename(String inpFileLocation, String startMsisdn) {
		boolean inpFound = false;
		String filename = null;

		// get a new commands list
		commands = new ArrayList<String>();

		// add the grep command to the commands list
		commands.add("grep " + startMsisdn + " " + inpFileLocation + "*");

		// execute the commands and return the output text
		String output = sv.executeCommands(commands);

		// read out the file name from the grep response
		String[] lines = output.split("\n");

		// for each line in the response, look for the reference to the ".inp"
		// file
		for (int i = 0; i < lines.length && !inpFound; i++) {

			// if this line contains the .inp file name read out the filename
			if (lines[i].contains(".inp:")) {
				filename = lines[i].substring(lines[i].lastIndexOf("/") + 1,
						lines[i].indexOf(":"));
				inpFound = true;
			}
		}

		// return the .inp file name
		return filename;
	}

	/*
	 * Method to execute the .awk command on the server, converting the .inp
	 * file to a corresponding .out file
	 */
	public String runAwkCommand(String inpFilename) {
		// get a new commands list
		commands = new ArrayList<String>();

		// the .out file name will match the .inp file name, just with a
		// different extension
		//String outFilename = inpFilename.replace(".inp", ".out");
		File outputFile = new File(inpFilename.replace(".inp", ".out"));
		System.err.println(outputFile.getName());
		
		String outputLocation="/isv/$env/data/server/sim_manufacture_response/transfer/";
		outputLocation = outputLocation.replace("$env", environment);
		
		String outFilename=outputLocation + outputFile.getName();
		
		// Add the .awk script to the commands list
		String awkFileString = "awk -f " + awkFileLocation
				+ "GenSIMOutFile.awk " + inpFilename + " > "
				+ outFilename;
		commands.add(awkFileString);

		// execute the .awk command via SSH to generate the .out file
		sv.executeCommands(commands);
		return outFilename;
	}

	/*
	 * Method to run the task "Process SIM & Manufacturing Request" on the CB
	 * server via SSH
	 */
	public String processSimAndManufacturingRequest(String outFilename) {
		boolean idFound = false;
		String taskID = null;
		// get a new commands list
		commands = new ArrayList<String>();

		// Add Task 3: Process SIM & Manufacturing Request to the commands list
		String command = "runtask 'Process SIM Manufactured File' "
				+ outFilename + " 1174";
		commands.add(command);

		// execute the commands and return the output text
		output = sv.executeCommands(commands);

		// parse the output text from the task to determine the task ID
		String[] responseLines = output.split("\n");
		for (int i = 0; i < responseLines.length && !idFound; i++) {
			if (responseLines[i].contains("Inserted task")) {
				taskID = responseLines[i].substring(23).trim();
				idFound = true;
			}
		}
		return taskID;
	}

	/*
	 * Method to run the task "Allot SIMs" on the CB server via SSH
	 */
	public String allotSimsForPackCreation(String batchID,String quantity, int allotSIMsInventoryPool) {

		// get a new commands list
		commands = new ArrayList<String>();
		boolean idFound = false;
		String taskID = null;

		// Add Task 4: Allot SIMs to the commands list
		String command = "runtask 'Allot SIM Cards' $inventoryPool 'BatchID " + batchID
				+ "' " + quantity + " 2 0 '' 0 '' '' 1";
		command=command.replace("$inventoryPool", Integer.toString(allotSIMsInventoryPool));
		commands.add(command);

		// execute the commands and return the output text
		output = sv.executeCommands(commands);

		// parse the output text from the task to determine the task ID
		for (int i = 0; i < output.split("\n").length && !idFound; i++) {
			if (output.split("\n")[i].contains("Inserted task ")) {
				taskID = output.split("\n")[i].substring(23).trim();
				idFound = true;
			}
		}
		return taskID;
	}

	/*
	 * Method to run Allot SIMs for CAPC
	 */
	public String allotSimsForCAPC(String batchID, String quantity, String tariffCode) {
		commands = new ArrayList<String>();
		boolean idFound = false;
		String taskID = null;

		String command = "runtask 'Allot SIM Cards' 1 'BatchID " + batchID
				+ "' " + quantity + " 1 0 " + tariffCode + " 0 '' 1 1";
		commands.add(command);
		output = sv.executeCommands(commands);
		for (int i = 0; i < output.split("\n").length && !idFound; i++) {
			if (output.split("\n")[i].contains("Inserted task ")) {
				taskID = output.split("\n")[i].substring(23).trim();
				idFound = true;
			}
		}
		sv.close();
		return taskID;
	}

	public String getResultsForTask(String taskID){
		return dao.getResultsForTask(taskID);
	}
	
	
	/*
	 * Method to read a .int file line by line and create the corresponding DEL
	 * file
	 */
	public String generateDelForxxxIntFile(String dir,String filename) {
		String line = "";
		int numRecords = 0;

		// The DEL file will have the same digits as the FRD name, with 2
		// additional 0's added to make it the correct length
		String delFilename = filename.replace("FRD", "DEL").replace(".int", "")
				+ "00";
		System.out.println("Generating DEL file " + delFilename + " for int file "+ dir + filename);
		try {
			// Create the new DEL file
			PrintWriter writer = new PrintWriter(dir+delFilename, "UTF-8");
			PrintWriter writer2 = new PrintWriter(dir+delFilename+".csv", "UTF-8");
			String content = "";
			String txtContent="";

			// open the FRD file
			BufferedReader br = new BufferedReader(new FileReader(dir+filename));

			// while there's lines in the FRD file, translate to DEL format and
			// add to the DEL content string
			while ((line = br.readLine()) != null) {
				System.out.println("Line found "+line);
				if (!line.contains(">")) {
					numRecords++;
					String[] fields = line.split(",");
					String delString = "00001 001 "
							+ fields[3].replace("\"", "").replace("F", "")
							+ " " + fields[4].trim() + " " + "9488 00009488 "
							+ packType + fields[4].substring(3).trim()
							+ " 101EMS01EBLAN501 " + fields[6] + " 0000000";
					
					content += "\n" + delString;

					// add the pack information to the packs list to display at
					// the end
					packs.add(delString);
					System.err.println("Content = "+delString);
					txtContent=txtContent+packType + fields[4].substring(3).trim()+","+fields[4].trim()+","+fields[5].trim()+","+delString.split(" ")[4]+"\n";
				}
			}

			// add the file header to the content String, and then append the
			// content
			content = "<NUMBER_OF_RECORDS>" + numRecords + content;

			// write the content to the DEL file
			writer.println(content);
			writer2.println("SIM Pack ID, MSISDN, IMSI, Pin\n"+txtContent);

			// close the writer/reader objects
			writer.close();
			writer2.close();
			br.close();

		} catch (IOException e) {

		}
		return delFilename;
	}

	/*
	 * Method to run the task "Process SIGMA Delivery (Dealers and Stores) File"
	 * on the CB server via SSH
	 */
	public String processDelFile(String delFilename) {
		boolean idFound = false;
		String taskID = null;
		commands = new ArrayList<String>();

		String command = "runtask 'Process SIGMA Delivery (Dealers and Stores) File' "
				+ delFilename;
		commands.add(command);

		// execute the commands and return the output text
		output = sv.executeCommands(commands);

		// parse the output text from the task to determine the task ID
		for (int i = 0; i < output.split("\n").length && !idFound; i++) {
			if (output.split("\n")[i].contains("Inserted task ")) {
				taskID = output.split("\n")[i].substring(23).trim();
				idFound = true;
			}
		}
		return taskID;
	}

	// wait/delay for a specified number of seconds
	public void waitSeconds(int num) {
		try {
			Thread.sleep(num * 1000);
		} catch (InterruptedException ex) {
			Thread.currentThread().interrupt();
		}
	}

	public boolean createSIMs(int amount, String brand, boolean unpair) {
		
		// convert the quantity to a BigInteger
		quantity = new BigInteger(Integer.toString(amount));
		
		// read in the next available MSISDN (start msisdn)
		String startMsisdn = getNextMsisdn();
		
		// calculate the end MSISDN
		String endMsisdn = new BigInteger(startMsisdn).add(quantity).toString();
		
		// if the endMsisdn does not start with a "0", add the "0"
		if(endMsisdn.length()==9 && endMsisdn.startsWith("8")) {
			endMsisdn="0"+endMsisdn;
		}

		// create an empty list for the pack IDs
		packs = new ArrayList<String>();
		commands = new ArrayList<String>();

		// Create a new access object for the Singl.eView database
		SV_DB_Utility dao = new SV_DB_Utility(environment);
		sv = new SV_SSH_Utility(environment);

		// determine the config items
		int generateMsisdnsInventoryPool=-1;
		int generateSIMsInventoryPool=-1;
		int allotSIMsInventoryPool=-1;
		String generateSIMsConfigItem=null;
		String simManufacturingDirectory=null;
		
		if(brand.equalsIgnoreCase("GoMo")) {
			generateMsisdnsInventoryPool=8;
			generateSIMsInventoryPool=8;
			allotSIMsInventoryPool=8;
			generateSIMsConfigItem="ION_PAIR_MULTISIM";
			simManufacturingDirectory="/isv/$env/data/server/sim_manufacturing/ion/pair/transfer/";
		}
		else if(brand.equalsIgnoreCase("Meteor")) {
			generateMsisdnsInventoryPool=1;
			generateSIMsInventoryPool=1;
			allotSIMsInventoryPool=1;
			generateSIMsConfigItem="METEOR_PAIR_MULTISIM";
			simManufacturingDirectory="/isv/$env/data/server/sim_manufacturing/meteor/pair/transfer/";
		}
		else {
			System.err.println("Error: Unable to derive config items from brand " + brand);
			return false;
		}
		
		simManufacturingDirectory=simManufacturingDirectory.replace("$env",environment.toLowerCase());
		
		
		// Run the task "Generate and Load MSISDNs" and return the task ID
		String genLoadMsisdnsId = this.runGenerateAndLoadMSISDNs(startMsisdn, endMsisdn, generateMsisdnsInventoryPool);
		System.out.println("Generate and Load MSISDNs: Task ID = "
				+ genLoadMsisdnsId);

		// check whether the program is ok to proceed to the next step
		if (genLoadMsisdnsId.trim().equals("") || genLoadMsisdnsId == null) {
			System.out.println("Exiting as task id = " + genLoadMsisdnsId);
			runningOk = false;
		}
		else{
			this.updateMsisdnCounter(startMsisdn,endMsisdn);
		}

		String genSimManReqId = null;
		
		
		if (runningOk) {
			// Run the task "Generate SIM & Manufacturing Request" and return
			// the task ID
			genSimManReqId = runGenerateSIMsAndManufacturingRequest(quantity.intValue(), startMsisdn,endMsisdn, generateSIMsInventoryPool,generateSIMsConfigItem);
			System.out
					.println("Generate SIM & Manufacturing Request: Task ID = "
							+ genSimManReqId);
		}

		// check whether the program is ok to proceed to the next step
		if (genLoadMsisdnsId.trim().equals("") || genLoadMsisdnsId == null) {
			System.err.println("Exiting as task id = " + genSimManReqId);
			runningOk = false;
		}

		// wait for 5 seconds to allow the database record to be written
		waitSeconds(5);

		String batchID = null;
		if (runningOk) {
			// read out the ID of the SIM batch created for this order
			
			long endTime = System.currentTimeMillis() + 30000;
			
			while(batchID==null && System.currentTimeMillis() < endTime) {
				batchID = dao.getBatchIDForTask(genSimManReqId);
				WaitUtil.waitForSeconds(5);
			}
			
			
			System.out.println("Batch ID for task " + genSimManReqId + " = "
					+ batchID);
		}

		try {
			// check whether the program is ok to proceed to the next step
			if (batchID.trim().equals("") || batchID == null) {
				System.err.println("Exiting as batchId = " + batchID);
				runningOk = false;
			}
		} catch (NullPointerException e) {
			System.err.println("Exiting as batchId is null");
			runningOk = false;
			return false;
		}

		String inpFilename = null;

		// read out the name of the .inp file created for this order
		if (runningOk) {
			inpFilename = getInpFilename(simManufacturingDirectory,startMsisdn);
			System.out.println(".inp filename = " + inpFilename);
			// wait for 5 seconds to allow the database record to be written
			waitSeconds(5);
		}

		try {
			// check whether the program is ok to proceed to the next step
			if (inpFilename.equals("")) {
				System.err
						.println("Exiting as inpFilename id = " + inpFilename);
				runningOk = false;
			}
		} catch (NullPointerException e) {
			System.err.println("Exiting as inpFilename id is null");
			runningOk = false;
		}

		String newFile=null;
		
		if (runningOk) {
			
			String simManufacturedResponseDirectory="/isv/$env/data/server/sim_manufacture_response/transfer";
			simManufacturedResponseDirectory = simManufacturedResponseDirectory.replace("$env", environment);
			
			// run the .awk file on the .inp file to generate the .out file
			String outFile = runAwkCommand(simManufacturingDirectory + inpFilename);
			System.out.println(".out file found at " + outFile);
			
			// download and fix the .out file
			SV_FTP_Utility ftp = new SV_FTP_Utility(environment);
			String localFile = ftp.retrieveFile(outFile, "generated_Logistic_files/SingleView");
			newFile = DBLoader.fix(localFile);

			// send the new .out file to the server (tmp directory)
			ftp.sendFile(newFile, "/tmp/");

			commands = new ArrayList<String>();
			
			// move the fixed .out file to the sim_manufacture_response folder
			commands.add("cp /tmp/" + new File(newFile).getName() + " " + simManufacturedResponseDirectory);
			System.out.println("File downloaded " + localFile);			
			output = sv.executeCommands(commands);
		}

		
		String s = null;

		if (runningOk) {
			// run the task "Process SIM & Manufacturing Request" on the .out
			// file
			// and return the task ID
			s = processSimAndManufacturingRequest(new File(newFile).getName());
			System.out
					.println("Process SIM & Manufacturing Request: Task ID = "
							+ s);
		}

		
		// run the allot SIMs task and return the task ID
		String allotSimsId = null;

		if (runningOk) {
			
			// Run the 'Allot SIMs' task and return the batch ID
			allotSimsId = allotSimsForPackCreation(batchID,quantity.toString(),allotSIMsInventoryPool);
			System.out.println("Allot SIMs: Task ID = " + allotSimsId);
			
			// wait for 5 seconds to allow the database record to be written
			waitSeconds(5);
		}

		try {

			// check whether the program is ok to proceed to the next step
			if (allotSimsId.trim().equals("")) {
				System.err
						.println("Exiting as allotSimsId id = " + allotSimsId);
				runningOk = false;
			}
		} catch (NullPointerException e) {
			System.err.println("Exiting as allotSimsId id is null");
			runningOk = false;
		}

		String intFilename = null;

		if (runningOk) {
			// read out the name of the created .int file from the database
			intFilename = dao.getIntFilenameforAllotSimsTaskID(allotSimsId);
			System.err.println("File created: " + intFilename);
		}

		try {
			// check whether the program is ok to proceed to the next step
			if (intFilename.trim().equals("")) {
				System.err
						.println("Exiting as intFilename id = " + intFilename);
				runningOk = false;
			}
		} catch (NullPointerException e) {
			System.err.println("Exiting as intFilename id is null");
			runningOk = false;
		}

		SV_FTP_Utility fileWriter = null;

		String localIntFilename="generated_Logistic_files/SingleView/" + environment.toUpperCase() + "_" + brand.toUpperCase() + "_" + intFilename;
		
		if (runningOk) {
			// retrieve the .int file from the Singl.eView server
			fileWriter = new SV_FTP_Utility(environment);
			fileWriter.retrieveFile(simAllotmentLocation + intFilename,	localIntFilename);
		}
		
		System.out.println("Packs Created:\n=================");
		
		//String intContent = TextReader.getContent(localIntFilename);
		ArrayList<EquipmentPack> packs = getPacksFromIntFile(localIntFilename);

		for(EquipmentPack pack:packs) {
			System.err.println(pack);
		}

		if(unpair) {
			String startIccid=packs.get(0).getIccid();
			String endIccid=packs.get(packs.size()-1).getIccid();
			
			String query="update equipment_history\r\n" + 
					"set general_3=''\r\n" + 
					"where serial_number between '$startIccid' and '$endIccid'\r\n" + 
					"and sysdate between effective_start_date and effective_end_date";
			query=query.replace("$startIccid", startIccid).replace("$endIccid",endIccid);
			
			System.out.println(query);
			
			try {
				dao.executeUpdateQuery(query);
				System.err.println("*** Packs are now unpaired ***");
			} catch (SQLException e) {
				try {
					dao.closeConnections();
				} catch (SQLException e1) {
					e1.printStackTrace();
				}
				e.printStackTrace();
			}
		}
/*
		String delFilename = null;
		if (runningOk) {
			// read the .int file and generate the DEL file
			delFilename = generateDelForIntFile("",intFilename);
			System.out.println(delFilename + " created");
			String location = "/isv/" + environment
					+ "/data/server/Sigma/DEL/transfer/" + delFilename;
			// send the DEL file to the server
			fileWriter.sendFile(delFilename, location);
		}
		

		// check whether the program is ok to proceed to the next step

		try {
			if (delFilename.trim().equals("") || delFilename == null) {

				System.err
						.println("Exiting as delFilename id = " + delFilename);
				runningOk = false;
			}
		} catch (NullPointerException e) {
			System.err.println("Exiting as delFilename is null");
			runningOk = false;
		}

		// run the task "Process SIGMA Delivery (Dealers and Stores) File" to
		// process the DEL file
		String processTaskID = null;

		if (runningOk) {
			processTaskID = processDelFile(delFilename);
			System.out
					.println("Process SIGMA Delivery (Dealers and Stores) File: Task ID = "
							+ processTaskID);
		}

		try {
			// check whether the program is ok to proceed to the next step
			if (processTaskID.trim().equals("")) {
				System.err.println("Exiting as processTaskID id = "
						+ processTaskID);
				runningOk = false;
			}
		} catch (NullPointerException e) {
			System.err.println("Exiting as processTaskID id is null");
			runningOk = false;
		}
		

		if (runningOk) {
			// Print the Pack ID information
			for (String pack : packs) {
				System.err.println("added" + pack);
				System.out.println("Pack ID created: " + pack.split(" ")[3]
						+ " / " + pack.split(" ")[6]);
			}
		}
		*/

		sv.close();
		
		System.out.println("Complete!");
		return true;
	}

	
	public ArrayList<String> getPacks(){
		return packs;
	}
	
	public String getScheduleIdForAllotSIMs(String allotSimsId){
		return dao.getScheduleIdForAllotSIMs(allotSimsId);
	}
	
	public String getTaskIDforScheduleID(String scheduleID){
		return dao.getTaskIDforScheduleID(scheduleID);
	}
	
	public String getIntFilenameforTaskID(String taskId){
		return dao.getIntFilenameforTaskID(taskId);
	}
	// update the MSISDN counter file to contain the next available MSISDN after this batch
	public String updateMsisdnCounter(String startMsisdn, String endMsisdn){
		commands = new ArrayList<String>();

		// cd to the file directory
		String command = "cd /tmp/AutoProv/$environment";
		command = command.replace("$environment", environment.toUpperCase());
		commands.add(command);
		
		// set the start msisdn for the next batch to be the end msisdn for this batch + 1
		BigInteger nextMsisdn=new BigInteger(endMsisdn).add(new BigInteger("1"));
		
		// run the command to replace the file's current counter with the new value, saving the output in a new (temp) file
		command = "sed -e 's/"+ startMsisdn+"/0"+nextMsisdn+"/g' msisdn_counter_"+environment.toLowerCase()+".txt > msisdn_counter_"+environment.toLowerCase()+"_temp.txt";
		commands.add(command);
		System.out.println("Command 2: " + command);

		// remove the original file
		command = "rm msisdn_counter_"+environment.toLowerCase()+".txt";
		commands.add(command);
		System.out.println("Command 3: " + command);
		
		// rename the temp file to replace the original file
		command = "mv msisdn_counter_"+environment.toLowerCase()+"_temp.txt msisdn_counter_"+environment.toLowerCase()+".txt";
		commands.add(command);
		
		// execute the commands via SSH
		output = sv.executeCommands(commands);
		
		return nextMsisdn.toString();
	}
	
	public ArrayList<EquipmentPack> getPacksFromIntFile(String intLocation){
		String intContent = TextReader.getContent(intLocation);
		
		ArrayList<EquipmentPack> packs = new ArrayList<EquipmentPack>();
		
		String[] intLines=intContent.split("\n");
		for(int i=3; i<intLines.length;i++) {
			
			EquipmentPack pack = new EquipmentPack();
			
			// read the line of the int file
			String line = intLines[i];
			
			// split the line up by commas and build the pack
			String[] fields = line.split(",");
			pack.setIccid(fields[3].substring(1,19));
			pack.setImsi(fields[5]);
			pack.setMsisdn(fields[4]);
			
			// add the pack to the list
			packs.add(pack);
		}
		
		return packs;
	}
	
	// main method
	public static void main(String[] args) throws SQLException, IOException {

		SingleViewPackCreator creator = new SingleViewPackCreator("isvtst01");
		creator.startSSH();
		
		// accept "gomo" or "meteor" for now
		creator.createSIMs(100, "meteor",true);
	}
}