package pojo_repo.eir_product_catalog_backend;

import java.sql.Date;
import java.sql.ResultSet;
import java.sql.SQLException;

public class BillableElement {

	private int id;
	private String billableType;
	private Date startDate;
	private Date endDate;
	private int amount;
	private int vatId;

	public BillableElement() {

	}

	public BillableElement(ResultSet rs) {
		try {
			id = rs.getInt("id");
			billableType = rs.getString("billable_type");
			startDate = rs.getDate("start_date");
			endDate = rs.getDate("end_date");
			amount = rs.getInt("amount");
			vatId = rs.getInt("vat_id");
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

	public String getBillableType() {
		return billableType;
	}

	public void setBillableType(String billableType) {
		this.billableType = billableType;
	}

	public Date getStartDate() {
		return startDate;
	}

	public void setStartDate(Date startDate) {
		this.startDate = startDate;
	}

	public Date getEndDate() {
		return endDate;
	}

	public void setEndDate(Date endDate) {
		this.endDate = endDate;
	}

	public int getAmount() {
		return amount;
	}

	public void setAmount(int amount) {
		this.amount = amount;
	}

	public int getVatId() {
		return vatId;
	}

	public void setVatId(int vatId) {
		this.vatId = vatId;
	}

}