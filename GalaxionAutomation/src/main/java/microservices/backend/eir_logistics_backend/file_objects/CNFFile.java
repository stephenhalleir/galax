package microservices.backend.eir_logistics_backend.file_objects;

/*
 * Author: Stephen Hall
 * Created 17/08/2018 for Project �on
 * 
 * This class represents a CNF file.
 * It consists of a name and a list of CNF line item objects
 */

import java.io.IOException;
import java.util.ArrayList;

import microservices.backend.eir_inventory_management_backend.objects.EquipmentPack;
import utilities.generic.files.TextReader;

public class CNFFile {
	
	private String cnfName;
	private ArrayList<CNFLineItem> lineItems;
	
	public String getCnfName(){
		return cnfName;
	}
	
	// constructor for the CNF file
	public CNFFile(ORDFile ordFile,ArrayList<EquipmentPack> packs){

		lineItems = new ArrayList<CNFLineItem>();
		
		// determine the CNF name based on the ORD name
		cnfName = ordFile.getORDName().replace("ORD","ION_CNF");

		// for each line in the ord file, create a corresponding CNF line-item object
		for(int i = 0; i < ordFile.getLineCount(); i++){
			
			EquipmentPack pack=null;
			
			// read the ORD line item
			ORDLineItem ordItem = ordFile.getLineItems().get(i);
			
			// determine the equipment type (e.g. SIM)
			System.out.println("Equipment Type = " + ordItem.getEquipmentType());
			
			// take a pack from the list
			if(ordItem.getEquipmentType().equalsIgnoreCase("SIM") || ordItem.getEquipmentType().equalsIgnoreCase("SIM/Handset")){
				pack = packs.get(0);
				packs.remove(0);
			}
			
			// create the CNF line item
			CNFLineItem cnfItem = new CNFLineItem(ordItem,pack);			
			
			// add the CNF line item to the list
			lineItems.add(cnfItem);
		}
	}
	
	// string method returns the contents of the CNF file
	public String toString(){
		
		// start the string with the CNF file header
		String cnfContents="<NUMBER_OF_RECORDS>" + lineItems.size() + "\n";
		
		// add each CNF line-item from the list
		for(CNFLineItem item:lineItems){
			cnfContents = cnfContents + item.toString().trim() + "\n";
		}
		
		// trim the last new-line character as it is not required
		if(cnfContents.endsWith("\n")){
			cnfContents =cnfContents.trim();
		}
		
		return cnfContents;
	}
	
	// write the CNF contents to a file
	public String writeCNFFile() throws IOException{
		
		// write the contents to a file
		TextReader.writeFile(toString(), System.getProperty("user.dir") + "\\generated_Logistic_Files\\"+ cnfName);
		
	    // return the CNF content
	    return toString();
	}
	
	public ArrayList<CNFLineItem> getLineItems(){
		return lineItems;
	}
}
