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
	
	By nameLg = By.name("uid");
	By passLg = By.name("password");
	By loginBtn = By.name("btnLogin");
	By newCustomerLink = By.linkText("New Customer");
	By nameCtm = By.name("name");
	By birthCtm = By.id("message24");
	By addressCtm = By.name("addr");
	By stateCtm = By.name("state");
	By cityCtm = By.name("city");
	By PINCtm = By.name("pinno");
	By phoneCtm = By.name("telephoneno");
	By emailCtm = By.name("emailid");
	By passCtm = By.name("password");
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
		driver.findElement(nameLg).sendKeys("mngr300050");
		driver.findElement(passLg).sendKeys("YzumerE");
		driver.findElement(loginBtn).click();		
		Assert.assertEquals(driver.getTitle(), "Guru99 Bank Manager HomePage");
		
		driver.findElement(newCustomerLink).click();
		driver.findElement(nameCtm).sendKeys("Phan Dinh");
		driver.findElement(birthCtm).click();
		driver.findElement(birthCtm).sendKeys("10121989");
		driver.findElement(addressCtm).sendKeys("123 XXX");
		driver.findElement(cityCtm).sendKeys("Ha Noi");
		driver.findElement(addressCtm).sendKeys("Ha Noi");
		driver.findElement(stateCtm).sendKeys("Hai Ba Trung");
		driver.findElement(PINCtm).sendKeys("123456");
		driver.findElement(phoneCtm).sendKeys("0988777888");
		driver.findElement(emailCtm).sendKeys("Dinh@gmail.com");
		driver.findElement(passCtm).sendKeys("12345678");
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