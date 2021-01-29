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
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class Topic_16_Wait_VI {
	WebDriver driver;
	JavascriptExecutor jsExecutor;
	WebDriverWait explicitWait;
	String project_location = System.getProperty("user.dir");

	@BeforeClass
	public void beforeClass() {
		System.setProperty("webdriver.chrome.driver", project_location + "\\BrowserDriver\\chromedriver.exe");
		driver = new ChromeDriver();
		
		//Nếu chưa thảo mãn đk có cơ chế tìm lặp lại mỗi 0.5s, nếu thỏa mãn khoogn cần chờ hết timeout
		//Chờ có điều kiện
		explicitWait = new WebDriverWait (driver,15);
		
		//Chạy 1 lần duy nhất trước khi bắt đầu run testcase, áp dụng cho tất cả hàm FindElement
		//driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
		
	}

	//@Test
	public void TC_06_ExplicitWait() {
		driver.get("https://demos.telerik.com/aspnet-ajax/ajaxloadingpanel/functionality/explicit-show-hide/defaultcs.aspx");
		driver.manage().window().maximize();
		System.out.println(driver.findElement(By.xpath("//legend/parent::fieldset/div/span")).getText());
		
		//chờ cho ngày 12 click được
		explicitWait.until(ExpectedConditions.elementToBeClickable(By.xpath("//a[text()='12']/parent::td")));	
		clickToElementByJS("//a[text()='12']/parent::td");
		//driver.findElement(By.xpath("//a[text()='12']/parent::td")).click();
		
		
		//chờ ngày đc chọn thành công
		explicitWait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//a[text()='12']/parent::td[@class='rcSelected']")));
		
		
		
		//chờ icon loading biến mất
		explicitWait.until(ExpectedConditions.invisibilityOfElementLocated(By.xpath("//div[@class='raDiv']/parent::div[not(@style='display:none;')]")));
		
		System.out.println(driver.findElement(By.xpath("//div[@id='ctl00_ContentPlaceholder1_ctl00_ContentPlaceholder1_Label1Panel']")).getText());
	}
	
		
	
	@Test
	public void TC_07() {
		driver.get("https://www.file.io/");
		driver.findElement(By.xpath("//input[@type='file']")).sendKeys(project_location + "\\UploadFiles\\cade-prior-qzv0os5eIJQ-unsplash.jpg");
		
		//chờ icon loading biến mất
		explicitWait.until(ExpectedConditions.invisibilityOfElementLocated(By.id("progress-button")));
		explicitWait.until(ExpectedConditions.visibilityOfElementLocated(By.id("download-url")));
		driver.findElement(By.id("download-url")).click();
				
	}
	
	
	
	@Test
	public void TC_09_FluentWait() {
		driver.get("https://automationfc.github.io/fluent-wait/");
		driver.findElement(By.xpath("//input[@type='file']")).sendKeys(project_location + "\\UploadFiles\\cade-prior-qzv0os5eIJQ-unsplash.jpg");
		
		//chờ icon loading biến mất
		explicitWait.until(ExpectedConditions.invisibilityOfElementLocated(By.id("progress-button")));
		explicitWait.until(ExpectedConditions.visibilityOfElementLocated(By.id("download-url")));
		driver.findElement(By.id("download-url")).click();
		
	}
	
	
	
	

	public void clickToElementByJS(String locator) {
		jsExecutor = (JavascriptExecutor) driver;
		jsExecutor.executeScript("arguments[0].click();", getElement(driver, locator));
	}
	
	public WebElement getElement(WebDriver driver, String xpathLocator) {
		return driver.findElement(By.xpath(xpathLocator));
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