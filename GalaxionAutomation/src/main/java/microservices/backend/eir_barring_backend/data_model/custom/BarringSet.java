package microservices.backend.eir_barring_backend.data_model.custom;

import java.util.ArrayList;

/**
 * This class represents a list of barring objects for validation of the MyGoMo GET barring API
 * @author stephenhall
 *
 */
public class BarringSet {

	private ArrayList<BarringDetail> barrings;

	
	public BarringSet(ArrayList<BarringDetail> barrings) {
		super();
		this.barrings = barrings;
	}

	public ArrayList<BarringDetail> getBarrings() {
		return barrings;
	}

	public void setBarrings(ArrayList<BarringDetail> barrings) {
		this.barrings = barrings;
	}	
	
	public boolean hasBarring(int pcatId) {
		for(BarringDetail bar:barrings) {
			if(bar.getPcatID()==pcatId && bar.getBarringStatus().equals("ACTIVE")) {
				return true;
			}
		}
		
		return false;
	}
}
