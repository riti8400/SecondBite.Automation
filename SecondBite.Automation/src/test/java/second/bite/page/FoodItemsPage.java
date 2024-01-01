package second.bite.page;

import java.util.ArrayList;
import java.util.Arrays;

import org.testng.Assert;
import io.restassured.RestAssured;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import second.bite.utils.CommonUtilityMethods;
import second.bite.utils.JsonManager;
import second.bite.utils.MainClass;

public class FoodItemsPage extends MainClass {
	
	private static final int randomNumDigitLimit = 4;
	private static ArrayList<String> dynamicFoodKeys = new ArrayList<String>(Arrays.asList("name","quantity"));
	
	public static void getFoodItems() {
		RequestSpecification httpRequest = RestAssured.given();
		requestSpecification.basePath(JsonManager.readJsonFile(property.getProperty("apiEndpointsJsonPath"),"foodItemData").getString("getFoodItemBasePath").toString());
		String token = JsonManager.readJsonFile(property.getProperty("outputValuesPath"),"token").getString("Value");
		requestSpecification.headers("Authorization","Bearer " + token);
		httpRequest.spec(requestSpecification);
		Response  response = httpRequest.get();
		System.out.println(response.getBody().asString());
		Assert.assertEquals(response.getStatusCode(), 200);
	}
	
	public static void postFoodItems() {
		RequestSpecification httpRequest = RestAssured.given();
		requestSpecification.basePath(JsonManager.readJsonFile(property.getProperty("apiEndpointsJsonPath"),"createFoodItemData").getString("postFoodItemBasePath").toString());
		String payload = CommonUtilityMethods.createDynamicFoodItemPayload(randomNumDigitLimit, dynamicFoodKeys,
				"CreateFoodItemPayload");
		String token = JsonManager.readJsonFile(property.getProperty("outputValuesPath"),"token").getString("Value");
		requestSpecification.headers("Authorization","Bearer " + token);
		httpRequest.spec(requestSpecification);
		httpRequest.body(payload);
		Response  response = httpRequest.post();
		System.out.println(response.getBody().asString());
		Assert.assertEquals(response.getStatusCode(), 200);
	}

}
