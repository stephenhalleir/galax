package pojo_repo.eir_subscription_management_backend;

import java.sql.Date;
import java.sql.ResultSet;
import java.sql.SQLException;

public class AddonBundleHandset {

private int addonBundleId;

public AddonBundleHandset() {

}

public AddonBundleHandset(ResultSet rs) {
try {
	addonBundleId = rs.getInt("addon_bundle_id");
} catch (SQLException e) {
		e.printStackTrace();
	}
}

public int getAddonBundleId() {
 	return addonBundleId;
}
public void setAddonBundleId(int addonBundleId) {
 	 this.addonBundleId=addonBundleId;
}

}