package microservices.backend.eir_salt_ar_backend.dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import microservices.backend.eir_salt_ar_backend.data_model.Balance;
import microservices.backend.eir_salt_ar_backend.data_model.BalanceChange;
import microservices.backend.eir_salt_ar_backend.data_model.ImmediateAdjustment;
import utilities.generic.database.GalaxionDBUtil;
import utilities.generic.database.MariaDBConnection;
import utilities.test.config_readers.ExcelSQLManager;

public class AccountsReceivableDAO {
	
	public static Balance getBalance(int billingAccountID) {
	
		String query = ExcelSQLManager.getSQLQuery("SALT_AR", "GET_BALANCE");
		query = query.replace("$billingAccountID", Integer.toString(billingAccountID));

		try {
			ResultSet rs = GalaxionDBUtil.getQueryResultSet(query);
			if (rs.next()) {
				return new Balance(rs);
			}
		}
		catch (SQLException e) {
			e.printStackTrace();
		}
		
		return null;
	}
	
	public static ArrayList<BalanceChange> getBalanceChanges(int billingAccountID){
		ArrayList<BalanceChange> balanceChanges = new ArrayList<BalanceChange>();
		String query = ExcelSQLManager.getSQLQuery("SALT_AR", "GET_BALANCE_CHANGES");
		query = query.replace("$billingAccountID", Integer.toString(billingAccountID));

		try {
			ResultSet rs = GalaxionDBUtil.getQueryResultSet(query);
			while (rs.next()) {
				balanceChanges.add(new BalanceChange(rs));
			}
		}
		catch (SQLException e) {
			e.printStackTrace();
		}
		
		return balanceChanges;
	}
	
	public static ArrayList<BalanceChange> getMyGoMoRelevantBalanceChanges(int billingAccountID){
		ArrayList<BalanceChange> balanceChanges = getBalanceChanges(billingAccountID);
		ArrayList<BalanceChange> relevantChanges = new ArrayList<BalanceChange>();
		
		for(BalanceChange change:balanceChanges) {
			if(change.getChangeType().equals("PAYMENT") || change.getChangeType().equals("IMMEDIATE_ADJUSTMENT") || change.getChangeType().equals("INVOICE")) {
				relevantChanges.add(change);
			}
		}
		
		return relevantChanges;
	}
	
	public static ArrayList<ImmediateAdjustment> getImmediateAdjustments(int billingAccountID){
		ArrayList<ImmediateAdjustment> adjustments = new ArrayList<ImmediateAdjustment>();
		String query = ExcelSQLManager.getSQLQuery("SALT_AR", "GET_IMMEDIATE_ADJUSTMENTS");
		query = query.replace("$billingAccountId", Integer.toString(billingAccountID));

		try {
			ResultSet rs = GalaxionDBUtil.getQueryResultSet(query);
			while (rs.next()) {
				adjustments.add(new ImmediateAdjustment(rs));
			}
		}
		catch (SQLException e) {
			e.printStackTrace();
		}
		
		return adjustments;
	}
}
