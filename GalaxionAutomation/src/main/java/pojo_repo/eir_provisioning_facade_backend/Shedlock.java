package pojo_repo.eir_provisioning_facade_backend;

import java.sql.Date;
import java.sql.ResultSet;
import java.sql.SQLException;

public class Shedlock {

	private String name;
	private Date lockUntil;
	private Date lockedAt;

	public Shedlock() {

	}

	public Shedlock(ResultSet rs) {
		try {
			name = rs.getString("name");
			lockUntil = rs.getDate("lock_until");
			lockedAt = rs.getDate("locked_at");
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Date getLockUntil() {
		return lockUntil;
	}

	public void setLockUntil(Date lockUntil) {
		this.lockUntil = lockUntil;
	}

	public Date getLockedAt() {
		return lockedAt;
	}

	public void setLockedAt(Date lockedAt) {
		this.lockedAt = lockedAt;
	}

}