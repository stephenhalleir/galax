package microservices.frontend.eir_crm_ui_frontend.dto.response.get_barrings;

import java.util.List;

/**
 * This class represents a Barring object as returned by the GET /barring API call
 * @author stephenhall
 *
 */

public class Barring {

	private String name;
	private String barringCode;
	private int barringId;
	private String barringStatus;
	private String barringLevel;
	private int pcatId;
	private List<BarringAttribute> barringAttributes;

	public Barring() {
		super();
	}

	public int getPcatId() {
		return pcatId;
	}

	public void setPcatId(int pcatId) {
		this.pcatId = pcatId;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getBarringId() {
		return barringId;
	}

	public void setBarringId(int barringId) {
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

	public String getBarringCode() {
		return barringCode;
	}

	public void setBarringCode(String barringCode) {
		this.barringCode = barringCode;
	}
}
