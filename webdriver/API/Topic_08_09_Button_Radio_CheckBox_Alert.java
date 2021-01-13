package API;

import java.util.List;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class Topic_08_09_Button_Radio_CheckBox_Alert {
	WebDriver driver;
	Alert alert;
	WebDriverWait explicitWait;
	JavascriptExecutor jsExecutor;
	String project_location = System.getProperty("user.dir");
	By loginTab = By.cssSelector(".popup-login-tab-login");
	By email = By.cssSelector("#login_username");
	By pass = By.cssSelector("#login_password");
	By loginBtn = By.cssSelector(".fhs-btn-login");
	By errorMsgEmail = By
			.xpath("(//label[text()='Số điện thoại/Email']/following-sibling::div[@class='fhs-input-alert'])[1]");
	By errorMsgPass = By.xpath("(//label[text()='Mật khẩu']/following-sibling::div[@class='fhs-input-alert'])[1]");

	@BeforeClass
	public void beforeClass() {

		System.setProperty("webdriver.chrome.driver", project_location + "\\BrowserDriver\\chromedriver.exe");
		driver = new ChromeDriver();
		explicitWait = new WebDriverWait(driver,30);
		jsExecutor = (JavascriptExecutor) driver;		
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);

	}

	// @Test
	public void TC_01_Button() {
		driver.get("https://www.fahasa.com/customer/account/create?attempt=1");
		clickToElement(loginTab);
		Assert.assertFalse(isElementEnabled(loginBtn));

		senkeyToElement(email, "testing@gmail.com");
		senkeyToElement(pass, "1234567");
		sleepInSecond(2);
		Assert.assertTrue(isElementEnabled(loginBtn));

		driver.navigate().refresh();
		clickToElement(loginTab);
		removeDisableAttributeByJS(loginBtn);
		sleepInSecond(5);
		Assert.assertTrue(isElementEnabled(loginBtn));
		sleepInSecond(3);
		clickToElement(loginBtn);
		Assert.assertEquals(driver.findElement(errorMsgEmail).getText(), "Thông tin này không thể để trống");
		Assert.assertEquals(driver.findElement(errorMsgPass).getText(), "Thông tin này không thể để trống");

	}

	// @Test
	public void defaulChecbox() {
		driver.get("http://demos.telerik.com/kendo-ui/styling/checkboxes");
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
		driver.manage().window().maximize();

		// select all
		List<WebElement> checkboxes = driver.findElements(By.xpath("//input[@type='checkbox']"));
		for (WebElement checkbox : checkboxes) {
			checkToCheckboxRadio(checkbox);
			sleepInSecond(1);
		}

		// verify select all
		for (WebElement checkbox : checkboxes) {
			Assert.assertTrue(isElementSelected(checkbox));
			sleepInSecond(1);
		}

		// Deselect all
		for (WebElement checkbox : checkboxes) {
			uncheckToCheckbox(checkbox);
			sleepInSecond(1);
		}

		// verify deselect all
		for (WebElement checkbox : checkboxes) {
			Assert.assertFalse(isElementSelected(checkbox));
			sleepInSecond(1);
		}
	}

	// @Test
	public void TC_02_defaulChecboxRadio() {

		driver.get("http://demos.telerik.com/kendo-ui/styling/checkboxes");
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
		driver.manage().window().maximize();
		sleepInSecond(3);

		WebElement elementCheckbox = driver
				.findElement(By.xpath("//label[text()='Dual-zone air conditioning']/preceding-sibling::input"));
		checkToCheckboxRadio(elementCheckbox);
		Assert.assertTrue(isElementSelected(elementCheckbox));

		uncheckToCheckbox(elementCheckbox);
		Assert.assertFalse(isElementSelected(elementCheckbox));

		driver.get("https://demos.telerik.com/kendo-ui/radiobutton/index");
		WebElement elementRadio = driver
				.findElement(By.xpath("//label[text()='2.0 Petrol, 147kW']/preceding-sibling::input"));
		checkToCheckboxRadio(elementRadio);
		Assert.assertTrue(isElementSelected(elementRadio));

	}

	//@Test
	public void TC_03_custom() {
		driver.get("https://material.angular.io/components/radio/examples");
		clickToElementByJS(driver.findElement(By.xpath("//input[@value='Summer']")));
		Assert.assertTrue(isElementSelected(driver.findElement(By.xpath("//input[@value='Summer']"))));

		driver.get("https://material.angular.io/components/checkbox/examples");
		WebElement elmChecked = driver
				.findElement(By.xpath("//span[contains(text(),'Checked')] /preceding-sibling::span/input"));
		WebElement elmIndeterminate = driver
				.findElement(By.xpath("(//span[contains(text(),'Indeterminate')] /preceding-sibling::span/input)[1]"));
		clickToElementByJS(elmChecked);
		clickToElementByJS(elmIndeterminate);
		sleepInSecond(2);

		Assert.assertTrue(isElementSelected(elmChecked));
		Assert.assertTrue(isElementSelected(elmIndeterminate));
	}

	@Test
	public void TC_04_acceptAlert() {
		driver.get("https://automationfc.github.io/basic-form/index.html");
		clickToElement(By.xpath("//button[text()='Click for JS Alert']"));
		explicitWait.until(ExpectedConditions.alertIsPresent());
		alert = driver.switchTo().alert();
		String textOnAlert = alert.getText();
		Assert.assertEquals(textOnAlert, "I am a JS Alert");
		alert.accept();
		sleepInSecond(1);
		Assert.assertEquals(driver.findElement(By.cssSelector("#result")).getText(),
				"You clicked an alert successfully");

	}
	//@Test
	public void TC_05_confirmAlert() {
		driver.get("https://automationfc.github.io/basic-form/index.html");
		clickToElement(By.xpath("//button[text()='Click for JS Confirm']"));
		explicitWait.until(ExpectedConditions.alertIsPresent());
		Alert alert = driver.switchTo().alert();
		String textOnAlert = alert.getText();
		Assert.assertEquals(textOnAlert, "I am a JS Confirm");
		alert.dismiss();
		sleepInSecond(1);
		Assert.assertEquals(driver.findElement(By.cssSelector("#result")).getText(),
				"You clicked: Cancel");
		
	}
	//@Test
	public void TC_06_promptAlert() {
		driver.get("https://automationfc.github.io/basic-form/index.html");
		clickToElement(By.xpath("//button[text()='Click for JS Prompt']"));
		Alert alert = driver.switchTo().alert();
		String textOnAlert = alert.getText();
		Assert.assertEquals(textOnAlert, "I am a JS prompt");
		alert.sendKeys("Phandinh Tesing");
		alert.accept();
		sleepInSecond(1);
		Assert.assertEquals(driver.findElement(By.cssSelector("#result")).getText(),
				"You entered: Phandinh Tesing");
		
	}
	
	//@Test
	public void TC_07_authenticationAlertByLink() {
		//String usernameAndPass = "admin";
		String url = "http://the-internet.herokuapp.com/basic_auth";
		url = "http://admin:admin@the-internet.herokuapp.com/basic_auth";
		driver.get(url);
		Assert.assertEquals(driver.findElement(By.xpath("//div[@class='example']/p")).getText(), "Congratulations! You must have the proper credentials.");
		
	}
	@Test
	public void TC_07_authenticationAlertByAutoIT() {
		//String usernameAndPass = "admin";
		String url = "http://the-internet.herokuapp.com/basic_auth";
		url = "http://admin:admin@the-internet.herokuapp.com/basic_auth";
		driver.get(url);
		Assert.assertEquals(driver.findElement(By.xpath("//div[@class='example']/p")).getText(), "Congratulations! You must have the proper credentials.");
		
	}

	public void checkToCheckboxRadio(WebElement element) {
		if (!element.isSelected()) {
			element.click();
		}
	}

	public void uncheckToCheckbox(WebElement element) {
		if (element.isSelected()) {
			element.click();
		}
	}

	public boolean isElementSelected(WebElement element) {
		if (element.isSelected()) {
			System.out.println("Element is selected");
			return true;
		} else {
			System.out.println("Element is de-selected");
			return false;
		}
	}

	public void clickToElement(By by) {
		WebElement element = driver.findElement(by);
		element.click();
	}

	public void senkeyToElement(By by, String value) {
		WebElement element = driver.findElement(by);
		element.sendKeys(value);
	}

	public void removeDisableAttributeByJS(By by) {
		WebElement element = driver.findElement(by);
		jsExecutor.executeScript("arguments[0].removeAttribute('disabled')", element);
	}

	public void clickToElementByJS(WebElement element) {
		jsExecutor.executeScript("arguments[0].click();", element);
	}

	public boolean isElementEnabled(By by) {
		WebElement element = driver.findElement(by);
		if (element.isEnabled()) {
			System.out.println("Element is enabled");
			return true;
		} else {
			System.out.println("Element is disabled");
			return false;
		}
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