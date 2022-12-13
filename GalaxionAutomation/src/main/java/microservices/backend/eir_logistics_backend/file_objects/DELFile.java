package microservices.backend.eir_logistics_backend.file_objects;

import java.util.ArrayList;

public class DELFile {

	private String filepath;
	private ArrayList<DELLine> delLines;
	private boolean includeHeader;

	public DELFile(String fileName, ArrayList<DELLine> delLines) {
		super();
		this.filepath = fileName;
		this.delLines = delLines;
		this.includeHeader=false;
	}

	public DELFile() {
		super();
		this.delLines = new ArrayList<DELLine>();
		this.includeHeader=false;
	}

	public String getFilepath() {
		return filepath;
	}

	public void setFilepath(String filepath) {
		this.filepath = filepath;
	}

	public ArrayList<DELLine> getDelLines() {
		return delLines;
	}

	public void setDelLines(ArrayList<DELLine> delLines) {
		this.delLines = delLines;
	}
	
	public boolean isIncludeHeader() {
		return includeHeader;
	}

	public void setIncludeHeader(boolean includeHeader) {
		this.includeHeader = includeHeader;
	}

	public String toString() {
		String contents = "";
		
		if(includeHeader) {
			contents = "<NUMBER_OF_RECORDS>" + delLines.size();
		}
		
		for(DELLine line:delLines) {
			contents = contents + "\n" + line.toString();
		}
		
		return contents.trim();
	}
}
