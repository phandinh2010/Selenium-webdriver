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

public class Topic_07_08_Exercise {
	WebDriver driver;
	WebElement element;
	String project_location = System.getProperty("user.dir");
	
	By submitBtn = By.name("sub");	

	@BeforeClass
	public void beforeClass() {

		System.setProperty("webdriver.chrome.driver", project_location+ "\\BrowserDriver\\chromedriver.exe");
		  driver = new ChromeDriver();
		  driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
		  driver.get("http://demo.guru99.com/v4");	
	}

	@Test
	public void TC_getInfo_User() {
		driver.findElement(By.name("uid")).sendKeys("mngr300050");
		driver.findElement(By.name("password")).sendKeys("YzumerE");
		driver.findElement(By.name("btnLogin")).click();		
		Assert.assertEquals(driver.getTitle(), "Guru99 Bank Manager HomePage");
		
		driver.findElement(By.linkText("New Customer")).click();
		driver.findElement(By.name("name")).sendKeys("Phan Dinh");
		driver.findElement(By.id("message24")).click();
		driver.findElement(By.id("message24")).sendKeys("10121989");
		driver.findElement(By.name("addr")).sendKeys("123 XXX");
		driver.findElement(By.name("city")).sendKeys("Ha Noi");
		driver.findElement(By.name("state")).sendKeys("Hai Ba Trung");
		driver.findElement(By.name("pinno")).sendKeys("123456");
		driver.findElement(By.name("telephoneno")).sendKeys("0988777888");
		driver.findElement(By.name("emailid")).sendKeys("Dinh@gmail.com");
		driver.findElement(By.name("password")).sendKeys("12345678");
		driver.findElement(submitBtn).click();
		
		//driver.findElement(By.xpath("//td[text()='Customer ID']/following-sibling::td")).getText();
		System.out.println(driver.findElement(By.xpath("//td[text()='Customer ID']/following-sibling::td")).getText());
	}
		
	
	
	
	
	public boolean isElementDisplay(By by) {
		if(driver.findElement(by).isDisplayed()) {
			System.out.println("Element is displayed");
			return true;
		}	else {
			System.out.println("Element is not displayed");
			return false;
		}
	}
	
	public boolean isElementEnabled(By by) {
		if(driver.findElement(by).isEnabled()) {
			System.out.println("Element is enabled");
			return true;
		}	else {
			System.out.println("Element is not enabled");
			return false;
		}
	}
	
	public boolean isElementSelected(By by) {
		if(driver.findElement(by).isEnabled()) {
		System.out.println("Element is selected");
		return true;
		}	else {
			System.out.println("Element is not selected");
			return false;
		}
	}

	
	 public void sleepInSecond (long time) {
		  try {
			Thread.sleep(time * 1000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	  }
	 
	@AfterClass
	public void afterClass() {
		//driver.quit();
	}

}