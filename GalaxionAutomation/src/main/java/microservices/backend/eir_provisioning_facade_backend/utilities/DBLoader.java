package microservices.backend.eir_provisioning_facade_backend.utilities;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

import external_systems.mmw.MMWUtility;
import external_systems.mobile_network.nodes.spr.SPRProfile;
import external_systems.singleview.SingleViewDAO;
import utilities.generic.database.GalaxionDBUtil;

public class DBLoader {

	public static void loadIntFileToDB(String file, boolean migrateSPR) {

		BufferedReader reader;
		try {
			reader = new BufferedReader(new FileReader(file));

			String line = reader.readLine();

			String contents = "";

			// while lines are found
			while (line != null) {

				// skip the lines with <tags>
				if (!line.startsWith("<")) {

					System.out.println(line);
					String[] attributes = line.split(",");

					// create a new row in the network table
					String query1 = "INSERT INTO `eir-provisioning-facade-backend`.network VALUES (NULL);";
					try {
						GalaxionDBUtil.runUpdateQuery(query1);
					} catch (SQLException e) {
						e.printStackTrace();
					}

					int lastId = -1;

					ResultSet rs;
					try {
						rs = GalaxionDBUtil.getQueryResultSet("select MAX(id) as last_id from `eir-provisioning-facade-backend`.network LIMIT 1;");

						// get the ID of the newly added row
						while (rs.next()) {
							try {
								lastId = rs.getInt("last_id");
							} catch (SQLException e) {
								e.printStackTrace();
							}
						}
					} catch (SQLException e1) {
						e1.printStackTrace();
					}

					// insert the SIM values into the new row
					String query2 = "INSERT INTO `eir-provisioning-facade-backend`.batch (msisdn, imsi, network_id, `status`, `catalog_offer_code`, created_date_time)"
							+ " VALUES ('$msisdn', '$imsi', $lastId, 'PREACTIVE', '$catalog_offer_code', '$timestamp');";

					// get time stamp
					String pattern = "yyyy-MM-dd HH.mm.ss";
					SimpleDateFormat simpleDateFormat = new SimpleDateFormat(pattern);
					String date = simpleDateFormat.format(new Date());

					String iccid = attributes[3].replace("\"", "");
					String msisdn = attributes[4].replace("\"", "");

					String catalogOfferCode = null;
					String tariffPlan = attributes[10].replace("\"", "");

					if (tariffPlan.equalsIgnoreCase("Prepaid Anytime Perfect")) {
						catalogOfferCode="PAYG_SIMP";
					} else if (tariffPlan.equalsIgnoreCase("Prepaid MBB")) {
						catalogOfferCode="PAYG_MBB";
					} else {
						// some default value
						catalogOfferCode="PAYG_SIMP";
					}

					query2 = query2.replace("$msisdn", attributes[4].replace("\"", "")).replace("$imsi", attributes[5].replace("\"", ""))
							.replace("$timestamp", date).replace("$lastId", "" + lastId).replace("$catalog_offer_code", catalogOfferCode);
					System.out.println(query2);

					try {
						GalaxionDBUtil.runUpdateQuery(query2);
					} catch (SQLException e) {
						e.printStackTrace();
					}
					System.out.println("Record loaded into database: " + line);

					// migrate on SPR, if required
					if (migrateSPR) {

						// get the network ID from the batch table and use it to provision to the SPR
						String query = "SELECT * FROM `eir-provisioning-facade-backend`.batch where msisdn='$msisdn';";
						query = query.replace("$msisdn", msisdn);
						try {
							rs = GalaxionDBUtil.getQueryResultSet(query);
							if (rs.next()) {
								int netID = rs.getInt("network_id");
								migrateToIon(msisdn, netID);
							}
						} catch (SQLException e) {
							e.printStackTrace();
						}

					}
				}

				contents = contents + "\n" + line;

				// read next line
				line = reader.readLine();
			}
			System.err.println(contents);
			reader.close();
		} catch (IOException e) {
			e.printStackTrace();
		}

	}
	
	public static void migrateToIon(String msisdn, int networkId) {

		if (msisdn.startsWith("08")) {
			msisdn = msisdn.replaceFirst("08", "3538");
		}
		
		// build the profile based on the current SPR values
		SPRProfile sprProfile = MMWUtility.getSprProfile(msisdn);
		System.err.println(sprProfile.getImsi());

		if (sprProfile.isRetrievalOk()) {
			System.out.println("Retrieved SPR details for " + msisdn + ", " + sprProfile.getImsi());

			// if the network ID is found, remove the records from the SPR
			if (networkId != -1) {
				System.out.println("Found network_id: " + networkId);

				String r = MMWUtility.terminateSPRSubscriber(msisdn);
				System.out.println("Terminate SPR response: " + r);

				// if the delete is successful, recreate the record
				if (r.contains("Sucess")) {
					System.err.println(MMWUtility.recreateAsIon(Integer.toString(networkId),msisdn,sprProfile.getImsi(),sprProfile.getPricePlan(),sprProfile.getCfsp()));
				}
			} else {
				System.out.println("NetworkID not found in BATCH table for " + msisdn);
			}
		} else {
			System.err.println("Subscriber " + msisdn + " not found in SPR");
		}
	}

	public static String fix(String file) {

		// this will fix a .out file to remove the newline character before the ki
		ArrayList<String> lines = new ArrayList();
		lines.add("");
		ArrayList<String> newLines = new ArrayList();

		BufferedReader reader;
		try {
			reader = new BufferedReader(new FileReader(file));

			String line = reader.readLine();

			String contents = "";

			while (!line.startsWith("2")) {
				newLines.add(line);
				System.out.println("Skipping line: " + line);
				line = reader.readLine();
			}

			while (line != null) {
				System.out.println("Processing line: " + line);
				lines.add(line);
				line = reader.readLine();
			}

			System.out.println(lines.size());

			for (int i = 1; i < lines.size(); i++) {
				if (lines.get(i).startsWith(" 8")) {
					String newLine = lines.get(i - 1) + lines.get(i);
					newLines.add(newLine);
				} else if (lines.get(i).length() > 100) {
					newLines.add(lines.get(i));
				}
			}

			File fout = new File(file.replace(".out", "_2.out"));
			FileOutputStream fos = new FileOutputStream(fout);

			BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(fos));

			for (String s : newLines) {
				bw.write(s.trim());
				bw.newLine();
			}

			bw.close();

			return fout.getAbsolutePath();

		} catch (IOException e) {
			e.printStackTrace();
		}

		return null;

	}

}
