package UIAutomation.SeleniumFrameworkDesign;

import java.io.IOException;
import java.util.List;

import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.Test;

import GenericTestComponents.BaseTest;
import GenericTestComponents.RetryFailedTestcases;


public class ErrorValidations extends BaseTest{

//	public static void main(String[] args) {
//		// TODO Auto-generated method stub
	
		@Test(groups= {"smoke"},retryAnalyzer=RetryFailedTestcases.class)
		public void loginErrors() throws InterruptedException, IOException
		{
		login.loginApplication("vinodnb520@gmail.com", "Welcome@2023");
		Assert.assertEquals("Incorrect email or password.",login.getErrorMessage());
		System.out.println("Login Error validations performed...");
		Thread.sleep(3000);
		}
				
		@Test
		public void errorValidationsInProductCatalogue()
		{
			String productName="zara coat 3";
			ProductCataloguePage catalogue=login.loginApplication("qat@gmail.com", "Welcome*123");
			List<WebElement> listOfProducts = catalogue.getListOfProducts();
			catalogue.addProductToCart(productName);
			CartPage cart=catalogue.goToCartPage();
			boolean verifyItemPresence = cart.verifyProductsInCart("zara coat 33");
			Assert.assertFalse(verifyItemPresence);
			System.out.println("Item not matched in the cart");
		}
}


