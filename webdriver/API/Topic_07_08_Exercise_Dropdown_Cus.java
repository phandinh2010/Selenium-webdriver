package API;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.Select;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;


public class Topic_07_08_Exercise_Dropdown_Cus {
	WebDriver driver;
	WebElement element;
	Select select;
	String project_location = System.getProperty("user.dir");
	

	@BeforeClass
	public void beforeClass() {

		System.setProperty("webdriver.chrome.driver", project_location+ "\\BrowserDriver\\chromedriver.exe");
		  driver = new ChromeDriver();
		  driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
		  driver.get("https://automationfc.github.io/basic-form/index.html");	
	}

	
		
	//@Test
	public void TC_01_Dropdown_Type1() {
		select = new Select(driver.findElement(By.id("job1")));
		Assert.assertFalse(select.isMultiple());	
		
		//driver.findElement(By.id("job1")).click();
		sleepInSecond(3);
		select.selectByVisibleText("Mobile Testing");
		String getValue = select.getFirstSelectedOption().getText();
		
		Assert.assertEquals(getValue,"Mobile Testing");
		System.out.println(getValue);
		
		select.selectByValue("manual");
		System.out.println(select.getFirstSelectedOption().getText());
		Assert.assertEquals(select.getFirstSelectedOption().getText(),"Manual Testing");
		
		select.selectByIndex(9);
		System.out.println(select.getFirstSelectedOption().getText());
		Assert.assertEquals(select.getFirstSelectedOption().getText(),"Functional UI Testing");
		
		Assert.assertEquals(select.getOptions().size(),10);
	} 
	
	//@Test
	public void TC_02_Droplist_Type2() {
		Select select2 = new Select(driver.findElement(By.id("job2")));
		Assert.assertTrue(select2.isMultiple());
		
		String testing[] = {"automation", "mobile", "desktop"};
		for (String value : testing) {
			select.selectByValue((value));
			sleepInSecond(3);
			
		}
		
		
		List<WebElement> selectedOption = select.getAllSelectedOptions();
		Assert.assertEquals(selectedOption.size(), 3);
		
		List<String> actualValues = new ArrayList<String>();
		for (WebElement option : selectedOption) {
			actualValues .add(option.getText());
		}
		List<String> expectedValues = Arrays.asList("Automation", "Mobile", "Desktop");
		Assert.assertEquals(actualValues, expectedValues);
		
		select2.deselectAll();
		Assert.assertEquals(select.getAllSelectedOptions().size(), 0);
	}
	
	@Test
	public void TC_03() {
		driver.get("https://demo.nopcommerce.com/register");
		driver.findElement(By.xpath("//a[@class='ico-register']"));
		driver.findElement(By.id("gender-male")).click();
		driver.findElement(By.id("FirstName")).sendKeys("Phan");
		driver.findElement(By.id("LastName")).sendKeys("Dinh");
		driver.findElement(By.id("Email")).sendKeys("Dinh@gmail.com");		
		driver.findElement(By.id("Password")).sendKeys("Dinh12345");
		driver.findElement(By.id("ConfirmPassword")).sendKeys("Dinh12345");
		
		
		Select selectDate = new Select(driver.findElement(By.name("DateOfBirthDay")));
		selectDate.selectByValue("1");
		Assert.assertEquals(selectDate.getOptions().size(), 32);
		
		Select selectMonth = new Select(driver.findElement(By.xpath("//select[@name='DateOfBirthMonth']")));
	    selectMonth.selectByValue("5");
	    Assert.assertEquals(selectMonth.getOptions().size(), 13);
	    
	    Select selectYear = new Select(driver.findElement(By.xpath("//select[@name='DateOfBirthYear']")));
	    selectYear.selectByValue("1980");
	    Assert.assertEquals(selectYear.getOptions().size(), 112);
	    
	    driver.findElement(By.id("register-button")).click();
	    Assert.assertTrue(driver.findElement(By.xpath("//div[text()='Your registration completed']")).isDisplayed());
	}
	
	 public void sleepInSecond (long time) {
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