package utils;

import java.io.File;
import java.io.IOException;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;

public class Screenshot {
	
	
	public static void takeScreenshot(WebDriver driver, String status, String name ) throws Exception {
		
		File f = ((TakesScreenshot)driver).getScreenshotAs(OutputType.FILE);
		
		if(status.equalsIgnoreCase("FAILURE")) {
			
			FileUtils.copyFile(f, new File("./FailureScreenshots/"+name+".png"));
			
		} else if(status.equalsIgnoreCase("SUCCESS")) {
			
			
			FileUtils.copyFile(f, new File("./SuccessScreenshots/"+name+".png"));
		}
		
	}

}
