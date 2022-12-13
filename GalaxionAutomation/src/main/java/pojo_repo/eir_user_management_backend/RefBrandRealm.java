package pojo_repo.eir_user_management_backend;

import java.sql.Date;
import java.sql.ResultSet;
import java.sql.SQLException;

public class RefBrandRealm {

	private int id;
	private String brand;
	private String realm;
	private String myaccountKeycloakClientId;
	private String myaccountKeycloakRedirectUri;

	public RefBrandRealm() {

	}

	public RefBrandRealm(ResultSet rs) {
		try {
			id = rs.getInt("id");
			brand = rs.getString("brand");
			realm = rs.getString("realm");
			myaccountKeycloakClientId = rs.getString("myaccount_keycloak_client_id");
			myaccountKeycloakRedirectUri = rs.getString("myaccount_keycloak_redirect_uri");
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

	public String getBrand() {
		return brand;
	}

	public void setBrand(String brand) {
		this.brand = brand;
	}

	public String getRealm() {
		return realm;
	}

	public void setRealm(String realm) {
		this.realm = realm;
	}

	public String getMyaccountKeycloakClientId() {
		return myaccountKeycloakClientId;
	}

	public void setMyaccountKeycloakClientId(String myaccountKeycloakClientId) {
		this.myaccountKeycloakClientId = myaccountKeycloakClientId;
	}

	public String getMyaccountKeycloakRedirectUri() {
		return myaccountKeycloakRedirectUri;
	}

	public void setMyaccountKeycloakRedirectUri(String myaccountKeycloakRedirectUri) {
		this.myaccountKeycloakRedirectUri = myaccountKeycloakRedirectUri;
	}

}