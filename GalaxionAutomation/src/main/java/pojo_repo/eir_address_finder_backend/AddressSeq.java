package pojo_repo.eir_address_finder_backend;

import java.sql.Date;
import java.sql.ResultSet;
import java.sql.SQLException;

public class AddressSeq {

	private int nextNotCachedValue;
	private int minimumValue;
	private int maximumValue;
	private int startValue;
	private int increment;
	private int cacheSize;
	private int cycleOption;

	public AddressSeq() {

	}

	public AddressSeq(ResultSet rs) {
		try {
			nextNotCachedValue = rs.getInt("next_not_cached_value");
			minimumValue = rs.getInt("minimum_value");
			maximumValue = rs.getInt("maximum_value");
			startValue = rs.getInt("start_value");
			increment = rs.getInt("increment");
			cacheSize = rs.getInt("cache_size");
			cycleOption = rs.getInt("cycle_option");
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	public int getNextNotCachedValue() {
		return nextNotCachedValue;
	}

	public void setNextNotCachedValue(int nextNotCachedValue) {
		this.nextNotCachedValue = nextNotCachedValue;
	}

	public int getMinimumValue() {
		return minimumValue;
	}

	public void setMinimumValue(int minimumValue) {
		this.minimumValue = minimumValue;
	}

	public int getMaximumValue() {
		return maximumValue;
	}

	public void setMaximumValue(int maximumValue) {
		this.maximumValue = maximumValue;
	}

	public int getStartValue() {
		return startValue;
	}

	public void setStartValue(int startValue) {
		this.startValue = startValue;
	}

	public int getIncrement() {
		return increment;
	}

	public void setIncrement(int increment) {
		this.increment = increment;
	}

	public int getCacheSize() {
		return cacheSize;
	}

	public void setCacheSize(int cacheSize) {
		this.cacheSize = cacheSize;
	}

	public int getCycleOption() {
		return cycleOption;
	}

	public void setCycleOption(int cycleOption) {
		this.cycleOption = cycleOption;
	}

}