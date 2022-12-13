package microservices.backend.eir_catalog_core_backend.data_model;

import java.sql.ResultSet;
import java.sql.SQLException;

public class NetworkElement {

private String name;
private String type;
private String networkName;
private String networkType;
private String status;
private int id;
private String code;

public NetworkElement() {

}

public NetworkElement(ResultSet rs) {
try {
	name = rs.getString("name");
	type = rs.getString("type");
	networkName = rs.getString("network_name");
	networkType = rs.getString("network_type");
	status = rs.getString("status");
	id = rs.getInt("id");
	code = rs.getString("code");
} catch (SQLException e) {
				e.printStackTrace();
			}
}

public String getName() {
 	return name;
}
public void setName(String name) {
 	 this.name=name;
}
public String getType() {
 	return type;
}
public void setType(String type) {
 	 this.type=type;
}
public String getNetworkName() {
 	return networkName;
}
public void setNetworkName(String networkName) {
 	 this.networkName=networkName;
}
public String getNetworkType() {
 	return networkType;
}
public void setNetworkType(String networkType) {
 	 this.networkType=networkType;
}
public String getStatus() {
 	return status;
}
public void setStatus(String status) {
 	 this.status=status;
}
public int getId() {
 	return id;
}
public void setId(int id) {
 	 this.id=id;
}
public String getCode() {
 	return code;
}
public void setCode(String code) {
 	 this.code=code;
}

}