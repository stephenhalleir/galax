package microservices.backend.tecrep.equipments_management.files;

import java.util.ArrayList;

import utilities.generic.files.TextReader;

public class OutFile {
	private String header;
	private ArrayList<OutRow> rows;
	
	public OutFile(InpFile inpFile) {
		
		rows = new ArrayList<OutRow>();
		
		header = TextReader.getContent("templates\\tecrep\\out_file_header");
		header = header.replace("$batchNumber", Integer.toString(inpFile.getBatch()));
		header = header.replace("$quantity",Integer.toString(inpFile.getRows().size()));
		
		for(InpRow inpRow:inpFile.getRows()) {
			rows.add(new OutRow(inpRow.getImsi(),inpRow.getIccid(),"         "));
		}
	}
	
	public OutFile(InpFile inpFile,ArrayList<String>msisdns) {
		
		rows = new ArrayList<OutRow>();
		
		header = TextReader.getContent("templates\\tecrep\\out_file_header");
		header = header.replace("$batchNumber", Integer.toString(inpFile.getBatch()));
		header = header.replace("$quantity",Integer.toString(inpFile.getRows().size()));
		
		int i=0;
		for(InpRow inpRow:inpFile.getRows()) {
			rows.add(new OutRow(inpRow.getImsi(),inpRow.getIccid(),msisdns.get(i)));
			i++;
		}
	}
	
	public String toString() {
		String contents = header;
		
		for(OutRow outRow:rows) {
			contents = contents + "\n" + outRow;
		}
		
		return contents;
	}
}
