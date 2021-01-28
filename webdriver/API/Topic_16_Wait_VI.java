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
	Alert alert;
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
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
		
	}

	@Test
	public void TC_01_ExplicitWait() {
		driver.get("https://demos.telerik.com/aspnet-ajax/ajaxloadingpanel/functionality/explicit-show-hide/defaultcs.aspx");
		explicitWait.until(ExpectedConditions.elementToBeClickable(By.xpath("//a[text()='12']")));
		driver.findElement(By.xpath("//a[text()='12']")).click();
		
		explicitWait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(By.xpath("//a[text()='12']/parent::td[@class='rcSelected']")));
		
		System.out.println(driver.findElement(By.xpath("//legend/parent::fieldset/div/span")).getText());
	}
	
		
	
	@Test
	public void TC_03() {
		
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