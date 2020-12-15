package API;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class Login_TestCase_I {
	WebDriver driver;

	@BeforeClass
	public void beforeClass() {
		driver = new FirefoxDriver();
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
		driver.manage().window().maximize();
		driver.get("http://live.demoguru99.com/index.php/");
		driver.findElement(By.xpath("//div[@class='footer']//a[@title='My Account']")).click();
	}

	@Test
	public void TC_01_with_Empty_Username_Password() {

		driver.findElement(By.id("email")).sendKeys("");
		driver.findElement(By.id("pass")).sendKeys("");
		driver.findElement(By.name("send")).click();
		Assert.assertTrue(driver.findElement(By.xpath("//div[@id='advice-required-entry-email']")).isDisplayed());
		Assert.assertTrue(driver.findElement(By.xpath("//div[@id='advice-required-entry-pass']")).isDisplayed());
	}

	@Test
	public void TC_02_with_Invalid_Email() {
		// Login Page title login testcase
		//String loginPageTitle = driver.getTitle();
		//Assert.assertEquals(loginPageTitle, "Guru99 Bank Home Page");
		driver.findElement(By.id("email")).sendKeys("123@123");
		driver.findElement(By.id("pass")).sendKeys("12346");
		driver.findElement(By.name("send")).click();
		Assert.assertTrue(driver.findElement(By.xpath("//div[text()='Please enter a valid email address. For example johndoe@domain.com.']")).isDisplayed());
	}

	@Test
	public void TC_03_withPassInvalid() {
		// Login form displayed
		//Assert.assertTrue(driver.findElement(By.xpath("//form[@name='frmLogin']")).isDisplayed());
		driver.findElement(By.id("email")).sendKeys("automaiton@gmail.com");
		driver.findElement(By.id("pass")).sendKeys("123");
		driver.findElement(By.name("send")).click();
		Assert.assertTrue(driver.findElement(By.xpath("//div[text()='Please enter 6 or more characters without leading or trailing spaces.']")).isDisplayed());
	}

	@Test
	public void TC_04_withIncorrectEmailPass() {
		driver.findElement(By.id("email")).clear();
		driver.findElement(By.id("email")).sendKeys("automaiton@gmail.com");
		driver.findElement(By.id("pass")).sendKeys("123123123");
		driver.findElement(By.name("send")).click();
		Assert.assertTrue(driver.findElement(By.xpath("//span[text()='Invalid login or password.']")).isDisplayed());
	}
	
	
	@AfterClass
	public void afterClass() {
		driver.quit();
	}

}