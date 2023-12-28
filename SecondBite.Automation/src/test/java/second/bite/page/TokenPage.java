package second.bite.page;

import io.restassured.RestAssured;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import second.bite.utils.JsonManager;
import second.bite.utils.MainClass;

public class TokenPage extends MainClass{
	
	public static void sendPostToToken() {
		RequestSpecification httpRequest = RestAssured.given();
		requestSpecification.basePath(JsonManager.readJsonFile(property.getProperty("apiEndpointsJsonPath"),"sfTokenData").getString("postSfTokenBasePath").toString());
		String payload = JsonManager.readJsonFile(property.getProperty("payloadJsonPath"), "LoginPayload").toString();
		requestSpecification.body(payload);
		Response  response = httpRequest.post();
	}

}
