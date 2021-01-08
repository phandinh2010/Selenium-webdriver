package API;

import java.util.List;
import java.util.concurrent.TimeUnit;


import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class Topic_04_WebBrowser_Command {
	WebDriver driver;
	WebElement element;
	List <WebElement> elements;
  
  @BeforeClass
  public void beforeClass() {
	
	  
	  
  }

  @Test
  public void TC_01_Web_Browser() { 
  driver = new ChromeDriver();
  driver.close();
  driver.quit();
  driver.get("url");
  driver.getCurrentUrl();
  driver.manage().timeouts().implicitlyWait(3, TimeUnit.SECONDS);
  driver.manage().timeouts().pageLoadTimeout(3,TimeUnit.SECONDS);
  driver.manage().timeouts().setScriptTimeout(13, TimeUnit.SECONDS);//Js executor
  
  
  driver.manage().window().fullscreen();
  driver.manage().window().maximize();
  //driver.manage().window().setSize(arg0);
  
  driver.navigate().back();
  driver.navigate().forward();
  driver.navigate().refresh();
  driver.navigate().back();
  
  //Alert/Iframe/Window(tab)
  driver.switchTo().alert();
  driver.switchTo().frame("");
  driver.switchTo().window("");		
  }
  
  
  @Test
  public void TC_01_Web_Elements() { 
  driver = new ChromeDriver();
  //kiểu dữ liệu nguyên thủy: byte, short, int, long, float, double, boolean, char
  //kiểu dữ liệu tham chiếu: String, Class, object, Interface, Array, Collection(List/Set), Map
  
  String homePageUrl = driver.getCurrentUrl();
Assert.assertEquals(homePageUrl, "");
  
  }    
  
  @AfterClass
  public void after() {
	  driver.quit();
  }
}