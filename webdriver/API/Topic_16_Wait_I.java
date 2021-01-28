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

		action = new Actions(driver);
	}

	//@Test
	public void TC_01_Iframe() {
		driver.get("https://kyna.vn/");
		driver.manage().window().maximize();
		
		//explicitWait.until(ExpectedConditions.frameToBeAvailableAndSwitchToIt(By.xpath("//iframe[contains(@src,'//www.facebook.com')]")));
				//WebElement elmIframeFB = driver.findElement(By.xpath("//iframe[contains(@src,'//www.facebook.com')]"));
		//Assert.assertTrue(driver.findElement(By.xpath("//iframe[contains(@src,'//www.facebook.com')]")).isDisplayed());
		driver.switchTo().frame(driver.findElement(By.xpath("//iframe[contains(@src,'//www.facebook.com')]")));
		Assert.assertEquals(driver.findElement(By.xpath("//div[@class='_1drq']")).getText(), "169K likes");

		//WebElement elmIframeChat = driver.findElement(By.xpath("//iframe[@id='cs_chat_iframe']"));
		//Assert.assertTrue(elmIframeChat.isDisplayed());
		// driver.switchTo().frame("cs_chat_iframe]");
		// driver.findElement(By.xpath("//textarea[@ng-model='chatMessage.content']")).sendKeys("Automation");
		// driver.findElement(By.xpath("//textarea[@ng-model='chatMessage.content']")).sendKeys(Keys.ENTER);
		// Assert.assertTrue(driver.findElement(By.xpath("")).isDisplayed());
		 

		driver.switchTo().defaultContent();
		driver.findElement(By.cssSelector("#navDesktop .live-search-bar")).sendKeys("Excel");
		clickToElement(By.cssSelector(".search-button"));
		sleepInSecond(2);

		List<WebElement> elmName = driver.findElements(By.xpath("//div[@class='content']//h4"));

		List<String> nameResuilt = new ArrayList<String>();
		for (WebElement resuilt : elmName) {
			System.out.println(resuilt.getText());
			nameResuilt.add(resuilt.getText());
		}
		for (String courseName : nameResuilt) {
			Assert.assertTrue(courseName.contains("Excel"));
		}
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