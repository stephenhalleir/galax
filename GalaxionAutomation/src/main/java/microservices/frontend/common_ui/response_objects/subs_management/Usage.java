package microservices.frontend.common_ui.response_objects.subs_management;

public class Usage {
	private String name;
	private int priority;

	public Usage() {
		super();
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getPriority() {
		return priority;
	}

	public void setPriority(int priority) {
		this.priority = priority;
	}

}
