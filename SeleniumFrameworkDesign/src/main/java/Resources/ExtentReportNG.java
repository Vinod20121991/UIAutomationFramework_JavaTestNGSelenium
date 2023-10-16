package Resources;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;

public class ExtentReportNG {
	
	
	public static ExtentReports getReportObject()
	{
	String path = System.getProperty("user.dir")+"//Reports//index.html";
	ExtentSparkReporter reporter = new ExtentSparkReporter(path);
	
	//Configuring the Report by using reporter object
	reporter.config().setReportName("Simple Test Report");
	reporter.config().setDocumentTitle("Launch Application");
	
	//Create ExtentReports class object which is responsible for the Entire Report execution based on the Tests
	ExtentReports extent = new ExtentReports();
	//Attach ExtentSparkReporter object to the ExtentReposts class using attachReporter() method
	extent.attachReporter(reporter);
	extent.setSystemInfo("Developer", "Vinod");
	return extent;
	
	}

}
