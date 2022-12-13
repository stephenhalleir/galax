package testCases;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import org.jooq.DSLContext;
import org.jooq.impl.DSL;

import framework.test_data.galaxion.TestDataGenerator;
import microservices.backend.eir_subscription_management_backend.SubscriptionManagementDAO;
import microservices.backend.keycloak.api.KeycloakAPI;
import microservices.backend.keycloak.dao.KeycloakDAO;
import microservices.backend.keycloak.data_model.Client;
import microservices.backend.keycloak.enums.Realm;
import microservices.frontend.eir_crm_ui_frontend.api.PAYGCRMAPI;
import utilities.galaxion.ftp.IONFileUploader;
import utilities.generic.database.rework.MariaDBConn;
import utilities.generic.pojo_generation.db_to_pojo.DbToPojoConverter;

public class TestClass {

	public static void print(int x, String... values) {
		for (String d : values) {
			System.out.println(d);
		}
		System.err.println(x);
	}

	public static void main(String[] arg) {
		System.out.println("Hello world");
	}
}