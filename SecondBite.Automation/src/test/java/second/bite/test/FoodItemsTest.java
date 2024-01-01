package second.bite.test;

import org.testng.annotations.Test;

import second.bite.page.FoodItemsPage;
import second.bite.utils.MainClass;

public class FoodItemsTest extends MainClass{
	
	@Test (groups = {""} ,priority = 0, enabled = true, description = "Send Get Call For Food Items")
	public void getFoodItems() {
		FoodItemsPage.getFoodItems();
	}
	
	@Test (groups = {""} ,priority = 0, enabled = true, description = "Send Post Call For Food Items")
	public void postFoodItems() {
		FoodItemsPage.postFoodItems();
	}

}
