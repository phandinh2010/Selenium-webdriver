package API;


import java.util.concurrent.TimeUnit;

import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class Topic_11_Popup_Iframe_Window {
	WebDriver driver;
	Alert alert;
	WebDriverWait explicitWait;
	JavascriptExecutor jsExecutor;
	Actions action;
	String project_location = System.getProperty("user.dir");
	@BeforeClass
	public void beforeClass() {
		System.setProperty("webdriver.chrome.driver", project_location + "\\BrowserDriver\\chromedriver.exe");
		driver = new ChromeDriver();
		explicitWait = new WebDriverWait(driver, 30);
		jsExecutor = (JavascriptExecutor) driver;
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
		
		action = new Actions(driver);
	}

	//@Test
	public void TC_01_Fixed_PopUP() {
		driver.get("https://www.zingpoll.com/");
		sleepInSecond(2);
		clickToElement(By.cssSelector("#Loginform"));
		
		explicitWait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//form[@id='loginForm']")));
		Assert.assertTrue(driver.findElement(By.xpath("//form[@id='loginForm']")).isDisplayed());
		
		clickToElement(By.xpath("//div[@id='Login']//button[@class='close']"));
		explicitWait.until(ExpectedConditions.invisibilityOfElementLocated(By.xpath("//form[@id='loginForm']")));
		Assert.assertFalse(driver.findElement(By.xpath("//form[@id='loginForm']")).isDisplayed());
		
		
		
	}

	//@Test
	public void TC_02_Fixed_PopUp() {
		driver.get("https://bni.vn/");
		explicitWait.until(ExpectedConditions.invisibilityOfElementLocated(By.xpath("//div[@id='sgpb-popup-dialog-main-div']")));
		Assert.assertFalse(driver.findElement(By.xpath("//div[@id='sgpb-popup-dialog-main-div']")).isDisplayed());
		explicitWait.until(ExpectedConditions.elementToBeClickable(By.xpath("//img[@alt='Close']")));
		clickToElement(By.xpath("//img[@alt='Close']"));
		
	}
	
	@Test
	public void TC_03_Random_PopUp_In_DOM() {
		driver.get("https://blog.testproject.io/");
		if (driver.findElement(By.xpath("//div[@class='mailch-wrap rocket-lazyload']")).isDisplayed()) {
			clickToElement(By.xpath("//div[@id='close-maich']"));			
		}
		
		explicitWait.until(ExpectedConditions.invisibilityOfElementLocated(By.xpath("//section//input[@class='search-field']")));
		driver.findElement(By.xpath("//section//input[@class='search-field']")).sendKeys("Selenium");
		explicitWait.until(ExpectedConditions.elementToBeClickable(By.xpath("//section//input[@class='search-field']")));
		clickToElement(By.xpath("//section//span[@class='glass']"));
		
	}

		
	
	
	
	
	public void clickToElement(By by) {
		WebElement element = driver.findElement(by);
		element.click();
	}
	
	public void sleepInSecond(long time) {
		try {
			Thread.sleep(time * 1000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	
	@AfterClass
	public void afterClass() {
		driver.quit();
	}

}