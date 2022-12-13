package pojo_repo.eir_collection_backend;

import java.sql.Date;
import java.sql.ResultSet;
import java.sql.SQLException;

public class StepAdditionalParameter {

private String stepDefinitionName;
private String additionalParameterKey;

public StepAdditionalParameter() {

}

public StepAdditionalParameter(ResultSet rs) {
try {
	stepDefinitionName = rs.getString("step_definition_name");
	additionalParameterKey = rs.getString("additional_parameter_key");
} catch (SQLException e) {
		e.printStackTrace();
	}
}

public String getStepDefinitionName() {
 	return stepDefinitionName;
}
public void setStepDefinitionName(String stepDefinitionName) {
 	 this.stepDefinitionName=stepDefinitionName;
}
public String getAdditionalParameterKey() {
 	return additionalParameterKey;
}
public void setAdditionalParameterKey(String additionalParameterKey) {
 	 this.additionalParameterKey=additionalParameterKey;
}

}