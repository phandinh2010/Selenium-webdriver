package API;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class Topic_08_Button_Radio_CheckBox {
	WebDriver driver;
	JavascriptExecutor jsExecutor;
	String project_location = System.getProperty("user.dir");
	By loginTab = By.cssSelector("");
	By email = By.xpath("");
	By pass = By.xpath("");
	By loginBtn = By.xpath("");
	By errorMsgEmail = By.xpath("");
	By errorMsgPass = By.xpath("");
	
	

	@BeforeClass
	public void beforeClass() {

		System.setProperty("webdriver.firefox.driver", project_location + "\\BrowserDriver\\chromedriver.exe");
		driver = new ChromeDriver();
		jsExecutor = (JavascriptExecutor) driver;
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
		
	}

	@Test
	public void TC_01_Button() {
		driver.get("https://www.fahasa.com/customer/account/createhttp://demo.guru99.com/v4/");
		clickToElement(loginTab);
		Assert.assertFalse(isElementEnabled(loginBtn));
		
		senkeyToElement(email, "testing@gmail.com");
		senkeyToElement(pass, "1234567");
		sleepInSecond(2);
		Assert.assertTrue(isElementEnabled(loginBtn));
		
		driver.navigate().refresh();
		clickToElement(loginTab);		
		removeDisableAttributeByJS(loginBtn);
		sleepInSecond(3);
		clickToElement(loginBtn);
		Assert.assertEquals(driver.findElement(errorMsgEmail).getText(), "");
		Assert.assertEquals(driver.findElement(errorMsgPass), "");
		

	}

	public void TC_02_defaulChecbox() {
		driver.get("http://demos.telerik.com/kendo-ui/styling/checkboxes");
		clickToElement(By.cssSelector("#eq5"));
		sleepInSecond(1);
		
		
	}
	public void clickToElement(By by) {
		WebElement element = driver.findElement(by);
		element.click();
	}
	
	public void senkeyToElement(By by, String value) {
		WebElement element = driver.findElement(by);
		element.sendKeys(value);
	}
	
	public void removeDisableAttributeByJS(By by) {
		WebElement element = driver.findElement(by);		
		jsExecutor.executeScript("arguments[0].removeAttribute('disabled')", element);
	}

	public boolean isElementEnabled (By by) {
		WebElement element = driver.findElement(by);
		if (element.isEnabled()) {
			System.out.println("Element is enabled");
			return true;	
		} else {
			System.out.println("Element is disabled");
			return false;
		}
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