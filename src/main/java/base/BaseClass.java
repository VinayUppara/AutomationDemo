package base;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.lang.reflect.Method;
import java.text.SimpleDateFormat;
import java.time.Duration;
import java.util.Date;
import java.util.List;
import java.util.Properties;
import java.util.Set;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.ITestResult;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.BeforeTest;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;

import utils.Screenshot;

public class BaseClass {


	public static WebDriver driver;

	public static WebDriverWait wait;



	public static  ExtentReports reporter;

	public  static ExtentTest extent;

	public static String REPORT_PATH="Reports";

	public  static String filePath;
	
	public  static ExtentSparkReporter sparkReporter;


	@BeforeTest
	public void setUp(){

		filePath = "reports1/DSITE_Microsite_RIN.html";

		sparkReporter = new ExtentSparkReporter(filePath);
		
		reporter = new ExtentReports();

		reporter.attachReporter(sparkReporter);

		

	}

	static {

		System.setProperty("webdriver.chrome.driver","src/test/resources/drivers/chromedriver.exe");

		driver = new ChromeDriver();
	}



	public String getProperty(String key)  {
		
		Properties prop = new Properties();
		
		try {


		File propertiesFile = new File("src/test/resources/data.properties");//to locate properties code

		prop.load(new FileInputStream(propertiesFile));

		}
			
		catch(Exception e) {
			
			extent.info("Couldnt load the properties file");
		}
		
		return prop.getProperty(key);
	}

	public void clickElement(WebElement ele) {

		ele.click();

	}
	
	public void jsClickElement(WebElement e) {
		
		((JavascriptExecutor)driver).executeScript("arguments[0].click();",e);
	}

	public void setValue(WebElement ele, String value) {

		ele.clear();
		ele.sendKeys(value);
	}

	public void waitForElementAndClick(WebElement ele) {

		wait = new WebDriverWait(driver,Duration.ofSeconds(10));

		wait.until(ExpectedConditions.visibilityOf(ele));

		ele.click();
	}

	public void waitForElementAndSendkeys(WebElement ele, String value) {

		wait = new WebDriverWait(driver,Duration.ofSeconds(10));

		wait.until(ExpectedConditions.visibilityOf(ele));

		setValue(ele,value);
	}

	public void clickListElements(List<WebElement> ele) throws InterruptedException {

		for(WebElement e: ele) {
			clickElement(e);
			Thread.sleep(1000);
		}
	}

	public void printTextFromElements(List<WebElement> elements) {

		for(WebElement e : elements) {

			System.out.println(e.getText());
		}
	}

	public void selectDropDownByValue(WebElement element, String value) {

		Select select = new Select(element);

		select.selectByValue(value);


	}

	public void switchToChildWindow(String parent) {


		Set<String> windowhandles = driver.getWindowHandles(); // 2 handles

		for(String window : windowhandles) { // go through the set of window handles


			if(!window.equals(parent)) {

				driver.switchTo().window(window); // switch to child

				System.out.println(driver.getTitle()); //perform some action on child window

				driver.close(); // close child

				// switch to child
			}

		}
		driver.switchTo().window(parent);

	}

	public void scrollToAnElement(WebElement ele) {


		((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView();",ele);
	}

	public void scrollTillPageEnd() {

		((JavascriptExecutor)driver).executeScript("window.scrollTo(0,document.body.scrollHeight)");
	}


	public void clickAndSwitchWindows(List<WebElement> ele, String parent) {

		for(WebElement e : ele) {

			e.click();

			switchToChildWindow(parent);
		}

	}


	public void takeScreenshot() throws IOException {

		SimpleDateFormat simpleDateFormat=new SimpleDateFormat("dd_MM_yyyy_hh_mm_ss"); 
		String date=simpleDateFormat.format(new Date()); 
		File f = ((TakesScreenshot)driver).getScreenshotAs(OutputType.FILE);
		FileUtils.copyFile(f, new File("./Screenshots/"+date+".png"));

	}


	@AfterMethod()
	public void afterMethod(ITestResult result) throws Exception {

		if(ITestResult.FAILURE==result.getStatus()) {

			Screenshot.takeScreenshot(driver, "FAILURE", result.getName());
			
			extent.fail("Method failed "+result.getName());
		} 
		else 
		{

			Screenshot.takeScreenshot(driver, "SUCCESS", result.getName());
		}
		
		reporter.flush();

		
	}
	
	
	public void developMethod() {
		
		
		
		System.out.println("im a master method");
		
		
		
	}



	@AfterSuite
	public void closeDriver() {

		

		
		driver.close();
	}
}
