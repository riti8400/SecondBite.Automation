package second.bite.utils;

import io.restassured.builder.RequestSpecBuilder;
import io.restassured.http.ContentType;
import io.restassured.specification.RequestSpecification;

public class CommonUtilityMethods extends MainClass {
	
	/**
	 * A method to set request specifications for SF Token API
	 */
	public static RequestSpecification tokenRequestSpec() {
		RequestSpecBuilder builder = new RequestSpecBuilder();
		builder.setBaseUri(System.getenv("baseUri"));
		builder.setContentType(ContentType.JSON);
		return builder.build();
	}

}
