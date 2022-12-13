package microservices.backend.eir_contact_management_backend.validator;

import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertNotEquals;

import java.util.ArrayList;

import microservices.backend.eir_contact_management_backend.data_model.ContactManagementPermission;
import microservices.backend.eir_contact_management_backend.enums.PermissionCode;
import microservices.backend.eir_contact_management_backend.enums.PermissionGroupCode;
import selenium.pages.eir_prepay.customer_care.CRMLandingPage;
import selenium.pages.gomo.csr_ui.CSRDetailsPage;
import selenium.pages.gomo.my_gomo.MyAccountMyProfilePage;

public class ContactPreferencesValidator {

	/**
	 * Determine whether a list of permissions objects contains a particular
	 * permission
	 * 
	 * @param permissionsList
	 * @param permissionCode
	 * @param permissionGroupCode
	 * @return true/false
	 */
	public static boolean contactPreferencesContains(ArrayList<ContactManagementPermission> permissionsList, PermissionCode permissionCode,
			PermissionGroupCode permissionGroupCode) {
		for (ContactManagementPermission permission : permissionsList) {
			if (permissionCode.toString().equals(permission.getPermissionCode())
					&& permissionGroupCode.toString().equals(permission.getPermissionGroupCode())) {
				return true;
			}
		}
		return false;
	}

	public static boolean isOptedOut(ArrayList<ContactManagementPermission> permissions, PermissionGroupCode group) {
		for(ContactManagementPermission permission:permissions) {
			if(permission.getPermissionGroupCode().equals(group.toString())) {
				return false;
			}
		}
		return true;
	}
	
	/**
	 * Verify that the permissions before and after have changed from true <--> false
	 * @param permissionsBefore
	 * @param permissionsAfter
	 */
	public static void checkAllContactPreferencesChanged(ArrayList<ContactManagementPermission> permissionsBefore, ArrayList<ContactManagementPermission> permissionsAfter, PermissionGroupCode permissionGroup) {

		assertNotEquals(
				ContactPreferencesValidator.contactPreferencesContains(permissionsBefore, PermissionCode.ALLOW_EMAIL_CONTACT,
						permissionGroup),
				ContactPreferencesValidator.contactPreferencesContains(permissionsAfter, PermissionCode.ALLOW_EMAIL_CONTACT,
						permissionGroup));
		
		assertNotEquals(
				ContactPreferencesValidator.contactPreferencesContains(permissionsBefore, PermissionCode.ALLOW_SMS_CONTACT, permissionGroup),
				ContactPreferencesValidator.contactPreferencesContains(permissionsAfter, PermissionCode.ALLOW_SMS_CONTACT, permissionGroup));
		
		assertNotEquals(
				ContactPreferencesValidator.contactPreferencesContains(permissionsBefore, PermissionCode.ALLOW_DIRECT_MAIL_CONTACT,
						permissionGroup),
				ContactPreferencesValidator.contactPreferencesContains(permissionsAfter, PermissionCode.ALLOW_DIRECT_MAIL_CONTACT,
						permissionGroup));
		assertNotEquals(
				ContactPreferencesValidator.contactPreferencesContains(permissionsBefore, PermissionCode.ALLOW_PHONE_CONTACT,
						permissionGroup),
				ContactPreferencesValidator.contactPreferencesContains(permissionsAfter, PermissionCode.ALLOW_PHONE_CONTACT,
						permissionGroup));
		
		assertNotEquals(
				ContactPreferencesValidator.contactPreferencesContains(permissionsBefore, PermissionCode.ALLOW_FOTS_CONTACT,
						permissionGroup),
				ContactPreferencesValidator.contactPreferencesContains(permissionsAfter, PermissionCode.ALLOW_FOTS_CONTACT,
						permissionGroup));
	}

	/**
	 * Verify that the contact preferences are displayed correctly on the MyGoMo screen
	 * 
	 * @param myProfilePage - page object
	 * @param permissions - list of permissions retrieved from the database
	 * @param permissionGroup - enum - ACTIVE_CUSTOMER or NO_LONGER_CUSTOMER
	 */
	public static void checkProfilePageCorrect(MyAccountMyProfilePage myProfilePage, ArrayList<ContactManagementPermission> permissions, PermissionGroupCode permissionGroup) {

		assertEquals(ContactPreferencesValidator.contactPreferencesContains(permissions, PermissionCode.ALLOW_EMAIL_CONTACT, permissionGroup),
				myProfilePage.isEmailEnabled());
		assertEquals(ContactPreferencesValidator.contactPreferencesContains(permissions, PermissionCode.ALLOW_SMS_CONTACT, permissionGroup),
				myProfilePage.isSMSEnabled());
		assertEquals(ContactPreferencesValidator.contactPreferencesContains(permissions, PermissionCode.ALLOW_PHONE_CONTACT, permissionGroup),
				myProfilePage.isPhoneEnabled());
		assertEquals(ContactPreferencesValidator.contactPreferencesContains(permissions, PermissionCode.ALLOW_DIRECT_MAIL_CONTACT, permissionGroup),
				myProfilePage.isDirectMailEnabled());
		assertEquals(ContactPreferencesValidator.contactPreferencesContains(permissions, PermissionCode.ALLOW_FOTS_CONTACT, permissionGroup),
				myProfilePage.isFOTSEnabled());
	}
	
	/**
	 * Verify that the contact preferences are displayed correctly on the Prepay CRM UI screen
	 * 
	 * @param myProfilePage - page object
	 * @param permissions - list of permissions retrieved from the database
	 */
	public static void checkProfilePageCorrect(CRMLandingPage landingPage, ArrayList<ContactManagementPermission> permissions) {
		
		assertEquals(isOptedOut(permissions, PermissionGroupCode.ACTIVE_CUSTOMER),
				landingPage.isMPActiveNoContactSelected());
		assertEquals(contactPreferencesContains(permissions, PermissionCode.ALLOW_EMAIL_CONTACT, PermissionGroupCode.ACTIVE_CUSTOMER),
				landingPage.isMPActiveEmailSelected());
		System.err.println(landingPage.isMPActiveSMSSelected());
		assertEquals(contactPreferencesContains(permissions, PermissionCode.ALLOW_SMS_CONTACT, PermissionGroupCode.ACTIVE_CUSTOMER),
				landingPage.isMPActiveSMSSelected());
		assertEquals(contactPreferencesContains(permissions, PermissionCode.ALLOW_PHONE_CONTACT, PermissionGroupCode.ACTIVE_CUSTOMER),
				landingPage.isMPActivePhoneSelected());
		assertEquals(contactPreferencesContains(permissions, PermissionCode.ALLOW_DIRECT_MAIL_CONTACT, PermissionGroupCode.ACTIVE_CUSTOMER),
				landingPage.isMPActiveDirectMailSelected());
		assertEquals(contactPreferencesContains(permissions, PermissionCode.ALLOW_FOTS_CONTACT, PermissionGroupCode.ACTIVE_CUSTOMER),
				landingPage.isMPActiveFOTSSelected());
		
		System.err.println("opted out=" + isOptedOut(permissions, PermissionGroupCode.NO_LONGER_CUSTOMER));
		System.err.println("page=" + landingPage.isMPInactiveNoContactSelected());
		
		assertEquals(isOptedOut(permissions, PermissionGroupCode.NO_LONGER_CUSTOMER),
				landingPage.isMPInactiveNoContactSelected());
		assertEquals(contactPreferencesContains(permissions, PermissionCode.ALLOW_EMAIL_CONTACT, PermissionGroupCode.NO_LONGER_CUSTOMER),
				landingPage.isMPInactiveEmailSelected());
		assertEquals(contactPreferencesContains(permissions, PermissionCode.ALLOW_SMS_CONTACT, PermissionGroupCode.NO_LONGER_CUSTOMER),
				landingPage.isMPInactiveSMSSelected());
		assertEquals(contactPreferencesContains(permissions, PermissionCode.ALLOW_PHONE_CONTACT, PermissionGroupCode.NO_LONGER_CUSTOMER),
				landingPage.isMPInactivePhoneSelected());
		assertEquals(contactPreferencesContains(permissions, PermissionCode.ALLOW_DIRECT_MAIL_CONTACT, PermissionGroupCode.NO_LONGER_CUSTOMER),
				landingPage.isMPInactiveDirectMailSelected());
		assertEquals(contactPreferencesContains(permissions, PermissionCode.ALLOW_FOTS_CONTACT, PermissionGroupCode.NO_LONGER_CUSTOMER),
				landingPage.isMPInactiveFOTSSelected());
	}
	
	/**
	 * Check that the contact preferences are correctly displayed on a CSR UI details page
	 * @param myProfilePage
	 * @param permissions
	 * @param permissionGroup
	 */
	public static void checkProfilePageCorrect(CSRDetailsPage myProfilePage, ArrayList<ContactManagementPermission> permissions, PermissionGroupCode permissionGroup) {
		
		assertEquals(ContactPreferencesValidator.contactPreferencesContains(permissions, PermissionCode.ALLOW_EMAIL_CONTACT, permissionGroup),
				myProfilePage.isPreferenceSelectedEmail());
		assertEquals(ContactPreferencesValidator.contactPreferencesContains(permissions, PermissionCode.ALLOW_SMS_CONTACT, permissionGroup),
				myProfilePage.isPreferenceSelectedSMS());
		assertEquals(ContactPreferencesValidator.contactPreferencesContains(permissions, PermissionCode.ALLOW_PHONE_CONTACT, permissionGroup),
				myProfilePage.isPreferenceSelectedPhone());
		assertEquals(ContactPreferencesValidator.contactPreferencesContains(permissions, PermissionCode.ALLOW_DIRECT_MAIL_CONTACT, permissionGroup),
				myProfilePage.isPreferenceSelectedDirectMail());
		assertEquals(ContactPreferencesValidator.contactPreferencesContains(permissions, PermissionCode.ALLOW_FOTS_CONTACT, permissionGroup),
				myProfilePage.isPreferenceSelectedFOTS());
	}
}
