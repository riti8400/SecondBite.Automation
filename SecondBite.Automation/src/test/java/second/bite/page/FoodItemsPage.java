package second.bite.page;

import org.testng.Assert;
import io.restassured.RestAssured;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import second.bite.utils.JsonManager;
import second.bite.utils.MainClass;

public class FoodItemsPage extends MainClass {
	
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

}
