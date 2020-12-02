package API;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class Login_TestCase {
	WebDriver driver;

	@BeforeClass
	public void beforeClass() {
		driver = new FirefoxDriver();
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
		driver.manage().window().maximize();
		driver.get("http://live.demoguru99.com/");
		driver.findElements(By.xpath("//span[@class='label']"));
		driver.findElement(By.xpath("//a[@title='My Accouxxxxxxxxxxxxxx']"));
	}

	@Test
	public void TC_01_with_Empty_Username_Password() {
		driver.findElement(By.xpath("//button[@xpath='1']")).click();
		//String m = driver.getCurrentUrl(); abccccccc
		//Assert.assertEquals(loginPageUrl, "http://demo.guru99.com/v4/"); xxxx
		Assert.assertTrue(driver.findElement(By.xpath("//div[@id='advice-required-entry-email']")).isDisplayed());
		Assert.assertTrue(driver.findElement(By.xpath("//div[@id='advice-required-entry-pass']")).isDisplayed());
	}

	@Test
	public void TC_02_with_Invalid_Email() {
		// Login Page title login testcase
		//String loginPageTitle = driver.getTitle();
		//Assert.assertEquals(loginPageTitle, "Guru99 Bank Home Page");
		driver.findElement(By.xpath("//input[@id='email']")).sendKeys("123@123");
		driver.findElement(By.xpath("//input[@id='pass']")).sendKeys("12346");
		driver.findElement(By.xpath("//button[@xpath='1']")).click();
		Assert.assertTrue(driver.findElement(By.xpath("//div[text()='Please enter a valid email address. For example johndoe@domain.com.']")).isDisplayed());
	}

	@Test
	public void TC_03_withPassInvalid() {
		// Login form displayed
		//Assert.assertTrue(driver.findElement(By.xpath("//form[@name='frmLogin']")).isDisplayed());
		driver.findElement(By.xpath("//input[@id='email']")).sendKeys("automaiton@gmail.com");
		driver.findElement(By.xpath("//input[@id='pass']")).sendKeys("123");
		driver.findElement(By.xpath("//button[@xpath='1']")).click();
		Assert.assertTrue(driver.findElement(By.xpath("//div[text()='Please enter 6 or more characters without leading or trailing spaces.']")).isDisplayed());
	}

	@AfterClass
	public void afterClass() {
		driver.quit();
	}

}