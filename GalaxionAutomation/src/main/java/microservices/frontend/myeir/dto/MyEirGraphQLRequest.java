package microservices.frontend.myeir.dto;

public class MyEirGraphQLRequest {
	private String operationName;
	private String query;
	private Variables variables;
	
	
	
	public MyEirGraphQLRequest() {
		super();
		variables = new Variables();
		// TODO Auto-generated constructor stub
	}
	public String getOperationName() {
		return operationName;
	}
	public void setOperationName(String operationName) {
		this.operationName = operationName;
	}
	public String getQuery() {
		return query;
	}
	public void setQuery(String query) {
		this.query = query;
	}
	public Variables getVariables() {
		return variables;
	}
	public void setVariables(Variables variables) {
		this.variables = variables;
	}
	
	
}
