package testCases.gomo.test_objects;

public class GoMoNewAcquisitionOrder {

	private int numOffers;

	public GoMoNewAcquisitionOrder() {
		numOffers=1;
	}
	
	public GoMoNewAcquisitionOrder(int numOffers) {
		this.numOffers=numOffers;
	}

	public int getNumOffers() {
		return numOffers;
	}

	public void setNumOffers(int numOffers) {
		this.numOffers = numOffers;
	}
	
	public String toString() {
		return "Order: Offers x " + numOffers;
	}
}
