package API;

import org.testng.annotations.Test;

import com.google.common.base.Function;

import org.testng.annotations.BeforeClass;
import org.testng.Assert;
import org.testng.AssertJUnit;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.FluentWait;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.AfterClass;

public class Topic_16_Wait_VII_Fluence {
	WebDriver driver;
	FluentWait<WebDriver> fluentDriver;
	FluentWait<WebElement> fluentElement;
	WebDriverWait explicitWait;
	WebElement element;
	long timeoutInSecond = 15;
	long intervalInMiliSecond = 300;
	String project_location = System.getProperty("user.dir");

	@BeforeClass
	public void beforeClass() {
		System.setProperty("webdriver.chrome.driver", project_location + "\\BrowserDriver\\chromedriver.exe");
		driver = new ChromeDriver();

	}

	@Test
	public void TC_09_Fluent() {

		driver.get("https://automationfc.github.io/fluent-wait/");
		WebElement countDownTime = driver.findElement(By.xpath("//div[@id='javascript_countdown_time']"));

		fluentElement = new FluentWait<WebElement>(countDownTime);

		fluentElement.withTimeout(15, TimeUnit.SECONDS).pollingEvery(300, TimeUnit.MILLISECONDS)
				.ignoring(NoSuchElementException.class);

		fluentElement.until(new Function<WebElement, Boolean>() {
			@Override
			public Boolean apply(WebElement countDownTime) {
				String text = countDownTime.getText();
				System.out.println(text);
				return text.endsWith("00");
			}

		});

	}

	@Test
	public void TC_010_Fluent() {

		driver.get("http://the-internet.herokuapp.com/dynamic_loading/2");
		waitForElementAndClick(By.xpath("//div[@id='start']/button"));
		isELementDisplayed(By.xpath("//div[@id='finish']/h4"));
	}

	public WebElement getElement(By locator) {
		FluentWait<WebDriver> wait = new FluentWait<WebDriver>(driver).withTimeout(timeoutInSecond, TimeUnit.SECONDS)
				.pollingEvery(300, TimeUnit.MILLISECONDS).ignoring(NoSuchElementException.class);

		WebElement element = wait.until(new Function<WebDriver, WebElement>() {
			public WebElement apply(WebDriver driver) {
				return driver.findElement(locator);
			}

		});
		return element;
	}

	public void waitForElementAndClick(By locator) {
		WebElement element = getElement(locator);
		element.click();
		;
	}

	public boolean isELementDisplayed(By locator) {
		WebElement element = getElement(locator);
		FluentWait<WebElement> wait = new FluentWait<WebElement>(element);
		boolean isDisplay = wait.until(new Function<WebElement, Boolean>() {
			public Boolean apply(WebElement element) {
				boolean flag = element.isDisplayed();
				return flag;
			}
		});
		return isDisplay;
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