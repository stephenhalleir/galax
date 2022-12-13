package testCases.eir.b2b.bulk.payments;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;

import org.testng.ITestContext;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import framework.basetest.BaseTest;
import microservices.backend.eir_bulk_backend.bulk_files.payments_file.PaymentFile;
import microservices.backend.eir_bulk_backend.bulk_files.payments_file.PaymentRow;
import utilities.generic.time.Timestamp;
 
public class TestB2BPayments extends BaseTest {

	@Test(enabled = true, description = "B2B > Bulk File: Change Offers", invocationCount = 1)
	public void testB2BBulkAddSubscriptionAddons(ITestContext iTestContext) {

		String s1 = "89818434,250.00,13/01/2021,,,,";
		String s2 = "89818434,21528.25,13/01/2021,,Lorem epsum,,";
		String s3 = "89810000,425.00,13/01/2021,,,,";
		PaymentRow r1 = new PaymentRow(s1);
		PaymentRow r2 = new PaymentRow(s2);
		PaymentRow r3 = new PaymentRow(s3);

		PaymentFile file = new PaymentFile();
		file.addRow(r1);
		file.addRow(r2);
		file.addRow(r3);
		System.out.println("FILE:\n" + file);
		
		String filename="payments_" + Timestamp.getUniqueTimestamp() + ".csv";
		
		BufferedWriter writer;
		try {
			
			// write to the file
			String fullFileName = System.getProperty("user.dir") + "\\files\\bulk\\payment" + "\\"+ filename;
			writer = new BufferedWriter(new 
					FileWriter(fullFileName));
			writer.write(file.toString());
		    writer.close();

		    // upload the file to the server location
		    //IONFileUploader.uploadFile(fullFileName, "sftp-backend", "/home/eir-logistics-backend/logistics");
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		// print the file contents
		System.out.println(file);
	}

	
	@BeforeMethod
	public void setUp() {

	}

	@AfterMethod
	public void tearDown() {

	}

	public void waitForSeconds(int seconds) {
		try {
			Thread.sleep(seconds * 1000);
		} catch (InterruptedException ex) {
			Thread.currentThread().interrupt();
		}
	}
}
