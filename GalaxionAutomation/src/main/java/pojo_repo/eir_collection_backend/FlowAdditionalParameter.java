package pojo_repo.eir_collection_backend;

import java.sql.Date;
import java.sql.ResultSet;
import java.sql.SQLException;

public class FlowAdditionalParameter {

private String flowDefinitionName;
private String additionalParameterKey;

public FlowAdditionalParameter() {

}

public FlowAdditionalParameter(ResultSet rs) {
try {
	flowDefinitionName = rs.getString("flow_definition_name");
	additionalParameterKey = rs.getString("additional_parameter_key");
} catch (SQLException e) {
		e.printStackTrace();
	}
}

public String getFlowDefinitionName() {
 	return flowDefinitionName;
}
public void setFlowDefinitionName(String flowDefinitionName) {
 	 this.flowDefinitionName=flowDefinitionName;
}
public String getAdditionalParameterKey() {
 	return additionalParameterKey;
}
public void setAdditionalParameterKey(String additionalParameterKey) {
 	 this.additionalParameterKey=additionalParameterKey;
}

}