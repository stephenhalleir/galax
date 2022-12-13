package external_systems.mobile_network.nodes.ec20.components;

//=================================================================================================================
// This class represents an ECS bundle, consisting of a set of components - e.g. a FUP or main balance
//=================================================================================================================

import java.util.ArrayList;

public class ECSBundle {

	private ArrayList<ECSBundleComponent> components;

	// =================================================================================================================
	// Constructors
	// =================================================================================================================

	public ECSBundle() {
		components = new ArrayList<ECSBundleComponent>();
	}

	// =================================================================================================================
	// Getters and Setters
	// =================================================================================================================

	public ArrayList<ECSBundleComponent> getComponents() {
		return components;
	}

	public void setComponents(ArrayList<ECSBundleComponent> components) {
		this.components = components;
	}

	public void addComponent(ECSBundleComponent component) {
		components.add(component);
	}

	// =================================================================================================================
	// Methods
	// =================================================================================================================
	
	public String toString() {
		String s = "Balance:\n";
		for (ECSBundleComponent component : components) {
			s = s + "\t" + component + "\n";
		}
		return s;
	}
}
