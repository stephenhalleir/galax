package pojo_repo.eir_prospect_backend;

import java.sql.Date;
import java.sql.ResultSet;
import java.sql.SQLException;

public class AddonBundle {

private int id;
private int catalogId;

public AddonBundle() {

}

public AddonBundle(ResultSet rs) {
try {
	id = rs.getInt("id");
	catalogId = rs.getInt("catalog_id");
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
public int getCatalogId() {
 	return catalogId;
}
public void setCatalogId(int catalogId) {
 	 this.catalogId=catalogId;
}

}