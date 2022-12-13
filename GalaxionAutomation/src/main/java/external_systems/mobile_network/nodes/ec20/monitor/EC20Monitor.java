package external_systems.mobile_network.nodes.ec20.monitor;

import external_systems.mmw.MMWUtility;
import utilities.generic.time.WaitUtil;

public class EC20Monitor {

	/**
	 * Wait for the IN balance to change
	 * @param msisdn
	 * @param previousBalance
	 * @return the new balance
	 */
	public static double waitForCreditChange(String msisdn, double previousBalance) {
		
		long endTime=System.currentTimeMillis() + 30000;
		
		// read the balance
		String balanceString = MMWUtility.getEC20CreditBalanceStr(msisdn);
		double mainBalance = Double.parseDouble(balanceString);
		
		while(System.currentTimeMillis()<endTime) {
			
			// read the balance again
			balanceString = MMWUtility.getEC20CreditBalanceStr(msisdn);
			mainBalance = Double.parseDouble(balanceString);
			
			// if the balance has not changed, wait for 5 seconds
			if(mainBalance==previousBalance) {
				WaitUtil.waitForSeconds(5);
			}
			else {
				return mainBalance;
			}
		}
		
		return mainBalance;
	}
}
