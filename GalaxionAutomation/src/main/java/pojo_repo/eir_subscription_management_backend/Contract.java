package pojo_repo.eir_subscription_management_backend;

import java.sql.Date;
import java.sql.ResultSet;
import java.sql.SQLException;

public class Contract {

private int id;
private Date startAt;

public Contract() {

}

public Contract(ResultSet rs) {
try {
	id = rs.getInt("id");
	startAt = rs.getDate("start_at");
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
public Date getStartAt() {
 	return startAt;
}
public void setStartAt(Date startAt) {
 	 this.startAt=startAt;
}

}