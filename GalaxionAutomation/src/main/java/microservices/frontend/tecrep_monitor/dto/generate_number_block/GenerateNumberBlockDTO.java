package microservices.frontend.tecrep_monitor.dto.generate_number_block;

public class GenerateNumberBlockDTO {
	
	private String blockPrefix;
	private String countryCode;
	private String length;
	private String localPrefix;
	private String operator;
	private boolean portability;
	
	public GenerateNumberBlockDTO() {
		super();
	}

	public GenerateNumberBlockDTO(String blockPrefix, String countryCode, String length, String localPrefix, String operator, boolean portability) {
		super();
		this.blockPrefix = blockPrefix;
		this.countryCode = countryCode;
		this.length = length;
		this.localPrefix = localPrefix;
		this.operator = operator;
		this.portability = portability;
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
}
