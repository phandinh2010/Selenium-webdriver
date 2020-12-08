package API;

import org.testng.annotations.Test;
import org.testng.annotations.BeforeClass;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;


public class Topic_06_Element {
	WebDriver driver;
	
	@BeforeClass
	  public void beforeClass() {
		driver = new ChromeDriver();
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
		driver.get("http://live.demoguru99.com");		
	  }
	
	@Test
  public void verify_Url() {

		driver.findElement(By.xpath("//div[@class='footer']//a[@title='My Account']")).click();
		sleepInSecond(3);
		String Url_loginPage = driver.getCurrentUrl();
		Assert.assertEquals(Url_loginPage, "http://live.demoguru99.com/index.php/customer/account/login/");
		
		driver.findElement(By.xpath("//span[(text()='Create an Account')]")).click();
		sleepInSecond(3);
		String Url_createAccount = driver.getCurrentUrl();
		Assert.assertEquals(Url_createAccount, "http://live.demoguru99.com/index.php/customer/account/create/");
		
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
