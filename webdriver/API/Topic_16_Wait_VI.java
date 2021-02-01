package API;

import org.testng.annotations.Test;
import org.testng.annotations.BeforeClass;
import org.testng.Assert;
import org.testng.AssertJUnit;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.AfterClass;

public class Topic_16_Wait_VI {
	WebDriver driver;
	JavascriptExecutor jsExecutor;
	WebDriverWait explicitWait;
	String project_location = System.getProperty("user.dir");

	@BeforeClass
	public void beforeClass() {
		System.setProperty("webdriver.chrome.driver", project_location + "\\BrowserDriver\\chromedriver.exe");
		driver = new ChromeDriver();

	}

	@Test
	public void TC_10_Fount_Element() {

		// Nếu chưa thảo mãn đk có cơ chế tìm lặp lại mỗi 0.5s, nếu thỏa mãn khoogn cần
		// chờ hết timeout
		// Chờ có điều kiện: khi sử dụng thì cần gọi
		explicitWait = new WebDriverWait(driver, 15);

		// Chạy 1 lần duy nhất trước khi bắt đầu run testcase, áp dụng cho tất cả hàm
		// FindElement. Ưu tiên sử dụng implicit hơn explictit vì hàm findElement luôn tìm trước khi thực hiện gì đó
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);

		driver.get("");
		explicitWait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("xpath có tồm tại")));
		Assert.assertTrue(driver.findElement(By.xpath("")).isDisplayed());
	}

	@Test
	public void TC_11_Not_Found_Element_Implicit_And_Explicit() {

		explicitWait = new WebDriverWait(driver,5);
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);

		driver.get("");
		explicitWait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("xpath không tìm thấy")));
		Assert.assertTrue(driver.findElement(By.xpath("")).isDisplayed());
		//failse trong 10s
		//Trường hợp mixing giữ Implicit và Explicit:
		// nếu Impllicit >Explitcit: time out = timeout Implicit
		//nếu Impllicit = hoặc <Explitcit: time out = timeout Implicit + time out Explicit: nhưng do Imp và Exp chạy bất đồng bộ
		//Async nên sẽ không bằng tổng timeout 2 cái, mà timout <tổng 2 cái
	}
	
	@Test
	public void TC_12_Not_Found_Element_Only_Implicit() {
		
		
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
		
		driver.get("");
		explicitWait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("xpath không tìm thấy")));
		Assert.assertTrue(driver.findElement(By.xpath("")).isDisplayed());
		//failse trong 10s
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