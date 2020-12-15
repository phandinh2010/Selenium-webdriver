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

public class Login_TestCase_II {
	WebDriver driver;
	WebElement element;
	String project_location = System.getProperty("user.dir");
	
	By emailTextbox = By.xpath("//input[@id='mail']");
	By ageRadioBtn = By.xpath("//label[text()='Under 18']");
	By eduTextbox = By.id("edu");
	By passTextbox = By.xpath("//input[@id='new_password']");

	@BeforeClass
	public void beforeClass() {

		System.setProperty("webdriver.chrome.driver", project_location+ "\\BrowserDriver\\chromedriver.exe");
		  driver = new ChromeDriver();
		  driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
		  driver.get("https://automationfc.github.io/basic-form/index.html");	
	}

	@Test
	public void TC_01_verify_Element_isDisplay() {
		
		if(driver.findElement(emailTextbox).isDisplayed()) {
			System.out.print("Element is display");
			driver.findElement(emailTextbox).sendKeys("Automation Testing");
		}else {
			System.out.print("Element is not display");
		}
		
		sleepInSecond(3);
		
		if(driver.findElement(eduTextbox).isDisplayed()) {
			System.out.print("Element is display");
			driver.findElement(eduTextbox).sendKeys("Automation Testing");
		}else {
			System.out.print("Element is not display");
		}
		
		sleepInSecond(3);
		
		if(driver.findElement(ageRadioBtn).isDisplayed()) {
			System.out.print("Element is display");
			driver.findElement(ageRadioBtn).sendKeys("Automation Testing");
		}else {
			System.out.print("Element is not display");
		}
		
	}
		
	@Test
	public void TC_02_Element_Display() {
		driver.navigate().refresh();
		
		if(isElementDisplay(emailTextbox)) {
			driver.findElement(emailTextbox).sendKeys("automation testing");
		}
		
		if(isElementDisplay(eduTextbox)) {
			driver.findElement(emailTextbox).sendKeys("automation testing");
		}
		
		if(isElementDisplay(ageRadioBtn)) {
			driver.findElement(ageRadioBtn).click();
		}
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

	@Test
	public void TC_03_verifyElementSelected() {
		 driver.get("https://automationfc.github.io/basic-form/index.html");	
		
		driver.findElement(ageRadioBtn).click();
		driver.findElement(By.id("java")).click();
		Assert.assertTrue(driver.findElement(ageRadioBtn).isSelected());
		Assert.assertTrue(driver.findElement(By.id("java")).isSelected());
		
		driver.findElement(By.id("java")).click();
		Assert.assertFalse(driver.findElement(By.id("java")).isSelected());
	}

	@Test
	public void TC_04_mixTestcase() {
		driver.get("https://login.mailchimp.com/signup/");
		//WebElement passField = driver.findElement(By.xpath("//input[@id='new_password']"));
		
		driver.findElement(passTextbox).sendKeys("123");	
		sleepInSecond(2);
		Assert.assertEquals(driver.findElement(By.xpath("//li[@class='number-char completed']")).getAttribute("color"), "#bdbbb9");
		
		driver.findElement(passTextbox).sendKeys("A");
		sleepInSecond(2);
		Assert.assertEquals(driver.findElement(By.xpath("//li[@class='uppercase-char completed']")).getAttribute("color"), "#bdbbb9");
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