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

public class Topic_16_Wait_I {
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
		driver.manage().window().maximize();
	}

	@Test
	public void TC_01_Displayed_Visible() {
		driver.get("http://automationpractice.com/index.php?controller=authentication&back=my-account");
		
		//wait cho element hiển thị/visible
		//có hiện thị trong DOM và UI		
		explicitWait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(By.id("email")));
	}
	
	
	@Test
	public void TC_02_Undisplayed_Invisible_In_DOM() {
		driver.get("http://automationpractice.com/index.php?controller=authentication&back=my-account");
		
		//wait cho element không hiển thị Undisplay/Invisible
		//Không hiện thị UI nhưng có trong DOM	
		//Có sẵn trong DOm, tìm thấy luôn, pass đk luôn không cần chờ timeout
		explicitWait.until(ExpectedConditions.invisibilityOfElementLocated(By.id("email")));
	}
	
	@Test
	public void TC_03_Undisplayed_Invisible_Out_DOM() {
		driver.get("http://automationpractice.com/index.php?controller=authentication&back=my-account");
		
		//wait cho element không hiển thị Undisplay/Invisible
		//Không hiện thị UI và không có trong DOM	
		//Không tìm thấy elemennt, tìm đi tìm lại cho hết timeout của implicit -->30s.
		//sau đó mới apply điều kiện cảu explicit vào invisibility
		explicitWait.until(ExpectedConditions.invisibilityOfElementLocated(By.id("")));
	}
	
	@Test
	public void TC_04_Presence() {
		driver.get("http://automationpractice.com/index.php?controller=authentication&back=my-account");
		
		//wait cho element có trong DOM
		//Có trong DOM và có hiện trên UI = visible TC_01
		//Có trong DOM và kHông hiện trên UI =TC_02
		//presence: khong quan tâm có trên Ui hay không, chỉ cần có trong DOM
		explicitWait.until(ExpectedConditions.presenceOfAllElementsLocatedBy(By.id("")));
	}
	
	@Test
	public void TC_05_Clickable() {
		
		//Apply: btn, radio, checkbox,dropdown  -->Stable trước khi thao tác
		//exp: login form: điền username, pass. chờ cho button Login enable rồi mới click
		driver.get("https://login.mailchimp.com/");
		driver.findElement(By.id("email")).sendKeys("Dinh@gmail.com");
		driver.findElement(By.id("password")).sendKeys("Dinh");
		
		explicitWait.until(ExpectedConditions.elementToBeClickable(By.id("login")));
		driver.findElement(By.id("login")).click();
	}
	
	@Test
	public void TC_06_Staleness() {
		
		//Wait cho 1 element Stalenss không có/còn ở trong DOM
		//Exp: Thao tác với 1 element để xuất hiện error msg
		//Thao tác tiếp để error không còn xuất hiện -->cần chờ để error msg không còn có trong DOM
		driver.get("https://login.mailchimp.com/");
		driver.findElement(By.id("email")).sendKeys("Dinh@gmail.com");
		driver.findElement(By.id("password")).sendKeys("Dinh");
		
		explicitWait.until(ExpectedConditions.elementToBeClickable(By.id("login")));
		driver.findElement(By.id("login")).click();
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