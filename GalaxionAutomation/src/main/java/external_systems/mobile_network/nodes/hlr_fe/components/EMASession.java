package external_systems.mobile_network.nodes.hlr_fe.components;

public class EMASession {

	private String sessionId;
	private String sequenceId;

	public EMASession() {
		super();
		sessionId = "";
		sequenceId = "";
	}

	public EMASession(String sessionId, String sequenceId) {
		super();
		this.sessionId = sessionId;
		this.sequenceId = sequenceId;
	}

	public String getSessionId() {
		return sessionId;
	}

	public void setSessionId(String sessionId) {
		this.sessionId = sessionId;
	}

	public String getSequenceId() {
		return sequenceId;
	}

	public void setSequenceId(String sequenceId) {
		this.sequenceId = sequenceId;
	}
}