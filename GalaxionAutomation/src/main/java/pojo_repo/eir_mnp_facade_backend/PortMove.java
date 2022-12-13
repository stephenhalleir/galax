package pojo_repo.eir_mnp_facade_backend;

import java.sql.Date;
import java.sql.ResultSet;
import java.sql.SQLException;

public class PortMove {

	private int portId;
	private String orderNumber;
	private Date portStartDate;
	private Date origPortStartDate;
	private String jobName;
	private int nextFireTime;
	private int origNextFireTime;
	private int startTime;

	public PortMove() {

	}

	public PortMove(ResultSet rs) {
		try {
			portId = rs.getInt("PORT_ID");
			orderNumber = rs.getString("order_number");
			portStartDate = rs.getDate("port_start_date");
			origPortStartDate = rs.getDate("orig_port_start_date");
			jobName = rs.getString("job_name");
			nextFireTime = rs.getInt("next_fire_time");
			origNextFireTime = rs.getInt("orig_next_fire_time");
			startTime = rs.getInt("start_time");
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	public int getPortId() {
		return portId;
	}

	public void setPortId(int portId) {
		this.portId = portId;
	}

	public String getOrderNumber() {
		return orderNumber;
	}

	public void setOrderNumber(String orderNumber) {
		this.orderNumber = orderNumber;
	}

	public Date getPortStartDate() {
		return portStartDate;
	}

	public void setPortStartDate(Date portStartDate) {
		this.portStartDate = portStartDate;
	}

	public Date getOrigPortStartDate() {
		return origPortStartDate;
	}

	public void setOrigPortStartDate(Date origPortStartDate) {
		this.origPortStartDate = origPortStartDate;
	}

	public String getJobName() {
		return jobName;
	}

	public void setJobName(String jobName) {
		this.jobName = jobName;
	}

	public int getNextFireTime() {
		return nextFireTime;
	}

	public void setNextFireTime(int nextFireTime) {
		this.nextFireTime = nextFireTime;
	}

	public int getOrigNextFireTime() {
		return origNextFireTime;
	}

	public void setOrigNextFireTime(int origNextFireTime) {
		this.origNextFireTime = origNextFireTime;
	}

	public int getStartTime() {
		return startTime;
	}

	public void setStartTime(int startTime) {
		this.startTime = startTime;
	}

}