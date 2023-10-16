package UIAutomation.SeleniumFrameworkDesign;

import java.time.Duration;
import java.util.Iterator;
import java.util.List;
import java.util.stream.Stream;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.Test;

import io.github.bonigarcia.wdm.WebDriverManager;

public class StandaloneTest {

//	public static void main(String[] args) {
//		// TODO Auto-generated method stub
	
		
		@Test
		public void standAloneTest() throws InterruptedException
		{
		WebDriver driver = WebDriverManager.chromedriver().create();
		driver.manage().window().maximize();
		LandingPage ld = new LandingPage(driver);
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
		//Implementing explicit wait accepts 2 arguments
		WebDriverWait wait = new WebDriverWait(driver,Duration.ofSeconds(5000));
		
		driver.get("https://rahulshettyacademy.com/client");
		
		driver.findElement(By.id("userEmail")).sendKeys("vinodnb52@gmail.com");
		driver.findElement(By.id("userPassword")).sendKeys("Welcome@2023");
		driver.findElement(By.id("login")).click();
		
		List<WebElement> products = driver.findElements(By.cssSelector("div.mb-3"));
//		WebElement prod = products.stream().filter(product->product.findElement(By.cssSelector("b")).getText().equalsIgnoreCase("zara coat 3")).findFirst().orElse(null);
//		prod.findElement(By.cssSelector(".card-body button:last-child")).click();
		
		//Implementation using for loop
//		for(int i=0;i<products.size();i++)
//		{
//			WebElement product = products.get(i);
//			String productName = product.findElement(By.cssSelector("b")).getText();
//			if(productName.equalsIgnoreCase("zara coat 3"))
//			{
//				product.findElement(By.cssSelector(".mb-3 .card-body button:last-child")).click();
//			}
//		}
		
		//implementation using iterator()
//		Iterator<WebElement> it = products.iterator();
//		while(it.hasNext())
//		{
//			WebElement product = it.next();
//			String productName = product.findElement(By.cssSelector("b")).getText();
//			if(productName.equalsIgnoreCase("zara coat 3"))
//			{
//				product.findElement(By.cssSelector(".mb-3 .card-body button:last-child")).click();
//			}
//			break;
//		}
		
		//Using Java Stream
		WebElement prod = products.stream().filter(product->product.findElement(By.cssSelector("b")).getText().equalsIgnoreCase("zara coat 3")).findFirst().orElse(null);
		prod.findElement(By.cssSelector(".card-body button:last-of-type")).click();
		wait.until(ExpectedConditions.invisibilityOfElementLocated(By.cssSelector("#toast-container")));
		driver.findElement(By.cssSelector("button[routerlink*='cart']")).click();
		List<WebElement> cartProducts = driver.findElements(By.cssSelector(".cartSection h3"));
		
		boolean pr = cartProducts.stream().anyMatch(cartProduct->cartProduct.getText().equalsIgnoreCase("zara coat 3"));
		Assert.assertTrue(pr);
		System.out.println("Item Added Present in the List");
		
		driver.findElement(By.cssSelector(".totalRow button")).click();
		
//		Actions act = new Actions(driver);
//		act.sendKeys(driver.findElement(By.cssSelector("")), "India");
		
		driver.findElement(By.cssSelector("input[placeholder='Select Country']")).sendKeys("India");
		Thread.sleep(3000);
		driver.findElement(By.cssSelector(".ta-results button:nth-child(3)")).click();
		driver.findElement(By.cssSelector("a[class*='action__submit']")).click();
		String placeOrderMessage = driver.findElement(By.cssSelector(".hero-primary")).getText();
		System.out.println(placeOrderMessage);
		boolean confirmedMessage = placeOrderMessage.equalsIgnoreCase("THANKYOU FOR THE ORDER.");
		Assert.assertTrue(confirmedMessage);
		}

		
}
