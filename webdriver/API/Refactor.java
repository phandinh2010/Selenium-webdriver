package API;

import org.testng.annotations.AfterClass;
import org.testng.annotations.Test;
import org.testng.annotations.BeforeClass;
import org.testng.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;


public class Refactor {
	WebDriver driver;
	By emailTextbox = By.cssSelector("#mail");
	By educationTextArea = By.cssSelector("#edu");
	By ageUnder18 = By.cssSelector("#edu");
	By nameText = By.xpath("//h5[text()='Name:User5']");
	By slider1 = By.cssSelector("#slider-1");
	By slider2 = By.cssSelector("#slider-2");
	By javaLanguageCheckbox = By.cssSelector("#Java");
	
	
	@BeforeClass
	  public void beforeClass() {
	  }
	
	
	@Test
  public void TC_01_Displayed() {
		driver.get("https://automationfc.github.io/basic-form/index.html");
		
		if (isElementDisplayed(emailTextbox)) {
			sendkeyToElement(emailTextbox, "string");
		}
		
		if (isElementDisplayed(educationTextArea)) {
			sendkeyToElement(educationTextArea, "string");
		}
		
		
		if (isElementDisplayed(ageUnder18 )) {
			clickToElement(ageUnder18 );
		}
		
		Assert.assertFalse(!isElementDisplayed(nameText));		
		
  }
  
	
	@Test
	public void TC_02_Enabled() {
		driver.get("https://automationfc.github.io/basic-form/index.html");
		Assert.assertTrue(isElementEnabled(emailTextbox));		
		Assert.assertTrue(isElementEnabled(slider1));		
		Assert.assertTrue(isElementEnabled(slider2));		
		
	}
	
	
	@Test
	public void TC_03_Selected() {
		driver.get("https://automationfc.github.io/basic-form/index.html");
		clickToElement(ageUnder18);
		clickToElement(javaLanguageCheckbox);
		Assert.assertTrue(isElementSelected(ageUnder18));		
		Assert.assertTrue(isElementSelected(javaLanguageCheckbox));		

		clickToElement(ageUnder18);
		clickToElement(javaLanguageCheckbox);

		Assert.assertFalse(isElementSelected(ageUnder18));		
		Assert.assertFalse(isElementSelected(javaLanguageCheckbox));		

	}
	
	
	public boolean isElementDisplayed (By by) {
		WebElement element = driver.findElement(by);
		if (element.isDisplayed()) {
			System.out.println("Element is displayed");
			return true;	
		} else {
			System.out.println("Element is not displayed");
			return false;
		}
	}
	
	public boolean isElementEnabled (By by) {
		WebElement element = driver.findElement(by);
		if (element.isEnabled()) {
			System.out.println("Element is enabled");
			return true;	
		} else {
			System.out.println("Element is disabled");
			return false;
		}
	}	
	
	public boolean isElementSelected (By by) {
		WebElement element = driver.findElement(by);
		if (element.isSelected()) {
			System.out.println("Element is selected");
			return true;	
		} else {
			System.out.println("Element is disselected");
			return false;
		}
	}
	
	public void sendkeyToElement(By by, String value) {
		WebElement element = driver.findElement(by);
				element.clear();
		element.sendKeys(value);
	}
	
	public void clickToElement (By by) {
		WebElement element = driver.findElement(by);
		element.click();
				
	}

  @AfterClass
  public void afterClass() {
  driver.quit();
  }

}
