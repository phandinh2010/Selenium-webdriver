package API;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.annotations.Test;

public class Topic_03_Run_On_Chrome_Firefox {
	WebDriver driver;
	String project_location = System.getProperty("user.dir");
	
	@Test
	  public void TC_01_Run_On_Chrome() {
		  //Selenium 2.53.1
		  //Chrome lasstest
		  //ChromeDriver lastest
		//System.setProperty("webdriver.chrome.driver","D:\\AutoTest_DaoMinhDam\\2. Selenium API\\BrowserDriver\\chromedriver.exe");
		//System.setProperty("webdriver.chrome.driver",".\\BrowserDriver\\chromedriver.exe");
		
		System.setProperty("webdriver.chrome.driver", project_location+ "\\BrowserDriver\\chromedriver.exe");
		  driver = new ChromeDriver();
		  driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
		  driver.get("https://automationfc.github.io/basic-form/index.html");	
		  driver.quit();
		  
	  }
  
  @Test
  public void TC_01_Run_On_FireFox() {
	  //Selenium 2.53.1
	  //Firefox 47.0.2
	  //No need geckodriver
	  driver = new FirefoxDriver();
	  driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
	  driver.get("https://automationfc.github.io/basic-form/index.html");	
	  driver.quit();
	  
  } 
  
 
}
