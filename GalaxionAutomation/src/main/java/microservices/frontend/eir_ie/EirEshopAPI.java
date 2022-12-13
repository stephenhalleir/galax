package microservices.frontend.eir_ie;

import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import microservices.backend.eir_subscription_management_backend.enums.NDDSetting;
import microservices.frontend.myeir.dto.MyEirGraphQLRequest;
import microservices.frontend.myeir.dto.PlanIDSet;
import utilities.api.RestAssuredUtil;
import utilities.generic.api.APITransaction;
import utilities.generic.pojo_generation.pojo_to_json.PojoToJsonConverter;

public class EirEshopAPI {

	static String url="https://mytt01.eir.ie/graphql/";
	
	public static APITransaction createProspect() {
		
		// build the request
		MyEirGraphQLRequest request = new MyEirGraphQLRequest();
		String query="mutation createProspect($brand: String!, $channelType: String!, $offerType: String!) {\n  prospect(\n    prospectInput: {brand: $brand, channelType: $channelType, offerType: $offerType}\n  ) {\n    prospectUuid\n    orderReference\n    __typename\n  }\n}\n";
		request.setOperationName("createProspect");
		request.setQuery(query);
		request.getVariables().setBrand("EIR");
		request.getVariables().setOfferType("DIRECT");
		request.getVariables().setChannelType("PREPAY");
		
		// get the response
		Response r = RestAssuredUtil.galaxionPost(url, PojoToJsonConverter.getJSON(request), null);
		return new APITransaction(url,r);
	}
	
	public static APITransaction getPlans() {
		
		String payload="{\"operationName\":\"getPlans\",\"variables\":{\"planIds\":[\"1PM_PREPAY_01\",\"1PM_PREPAY_02\",\"1PM_PREPAY_03\",\"1PM_PREPAY_05\"]},\"query\":\"query getPlans($planIds: [String!]) {\\n  plans(planIds: $planIds) {\\n    planId\\n    price\\n    attributes {\\n      name\\n      value\\n      sortOrder\\n      __typename\\n    }\\n    catalogAddonBundleId\\n    name\\n    __typename\\n  }\\n}\\n\"}";
		
		// get the response
		Response r = RestAssuredUtil.galaxionPost(url, payload, null);
		return new APITransaction(url,r);
	}
	
	public static APITransaction addOffer(String prospectUuid) {
		
		String payload="{\"operationName\":\"addOffer\",\"variables\":{\"prospectUuid\":\"$$prospectUuid\",\"offerName\":\"Simplicity\"},\"query\":\"mutation addOffer($prospectUuid: String!, $offerName: String!) {\\n  addOffer(prospectUuid: $prospectUuid, offerName: $offerName) {\\n    orderReference\\n    plan {\\n      planId\\n      price\\n      catalogAddonBundleId\\n      addonId\\n      attributes {\\n        name\\n        value\\n        sortOrder\\n        __typename\\n      }\\n      __typename\\n    }\\n    numberOfProducts\\n    device {\\n      id\\n      name\\n      model\\n      manufacturer\\n      variants {\\n        catalogHandsetId\\n        name\\n        colour\\n        sku\\n        inStock\\n        stockStatus\\n        carouselImages {\\n          path\\n          description\\n          __typename\\n        }\\n        image {\\n          path\\n          description\\n          __typename\\n        }\\n        __typename\\n      }\\n      handsetId\\n      url\\n      sortingOrder\\n      imageName\\n      price\\n      fromPrice\\n      details {\\n        description\\n        prices {\\n          mobileOrderType\\n          price\\n          __typename\\n        }\\n        specifications\\n        features\\n        __typename\\n      }\\n      flash {\\n        id\\n        title\\n        description\\n        shortDescription\\n        __typename\\n      }\\n      availability {\\n        status\\n        availableDate\\n        __typename\\n      }\\n      __typename\\n    }\\n    port {\\n      otherNetworkAccountNumber\\n      otherNetworkId\\n      otherNetworkName\\n      phoneNumber\\n      portStartDateTime\\n      type\\n      __typename\\n    }\\n    discount {\\n      catalogDiscountId\\n      description\\n      value\\n      discountType\\n      __typename\\n    }\\n    offerId\\n    nddPreference\\n    topUpAmount\\n    __typename\\n  }\\n}\\n\"}";
		payload=payload.replace("$$prospectUuid", prospectUuid);
		
		// get the response
		Response r = RestAssuredUtil.galaxionPost(url, payload, null);
		return new APITransaction(url,r);
	}
	
	// {"operationName":"addPlan","variables":{"prospectUuid":"346b9749-a517-4d12-9069-70b61b92ff9a","cartOfferId":10843,"planId":"1PM_PREPAY_02"},"query":"mutation addPlan($prospectUuid: String!, $cartOfferId: Int!, $planId: String!) {\n  addPlan(prospectUuid: $prospectUuid, cartOfferId: $cartOfferId, planId: $planId) {\n    orderReference\n    numberOfProducts\n    nddPreference\n    offerId\n    topUpAmount\n    device {\n      fromPrice\n      id\n      manufacturer\n      model\n      name\n      price\n      sortingOrder\n      url\n      variants {\n        colour\n        inStock\n        name\n        sku\n        stockStatus\n        __typename\n      }\n      __typename\n    }\n    plan {\n      planId\n      catalogAddonBundleId\n      addonId\n      price\n      catalogAddonBundleId\n      attributes {\n        name\n        value\n        sortOrder\n        __typename\n      }\n      __typename\n    }\n    __typename\n  }\n}\n"}
	
	// {"operationName":"getCart","variables":{"prospectUuid":"346b9749-a517-4d12-9069-70b61b92ff9a"},"query":"query getCart($prospectUuid: String!) {\n  cartDetails(prospectUuid: $prospectUuid) {\n    orderReference\n    plan {\n      planId\n      price\n      catalogAddonBundleId\n      addonId\n      attributes {\n        name\n        value\n        sortOrder\n        __typename\n      }\n      __typename\n    }\n    numberOfProducts\n    device {\n      id\n      name\n      model\n      manufacturer\n      variants {\n        catalogHandsetId\n        name\n        colour\n        sku\n        inStock\n        stockStatus\n        carouselImages {\n          path\n          description\n          __typename\n        }\n        image {\n          path\n          description\n          __typename\n        }\n        __typename\n      }\n      handsetId\n      url\n      sortingOrder\n      imageName\n      price\n      fromPrice\n      details {\n        description\n        prices {\n          mobileOrderType\n          price\n          __typename\n        }\n        specifications\n        features\n        __typename\n      }\n      flash {\n        id\n        title\n        description\n        shortDescription\n        __typename\n      }\n      availability {\n        status\n        availableDate\n        __typename\n      }\n      __typename\n    }\n    port {\n      otherNetworkAccountNumber\n      otherNetworkId\n      otherNetworkName\n      phoneNumber\n      portStartDateTime\n      type\n      __typename\n    }\n    discount {\n      catalogDiscountId\n      description\n      value\n      discountType\n      __typename\n    }\n    offerId\n    nddPreference\n    topUpAmount\n    availableTopUp\n    __typename\n  }\n}\n"}
	
	// {"operationName":"customerDetails","variables":{"prospectUuid":"346b9749-a517-4d12-9069-70b61b92ff9a"},"query":"query customerDetails($prospectUuid: String!) {\n  customerDetails(prospectUuid: $prospectUuid) {\n    isAnonymous\n    owner {\n      email\n      mobileNumber\n      person {\n        firstName\n        lastName\n        birthDate\n        __typename\n      }\n      __typename\n    }\n    deliveryAddress {\n      addressLine1\n      addressLine2\n      addressLine3\n      county\n      town\n      eircode\n      __typename\n    }\n    billingAddress {\n      addressLine1\n      addressLine2\n      addressLine3\n      county\n      town\n      eircode\n      __typename\n    }\n    securityQuestion {\n      questionCode\n      response\n      __typename\n    }\n    contactPermission {\n      allowThirdParty\n      permissionGroupResponse {\n        name\n        permissionGroup\n        permissions {\n          name\n          enabled\n          permission\n          __typename\n        }\n        __typename\n      }\n      __typename\n    }\n    __typename\n  }\n}\n"}
	
	// {"operationName":"getPortingTime","variables":{"brand":"EIR","channel":"ESHOP"},"query":"query getPortingTime($brand: String!, $channel: String!) {\n  portingTime(brand: $brand, channel: $channel) {\n    portingHours {\n      date\n      startTime\n      endTime\n      __typename\n    }\n    __typename\n  }\n}\n"}
	
	// {"operationName":"getDiscount","variables":{"offerName":"Simplicity"},"query":"query getDiscount($offerName: String!) {\n  discounts(offerName: $offerName) {\n    catalogDiscountId\n    description\n    value\n    discountType\n    __typename\n  }\n}\n"}

	// {"operationName":"securityQuestions","variables":{},"query":"query securityQuestions {\n  securityQuestions {\n    code\n    question\n    __typename\n  }\n}\n"}

	// {"operationName":"getAddress","variables":{"eirCode":"R32HN88"},"query":"query getAddress($eirCode: String!) {\n  addressList(eirCode: $eirCode) {\n    addresses {\n      addressLine1\n      addressLine2\n      addressLine3\n      town\n      county\n      eircode\n      __typename\n    }\n    __typename\n  }\n}\n"}
}
