package microservices.backend.tecrep.resource_management.dto;

import java.math.BigInteger;

public class IntervalNumberDto {

	private String activity;
	private BlockNumberDtoV2 blockNumber;
	private BigInteger firstNumber;
	private int intervalId;
	private BigInteger lastNumber;

	public String getActivity() {
		return activity;
	}

	public void setActivity(String activity) {
		this.activity = activity;
	}

	public BlockNumberDtoV2 getBlockNumber() {
		return blockNumber;
	}

	public void setBlockNumber(BlockNumberDtoV2 blockNumber) {
		this.blockNumber = blockNumber;
	}

	public int getIntervalId() {
		return intervalId;
	}

	public void setIntervalId(int intervalId) {
		this.intervalId = intervalId;
	}

	public BigInteger getFirstNumber() {
		return firstNumber;
	}

	public void setFirstNumber(BigInteger firstNumber) {
		this.firstNumber = firstNumber;
	}

	public BigInteger getLastNumber() {
		return lastNumber;
	}

	public void setLastNumber(BigInteger lastNumber) {
		this.lastNumber = lastNumber;
	}
}
