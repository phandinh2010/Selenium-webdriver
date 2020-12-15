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
	By passTextbox = By.xpath("//input[@placeholder='Textbox is disabled']");
	By job1Textbox = By.id("job1");
	By job2Textbox = By.id("job2");
	By interestsDevelopmentTextbox = By.xpath("//label[@for='development']");
	By ageRadioBtn2 = By.xpath("//label[@for='radio-disabled']");
	By bioTextArea = By.id("bio");
	By javaCheckBox = By.xpath("//label[@for='java']");
	By pass = By.id("new_password");
	

	@BeforeClass
	public void beforeClass() {

		System.setProperty("webdriver.chrome.driver", project_location+ "\\BrowserDriver\\chromedriver.exe");
		  driver = new ChromeDriver();
		  driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
		  driver.get("https://automationfc.github.io/basic-form/index.html");	
	}

	//@Test
	public void TC_01_verify_Element_isDisplayed() {
		
		if(driver.findElement(emailTextbox).isDisplayed()) {
			System.out.print("Element is displayed");
			driver.findElement(emailTextbox).sendKeys("Automation Testing");
		}else {
			System.out.print("Element is not display");
		}
		
		sleepInSecond(3);
		
		if(driver.findElement(eduTextbox).isDisplayed()) {
			System.out.print("Element is displayed");
			driver.findElement(eduTextbox).sendKeys("Automation Testing");
		}else {
			System.out.print("Element is not display");
		}
		
		sleepInSecond(3);
		
		if(driver.findElement(ageRadioBtn).isDisplayed()) {
			System.out.print("Element is display");
			driver.findElement(ageRadioBtn).click();
		}else {
			System.out.print("Element is not display");
		}
		
	}
		
	//@Test
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

	@Test
	public void TC_03_verifyElementEnabled() {
		 driver.get("https://automationfc.github.io/basic-form/index.html");
		 driver.navigate().refresh();
		 
		 Assert.assertTrue(isElementEnabled(emailTextbox));
		 Assert.assertTrue(isElementEnabled(ageRadioBtn));
		 Assert.assertTrue(isElementEnabled(eduTextbox));
		 Assert.assertTrue(isElementEnabled(job1Textbox));
		 Assert.assertTrue(isElementEnabled(job2Textbox));
		 Assert.assertTrue(isElementEnabled(interestsDevelopmentTextbox));
		 
		Assert.assertFalse(isElementEnabled(passTextbox));
		 //Assert.assertFalse(isElementEnabled(ageRadioBtn2));
		 //Assert.assertFalse(isElementEnabled(bioTextArea));		
	}

	@Test
	public void TC_04_Element_Selected() {
		driver.get("https://automationfc.github.io/basic-form/index.html");
		driver.navigate().refresh();
		driver.findElement(ageRadioBtn).click();
		driver.findElement(javaCheckBox).click();
		
		sleepInSecond(3);
		Assert.assertTrue(isElementSelected(ageRadioBtn));
		Assert.assertTrue(isElementSelected(javaCheckBox));
	}
	
	@Test
	public void TC_05_Validate_Register_Form() {
		By signUpBtn = By.id("create-account");
		By verifyOneNumber = By.xpath("//li[@class='number-char completed']");
		By verifyOneUppercase = By.xpath("//li[@class='uppercase-char completed']");
		driver.get("https://login.mailchimp.com/signup/");
		//WebElement passField = driver.findElement(By.xpath("//input[@id='new_password']"));
		
		driver.findElement(pass).sendKeys("123");	
		sleepInSecond(2);
		Assert.assertFalse(isElementEnabled(signUpBtn));
		Assert.assertTrue(isElementDisplay(verifyOneNumber));
		
		driver.findElement(pass).sendKeys("A");
		sleepInSecond(2);
		Assert.assertTrue(isElementDisplay(verifyOneUppercase));
		
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