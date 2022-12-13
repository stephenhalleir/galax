package microservices.backend.tecrep.utility.equipment_update_file;

import utilities.generic.time.Timestamp;

public class EquipmentUpdateFileGenerator {

	public static String generateEquipmentUpdateFile(String msisdn, String iccid, int action) {
		String template = msisdn + "," + iccid + "," + action + ",";
		String filename="EQUIP_STATUS_CHG_" + Timestamp.getCurrentTimestamp("ddMMyyHHmmss") + "_R6.csv_R6";
		System.out.println(template + "\n" + filename);
		return null;
	}
	
}
