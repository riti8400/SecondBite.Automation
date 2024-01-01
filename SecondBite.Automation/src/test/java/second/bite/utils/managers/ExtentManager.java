package second.bite.utils.managers;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.reporter.ExtentHtmlReporter;

public class ExtentManager {
    private static ExtentReports extent;

    public static ExtentReports getInstance() {
        if (extent == null) {
            ExtentHtmlReporter htmlReporter = new ExtentHtmlReporter(
                    System.getProperty("user.dir") + "/Report/ExtentReport.html");
            extent = new ExtentReports();
            extent.attachReporter(htmlReporter);

        }

        return extent;
    }
}
