package API;

import org.testng.annotations.BeforeClass;

import java.util.concurrent.TimeUnit;

import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.annotations.AfterClass;

public class Topic_02_Xpath_Css {
	WebDriver driver;
	
  
  @BeforeClass
  public void beforeClass() {
	  driver = new FirefoxDriver();
	  driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
	  driver.manage().window().maximize();
	  driver.get("http://live.demoguru99.com/index.php/customer/account/login/");
			  
  }
  
  @Test
  public void TC_01ID1() throws InterruptedException {
	  driver.findElement(By.id("email")).sendKeys("Dinhphan@gmail.com");
	  driver.findElement(By.id("pass")).sendKeys("Dinhphan@gmail.com");
	  Thread.sleep(3000);
	  
  }
  
  @Test
  public void TC_02Class() {
	  
  }
  
  @Test
  public void TC_03ID() {
	  
  }
  
  @Test
  public void TC_01ID() {
	  
  }
  
  @Test
  public void TC_01ID() {
	  
  }

  @AfterClass
  public void afterClass() {
	  driver.quit();
  }

}
