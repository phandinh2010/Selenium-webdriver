package API;

import org.testng.annotations.Test;
import org.testng.annotations.BeforeClass;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.annotations.AfterClass;

public class Topic_02_Xpath {
	WebDriver driver;//Biến toàn cục
  
  @BeforeClass
  public void beforeClass() { //Hàm
	  driver = new FirefoxDriver();
	  driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
	  driver.manage().window().maximize();
	  driver.get("http://live.demoguru99.com/index.php/customer/account/login/"); 
	  //biến cục bộ
  }
  
  //@Test
  public void TC_01_ID() throws InterruptedException {
	  driver.findElement(By.id("email")).sendKeys("phandinh2010@gmail.com");
	  driver.findElement(By.id("pass")).sendKeys("123456");
	  Thread.sleep(30000);
  }
  
  @Test
  public void TC_02_Class() throws InterruptedException {
	  driver.navigate().refresh();
	  driver.findElement(By.className("validate-password")).sendKeys("1234567888");
	  driver.findElement(By.className("account-login")).isDisplayed();
	  Thread.sleep(3000);
	  
  }
  
  //@Test
  public void TC_03_Name() throws InterruptedException {
	  driver.findElement(By.name("login[username]")).sendKeys("122343434");
	  Thread.sleep(30000);
	  
  }

  @Test
  public void TC_04_TagName() {
	  System.out.println("Tổng link số link trên trang = "+ driver.findElements(By.tagName("a")).size());
	  
	  
  }
  
  @Test
  public void TC_05_LinkTest() throws InterruptedException {
	  //Text của lilnk tuyệt đối (toàn bộ chuỗi)
	  driver.findElement(By.linkText("Forgot Your Password?")).click();
	  Thread.sleep(30000);
	  
  }
  
  @Test
  public void TC_06_Partial_LinkText() throws InterruptedException {
	  //Text của lilnk tương đối (t1 phần chuỗi)
	  driver.findElement(By.partialLinkText("Forgot Your")).click();
	  Thread.sleep(30000);
	  
  }
  
  @Test
  public void TC_07_Selector() {
	  // tagname[attribute='value']
	  //nếu attribute là Id có thể thay bằng #, class có thể thay bằng .
	  
  }
  
  @Test
  public void TC_08_Xpath() {
	  driver.get("https://login.ubuntu.com/");
	  driver.findElement(By.xpath("")).sendKeys("");
	  sleepInSecond(3);
  }
  
  public void sleepInSecond (long time) {
	  try {
		Thread.sleep(time * 1000);
	} catch (InterruptedException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
  }
  @AfterClass
  public void afterClass() {
  }

}
