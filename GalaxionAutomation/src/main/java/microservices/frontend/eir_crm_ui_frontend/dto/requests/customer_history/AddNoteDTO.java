package microservices.frontend.eir_crm_ui_frontend.dto.requests.customer_history;

import java.util.ArrayList;
import java.util.List;

public class AddNoteDTO {
	
	private int accountId;
	private String content;
	private List<String> serviceNumbers;

	public AddNoteDTO(int accountId, String content, String msisdn) {
		super();
		this.accountId = accountId;
		this.content = content;
		this.serviceNumbers = new ArrayList<String>();
		this.serviceNumbers.add(msisdn);
	}

	public int getAccountId() {
		return accountId;
	}

	public void setAccountId(int accountId) {
		this.accountId = accountId;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public List<String> getServiceNumbers() {
		return serviceNumbers;
	}

	public void setServiceNumbers(List<String> serviceNumbers) {
		this.serviceNumbers = serviceNumbers;
	}	
}
