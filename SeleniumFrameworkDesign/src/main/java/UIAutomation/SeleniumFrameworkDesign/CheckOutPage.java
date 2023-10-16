package UIAutomation.SeleniumFrameworkDesign;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import Utility.GenericFunctions;

public class CheckOutPage extends GenericFunctions{
	
	WebDriver driver;
	public CheckOutPage(WebDriver driver)
	{
		super(driver);
		this.driver=driver;
		PageFactory.initElements(driver, this);
	}
	
	@FindBy(css="input[placeholder='Select Country']")
	WebElement countryDetails;
	
	@FindBy(css=".ta-results button:nth-child(3)")
	WebElement clickOnCountry;
	
	@FindBy(css="a[class*='action__submit']")
	WebElement submitProduct;
	
	By countryDropDown = By.cssSelector(".ta-results");
	
	
	public void enterCountryDetail(String country)
	{
		countryDetails.sendKeys(country);
		explicitWait(countryDropDown);
	}
	
	public void clickOnCountryDetails()
	{
		clickOnCountry.click();
	}
	
	public ConfirmationPage submitOrder()
	{
		submitProduct.click();
		ConfirmationPage orderConfirmed = new ConfirmationPage(driver);
		return orderConfirmed;
	}
	
	
	
	

}
