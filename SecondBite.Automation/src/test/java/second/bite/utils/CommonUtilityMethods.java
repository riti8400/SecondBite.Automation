package second.bite.utils;

import java.util.ArrayList;
import java.util.Random;

import org.json.JSONObject;

import io.restassured.builder.RequestSpecBuilder;
import io.restassured.http.ContentType;
import io.restassured.specification.RequestSpecification;

public class CommonUtilityMethods extends MainClass {
	
	/**
	 * A method to set request specifications for SF Token API
	 */
	public static RequestSpecification tokenRequestSpec() {
		RequestSpecBuilder builder = new RequestSpecBuilder();
		builder.setBaseUri(property.getProperty("baseUri"));
		builder.setContentType(ContentType.JSON);
		return builder.build();
	}
	
	/**
	 * A method to fetch String Value from API response
	 */
	public static String fetchStringValueFromApiResponse(String responseBody, String key) {
		String apiResult = null;
		try {
			JSONObject jObject = new JSONObject(responseBody);
			apiResult = jObject.getString(key);
		}catch(Exception jsonException) {
			jsonException.printStackTrace();
		}
		return apiResult;
	}

	/**
	 * A method to create dynamic payload for food item
	 */
	public static String createDynamicFoodItemPayload(int limit, ArrayList<String> keys, String attribute) {
		String data = JsonManager.readJsonFile(property.getProperty("payloadJsonPath"),attribute).toString();
		JSONObject jObject = new JSONObject(data);
		for (String key : keys) {
			if(!jObject.has(key)) continue;
			switch (key){
			case "name":
				jObject.put(key, (jObject.get(key)+generateRandomNumber(limit))); break;
			case "quantity":
				jObject.put(key,generateRandomNumber(2)); break;
			default: break;
			}
		}
		return jObject.toString();
	}
	
	/**
	 * A method to generate random number as string
	 */
	public static String generateRandomNumber(int limit) {
		return String.format("%0"+limit+"d",new Random().nextInt((int)Math.pow(10,limit)));
	}

}
