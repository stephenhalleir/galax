package external_systems.mobile_network.nodes.ec20.tap_files;

import java.util.ArrayList;

import utilities.generic.time.Timestamp;

public class TapFile {

	private ArrayList<TapRecord> rows;
	private String filename;
	
	public TapFile() {
		filename=Timestamp.getCurrentTimestamp("yyyyMMdd.HHmmss") + "CDESPTEIRLME" + System.currentTimeMillis() + ".voice.new.gz";
		rows=new ArrayList<TapRecord>();
	}
	
	public String toString() {
		String contents = "";
		
		for(TapRecord row:rows) {
			contents = contents + row + "\n";
		}
		
		return contents.trim();
	}

	public ArrayList<TapRecord> getRows() {
		return rows;
	}

	public void setRows(ArrayList<TapRecord> rows) {
		this.rows = rows;
	}

	public String getFilename() {
		return filename;
	}

	public void setFilename(String filename) {
		this.filename = filename;
	}
}
