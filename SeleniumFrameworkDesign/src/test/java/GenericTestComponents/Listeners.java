package GenericTestComponents;

import java.io.IOException;

import org.openqa.selenium.WebDriver;
import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;

import Resources.ExtentReportNG;

public class Listeners extends BaseTest implements ITestListener{
	
	ExtentTest test;
	ExtentReports extent=ExtentReportNG.getReportObject();
	
	//Implementing Thread Safe in the Framework to avoid concurrency issues using ThreadLocal class of Java
	ThreadLocal<ExtentTest> extentTest = new ThreadLocal<ExtentTest>();
	@Override
	public void onTestStart(ITestResult result) {
		
	test = extent.createTest(result.getMethod().getMethodName());
	//It creates unique threadId for each Tests
	extentTest.set(test);
	}

	@Override
	public void onTestSuccess(ITestResult result) {
		extentTest.get().pass(result.getThrowable());
//		test.log(Status.PASS, "Test Passed");
	}

	@Override
	public void onTestFailure(ITestResult result) {
		
//	test.fail(result.getThrowable());
		extentTest.get().fail(result.getThrowable());
		
		try {
			driver=(WebDriver) result.getTestClass().getRealClass().getField("driver").get(result.getInstance());
		} catch (Exception e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		
		//Screenshot code
		String filePath=null;
		try {
			filePath = getScreenshot(result.getMethod().getMethodName(),driver);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace(); 
		}
		
		//To add screenshot captured from the path to the ExtentReports
		extentTest.get().addScreenCaptureFromPath(filePath, result.getMethod().getMethodName());
		
	}

	@Override
	public void onTestSkipped(ITestResult result) {
		// TODO Auto-generated method stub
		ITestListener.super.onTestSkipped(result);
	}

	@Override
	public void onTestFailedButWithinSuccessPercentage(ITestResult result) {
		// TODO Auto-generated method stub
		ITestListener.super.onTestFailedButWithinSuccessPercentage(result);
	}

	@Override
	public void onTestFailedWithTimeout(ITestResult result) {
		// TODO Auto-generated method stub
		ITestListener.super.onTestFailedWithTimeout(result);
	}

	@Override
	public void onStart(ITestContext context) {
		// TODO Auto-generated method stub
		ITestListener.super.onStart(context);
	}

	@Override
	public void onFinish(ITestContext context) {
		extent.flush();
		

	}
	
	//When Add unimplemented methods notification has not display then 
	//Right Click -> Source-> Override/Implement Methods
	
	
	
	
}
