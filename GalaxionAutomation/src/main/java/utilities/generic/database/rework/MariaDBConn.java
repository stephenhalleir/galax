package utilities.generic.database.rework;

public class MariaDBConn extends DBConnection {

	public MariaDBConn(String dB_URL, String dB_PORT, String uSER, String pASS) {
		super(dB_URL, dB_PORT, uSER, pASS);
		this.setDB_URL("jdbc:mariadb://" + getDB_URL());
		JDBC_DRIVER="org.mariadb.jdbc.Driver";
	}
}
