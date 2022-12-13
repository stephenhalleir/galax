package microservices.backend.tecrep.equipments_management.files;

import java.util.ArrayList;

public class InpFile {
	
	private String header;
	private int quantity;
	private int batch;
	private ArrayList<InpRow> rows;
	private String contents;
	
	public InpFile(String contents) {
		this.contents=contents;
		
		rows = new ArrayList<InpRow>();
		
		String[] lines = contents.split("\n");
		for(int i=0;i<lines.length;i++) {
			if(lines[i].startsWith("2720")) {
				String imsi=lines[i].split(" ")[0];
				String iccid=lines[i].split(" ")[1];
				rows.add(new InpRow(imsi, iccid));
			}
			else if(lines[i].startsWith("Batch")) {
				String batchString=lines[i].split(":")[1].trim();
				batch=Integer.parseInt(batchString);
			}
			else if(lines[i].startsWith("Quantity")) {
				String quantityString=lines[i].split(":")[1].trim();
				quantity=Integer.parseInt(quantityString);
			}
		}
		
	}
	
	public InpFile(String header, ArrayList<InpRow> rows) {
		super();
		this.header = header;
		this.rows = rows;
	}
	
	public InpFile() {
		super();
		rows = new ArrayList<InpRow>();
	}

	public String getHeader() {
		return header;
	}
	public void setHeader(String header) {
		this.header = header;
	}
	public ArrayList<InpRow> getRows() {
		return rows;
	}
	public void setRows(ArrayList<InpRow> rows) {
		this.rows = rows;
	}

	public int getQuantity() {
		return quantity;
	}

	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}

	public int getBatch() {
		return batch;
	}

	public void setBatch(int batch) {
		this.batch = batch;
	}
	
	public String toString() {
		return contents;
	}
}
