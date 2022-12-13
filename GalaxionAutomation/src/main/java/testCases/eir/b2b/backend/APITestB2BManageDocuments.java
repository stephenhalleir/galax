package testCases.eir.b2b.backend;

import static org.testng.Assert.assertTrue;
import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertNotNull;

import java.util.ArrayList;
import java.util.Iterator;

import org.testng.ITestContext;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import framework.basetest.BaseTest;
import framework.test_data.galaxion.TestDataManager;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import microservices.backend.eir_document_management_backend.api.DocumentAPI;
import microservices.backend.eir_document_management_backend.dao.DocumentDAO;
import microservices.backend.eir_document_management_backend.data_model.Document;
import microservices.backend.eir_document_management_backend.enums.DocumentType;
import microservices.backend.eir_subscription_management_backend.SubscriptionManagementDAO;
import utilities.generic.files.PDFWriter;
import utilities.generic.files.TextReader;
import utilities.generic.time.Timestamp;
 
public class APITestB2BManageDocuments extends BaseTest {

	private int accountID;
	private String template="templates\\documents\\DocumentTemplate.txt";
	
	@Test(enabled = true, description = "B2B > Manage Documents > Upload Document [CONTRACT]", invocationCount = 1)
	public void testB2BUploadDocumentContract(ITestContext iTestContext) {
		uploadDocumentViaBackend(DocumentType.CONTRACT);
	}
	
	@Test(enabled = true, description = "B2B > Manage Documents > Upload Document [AUTHORIZED_PERSON_PROOF]", invocationCount = 1)
	public void testB2BUploadDocumentAuthorizedPersonProof(ITestContext iTestContext) {
		uploadDocumentViaBackend(DocumentType.AUTHORIZED_PERSON_PROOF);
	}
	
	@Test(enabled = true, description = "B2B > Manage Documents > Upload Document [MANIFEST]", invocationCount = 1)
	public void testB2BUploadDocumentManifest(ITestContext iTestContext) {
		uploadDocumentViaBackend(DocumentType.MANIFEST);
	}
	
	@Test(enabled = true, description = "B2B > Manage Documents > Upload Document [BILLING_CONTROL_AGREEMENT]", invocationCount = 1)
	public void testB2BUploadDocumentBillingControlAgreement(ITestContext iTestContext) {
		uploadDocumentViaBackend(DocumentType.BILLING_CONTROL_AGREEMENT);
	}
	
	@Test(enabled = true, description = "B2B > Manage Documents > Upload Document [SEPA_PAYMENT_MANDATE]", invocationCount = 1)
	public void testB2BUploadDocumentSepaPaymentMandate(ITestContext iTestContext) {
		uploadDocumentViaBackend(DocumentType.SEPA_PAYMENT_MANDATE);
	}
	
	@Test(enabled = true, description = "B2B > Manage Documents > Upload Document [TAX_EXEMPTION_CERT]", invocationCount = 1)
	public void testB2BUploadDocumentTaxExemptionCert(ITestContext iTestContext) {
		uploadDocumentViaBackend(DocumentType.TAX_EXEMPTION_CERT);
	}
	
	@Test(enabled = false, description = "B2B > Manage Documents > Upload Document (Data Driven)",  dataProvider = "document-types", invocationCount = 1)
	public void testB2BUploadDocumentDataDriven(ITestContext iTestContext, String documentType) {
		DocumentType docType = DocumentType.valueOf(documentType);
		uploadDocumentViaBackend(docType);
	}

	/**
	 * upload a document via the backend
	 * @param docType - the document type - e.g CONTRACT, MANIFEST, etc
	 */
	public void uploadDocumentViaBackend(DocumentType type) {
		
		// generate a new PDF document
		String filepath = "files\\documents\\" + Timestamp.getTimestamp("yyyyMMddhhmmss") + "_Document_" + accountID + "_" + type.toString() + ".pdf";
		String content=TextReader.getContent(template);
		content=content.replace("$accountID", Integer.toString(accountID));
		content=content.replace("$documentType", type.toString());
		content=content.replace("$timestamp", Timestamp.getTimestamp("dd-MM-yyyy hh:mm:ss"));
		boolean fileCreated=PDFWriter.writePDF(content, filepath);
		
		// check that the file generation has completed ok
		assertTrue(fileCreated);
		
		// upload the document via the API
		Response r = DocumentAPI.uploadDocument("",accountID, type, filepath);
		
		// determine the ION file name from the file
		JsonPath jsonPathEvaluator = r.jsonPath();
		String internalFilename = (String) jsonPathEvaluator.get("documentFileName");
		System.err.println("--->" + internalFilename);
		
		// check that the status code is correct
		assertEquals(r.statusCode(),200);
		System.out.println(r.statusCode() + ", " + r.asString());
		
		// look up the document in the document database
		Document d = DocumentDAO.getDocumentByFilename(internalFilename);
		
		// confirm that the document has been found in the DOCUMENT table
		assertNotNull(d);
		System.out.println("Document '" + internalFilename + "' found in the database with status " + d.getDocumentStatus());
	}
	
	@DataProvider(name = "document-types")
	public Iterator<String> dataProviderDocumentTypes() {
		ArrayList<String> documentNames = DocumentDAO.getDocumentTypes();
		return documentNames.iterator();
	}
	
	@BeforeMethod
	public void setUp() {
		int billingAccountID=TestDataManager.getB2BCorporateAccount();
		accountID=SubscriptionManagementDAO.getAccountIDForBillingAccountID(billingAccountID);
	}

	@AfterMethod
	public void tearDown() {

	}
}
