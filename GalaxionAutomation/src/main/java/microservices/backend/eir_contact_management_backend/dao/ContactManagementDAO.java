package microservices.backend.eir_contact_management_backend.dao;

import java.sql.Date;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import microservices.backend.eir_cdr_repository_backend.data_model.MobileUsageFile;
import microservices.backend.eir_contact_management_backend.data_model.Address;
import microservices.backend.eir_contact_management_backend.data_model.Contact;
import microservices.backend.eir_contact_management_backend.data_model.ContactManagementPermission;
import microservices.backend.eir_contact_management_backend.data_model.Email;
import microservices.backend.eir_contact_management_backend.data_model.PhoneNumber;
import microservices.backend.eir_contact_management_backend.enums.AddressType;
import microservices.backend.eir_contact_management_backend.enums.PermissionGroupCode;
import utilities.generic.database.GalaxionDBUtil;
import utilities.generic.database.MariaDBConnection;
import utilities.test.config_readers.ExcelSQLManager;

public class ContactManagementDAO {

	/**
	 * Read a contact object from the database
	 * 
	 * @param uuid
	 * @return the contact object
	 */
	public static Contact getContact(String uuid) {
		String query = ExcelSQLManager.getSQLQuery("CONTACT_MANAGEMENT", "GET_CONTACT");
		query = query.replace("$uuid", uuid);

		try {
			ResultSet rs = GalaxionDBUtil.getQueryResultSet(query);
			if (rs.next()) {
				Contact contact = new Contact(rs);

				// read address, email and phone number details
				contact.setEmailAddress(getEmailAddressForUUID(uuid));
				contact.setAddresses(getAddressesForEmail(uuid));
				contact.setPhoneNumber(getContactNumber(uuid));
				return contact;
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}

		return null;
	}
	
	public static Contact getContactOnly(String uuid) {
		String query = ExcelSQLManager.getSQLQuery("CONTACT_MANAGEMENT", "GET_CONTACT");
		query = query.replace("$uuid", uuid);

		try {
			ResultSet rs = GalaxionDBUtil.getQueryResultSet(query);
			if (rs.next()) {
				Contact contact = new Contact(rs);
				return contact;
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}

		return null;
	}

	public static Email getEmailForUuid(String contactUuid) {
		String query = ExcelSQLManager.getSQLQuery("CONTACT_MANAGEMENT", "GET_EMAIL_ADDRESS_FOR_UUID");
		query = query.replace("$uuid", contactUuid);

		try {
			ResultSet rs = GalaxionDBUtil.getQueryResultSet(query);
			while (rs.next()) {
				return new Email(rs);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null;
	}

	public static String getEmailAddressForUUID(String contactUuid) {
		Email email = ContactManagementDAO.getEmailForUuid(contactUuid);
		return email.getEmail();
	}

	public static ArrayList<Address> getAddressesForEmail(String uuid) {
		String query = ExcelSQLManager.getSQLQuery("CONTACT_MANAGEMENT", "GET_ADDRESSES").replace("$uuid", uuid);

		ArrayList<Address> addresses = new ArrayList<Address>();

		try {
			ResultSet rs = GalaxionDBUtil.getQueryResultSet(query);

			while (rs.next()) {
				addresses.add(new Address(rs));
			}

		} catch (SQLException e) {
			e.printStackTrace();
		}

		return addresses;
	}

	/**
	 * Return the contact UUID for a contact on contact management
	 * 
	 * @param emailAddress
	 * @return uuid
	 */
	public static String getUuidForEmailAddress(String emailAddress) {
		String query = ExcelSQLManager.getSQLQuery("CONTACT_MANAGEMENT", "GET_EMAIL");
		query = query.replace("$email", emailAddress);
		try {
			ResultSet rs = GalaxionDBUtil.getQueryResultSet(query);
			while (rs.next()) {
				Email email = new Email(rs);
				return email.getContactUuid();
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null;
	}

	public static Date getDOBForUuid(String uuid) {
		Contact contact = getContact(uuid);
		return contact.getBirthDate();
	}

	public static ArrayList<ContactManagementPermission> getContactPermissions(String uuid) {

		ArrayList<ContactManagementPermission> permissions = new ArrayList<ContactManagementPermission>();

		String query = ExcelSQLManager.getSQLQuery("CONTACT_MANAGEMENT", "GET_CONTACT_PREFERENCES");
		query = query.replace("$uuid", uuid);

		ResultSet rs;

		try {
			rs = GalaxionDBUtil.getQueryResultSet(query);
			while (rs.next()) {
				permissions.add(new ContactManagementPermission(rs));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}

		return permissions;
	}

	public static ArrayList<ContactManagementPermission> getContactPermissionsForGroup(String uuid, PermissionGroupCode group) {

		// read all permissions for the contact
		ArrayList<ContactManagementPermission> permissions = getContactPermissions(uuid);
		ArrayList<ContactManagementPermission> permissionsToReturn = new ArrayList<ContactManagementPermission>();

		// if the permission belongs to the specified group, add the permission to the
		// list
		for (ContactManagementPermission permission : permissions) {
			if (permission.getPermissionGroupCode().equals(group.toString())) {
				permissionsToReturn.add(permission);
			}
		}

		return permissionsToReturn;
	}

	public static ArrayList<Address> getAddresses(String contactUuid) {
		String query = ExcelSQLManager.getSQLQuery("CONTACT_MANAGEMENT", "GET_ADDRESSES");
		query = query.replace("$uuid", contactUuid);

		ResultSet rs;

		ArrayList<Address> addresses = new ArrayList<Address>();

		try {
			rs = GalaxionDBUtil.getQueryResultSet(query);
			while (rs.next()) {
				Address a = new Address(rs);
				addresses.add(a);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}

		return addresses;
	}

	public static void markEmailAddressAsVerified(String emailAddress) {
		String query = ExcelSQLManager.getSQLQuery("CONTACT_MANAGEMENT", "MARK_EMAIL_AS_VERIFIED");
		query = query.replace("$emailAddress", emailAddress);
		try {
			GalaxionDBUtil.runUpdateQuery(query);
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	/**
	 * Return an address of a particulat type - i.e. BILLING or DELIVERY
	 * 
	 * @param contactUuid
	 * @param type
	 * @return
	 */
	public static Address getAddressOfType(String contactUuid, AddressType type) {
		ArrayList<Address> addresses = ContactManagementDAO.getAddresses(contactUuid);

		for (Address address : addresses) {
			if (address.getAddressType() == type) {
				return address;
			}
		}

		return null;
	}

	public static Address getDeliveryAddress(String contactUuid) {
		return getAddressOfType(contactUuid, AddressType.DELIVERY);
	}

	public static Address getBillingAddress(String contactUuid) {
		return getAddressOfType(contactUuid, AddressType.BILLING);
	}

	public static ArrayList<PhoneNumber> getPhoneNumbers(String contactUuid){
		ArrayList<PhoneNumber> numbers = new ArrayList<PhoneNumber>();
		String query = ExcelSQLManager.getSQLQuery("CONTACT_MANAGEMENT", "GET_PHONE_NUMBERS");
		query = query.replace("$contact_uuid", contactUuid);
		try {
			ResultSet rs = GalaxionDBUtil.getQueryResultSet(query);
			while (rs.next()) {
				numbers.add(new PhoneNumber(rs));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}

		return numbers;
	}
	
	public static String getContactNumber(String contactUuid) {
		String query = ExcelSQLManager.getSQLQuery("CONTACT_MANAGEMENT", "GET_PHONE_NUMBERS");
		query = query.replace("$contact_uuid", contactUuid);
		try {
			ResultSet rs = GalaxionDBUtil.getQueryResultSet(query);
			if (rs.next()) {
				return rs.getString("phone_number");
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}

		return null;
	}

}
