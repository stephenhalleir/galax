package microservices.backend.eir_elavon_facade_backend.api;

public class ProcessPaymentDTO {

	private String brand;
	private boolean encoded;
	private String hppResponse;

	public ProcessPaymentDTO(String hppResponse) {
		this.hppResponse = hppResponse;
		encoded = true;
		brand = "GOMO";
	}

	public String getBrand() {
		return brand;
	}

	public void setBrand(String brand) {
		this.brand = brand;
	}

	public boolean isEncoded() {
		return encoded;
	}

	public void setEncoded(boolean encoded) {
		this.encoded = encoded;
	}

	public String getHppResponse() {
		return hppResponse;
	}

	public void setHppResponse(String hppResponse) {
		this.hppResponse = hppResponse;
	}
}
