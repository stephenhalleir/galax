package external_systems.mobile_network.nodes.ec20.tap_files;

import utilities.generic.time.Timestamp;

public class TapRecord {

	private String field1;
	private String field2;
	private String field3;
	private String field4;
	private String field5;
	private String field6;
	private String field7;
	private String field8;
	private String field9;
	private String field10;
	private String field11;
	private String field12;
	private String field13;
	private String field14;
	private String field15;
	private String field16;
	private String field17;
	private String field18;
	private String field19;
	private String field20;
	private String field21;
	private String field22;
	

	public TapRecord() {
		field1="680185756";
		field2="0";
		field3=Timestamp.getCurrentTimestamp("yyyy-MM-dd");
		field4=Timestamp.getCurrentTimestamp("hh:mm:ss");
		field5="66";
		field6="353852610265";
		field7="272034010359137";
		field8="356557089969400";
		field9="ESPTE";
		field10="0872846548";
		field11="+353872846548";
		field12="";
		field13="";
		field14="";
		field15="";
		field16="";
		field17="";
		field18="";
		field19="";
		field20="";
		field21="";
		field22="";
	}
	
	

	public String toString() {
		String[] fields = { field1, field2, field3, field4, field5, field6, field7, field8, field9, field10, field11, field12,field13, field14, field15, field16, field17, field18, field19, field20, field21, field22};
		String s = fields[0];
		for (int i = 1; i < fields.length; i++) {
			s = s + "," + fields[i];
		}

		return s;
	}
}