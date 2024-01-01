package second.bite.test;

import org.testng.annotations.Test;

import second.bite.page.TokenPage;
import second.bite.utils.MainClass;

public class TokenTest extends MainClass {
	
	@Test (groups = {""} ,priority = 0, enabled = true, description = "Send Post Call to Generate Token")
	public void sendPostCallToToken() {
		TokenPage.sendPostToToken();
	}

}
