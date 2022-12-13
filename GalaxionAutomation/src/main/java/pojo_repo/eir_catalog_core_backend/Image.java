package pojo_repo.eir_catalog_core_backend;

import java.sql.Date;
import java.sql.ResultSet;
import java.sql.SQLException;

public class Image {

	private int id;
	private String alt;
	private String path;
	private String thumbnailPath;

	public Image() {

	}

	public Image(ResultSet rs) {
		try {
			id = rs.getInt("id");
			alt = rs.getString("alt");
			path = rs.getString("path");
			thumbnailPath = rs.getString("thumbnail_path");
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getAlt() {
		return alt;
	}

	public void setAlt(String alt) {
		this.alt = alt;
	}

	public String getPath() {
		return path;
	}

	public void setPath(String path) {
		this.path = path;
	}

	public String getThumbnailPath() {
		return thumbnailPath;
	}

	public void setThumbnailPath(String thumbnailPath) {
		this.thumbnailPath = thumbnailPath;
	}

}