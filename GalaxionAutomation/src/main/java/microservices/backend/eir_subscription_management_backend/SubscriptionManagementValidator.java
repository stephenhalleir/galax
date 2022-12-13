package microservices.backend.eir_subscription_management_backend;

import java.util.ArrayList;

import microservices.backend.eir_subscription_management_backend.data_model.DeviceEnrollment;

public class SubscriptionManagementValidator {
	public static boolean deviceEnrollmentExists(ArrayList<DeviceEnrollment> deviceEnrollmentsFromDB, String provider, String reference) {
		for(DeviceEnrollment enr:deviceEnrollmentsFromDB) {
			if(enr.getDeviceEnrollmentProvider().equals(provider) && enr.getDeviceEnrollmentRef().equals(reference)) {
				return true;
			}
		}
		
		return false;
	}
}
