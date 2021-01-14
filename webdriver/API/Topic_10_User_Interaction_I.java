package API;

import java.util.List;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class Topic_10_User_Interaction_I {
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
	public void TC_01_Hover_Mouse() {
		driver.get("https://tiki.vn/");
		Assert.assertFalse(driver.findElement(By.xpath("//button[text()='Đăng nhập']")).isDisplayed());
		WebElement shortCutAcc = driver.findElement(By.xpath("//div[@data-view-id='header_header_account_container']"));
		action.moveToElement(shortCutAcc).perform();
		Assert.assertTrue(driver.findElement(By.xpath("//button[text()='Đăng nhập']")).isDisplayed());
		sleepInSecond(2);
		
		driver.get("https://jqueryui.com/resources/demos/tooltip/default.html");
		action.moveToElement(driver.findElement(By.cssSelector("#age"))).perform();
		Assert.assertTrue(driver.findElement(By.xpath("//div[@class='ui-tooltip-content']")).isDisplayed());
		
		driver.get("https://www.myntra.com/");
		driver.manage().window().maximize();
		sleepInSecond(2);
		WebElement elmKids = driver.findElement(By.xpath("//a[text()='Kids'][@class='desktop-main']"));
		action.moveToElement(elmKids).perform();
		sleepInSecond(2);
		
		clickToElement(By.xpath("//a[text()='Home & Bath']"));
		sleepInSecond(2);
		Assert.assertEquals(driver.getCurrentUrl(), "https://www.myntra.com/kids-home-bath");
		
		driver.get("https://hn.telio.vn/");
		sleepInSecond(2);
		WebElement elmDrink = driver.findElement(By.xpath("//main[@id='maincontent']//a[@href='/do-uong?source=home']"));
		action.moveToElement(elmDrink).perform();
		sleepInSecond(1);
		clickToElement(By.xpath("//main[@id='maincontent']//a[@text='Bia']"));
		Assert.assertTrue(driver.findElement(By.cssSelector(".groupmenu-drop")).isDisplayed());
		
	}

	//@Test
	public void TC_02_clickAndHold() {
		driver.get("http://jqueryui.com/resources/demos/selectable/display-grid.html");
		List<WebElement> listItems = driver.findElements(By.xpath("//ol[@id='selectable']/li"));
		
		//1. chọn hàng loạt
		action.clickAndHold(listItems.get(0)).moveToElement(listItems.get(3)).release().perform();
		sleepInSecond(3);
		
		 List<WebElement> numberSelected = driver.findElements(By.xpath("//li[contains(@class,'ui-selected')]"));
		 System.out.println("number selected is " +numberSelected.size());
	
	Assert.assertEquals(numberSelected.size(),4);
	
	//2.chọn từng số
	
	//Nhấn phím Ctrl xuống
	action.keyDown(Keys.CONTROL).perform();
	
	action.click(listItems.get(0)).
	click(listItems.get(2)).
	click(listItems.get(4)).
	click(listItems.get(6));
	
	//Nhả phím Ctrl ra
	action.keyUp(Keys.CONTROL).perform();
	sleepInSecond(2);
	}

	//@Test
	public void TC_05_doubleClick() {
		driver.get("https://automationfc.github.io/basic-form/index.html");
		driver.manage().window().maximize();
		WebElement element = driver.findElement(By.xpath("//button[text()='Double click me']"));
		action.doubleClick(element).perform();
		sleepInSecond(2);
		Assert.assertEquals(driver.findElement(By.xpath("//p[@id='demo']")).getText(), "Hello Automation Guys!");
		
	}
	
	@Test
	public void TC_06_rightClick() {
		driver.get("http://swisnl.github.io/jQuery-contextMenu/demo.html");
		driver.manage().window().maximize();
		WebElement element = driver.findElement(By.xpath("//span[text()='right click me']"));
		action.contextClick(element).perform();
		sleepInSecond(2);
		
		action.moveToElement(driver.findElement(By.xpath("//span[text()='Quit']"))).perform();
		Assert.assertEquals(driver.findElement(By.xpath("//p[@id='demo']")).getText(), "Hello Automation Guys!");
		
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