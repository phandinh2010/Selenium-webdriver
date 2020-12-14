package API;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class Topic_06_Element_II {
	WebDriver driver;
	
	By emailTexboxBy = By.id("email");
	By passwordTexboxBy = By.id("pass");
  
  @BeforeClass
  public void beforeClass() {	  
	  driver = new FirefoxDriver();
	  driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
	  driver.get("https://automationfc.github.io/basic-form/index.html");	  
  }

  @Test
  public void verify_elementDisplay_01() {
	   //element = driver.findElement(By.xpath("//label[@for='mail']"));
	  driver.findElement(By.xpath("//label[@for='mail']")).isDisplayed();
	  driver.findElement(By.xpath("//label[contains(text(),'Age')]")).isDisplayed();
	  driver.findElement(By.xpath("//label[@for='under_18']")).isDisplayed();
	  driver.findElement(By.xpath("//label[@for='edu']")).isDisplayed();
	  
	  driver.findElement(By.id("mail")).sendKeys("automaiton test Dinh abc xxx");
	  driver.findElement(By.id("edu")).sendKeys("automaiton test Dinh abc xxx");
	  driver.findElement(By.id("under_18")).click();
	  
  }
  
  
  @AfterClass
  public void afterClass() {
  }

}
