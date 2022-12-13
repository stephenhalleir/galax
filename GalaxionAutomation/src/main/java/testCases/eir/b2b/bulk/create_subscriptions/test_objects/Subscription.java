package testCases.eir.b2b.bulk.create_subscriptions.test_objects;

import java.util.ArrayList;

import microservices.backend.eir_catalog_core_backend.enums.OfferCode;
import microservices.backend.eir_catalog_core_backend.enums.SubscriptionAddonBundle;
import microservices.backend.eir_catalog_core_backend.enums.TariffCode;

public class Subscription {

	private String tariffCode;
	private Handset handset;
	private boolean port;
	private int accountNumber;
	private ArrayList<String> addons;

	public Subscription() {
		tariffCode = "";
		handset = null;
		port = false;
		accountNumber = -1;
		addons = new ArrayList<String>();
	}

	public Subscription(int accountNumber, TariffCode tariffCode, Handset handset, SubscriptionAddonBundle[] bundleCodes) {

		this.accountNumber = accountNumber;
		this.handset = handset;
		this.tariffCode = tariffCode.toString().replace("_", "-");
		this.addons = new ArrayList<String>();

		if (bundleCodes != null) {
			for (int i = 0; i < bundleCodes.length; i++) {
				addSubscriptionAddon(bundleCodes[i]);
			}
		}
	}

	public String getTariffCode() {
		return tariffCode;
	}

	public void setTariffCode(String tariffCode) {
		this.tariffCode = tariffCode;
	}

	public Handset getHandset() {
		return handset;
	}

	public void setHandset(Handset handset) {
		this.handset = handset;
	}

	public boolean isPort() {
		return port;
	}

	public void setPort(boolean port) {
		this.port = port;
	}

	public int getAccountNumber() {
		return accountNumber;
	}

	public void setAccountNumber(int accountNumber) {
		this.accountNumber = accountNumber;
	}

	public ArrayList<String> getAddons() {
		return addons;
	}

	public void setAddons(ArrayList<String> addons) {
		this.addons = addons;
	}

	public void addSubscriptionAddon(SubscriptionAddonBundle bundleCode) {

		if (addons == null) {
			addons = new ArrayList<String>();
		}

		if (bundleCode != null) {
			String addon = bundleCode.toString();
			if (addon.startsWith("_")) {
				addon = addon.substring(1);
			}
			addons.add(addon);
		}
	}
}
