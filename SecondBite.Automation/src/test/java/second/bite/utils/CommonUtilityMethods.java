package second.bite.utils;

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


}
