package test;

import java.io.IOException;
import java.lang.reflect.Method;
import java.util.HashMap;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.By;
import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import base.BaseClass;
import pages.HomePage;

public class AutomationTest extends BaseClass{

	HomePage home = new HomePage(driver);

	private static final Logger log = LogManager.getLogger(AutomationTest.class);
	
	
	@Test(priority=0,enabled=true)
	public void addProducts()  {
		
		extent = reporter.createTest("addProducts");

		driver.get(getProperty("url"));
		
		extent.info("navigated to website"+ driver.getTitle());
		
		log.info("navigated to website"+ driver.getTitle());

		driver.manage().window().maximize();

		//scrollToAnElement(home.flights);
		
		jsClickElement(home.signIn);
		
		driver.findElement(By.name("username")).sendKeys("vinay.aftermath@gmail.com");
		
		driver.findElement(By.xpath("//button[@type='submit']")).click();
		
		waitForElementAndSendkeys(home.password,"Raptor@123");
		
		//driver.findElement(By.name("password")).sendKeys("Raptor@123");
		/*
		 * clickElement(home.login);
		 * 
		 * clickListElements(home.addToCart);
		 * 
		 * Thread.sleep(2000);
		 * 
		 * clickElement(home.cart);
		 * 
		 * printTextFromElements(home.productNames);
		 * 
		 * clickListElements(home.removeProducts);
		 */	}

	@Test(priority=1)
	public void sortProducts() throws Exception {
		
		extent = reporter.createTest("sortProducts");
		
		driver.get(getProperty("url"));
		
		log.info("navigated to website"+ driver.getTitle());

		driver.manage().window().maximize();
		
		extent.info("sample code");

		setValue(home.userName,getProperty("username"));

		setValue(home.password,getProperty("password"));

		clickElement(home.login);

		selectDropDownByValue(home.selectFilter,getProperty("filter"));

		Thread.sleep(3000);

		printTextFromElements(home.prices);

	}

	@Test(priority=2)
	public void verifySocialLinks() throws Exception {
		
		extent = reporter.createTest("verifySocailLinks");
		
		driver.get(getProperty("url"));
		
		log.info("navigated to website"+ driver.getTitle());

		driver.manage().window().maximize();

		setValue(home.userName,getProperty("username"));

		setValue(home.password,getProperty("password"));

		clickElement(home.login);
		
		scrollTillPageEnd();
		
		String parent = driver.getWindowHandle();
		
		clickAndSwitchWindows(home.socialLinks,parent);
			
	}
	
	@DataProvider(name="testData")
	public Object[][] testData(Method m) throws IOException, Exception{
		
		Object[][] data = utils.ExcelUtils.getDataFromExcel(getProperty("excel"),"login",m.getName());
		
		return data;
	}
	
	@Test(dataProvider="testData",enabled=false)
	public void login1(HashMap<String,String> data) throws Exception 
	{
		
		driver.get(getProperty("url"));

		driver.manage().window().maximize();

		setValue(home.userName,data.get("username"));

		setValue(home.password,data.get("password"));

		Thread.sleep(2000);
			
	}
	
	@Test(dataProvider="testData",enabled=false)
	public void login2(HashMap<String,String> data) throws Exception {
		driver.get(getProperty("url"));

		driver.manage().window().maximize();

		setValue(home.userName,data.get("username"));

		setValue(home.password,data.get("password"));
		
		Thread.sleep(2000);
	}
	
	@Test(dataProvider="testData",enabled=false)
	public void login3(HashMap<String,String> data) throws Exception {
		
		driver.get(getProperty("url"));

		driver.manage().window().maximize();

		setValue(home.userName,data.get("username"));

		setValue(home.password,data.get("password"));
		
		Thread.sleep(2000);
	}

}
