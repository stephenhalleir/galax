package microservices.backend.eir_cdr_repository_backend.files;

import java.util.ArrayList;

public class UsageFile {
	
	private String filename;
	private ArrayList<UsageRow> usageRecords;
	
	public UsageFile() {
		usageRecords = new ArrayList<UsageRow>();
		filename=null;
	}

	public String getFilename() {
		return filename;
	}

	public void setFilename(String filename) {
		this.filename = filename;
	}

	public ArrayList<UsageRow> getUsageRecords() {
		return usageRecords;
	}

	public void setUsageRecords(ArrayList<UsageRow> usageRecords) {
		this.usageRecords = usageRecords;
	}	
}
