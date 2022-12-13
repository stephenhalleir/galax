package microservices.backend.eir_salt_ar_backend.dto.invoicing_done_request;

public class InvoicingDoneDTO {
	private String bill_cycle_name;
	private int bill_run_index;

	public InvoicingDoneDTO(String bill_cycle_name, int bill_run_index) {
		super();
		this.bill_cycle_name = bill_cycle_name;
		this.bill_run_index = bill_run_index;
	}

	public String getBill_cycle_name() {
		return bill_cycle_name;
	}

	public void setBill_cycle_name(String bill_cycle_name) {
		this.bill_cycle_name = bill_cycle_name;
	}

	public int getBill_run_index() {
		return bill_run_index;
	}

	public void setBill_run_index(int bill_run_index) {
		this.bill_run_index = bill_run_index;
	}
}
