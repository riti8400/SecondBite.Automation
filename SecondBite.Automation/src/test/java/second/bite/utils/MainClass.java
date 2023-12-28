package second.bite.utils;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.lang.reflect.Method;
import java.util.Properties;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeSuite;
import io.restassured.specification.RequestSpecification;

public class MainClass {
	
	protected static RequestSpecification requestSpecification;
	protected static Properties property;
	protected static String workingDir = System.getProperty("user.dir");
	private FileInputStream input;
//	protected static JSONObject data;

	/**
	 * A method to get the setup ready
	 */
	@BeforeSuite(alwaysRun = true)
	public void setUpMethod() {
		try {
			input = new FileInputStream(workingDir + "/resources/config.properties");
			property = new Properties();
			try {
				property.load(input);
			} catch (IOException e) {
				e.printStackTrace();
			}
		} catch (FileNotFoundException fileException) {
			fileException.printStackTrace();
		}
	}
	
	@BeforeMethod(alwaysRun = true)
	public void setup(Method method) {
		String envValue = System.getenv("env");
//		logger.startTestCase(method.getName());
		requestSpecification = CommonUtilityMethods.tokenRequestSpec();
	}


}
