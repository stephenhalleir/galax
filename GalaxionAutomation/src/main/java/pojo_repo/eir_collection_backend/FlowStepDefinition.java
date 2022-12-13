package pojo_repo.eir_collection_backend;

import java.sql.Date;
import java.sql.ResultSet;
import java.sql.SQLException;

public class FlowStepDefinition {

private int id;
private int ordering;
private String flowDefinitionName;
private String stepDefinitionName;

public FlowStepDefinition() {

}

public FlowStepDefinition(ResultSet rs) {
try {
	id = rs.getInt("id");
	ordering = rs.getInt("ordering");
	flowDefinitionName = rs.getString("flow_definition_name");
	stepDefinitionName = rs.getString("step_definition_name");
} catch (SQLException e) {
		e.printStackTrace();
	}
}

public int getId() {
 	return id;
}
public void setId(int id) {
 	 this.id=id;
}
public int getOrdering() {
 	return ordering;
}
public void setOrdering(int ordering) {
 	 this.ordering=ordering;
}
public String getFlowDefinitionName() {
 	return flowDefinitionName;
}
public void setFlowDefinitionName(String flowDefinitionName) {
 	 this.flowDefinitionName=flowDefinitionName;
}
public String getStepDefinitionName() {
 	return stepDefinitionName;
}
public void setStepDefinitionName(String stepDefinitionName) {
 	 this.stepDefinitionName=stepDefinitionName;
}

}