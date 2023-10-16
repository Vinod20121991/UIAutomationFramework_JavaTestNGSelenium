package UIAutomation.SeleniumFrameworkDesign;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;

import Utility.GenericFunctions;

public class ProductCataloguePage extends GenericFunctions{
	
	WebDriver driver;
	public ProductCataloguePage(WebDriver driver)
	{
		super(driver);
		this.driver=driver;
		PageFactory.initElements(driver, this);
	}	
	
	//List<WebElement> products = driver.findElements(By.cssSelector("div.mb-3"));
	//declaring WebElements of Product Catalogue page
	@FindBy(css="div.mb-3")
	List<WebElement> products;
	
	//Actions Methods
	By allProducts= By.cssSelector("div.mb-3");
	By toastMessage=By.cssSelector("#toast-container");
	
	public List<WebElement> getListOfProducts()
	{
		explicitWait(allProducts);
		return products;
	}
	
	public WebElement getProductByName(String productName)
	{
		WebElement prod = getListOfProducts().stream()
				.filter(product->product.findElement(By.cssSelector("b")).getText().equalsIgnoreCase(productName)).findFirst().orElse(null);
		return prod;
	}
	
	public void addProductToCart(String productName)
	{
		WebElement addItemToCart = getProductByName(productName);
		addItemToCart.findElement(By.cssSelector(".card-body button:last-child")).click();
		explicitWait(toastMessage);
		explicitWaitForElementInvisibility(toastMessage);
	}

}
