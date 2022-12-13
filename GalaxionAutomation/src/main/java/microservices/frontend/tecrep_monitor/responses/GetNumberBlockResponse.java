package microservices.frontend.tecrep_monitor.responses;

public class GetNumberBlockResponse {
	
	private int blockId;
	private String blockPrefix;
	private String countryCode;
	private String length;
	private String localPrefix;
	private String operator;
	private boolean portability;
	
	public GetNumberBlockResponse() {
		super();
	}

	public String getBlockPrefix() {
		return blockPrefix;
	}

	public void setBlockPrefix(String blockPrefix) {
		this.blockPrefix = blockPrefix;
	}

	public String getCountryCode() {
		return countryCode;
	}

	public void setCountryCode(String countryCode) {
		this.countryCode = countryCode;
	}

	public String getLength() {
		return length;
	}

	public void setLength(String length) {
		this.length = length;
	}

	public String getLocalPrefix() {
		return localPrefix;
	}

	public void setLocalPrefix(String localPrefix) {
		this.localPrefix = localPrefix;
	}

	public String getOperator() {
		return operator;
	}

	public void setOperator(String operator) {
		this.operator = operator;
	}

	public boolean isPortability() {
		return portability;
	}

	public void setPortability(boolean portability) {
		this.portability = portability;
	}

	public int getBlockId() {
		return blockId;
	}

	public void setBlockId(int blockId) {
		this.blockId = blockId;
	}
}
