package API;

import org.testng.annotations.AfterClass;
import org.testng.annotations.Test;
import org.testng.annotations.BeforeClass;
import org.testng.Assert;
import org.testng.AssertJUnit;

import java.util.Random;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class Topic_14_Javascript_Executor {
	WebDriver driver;
	Alert alert;
	WebDriverWait explicitWait;
	JavascriptExecutor jsExecutor;
	String project_location = System.getProperty("user.dir");

	@BeforeClass
	public void beforeClass() {
		System.setProperty("webdriver.chrome.driver", project_location + "\\BrowserDriver\\chromedriver.exe");
		driver = new ChromeDriver();
		explicitWait = new WebDriverWait(driver, 30);
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
	}

	//@Test
	public void TC_01() {

		// Tuong tac voi browser
		jsExecutor.executeScript("code javascrip");

		// Tuong tac webElement
		jsExecutor.executeScript("code javascrip", driver.findElement(By.xpath("")));

		navigateToUrlByJS("http://live.demoguru99.com/");
		String liveGuruDomain = (String) executeForBrowser("return document.domain;");
		Assert.assertEquals(liveGuruDomain, "live.demoguru99.com");

		String homePageUrl = (String) executeForBrowser("return document.URL;");
		Assert.assertEquals(homePageUrl, "http://live.demoguru99.com/");

		clickToElementByJS("//a[text()='Mobile']");
		clickToElementByJS("");
		String innerText = getInnerText();
		Assert.assertTrue(innerText.contains("Samsung Galaxy was added to your shopping cart."));

		clickToElementByJS("//a[text()='Customer Service']");
		String titlepage = (String) executeForBrowser("return document.title;");
		Assert.assertEquals(titlepage, "Customer Service");

		scrollToElement("//input[@id='newsletter']");
		sendkeyToElementByJS("//input[@id='newsletter']", generateEmail());
		clickToElementByJS("//button[@title='Subscribe']");
		Assert.assertTrue(innerText.contains("Thanks you for your subscribe"));

		navigateToUrlByJS("http://demo.guru99.com/v4/");
		Assert.assertEquals(executeForBrowser("return document.domain;"), "demo.guru99.com");

	}

	//@Test
	public void TC_02() {
		driver.get("");
		driver.findElement(By.name("submit-btn")).click();
		String html5Msg = getElementValidationMessage("//input[@id='fname']");
		System.out.println(html5Msg);
		Assert.assertEquals(html5Msg, "Please fill out this field.");
		
	}
	
	@Test
	public void TC_03() {
		driver.get("https://login.ubuntu.com/");
		driver.findElement(By.xpath("//button[text()='Accept all and visit site']")).click();
		driver.findElement(By.xpath("//div[@class='login-form']//button[@name='continue']")).click();
		String html5Msg = getElementValidationMessage("//form[@id='login-form']//input[@id='id_email']");
		System.out.println(html5Msg);
		Assert.assertEquals(html5Msg, "Please fill out this field.");

	}

	public Object executeForBrowser(String javaScript) {
		jsExecutor = (JavascriptExecutor) driver;
		return jsExecutor.executeScript(javaScript);
	}

	public String getInnerText() {
		jsExecutor = (JavascriptExecutor) driver;
		return (String) jsExecutor.executeScript("return document.documentElement.innerText;");
	}

	public boolean areExpectedTextInInnerText(String textExpected) {
		jsExecutor = (JavascriptExecutor) driver;
		String textActual = (String) jsExecutor
				.executeScript("return document.documentElement.innerText.match('" + textExpected + "')[0]");
		return textActual.equals(textExpected);
	}

	public void scrollToBottomPage() {
		jsExecutor = (JavascriptExecutor) driver;
		jsExecutor.executeScript("window.scrollBy(0,document.body.scrollHeight)");
	}

	public void navigateToUrlByJS(String url) {
		jsExecutor = (JavascriptExecutor) driver;
		jsExecutor.executeScript("window.location = '" + url + "'");
	}

	public void highlightElement(String locator) {
		jsExecutor = (JavascriptExecutor) driver;
		WebElement element = getElement(driver, locator);
		String originalStyle = element.getAttribute("style");
		jsExecutor.executeScript("arguments[0].setAttribute(arguments[1], arguments[2])", element, "style",
				"border: 2px solid red; border-style: dashed;");
		sleepInSecond(1);
		jsExecutor.executeScript("arguments[0].setAttribute(arguments[1], arguments[2])", element, "style",
				originalStyle);
	}

	public void clickToElementByJS(String locator) {
		jsExecutor = (JavascriptExecutor) driver;
		jsExecutor.executeScript("arguments[0].click();", getElement(driver, locator));
	}

	public void scrollToElement(String locator) {
		jsExecutor = (JavascriptExecutor) driver;
		jsExecutor.executeScript("arguments[0].scrollIntoView(true);", getElement(driver, locator));
	}

	public void sendkeyToElementByJS(String locator, String value) {
		jsExecutor = (JavascriptExecutor) driver;
		jsExecutor.executeScript("arguments[0].setAttribute('value', '" + value + "')", getElement(driver, locator));
	}

	public void removeAttributeInDOM(WebDriver driver, String locator, String attributeRemove) {
		jsExecutor = (JavascriptExecutor) driver;
		jsExecutor.executeScript("arguments[0].removeAttribute('" + attributeRemove + "');",
				getElement(driver, locator));
	}

	public boolean areJQueryAndJSLoadedSuccess(WebDriver driver) {
		explicitWait = new WebDriverWait(driver, 30);
		jsExecutor = (JavascriptExecutor) driver;

		ExpectedCondition<Boolean> jQueryLoad = new ExpectedCondition<Boolean>() {
			@Override
			public Boolean apply(WebDriver driver) {
				try {
					return ((Long) jsExecutor.executeScript("return jQuery.active") == 0);
				} catch (Exception e) {
					return true;
				}
			}
		};

		ExpectedCondition<Boolean> jsLoad = new ExpectedCondition<Boolean>() {
			@Override
			public Boolean apply(WebDriver driver) {
				return jsExecutor.executeScript("return document.readyState").toString().equals("complete");
			}
		};

		return explicitWait.until(jQueryLoad) && explicitWait.until(jsLoad);
	}

	public String getElementValidationMessage(WebDriver driver, String locator) {
		jsExecutor = (JavascriptExecutor) driver;
		return (String) jsExecutor.executeScript("return arguments[0].validationMessage;", getElement(driver, locator));
	}

	public boolean isImageLoaded(WebDriver driver, String locator) {
		jsExecutor = (JavascriptExecutor) driver;
		boolean status = (boolean) jsExecutor.executeScript(
				"return arguments[0].complete && typeof arguments[0].naturalWidth != \"undefined\" && arguments[0].naturalWidth > 0",
				getElement(driver, locator));
		if (status) {
			return true;
		} else {
			return false;
		}
	}

	public String getElementValidationMessage(String locator) {
		JavascriptExecutor jsExecutor = (JavascriptExecutor) driver;
		WebElement elm = getElement(driver, locator);
		return (String) jsExecutor.executeScript("return arguments[0].validationMessage;", elm);
	}

	public String generateEmail() {
		Random rand = new Random();
		return "testing" + rand.nextInt(9999) + "@gmail.com";
	}

	public WebElement getElement(WebDriver driver, String xpathLocator) {
		return driver.findElement(By.xpath(xpathLocator));
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