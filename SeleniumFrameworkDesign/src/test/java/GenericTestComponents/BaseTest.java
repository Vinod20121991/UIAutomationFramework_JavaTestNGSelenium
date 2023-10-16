package GenericTestComponents;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.time.Duration;
import java.util.HashMap;
import java.util.List;
import java.util.Properties;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

import UIAutomation.SeleniumFrameworkDesign.LandingPage;
import io.github.bonigarcia.wdm.WebDriverManager;


public class BaseTest {
	
	
	public WebDriver driver;
	public LandingPage login;
	public Properties prop;
	public WebDriver initializeDriver() throws IOException
	{
		
		//Code to retrieve parameters from GlobalProperties.properties file
		 prop = new Properties();  //Java Class to retrive parameters from .properties file
		//FileInputStream converts File into InputStream object
		FileInputStream fis = new FileInputStream(System.getProperty("user.dir")+"//src//main//java//Resources//GlobalProperties.properties");
		//load() function accept InputStream of object
		prop.load(fis);
		
		// Used Java ternary operator ? and : 
		String browserName=System.getProperty("browser")!=null ? System.getProperty("browser") : prop.getProperty("browser");
		
		//String browserName = prop.getProperty("browser");
		
		if(browserName.contains("chrome"))
		{
			ChromeOptions options = new ChromeOptions();
			if(browserName.contains("headless"))
			{
				options.addArguments("headless");
			}
			driver=WebDriverManager.chromedriver().capabilities(options).create();
			driver.manage().window().setSize(new Dimension(1440,900));

		}
		else if(browserName.equalsIgnoreCase("firefox"))
		{
			driver=WebDriverManager.firefoxdriver().create();
		}
		else if(browserName.equalsIgnoreCase("edge"))
		{
			driver=WebDriverManager.edgedriver().create();
		}
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(4000));
		return driver;
		
	}
	
//	public List<HashMap<String, String>> getJsonDataToMap(String filePath) throws IOException
//	{
//		//readFileToString-> this method will scan entire Json file content and convert it into String variable
//		//Send File Object as an argument
//		String jsonContent = FileUtils.readFileToString(new File(filePath),StandardCharsets.UTF_8);
//		
//		//Convert String to HashMap using Jackson DataBind
//		ObjectMapper mapper = new ObjectMapper();
//		List<HashMap<String,String>> data = mapper.readValue(jsonContent, new TypeReference<List<HashMap<String,String>>>(){});
//		return data;
//	}
	
	public List<HashMap<String, String>> getDataFromJsonToMap(String filePath) throws IOException
	{
		//Read and Convert Json Data into String and store it in one String variable
		String jsonContentToString = FileUtils.readFileToString(new File(filePath), StandardCharsets.UTF_8);
		
		//Convert JSON String Array into List of Java HashMaps objects using Jackson DataBind package that is ObjectMapper
		//Converting String to List
		ObjectMapper mapper = new ObjectMapper();
		List<HashMap<String, String>> data = mapper.readValue(jsonContentToString, new TypeReference<List<HashMap<String,String>>>(){});
		return data;
		
	}
	
	public String getScreenshot(String testcaseName,WebDriver driver) throws IOException
	{
		TakesScreenshot ts = (TakesScreenshot)driver;
		File src = ts.getScreenshotAs(OutputType.FILE);
		File dest = new File(System.getProperty("user.dir")+"//Reports//"+testcaseName+".png");
		FileUtils.copyFile(src, dest);
		return System.getProperty("user.dir")+"//Reports//"+testcaseName+".png";
		
	}
	
	@BeforeMethod(alwaysRun=true)
	public LandingPage launchBrowser() throws IOException
	{
		System.out.println("Before Method executed....");
		driver=initializeDriver();
		login = new LandingPage(driver);
		login.goToTheApplication();
		return login;
		
	}
	
	@AfterMethod(alwaysRun=true)
	public void tearDown()
	{
		driver.quit();
		System.out.println("After Method is executed....");
	}
	
	

}
