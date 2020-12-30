package API;

import java.util.List;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class Topic_07_Cus_Dropdown {
	WebDriver driver;
	WebDriverWait explicitWait;
	Select select;
	JavascriptExecutor jsExecutor;
	String project_location = System.getProperty("user.dir");
	String[] firstMonths = {"January", "April", "July"};
	String[] secondMonths = {"January", "April", "July", "September", "December"};

	@BeforeClass
	public void beforeClass() {
		System.setProperty("webdriver.chrome.driver", project_location + "\\BrowserDriver\\chromedriver.exe");
		driver = new ChromeDriver();
		explicitWait = new WebDriverWait(driver, 30);
		jsExecutor = (JavascriptExecutor) driver;
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
		driver.manage().window().maximize();

	}

	// @Test
	public void TC_01_JQuery() {
		driver.get("https://jqueryui.com/resources/demos/selectmenu/default.html");
		selectItemCusDropdown("//span[@id='number-button']", "//ul[@id='number-menu']//div", "5");
		sleepInSecond(2);
		Assert.assertEquals(driver
				.findElement(By.xpath("//span[@id='number-button']//span[@class='ui-selectmenu-text']")).getText(),
				"5");

		selectItemCusDropdown("//span[@id='number-button']", "//ul[@id='number-menu']//div", "10");
		sleepInSecond(2);
		Assert.assertEquals(driver
				.findElement(By.xpath("//span[@id='number-button']//span[@class='ui-selectmenu-text']")).getText(),
				"10");
	}

	// @Test
	public void TC_02_Angular() {
		driver.get(
				"https://ej2.syncfusion.com/angular/demos/?_ga=2.262049992.437420821.1575083417-524628264.1575083417#/material/drop-down-list/data-binding");

		selectItemCusDropdown("//span[@aria-labelledby='games_hidden']", "//div[@class='e-content e-dropdownbase']//li",
				"American Football");
		sleepInSecond(2);
		select = new Select(driver.findElement(By.xpath("//select[@id='games_hidden']")));
		Assert.assertEquals(getSelectedValueByJS(), "American Football");

	}

	// @Test
	public void TC_03_React() {
		driver.get("https://react.semantic-ui.com/maximize/dropdown-example-selection/");
		selectItemCusDropdown("//i[@class='dropdown icon']", "//div[@class='visible menu transition']//span",
				"Christian");
		sleepInSecond(2);
		Assert.assertTrue(driver.findElement(By.xpath("//div[text()='Christian']")).isDisplayed());
	}

	// @Test
	public void TC_04_VesJS() {
		driver.get("https://mikerodham.github.io/vue-dropdowns/");
		selectItemCusDropdown("//li[@class='dropdown-toggle']", "//ul[@class='dropdown-menu']//a", "Third Option");
		sleepInSecond(2);
		Assert.assertEquals(driver.findElement(By.xpath("//li[@class='dropdown-toggle']")).getText(), "Third Option");
	}

	//@Test
	public void TC_05_EditAble() {
		driver.get("http://indrimuska.github.io/jquery-editable-select/");

		selectItemCusDropdown2("//div[@id='default-place']/input", "//div[@id='default-place']//li", "Fiat");
		sleepInSecond(2);
		// Assert.assertTrue(driver.findElement(By.xpath("//li[@class='es-visible
		// selected']")).isDisplayed());
		Assert.assertEquals(
				driver.findElement(By.xpath("//ul[@class='es-list']//li[@class='es-visible selected']")).getText(),
				"Fiat");
	}
	
	@Test
	public void TC_06_MultiSelect() {
		driver.get("http://multiple-select.wenzhixin.net.cn/templates/template.html?v=189&url=basic.html");
		selectMultiDropdown("(//button[@class='ms-choice'])[1]", "(//button[@class='ms-choice'])[1]/following-sibling::div//span", firstMonths);
		Assert.assertTrue(areItemSelected(firstMonths));
	}

	// Common
	public void selectMultiDropdown(String parentXpath, String childXpath, String[] expectedValueitem) {
		driver.findElement(By.xpath(parentXpath)).click();
		explicitWait.until(ExpectedConditions.presenceOfAllElementsLocatedBy(By.xpath(childXpath)));
		List<WebElement> allItems = driver.findElements(By.xpath(childXpath));
		for (WebElement childElement : allItems) {
			for (String item : expectedValueitem) {
				if (childElement.getText().equals(item)) {
					childElement.click();
					sleepInSecond(2);

					List<WebElement> itemSelected = driver.findElements(By.xpath("//li[@class='selected']"));
					System.out.println("Item selected = " + itemSelected);
					if (expectedValueitem.length == itemSelected.size()) {
						break;
					}
				}
			}
		}

	}
	
	public boolean areItemSelected(String[] itemSelectedText) {
		List<WebElement> itemSelected = driver.findElements(By.xpath("//li[@class='selected']"));
		int numberItemSelected = itemSelected.size();
		
		String allItemSelectedText = driver.findElement(By.xpath("(//button[@class='ms-choice']/span)[1]")).getText();
		System.out.println("text đã chọn là: "+ allItemSelectedText);
		
		if (numberItemSelected <=3 && numberItemSelected>0) {
			for (String item:itemSelectedText) {
				if(allItemSelectedText.contains(item)) {
					break;
				}
			}
			return true;
		} else {
			return driver.findElement(By.xpath("//button[@class='ms-choice']/span[text()=" + numberItemSelected + "of 12 selected")).isDisplayed();
		}
	}

	public void selectItemCusDropdown(String parentXpath, String allItemXpath, String expectedValueItem) {
		driver.findElement(By.xpath(parentXpath)).click();

		// chờ để load tât cả các item trong droplist
		explicitWait.until(ExpectedConditions.presenceOfAllElementsLocatedBy(By.xpath(allItemXpath)));
		// sleepInSecond(3);

		// Luu lại vào 1 list WebEelement
		List<WebElement> allItems = driver.findElements(By.xpath(allItemXpath));
		// int allItemsNumber = allItems.size();

		// Duyệt qua từng element và lấy ra text
		// Traditon for
		/*
		 * for (int i = 0; i < allItemsNumber; i++) { String actualValueItem =
		 * allItems.get(i).getText();
		 * 
		 * if (actualValueItem.equals(expectedValueItem)) { allItems.get(i).click();
		 * break; } }
		 */

		// for each

		for (WebElement item : allItems) {
			String actualValueItem = item.getText();
			if (actualValueItem.equals(expectedValueItem)) {
				item.click();
				break;
			}
		}

	}

	public void selectItemCusDropdown2(String parentXpath, String allItemXpath, String expectedValueItem) {
		driver.findElement(By.xpath(parentXpath)).sendKeys("a");

		// chờ để load tât cả các item trong droplist
		explicitWait.until(ExpectedConditions.presenceOfAllElementsLocatedBy(By.xpath(allItemXpath)));
		// sleepInSecond(3);

		// Luu lại vào 1 list WebEelement
		List<WebElement> allItems = driver.findElements(By.xpath(allItemXpath));
		// int allItemsNumber = allItems.size();

		// Duyệt qua từng element và lấy ra text
		// Traditon for
		/*
		 * for (int i = 0; i < allItemsNumber; i++) { String actualValueItem =
		 * allItems.get(i).getText();
		 * 
		 * if (actualValueItem.equals(expectedValueItem)) { allItems.get(i).click();
		 * break; } }
		 */

		// for each

		for (WebElement item : allItems) {
			String actualValueItem = item.getText();
			if (actualValueItem.equals(expectedValueItem)) {
				item.click();
				break;
			}
		}

	}

	public String getSelectedValueByJS() {
		return (String) jsExecutor.executeScript("return document.querySelector(\"select[name='game'] option\").text");
	}

	public void sleepInSecond(long time) {
		try {
			Thread.sleep(time * 1000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}

	@AfterClass
	public void afterClass() {
		driver.quit();
	}

}