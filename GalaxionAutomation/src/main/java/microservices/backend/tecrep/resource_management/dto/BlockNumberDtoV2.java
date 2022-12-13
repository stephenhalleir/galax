package microservices.backend.tecrep.resource_management.dto;

public class BlockNumberDtoV2 {

	private int blockId;
	private int blockPrefix;
	private int countryCode;
	private int length;
	private int localPrefix;
	private String operator;
	private boolean portability;
	public int getBlockId() {
		return blockId;
	}
	public void setBlockId(int blockId) {
		this.blockId = blockId;
	}
	public int getBlockPrefix() {
		return blockPrefix;
	}
	public void setBlockPrefix(int blockPrefix) {
		this.blockPrefix = blockPrefix;
	}
	public int getCountryCode() {
		return countryCode;
	}
	public void setCountryCode(int countryCode) {
		this.countryCode = countryCode;
	}
	public int getLength() {
		return length;
	}
	public void setLength(int length) {
		this.length = length;
	}
	public int getLocalPrefix() {
		return localPrefix;
	}
	public void setLocalPrefix(int localPrefix) {
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
