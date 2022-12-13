package microservices.backend.eir_bulk_backend.bulk_files.move_subscription_file;

public class MoveSubscriptionsRow {

	private String row;
	private String sourceMsisdn;
	private String destinationAccount;

	public MoveSubscriptionsRow(String sourceMsisdn, String destinationAccount) {
		this.sourceMsisdn = sourceMsisdn;
		this.destinationAccount = destinationAccount;
	}

	public MoveSubscriptionsRow() {
		row = "";
		sourceMsisdn = "";
		destinationAccount = "";
	}

	public void randomize() {

	}

	public String getSourceMsisdn() {
		return sourceMsisdn;
	}

	public void setSourceMsisdn(String sourceMsisdn) {
		this.sourceMsisdn = sourceMsisdn;
	}

	public String getDestinationAccount() {
		return destinationAccount;
	}

	public void setDestinationAccount(String destinationAccount) {
		this.destinationAccount = destinationAccount;
	}

	public String getRow() {
		return row;
	}

	public void setRow(String row) {
		this.row = row;
	}

	public String toString() {
		String[] fields = { row, sourceMsisdn, destinationAccount };
		String s = fields[0];
		for (int i = 1; i < fields.length; i++) {
			s = s + "," + fields[i];
		}

		return s;
	}
}