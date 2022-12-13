package microservices.frontend.tecrep_monitor.dto.create_interval_numbers;

import microservices.frontend.tecrep_monitor.dto.generate_number_block.GenerateNumberBlockDTO;
import microservices.frontend.tecrep_monitor.responses.GetNumberBlockResponse;

public class CreateIntervalNumbersDTO {

	private String firstNumber;
	private String lastNumber;
	private String activity;
	private GetNumberBlockResponse blockNumber;

	public CreateIntervalNumbersDTO(String firstNumber, String lastNumber, String activity, GetNumberBlockResponse blockNumber) {
		super();
		this.firstNumber = firstNumber;
		this.lastNumber = lastNumber;
		this.activity = activity;
		this.blockNumber = blockNumber;
	}

	public String getFirstNumber() {
		return firstNumber;
	}

	public void setFirstNumber(String firstNumber) {
		this.firstNumber = firstNumber;
	}

	public String getLastNumber() {
		return lastNumber;
	}

	public void setLastNumber(String lastNumber) {
		this.lastNumber = lastNumber;
	}

	public String getActivity() {
		return activity;
	}

	public void setActivity(String activity) {
		this.activity = activity;
	}

	public GetNumberBlockResponse getBlockNumber() {
		return blockNumber;
	}

	public void setBlockNumber(GetNumberBlockResponse blockNumber) {
		this.blockNumber = blockNumber;
	}
}
