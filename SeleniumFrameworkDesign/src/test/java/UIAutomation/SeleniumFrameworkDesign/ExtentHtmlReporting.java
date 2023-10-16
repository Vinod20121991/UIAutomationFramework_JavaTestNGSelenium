package UIAutomation.SeleniumFrameworkDesign;

import org.openqa.selenium.WebDriver;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;

import io.github.bonigarcia.wdm.WebDriverManager;

public class ExtentHtmlReporting {
	
	ExtentReports extent;
	//Method to configure ExtentReports
	@BeforeTest
	public void extentReportConfiguration()
	{
//		ExtentSparkReporter Class -> responsible to configure the Report and store it in a specified path in the Project
//		It accepts path as an argument
		String path = System.getProperty("user.dir")+"//Reports//index.html";
		ExtentSparkReporter reporter = new ExtentSparkReporter(path);
		
		//Configuring the Report by using reporter object
		reporter.config().setReportName("Simple Test Report");
		reporter.config().setDocumentTitle("Launch Application");
		
		//Create ExtentReports class object which is responsible for the Entire Report execution based on the Tests
		extent = new ExtentReports();
		//Attach ExtentSparkReporter object to the ExtentReposts class using attachReporter() method
		extent.attachReporter(reporter);
		extent.setSystemInfo("Developer", "Vinod");
		
		
		
		
	}	
	
	@Test
	public void launchApplication()
	{
		extent.createTest("Application Launch"); //Once the Test is completely executed this createTest() method will load/create the Test execution in the Report
		WebDriver driver = WebDriverManager.chromedriver().create();
		driver.manage().window().maximize();
		driver.get("https://rahulshettyacademy.com/client");
		driver.close();
		extent.flush();
	}

}
