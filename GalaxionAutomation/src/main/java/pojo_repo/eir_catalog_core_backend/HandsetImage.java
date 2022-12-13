package pojo_repo.eir_catalog_core_backend;

import java.sql.Date;
import java.sql.ResultSet;
import java.sql.SQLException;

public class HandsetImage {

	private int imageId;
	private int displayOrder;

	public HandsetImage() {

	}

	public HandsetImage(ResultSet rs) {
		try {
			imageId = rs.getInt("image_id");
			displayOrder = rs.getInt("display_order");
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	public int getImageId() {
		return imageId;
	}

	public void setImageId(int imageId) {
		this.imageId = imageId;
	}

	public int getDisplayOrder() {
		return displayOrder;
	}

	public void setDisplayOrder(int displayOrder) {
		this.displayOrder = displayOrder;
	}

}