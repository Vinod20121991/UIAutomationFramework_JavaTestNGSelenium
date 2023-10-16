package UIAutomation.SeleniumFrameworkDesign;

import java.util.List;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import Utility.GenericFunctions;

public class OrdersPage extends GenericFunctions{
	
	WebDriver driver;
	public OrdersPage(WebDriver driver)
	{
		super(driver);
		this.driver=driver;
		PageFactory.initElements(driver, this);
	}
	
	@FindBy(css="tr td:nth-child(3)")
	List<WebElement> orderInOrdersPage;
	
	public boolean verifyOrderInOrdersPage(String productName)
	{
		boolean match = orderInOrdersPage.stream().anyMatch(orderMatch->orderMatch.getText().equalsIgnoreCase(productName));
		return match;
	}
	
	
	
	
	

}

