package pojo_repo.eir_salt_ar_backend;

import java.sql.Date;
import java.sql.ResultSet;
import java.sql.SQLException;

public class PrepaymentRelease {

	private Date creationDate;
	private int prepayId;
	private int id;
	private Date createTs;

	public PrepaymentRelease() {

	}

	public PrepaymentRelease(ResultSet rs) {
		try {
			creationDate = rs.getDate("creation_date");
			prepayId = rs.getInt("prepay_id");
			id = rs.getInt("id");
			createTs = rs.getDate("create_ts");
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	public Date getCreationDate() {
		return creationDate;
	}

	public void setCreationDate(Date creationDate) {
		this.creationDate = creationDate;
	}

	public int getPrepayId() {
		return prepayId;
	}

	public void setPrepayId(int prepayId) {
		this.prepayId = prepayId;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public Date getCreateTs() {
		return createTs;
	}

	public void setCreateTs(Date createTs) {
		this.createTs = createTs;
	}

}