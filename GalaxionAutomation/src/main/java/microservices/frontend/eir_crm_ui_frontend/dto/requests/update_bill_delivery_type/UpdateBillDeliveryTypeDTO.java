package microservices.frontend.eir_crm_ui_frontend.dto.requests.update_bill_delivery_type;

public class UpdateBillDeliveryTypeDTO {
	
	private BillDeliveryType invoiceDeliveryMethod;

	public UpdateBillDeliveryTypeDTO(BillDeliveryType invoiceDeliveryMethod) {
		super();
		this.invoiceDeliveryMethod = invoiceDeliveryMethod;
	}

	public BillDeliveryType getInvoiceDeliveryMethod() {
		return invoiceDeliveryMethod;
	}

	public void setInvoiceDeliveryMethod(BillDeliveryType invoiceDeliveryMethod) {
		this.invoiceDeliveryMethod = invoiceDeliveryMethod;
	}
}
