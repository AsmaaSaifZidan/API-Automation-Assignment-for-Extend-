package reporting;

import com.aventstack.extentreports.ExtentReports;

public class ExtentManager {
    private static ExtentReports extent;

    public static synchronized ExtentReports getInstance() {
        if (extent == null) {
            extent = createInstance();
        }
        return extent;
    }

    private static ExtentReports createInstance() {
        String filePath = "extent.html";
        ExtentHtmlReporterCustom htmlReporter = new ExtentHtmlReporterCustom(filePath);

        ExtentReports extent = new ExtentReports();
        extent.attachReporter(htmlReporter);
        extent.setSystemInfo("Environment", "QC");
        extent.setSystemInfo("User", "Asmaa Saif Zidan");

        return extent;
    }
}
