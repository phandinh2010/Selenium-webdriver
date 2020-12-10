package API;

import org.testng.annotations.Test;
import org.testng.annotations.BeforeClass;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;


public class Topic_06_Element_I {
	WebDriver driver;
	
	@BeforeClass
	  public void beforeClass() {
		driver = new FirefoxDriver();
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
		driver.get("http://live.demoguru99.com");
	  }
	
	//@Test
	public void verify_Url() {		

		//driver.get("http://live.demoguru99.com");		
		driver.findElement(By.xpath("//div[@class='footer']//a[@title='My Account']")).click();
		sleepInSecond(3);
		String Url_loginPage = driver.getCurrentUrl();
		Assert.assertEquals(Url_loginPage, "http://live.demoguru99.com/index.php/customer/account/login/");
		
		driver.findElement(By.xpath("//span[(text()='Create an Account')]")).click();
		sleepInSecond(3);
		String Url_createAccount = driver.getCurrentUrl();
		Assert.assertEquals(Url_createAccount, "http://live.demoguru99.com/index.php/customer/account/create/");
		
	}
	//@Test
	public void verify_Title() {
		
		//driver.get("http://live.demoguru99.com");		
		driver.findElement(By.xpath("//div[@class='footer']//a[@title='My Account']")).click();
		sleepInSecond(3);
		String Title_loginPage = driver.getTitle();
		Assert.assertEquals(Title_loginPage, "Customer Login");
		
		driver.findElement(By.xpath("//span[(text()='Create an Account')]")).click();
		sleepInSecond(3);
		String Title_registerPage = driver.getTitle();
		Assert.assertEquals(Title_registerPage, "Create New Customer Account");
		
	}
	//@Test
  public void navigate_Function() {

		//driver.get("http://live.demoguru99.com");		
		driver.findElement(By.xpath("//div[@class='footer']//a[@title='My Account']")).click();
		sleepInSecond(3);
		
		driver.findElement(By.xpath("//a[@title='Create an Account']")).click();
		sleepInSecond(3);
		String Url_registerPage = driver.getCurrentUrl();
		Assert.assertEquals(Url_registerPage, "http://live.demoguru99.com/index.php/customer/account/create/");
		driver.navigate().back();
		String Url_current = driver.getCurrentUrl();
		Assert.assertEquals(Url_current, "http://live.demoguru99.com/index.php/customer/account/login/");
		
		driver.navigate().forward();
		Assert.assertEquals(driver.getTitle(), "Create New Customer Account");
  }
	
	@Test
	public void get_PageSource() {
		driver.findElement(By.xpath("//div[@class='footer']//a[@title='My Account']")).click();
		sleepInSecond(3);
		String page_Source = driver.getPageSource();
		Assert.assertTrue(page_Source.contains("Login"));
		
		driver.findElement(By.xpath("//a[@title='Create an Account']")).click();
		Assert.assertTrue(driver.getPageSource().contains("Create an Account"));
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
	  driver.quit();  }

}
