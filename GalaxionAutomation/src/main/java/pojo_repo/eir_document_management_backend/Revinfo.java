package pojo_repo.eir_document_management_backend;

import java.sql.Date;
import java.sql.ResultSet;
import java.sql.SQLException;

public class Revinfo {

	private int rev;

	public Revinfo() {

	}

	public Revinfo(ResultSet rs) {
		try {
			rev = rs.getInt("REV");
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	public int getRev() {
		return rev;
	}

	public void setRev(int rev) {
		this.rev = rev;
	}

}