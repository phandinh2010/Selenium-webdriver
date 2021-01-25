package API;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
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

public class Topic_13__Window {
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

	// @Test
	public void TC_01_Window_2Tab() {
		driver.get("https://automationfc.github.io/basic-form/index.html");
		driver.manage().window().maximize();
		String parenWindowID = driver.getWindowHandle();
		clickToElement(By.xpath("//a[text()='GOOGLE']"));
		sleepInSecond(2);
		switchToWindowByID(parenWindowID);
		Assert.assertTrue(driver.findElement(By.xpath("//img[@id='hplogo']")).isDisplayed());

	}

	//@Test
	public void TC_02_Window_Geater2Tab() {
		driver.get("https://automationfc.github.io/basic-form/index.html");
		driver.manage().window().maximize();
		String parenWindowID = driver.getWindowHandle();
		clickToElement(By.xpath("//a[text()='GOOGLE']"));
		sleepInSecond(2);
		switchToWindowByTitle("Google");
		Assert.assertTrue(driver.findElement(By.xpath("//img[@id='hplogo']")).isDisplayed());
		
		driver.switchTo().window(parenWindowID);
		clickToElement(By.xpath("//a[text()='FACEBOOK']"));
		sleepInSecond(2);
		switchToWindowByTitle("Facebook - Đăng nhập hoặc đăng ký");
		Assert.assertTrue(driver.findElement(By.xpath("//button[@name='login']")).isDisplayed());
		closeAllWindowsWithoutParent(parenWindowID);
		Assert.assertEquals(driver.getCurrentUrl(), "https://automationfc.github.io/basic-form/index.html");
		
	}
	
	//@Test
	public void TC_03_Window_Tab() {
		driver.get("https://kyna.vn/");
		String parentID = driver.getWindowHandle();
		clickToElementByJS(By.xpath("//div[@id='k-footer']//img[@alt='facebook']"));
		switchToWindowByTitle("Kyna.vn - Trang chủ | Facebook");
		Assert.assertEquals(driver.getCurrentUrl(), "https://www.facebook.com/kyna.vn");
		
		driver.switchTo().window(parentID);		
		clickToElementByJS(By.xpath("//div[@id='k-footer']//img[@alt='youtube']"));
		switchToWindowByTitle("Kyna.vn - YouTube");
		Assert.assertEquals(driver.getCurrentUrl(), "https://www.youtube.com/user/kynavn");
		
		driver.switchTo().window(parentID);
		clickToElementByJS(By.xpath("//div[@id='k-footer']//img[@alt='zalo']"));
		switchToWindowByTitle("Zalo Official Account");
		Assert.assertEquals(driver.getCurrentUrl(), "https://zalo.me/1985686830006307471");
		
		closeAllWindowsWithoutParent(parentID);
		Assert.assertEquals(driver.getTitle(), "Kyna.vn - Học online cùng chuyên gia");
	}
	
	@Test
	public void TC_04_Window_Tab() {
		driver.get("http://live.demoguru99.com/index.php/");
		String parentID = driver.getWindowHandle();
		clickToElement(By.xpath("//a[text()='Mobile']"));
		clickToElement(By.xpath("//a[@title='Sony Xperia']/parent::h2/following-sibling::div[@class='actions']//a[text()='Add to Compare']"));
		Assert.assertEquals(driver.findElement(By.xpath("//li[@class='success-msg']//span")).getText(), "The product Sony Xperia has been added to comparison list.");
		
		clickToElement(By.xpath("//a[@title='Samsung Galaxy']/parent::h2/following-sibling::div[@class='actions']//a[text()='Add to Compare']"));
		Assert.assertEquals(driver.findElement(By.xpath("//li[@class='success-msg']//span")).getText(), "The product Samsung Galaxy has been added to comparison list.");
	  clickToElement(By.xpath("//button[@title='Compare']"));
	  
	  switchToWindowByID(parentID);
	  Assert.assertEquals(driver.getTitle(), "Products Comparison List - Magento Commerce");
	  driver.close();
	  driver.switchTo().window(parentID);
	  clickToElement(By.xpath(" //a[text()='Clear All']"));
	  alert = driver.switchTo().alert();
	  alert.accept();
	  
	  Assert.assertEquals(driver.findElement(By.xpath("//li[@class='success-msg']//span")).getText(), "The comparison list was cleared.");
	  
	}

	
	
	
	public void clickToElementByJS(By by) {
		WebElement elm = driver.findElement(by);
		jsExecutor.executeScript("arguments[0].click();", elm);
		
	}
	public void switchToWindowByID(String parentID) {
		Set<String> allWindows = driver.getWindowHandles();
		for (String runWindow : allWindows) {
			if (!runWindow.equals(parentID)) {
				driver.switchTo().window(runWindow);
				break;
			}
		}
	}

	public void switchToWindowByTitle(String titleExpected) {
		Set<String> allWindows = driver.getWindowHandles();
		for (String runWindows : allWindows) {
			driver.switchTo().window(runWindows);
			String currentWin = driver.getTitle();
			if (currentWin.equals(titleExpected)) {
				break;
			}
		}
	}

	public void closeAllWindowsWithoutParent(String parentID) {
		Set<String> allWindows = driver.getWindowHandles();
		for (String runWindows : allWindows) {
			if (!runWindows.equals(parentID)) {
				driver.switchTo().window(runWindows);
				driver.close();
			}
		}
		driver.switchTo().window(parentID);
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