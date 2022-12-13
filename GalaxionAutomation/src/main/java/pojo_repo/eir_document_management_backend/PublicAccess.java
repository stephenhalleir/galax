package pojo_repo.eir_document_management_backend;

import java.sql.Date;
import java.sql.ResultSet;
import java.sql.SQLException;

public class PublicAccess {

	private int id;
	private Date expiresAt;
	private int nbConsultations;
	private int nbConsultationsMax;
	private String token;

	public PublicAccess() {

	}

	public PublicAccess(ResultSet rs) {
		try {
			id = rs.getInt("id");
			expiresAt = rs.getDate("expires_at");
			nbConsultations = rs.getInt("nb_consultations");
			nbConsultationsMax = rs.getInt("nb_consultations_max");
			token = rs.getString("token");
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

	public Date getExpiresAt() {
		return expiresAt;
	}

	public void setExpiresAt(Date expiresAt) {
		this.expiresAt = expiresAt;
	}

	public int getNbConsultations() {
		return nbConsultations;
	}

	public void setNbConsultations(int nbConsultations) {
		this.nbConsultations = nbConsultations;
	}

	public int getNbConsultationsMax() {
		return nbConsultationsMax;
	}

	public void setNbConsultationsMax(int nbConsultationsMax) {
		this.nbConsultationsMax = nbConsultationsMax;
	}

	public String getToken() {
		return token;
	}

	public void setToken(String token) {
		this.token = token;
	}

}