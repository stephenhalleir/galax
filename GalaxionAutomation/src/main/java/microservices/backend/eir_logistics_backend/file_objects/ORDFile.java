package microservices.backend.eir_logistics_backend.file_objects;

/*
 * Author: Stephen Hall
 * Created 17/08/2018 for Project �on
 * 
 * This class represents an ORD file.
 * It consists of a name and a list of ORD line item objects
 */

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.util.ArrayList;

import javax.swing.JOptionPane;

public class ORDFile {

	private String ordName;
	private ArrayList<ORDLineItem> lineItems;

	public ORDFile() {
		// create the new list of line items
		lineItems = new ArrayList<ORDLineItem>();
	}
	
	// Constructor - taking an ORD file directory as input
	@SuppressWarnings("deprecation")
	public ORDFile(String fileName) {

		// create the new list of line items
		lineItems = new ArrayList<ORDLineItem>();

		BufferedReader reader;

		try {
			// split the directory string by "\\"
			String[] dirParts = fileName.replace("\\", "/").split("/");
			
			// determine the ORD filename - i.e. the last portion of the location string
			ordName = dirParts[dirParts.length - 1];
			
			// set up a new reader to read the ORD file
			reader = new BufferedReader(new FileReader(fileName));
			String text = null;

			// while there are lines in the ORD file
			while ((text = reader.readLine()) != null) {
				
				// create a new ORDLineItem object based on the line text
				ORDLineItem item = new ORDLineItem(text + " ");
				
				// add the ORD line item to the list
				lineItems.add(item);
			}
		} catch (NullPointerException e) {
			e.printStackTrace();
			JOptionPane.showMessageDialog(null, "ORD not found.", "Attention", 1);
			Thread.currentThread().stop();
		}
		catch (Exception e) {
			e.printStackTrace();
		}
	}
	

	// toString() method, returning the ORD file name and list of contents
	public String toString() {
		String ordFileString = "ORD file:\nName :" + ordName + "\n";
		for (ORDLineItem item : lineItems) {
			ordFileString = ordFileString + item + "\n";
		}

		return ordFileString;
	}

	// return the count of ORD line items in a file
	public int getLineCount() {
		return lineItems.size();
	}
	
	/*
	 * Getter and Setter methods...
	 */
	public String getORDName() {
		return ordName;
	}

	public void setContents(String contents) {
		String[] lines = contents.split("\n");
		lineItems = new ArrayList<ORDLineItem>();
		for(int i=0;i<lines.length;i++) {
			lineItems.add(new ORDLineItem(lines[i]));
		}
	}
	
	public String getContents() {
		String contents="";
		for(ORDLineItem item:lineItems) {
			contents = contents + "\n" + item.toString();
		}
		
		return contents;
	}
	
	public ArrayList<ORDLineItem> getLineItems() {
		return lineItems;
	}

	public void setORDName(String ORDName) {
		File file = new File(ORDName);
		this.ordName = file.getName();
	}

	public void setLineItems(ArrayList<ORDLineItem> lineItems) {
		this.lineItems = lineItems;
	}

}
