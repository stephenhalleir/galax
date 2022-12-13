package microservices.frontend.tecrep_monitor.utility;

import microservices.frontend.tecrep_monitor.enums.InventoryPool;
import microservices.frontend.tecrep_monitor.enums.SimCardConfiguration;
import utilities.config.ConfigReader;

public class TecrepUtility {
	
	public static SimCardConfiguration getConfiguration(InventoryPool pool) {
		String configurationName = ConfigReader.readConfigValue("maps\\sim_generation_inventory_pool_configurations_map.properties", pool.toString());
		return SimCardConfiguration.valueOf(configurationName);
	}
}
