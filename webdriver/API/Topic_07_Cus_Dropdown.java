package API;

import java.awt.List;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class Topic_07_Cus_Dropdown {
	WebDriver driver;
	WebDriverWait expicitWait;
	String project_location = System.getProperty("user.dir");

	@BeforeClass
	public void beforeClass() {
		expicitWait = new WebDriverWait(driver, 30);

		System.setProperty("webdriver.firefox.driver", project_location + "\\BrowserDriver\\chromedriver.exe");
		driver = new ChromeDriver();
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
		driver.manage().window().maximize();
		driver.get("https://jqueryui.com/resources/demos/selectmenu/default.html");
	}

	@Test
	public void TC_01() {
		selectItemCusDropdown("//span[@id='number-button']", "//ul[@id='number-menu']/li");
	}

	public void selectItemCusDropdown(String parentXpath, String allItemXpath) {
		driver.findElement(By.xpath(parentXpath)).click();
		
		//chờ để load tât cả các item trong droplist
		expicitWait.until(ExpectedConditions.presenceOfAllElementsLocatedBy(By.xpath(allItemXpath)));
		
		//Luu lại vào 1 list WebEelement
		List<WebElement> allItems = driver.findElement(By.xpath(allItemXpath));
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