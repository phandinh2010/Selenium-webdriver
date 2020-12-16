package API;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class Demo_Dinh {
	WebDriver driver;
	String project_location = System.getProperty("user.dir");
	
	@BeforeClass
	public void beforeClass() {

		System.setProperty("webdriver.firefox.driver", project_location+ "\\BrowserDriver\\chromedriver.exe");
		  driver = new ChromeDriver();
		  driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
		  driver.get("http://demo.guru99.com/v4/");	
	}

	@Test
	public void TC_01_verify_Element_isDisplay() {
		String loginPageUrl = driver.getCurrentUrl();
		Assert.assertEquals(loginPageUrl, "http://demo.guru99.com/v4/");
		
		}
		
		



	
	 public void sleepInSecond (long time) {
		  try {
			Thread.sleep(time * 1000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	  }
	 
	@AfterClass
	public void afterClass() {
		driver.quit();
	}

}