package pojo_repo.eir_prospect_backend;

import java.sql.Date;
import java.sql.ResultSet;
import java.sql.SQLException;

public class Discount {

private int oldId;
private int catalogId;

public Discount() {

}

public Discount(ResultSet rs) {
try {
	oldId = rs.getInt("old_id");
	catalogId = rs.getInt("catalog_id");
} catch (SQLException e) {
		e.printStackTrace();
	}
}

public int getOldId() {
 	return oldId;
}
public void setOldId(int oldId) {
 	 this.oldId=oldId;
}
public int getCatalogId() {
 	return catalogId;
}
public void setCatalogId(int catalogId) {
 	 this.catalogId=catalogId;
}

}