package API;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.concurrent.TimeUnit;

import org.apache.jasper.tagplugins.jstl.core.ForEach;
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

public class Topic_15_Upload_File {
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
	}

	//@Test
	public void TC_01_Upload_multiFiles() {
		driver.get("http://blueimp.github.io/jQuery-File-Upload/");
		driver.manage().window().maximize();
		
		WebElement uploadFiles = driver.findElement(By.xpath("//input[@type='file']"));
		uploadFiles.sendKeys(project_location +"\\UploadFiles\\DInh2.jpg" + "\n" + project_location +"\\UploadFiles\\Dinh3.jpg"
		+ "\n" + project_location +"\\UploadFiles\\cade-prior-qzv0os5eIJQ-unsplash.jpg");
		
		explicitWait.until(ExpectedConditions.visibilityOfAllElements(driver.findElements(By.xpath("//button[@class='btn btn-primary start']  [not(@type='submit')]"))));
		List<WebElement> uploadBtnList= driver.findElements(By.xpath("//button[@class='btn btn-primary start']  [not(@type='submit')]"));
		
		for (WebElement uploadBtn : uploadBtnList) {
			uploadBtn.click();			
		}
		
		explicitWait.until(ExpectedConditions.invisibilityOfElementLocated(By.xpath("//div[@class='progress progress-striped active']")));
		List<WebElement> uploadSuccess = driver.findElements(By.xpath("//p[@class='name']/a[contains(@href,\"http\")]"));
		System.out.println(uploadSuccess.size());
		Assert.assertEquals(uploadSuccess.size(), 3);	
		
	}
	
	
	@Test
	public void TC_02_Upload_ByAutoIT() {
				
		driver.get("http://blueimp.github.com/jQuery-File-Upload/");
		
		
		
		
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