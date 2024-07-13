package reporting;

import com.aventstack.extentreports.reporter.ExtentHtmlReporter;
import com.aventstack.extentreports.reporter.configuration.Theme;

public class ExtentHtmlReporterCustom extends ExtentHtmlReporter {

    private Object ChartLocation;

    public ExtentHtmlReporterCustom(String filePath) {
        super(filePath);
        setupConfig();
    }

    private void setupConfig() {
        this.config().setTheme(Theme.STANDARD);
        this.config().setDocumentTitle("API Automation Report");
        this.config().setReportName("API Test Report");
        String BOTTOM = null;
        this.config().setTimeStampFormat(BOTTOM);
        this.config().setTimeStampFormat("MMM dd, yyyy HH:mm:ss");
    }

    private class BOTTOM {
    }
}
