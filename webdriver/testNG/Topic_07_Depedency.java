package testNG;

import java.util.Random;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;

@Listeners(testNG.ReportListener.class) //để report
public class Topic_07_Depedency {

	
	@Test
	public void TC_01_Create() {
		
	}
	
	@Test(dependsOnMethods="TC_01_Create") //neu TC_01_Create fail thì không chạy vào test này
	public void TC_02_Edit() {
		
	}
	
	@Test(dependsOnMethods="TC_01_Create") //neu TC_01_Create fail thì không chạy vào test này
	public void TC_03_View() {
		
	}
	





}
