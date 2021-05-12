package testNG;

import java.util.Random;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

public class Topic_06_Loop {

	WebDriver driver;
	String project_location = System.getProperty("user.dir");

	@BeforeClass
	public void beforeClass() {

		System.setProperty("webdriver.chrome.driver", project_location + "\\BrowserDriver\\chromedriver.exe");
		driver = new ChromeDriver();
		driver.manage().timeouts().implicitlyWait(15, TimeUnit.SECONDS);

	}
//lặp lại testcasse với bộ dữ liệu khác nhau
	@Test(dataProvider = "register", invocationCount =10)
	public void TC_02_Register_User(String emailAddress, String password) {
		driver.get("http://live.demoguru99.com/index.php/customer/account/create/");
		driver.findElement(By.id("firstname")).sendKeys("test");
		driver.findElement(By.id("lastname")).sendKeys("auto");
		driver.findElement(By.id("email_address")).sendKeys(emailAddress);
		driver.findElement(By.id("password")).sendKeys(password);
		driver.findElement(By.id("confirmation")).sendKeys(password);
		driver.findElement(By.xpath("//button[@title='Register']")).click();

		driver.findElement(By.xpath("//header[@id='header']//span[text()='Account']")).click();
		driver.findElement(By.xpath("//a[text()='Log Out']")).click();

	}

	@DataProvider(name = "register")
	public String[][] Register_Data() {
		return new String[][] { { "selenium" + getRandomNumber() + "@gmail.com", "111111" }};
				
	}

	public int getRandomNumber() {
		Random rand = new Random();
		return rand.nextInt(9999);

	}

}
