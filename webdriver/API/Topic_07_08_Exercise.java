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
		driver.findElement(By.name("name")).sendKeys("PhanDinh");
		
		sleepInSecond(3);
		//driver.findElement(By.id("dob")).click();
		driver.findElement(By.id("dob")).sendKeys("11/12/1989");
		driver.findElement(By.name("addr")).sendKeys("123 xxXXX");
		driver.findElement(By.name("city")).sendKeys("Ha Noi");
		driver.findElement(By.name("state")).sendKeys("Hai Ba Trung");
		driver.findElement(By.name("pinno")).sendKeys("123459");
		driver.findElement(By.name("telephoneno")).sendKeys("0988777888");
		driver.findElement(By.name("emailid")).sendKeys("Dinh12@gmail.com");
		driver.findElement(By.name("password")).sendKeys("123456789");
		driver.findElement(submitBtn).click();
		
		String idUser = driver.findElement(By.xpath("//td[text()='Customer ID']/following-sibling::td")).getText();
		System.out.println("ID user = " + idUser);		
		//idUser = 3873
		
		driver.findElement(By.xpath("//a[text()='Edit Customer']")).click();
		driver.findElement(By.name("cusid")).sendKeys(idUser);
		driver.findElement(By.name("AccSubmit")).click();
		
		sleepInSecond(3);
		Assert.assertEquals(driver.findElement(By.name("name")).getAttribute("value"), "PhanDinh");
		Assert.assertEquals(driver.findElement(By.name("addr")).getAttribute("value"), "123 xxXXX");
		
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
		driver.quit();
	}

}