package pojo_repo.eir_product_catalog_backend;

import java.sql.Date;
import java.sql.ResultSet;
import java.sql.SQLException;

public class Duration {

	private int id;
	private String name;
	private int years;
	private int months;
	private int days;
	private int hours;
	private int minutes;

	public Duration() {

	}

	public Duration(ResultSet rs) {
		try {
			id = rs.getInt("id");
			name = rs.getString("name");
			years = rs.getInt("years");
			months = rs.getInt("months");
			days = rs.getInt("days");
			hours = rs.getInt("hours");
			minutes = rs.getInt("minutes");
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getYears() {
		return years;
	}

	public void setYears(int years) {
		this.years = years;
	}

	public int getMonths() {
		return months;
	}

	public void setMonths(int months) {
		this.months = months;
	}

	public int getDays() {
		return days;
	}

	public void setDays(int days) {
		this.days = days;
	}

	public int getHours() {
		return hours;
	}

	public void setHours(int hours) {
		this.hours = hours;
	}

	public int getMinutes() {
		return minutes;
	}

	public void setMinutes(int minutes) {
		this.minutes = minutes;
	}

}