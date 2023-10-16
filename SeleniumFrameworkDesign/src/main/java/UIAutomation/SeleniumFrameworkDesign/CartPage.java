package UIAutomation.SeleniumFrameworkDesign;

import java.util.List;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import Utility.GenericFunctions;

public class CartPage extends GenericFunctions{
	
	WebDriver driver;
	public CartPage(WebDriver driver)
	{
		super(driver);
		this.driver=driver;
	}
	
	@FindBy(css=".cartSection h3")
	List<WebElement> productsInCart;
	
	@FindBy(css=".totalRow button")
	WebElement checkout;
	
	public boolean verifyProductsInCart(String productName)
	{
		boolean match = productsInCart.stream().anyMatch(cartProduct->cartProduct.getText().equalsIgnoreCase(productName));
		return match;
	}
	
	public CheckOutPage goToCheckoutPage()
	{
		checkout.click();
		CheckOutPage page = new CheckOutPage(driver);
		return page;
	}
	
	
	
	

}
