package testCases.gomo.test_objects;

public class GoMoOrderCrossSell {

	private int numOffers;
	private boolean useExistingCard;
	private int billingAccountID;

	public GoMoOrderCrossSell() {
		numOffers=1;
		useExistingCard=true;
	}
	
	public GoMoOrderCrossSell(int numOffers, int billingAccountID, boolean useExistingCard) {
		this.numOffers=numOffers;
		this.useExistingCard=useExistingCard;
		this.billingAccountID=billingAccountID;
	}

	public int getNumOffers() {
		return numOffers;
	}

	public void setNumOffers(int numOffers) {
		this.numOffers = numOffers;
	}

	public boolean isUseExistingCard() {
		return useExistingCard;
	}

	public void setUseExistingCard(boolean useExistingCard) {
		this.useExistingCard = useExistingCard;
	}

	public int getBillingAccountID() {
		return billingAccountID;
	}

	public void setBillingAccountID(int billingAccountID) {
		this.billingAccountID = billingAccountID;
	}

	public String toString() {
		return "Order: Offers x " + numOffers;
	}
}
