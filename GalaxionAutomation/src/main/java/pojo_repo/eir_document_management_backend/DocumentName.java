package pojo_repo.eir_document_management_backend;

import java.sql.Date;
import java.sql.ResultSet;
import java.sql.SQLException;

public class DocumentName {

	private int id;
	private String name;
	private String label;
	private int domainNameId;

	public DocumentName() {

	}

	public DocumentName(ResultSet rs) {
		try {
			id = rs.getInt("id");
			name = rs.getString("name");
			label = rs.getString("label");
			domainNameId = rs.getInt("domain_name_id");
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

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getLabel() {
		return label;
	}

	public void setLabel(String label) {
		this.label = label;
	}

	public int getDomainNameId() {
		return domainNameId;
	}

	public void setDomainNameId(int domainNameId) {
		this.domainNameId = domainNameId;
	}

}