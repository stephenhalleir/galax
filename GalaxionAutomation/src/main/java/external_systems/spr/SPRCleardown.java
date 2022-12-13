package external_systems.spr;

import java.sql.ResultSet;
import java.sql.SQLException;

import utilities.generic.database.OracleDBUtil;

public class SPRCleardown {

	private OracleDBUtil db;
	int amount=200;
	
	public void cleardown(String range) {
		
		db = new OracleDBUtil("rhot3.intmet.ie", "1521", "BEAMPP", "SPR_USER", "spr1tst");
		System.out.println(db.openDBConnection());
		doBatch(range,amount);
	}
	
	public void doBatch(String range, int amount) {
		ResultSet rs;
		try {
			rs = db.executeQuery("select * from subscriber " + 
					"where msisdn like '" + range + "' and ROWNUM <= " + amount + " order by msisdn");
			
			String[] msisdns=new String[amount];
			String[] customerAccountIDs=new String[amount];
			int index=0;
			while(rs.next()) {
				String customerAccountID=rs.getString("customer_account_id");
				String msisdn=rs.getString("msisdn");
				System.out.println(msisdn + ", " + customerAccountID);
				msisdns[index]=msisdn;
				customerAccountIDs[index]=customerAccountID;
				index++;
			}
			
			String queryDeleteSubExtra=getQuery("delete from subscriber_extra where msisdn in ",msisdns);
			String queryDeleteSubscriber=getQuery("delete from subscriber where customer_account_id in ",customerAccountIDs);
			String queryDeleteAccount=getQuery("delete from subscriber_account where customer_account_id in ",customerAccountIDs);
			
			db.runUpdateQuery(queryDeleteSubscriber);
			db.runUpdateQuery(queryDeleteAccount);
			db.runUpdateQuery(queryDeleteSubExtra);
			
			db.closeConnections();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	public String getQuery(String start, String[] items) {
		String query = start + "(";
		for(int i=0;i<items.length;i++) {
			query=query+"'" + items[i] + "'";
			
			if(i<items.length-1) {
				query=query+",";
			}
		}
		return query + ")";
	}
	
	public static void main(String [] args) {
		SPRCleardown cleardown = new SPRCleardown();
		for(int i=0;i<5261;i++) {
			long startTime=System.currentTimeMillis();
			cleardown.cleardown("353851%");
			long endTime=System.currentTimeMillis();
			long seconds=(endTime-startTime)/1000;
			System.out.println(i + "/" + 5261 + ", " + seconds + " seconds");
		}
		
		System.out.println("Done");
	}
}
