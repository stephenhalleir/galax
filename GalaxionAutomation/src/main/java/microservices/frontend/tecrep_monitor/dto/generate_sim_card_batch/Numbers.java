package microservices.frontend.tecrep_monitor.dto.generate_sim_card_batch;

import java.math.BigInteger;

public class Numbers {

	private BigInteger firstNumber;
	private BigInteger lastNumber;
	
	public Numbers(BigInteger firstNumber, BigInteger lastNumber) {
		super();
		this.firstNumber = firstNumber;
		this.lastNumber = lastNumber;
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
