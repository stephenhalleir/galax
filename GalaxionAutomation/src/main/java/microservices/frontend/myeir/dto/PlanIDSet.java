package microservices.frontend.myeir.dto;

import java.util.ArrayList;

public class PlanIDSet {
	private ArrayList<String> planIds;
	
	public PlanIDSet() {
		planIds=new ArrayList<String>();
		planIds.add("1PM_PREPAY_01");
		planIds.add("1PM_PREPAY_02");
		planIds.add("1PM_PREPAY_03");
		planIds.add("1PM_PREPAY_05");
	}

	public ArrayList<String> getPlanIds() {
		return planIds;
	}

	public void setPlanIds(ArrayList<String> planIds) {
		this.planIds = planIds;
	}
}
