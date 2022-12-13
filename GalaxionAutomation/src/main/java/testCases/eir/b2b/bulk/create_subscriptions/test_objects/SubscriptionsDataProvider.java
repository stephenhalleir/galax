package testCases.eir.b2b.bulk.create_subscriptions.test_objects;

import java.util.ArrayList;
import java.util.List;

import microservices.backend.eir_catalog_core_backend.data_model.TariffPlan;

public class SubscriptionsDataProvider {
	
	public static ArrayList<Subscription> getSubscriptions(int billingAccountNumber, List<TariffPlan> plans){
		ArrayList<Subscription> subscriptions = new ArrayList<Subscription>();

		for (TariffPlan plan : plans) {
			Subscription sub = new Subscription();
			sub.setAccountNumber(billingAccountNumber);
			sub.setTariffCode(plan.getCode());
			subscriptions.add(sub);
		}
		
		return subscriptions;
	}
}
