package test;

import java.io.IOException;
import java.lang.reflect.Method;
import java.util.HashMap;

import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import base.BaseClass;
import pages.HomePage;

public class AutomationTest extends BaseClass{

	HomePage home = new HomePage(driver);

	
	
	
	@Test(priority=0,enabled=true)
	public void addProducts() throws Exception {

		driver.get(getProperty("url"));

		driver.manage().window().maximize();

		setValue(home.userName,getProperty("username"));

		setValue(home.password,"test");

		clickElement(home.login);

		clickListElements(home.addToCart);

		Thread.sleep(2000);

		clickElement(home.cart);

		printTextFromElements(home.productNames);

		clickListElements(home.removeProducts);
	}

	@Test(priority=1,enabled=true)
	public void sortProducts() throws Exception {

		driver.get(getProperty("url"));

		driver.manage().window().maximize();

		setValue(home.userName,getProperty("username"));

		setValue(home.password,getProperty("password"));

		clickElement(home.login);

		selectDropDownByValue(home.selectFilter,getProperty("filter"));

		Thread.sleep(3000);

		printTextFromElements(home.prices);

	}

	@Test(priority=2,enabled=false)
	public void verifySocialLinks() throws Exception {
		
		driver.get(getProperty("url"));

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
	
	@Test(dataProvider="testData")
	public void login1(HashMap<String,String> data) throws Exception 
	{
		
		driver.get(getProperty("url"));

		driver.manage().window().maximize();

		setValue(home.userName,data.get("username"));

		setValue(home.password,data.get("password"));

		Thread.sleep(2000);
			
	}
	
	@Test(dataProvider="testData")
	public void login2(HashMap<String,String> data) throws Exception {
		driver.get(getProperty("url"));

		driver.manage().window().maximize();

		setValue(home.userName,data.get("username"));

		setValue(home.password,data.get("password"));
		
		Thread.sleep(2000);
	}
	
	@Test(dataProvider="testData")
	public void login3(HashMap<String,String> data) throws Exception {
		
		driver.get(getProperty("url"));

		driver.manage().window().maximize();

		setValue(home.userName,data.get("username"));

		setValue(home.password,data.get("password"));
		
		Thread.sleep(2000);
	}

}
