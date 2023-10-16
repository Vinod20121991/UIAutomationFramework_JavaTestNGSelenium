package Utility;

import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import UIAutomation.SeleniumFrameworkDesign.CartPage;
import UIAutomation.SeleniumFrameworkDesign.OrdersPage;

public class GenericFunctions {
	
	
	WebDriver driver;
	
	public GenericFunctions(WebDriver driver)
	{
		this.driver=driver;
		PageFactory.initElements(driver, this);
	}
	
	@FindBy(css="button[routerlink*='cart']")
	WebElement cartHeader;
	
	@FindBy(css="[routerlink*='myorders']")
	WebElement orderHeader;
	
	public void explicitWait(By find)
	{
		WebDriverWait wait = new WebDriverWait(driver,Duration.ofSeconds(5000));
		wait.until(ExpectedConditions.visibilityOfElementLocated(find));
	}
	
	public void explicitWaitForElementInvisibility(By elementInvisible)
	{
		WebDriverWait wait = new WebDriverWait(driver,Duration.ofSeconds(5000));
		wait.until(ExpectedConditions.invisibilityOfElementLocated(elementInvisible));
	}
	
	public CartPage goToCartPage()
	{
		cartHeader.click();
		CartPage cart = new CartPage(driver);
		return cart;
	}
	
	public OrdersPage goToOrderPage()
	{
		orderHeader.click();
		OrdersPage opage = new OrdersPage(driver);
		return opage;
	}
	

}
