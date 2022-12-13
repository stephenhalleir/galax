package microservices.backend.eir_order_management_backend.data_model;

import java.sql.SQLException;
import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.sql.ResultSet;

public class Payment {
	private int id;
	private int orderID;
	private String payerReference;
	private String paymentReference;
	private int amount;
	
	public Payment() {
		
	}
	
	public Payment(ResultSet rs) {
		try {
			id=rs.getInt("id");
			orderID=rs.getInt("order_id");
			amount=rs.getInt("amount");
			payerReference=rs.getString("payer_reference");
			paymentReference=rs.getString("payment_reference");
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

	public int getOrderID() {
		return orderID;
	}

	public void setOrderID(int orderID) {
		this.orderID = orderID;
	}

	public String getPayerReference() {
		return payerReference;
	}

	public void setPayerReference(String payerReference) {
		this.payerReference = payerReference;
	}

	public String getPaymentReference() {
		return paymentReference;
	}

	public void setPaymentReference(String paymentReference) {
		this.paymentReference = paymentReference;
	}

	public int getAmount() {
		return amount;
	}

	public void setAmount(int amount) {
		this.amount = amount;
	}
	
	public String getAmountAsCurrency() {
		
		if(amount==0) {
			return "€0";
		}
		
		double amountEuros = (double)amount/100;
		DecimalFormat formatter = new DecimalFormat("€####.00");
		return formatter.format(amountEuros);
	}
}
