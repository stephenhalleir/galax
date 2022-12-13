package pojo_repo.eir_subscription_management_backend;

import java.sql.Date;
import java.sql.ResultSet;
import java.sql.SQLException;

public class ServiceHistoryEnversOld {

	private int id;
	private int rev;
	private int revtype;
	private Date activationDateTime;

	public ServiceHistoryEnversOld() {

	}

	public ServiceHistoryEnversOld(ResultSet rs) {
		try {
			id = rs.getInt("id");
			rev = rs.getInt("rev");
			revtype = rs.getInt("revtype");
			activationDateTime = rs.getDate("activation_date_time");
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

	public int getRev() {
		return rev;
	}

	public void setRev(int rev) {
		this.rev = rev;
	}

	public int getRevtype() {
		return revtype;
	}

	public void setRevtype(int revtype) {
		this.revtype = revtype;
	}

	public Date getActivationDateTime() {
		return activationDateTime;
	}

	public void setActivationDateTime(Date activationDateTime) {
		this.activationDateTime = activationDateTime;
	}

}