package microservices.frontend.common_ui.response_objects.barring;

import java.util.List;

public class ReturnedBarring {
	private String name;
	private String barringId;
	private String barringCode;
	private String barringStatus;
	private String barringLevel;
	private List<BarringAttribute> barringAttributes;
	private int pcatId;

	public ReturnedBarring() {
		super();
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getBarringId() {
		return barringId;
	}

	public void setBarringId(String barringId) {
		this.barringId = barringId;
	}

	public String getBarringStatus() {
		return barringStatus;
	}

	public void setBarringStatus(String barringStatus) {
		this.barringStatus = barringStatus;
	}

	public String getBarringLevel() {
		return barringLevel;
	}

	public void setBarringLevel(String barringLevel) {
		this.barringLevel = barringLevel;
	}

	public List<BarringAttribute> getBarringAttributes() {
		return barringAttributes;
	}

	public void setBarringAttributes(List<BarringAttribute> barringAttributes) {
		this.barringAttributes = barringAttributes;
	}

	public int getPcatId() {
		return pcatId;
	}

	public void setPcatId(int pcatId) {
		this.pcatId = pcatId;
	}

	public String getBarringCode() {
		return barringCode;
	}

	public void setBarringCode(String barringCode) {
		this.barringCode = barringCode;
	}	
}
