package pojo_repo.eir_order_management_backend;

import java.sql.Date;
import java.sql.ResultSet;
import java.sql.SQLException;

public class RefTask {

	private int id;
	private String type;
	private int sequence;
	private int isOptional;
	private int isBlocking;
	private int hasBreakPoint;
	private String hierarchyLevel;

	public RefTask() {

	}

	public RefTask(ResultSet rs) {
		try {
			id = rs.getInt("id");
			type = rs.getString("type");
			sequence = rs.getInt("sequence");
			isOptional = rs.getInt("is_optional");
			isBlocking = rs.getInt("is_blocking");
			hasBreakPoint = rs.getInt("has_break_point");
			hierarchyLevel = rs.getString("hierarchy_level");
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

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public int getSequence() {
		return sequence;
	}

	public void setSequence(int sequence) {
		this.sequence = sequence;
	}

	public int getIsOptional() {
		return isOptional;
	}

	public void setIsOptional(int isOptional) {
		this.isOptional = isOptional;
	}

	public int getIsBlocking() {
		return isBlocking;
	}

	public void setIsBlocking(int isBlocking) {
		this.isBlocking = isBlocking;
	}

	public int getHasBreakPoint() {
		return hasBreakPoint;
	}

	public void setHasBreakPoint(int hasBreakPoint) {
		this.hasBreakPoint = hasBreakPoint;
	}

	public String getHierarchyLevel() {
		return hierarchyLevel;
	}

	public void setHierarchyLevel(String hierarchyLevel) {
		this.hierarchyLevel = hierarchyLevel;
	}

}