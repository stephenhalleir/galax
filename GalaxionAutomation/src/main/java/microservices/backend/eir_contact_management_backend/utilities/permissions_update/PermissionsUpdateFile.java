package microservices.backend.eir_contact_management_backend.utilities.permissions_update;

import java.util.ArrayList;

public class PermissionsUpdateFile {
	private String filename;
	private ArrayList<PermissionsUpdate> updates;
	
	public PermissionsUpdateFile() {
		updates = new ArrayList<PermissionsUpdate>();
	}
	
	public void addUpdate(PermissionsUpdate update) {
		updates.add(update);
	}

	public String getFilename() {
		return filename;
	}

	public void setFilename(String filename) {
		this.filename = filename;
	}

	public ArrayList<PermissionsUpdate> getUpdates() {
		return updates;
	}

	public void setUpdates(ArrayList<PermissionsUpdate> updates) {
		this.updates = updates;
	}
	
	public String toString() {
		String contents="";
		
		for(PermissionsUpdate update:updates) {
			contents = contents + update + "\n";
		}
		
		return contents.trim();
	}
}
