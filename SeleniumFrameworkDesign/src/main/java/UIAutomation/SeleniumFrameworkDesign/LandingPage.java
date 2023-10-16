package UIAutomation.SeleniumFrameworkDesign;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import Utility.GenericFunctions;

public class LandingPage extends GenericFunctions{
	
	WebDriver driver;
	
	public LandingPage(WebDriver driver)
	{
		super(driver);
		this.driver=driver;
		//to initialize driver to all the WebElements, we have to use PageFactory initElements method
		PageFactory.initElements(driver, this);
	}
	
	
	//WebElement ue=driver.findElement(By.id("userEmail"));
	//@PageFactory design pattern used to declare WebElements
	@FindBy(id="userEmail")
	WebElement userId;
	
	@FindBy(id="userPassword")
	WebElement password;
	
	@FindBy(id="login")
	WebElement submit;
	
	@FindBy(css="[class*='flyInOut']")
	WebElement errorMessage;
	
	By errorToastMessage = By.cssSelector("[class*='flyInOut']");
	//Need to implement Actions methods for the above WebElements
	//Login Action
	public void goToTheApplication()
	{
		driver.get("https://rahulshettyacademy.com/client");
		
	}
	
	public ProductCataloguePage loginApplication(String email,String pwd)
	{
		userId.sendKeys(email);
		password.sendKeys(pwd);
		submit.click();
		ProductCataloguePage catalogue = new ProductCataloguePage(driver);
		return catalogue;
	}
	
	public String getErrorMessage()
	{
		explicitWait(errorToastMessage);
		return errorMessage.getText();
	}
	
		

}
