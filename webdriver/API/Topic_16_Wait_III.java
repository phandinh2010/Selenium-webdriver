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

public class Topic_16_Wait_III {
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
		
		//Nếu chưa thảo mãn đk có cơ chế tìm lặp lại mỗi 0.5s, nếu thỏa mãn khoogn cần chờ hết timeout
		//Chờ có điều kiện
		explicitWait = new WebDriverWait (driver,15);
		
		//Chạy 1 lần duy nhất trước khi bắt đầu run testcase, áp dụng cho tất cả hàm FindElement
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
		
	}

	@Test
	public void TC_01() {
		driver.get("");
		explicitWait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(By.id("email")));
		driver.findElement(By.xpath("")).click();
		
		//-->fail: timeout =3 <loading =5s
	}
	
	@Test
	public void TC_02() {
		driver.get("");
		driver.findElement(By.xpath("")).click();
		//-->fail: timeout =3 <loading =5s. do overide timemout set ở before
	}
	
	
	@Test
	public void TC_03() {
		driver.get("");
		driver.manage().timeouts().implicitlyWait(15, TimeUnit.SECONDS);
		driver.findElement(By.xpath("")).click();
		//-->pas: timeout =15 >loading =5s. do overide timemout set ở before, hiện đang nhận giá trị =15
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